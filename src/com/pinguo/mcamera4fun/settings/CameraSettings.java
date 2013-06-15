/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pinguo.mcamera4fun.settings;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.media.CamcorderProfile;
import android.os.Environment;
import android.util.FloatMath;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Provides utilities and keys for Camera settings.
 */
@TargetApi(14)
public class CameraSettings {
    private static final String TAG = "CameraSettings";
    private static final int NOT_FOUND = -1;

    public static final String KEY_VERSION = "pref_version_key";
    public static final String KEY_LOCAL_VERSION = "pref_local_version_key";
    public static final String KEY_EFFECT_PARAM = "pref_effect_param_key";
    public static final String KEY_EFFECT_NAME = "pref_effect_name_key";
    public static final String KEY_PREV_EFFECT_PARAM = "pref_prev_effect_param_key";
    public static final String KEY_SUB_EFFECT_PARAM = "pref_sub_effect_param_key";
    public static final String KEY_SCENE_TEMPLATE_PARAM = "pref_scene_template_key";
    public static final String KEY_SCENE_TEMPLATE_SELECT = "pref_scene_template_select_key";
    public static final String KEY_SCENE_TEMPLATE_HAS_NEW = "pref_scene_template_has_new_key";

    // first click cloud backup.
    public static final String KEY_FIRST_CLICK_CLD_BKP = "pref_first_click_cld_bkp_key";
    
    // camera first init.
    public static final String KEY_FIRST_INIT_FLAG = "pref_first_init_flag_key";
//    /**
//     *  如果需要修改为locale类型，那么必须废弃掉此key，使用新key :  tangsong & tangzhen
//     */
//    public static final String KEY_RENDER_DETECTIOIN_FLAG = "pref_render_detection_key";
//    public static final String KEY_CAN_RENDER_DETECTIOIN = "pref_can_render_detection";

    // scene first init.
    public static final String KEY_FIRST_INIT_SCENE = "pref_first_init_scene_key";
    
    // app first start
    /**
     * pref_first_start_flag_key
     * @Description 变量描述.
     */
    public static final String KEY_FIRST_START_FLAG_OLD = "pref_first_start_flag_key";
    
    public static final String KEY_FIRST_START_FLAG = "pref_first_start_flag_key_20130427";
    
    public static final String KEY_FIRST_HAS_RENDER = "pref_first_has_render";
    
    // app first show welcome page
    public static final String KEY_FIRST_SHOW_WELCOME = "pref_first_welcome_flag_key";

    // picture first save
    public static final String KEY_FIRST_SAVE_FLAG = "pref_first_save_flag_key";

    // picture first adjust
    public static final String KEY_FIRST_ADJUST_FLAG = "pref_first_adjust_flag_key";
    
    // switch camera triggered
    public static final String KEY_SWITCH_CAMERA = "pref_switch_camera_key";

    // render enable
    public static final String KEY_ENABLE_RENDER = "pref_is_enable_render_key";

    // render ability
    public static final String KEY_RENDER_ABILITY = "pref_render_ability_key";

    // hide indicator
    public static final String KEY_HIDE_INDICATOR = "pref_hide_indicator_key";

    // 1. first indicator bar.
    public static final String KEY_FLASH_MODE = "pref_camera_flashmode_key";
    public static final String KEY_CAMERA_ID = "pref_camera_id_key";
    public static final String KEY_FOCUS_MODE = "pref_camera_focusmode_key";
    public static final String KEY_TIMER = "pref_camera_timer_key";

    // 2. second indicator bar.
    public static final String KEY_WHITE_BALANCE = "pref_camera_whitebalance_key";
    public static final String KEY_TOUCH_SHOT = "pref_camera_touchshot_key";
    public static final String KEY_SHOT_SOUND = "pref_camera_shotsound_key";
    public static final String KEY_DEBOUNCE = "pref_camera_debounce_key";
    public static final String KEY_EXPOSURE = "pref_camera_exposure_key";

    public static final String KEY_SHOT_SOUND_FIRST_SHOW = "pref_camera_shotsound_firstshown_key";

    // 3. other advanced settings.
    // 3.1 Smart easy settings.
    public static final String KEY_SMART_RESOLUTION_IMPROVEMENT = "pref_camera_smartresolutionimprovement_key";
    public static final String KEY_NOISE_REMOVAL = "pref_camera_noiseremovel_key";
    public static final String KEY_PICTURE_AUTO_SAVE_MODE = "pref_camera_pictureautosavemode_key";
    public static final String KEY_COMPOSITION_LINE = "pref_camera_compositionline_key";
    public static final String KEY_VOLUME_KEYS = "pref_camera_volumekeys_key";
    public static final String KEY_AUTOMATIC_ENTER_EFFECTS_MODEL = "pref_camera_automatic_enter_effects_model_key";
    public static final String KEY_BACK_SAVE = "pref_camera_back_save_key";

    // 3.2 picture settings.
    public static final String KEY_PICTURE_SIZE = "pref_camera_picturesize_key";
    public static final String KEY_JPEG_QUALITY = "pref_camera_jpegquality_key";
    public static final String KEY_TIME_WATERMARK = "pref_camera_timewatermake_key";
    public static final String KEY_RECORD_LOCATION = "pref_camera_recordlocation_key";
    public static final String KEY_IS_SAVE_ORG_PIC = "pref_camera_saveorg_key";

    // 3.3 camera settings.
    public static final String KEY_ISO = "pref_camera_iso_key";
    public static final String KEY_METERING_MODE = "pref_camera_meteringmode_key";
    public static final String KEY_FRONT_MIRROR = "pref_camera_frontmirror_key";
    public static final String KEY_HARDWARE_COMPATIBILITY = "pref_camera_hardwarecompatibility_key";
    public static final String KEY_VIBRATION_FEEDBACK = "pref_camera_vibrationfeedback_key";

    public static final String KEY_PIC_SAVE_TYPE = "pref_camera_save_type_key";
    public static final String KEY_PIC_SAVE_PATH = "pref_camera_save_path_key";
    public static final String KEY_CLOUD_SHARE = "pref_camera_cloudshare_key";
    public static final String KEY_SHARE_SETTING = "pref_camera_sharesetting_key";
    public static final String KEY_ENABLE_ZOOM = "pref_camera_enable_zoom_key";

    // 4. bottom bar.
    public static final String KEY_CAMERA_MODE_HAS_NEW = "pref_camera_mode_has_new_key";
    public static final String KEY_CAMERA_MODE_NEW = "pref_camera_mode_new_key";
    public static final String KEY_CAMERA_MODE = "pref_camera_mode_key";

    public static final String KEY_MODE_PICKER = "pref_video_effect_key";

    public static final String KEY_CAMERA_FIRST_USE_HINT_SHOWN = "pref_camera_first_use_hint_shown_key";

    public static final String KEY_EFFECT_POP_TYPE = "pref_key_effect_pop_type";

    public static final String EXPOSURE_DEFAULT_VALUE = "0";

    public static final String KEY_LAST_UPDATE_CHECK_TIME = "pref_key_last_update_check_time";

    public static final String KEY_MIN_SENSOR_VALUE = "pref_key_min_sensor_value";
    
    /**
     * 前置摄像头是否矫正（false，不矫正、true、矫正）
     */
    public static final String KEY_FRONT_REDRESS = "pref_key_front_redress";

    /**
     * 后置摄像头是否矫正（false，不矫正、true、矫正）
     */
    public static final String KEY_BACK_REDRESS = "pref_key_back_redress";
    
    /**
     * 前置摄像头是否矫正度数
     */
    public static final String KEY_FRONT_REDRESS_DEGREE = "pref_key_front_redress_degree";
    
    /**
     * 前置摄像头是否矫正镜像
     */
    public static final String KEY_FRONT_REDRESS_MIRROR = "pref_key_front_mirror";
    
    /**
     * 前置摄像头是否矫正度数
     */
    public static final String KEY_BACK_REDRESS_DEGREE = "pref_key_back_redress_degree";
    
    /**
     * 前置摄像头预览是否矫正（false，不矫正、true、矫正）
     */
    public static final String KEY_FRONT_PREVIEW_ADJUST = "pref_key_front_preview_adjust";

    /**
     * 后置摄像头预览是否矫正（false，不矫正、true、矫正）
     */
    public static final String KEY_BACK_PREVIEW_ADJUST = "pref_key_back_preview_adjust";
    
    /**
     * 前置摄像头预览矫正度数
     */
    public static final String KEY_FRONT_PREVIEW_ADJUST_DEGREE = "pref_key_front_preview_adjust_degree";
    // 实时预览
    public static final String KEY_RENDER_FRONT_PREVIEW_ADJUST_DEGREE = "pref_key_render_front_preview_adjust_degree";
    
    /**
     * 前置摄像头预览矫正度数
     */
    public static final String KEY_BACK_PREVIEW_ADJUST_DEGREE = "pref_key_back_preview_adjust_degree";
    // 实时预览
    public static final String KEY_RENDER_BACK_PREVIEW_ADJUST_DEGREE = "pref_key_render_back_preview_adjust_degree";
    
    public static final String KEY_PREVIEW_ADJUST_FIRST_TIPS = "pref_key_preview_adjust_tips";
    
    /**
     * 首次退出弹出评分提示
     */
    public static final String KEY_SHOWN_RATE_DIALOG = "pref_key_show_rate_dialog";
    
    public static final int CURRENT_VERSION = 5;
    public static final int CURRENT_LOCAL_VERSION = 2;

    private static final int MIN_PICTURE_SIZE = 100 * 10000;
    private static final float RATIO_4_3 = 4 / 3.0f;
    private static final float RATIO_16_9 = 16 / 9.0f;
    private static final float RATIO_16_10 = 16 / 10.0f;
    private static final float RATIO_THRESHOLD = 0.1f;
    
    public static final boolean IS_USE_DCIM = false;
}
