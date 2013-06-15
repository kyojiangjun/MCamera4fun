package com.pinguo.mcamera4fun.utils;

import com.pinguo.mcamera4fun.camera.GlobalApplication;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

public class CameraUtil {

	/**
     * @Description 零快门拍照模式.
     */
    public static final int CAMERA_MODE_NOMARL = 1;
    /**
     * @Description 特效拍照模式.
     */
    public static final int CAMERA_MODE_EFFECT = 2;
    /**
     * @Description 趣味拍照模式.
     */
    public static final int CAMERA_MODE_FUN = 3;
//    /**
//     * @Description 定时拍照模式.
//     */
//    public static final int CAMERA_MODE_TIMER = 3;
    /**
     * @Description 防抖拍照模式.
     */
//    public static final int CAMERA_MODE_ANTISHAKE = 4;
    /**
     * @Description 自拍模式.
     */
    public static final int CAMERA_MODE_SELF_TIMER = 4;
    /**
     * @Description 移色模式.
     */
    public static final int CAMERA_MODE_COLOR_SHIFT = 5;
    /**
     * @Description 移軸模式.
     */
    public static final int CAMERA_MODE_TILF_SHIFT = 6;
    /**
     * @Description 实时预览模式.
     */
    public static final int CAMERA_MODE_RENDER = 8;
    /**
     * @Description 照片矫正模式.
     */
    public static final int CAMERA_MODE_PIC_ADJUST = 9;
    /**
     * @Description 预览矫正模式.
     */
    public static final int CAMERA_MODE_PREVIEW_ADJUST = 10;
    /**
     * @Description 实时预览检测器装饰者
     */
    public static final int CAMERA_MODE_RENDER_DETECTION = 11;
    
    /** 留声相机 */
	public static final int CAMERA_MODE_SOUND = 12;
	
	/**
     * @Description 最低允许拍照SD卡容量20M，单位byte.
     */
    public static final long CAMERA_SD_CARD_LIMIT = 20971520;
    
    public static final String DEFALUT_DIRECTORY = GlobalApplication.getSystemPhotoPath();
    
    public static final String SAVE_MODE_CONFIRM = "confirmsave";
    public static final String SAVE_MODE_AUTO = "auto";
    public static final String SAVE_MODE_2S = "2sdelay";

	public static void viberate(Context context, long[] pattern, int repeat) {
		if (null == context)
			return;
		if (CommonSharedPreferences.getInstance(context).getIsOn(CameraSettings.KEY_VIBRATION_FEEDBACK, false)) {
			Vibrator mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
			boolean isSupportVibrate = (null != mVibrator);
			if (isSupportVibrate) {
				mVibrator.vibrate(pattern, repeat);
			}
		}
	}
	
}
