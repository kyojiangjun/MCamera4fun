package com.pinguo.mcamera4fun.camera;

import com.pinguo.mcamera.common.log.GLogger;
import com.pinguo.mcamera4fun.camera.CameraHolder.CameraState;
import com.pinguo.mcamera4fun.camera.CameraHolder.CameraType;
import com.pinguo.mcamera4fun.camera.FocusManager.CameraOpCallback;
import com.pinguo.mcamera4fun.camera.FocusManager.FocusState;
import com.pinguo.mcamera4fun.camera.mode.ModeManager;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;
import com.pinguo.mcamera4fun.ui.CameraUiListener;
import com.pinguo.mcamera4fun.ui.CameraUiManager;
import com.pinguo.mcamera4fun.ui.view.ShutterButtonPanel.ShutterButtonPanelListener;
import com.pinguo.mcamera4fun.utils.CameraUtil;
import com.pinguo.mcamera4fun.utils.DeviceUtils;
import com.pinguo.mcamera4fun.utils.Util;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;

public class CameraOperator implements CameraOpCallback, AutoFocusCallback, OnTouchListener {
	
	String TAG = "CameraOperator";
	Context mContext;
	Activity mActivity;
	CommonSharedPreferences mPreferences;
	
	FocusManager mFocusManager;
	CameraHolder mCameraHolder;
	
	boolean faceDetectionStarted = false;
	boolean initializeFinished = false;
	
	public static final int UPDATE_PARAM_INITIALIZE = 1;
	public static final int UPDATE_PARAM_ZOOM = 2;
	public static final int UPDATE_PARAM_PREFERENCE = 4;
	public static final int UPDATE_PARAM_ALL = -1;
	
	public static final long PICTURE_CALLBACK_WAIT_TIME = 5000;
	
	private CameraEventInterface mCameraEventOper;
	
	public void setCameraEventOper(CameraEventInterface mCameraEventOper) {
		this.mCameraEventOper = mCameraEventOper;
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public interface Callback {
		public void onResetFocus();
		public FaceDetectionListener setupFaceView(boolean mirror, int displayOrientation);
		public void clearFaceView();
	}
	private Callback mCallback;
	
	private static CameraOperator instance;
	
	public void setCallback(Callback callback) {
		this.mCallback = callback;
	}
	
	private CameraOperator(Activity activity) {
		this.mActivity = activity;
		this.mContext = activity.getApplicationContext();
		mPreferences = CommonSharedPreferences.getInstance(mContext);
		mFocusManager = FocusManager.getInstance(mContext);
		mFocusManager.setListener(this);
		
		mCameraHolder = CameraHolder.getInstance(mContext);
		mCameraEventOper.setCameraType(mCameraHolder.mCameraType);
	}

	public static CameraOperator getInstance(Activity activity) {
		if(instance == null)
			instance = new CameraOperator(activity);
		return instance;
	}
	
	public boolean isCameraOpened() {
		return !(null == mCameraHolder.mCamera);
	}
	
	public void setCameraState(CameraState state) {
		mCameraHolder.mState = state;
	}
	
	public void openDevice() {
		mCameraHolder.openDevice();
	}
	
	public void startPreview(SurfaceHolder holder) {
		Log.i(TAG, "CameraOperator | startPreview");
		mFocusManager.initCapabilities();
		mFocusManager.resetTouchFocus();
		
		setCameraPreviewHolder(holder);
		setCameraDisplayOrientation();
		updateCameraParameters();
		
		mCameraHolder.startPreview();
		mFocusManager.onStartPreview();
		
		setCameraState(CameraState.IDLE);
		if(initializeFinished)
			startFaceDetection();
	}
	
	private void setCameraDisplayOrientation() {
		mCameraHolder.displayRotation = Util.getDisplayRotation(mActivity);
		mCameraHolder.displayOrientation = Util.getDisplayOrientation(mCameraHolder.displayRotation, mCameraHolder.mCameraId);
		mCameraHolder.mCamera.setDisplayOrientation(mCameraHolder.displayOrientation);
	}
	
	public void stopPreview() {
		if(isCameraOpened() && mCameraHolder.mState != CameraState.PREVIEW_STOPPED) {
			mCameraHolder.stopPreview();
			setCameraState(CameraState.PREVIEW_STOPPED);
		}
		faceDetectionStarted = false;
	}
	
	public void onSurfaceCreated(SurfaceHolder holder) {
		if (isCameraOpened()) {
			Log.i(TAG, "CameraOperator | onSurfaceCreated ");
			mCameraHolder.setCameraPreviewHolder(holder);
		}
	}
	
	public void onSurfaceChanged(SurfaceHolder holder) {
		Log.i(TAG, "CameraOperator | onSurfaceChanged isCameraOpened = " + (isCameraOpened()));
		if(!isCameraOpened())
			return;
		else if(mCameraHolder.mState != CameraState.PREVIEW_STOPPED) {
			stopPreview();
		}
		setCameraPreviewHolder(holder);
		startPreview(holder);
	}
	
	public void onResume(int width, int height) {
		Log.i(TAG, "CameraOperator | onResume");
		if(!initializeFinished)
			initialize(width, height);
		if(mFocusManager != null) {
			mFocusManager.onResume();
		}
	}
	
	public void onPause() {
		Log.i(TAG, "CameraOperator | onPause");
		if(mFocusManager != null) {
			mFocusManager.onPause();
		}
	}
	
	public void onDestroy() {
		if(isCameraOpened()) {
			Log.i(TAG, "CameraOperator | onDestroy");
			stopPreview();
			mCameraHolder.mCamera.release();
		}
	}
	
	public void setCameraPreviewHolder(SurfaceHolder holder) {
			mCameraHolder.setCameraPreviewHolder(holder);
	}
	
	public void updateCameraParameters() {
		mFocusManager.updateFocusParameters();
	}
	
	@Override
	public void onResetFocusArea() {
		mCallback.onResetFocus();
	}
	
	public OperHandler mHandler = new OperHandler();
	private class OperHandler extends Handler {
		
		public final int INIT_FIRST_IN = 0;
		public final int PICTURE_CALLBACK_FAIL = 1;
		
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case INIT_FIRST_IN:
				Bundle data = msg.getData();
				int width = data.getInt("width");
				int height = data.getInt("height");
				initialize(width, height);
				break;
			case PICTURE_CALLBACK_FAIL:
				mCameraEventOper.onTakePictureFailed();
				break;
			}
		}
		
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void startFaceDetection() {
		if(faceDetectionStarted || mCameraHolder.mState != CameraState.IDLE)
			return;
		Log.i(TAG, "CameraOperator | startFaceDetection");
		if(mCameraHolder.getMaxNumOfFaces() > 0) {
			faceDetectionStarted = true;
			boolean mirror = mCameraHolder.mCameraType == CameraType.FRONT;
			mCameraHolder.mCamera.setFaceDetectionListener(mCallback.setupFaceView(mirror, mCameraHolder.displayOrientation));
			mCameraHolder.mCamera.startFaceDetection();
		}
	}
	
	private void initialize(int width, int height) {
		if(initializeFinished)
			return;
		mFocusManager.initialize(width, height);
		startFaceDetection();
		initializeFinished = true;
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void stopFaceDetection() {
		if(!faceDetectionStarted)
			return;
		if(mCameraHolder.getMaxNumDetectedFaces() > 0) {
			faceDetectionStarted = false;
			mCameraHolder.mCamera.stopFaceDetection();
			mCameraHolder.mCamera.setFaceDetectionListener(null);
			mCallback.clearFaceView();
		}
	}

	@Override
	public void autoFocus() {
		if(!isCameraOpened())
			return;
		if(mCameraHolder.mState == CameraState.FOCUSING) {
			mCameraHolder.mCamera.cancelAutoFocus();
		}
		if(mCameraHolder.mState == CameraState.IDLE) {
			Log.i(TAG, "CameraOperator | autoFocus ");
			mCameraHolder.mCamera.autoFocus(this);
			setCameraState(CameraState.FOCUSING);
		}
	}

	@Override
	public void onAutoFocus(boolean success, Camera camera) {
		Log.i(TAG, "AutoFocusCallback | onAutoFocus success=" + success);
		mFocusManager.onAutoFocus(success);
		setCameraState(CameraState.IDLE);
	}

	@Override
	public boolean capture() {
		if(!isCameraOpened() || mCameraHolder.mState == CameraState.SNAPSHOT_IN_PROGRESS)
			return false;
		setCameraState(CameraState.SNAPSHOT_IN_PROGRESS);
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if(!(mCameraHolder.mState == CameraState.PREVIEW_STOPPED)) {
					doCameraTakePicture();
				}
			}
		}, mFocusManager.CAPTURE_DELAY_MILLIS_AFTER_FOCUS);
		return true;
	}
	
	private void doCameraTakePicture() {
		mHandler.sendEmptyMessageDelayed(mHandler.PICTURE_CALLBACK_FAIL, PICTURE_CALLBACK_WAIT_TIME);
		mCameraHolder.mCamera.takePicture(mShutterCallback, new RawPictureCallback(), new JpgPictureCallback());
	}

	@Override
	public void cancelAutoFocus() {
		if(isCameraOpened() && !(mFocusManager.mFocusState == FocusState.SUCCESS))
			mCameraHolder.mCamera.cancelAutoFocus();
		setCameraState(CameraState.IDLE);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if(event.getPointerCount() > 1)
			return false;
		
		if(!isCameraOpened() || !initializeFinished || mCameraHolder.mState == CameraState.SNAPSHOT_IN_PROGRESS) {
			return false;
		}
		
		if(event.getAction() == MotionEvent.ACTION_MOVE) {
			return true;
		}
		
		if(!mFocusManager.isFocusSupported()) {
			return false;
		}
		
		return mFocusManager.onTouchEvent(event);
	}

	@Override
	public void setFocusParams() {
		setCameraParameters(UPDATE_PARAM_PREFERENCE);
	}
	
	public void setCameraParameters(int paramType) {
		if(!isCameraOpened())
			return;
		if((paramType & UPDATE_PARAM_ALL) == 0) {
			
		}
		if((paramType & UPDATE_PARAM_INITIALIZE) == 0) {
			
		}
		if((paramType & UPDATE_PARAM_PREFERENCE) == 0) {
			mFocusManager.updateFocusParameters();
		}
		if((paramType & UPDATE_PARAM_ZOOM) == 0) {
			
		}
	}
	
	public void takePicture() {
		if(null != mFocusManager)
			mFocusManager.doFocusAndCaptrue();
	}
	
	public ShutterCallback mShutterCallback = new ShutterCallback() {
		@Override
		public void onShutter() {
			GLogger.v(TAG, "ShutterCallback.onShutter");
		}
	};
	
	public final class RawPictureCallback implements PictureCallback {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			GLogger.v(TAG, "RawPictureCallback.onPictureTaken");
		}
		
	}
	
	public final class PostRawPictureCallback implements PictureCallback {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			GLogger.v(TAG, "PostRawPictureCallback.onPictureTaken");
		}
		
	}
	
	public final class JpgPictureCallback implements PictureCallback {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			GLogger.v(TAG, "JpgPictureCallback.onPictureTaken");
			mHandler.removeMessages(mHandler.PICTURE_CALLBACK_FAIL);
			if(data.length < 1024) {
				mHandler.sendEmptyMessage(mHandler.PICTURE_CALLBACK_FAIL);
			} else {
				mFocusManager.resetTouchFocus();
				mCameraEventOper.onTakePictureSuccess(data, camera);
				setCameraState(CameraState.IDLE);
			}
		}
		
	}

}
