
package com.pinguo.mcamera4fun.camera.effect;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.pinguo.mcamera.common.log.GLogger;
import com.pinguo.mcamera4fun.utils.FileUtils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;

public class EffectParamFactory {

    public static final String PATH_GHOST_DIR = FileUtils.CAMERA360_ROOT + "/ghost/";
    
    public static final String DIRECT_0 = "direct=0";

    public static final String DIRECT_1 = "direct=1";

    public static final float DEFAULT_DENSITY = 1.5f;

    public static final float HEAD_CX = 244f;

    public static final float HEAD_CY = 284f;

    public static final float HEAD_CR = 109f;

    private static final int HEAD_SCALE = 185;

    public final static String TIMES = "80";

    // private float deltaX;
    // private float deltaY;

    // private float cX;
    // private float cY;

    private static String PARAM;
    
//    public static final String NOISE = ";effect=denoisebil,3,8"; 
    public static final String SHARPNESS_BASE = "effect=sharpen"; 
    
    public static final int SHARPNESS_BASE_SIZE = 2500000;
    
//    public static final int SHARPNESS_STEP = 500000;
    
    public static final int SHARPNESS_VALUES = 10;

    /**
     * 制作智能清晰度参数。
     * @param length
     * @param orgParam
     * @return
     * @version 1.1 添加如果是无特效特殊判断
     */
    public static String makeSharpnessParam(int length, String orgParam) {
        
        if(orgParam.equals(EffectInfoFactory.PARAM_NONE)){
            orgParam = "effect="+orgParam;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(orgParam);
        sb.append(";");
        sb.append(SHARPNESS_BASE);
        sb.append(",");
        if (SHARPNESS_BASE_SIZE >= length) {
            sb.append(String.valueOf(SHARPNESS_VALUES));
        } else {
            float count = length*SHARPNESS_VALUES/SHARPNESS_BASE_SIZE;
            if (count >100) {
                count = 100;
            }
            sb.append(String.valueOf(count));
        }
//        System.out.print("sharp: "+sb.toString());
        return sb.toString();
    }
    
    /**
     * 生成大头部分效果参数 cxBL,cyBL,rBL:头部圆的中心坐标和半径比例 zoomBL:把圆放大的比例 offsetBL:y坐标的偏移比例
     * 
     * @param deltaX
     * @param deltaY
     * @param density
     * @param preWidth
     * @param preHeight
     * @return
     */
    public static String makeDatouParam(float deltaX, float deltaY, float density, int preWidth,
            int preHeight) {
        /*
         * if (PARAM == null) { synchronized (DatouInfo.class) { if (PARAM ==
         * null) { PARAM = make(deltaX, deltaY, density, preWidth, preHeight); }
         * } }
         */

        PARAM = makeDatou(deltaX, deltaY, density, preWidth, preHeight);

        return PARAM;
    }

    private static String makeDatou(float deltaX, float deltaY, float density, int preWidth,
            int preHeight) {
        /*
         * final float scale = density / DEFAULT_DENSITY; final float cX =
         * HEAD_CX * scale + deltaX / preWidth; final float cY = HEAD_CY * scale
         * + deltaY / preHeight; final float cR = HEAD_CR * scale; final int
         * cxBl = (int) (cX * 100 / preWidth); final int cyBl = (int) (cY * 100
         * / preHeight); final int crBl = (int) (cR * 100 / preWidth); final int
         * deltaHeight = crBl;
         */

        // return "effect=avata,49,34,25,190,15;effect=skin_class,0";
        // retur"effect=avata,49,34,25,190,"+times+";effect=skin_class,0";
        return "effect=avata,49,34,20,190,22," + TIMES + ";effect=enhance_class,0";
    }
    
    public static String makeDatou() {
        return "effect=avata,49,34,20,190,22," + TIMES + ";effect=enhance_class,0";
    }

    /**
     * 生成移轴模式的参数 effect=tiltshift;direct=0;centerbl=30;sizebl=20;amountbl=100
     * 
     * @param direct
     * @param posBL
     * @param sizeBL
     * @return
     */
    public static String MakeTiltShiftParam(int direct, int posBL, int sizeBL) { // 模糊类型
        final int blurType = 0;
        // //色彩加强
        final int shiftColorIdx = 2;
        // 过渡类型
        final int shiftStepValue = 25;
        // 模糊等级
        final int shiftAlphaIdx = 1;
        return "effect=tiltshift;direct=" + Integer.toString(direct) + ";centerbl="
                + Integer.toString(posBL) + ";sizebl=" + Integer.toString(sizeBL) + ";tsblurlv="
                + Integer.toString(shiftAlphaIdx) + ";tstype=" + Integer.toString(blurType)
                + ";tscolor=" + Integer.toString(shiftColorIdx) + ";tsstep="
                + Integer.toString(shiftStepValue + 5);

    }
    
    public static void Make1839Param(Context context) throws Exception {
        FileUtils.copyImageFromAsset(context, "test_old.png", "/sdcard/Camera360/Data");
    }

    /**
     * 生成幽灵模式的参数
     * 
     * @param screenDirect
     * @param context
     * @return
     * @throws Exception
     */
    public static String MakeGhostParam(int screenDirect, Context context) throws Exception {
        int posXbl = (int) (Math.random() * 100);
        int posYbl = (int) (Math.random() * 100);
        int zoomBL = (int) (Math.random() * 30) + 20;       // 20 ~ 50
        int iAlpha = (int) (Math.random() * 20) + 20;       // 20 ~ 40

        // 得到png文件
        String strPngFile;
        strPngFile = null;
        File myFile = new File(PATH_GHOST_DIR);
        if (myFile.exists()) // 若目录存在，则列举文件
        {
            // 列举出所有Png文件
            File[] pngFiles = myFile.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    if (pathname.getName().toLowerCase().endsWith(".png"))
                        return true;
                    else
                        return false;
                }
            });

            if (pngFiles.length > 0) {
                int idx = (int) (Math.random() * pngFiles.length);
                strPngFile = pngFiles[idx].getPath();
                GLogger.i("Effect", "ghost file:" + strPngFile);
            }
        }

        // 如目录中没有文件，则copy缺省的文件
        if (strPngFile == null) {
            try {
                strPngFile = context.getPackageManager().getApplicationInfo(
                        context.getPackageName(), 0).dataDir
                        + "/test_ghost.png";
            } catch (NameNotFoundException e) {
                strPngFile = null;
                e.printStackTrace();
            }
            if (strPngFile != null) {
                File myFile2 = new File(strPngFile);
                if (!myFile2.exists()) // 若文件不存在，则copy
                {
                    try {
                        AssetManager am = context.getAssets();
                        InputStream is = am.open("test_ghost.png");
                        FileOutputStream fos = new FileOutputStream(strPngFile);

                        int bytesRead;
                        byte[] buf = new byte[4 * 1024]; // 4K buffer
                        while ((bytesRead = is.read(buf)) != -1) {
                            fos.write(buf, 0, bytesRead);
                        }
                        fos.flush();
                        fos.close();
                        is.close();

                        is = null;
                        am = null;
                        fos = null;
                        System.gc();
                    } catch (Exception e) {
                        e.printStackTrace();
                        strPngFile = null;
                    }

                }
            }
        }

        if (strPngFile != null) {
            return "effect=ghost;direct=" + Integer.toString(screenDirect) + ";posxbl="
                    + Integer.toString(posXbl) + ";posybl=" + Integer.toString(posYbl) + ";zoombl="
                    + Integer.toString(zoomBL) + ";alpha=" + Integer.toString(iAlpha) + ";pngfile="
                    + strPngFile;
            // mEffectParam = strParam;
        } else {
            throw new NullPointerException(" ghost file is not exist ..");
        }

    }
    

}
