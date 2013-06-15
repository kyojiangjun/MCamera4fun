package com.pinguo.mcamera4fun.camera.mode;

import com.pinguo.mcamera.common.log.GLogger;

import android.app.Activity;
import android.hardware.Camera;

public class ModeNoEffect extends ModeDecorator {

	private static final String TAG = "ModeNoEffect";
	
	public ModeNoEffect(Activity mActivity, ModeAbstract mDecoratedMode) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTakePictureSuccess(byte[] data, Camera device) {
		GLogger.v(TAG, "ModeNoEffect | onTakePictureSuccess ");
	}

}
