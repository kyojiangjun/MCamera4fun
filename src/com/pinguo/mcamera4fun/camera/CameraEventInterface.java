package com.pinguo.mcamera4fun.camera;

import com.pinguo.mcamera4fun.camera.CameraHolder.CameraType;

import android.hardware.Camera;

public interface CameraEventInterface {
	
	public void setCameraType(CameraType mType);
	public void takePicture();
	public void onTakePictureFailed();
	public void onTakePictureSuccess(byte[] data, Camera device);
}
