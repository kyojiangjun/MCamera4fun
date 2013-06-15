package com.pinguo.mcamera4fun.camera.mode;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.pinguo.mcamera4fun.camera.CameraOperator;
import com.pinguo.mcamera4fun.camera.CameraHolder.CameraType;
import com.pinguo.mcamera4fun.camera.effect.EffectInfo;
import com.pinguo.mcamera4fun.camera.effect.EffectInfoFactory;
import com.pinguo.mcamera4fun.camera.effect.EffectInfoFactory.EffectSearchRuslt;
import com.pinguo.mcamera4fun.camera.sandbox.PhotoProject;
import com.pinguo.mcamera4fun.settings.CameraSettings;
import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;
import com.pinguo.mcamera4fun.settings.EffectPreference;

public abstract class ModeAbstract implements ModeInterface {

	Activity mActivity;
	Context mContext;
	CommonSharedPreferences mPreferences;
	protected String mPreferEffect;
	protected EffectInfo mCurEffect;
	protected EffectInfo mCurSubEffect;
	protected PhotoProject mPhotoProject;

	public ModeAbstract(Activity mActivity) {
		this.mActivity = mActivity;
		this.mContext = mActivity.getApplicationContext();
		mPreferences = CommonSharedPreferences.getInstance(mContext);
		mPhotoProject = new PhotoProject();
	}

	public static final int VIBRATE_TIME = 35;
	public static final int VIBRATE_REPEATE = -1;

	private void vibrate(Context context, long[] pattern, int repeat) {
		Vibrator mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		boolean isSupportVibrate = (null != mVibrator);
		Log.v("Test", "Vibrate: isSupportVibrate:" + isSupportVibrate);
		if (isSupportVibrate) {
			mVibrator.vibrate(pattern, repeat);
		}
	}

	protected void vibrate() {
		if (null != mPreferences && mPreferences.getIsOn(CameraSettings.KEY_VIBRATION_FEEDBACK, true)) {
			long[] vibrPattern = { 0, VIBRATE_TIME };
			vibrate(mContext, vibrPattern, VIBRATE_REPEATE);
		}
	}
	
	@Override
	public void takePicture() {
		CameraOperator.getInstance(mActivity).takePicture();
	}
	
	@Override
	public void onTakePictureFailed() {
		Toast.makeText(mContext, "Take Picture Failed, Please try agan later...", Toast.LENGTH_SHORT).show();
	}
	
	public abstract void load();
    public abstract void unload();
	public abstract ModeAbstract getDecorator();
	
	@Override
	public void setCameraType(CameraType mType) {
		if(null != mPhotoProject)
			mPhotoProject.setmCameraType(mType);
	}
	
	public void setEffect(String eftParam) {
		EffectSearchRuslt esr = EffectInfoFactory.search(eftParam, mActivity);
		if(null == esr || null == esr.eftInfo)
			return;
		mCurEffect = esr.eftInfo;
		if(mCurEffect.isClass()) {
			String subParam = EffectPreference.getInstance(mContext).getString(EffectPreference.KEY_EFFECT_SELECT_VALUE + 
					mCurEffect.param, mCurEffect.param);
			EffectSearchRuslt subEsr = EffectInfoFactory.search(subParam, mActivity);
			for(EffectInfo efi : EffectInfoFactory.obtainEffectClass(mActivity, mCurEffect.param)) {
				if(efi.param.equals(subEsr.eftInfo.param)) {
					mCurSubEffect = efi;
					break;
				}
			}
		} else {
			mCurSubEffect = null;
		}
	}
	
	public EffectInfo refreshEffectParam(EffectInfo eif) {
		return eif;
	}
}
