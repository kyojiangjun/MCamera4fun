package com.pinguo.mcamera4fun.camera;

import java.util.ArrayList;
import java.util.List;

import com.pinguo.mcamera.common.log.GLogger;
import com.pinguo.mcamera4fun.R;
import com.pinguo.mcamera4fun.camera.CameraHolder.CameraType;
import com.pinguo.mcamera4fun.utils.Util;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;

public class FocusManager {

	String TAG = "FocusManager";
	Context mContext;
	String[] mDefaultFocusMode;

	public enum FocusState {
		/**
		 * Focus is not active, camera can call auto focus
		 */
		IDLE, 
		/**
		 * Focus is in progress
		 */
		FOCUSING, 
		/**
		 * Focus is in progress and the camera will take a picture
		 * after focus finishes
		 */
		FOCUSING_SNAP_ON_FINISH, 
		/**
		 * camera is close
		 */
		CAMERA_CLOSE, SUCCESS, FAIL
	}
	FocusState mFocusState;
	
	boolean isInitialized = false;
	boolean mIsFocusSupported;
	boolean mFocusAreaSupported;
	boolean mMeteringAreaSupported;
	boolean mLockAeAwbNeeded;
	boolean isFocusing = false;
	boolean isPreviewPaused = false;
	boolean isTouchFocus = false;
	
	private List<Area> mFocusArea; // focus area in driver format
	private List<Area> mMeteringArea; // metering area in driver format
	
	CameraHolder mCameraHolder;
	
	int mPreviewWidth;
	int mPreviewHeight;
	Matrix mMatrix;
	
	boolean mLastAutoFocusSuccess = false;
	long mLastAutoFocusTime;
	long mLastAutoFocusFailTime;
	boolean mLastFocusSuccess;
	long mLastFocusMillis;
	
	final long MIN_FOCUS_TIME = 1600;
	final long MIN_FOCUS_FAILED_TIME = 2000;
	final long CAPTURE_DELAY_MILLIS_AFTER_FOCUS = 100;
	final long RESET_TOUCH_FOCUS_DELAY_MILLIS = 1000;
	
	public final long RESET_USER_FOCUS_MODE_MILLIS = 3000;
	public final long RESET_USER_FOCUS_MODE_MILLIS_MACRO = 10000;
	
	public final long RESET_FOCUS_STATE_MILLIS = 2000;
	public final long RESET_FOCUS_STATE_MILLIS_MACRO = 10000;
	
	private long mSmartFocusCDMillis = RESET_USER_FOCUS_MODE_MILLIS;
	private long mResetFocusStateMillis = RESET_FOCUS_STATE_MILLIS;
	
	public interface CameraOpCallback {
		public void autoFocus();
		public void cancelAutoFocus();
		public void onResetFocusArea();
		public void startFaceDetection();
		public void stopFaceDetection();
		public boolean capture();
		public void setFocusParams();
	}
	
	public interface UiCallback {
		public void showStart();
		public void showSuccess(boolean flag);
		public void showFail(boolean flag);
		public void clear();
		
		public void pauseFaceview();
		public void resumeFaceview();
		
		public Point getFocusSize();
		public void setFocusArea(int posX, int posY, int previewW, int previewH);
	}
	
	private CameraOpCallback mListener;
	private UiCallback mCallback;
	
	DistanceManager mDistanceManager;
	
	public void setCallback(UiCallback callback) {
		this.mCallback = callback;
	}
	
	public void setListener(CameraOpCallback listener) {
		this.mListener = listener;
	}
	
	private static final int MSG_RESET_TOUCH_FOCUS = 0;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case MSG_RESET_TOUCH_FOCUS:
				cancelAutoFocus();
				mListener.startFaceDetection();
				break;
			}
		}
	};
	
	public void initialize(int width, int height) {
		mPreviewWidth = width;
		mPreviewHeight = height;
		
		boolean mirror = mCameraHolder.mCameraType == CameraType.FRONT;
		Matrix matrix = new Matrix();
		Util.prepareMatrix(matrix, mirror, mCameraHolder.displayOrientation, width, height);
		
		matrix.invert(mMatrix);
		if(mCameraHolder.mParameters == null) {
			Log.e(TAG, "FocusManager initializes failed | mCameraHolder.mParameters = null");
		} else {
			getFocusMode();
			isInitialized = true;
		}
	}
	
	private static FocusManager instance;
	private FocusManager(Context mContext) {
		this.mContext = mContext;
		this.mDefaultFocusMode = mContext.getResources().getStringArray(R.array.pref_camera_focusmode_default_array);
		mMatrix = new Matrix();
		mCameraHolder = CameraHolder.getInstance(mContext);
		mDistanceManager = new DistanceManager(mContext);
	}
	public static FocusManager getInstance(Context mContext) {
		if(instance == null)
			instance = new FocusManager(mContext);
		return instance;
	}
	
	public void initCapabilities() {
		mIsFocusSupported = mCameraHolder.getFocusSupported();
		mFocusAreaSupported = mCameraHolder.getMaxNumFocusAreas() > 0 && mIsFocusSupported;
		mMeteringAreaSupported = mCameraHolder.getMaxNumMeteringAreas() > 0;
		mLockAeAwbNeeded = mCameraHolder.getLockAeAwbNeeded();
		
	}
	
	public void resetTouchFocus() {
		if(mListener != null)
			mListener.onResetFocusArea();
		
		mFocusArea = null;
		mMeteringArea = null;
	}
	
	public void updateFocusParameters() {
		if(!mFocusAreaSupported || !mMeteringAreaSupported || 
				mCameraHolder.mCamera == null)
			return;
		if(mFocusAreaSupported) {
			mCameraHolder.setFocusArea(mFocusArea);
		}
		if(mMeteringAreaSupported) {
			mCameraHolder.setMeteringArea(mMeteringArea);
		}
	}
	
	public void onStartPreview() {
		isFocusing = false;
		isPreviewPaused = false;
		mFocusState = FocusState.IDLE;
	}
	
	boolean useSensorFocus = true;
	String mFocusMode;
	
	public String getFocusMode() {
		
		List<String> supportedFocusModes = mCameraHolder.mParameters.getSupportedFocusModes();
		if(mFocusAreaSupported && mFocusArea != null && !Parameters.FOCUS_MODE_MACRO.equals(mFocusMode)) {
			mFocusMode = Parameters.FOCUS_MODE_AUTO;
		} else {
			mFocusMode = mCameraHolder.mParameters.getFocusMode();
			if(mFocusMode == null) {
				for(String mode : mDefaultFocusMode) {
					if(mCameraHolder.isSupported(mode, supportedFocusModes)) {
						mFocusMode = mode;
						break;
					}
				}
			}
		}
		return mFocusMode;
	}
	
	public boolean isFocusSupported() {
		getFocusMode();
		return Parameters.FOCUS_MODE_AUTO.equals(mFocusMode) || Parameters.FOCUS_MODE_MACRO.equals(mFocusMode);
	}
	
	private void updateFocusUI() {
		if(!isInitialized || mCallback == null)
			return;
		Log.i(TAG, "FocusManager | updateFocusUI focus success=" + (mFocusState == FocusState.SUCCESS));
		if(mFocusState == FocusState.IDLE) {
			if(mFocusArea == null) {
				mCallback.clear();
			} else {
				mCallback.showStart();
			}
		} else if(mFocusState == FocusState.FOCUSING || mFocusState == FocusState.FOCUSING_SNAP_ON_FINISH) {
			mCallback.showStart();
		} else {
			if(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE.equals(mFocusMode)) {
				mCallback.showSuccess(false);
			} else if(mFocusState == FocusState.SUCCESS) {
				mCallback.showSuccess(false);
			} else if(mFocusState == FocusState.FAIL) {
				mCallback.showFail(false);
				mLastAutoFocusFailTime = System.currentTimeMillis();
			}
		}
	}
	
	private void doAutoFocus() {
		Log.i(TAG, "FocusManager | doAutoFocus");
		mDistanceManager.onFocus();
		isFocusing = true;
		mListener.autoFocus();
		if(mFocusState != FocusState.FOCUSING_SNAP_ON_FINISH)
			mFocusState = FocusState.FOCUSING;
		mCallback.pauseFaceview();
		updateFocusUI();
		mHandler.removeMessages(MSG_RESET_TOUCH_FOCUS);
	}
	
	private void resetFocusState() {
		mDistanceManager.reset();
	}
	
	/*
	 * take picture
	 */
	private void capture() {
		if(mListener.capture()) {
			mFocusState = FocusState.IDLE;
			mHandler.removeMessages(MSG_RESET_TOUCH_FOCUS);
		}
	}
	
	private void cancelAutoFocus() {
		Log.i(TAG, "FocusManager | cancelAutoFocus");
		resetTouchFocus();
		mListener.cancelAutoFocus();
		mCallback.resumeFaceview();
		mFocusState = FocusState.IDLE;
		updateFocusUI();
		mHandler.removeMessages(MSG_RESET_TOUCH_FOCUS);
	}
	
	public void onAutoFocus(boolean success) {
		Log.i(TAG, "FocusManager | onAutoFocus success=" + success);
		isFocusing = false;
		mLastFocusSuccess = success;
		mLastFocusMillis = System.currentTimeMillis();
		
		if(isTouchFocus)
			isTouchFocus = false;
		
		if(mFocusState == FocusState.FOCUSING_SNAP_ON_FINISH) {
			if(success) {
				mFocusState = FocusState.SUCCESS;
			} else {
				resetFocusState();
				mFocusState = FocusState.FAIL;
			}
			updateFocusUI();
			capture();
		} else if(mFocusState == FocusState.FOCUSING) {
			if(success) {
				mFocusState = FocusState.SUCCESS;
			} else {
				mFocusState = FocusState.FAIL;
			}
			updateFocusUI();
			mHandler.sendEmptyMessageDelayed(MSG_RESET_TOUCH_FOCUS, RESET_TOUCH_FOCUS_DELAY_MILLIS);
		} 
		
	}
	
	private void autoFocus(boolean forceFocus) {
		Log.i(TAG, "FocusManager | autoFocus forceFocus=" + forceFocus);
		if(forceFocus) {
			if(mFocusState == FocusState.IDLE) {
				doAutoFocus();
			} else if(isTouchFocus) {
				isTouchFocus = false;
			}
		} else {
			if(mDistanceManager.needFocus()) {
				long currentTime = System.currentTimeMillis();
				long lastTime1 = currentTime - mLastAutoFocusTime;
				long lastTime2 = currentTime - mLastAutoFocusFailTime;
				
				if(lastTime1 < MIN_FOCUS_TIME) {
					onAutoFocus(false);
					return;
				} else if(lastTime2 < MIN_FOCUS_FAILED_TIME) {
					onAutoFocus(false);
					return;
				} else {
					mLastAutoFocusTime = currentTime;
					doAutoFocus();
				}
			} else {
				onAutoFocus(mLastAutoFocusSuccess);
			}
		}
	}
	
	private class DistanceManager implements SensorEventListener {

		SensorManager mSensorManager;
		Sensor mSensor;
		
		private int mI = 0;
		private float[] mLastFocusPosition;
		private int mDelta = 0;
		private int mSum = 0;
		private long mLastHoldMillis = -1;
		private boolean mIsHoldOn = false;
		private float[] mCurrOriValues = new float[3];
		
		private final float SMART_FOCUS_HOLD_MILLIS = 500;
		private final int SMART_FOCUS_HOLD_STABILIZE_DISTANCE = 30;
		private final int SMART_FOCUS_HOLD_STABILIZE_DISTANCE_SUM = 30;
		
		private DistanceManager(Context context) {
			mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		}
		
		public void regist() {
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
			if(null != mSensor) {
				Log.i(TAG, "DistanceManager | regist sensor");
				mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
			}
		}
		
		public void unregist() {
			if(null != mSensor) {
				Log.i(TAG, "DistanceManager | unregist sensor");
				mSensorManager.unregisterListener(this, mSensor);
			}
		}
		
		public void reset() {
			mLastHoldMillis =  -1;
			mLastFocusPosition = null;
		}
		
		public void onFocus() {
			mLastHoldMillis = -1;
			if(null != mCurrOriValues) {
				mLastFocusPosition = null;
				mLastFocusPosition = mCurrOriValues.clone();
			}
		}
		
		public boolean needFocus() {
			if(null == mCurrOriValues)
				return true;
			if(null == mLastFocusPosition)
				return true;
			else {
				mSum = 0;
				int length = mCurrOriValues.length;
				for(int i = 0; i < length; i++) {
					mDelta = (int) Math.abs(mLastFocusPosition[i] - mCurrOriValues[i]);
					mSum += mDelta;
					if(mDelta > SMART_FOCUS_HOLD_STABILIZE_DISTANCE) {
						mLastFocusPosition = mCurrOriValues.clone();
						return true;
					} else if(mSum > SMART_FOCUS_HOLD_STABILIZE_DISTANCE_SUM) {
						mLastFocusPosition = mCurrOriValues.clone();
						return true;
					}
				}
				return false;
			}
		}
		
		private boolean beyondRound(float[] newValues, float[] oldValues) {
			boolean isBeyond = false;
			
			mSum = 0;
			mDelta = 0;
			// 判断是否稳定
			
//			if(null != newValues && null != oldValues) {
//				Log.i(TAG, String.format("compare{old[%f, %f, %f], new[%f, %f, %f]}", 
//						oldValues[0], oldValues[1], oldValues[2], newValues[0], newValues[1], newValues[2]));
//			}
			
			for (int i = 0; i < newValues.length; i++) {
				mDelta = (int) Math.abs(newValues[i] - oldValues[i]); 
				if (mDelta > 180) 
					mDelta = 360 - mDelta;
				if (mDelta > SMART_FOCUS_HOLD_STABILIZE_DISTANCE) {                                                                                                                                                           
					isBeyond = true;
					break;
				}
				mSum += mDelta;
				if (mSum > SMART_FOCUS_HOLD_STABILIZE_DISTANCE_SUM) {
					isBeyond = true;
					break;
				}
			}
			
			return isBeyond;
		}
		
		private boolean checkStability(final float[] newValues, final float[] oldValues) {
			boolean isHold = false;
			
			if(newValues == null) {
				isHold = false;
				Log.i(TAG, "test test newValues = null");
			}
			else {
				isHold = !beyondRound(newValues, oldValues);
				Log.i(TAG, "test test isHold=" + isHold);
			}
			
			if(isHold) {
				if(mLastHoldMillis == -1) {
					mLastHoldMillis = System.currentTimeMillis();
					Log.i(TAG, "isHold first in at " + mLastHoldMillis);
					isHold = false;
				} else if((System.currentTimeMillis() - mLastHoldMillis) > SMART_FOCUS_HOLD_MILLIS) {
					Log.i(TAG, "isHold second in at " + System.currentTimeMillis());
					mLastFocusMillis = -1;
					isHold = true;
				}
			} else {
				mLastHoldMillis = -1;
			}
			
			return isHold;
		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			if(!useSensorFocus || isPreviewPaused || isFocusing || !isFocusSupported())
				return;
			final float[] nVal = event.values;
			switch(event.sensor.getType()) {
			case Sensor.TYPE_ORIENTATION:
				Log.i(TAG, "test test onSensorChanged");
				mIsHoldOn = checkStability(nVal, mCurrOriValues);
				
				mCurrOriValues = null;
				mCurrOriValues = nVal.clone();
				
				if(mIsHoldOn) {
					Log.i(TAG, "DistanceManager | onSensorChanged | mIsHoldOn");
					if(mLastFocusPosition == null) {
						autoFocus(false);
					} else if(beyondRound(nVal, mLastFocusPosition)) {
						autoFocus(false);
					}
				}
				
				break;
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
		
	}
	
	public void onResume() {
		Log.i(TAG, "FocusManager | onResume");
		mDistanceManager.regist();
	}
	
	public void onPause() {
		Log.i(TAG, "FocusManager | onPause");
		mDistanceManager.unregist();
	}
	
	public boolean onTouchEvent(MotionEvent e) {
		if(mFocusState == FocusState.FOCUSING_SNAP_ON_FINISH || mFocusState == FocusState.FOCUSING) {
			return false;
		}
		if(mFocusAreaSupported || mMeteringAreaSupported) {
			if(null != mFocusArea && (mFocusState == FocusState.FOCUSING || mFocusState == FocusState.SUCCESS || mFocusState == FocusState.FAIL)) {
				cancelAutoFocus();
			}
			int x = Math.round(e.getX());
			int y = Math.round(e.getY());
			Point fp = mCallback.getFocusSize();
			if(null == mFocusArea) {
				mFocusArea = new ArrayList<Area>();
				mMeteringArea = new ArrayList<Area>();
				mFocusArea.add(new Area(new Rect(), 1));
				mMeteringArea.add(new Area(new Rect(), 1));
			}
			calcTabArea(fp.x, fp.y, 1f, x, y, mPreviewWidth, mPreviewHeight, mFocusArea.get(0).rect);
			calcTabArea(fp.x, fp.y, 1.5f, x, y, mPreviewWidth, mPreviewHeight, mMeteringArea.get(0).rect);
			
			mCallback.setFocusArea(x, y, mPreviewWidth, mPreviewHeight);
			mListener.stopFaceDetection();
			mListener.setFocusParams();
		}
		String focusMode = getFocusMode();
		if(isFocusSupported()) {
			if(Parameters.FOCUS_MODE_MACRO.equals(focusMode)) {
				mSmartFocusCDMillis = RESET_USER_FOCUS_MODE_MILLIS_MACRO;
				mResetFocusStateMillis = RESET_FOCUS_STATE_MILLIS_MACRO;
			} else {
				mSmartFocusCDMillis = RESET_USER_FOCUS_MODE_MILLIS;
				mResetFocusStateMillis = RESET_USER_FOCUS_MODE_MILLIS;
			}
			isTouchFocus = true;
			autoFocus(isTouchFocus);
			return true;
		}
		return false;
	}
	
	public void calcTabArea(int focusW, int focusH, float multiNum, int posX, int posY, int previewW, int previewH, Rect rect) {
		int areaW = (int) (focusW * multiNum);
		int areaH = (int) (focusH * multiNum);
		
		int left = Util.clamp(posX - areaW / 2, 0, previewW - areaW);
		int top = Util.clamp(posY - areaH / 2, 0, previewH - areaH);
		
		RectF rf = new RectF(left, top, left + areaW, top + areaH);
		if(null != mMatrix) {
			mMatrix.mapRect(rf);
		}
		Util.parseRectF(rf, rect);
	}
	
	public void doFocusAndCaptrue() {
		if(!isInitialized)
			return;
		if(isFocusSupported()) {
			if (mFocusState == FocusState.FOCUSING) {
				mFocusState = FocusState.FOCUSING_SNAP_ON_FINISH;
				GLogger.d(TAG, " do snap, snap wait for focus finish ");
			} else if (mFocusMode.equals(Parameters.FOCUS_MODE_MACRO)) {
				GLogger.d(TAG, " do snap, macro focus didn't need focus!");
				capture();
			} else {
				GLogger.d(TAG, " do snap, ready auto focus !");
				mFocusState = FocusState.FOCUSING_SNAP_ON_FINISH;
				
				if (!mLastFocusSuccess && (System.currentTimeMillis() - mLastFocusMillis) > mResetFocusStateMillis) {
					this.resetFocusState();
				} 
				autoFocus(false);
			}
		} else {
			capture();
		}
	}

}
