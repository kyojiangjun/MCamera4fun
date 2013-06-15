package com.pinguo.mcamera4fun.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;

public class FileUtils {

	public static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String CAMERA360_ROOT = SDCARD_ROOT + "/Camera360";
    
public static void copyImageFromAsset(Context context, String fileName, String filePath) {
        
        File fileDir = new File(filePath);
        if (!fileDir.exists()) { 
            fileDir.mkdirs();
        }
        
        String wholePath;
        if (filePath.endsWith("/")) {
            wholePath = filePath + fileName;
        } else {
            wholePath = filePath + "/" + fileName;
        }
        
        File file = new File(wholePath);
        
        if (file.exists()) {
            // 如果文件存在，检测是否复制成功
            Options myOptions = new Options();
            myOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(wholePath, myOptions);
            if (myOptions.outWidth > 0 && myOptions.outHeight > 0) {
                return;
            }
        }

        // 若文件不存在，则copy
        AssetManager am = context.getAssets();
        InputStream is;
        FileOutputStream fos;
        try {
            is = am.open(fileName);
            fos = new FileOutputStream(wholePath);
            
            int bytesRead;
            byte[] buf = new byte[4 * 1024];
            while ((bytesRead = is.read(buf)) != -1) {
                fos.write(buf, 0, bytesRead);
            }
            
            fos.flush();
            fos.close();
            is.close();
        
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        is = null;
        am = null;
        fos = null;
        System.gc();
    }
	
}
