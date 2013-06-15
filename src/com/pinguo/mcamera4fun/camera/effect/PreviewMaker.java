package com.pinguo.mcamera4fun.camera.effect;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import vStudio.Android.GPhoto.GPhotoJNI;
import android.content.Context;

import com.pinguo.mcamera4fun.camera.effect.EffectInfoFactory.EffectSearchRuslt;
import com.pinguo.mcamera4fun.camera.sandbox.PhotoProject;
import com.pinguo.mcamera4fun.camera.sandbox.PictureProjectManager.IPictureProgressListener;
import com.pinguo.mcamera4fun.settings.EffectPreference;

public class PreviewMaker {
	
	private Context mContext;
	private IPictureProgressListener mProgressListener;
	private GPhotoJNI mGPhotoJNI;
	private EffectInfo mCurEffectInfo;
	private EffectInfo mCurSubEffectInfo;
	private PhotoProject mPhotoProject;
	private ExecutorService mThreadPool;
	private PreviewRunner mPreviewRunner;
	
	public PreviewMaker(Context mContext, ExecutorService threadPool) {
		mGPhotoJNI = new GPhotoJNI();
		this.mThreadPool = threadPool;
		mPreviewRunner = new PreviewRunner();
	}

	public void setmProgressListener(IPictureProgressListener mProgressListener) {
		this.mProgressListener = mProgressListener;
	}
	
	public void setmPhotoProject(PhotoProject mPhotoProject) {
		this.mPhotoProject = mPhotoProject;
	}

	public void makePreview() {
		mGPhotoJNI.beginProcessPreview(mPhotoProject.getmData(), mPhotoProject.getmData().length,
				mPhotoProject.getmRotateDegree(), mPhotoProject.getmPreviewWidth(), mPhotoProject.getmPreviewHeight(), 
				mPhotoProject.getmThumbWidth(), mPhotoProject.getmThumbHeight());
		mCurEffectInfo = mPhotoProject.getCurEffectInfo();
		mCurSubEffectInfo = mPhotoProject.getmSubEffectInfo();
		if (null != mCurEffectInfo && EffectInfoFactory.isRandom(mCurEffectInfo.param)) {
            // 获取父特效
            ArrayList<EffectInfo> effectList = EffectInfoFactory.obtainRandom(mContext);
            Random random = new Random();
            int randomIndex = random.nextInt(effectList.size());
            EffectInfo effectInfo = effectList.get(randomIndex);

            // 获取子特效
            EffectInfo subEffectInfo = null;
            if (effectInfo.isClass()) {
                String subParam = EffectPreference.getInstence(mContext)
                        .getString(EffectPreference.KEY_EFFECT_SELECT_VALUE + effectInfo.param, null);
                if (null == subParam) {
                    ArrayList<EffectInfo> list = EffectInfoFactory.obtainEffectClass(mContext, effectInfo.param);
                    subEffectInfo = list.get(0);
                } else {
                    EffectSearchRuslt result = EffectInfoFactory.search(subParam, mContext);
                    if (null != result && null != result.eftInfo) {
                        subEffectInfo = result.eftInfo;
                    } else {
                        ArrayList<EffectInfo> list = EffectInfoFactory.obtainEffectClass(mContext, effectInfo.param);
                        subEffectInfo = list.get(0);
                    }
                }
            }
            
            String param = null;
            if (null != subEffectInfo) {
                param = subEffectInfo.param;
            } else {
                param = effectInfo.param;
            }

            mPhotoProject.setEffectInfo(effectInfo);
            mPhotoProject.setSubEffectInfo(subEffectInfo);
            mPhotoProject.setEffectParam(param);
            mPhotoProject.setNeedMakeThumb(true);
            
            mClassEffectInfo = effectInfo;
            mSubEffectInfo = subEffectInfo;
        }
		
		mThreadPool.execute(mPreviewRunner);
	}
	
	private class PreviewRunner implements Runnable {
		@Override
		public void run() {
			
		}
	}

}
