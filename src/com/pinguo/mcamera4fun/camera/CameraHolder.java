package com.pinguo.mcamera4fun.camera;

import java.io.IOException;
import java.util.List;
import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;
import com.pinguo.mcamera4fun.utils.SdkUtil;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;

public class CameraHolder {
	
	String TAG = "CameraHolder";
	public static enum CameraType {
		FRONT, BACK, NONE
	}
	
	public enum CameraState {
		PREVIEW_STOPPED, FOCUSING, 
		/**
		 * // preview is active
		 */
		IDLE,
		/**
		 * Focus is in progress.
		 */
		SNAPSHOT_IN_PROGRESS,
		SNAPSHOT_FINISHED
	}
	CameraState mState;
	
	Camera mCamera;
	CameraType mCameraType;
	Parameters mParameters;
	int mCameraId;
	int numOfCameras;
	
	int displayRotation;
	int displayOrientation;
	
	private static CameraHolder instance;
	
	public Camera getCamera() {
		return mCamera;
	}
	
	private CameraHolder(Context context) {
		
		mCameraId = CommonSharedPreferences.getInstance(context).getInt(CommonSharedPreferences.PREF_CAMERA_ID);
		numOfCameras = Camera.getNumberOfCameras();
		mCameraType = CameraType.NONE;
		
		for(int i = 0; i < numOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if(info.facing == CameraInfo.CAMERA_FACING_BACK) {
				if(mCameraId == -1) {
					mCameraId = i;
					mCameraType = CameraType.BACK;
					break;
				} else if(mCameraId == i) {
					mCameraType = CameraType.BACK;
					break;
				}
			}
			if(info.facing == CameraInfo.CAMERA_FACING_FRONT ) {
				if(mCameraId == -1) {
					mCameraId = i;
					mCameraType = CameraType.FRONT;
					break;
				} else if(mCameraId == i) {
					mCameraType = CameraType.FRONT;
					break;
				}
			}
		}
	}
	
	public static CameraHolder getInstance(Context context) {
		if(instance == null)
			instance = new CameraHolder(context);
		return instance;
	}
	
	public void openDevice() {
		Log.i(TAG, "CameraHolder | openDevice");
		if(null != mCamera) {
			mCamera.release();
			mCamera = null;
			mParameters = null;
			mCameraType = CameraType.NONE;
		} 
		if(mCameraId != -1) {
			if((mCamera = Camera.open(mCameraId)) != null)
				mParameters = mCamera.getParameters();
		}
	}
	
	public void startPreview() {
		mCamera.startPreview();
	}
	
	public void stopPreview() {
		mCamera.stopPreview();
	}
	
	public void setCameraPreviewHolder(SurfaceHolder holder) {
		try {
			mCamera.setPreviewDisplay(holder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void setFocusArea(List<Area> area) {
		if(mParameters != null) {
			mParameters.setFocusAreas(area);
		}
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public void setMeteringArea(List<Area> area) {
		if(mParameters != null) {
			mParameters.setMeteringAreas(area);
		}
	}
	
	public boolean getFocusSupported() {
		return isSupported(mParameters.getSupportedFocusModes());
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public int getMaxNumFocusAreas() {
		if (Build.VERSION.SDK_INT < 14) {
			return 0;
		} else {
			return mParameters.getMaxNumFocusAreas();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public int getMaxNumMeteringAreas() {
		if (Build.VERSION.SDK_INT < 14) {
			return 0;
		} else {
			return mParameters.getMaxNumMeteringAreas();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public int getMaxNumOfFaces() {
		if(Build.VERSION.SDK_INT < 14) {
			return 0;
		} else {
			return mParameters.getMaxNumDetectedFaces();
		}
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public int getMaxNumDetectedFaces() {
		if (Build.VERSION.SDK_INT < 14) {
			return -1;
		} else {
		    return mParameters.getMaxNumDetectedFaces();
		}
	}
	
	@SuppressLint("NewApi")
	public boolean isAutoExposureLockSupported() {
		if(SdkUtil.is4x0()) {
			return mParameters.isAutoExposureLockSupported();
		} else {
			return false;
		}
	}
	
	@SuppressLint("NewApi")
	public boolean isAutoWhiteBalanceLockSupported() {
		if(SdkUtil.is4x0()) {
			return mParameters.isAutoWhiteBalanceLockSupported();
		} else {
			return false;
		}
	}
	
	@SuppressLint("NewApi")
	public boolean getLockAeAwbNeeded() {
		return isAutoExposureLockSupported() || isAutoWhiteBalanceLockSupported();
	}
	
	public boolean isSupported(List<?> values) {
		return (values != null && values.size() > 1);
	}
	
	public <T> boolean isSupported(T value, List<T> values) {
		if(values.contains(value))
			return true;
		return false;
	}

}
