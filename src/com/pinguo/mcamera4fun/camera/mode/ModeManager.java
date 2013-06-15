package com.pinguo.mcamera4fun.camera.mode;

import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;
import com.pinguo.mcamera4fun.utils.CameraUtil;
import android.app.Activity;
import android.content.Context;

public class ModeManager {

	private int mCurModeId;
	private int mLastModeId;
	
	private Activity mActivity;
	private Context mContext;
	
	private ModeAbstract mCurMode;
	
	private ModeNormal mNormal;
	private ModeNoEffect mNoEffect;
	
	private ModeAbstract mBaseMode;
	
	private static ModeManager instance;
	private ModeManager(Activity mActivity) {
		this.mActivity = mActivity;
		this.mContext = mActivity.getApplicationContext();
		this.mLastModeId = CommonSharedPreferences.getInstance(mContext).getCameraMode();
		this.mCurModeId = mLastModeId;
		
		this.mNormal = new ModeNormal(mActivity, null);
		this.mBaseMode = mNormal;
	}
	public static ModeManager getInstance(Activity mActivity) {
		if(null == instance)
			instance = new ModeManager(mActivity);
		return instance;
	}
	
	public int getmCurModeId() {
		return mCurModeId;
	}
	public void setmCurModeId(int mCurModeId) {
		this.mCurModeId = mCurModeId;
	}
	public int getmLastModeId() {
		return mLastModeId;
	}
	public void setmLastModeId(int mLastModeId) {
		this.mLastModeId = mLastModeId;
	}
	
	public ModeAbstract getMode(int mode, boolean update) {
		if(null != mCurMode && mode == mCurModeId && !update)
			return mCurMode;
		ModeAbstract tMode = mCurMode;
		while(null != tMode) {
			tMode.unload();
			tMode = tMode.getDecorator();
		}
		
		ModeAbstract retMode = null;
		switch(mode) {
		case CameraUtil.CAMERA_MODE_NOMARL:
			if(null == mNoEffect)
				mNoEffect = new ModeNoEffect(mActivity, mBaseMode);
			retMode = mNoEffect;
			break;
		
		}
		
		retMode.load();
		retMode = updateMode(retMode);
		
		mLastModeId = mCurModeId;
		mCurModeId = mode;
		
		mCurMode = retMode;
		
		return retMode;
	}
	
	public ModeAbstract getCurModeById() {
		return getMode(mCurModeId, false);
	}

	public ModeAbstract getLastMode() {
		return getMode(mLastModeId, false);
	}

	public ModeAbstract getCurMode() {
		return mCurMode;
	}
	
	private ModeAbstract updateMode(ModeAbstract retMode) {
		return retMode;
	}
}
