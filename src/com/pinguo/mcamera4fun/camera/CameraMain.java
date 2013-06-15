package com.pinguo.mcamera4fun.camera;

import com.pinguo.mcamera4fun.R;
import com.pinguo.mcamera4fun.camera.CameraOperator.Callback;
import com.pinguo.mcamera4fun.camera.mode.ModeAbstract;
import com.pinguo.mcamera4fun.camera.mode.ModeManager;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;
import com.pinguo.mcamera4fun.ui.CameraUiListener;
import com.pinguo.mcamera4fun.ui.CameraUiManager;
import com.pinguo.mcamera4fun.utils.CameraUtil;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera.FaceDetectionListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class CameraMain extends Activity implements SurfaceHolder.Callback,
		CameraOperator.Callback {

	String TAG = "CameraMain";
	RelativeLayout mFrameLayout;
	SurfaceView mSurfaceView;
	SurfaceHolder mSurfaceHolder;
	Context mContext;
	Activity mActivity;

	CameraOperator mCameraOperator;
	CameraUiManager mCameraUiManager;
	
	CommonSharedPreferences mPreferences;
	ModeManager mModeManager;
	ModeAbstract mModeAbstract;

//	Thread cameraOpenTask = new Thread() {
//		public void run() {
//			mCameraOperator.openDevice();
//		}
//	};

//	Thread cameraPreviewTask = new Thread() {
//		public void run() {
//			Log.i(TAG, "CameraMain | cameraPreviewTask");
//			mCameraOperator.startPreview(mSurfaceHolder);
//		}
//	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, "CameraMain | onCreate");
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.camera_layout);
		
		mContext = this;
		mActivity = this;
		
		mCameraOperator = CameraOperator.getInstance(mActivity);
		mCameraOperator.setCallback(this);
		mCameraUiManager = CameraUiManager.getInstance(mActivity);
		mModeManager = ModeManager.getInstance(mActivity);
		mModeAbstract = initCameraMode();
		mCameraOperator.setCameraEventOper(mModeAbstract);
		mCameraUiManager.setCameraUiListener(mModeAbstract);
		
//		cameraOpenTask.start();
//
//		try {
//			cameraOpenTask.join();
//			cameraOpenTask = null;
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		mCameraOperator.openDevice();
		setupView();

//		cameraPreviewTask.start();
//
//		synchronized (cameraPreviewTask) {
//			try {
//				cameraPreviewTask.wait();
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//		}
//
//		try {
//			cameraPreviewTask.join();
//			cameraPreviewTask = null;
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

	}
	
	private ModeAbstract initCameraMode() {

		if (null != mPreferences && mPreferences.getIsOn(CameraSettings.KEY_AUTOMATIC_ENTER_EFFECTS_MODEL, false)) {
			mModeAbstract = mModeManager.getMode(CameraUtil.CAMERA_MODE_NOMARL, false);
		} else {
			mModeAbstract = mModeManager.getCurModeById();
		}

		return mModeAbstract;
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "CameraMain | onResume");
		if(mSurfaceHolder != null) {
			int width = mSurfaceView.getWidth();
			int height = mSurfaceView.getHeight();
			mCameraOperator.onResume(width, height);
		}
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		Log.i(TAG, "CameraMain | onPause");
		mCameraOperator.onPause();
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		Log.i(TAG, "CameraMain | onDestroy");
		mCameraOperator.onDestroy();
		mSurfaceHolder = null;
		mSurfaceView = null;
		super.onDestroy();
	}
	
	private void setupView() {
		mFrameLayout = (RelativeLayout) findViewById(R.id.preview_frame);
		mFrameLayout.setOnTouchListener(mCameraOperator);
		mSurfaceView = (SurfaceView) findViewById(R.id.camera_preview);
		mSurfaceHolder= mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "CameraMain | surfaceCreated " + (holder.getSurface() == null));
		mSurfaceHolder = holder;
		mCameraOperator.onSurfaceCreated(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, 	int height) {
		Log.i(TAG, "CameraMain | surfaceChanged " + (holder.getSurface() == null));
		if (holder.getSurface() == null)
			return;
		mSurfaceHolder = holder;
		mCameraOperator.onSurfaceChanged(holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i(TAG, "CameraMain | surfaceDestroyed " + (holder == null));
		mCameraOperator.stopPreview();
		mSurfaceHolder = null;
	}

	@Override
	public void onResetFocus() {
		mCameraUiManager.resetFocus();
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public FaceDetectionListener setupFaceView(boolean mirror, int displayOrientation) {
		return mCameraUiManager.setupFaceView(mirror, displayOrientation);
	}

	@Override
	public void clearFaceView() {
		mCameraUiManager.clearFaceView();
	}

}
