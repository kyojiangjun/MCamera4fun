package com.pinguo.mcamera4fun.utils;

import java.io.File;

import com.pinguo.mcamera.common.log.GLogger;

import android.app.ActivityManager;
import android.content.Context;
import android.os.StatFs;
import android.os.Debug.MemoryInfo;

public class DeviceUtils {
	
	private static final String TAG = "DeviceUtils";
	
	public static boolean isMemoryEnough(Context context) {
		int myProcessID = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        MemoryInfo[] info = manager.getProcessMemoryInfo(new int[] {myProcessID});
        
        int memoryLimit = manager.getMemoryClass() * 1024;
        int curMemory = info[0].getTotalSharedDirty();
        
        return memoryLimit > curMemory;
	}
	
	public static long getAvailableStorage(String pPath) {
        long availableStorage = 0;
        try {
            // 判断path文件是否存在
            File dir = new File(pPath);
            if (dir.isFile()) {
                GLogger.w(TAG, "Picture save path is a file.");
                return 0;
            }
            
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            StatFs stat = new StatFs(pPath);
            availableStorage = (long) stat.getAvailableBlocks() * (long) stat.getBlockSize();
        } catch (Exception e) {
        }
        return availableStorage;
    }

}
