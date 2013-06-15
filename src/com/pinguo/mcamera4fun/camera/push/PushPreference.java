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
 * FileName:PushPreference.java
 * Author:liubo
 * Date:Dec 7, 2012 1:40:53 PM 
 * 
 */
package com.pinguo.mcamera4fun.camera.push;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * push配置
 * @author liubo
 * @since 4.0
 * @version 4.0
 */
public class PushPreference {
    //上次成功push时间
    public static final String SUCCESS_PUSH_TIME = "last_push_time";

    //上次成功push时间
    public static final String SUCCESS_PUSHLITE_TIME = "last_pushlite_time";

    //上次成功push时间
    public static final String SUCCESS_UNIFY_TIME = "last_unify_time";

    //上次成功push时间
    public static final String SUCCESS_SPECIAL_TIME = "last_special_time";

    //预置push时间
    public static final String PRE_PUSH_TIME = "push_pre_time";

    //点击暂不更新时，下次更新时间
    public static final String NEXT_UPDATE_TIME = "next_update_time";

    //push间隔
    public static final String PUSH_INTERVAL = "push_interval";

    /**
     * 取不到IMEI将随机生成一个
     */
    public static final String IMEI = "random_imei";

    private SharedPreferences sp = null;
    private SharedPreferences.Editor editor = null;

    public static final String PUSH_FILE_NAME = "push";

    public PushPreference(Context context) {
        this.sp = context.getApplicationContext().getSharedPreferences(PUSH_FILE_NAME, Activity.MODE_MULTI_PROCESS);
        this.editor = sp.edit();
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value);
    }

    public long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value);
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void putString(String key, String value) {
        editor.putString(key, value);
    }

    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void commit() {
        editor.commit();
    }
}
