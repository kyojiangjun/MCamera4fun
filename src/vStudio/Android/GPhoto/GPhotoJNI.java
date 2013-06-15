/*
 *                                                                                        
 * Copyright (c)2010-2012  Pinguo Company
 *                 品果科技                            版权所有 2010-2012
 * 
 * PROPRIETARY RIGHTS of Pinguo Company are involved in the
 * subject matter of this material.  All manufacturing, reproduction, use,
 * and sales rights pertaining to this subject matter are governed by the
 * license agreement.  The recipient of this software implicitly accepts   
 * the terms of the license.
 * 本软件文档资料是品果公司的资产,任何人士阅读和使用本资料必须获得
 * 相应的书面授权,承担保密责任和接受相应的法律约束.
 * 
 * FileName : GPhotoJNI.java
 * Author : xuying
 * Modifier : tangzhen
 * Date : Nov 5, 2012 1:43:39 PM 
 */
package vStudio.Android.GPhoto;

import com.pinguo.mcamera.common.log.GLogger;

import android.graphics.Bitmap;

public class GPhotoJNI {
    private static final String TAG = "GPhotoJNI";
	private static final String LIBRARY_NAME = "gphoto";
	public static final int TYPE_EFFECT = 0;
    public static final int TYPE_THUMB = 1;
    public static boolean USE_BGRA = false;
    
    /**
     * 照片时间水印左对齐
     */
    public static final int DATE_WATERMARK_ALIGN_LEFT = -1;
    /**
     * 照片时间水印居中
     */
    public static final int DATE_WATERMARK_ALIGN_CENTER = 0;
    /**
     * 照片时间水印右对齐
     */
    public static final int DATE_WATERMARK_ALIGN_RIGHT = 1;
    
    static {
        System.loadLibrary(LIBRARY_NAME);
    }
    
    private int mPreviewWidth = -1;
    private int mPreviewHeight = -1;
    private int mThumbWidth = -1;
    private int mThumbHeight = -1;
    
    public int getPreviewWidth() {
        return GetPrevImageWidth();
    }
    
    public int getPreviewHeight() {
        return GetPrevImageHeight();
    }
    
    public int getThumbWidth() {
        return mThumbWidth;
    }
    
    public int getThumbHeight() {
        return mThumbHeight;
    }
	
	public GPhotoJNI() {
//		System.out.println("USE_BGRA "+USE_BGRA);
		if (USE_BGRA) {
			SetJniBitmapUseBGRA(true);
		} else {
			SetJniBitmapUseBGRA(false);
		}
	}
		
    /**
     * 设置Bitmap是否使用BGRA格式 
     *
     * @author xuying
     * @param useBGRA true表示Bitmap改为使用BGRA，false则使用默认的RGBA
     * @return void
     */
    public native void SetJniBitmapUseBGRA(boolean useBGRA);
    /**
     * 设置底层库参数.  
     *
     * @author xuying
     * @param JpegQuality jpeg图片压缩质量
     * @param bSaveOrgImage 是否保存原始图片
     * @param bDeNoise 是否去噪
     * @param DeNoisePercent 去噪比例
     * @param SharpenPercent 磨皮比例
     * @return boolean true标识设置成功，false为设置失败
     */
    public native boolean SetParams(int JpegQuality, boolean bSaveOrgImage, boolean bDeNoise, int DeNoisePercent,
            int SharpenPercent);
	
    /**************************************************************************
     *                               情景模板
     **************************************************************************/
	public native boolean SetSceneTemplateData(String pngFileName, int prevMaxWH);
	public native int GetSceneTemplateWidth();
	public native int GetSceneTemplateHeight();
	public native boolean MakeScenePrevImage(int[] arrRet,byte[] arrYuv,int yuvWidth,int yuvHeight,String Params);
	public native int GetScenePrevTemplateHeight();
	public native int GetScenePrevTemplateWidth();
	public native int[] MakeSceneImage(byte[] arrJpeg,int JpegLength,int SceWidth,int SceHeight,String Params,String SaveFileName);
	public native void FreeSceneData();
	
	/**************************************************************************
     *                               特效图
     **************************************************************************/
    /**
     * 制作并存储特效照片
     * 
     * @author xuying
     * @param OrgFileName 原始图像文件名
     * @param SaveFileName 保存的文件名
     * @param Params 效果参数
     * @param arrExif exif信息(byte数组)
     * @param exifLength exif长度(byte数组长度)
     * @param rotateAngle 旋转角度(默认0, 可用值90, 180, 270)
     * @param LimitSize 限制处理大小(单位:万像素, 默认为0)
     * @param iZoom 图片缩放(默认1)
     * @return 返回制作是否成功
     */
    public native boolean MakeEffectImageEX(String OrgFileName, String SaveFileName, String Params, byte[] arrExif,
            int exifLength, int rotateAngle, int LimitSize, int iZoom);

    /**
     * 制作并存储特效照片(不缩放，不限制图片大小)
     * 
     * @author xuying
     * @param OrgFileName 原始图像文件名
     * @param SaveFileName 保存的文件名
     * @param Params 效果参数
     * @param arrExif exif信息(byte数组)
     * @param exifLength exif长度(byte数组长度)
     * @param rotateAngle 旋转角度(默认0, 可用值90, 180, 270)
     * @return 返回制作是否成功
     */
    public boolean MakeEffectImageEX(String OrgFileName, String SaveFileName, String Params, byte[] arrExif,
            int exifLength, int rotateAngle) {
        return MakeEffectImageEX(OrgFileName, SaveFileName, Params, arrExif, exifLength, rotateAngle, 0, 1);
    }

    /**
     * 读取jpeg文件到Bitmap
     *
     * @author xuying
     * @param jpgFileName 需加载的文件名
     * @param bmp *未知
     * @return 是否加载成功
     */
    public native boolean LoadBitmapFromJpgFile(String jpgFileName, Bitmap bmp);
    
    /**
     * 存储Bitmap为jpeg文件
     *
     * @author xuying
     * @param bmp 待存储的bitmap
     * @param jpgFileName 保存的文件名
     * @param quality jpeg压缩质量
     * @return boolean
     */
    public native boolean SaveBitmapToJpgFile(Bitmap bmp, String jpgFileName, int quality);

    /**
     * 设置时间水印Bitmap  
     *
     * @author xuying
     * @param dateBitmap 已生成的含有时间水印的bitmap
     * @param AlignMode 对齐方式 {@link GPhotoJNI#DATE_WATERMARK_ALIGN_LEFT GPhotoJNI.DATE_WATERMARK_ALIGN_LEFT}
     *      {@link GPhotoJNI#DATE_WATERMARK_ALIGN_CENTER GPhotoJNI.DATE_WATERMARK_ALIGN_CENTER}
     *      {@link GPhotoJNI#DATE_WATERMARK_ALIGN_RIGHT GPhotoJNI.DATE_WATERMARK_ALIGN_RIGHT}
     * @return void
     */
    public native void SetDateBitmap(Bitmap dateBitmap, int AlignMode);
    
    /**************************************************************************
     *                               预览图
     **************************************************************************/
    public boolean beginProcessPreview(byte[] arrJpg, int Length, int RotateAngle, int PrevW, int PrevH, int ThumbW,
            int ThumbH) {
        GLogger.v(TAG, "beginProcessPreview: size = " + PrevW + " x " + PrevH + " thumb size = " + ThumbW + " x "
                + ThumbH + ", angle = " + RotateAngle);
        boolean bOK;
        bOK = BeginProcessPrevImage(arrJpg, Length, RotateAngle, PrevW, PrevH, ThumbW, ThumbH);

        if (bOK) {
            mPreviewWidth = getPreviewWidth();
            mPreviewHeight = getPreviewHeight();
            mThumbWidth = ThumbW;
            mThumbHeight = ThumbH;
        }
        return bOK;
    }
    
    //---------------效果图分类制作，通过全局类制作－－－－－－－－－－
    //1.A制作效果图的初始化(从原始jpg大图)
    public native boolean BeginProcessPrevImage(byte[] arrJpg,int Length,int RotateAngle,int PrevW,int PrevH,int ThumbW,int ThumbH);
    //1.B制作效果图的初始化(从预览jpg小图)
    public native boolean BeginProcessPrevImageFromOrgPrevImg(byte[] arrJpg,int Length,int ThumbW,int ThumbH);

    //1.C制作效果图的初始化(从文件)
    public native boolean BeginProcessPrevImageFromFile(String imgFilename,int RotateAngle,int MaxPrevWH,int ThumbWH);
    //1.D制作效果图的初始化(从预览jpg小图文件)
    /**
     * @Description 方法描述.  
     *
     * @author zengchuanmeng
     * @param imgFilename
     * @param ThumbWH
     * @return boolean
     */
    public native boolean BeginProcessPrevImageFromOrgPrevImgFile(String imgFilename,int ThumbWH);

    //2.制作特效照片 (参数 PrevType： 0－－效果图   1－－缩略图）
    /**
     * @Description 方法描述.  
     *
     * @author zengchuanmeng
     * @param Params
     * @param PrevType (参数 PrevType： 0－－效果图   1－－缩略图）
     * @return int[]
     */
    public native int[] ProcessPrevImage(String Params,int PrevType);
    //2A.返回效果原始图
    public native int[] GetOrgPrevImage();
    
    //2.制作特效照片 (参数 PrevType： 0－－效果图   1－－缩略图）
    /**
     * @Description 方法描述.  
     *
     * @author zengchuanmeng
     * @param dstBmp 图像bitmap
     * @param Params 特效参数
     * @param PrevType (参数 PrevType： 0－－效果图   1－－缩略图）
     * @return boolean
     */
    public native boolean ProcessPrevImageEX(Bitmap dstBmp,String Params,int PrevType);
    //2A.返回效果原始图
    /**
     * @Description 方法描述.  
     *
     * @author zengchuanmeng
     * @param dstBmp
     * @return boolean
     */
    public native boolean GetOrgPrevImageEX(Bitmap dstBmp);

    //3.A结束操作
    public native void EndProcessPrevImage();
    
    /**
     * //3.B清空效果类别的临时数据，在切换效果后使用，不用使用begin方法切换效果
     */
    public native void ClearEffectTempData();
    
    //4.得到预览图的大小
    public native int GetPrevImageWidth();
    public native int GetPrevImageHeight();
    
    /**************************************************************************
     *                               其他功能
     **************************************************************************/
    /**
     * 缩放图像
     *
     * @author xuying
     * @param OrgFileName 原始图片路径
     * @param SaveFileName 保存图片路径
     * @param rotateAngle 旋转角度(默认0，可选90, 180, 270)
     * @param LimitSize 限制处理大小(单位:万像素)
     * @param iZoom 提取jpeg文件的倍数
     * @param quality 保存jpeg的质量 (1--100)
     * @return boolean true表示成功，false表示失败
     */
    public native boolean ScaleImageFile(String OrgFileName, String SaveFileName, int rotateAngle, int LimitSize,
            int iZoom, int quality);
    
    /**
     * 将Exif信息写入jpeg文件.  
     *
     * @author xuying
     * @param jpgFileName 保存图片路径
     * @param arrExif exif数据(byte数组)
     * @param exifLength exif数据长度(byte数组长度)
     * @return boolean true表示成功，false表示失败
     */
    public native boolean WriteExifToJpeg(String jpgFileName, byte[] arrExif, int exifLength);
    // TODO : 为什么有两个
    public native boolean WriteJpegExifInfo(String jpgFileName, byte[] arrExif, int exifLength);

    /**
     * 保存jpeg文件，并加入exif信息
     *
     * @author xuying
     * @param jpgFileName 保存图片路径
     * @param arrJpeg jpeg数据(byte数组)
     * @param JpegLength jpeg数据长度(byte数组长度)
     * @param arrExif exif数据(byte数组)
     * @param exifLength exif数据长度(byte数组长度)
     * @return boolean true表示成功，false表示失败
     */
    public native boolean SaveJpegWithExif(String jpgFileName, byte[] arrJpeg, int JpegLength, byte[] arrExif,
            int exifLength);

    
    /**************************************************************************
     *                           其他暂时未使用function
     **************************************************************************/
	public native int test(int Value);
	public native String tests(int Value);
	public native String testss(int Value);
	public native String testf1(int Value);
	public native String testf2(int Value);
	
	public native byte[] DecodeJpeg(byte[] arrJpeg,int jpegLength);
	public native int[] MakeEffectThumImage(byte[] arrYuv,int Width,int Height,String Params);
	public native int[] MakeEffectThumImageFromJpeg(byte[] arrJpg,int Length,int PrevW,int PrevH,String Params);
	// public native boolean MakeEffectImage(byte[] arrJpg,int Length,String Params,byte[] arrExif,int ExifLength);
	// public native boolean SetScenePrevTemplateData(byte[] arrOrgData,int Width,int Height,int prevWidth,int prevHeight);
	//public native int[] MakeScenePrevImage(byte[] arrYuv,int yuvWidth,int yuvHeight,String Params);
	
	public native int MakeEffectImageStep1(byte[] arrJpg,int Length);
	public native boolean MakeEffectImageStep2(int ImgID,int Length,String Params,byte[] arrExif,int ExifLength);
	//存储特效照片方案
	public native boolean MakeEffectImageProject(String PrjFileName,String JpgFileName,byte[] arrJpg,int Length,String Params,byte[] arrExif,int exifLength);
	public native String RunProject(String PrjFileName,int LimitSize);	
	// public native int[] MakeScenePrevImage(byte[] arrYuv,int Width,int Height,byte[] templateData,int tempWidth,int tempHeight,int orgTempWidth,int orgTempHeight,String Params);
	// public native int[] MakeSceneImage(byte[] arrJpeg,int JpegLength,byte[] templateData,int tempWidth,int tempHeight,String Params);
	// public native byte[] GetScenePrevTemplateData(byte[] arrOrgData,int Width,int Height,int dstWidth,int dstHeight);
	
	//制作拼图图像 2张拼图
    /*public native byte[] makeGridImageFor2(int Type,
          byte[] jpgData1,int Length1,int Orientation1,
          byte[] jpgData2,int Length2,int Orientation2,
          String saveFileName);*/
    //制作拼图图像 4张拼图
    /*public native boolean makeGridImageFor4(int Type,
                              byte[] jpgData1,int Length1,int Orientation1,
                              byte[] jpgData2,int Length2,int Orientation2,
                              byte[] jpgData3,int Length3,int Orientation3,
                              byte[] jpgData4,int Length4,int Orientation4,
                              String saveFileName);*/
	
	//设置多格的图像
    /**
     * @Description 方法描述.  
     *
     * @author zengchuanmeng
     * @param index 图片加载编号
     * @param jpgData 图像数据
     * @param Length 图像数据长度
     * @param Orientation 图片当前图片的角度 0 -0，1-90，2-180，3-270
     * @return boolean 是否设置成功
     */
    public native boolean setGridImage(int index,byte[] jpgData,int Length,int Orientation);
    //清空多格图像
    public native void clearGridImages();
    //制作拼图图像 2张拼图 
    public native byte[] makeGridImageFor2EX(int Type,String saveFileName);
    //制作拼图图像 4张拼图 
    public native byte[] makeGridImageFor4EX(int Type,String saveFileName);	
    //拼接图像
    /**
     * @Description 方法描述.  
     *
     * @author zengchuanmeng
     * @param Count 当前拼接图像的数量
     * @param Width 最后生成的图片宽度
     * @param saveFileName 图像保存位置
     * @param margin 生成的图片边距
     * @return boolean 是否拼接成功
     */
    public native boolean makeSpliceImage( int Count,int Width,String saveFileName,int margin);
	public void setPhotoPanoQuality(){
		int quality = 85;
		GLogger.v(TAG, "set jniParams for pano quality = " + quality);
		final boolean saveOrgPic = true;
		// 去噪点设置
		boolean mSetting_DeNoiseUsed = false;
		int mSetting_DeNoisePercent = 6;
		int mSetting_DeN_SharpenPercent = 0;
		SetParams(quality, saveOrgPic, mSetting_DeNoiseUsed, mSetting_DeNoisePercent, mSetting_DeN_SharpenPercent);
	}
}
