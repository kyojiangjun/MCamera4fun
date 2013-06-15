package com.pinguo.mcamera4fun.camera.mode;

import com.pinguo.mcamera4fun.camera.CameraEventInterface;
import com.pinguo.mcamera4fun.ui.CameraUiListener;

public interface ModeInterface extends CameraUiListener, CameraEventInterface{

	/**
	 * 拍照模式.
	 * 
	 * @author tangsong
	 */
	public enum CameraMode {
		/**
		 * 标准.
		 */
		STANDARD,
		/**
		 * 自拍.
		 */
		SELF,
		/**
		 * 连拍.
		 */
		BURST,
		/**
		 * 定时.
		 */
		TIMER,
		/**
		 * 防抖动.
		 */
		ANTI_SHAKE,
		/**
		 * 多格.
		 */
		MULTI_GRID
	}
	
}
