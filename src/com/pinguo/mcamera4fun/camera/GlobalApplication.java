package com.pinguo.mcamera4fun.camera;

import java.io.File;

import com.pinguo.mcamera4fun.settings.CameraSettings;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

public class GlobalApplication extends Application {
	
	String TAG = "GlobalApplication";
	
	@Override
	public void onCreate() {
		Log.i(TAG, "GlobalApplication | onCreate");
		super.onCreate();
	}
	
	public static String getSystemPhotoPath() {
		String dcimPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator;
		if (CameraSettings.IS_USE_DCIM) {
			return dcimPath;
		} else {
			return dcimPath + "Camera" + File.separator;
		}
	}

}
