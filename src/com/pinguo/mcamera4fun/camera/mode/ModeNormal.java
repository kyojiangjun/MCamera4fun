package com.pinguo.mcamera4fun.camera.mode;

import com.pinguo.mcamera.common.log.GLogger;
import com.pinguo.mcamera4fun.camera.sandbox.PhotoProject;
import com.pinguo.mcamera4fun.camera.sandbox.PictureProjectManager;
import com.pinguo.mcamera4fun.camera.sandbox.PictureProjectManager.IPictureProgressListener;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.utils.CameraUtil;
import com.pinguo.mcamera4fun.utils.Exif;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Size;

public class ModeNormal extends ModeDecorator implements IPictureProgressListener {

	private static final String TAG = "ModeNormal";
	
	public ModeNormal(Activity mActivity, ModeAbstract mDecoratedMode) {
		super(mActivity, mDecoratedMode);
	}

	@Override
	public void onSceneEventDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEffectEventDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		GLogger.v(TAG, "Normal is load.");
		mPreferEffect = mPreferences.getPreferEffect();
		setEffect(mPreferEffect);
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTakePictureSuccess(byte[] data, Camera device) {
		if(null != mDecoratedMode)
			mDecoratedMode.onTakePictureSuccess(data, device);
		Size size = device.getParameters().getPictureSize();
		int orientation = Exif.getOrientation(data);
		int width = 0;
		int height = 0;
		if(orientation % 180 == 0) {
			width = size.width;
			height = size.height;
		} else {
			width = size.height;
			height = size.width;
		}
		
		mPhotoProject.setmRotateDegree(orientation);
		mPhotoProject.setWidth(width);
		mPhotoProject.setHeight(height);
		mPhotoProject.setmData(data);
		
		String saveMode = mPreferences.getString(CameraSettings.KEY_PICTURE_AUTO_SAVE_MODE, CameraUtil.SAVE_MODE_CONFIRM);
		if(CameraUtil.SAVE_MODE_AUTO.equals(saveMode))
			mPhotoProject.setmNeedMakeThumb(false);
		else
			mPhotoProject.setmNeedMakeThumb(true);
		
		if(null != mCurEffect) {
			mCurEffect = refreshEffectParam(mCurEffect);
		}
		mPhotoProject.setCurEffectInfo(mCurEffect);
		
		try {
			PhotoProject cloneProj = (PhotoProject) mPhotoProject.clone();
			mPhotoProject.setmData(null);
			PictureProjectManager.getInstance().setProgressListener(this);
			PictureProjectManager.getInstance().addProject(cloneProj);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		PictureProjectManager.getInstance().makePicture();
	}

	@Override
	public void onMakeFinished(PhotoProject project) {
		
	}

}
