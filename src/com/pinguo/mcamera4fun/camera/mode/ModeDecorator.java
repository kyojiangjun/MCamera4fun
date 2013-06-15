package com.pinguo.mcamera4fun.camera.mode;

import com.pinguo.mcamera.common.log.GLogger;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.utils.CameraUtil;
import com.pinguo.mcamera4fun.utils.DeviceUtils;
import android.app.Activity;

public abstract class ModeDecorator extends ModeAbstract {

	private static final String TAG = "ModeDecorator";
	protected ModeAbstract mDecoratedMode = null;
	
	@Override
	public ModeAbstract getDecorator() {
		return mDecoratedMode;
	}
	
	public ModeDecorator(Activity mActivity, ModeAbstract mDecoratedMode) {
		super(mActivity);
		this.mDecoratedMode = mDecoratedMode;
	}

	@Override
	public void onShutterButtonClickListener() {
		vibrate();
		
		if(!DeviceUtils.isMemoryEnough(mContext)) {
			GLogger.w(TAG, "Device memory is full!");
			return;
		}
		
		String path = mPreferences.getString(CameraSettings.KEY_PIC_SAVE_PATH, CameraUtil.DEFALUT_DIRECTORY);
		if(CameraUtil.CAMERA_SD_CARD_LIMIT >= DeviceUtils.getAvailableStorage(path) ) {
			GLogger.w(TAG, "Sd card is full!");
			return;
		}
		
		takePicture();
	}
	
}
