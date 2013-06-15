package com.pinguo.mcamera4fun.settings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.pinguo.mcamera4fun.camera.effect.EffectInfoFactory;
import com.pinguo.mcamera4fun.utils.CameraUtil;

public class CommonSharedPreferences implements ICameraSetting {
	
	String TAG = "CommonSharedPreferences";
	
	private SharedPreferences mLocalPref;
	private SharedPreferences mGlobalPref;
	private static ConcurrentHashMap<Context, CommonSharedPreferences> ctxMap;
	public static String PREF_CAMERA_TYPE = "pref_camera_type";
	public static String PREF_CAMERA_ID = "pref_camera_id";
	
	private static HashMap<String, Integer> gmap;
	
	private static final String on = "on";
	
	static {
		gmap = new HashMap<String, Integer>();
		int index = 0;
		gmap.put(PREF_CAMERA_ID, index++);
		gmap.put(PREF_CAMERA_TYPE, index++);
	}

	private static CommonSharedPreferences instance;
	private CommonSharedPreferences(Context mContext) {
		mGlobalPref = PreferenceManager.getDefaultSharedPreferences(mContext);
		mLocalPref = mContext.getSharedPreferences(mContext.getPackageName() + "_local", Context.MODE_PRIVATE);
	}
	public static CommonSharedPreferences getInstance(Context context) {
		if(null == ctxMap) 
			ctxMap = new ConcurrentHashMap<Context, CommonSharedPreferences>();
		if(ctxMap.contains(context)) {
			return ctxMap.get(context);
		} else {
			instance = new CommonSharedPreferences(context);
			ctxMap.put(context, instance);
			return instance;
		}
	}
	
	private static Set<String> mGlobalKey = new HashSet<String>();
    
    static {
        mGlobalKey.add(CameraSettings.KEY_CAMERA_ID);
        mGlobalKey.add(CameraSettings.KEY_AUTOMATIC_ENTER_EFFECTS_MODEL);
        mGlobalKey.add(CameraSettings.KEY_RECORD_LOCATION);
        mGlobalKey.add(CameraSettings.KEY_CAMERA_FIRST_USE_HINT_SHOWN);
        mGlobalKey.add(CameraSettings.KEY_VOLUME_KEYS);
        mGlobalKey.add(CameraSettings.KEY_TIMER);
        mGlobalKey.add(CameraSettings.KEY_CAMERA_MODE);
        mGlobalKey.add(CameraSettings.KEY_TOUCH_SHOT);
        mGlobalKey.add(CameraSettings.KEY_SHOT_SOUND);
        mGlobalKey.add(CameraSettings.KEY_DEBOUNCE);
        mGlobalKey.add(CameraSettings.KEY_IS_SAVE_ORG_PIC);
        mGlobalKey.add(CameraSettings.KEY_TIME_WATERMARK);
        mGlobalKey.add(CameraSettings.KEY_NOISE_REMOVAL);
        mGlobalKey.add(CameraSettings.KEY_EFFECT_NAME);
        mGlobalKey.add(CameraSettings.KEY_EFFECT_PARAM);
        mGlobalKey.add(CameraSettings.KEY_SUB_EFFECT_PARAM);
        mGlobalKey.add(CameraSettings.KEY_JPEG_QUALITY);
        mGlobalKey.add(CameraSettings.KEY_PREV_EFFECT_PARAM);
        mGlobalKey.add(CameraSettings.KEY_SCENE_TEMPLATE_PARAM);
        mGlobalKey.add(CameraSettings.KEY_PIC_SAVE_TYPE);
        mGlobalKey.add(CameraSettings.KEY_PIC_SAVE_PATH);
        mGlobalKey.add(CameraSettings.KEY_SCENE_TEMPLATE_SELECT);
        mGlobalKey.add(CameraSettings.KEY_LAST_UPDATE_CHECK_TIME);
        mGlobalKey.add(CameraSettings.KEY_COMPOSITION_LINE);
        mGlobalKey.add(CameraSettings.KEY_FIRST_SAVE_FLAG);
        mGlobalKey.add(CameraSettings.KEY_PICTURE_AUTO_SAVE_MODE);
        mGlobalKey.add(CameraSettings.KEY_SWITCH_CAMERA);
        mGlobalKey.add(CameraSettings.KEY_FIRST_START_FLAG);
        mGlobalKey.add(CameraSettings.KEY_FIRST_HAS_RENDER);
        mGlobalKey.add(CameraSettings.KEY_ENABLE_RENDER);
        mGlobalKey.add(CameraSettings.KEY_RENDER_ABILITY);
        mGlobalKey.add(CameraSettings.KEY_SCENE_TEMPLATE_HAS_NEW);
        mGlobalKey.add(CameraSettings.KEY_SHOT_SOUND_FIRST_SHOW);
        mGlobalKey.add(CameraSettings.KEY_BACK_SAVE);
        mGlobalKey.add(CameraSettings.KEY_FRONT_MIRROR);
        mGlobalKey.add(CameraSettings.KEY_SMART_RESOLUTION_IMPROVEMENT);
        mGlobalKey.add(CameraSettings.KEY_VIBRATION_FEEDBACK);
        mGlobalKey.add(CameraSettings.KEY_HIDE_INDICATOR);
        mGlobalKey.add(CameraSettings.KEY_FIRST_INIT_SCENE);
        mGlobalKey.add(CameraSettings.KEY_FIRST_CLICK_CLD_BKP);
        
        mGlobalKey.add(CameraSettings.KEY_FRONT_REDRESS);
        mGlobalKey.add(CameraSettings.KEY_FRONT_REDRESS_DEGREE);
        mGlobalKey.add(CameraSettings.KEY_FRONT_REDRESS_MIRROR);
        mGlobalKey.add(CameraSettings.KEY_BACK_REDRESS);
        mGlobalKey.add(CameraSettings.KEY_BACK_REDRESS_DEGREE);
        mGlobalKey.add(CameraSettings.KEY_FIRST_SHOW_WELCOME);
        
        mGlobalKey.add(CameraSettings.KEY_PREVIEW_ADJUST_FIRST_TIPS);
        mGlobalKey.add(CameraSettings.KEY_FRONT_PREVIEW_ADJUST);
        mGlobalKey.add(CameraSettings.KEY_BACK_PREVIEW_ADJUST);
        mGlobalKey.add(CameraSettings.KEY_FRONT_PREVIEW_ADJUST_DEGREE);
        mGlobalKey.add(CameraSettings.KEY_BACK_PREVIEW_ADJUST_DEGREE);
        mGlobalKey.add(CameraSettings.KEY_RENDER_FRONT_PREVIEW_ADJUST_DEGREE);
        mGlobalKey.add(CameraSettings.KEY_RENDER_BACK_PREVIEW_ADJUST_DEGREE);
        
        mGlobalKey.add(CameraSettings.KEY_SHOWN_RATE_DIALOG);
    };

    private static boolean isGlobal(String key) {
        return mGlobalKey.contains(key);
    }
	
	public String getString(String key, String defaultValue) {
		if(gmap.containsKey(key)) {
			return mGlobalPref.getString(key, defaultValue);
		} else {
			return mLocalPref.getString(key, defaultValue);
		}
	}
	
	public void putString(String key, String value) {
		Editor editor = null;
		if(gmap.containsKey(key)) {
			editor = mGlobalPref.edit();
			editor.putString(key, value);
			editor.commit();
		} else {
			editor = mLocalPref.edit();
			editor.putString(key, value);
			editor.commit();
		}
	}
	
	public int getInt(String key) {
		if(gmap.containsKey(key)) {
			return mGlobalPref.getInt(key, -1);
		} else {
			return mLocalPref.getInt(key, -1);
		}
	}
	
	public int getInt(String key, int defVal) {
		if(null == mLocalPref) {
			return mGlobalPref.getInt(key, defVal);
		}
		if(!isGlobal(key) && null != mLocalPref) {
			return mLocalPref.getInt(key, defVal);
		} else {
			return mGlobalPref.getInt(key, defVal);
		}
	}
	
	public void setInt(String key, int value) {
		Editor editor = null;
		if(gmap.containsKey(key)) {
			editor = mGlobalPref.edit();
			editor.putInt(key, value);
			editor.commit();
		} else {
			editor = mLocalPref.edit();
			editor.putInt(key, value);
			editor.commit();
		}
	}
	@Override
	public void setCameraMode(int mode) {
		
	}
	@Override
	public int getCameraMode() {
		return getInt(CameraSettings.KEY_CAMERA_MODE, CameraUtil.CAMERA_MODE_EFFECT);
	}
	
	public boolean getIsOn(String key, boolean defVal) {
		if(null == key)
			return defVal;
		String isOn = getString(key, null);
		if(null == isOn)
			return defVal;
		if(on.equals(isOn))
			return true;
		else 
			return false;
	}
	public String getPreferEffect() {
		return getString(CameraSettings.KEY_PREV_EFFECT_PARAM, EffectInfoFactory.PARAM_RADOM);
	}
	
	
	
}
