package com.pinguo.mcamera4fun.camera.scene;

import com.pinguo.mcamera4fun.camera.push.PushPreference;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.utils.CameraUtil;
import com.pinguo.mcamera4fun.camera.mode.ModeManager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SceneTemplateModel {

	public static boolean hasNew(Activity mActivity) {
        if (ModeManager.getInstance(mActivity).getmCurModeId() != CameraUtil.CAMERA_MODE_EFFECT) {
            return false;
        }

        SharedPreferences pref = mActivity
                .getSharedPreferences(PushPreference.PUSH_FILE_NAME, Context.MODE_MULTI_PROCESS);
        return pref.getBoolean(CameraSettings.KEY_SCENE_TEMPLATE_HAS_NEW, false);
    }
	
}
