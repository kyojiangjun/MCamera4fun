package com.pinguo.mcamera4fun.camera.sandbox;

import java.io.Serializable;

import com.pinguo.mcamera4fun.camera.CameraHolder.CameraType;
import com.pinguo.mcamera4fun.camera.effect.EffectInfo;

public class PhotoProject implements Serializable, Cloneable {

	private static final long serialVersionUID = -6928467730393973644L;
	
	private int mRotateDegree = 0;
	/**
	 * 照片高度
	 */
	private int width;
	/**
	 * 照片宽度
	 */
	private int height;
	 /**
     * 预览图宽度
     */
    private int mPreviewWidth = 400;
    /**
     * 预览图高度
     */
    private int mPreviewHeight = 400;
    /**
     * 预览小图宽度
     */
    private int mThumbWidth = 50;
    /**
     * 预览小图高度
     */
    private int mThumbHeight = 50;
	/**
	 * 照片数据
	 */
	private byte[] mData;
	/**
	 * 效果图的保存路径
	 */
	private String mEffSavePath;
	private String mOrgSavePath;
	
	private boolean mNeedMakeThumb;
	private CameraType mCameraType;
	private EffectInfo mCurEffectInfo;
	private EffectInfo mSubEffectInfo;
	
	public EffectInfo getCurEffectInfo() {
		return mCurEffectInfo;
	}
	public void setCurEffectInfo(EffectInfo mCurEffectInfo) {
		this.mCurEffectInfo = mCurEffectInfo;
	}
	
	public EffectInfo getmSubEffectInfo() {
		return mSubEffectInfo;
	}
	public void setmSubEffectInfo(EffectInfo mSubEffectInfo) {
		this.mSubEffectInfo = mSubEffectInfo;
	}
	public CameraType getmCameraType() {
		return mCameraType;
	}
	public void setmCameraType(CameraType mCameraType) {
		this.mCameraType = mCameraType;
	}
	public boolean ismNeedMakeThumb() {
		return mNeedMakeThumb;
	}
	public void setmNeedMakeThumb(boolean mNeedMakeThumb) {
		this.mNeedMakeThumb = mNeedMakeThumb;
	}
	public int getmRotateDegree() {
		return mRotateDegree;
	}
	public void setmRotateDegree(int mRotateDegree) {
		this.mRotateDegree = mRotateDegree;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getmPreviewWidth() {
		return mPreviewWidth;
	}
	public void setmPreviewWidth(int mPreviewWidth) {
		this.mPreviewWidth = mPreviewWidth;
	}
	public int getmPreviewHeight() {
		return mPreviewHeight;
	}
	public void setmPreviewHeight(int mPreviewHeight) {
		this.mPreviewHeight = mPreviewHeight;
	}
	public int getmThumbWidth() {
		return mThumbWidth;
	}
	public void setmThumbWidth(int mThumbWidth) {
		this.mThumbWidth = mThumbWidth;
	}
	public int getmThumbHeight() {
		return mThumbHeight;
	}
	public void setmThumbHeight(int mThumbHeight) {
		this.mThumbHeight = mThumbHeight;
	}
	public byte[] getmData() {
		return mData;
	}
	public void setmData(byte[] mData) {
		this.mData = mData;
	}
	public String getmEffSavePath() {
		return mEffSavePath;
	}
	public void setmEffSavePath(String mEffSavePath) {
		this.mEffSavePath = mEffSavePath;
	}
	public String getmOrgSavePath() {
		return mOrgSavePath;
	}
	public void setmOrgSavePath(String mOrgSavePath) {
		this.mOrgSavePath = mOrgSavePath;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return (PhotoProject)super.clone();
	}
	
}
