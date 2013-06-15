package com.pinguo.mcamera4fun.ui;

import com.pinguo.mcamera4fun.R;
import com.pinguo.mcamera4fun.camera.CameraEventInterface;
import com.pinguo.mcamera4fun.camera.CameraHolder;
import com.pinguo.mcamera4fun.camera.FocusManager;
import com.pinguo.mcamera4fun.camera.FocusManager.UiCallback;
import com.pinguo.mcamera4fun.camera.mode.ModeManager;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;
import com.pinguo.mcamera4fun.ui.view.ShutterButtonPanel;
import com.pinguo.mcamera4fun.utils.CameraUtil;
import com.pinguo.mcamera4fun.utils.Util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Face;
import android.hardware.Camera.FaceDetectionListener;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class CameraUiManager implements UiCallback{
	
	String TAG = "CameraUiManager";
	
	Activity mActivity;
	Context mContext;
	CameraHolder mCameraHolder;
	
	FaceView mFaceView;
	FaceDetectionListener mFaceDetectionListener;
	
	CommonSharedPreferences mPreferences;
	CameraUiListener mCameraUiListener;
	ShutterButtonPanel mShutterButtonPanel;
	
	FocusIndicatorRotateLayout mFocusIndicatorRotateLayout;
	
	public void setCameraUiListener(CameraUiListener mCameraMode) {
		this.mCameraUiListener = mCameraMode;
	}
	
	private static CameraUiManager instance;
	private CameraUiManager(Activity activity) {
		this.mActivity = activity;
		this.mContext = activity.getApplicationContext();
		mCameraHolder = CameraHolder.getInstance(mContext);
		mFocusIndicatorRotateLayout = (FocusIndicatorRotateLayout) activity.findViewById(R.id.focus_indicator);
		FocusManager.getInstance(mContext).setCallback(this);
		
		mShutterButtonPanel = (ShutterButtonPanel) mActivity.findViewById(R.id.seekBarLayout);
		mPreferences = CommonSharedPreferences.getInstance(mContext);
		mShutterButtonPanel.setOnShutterButtonListener(mCameraUiListener);
	}
	
	public static CameraUiManager getInstance(Activity activity) {
		if(instance == null)
			instance = new CameraUiManager(activity);
		return instance;
	}
	
	public void resetFocus() {
		
	}
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	public FaceDetectionListener setupFaceView(boolean mirror, int displayOrientation) {
		if(mFaceView == null)
			mFaceView = new FaceView(mContext);
		if(mFaceDetectionListener == null)
			mFaceDetectionListener = new FaceDetectionListener() {
				@Override
				public void onFaceDetection(Face[] faces, Camera camera) {
					mFaceView.setFaces(faces);
				}
			};
			mFaceView.clear();
			mFaceView.setVisibility(View.VISIBLE);
			mFaceView.setDisplayOrientation(displayOrientation);
			mFaceView.setMirror(mirror);
			mFaceView.resume();
		return mFaceDetectionListener;
	}
	
	public void clearFaceView() {
		if(mFaceView != null) {
			mFaceView.clear();
		}
	}
	@Override
	public void showStart() {
		Log.i(TAG, "CameraUiManager | showStart ");
		if(mFocusIndicatorRotateLayout != null)
			mFocusIndicatorRotateLayout.showStart();
	}
	@Override
	public void showSuccess(boolean flag) {
		Log.i(TAG, "CameraUiManager | showSuccess");
		if(mFocusIndicatorRotateLayout != null)
			mFocusIndicatorRotateLayout.showSuccess(flag);
	}
	@Override
	public void showFail(boolean flag) {
		Log.i(TAG, "CameraUiManager | showFail");
		if(mFocusIndicatorRotateLayout != null)
			mFocusIndicatorRotateLayout.showFail(flag);
	}
	@Override
	public void clear() {
		Log.i(TAG, "CameraUiManager | clear");
		if(mFocusIndicatorRotateLayout != null)
			mFocusIndicatorRotateLayout.clear();
	}
	@Override
	public void pauseFaceview() {
		if(mFaceView != null)
			mFaceView.pause();
	}
	@Override
	public void resumeFaceview() {
		if(mFaceView != null)
			mFaceView.resume();
	}
	@Override
	public Point getFocusSize() {
		Point p = null;
		if(null != mFocusIndicatorRotateLayout) {
			p.x = mFocusIndicatorRotateLayout.getWidth();
			p.y = mFocusIndicatorRotateLayout.getHeight();
		}
		return p;
	}
	@Override
	public void setFocusArea(int posX, int posY, int previewW, int previewH) {
		int focusW = mFocusIndicatorRotateLayout.getWidth();
		int focusH = mFocusIndicatorRotateLayout.getHeight();
		
		int left = Util.clamp(posX - focusW / 2, 0, previewW - focusW);
		int top = Util.clamp(posY - focusH / 2, 0, previewH - focusH);
		
		RelativeLayout.LayoutParams lps= (RelativeLayout.LayoutParams) mFocusIndicatorRotateLayout.getLayoutParams();
		lps.setMargins(left, top, 0, 0);
		int[] rules = lps.getRules();
		rules[RelativeLayout.CENTER_IN_PARENT] = 0;
		mFocusIndicatorRotateLayout.requestLayout();
	}

}
