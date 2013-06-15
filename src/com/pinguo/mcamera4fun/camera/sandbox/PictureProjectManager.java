package com.pinguo.mcamera4fun.camera.sandbox;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import android.content.Context;

import com.pinguo.mcamera4fun.camera.effect.PreviewMaker;

public class PictureProjectManager {

	private BlockingQueue<PhotoProject> queue;
	private PhotoProject mCurProject;
	private boolean locked = false;
	private Context mContext;
	private static PictureProjectManager instance;
	private IPictureProgressListener mProgressListener;
	private PreviewMaker mPreviewMaker;
	private ExecutorService mThreadPool;
	
	public void setProgressListener(IPictureProgressListener mProgressListener) {
		this.mProgressListener = mProgressListener;
	}

	public interface IPictureProgressListener {
		public void onMakeFinished(PhotoProject project);
	}
	
	private PictureProjectManager(Context mContext) {
		this.mContext = mContext;
		queue = new LinkedBlockingDeque<PhotoProject>();
		mThreadPool = Executors.newSingleThreadScheduledExecutor();
		mPreviewMaker = new PreviewMaker(mContext, mThreadPool);
	}
	public static PictureProjectManager getInstance(Context mContext) {
		if(null == instance)
			instance = new PictureProjectManager(mContext);
		return instance;
	}
	
	public void addProject(PhotoProject mProject) {
		queue.offer(mProject);
	}
	
	public void makePicture() {
		if(locked) {
			return;
		}
		locked = true;
		
		mCurProject = queue.poll();
		mPreviewMaker.setmPhotoProject(mCurProject);
		mPreviewMaker.makePreview();
	}

}
