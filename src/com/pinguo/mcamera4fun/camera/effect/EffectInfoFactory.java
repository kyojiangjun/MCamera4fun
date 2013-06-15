
package com.pinguo.mcamera4fun.camera.effect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.pinguo.mcamera4fun.R;
import com.pinguo.mcamera4fun.camera.effect.EffectInfo.EffectId;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

public class EffectInfoFactory {

    // 效果分类
    public static final String EFFECT_CLASS = "effect_class=";

    public static final String PARAM_NONE = "none";
    public static final String PARAM_RADOM = "random";
    public static final String PARAM_CLASS_ENHANCE = "effect_class=enhance_class";
    public static final String PARAM_CLASS_SKIN = "effect_class=skin_class";
    public static final String PARAM_CLASS_LOMO = "effect_class=lomo_class";
    public static final String PARAM_CLASS_LIGHT_COLOR = "effect_class=lightcolor_class";
    public static final String PARAM_CLASS_HRD_PRO = "effect_class=hdrpro_class";
    public static final String PARAM_CLASS_RETRO = "effect_class=retro_class";
    public static final String PARAM_CLASS_SEKTCH_ = "effect_class=sketch_class";
    public static final String PARAM_CLASS_COLORFUL = "effect_class=colorful_class";
    public static final String PARAM_DREAM = "effect=dream";
    public static final String PARAM_BACK_TO_1839 = "effect=backto1839,/sdcard/Camera360/Data/test_old.png";
    // public static final String PARAM_BACK_TO_1839 = "effect=backto1839," +
    // FileTool.SDCARD_ROOT + "/Camera360/Data/test_old.png";
    public static final String PARAM_CLASS_FUNNY = "effect_class=funny_class";
    public static final String PARAM_GHOST = "ghost";
    public static final String PARAM_CLASS_MAGIC_COLOR = "effect_class=magic_colours_class";
    public static final String PARAM_CLASS_BW = "effect_class=bw_class";
    public static final String PARAM_TILF_SHIFT = "effect=tiltshift";
    // public static final String PARAM_AVATA =
    // "effect=avata,49,34,20,190,22,80;effect=enhance_class,0";
    public static final String PARAM_AVATA = "effect=avata";
    public static final String BW_CLASS = "bw_class";

    public static ArrayList<EffectInfo> obtainMain(Context context) {
        final LinkedList<EffectInfo> allEffect = new LinkedList<EffectInfo>();
        final Resources res = context.getResources();
        // 无特效,4.0单独为普通拍照模式
        EffectInfo none = new EffectInfo();
         none.iconId = R.drawable.cm_eft_ic_none;
//         none.icon = res.getDrawable(none.iconId);
        none.name = res.getString(R.string.ei_none_name);
        none.id = EffectId.NONE;
        none.param = PARAM_NONE;
        none.summary = R.string.ei_none_summary;
        none.description = R.string.ei_none_description;
        // 随机
        EffectInfo random = new EffectInfo();
        random.iconId = R.drawable.cm_eft_ic_random;
        // random.icon = res.getDrawable(random.iconId);
        random.name = res.getString(R.string.ei_random_name);
        random.id = EffectId.RADOM;
        random.param = PARAM_RADOM;
        random.summary = R.string.ei_random_summary;
        random.description = R.string.ei_random_description;

        // 效果增强
        EffectInfo enhance = new EffectInfo();
        enhance.iconId = R.drawable.cm_eft_ic_enhance;
        // enhance.icon = res.getDrawable(enhance.iconId);
        enhance.name = res.getString(R.string.ei_enhance_class_name);
        enhance.id = EffectId.CLASS_ENHANCE;
        enhance.param = PARAM_CLASS_ENHANCE;
        enhance.summary = R.string.ei_enhance_class_summary;
        enhance.description = R.string.ei_enhance_class_description;
        // 美肤
        EffectInfo skin = new EffectInfo();
        skin.iconId = R.drawable.cm_eft_ic_magic_skin;
        // skin.icon = res.getDrawable(skin.iconId);
        skin.name = res.getString(R.string.ei_skin_class_name);
        skin.id = EffectId.CLASS_SKIN;
        skin.param = PARAM_CLASS_SKIN;
        skin.summary = R.string.ei_skin_class_summary;
        skin.description = R.string.ei_skin_class_description;
        // LOMO
        EffectInfo lomo = new EffectInfo();
        lomo.iconId = R.drawable.cm_eft_ic_lomo;
        // lomo.icon = res.getDrawable(lomo.iconId);
        lomo.name = res.getString(R.string.ei_lomo_class_name);
        lomo.id = EffectId.CLASS_LOMO;
        lomo.param = PARAM_CLASS_LOMO;
        lomo.summary = R.string.ei_lomo_class_summary;
        lomo.description = R.string.ei_lomo_class_description;
        // 日系
        EffectInfo lightColor = new EffectInfo();
        lightColor.iconId = R.drawable.cm_eft_ic_lighting;
        // lightColor.icon = res.getDrawable(lightColor.iconId);
        lightColor.name = res.getString(R.string.ei_lightcolor_class_name);
        lightColor.id = EffectId.CLASS_LIGHT_COLOR;
        lightColor.param = PARAM_CLASS_LIGHT_COLOR;
        lightColor.summary = R.string.ei_lightcolor_class_summary;
        lightColor.description = R.string.ei_lightcolor_class_description;

        // HDR
        EffectInfo hdr = new EffectInfo();
        hdr.iconId = R.drawable.cm_eft_ic_hdrpro;
        // hdr.icon = res.getDrawable(hdr.iconId);
        hdr.name = res.getString(R.string.ei_hdr_pro_class_name);
        hdr.id = EffectId.CLASS_HDR;
        hdr.param = PARAM_CLASS_HRD_PRO;
        hdr.summary = R.string.ei_hdr_pro_class_summary;
        hdr.description = R.string.ei_hdr_pro_class_description;
        // 复古
        EffectInfo retro = new EffectInfo();
        retro.iconId = R.drawable.cm_eft_ic_retro;
        // retro.icon = res.getDrawable(retro.iconId);
        retro.name = res.getString(R.string.ei_retro_class_name);
        retro.id = EffectId.CLASS_RETRO;
        retro.param = PARAM_CLASS_RETRO;
        retro.summary = R.string.ei_retro_class_summary;
        retro.description = R.string.ei_retro_class_description;
        // 手绘
        EffectInfo sketch = new EffectInfo();
        sketch.iconId = R.drawable.cm_eft_ic_draw;
        // sketch.icon = res.getDrawable(sketch.iconId);
        sketch.name = res.getString(R.string.ei_sketch_class_name);
        sketch.id = EffectId.CLASS_SKETCH;
        sketch.param = PARAM_CLASS_SEKTCH_;
        sketch.summary = R.string.ei_sketch_class_summary;
        sketch.description = R.string.ei_sketch_class_description;

        // 流光溢彩
        EffectInfo colorful = new EffectInfo();
        colorful.iconId = R.drawable.cm_eft_ic_raindom;
        // colorful.icon = res.getDrawable(colorful.iconId);
        colorful.name = res.getString(R.string.ei_colorful_class_name);
        colorful.id = EffectId.CLASS_COLORFUL;
        colorful.param = PARAM_CLASS_COLORFUL;
        colorful.summary = R.string.ei_colorful_class_summary;
        colorful.description = R.string.ei_colorful_class_description;
        // 梦幻
        EffectInfo dream = new EffectInfo();
        dream.iconId = R.drawable.cm_eft_ic_dream;
        // dream.icon = res.getDrawable(dream.iconId);
        dream.name = res.getString(R.string.ei_dream_class_name);
        dream.id = EffectId.DREAM;
        dream.param = PARAM_DREAM;
        dream.summary = R.string.ei_dream_class_summary;
        dream.description = R.string.ei_dream_class_description;
        // 1839
        EffectInfo backTo1839 = new EffectInfo();
        backTo1839.iconId = R.drawable.cm_eft_ic_1839;
        // backTo1839.icon = res.getDrawable(backTo1839.iconId);
        backTo1839.name = res.getString(R.string.ei_back_to_1839_class_name);
        backTo1839.id = EffectId.BACK_TO_1839;
        backTo1839.param = PARAM_BACK_TO_1839;
        backTo1839.summary = R.string.ei_back_to_1839_class_summary;
        backTo1839.description = R.string.ei_back_to_1839_class_description;

        // 趣味
        EffectInfo funny = new EffectInfo();
        // funny.iconId = R.drawable.cm_eft_ic_fuuny;
        // funny.icon = res.getDrawable(funny.iconId);
        funny.name = res.getString(R.string.ei_funny_class_name);
        funny.id = EffectId.CLASS_FUNNY;
        funny.param = PARAM_CLASS_FUNNY;
        funny.summary = R.string.ei_funny_class_summary;
        funny.description = R.string.ei_funny_class_description;
        // 幽灵
        EffectInfo ghost = new EffectInfo();
        ghost.iconId = R.drawable.cm_eft_ic_ghost;
        // ghost.icon = res.getDrawable(ghost.iconId);
        ghost.name = res.getString(R.string.ei_ghost_name);
        ghost.id = EffectId.GOHST;
        ghost.param = PARAM_GHOST;
        ghost.summary = R.string.ei_ghost_summary;
        ghost.description = R.string.ei_ghost_description;
        // 魔法色彩
        EffectInfo magicColor = new EffectInfo();
        magicColor.iconId = R.drawable.cm_eft_ic_magic_color;
        // magicColor.icon = res.getDrawable(magicColor.iconId);
        magicColor.name = res.getString(R.string.ei_magic_color_class_name);
        magicColor.id = EffectId.CLASS_MAGIC_COLOR;
        magicColor.param = PARAM_CLASS_MAGIC_COLOR;
        magicColor.summary = R.string.ei_magic_color_class_summary;
        magicColor.description = R.string.ei_magic_color_class_description;

        // 黑白
        EffectInfo bw = new EffectInfo();
        bw.iconId = R.drawable.cm_eft_ic_bw;
        // bw.icon = res.getDrawable(bw.iconId);
        bw.name = res.getString(R.string.ei_bw_class_name);
        bw.id = EffectId.CLASS_BW;
        bw.param = PARAM_CLASS_BW;
        bw.summary = R.string.ei_bw_class_summary;
        bw.description = R.string.ei_bw_class_description;
        // 移轴相机
        final EffectInfo tilfShift = new EffectInfo();
        // tilfShift.iconId = R.drawable.effect_shift;
        // tilfShift.icon = res.getDrawable(tilfShift.iconId);
        tilfShift.name = res.getString(R.string.effect_shift_name);
        tilfShift.id = EffectId.TILF_SHIFT;
        tilfShift.param = PARAM_TILF_SHIFT;
        // tilfShift.summary = R.string.ei_random_summary;
        // tilfShift.description = R.string.ei_random_description;

        // //大头
        final EffectInfo bigHead = new EffectInfo();
        bigHead.iconId = R.drawable.cm_eft_ic_datou;
        // bigHead.icon = res.getDrawable(bigHead.iconId);
        bigHead.name = res.getString(R.string.effect_datou_title);
        bigHead.id = EffectId.BIG_HEAD;
        bigHead.param = PARAM_AVATA;
        bigHead.summary = R.string.effect_datou_summary;
        bigHead.description = R.string.effect_datou_description;

        allEffect.add(random);
        allEffect.add(none);
        allEffect.add(enhance);

        allEffect.add(skin);
        allEffect.add(lomo);
        allEffect.add(lightColor);

        allEffect.add(hdr);
        allEffect.add(retro);
        allEffect.add(sketch);

        allEffect.add(colorful);
        allEffect.add(dream);
        allEffect.add(backTo1839);

        allEffect.add(funny);
        allEffect.add(ghost);
        allEffect.add(magicColor);

        allEffect.add(bw);
        allEffect.add(tilfShift);
        allEffect.add(bigHead);

        // 转换为 Array
        ArrayList<EffectInfo> effectArray = new ArrayList<EffectInfo>(allEffect.size());
        for (EffectInfo ei : allEffect) {
            ei.renderName = null;
            ei.renderParam = null;
            effectArray.add(ei);
        }

        // // 特效过滤 zengchuanmeng 2012-11-26
        // effectArray = FilterEffect(effectArray);
        return effectArray;
    }

    public static ArrayList<EffectInfo> obtainRandom(Context context) {
        final LinkedList<EffectInfo> allEffect = new LinkedList<EffectInfo>();
        final Resources res = context.getResources();

        // LOMO
        EffectInfo lomo = new EffectInfo();
        lomo.iconId = R.drawable.cm_eft_ic_lomo;
        // lomo.icon = res.getDrawable(lomo.iconId);
        lomo.name = res.getString(R.string.ei_lomo_class_name);
        lomo.id = EffectId.CLASS_LOMO;
        lomo.param = PARAM_CLASS_LOMO;
        lomo.summary = R.string.ei_lomo_class_summary;
        lomo.description = R.string.ei_lomo_class_description;

        // 日系
        EffectInfo lightColor = new EffectInfo();
        lightColor.iconId = R.drawable.cm_eft_ic_lighting;
        // lightColor.icon = res.getDrawable(lightColor.iconId);
        lightColor.name = res.getString(R.string.ei_lightcolor_class_name);
        lightColor.id = EffectId.CLASS_LIGHT_COLOR;
        lightColor.param = PARAM_CLASS_LIGHT_COLOR;
        lightColor.summary = R.string.ei_lightcolor_class_summary;
        lightColor.description = R.string.ei_lightcolor_class_description;

        // 复古
        EffectInfo retro = new EffectInfo();
        retro.iconId = R.drawable.cm_eft_ic_retro;
        // retro.icon = res.getDrawable(retro.iconId);
        retro.name = res.getString(R.string.ei_retro_class_name);
        retro.id = EffectId.CLASS_RETRO;
        retro.param = PARAM_CLASS_RETRO;
        retro.summary = R.string.ei_retro_class_summary;
        retro.description = R.string.ei_retro_class_description;

        // 手绘
        EffectInfo sketch = new EffectInfo();
        sketch.iconId = R.drawable.cm_eft_ic_draw;
        // sketch.icon = res.getDrawable(sketch.iconId);
        sketch.name = res.getString(R.string.ei_sketch_class_name);
        sketch.id = EffectId.CLASS_SKETCH;
        sketch.param = PARAM_CLASS_SEKTCH_;
        sketch.summary = R.string.ei_sketch_class_summary;
        sketch.description = R.string.ei_sketch_class_description;

        // 梦幻
        EffectInfo dream = new EffectInfo();
        dream.iconId = R.drawable.cm_eft_ic_dream;
        // dream.icon = res.getDrawable(dream.iconId);
        dream.name = res.getString(R.string.ei_dream_class_name);
        dream.id = EffectId.DREAM;
        dream.param = PARAM_DREAM;
        dream.summary = R.string.ei_dream_class_summary;
        dream.description = R.string.ei_dream_class_description;

        // 黑白
        EffectInfo bw = new EffectInfo();
        bw.iconId = R.drawable.cm_eft_ic_bw;
        // bw.icon = res.getDrawable(bw.iconId);
        bw.name = res.getString(R.string.ei_bw_class_name);
        bw.id = EffectId.CLASS_BW;
        bw.param = PARAM_CLASS_BW;
        bw.summary = R.string.ei_bw_class_summary;
        bw.description = R.string.ei_bw_class_description;

        allEffect.add(lomo);
        allEffect.add(lightColor);
        allEffect.add(retro);
        allEffect.add(sketch);
        allEffect.add(dream);
        allEffect.add(bw);

        // 转换为 Array
        ArrayList<EffectInfo> effectArray = new ArrayList<EffectInfo>(allEffect.size());
        for (EffectInfo ei : allEffect) {
            ei.renderName = null;
            ei.renderParam = null;
            effectArray.add(ei);
        }

        return effectArray;
    }

    /**
     * @Description 过滤特效中其他模式的特效.
     * @author zengchuanmeng
     * @param
     * @return List<EffectInfo>
     */
    public static List<EffectInfo> FilterEffect(List<EffectInfo> effectInfos) {
        for (Iterator<EffectInfo> iterator = effectInfos.iterator(); iterator.hasNext();) {
            EffectInfo effectInfo = (EffectInfo) iterator.next();
            if (EffectId.CLASS_FUNNY == effectInfo.id || EffectId.TILF_SHIFT == effectInfo.id) {
                iterator.remove();
                
//                || EffectId.NONE == effectInfo.id
            }
        }
        return effectInfos;
    }

    /**
     * @Description 过滤特效中某个特效.
     * @author zengchuanmeng
     * @param
     * @return List<EffectInfo>
     */
    public static List<EffectInfo> FilterEffectRender(List<EffectInfo> effectInfos) {
        for (Iterator<EffectInfo> iterator = effectInfos.iterator(); iterator.hasNext();) {
            EffectInfo effectInfo = (EffectInfo) iterator.next();
            if (EffectId.RADOM == effectInfo.id || EffectId.CLASS_FUNNY == effectInfo.id
                    || EffectId.TILF_SHIFT == effectInfo.id) {
                iterator.remove();
            }
        }
        return effectInfos;
    }

    public static ArrayList<EffectInfo> obtainEffectClass(Context context, String eftParam) {
        final EffectId eftId;
        if (PARAM_CLASS_BW.equals(eftParam)) {
            eftId = EffectId.CLASS_BW;
        } else if (PARAM_CLASS_COLORFUL.equals(eftParam)) {
            eftId = EffectId.CLASS_COLORFUL;
        } else if (PARAM_CLASS_ENHANCE.equals(eftParam)) {
            eftId = EffectId.CLASS_ENHANCE;
        } else if (PARAM_CLASS_FUNNY.equals(eftParam)) {
            eftId = EffectId.CLASS_FUNNY;
        } else if (PARAM_CLASS_HRD_PRO.equals(eftParam)) {
            eftId = EffectId.CLASS_HDR;
        } else if (PARAM_CLASS_LOMO.equals(eftParam)) {
            eftId = EffectId.CLASS_LOMO;
        } else if (PARAM_CLASS_MAGIC_COLOR.equals(eftParam)) {
            eftId = EffectId.CLASS_MAGIC_COLOR;
        } else if (PARAM_CLASS_RETRO.equals(eftParam)) {
            eftId = EffectId.CLASS_RETRO;
        } else if (PARAM_CLASS_SEKTCH_.equals(eftParam)) {
            eftId = EffectId.CLASS_SKETCH;
        } else if (PARAM_CLASS_SKIN.equals(eftParam)) {
            eftId = EffectId.CLASS_SKIN;
        } else if (PARAM_CLASS_LIGHT_COLOR.equals(eftParam)) {
            eftId = EffectId.CLASS_LIGHT_COLOR;
        } else {
            // throw new
            // NullPointerException(" Cannot find effect class , param = " +
            // eftParam);
            return null;
        }

        return obtainEffectClass(context, eftId);
    }

    public static ArrayList<EffectInfo> obtainEffectClass(Context context, EffectId eftId) {
        ArrayList<EffectInfo> eftClass = new ArrayList<EffectInfo>();
        switch (eftId) {
        // 黑白分类
            case CLASS_BW:
                obtainBw(context, eftClass);
                break;
            // 流光溢彩
            case CLASS_COLORFUL:
                obtainColorFul(context, eftClass);
                break;
            // 效果增强
            case CLASS_ENHANCE:
                obtainEnhance(context, eftClass);
                break;
            // 趣味
            case CLASS_FUNNY:
                obtainFunny(context, eftClass);
                break;
            // HDR
            case CLASS_HDR:
                obtainHDR(context, eftClass);
                break;
            // 日系
            case CLASS_LIGHT_COLOR:
                obtainLightColor(context, eftClass);
                break;
            // LOMO
            case CLASS_LOMO:
                obtainLOMO(context, eftClass);
                break;
            // 魔法色彩
            case CLASS_MAGIC_COLOR:
                obtainMagicColor(context, eftClass);
                break;
            // 复古
            case CLASS_RETRO:
                obtainRetro(context, eftClass);
                break;
            // 手绘
            case CLASS_SKETCH:
                obtainSketch(context, eftClass);
                break;
            // 美肤
            case CLASS_SKIN:
                obtainSkin(context, eftClass);
                break;

            default:
                eftClass = null;
                break;
        }

        return eftClass;
    }

    static final String PARAM_BW_BIAO_ZHUN = "effect=bw_class,0";
    static final String PARAM_BW_YA_HEI = "effect=bw_class,1";
    static final String PARAM_BW_QIANG_LIE = "effect=bw_class,2";
    static final String PARAM_BW_FENG_BAO = "effect=bw_class,3";
    static final String PARAM_BW_DAN_CAI = "effect=bw_class,4";

    private static void obtainBw(Context context, ArrayList<EffectInfo> bwClass) {
        final Resources res = context.getResources();

        final EffectInfo biaoZhun = new EffectInfo();
        biaoZhun.name = res.getString(R.string.bw_biao_zhun);
        biaoZhun.param = PARAM_BW_BIAO_ZHUN;
        biaoZhun.id = EffectId.bw_biao_zhun;
        biaoZhun.iconId = R.drawable.render_bw_biao_zhun;

        final EffectInfo yaHei = new EffectInfo();
        yaHei.name = res.getString(R.string.bw_ya_hei);
        yaHei.param = PARAM_BW_YA_HEI;
        yaHei.id = EffectId.bw_ya_hei;
        yaHei.iconId = R.drawable.render_bw_ya_hei;

        final EffectInfo qiangLie = new EffectInfo();
        qiangLie.name = res.getString(R.string.bw_qiang_lie);
        qiangLie.param = PARAM_BW_QIANG_LIE;
        qiangLie.id = EffectId.bw_qiang_lie;
        qiangLie.iconId = R.drawable.render_bw_qiang_lie;

        final EffectInfo fengBao = new EffectInfo();
        fengBao.name = res.getString(R.string.bw_feng_bao);
        fengBao.param = PARAM_BW_FENG_BAO;
        fengBao.id = EffectId.bw_feng_bao;
        fengBao.iconId = R.drawable.render_bw_feng_bao;

        final EffectInfo danCai = new EffectInfo();
        danCai.name = res.getString(R.string.bw_dan_cai);
        danCai.param = PARAM_BW_DAN_CAI;
        danCai.id = EffectId.bw_dan_cai;
        danCai.iconId = R.drawable.render_bw_dan_cai;

        bwClass.add(biaoZhun);
        bwClass.add(yaHei);
        bwClass.add(qiangLie);
        bwClass.add(fengBao);
        bwClass.add(danCai);
    }

    static final String PARAM_SKIN_ZI_RAN_MEI_FU = "effect=skin_class,0";
    static final String PARAM_SKIN_GUANG_HUA_PI_FU = "effect=skin_class,1";
    static final String PARAM_SKIN_ZI_RAN_MEI_BAI = "effect=skin_class,2";
    static final String PARAM_SKIN_SHEN_DU_MEI_BAI = "effect=skin_class,3";
    static final String PARAM_SKIN_YI_SHU_HEI_BAI = "effect=skin_class,4";
    static final String PARAM_SKIN_NUAN_NUAN_YANG_GUANG = "effect=skin_class,5";
    static final String PARAM_SKIN_QING_XIN_LI_REN = "effect=skin_class,6";
    static final String PARAM_SKIN_XIANG_YAN_HONG_CHUN = "effect=skin_class,7";
    static final String PARAM_SKIN_TIAN_MEI_KE_REN = "effect=skin_class,8";

    private static void obtainSkin(Context context, ArrayList<EffectInfo> skinClass) {
        final Resources res = context.getResources();

        final EffectInfo ziRanMeiFu = new EffectInfo();
        ziRanMeiFu.name = res.getString(R.string.skin_zi_ran_mei_fu);
        ziRanMeiFu.param = PARAM_SKIN_ZI_RAN_MEI_FU;
        ziRanMeiFu.id = EffectId.skin_zi_ran_mei_fu;
        ziRanMeiFu.iconId = R.drawable.render_skin_zi_ran_mei_fu;

        final EffectInfo guangHuaPiFu = new EffectInfo();
        guangHuaPiFu.name = res.getString(R.string.skin_guang_hua_pi_fu);
        guangHuaPiFu.param = PARAM_SKIN_GUANG_HUA_PI_FU;
        guangHuaPiFu.id = EffectId.skin_guang_hua_pi_fu;
        guangHuaPiFu.iconId = R.drawable.render_skin_guang_hua_pi_fu;

        final EffectInfo ziRanMeiBai = new EffectInfo();
        ziRanMeiBai.name = res.getString(R.string.skin_zi_ran_mei_bai);
        ziRanMeiBai.param = PARAM_SKIN_ZI_RAN_MEI_BAI;
        ziRanMeiBai.id = EffectId.skin_zi_ran_mei_bai;
        ziRanMeiBai.iconId = R.drawable.render_skin_zi_ran_mei_bai;

        final EffectInfo shenDuMeiBai = new EffectInfo();
        shenDuMeiBai.name = res.getString(R.string.skin_shen_du_mei_bai);
        shenDuMeiBai.param = PARAM_SKIN_SHEN_DU_MEI_BAI;
        shenDuMeiBai.id = EffectId.skin_shen_du_mei_bai;
        shenDuMeiBai.iconId = R.drawable.render_skin_shen_du_mei_bai;

        final EffectInfo yiShuHeiBai = new EffectInfo();
        yiShuHeiBai.name = res.getString(R.string.skin_yi_shu_hei_bai);
        yiShuHeiBai.param = PARAM_SKIN_YI_SHU_HEI_BAI;
        yiShuHeiBai.id = EffectId.skin_yi_shu_hei_bai;
        yiShuHeiBai.iconId = R.drawable.render_skin_yi_shu_hei_bai;

        final EffectInfo nuanNuanYangGuang = new EffectInfo();
        nuanNuanYangGuang.name = res.getString(R.string.skin_nuan_nuan_yang_guang);
        nuanNuanYangGuang.param = PARAM_SKIN_NUAN_NUAN_YANG_GUANG;
        nuanNuanYangGuang.id = EffectId.skin_nuan_nuan_yang_guang;
        nuanNuanYangGuang.iconId = R.drawable.render_skin_nuan_nuan_yang_guang;

        final EffectInfo qingXinLiRen = new EffectInfo();
        qingXinLiRen.name = res.getString(R.string.skin_qing_xin_li_ren);
        qingXinLiRen.param = PARAM_SKIN_QING_XIN_LI_REN;
        qingXinLiRen.id = EffectId.skin_qing_xin_li_ren;
        qingXinLiRen.iconId = R.drawable.render_skin_qing_xin_li_ren;

        final EffectInfo xiangYanHongChun = new EffectInfo();
        xiangYanHongChun.name = res.getString(R.string.skin_xiang_yan_hong_chun);
        xiangYanHongChun.param = PARAM_SKIN_XIANG_YAN_HONG_CHUN;
        xiangYanHongChun.id = EffectId.skin_xiang_yan_hong_chun;
        xiangYanHongChun.iconId = R.drawable.render_skin_xiang_yan_hong_chun;

        final EffectInfo tianMeiKeRen = new EffectInfo();
        tianMeiKeRen.name = res.getString(R.string.skin_tian_mei_ke_ren);
        tianMeiKeRen.param = PARAM_SKIN_TIAN_MEI_KE_REN;
        tianMeiKeRen.id = EffectId.skin_tian_mei_ke_ren;
        tianMeiKeRen.iconId = R.drawable.render_skin_tian_mei_ke_ren;

        skinClass.add(ziRanMeiFu);
        skinClass.add(guangHuaPiFu);
        skinClass.add(ziRanMeiBai);
        skinClass.add(shenDuMeiBai);
        skinClass.add(yiShuHeiBai);
        skinClass.add(nuanNuanYangGuang);
        skinClass.add(qingXinLiRen);
        skinClass.add(xiangYanHongChun);
        skinClass.add(tianMeiKeRen);
    }

    static final String PARAM_SKETCH_HEI_BAI_XIAN_TIAO = "effect=sketch_class,8";
    static final String PARAM_SKETCH_CHAO_XIAN_SHI = "effect=sketch_class,2";
    static final String PARAM_SKETCH_NA_XIE_NIAN = "effect=sketch_class,15,0";
    static final String PARAM_SKETCH_CAI_SE = "effect=sketch_class,4";
    static final String PARAM_SKETCH_YOU_CAIA_HUA = "effect=sketch_class,11,0";
    static final String PARAM_SKETCH_MI_HONG_LA_BI = "effect=sketch_class,16,0";
    static final String PARAM_SKETCH_TAN_BI_HUA = "effect=sketch_class,9";
    static final String PARAM_SKETCH_LIANG_CAI = "effect=sketch_class,14,0";
    static final String PARAM_SKETCH_DAN_CAI = "effect=sketch_class,10";

    /**
     * @param context
     * @param sketchClass
     */
    private static void obtainSketch(Context context, ArrayList<EffectInfo> sketchClass) {
        final Resources res = context.getResources();

        final EffectInfo heiBaiXianTiao = new EffectInfo();
        heiBaiXianTiao.name = res.getString(R.string.sketch_hei_bai_xian_tiao);
        heiBaiXianTiao.param = PARAM_SKETCH_HEI_BAI_XIAN_TIAO;
        heiBaiXianTiao.id = EffectId.sketch_hei_bai_xian_tiao;
        heiBaiXianTiao.iconId = R.drawable.render_sketch_hei_bai_xian_tiao;

        final EffectInfo chaoXianShi = new EffectInfo();
        chaoXianShi.name = res.getString(R.string.sketch_chao_xian_shi);
        chaoXianShi.param = PARAM_SKETCH_CHAO_XIAN_SHI;
        chaoXianShi.id = EffectId.sketch_chao_xian_shi;
        chaoXianShi.iconId = R.drawable.render_sketch_chao_xian_shi;

        final EffectInfo naXieNian = new EffectInfo();
        naXieNian.name = res.getString(R.string.sketch_na_xie_nian);
        naXieNian.param = PARAM_SKETCH_NA_XIE_NIAN;
        naXieNian.id = EffectId.sketch_na_xie_nian;
        naXieNian.iconId = R.drawable.render_sketch_na_xie_nian;

        final EffectInfo caiSe = new EffectInfo();
        caiSe.name = res.getString(R.string.sketch_cai_se);
        caiSe.param = PARAM_SKETCH_CAI_SE;
        caiSe.id = EffectId.sketch_cai_se;
        caiSe.iconId = R.drawable.render_sketch_cai_se;

        final EffectInfo youCaiHua = new EffectInfo();
        youCaiHua.name = res.getString(R.string.sketch_you_cai_hua);
        youCaiHua.param = PARAM_SKETCH_YOU_CAIA_HUA;
        youCaiHua.id = EffectId.sketch_you_cai_hua;
        youCaiHua.iconId = R.drawable.render_sketch_you_cai_hua;

        final EffectInfo miHongLaBi = new EffectInfo();
        miHongLaBi.name = res.getString(R.string.sketch_mi_hong_la_bi);
        miHongLaBi.param = PARAM_SKETCH_MI_HONG_LA_BI;
        miHongLaBi.id = EffectId.sketch_mi_hong_la_bi;
        miHongLaBi.iconId = R.drawable.render_sketch_mi_hong_la_bi;

        final EffectInfo tanBiHua = new EffectInfo();
        tanBiHua.name = res.getString(R.string.sketch_tan_bi_hua);
        tanBiHua.param = PARAM_SKETCH_TAN_BI_HUA;
        tanBiHua.id = EffectId.sketch_tan_bi_hua;
        tanBiHua.iconId = R.drawable.render_sketch_tan_bi_hua;

        final EffectInfo xiangYanHongChun = new EffectInfo();
        xiangYanHongChun.name = res.getString(R.string.sketch_liang_cai);
        xiangYanHongChun.param = PARAM_SKETCH_LIANG_CAI;
        xiangYanHongChun.id = EffectId.sketch_liang_cai;
        xiangYanHongChun.iconId = R.drawable.render_sketch_liang_cai;

        final EffectInfo tianMeiKeRen = new EffectInfo();
        tianMeiKeRen.name = res.getString(R.string.sketch_dan_cai);
        tianMeiKeRen.param = PARAM_SKETCH_DAN_CAI;
        tianMeiKeRen.id = EffectId.sketch_dan_cai;
        tianMeiKeRen.iconId = R.drawable.render_sketch_dan_cai;

        sketchClass.add(heiBaiXianTiao);
        sketchClass.add(chaoXianShi);
        sketchClass.add(naXieNian);
        sketchClass.add(caiSe);
        sketchClass.add(youCaiHua);
        sketchClass.add(miHongLaBi);
        sketchClass.add(tanBiHua);
        sketchClass.add(xiangYanHongChun);
        sketchClass.add(tianMeiKeRen);
    }

    static final String PARAM_RETRO_ZI_SE_MI_QING = "effect=retro_class,0";
    static final String PARAM_RETRO_FU_GU_NUAN_HUANG = "effect=retro_class,1";
    static final String PARAM_RETRO_JIN_SE_NIAN_HUA = "effect=retro_class,2";
    static final String PARAM_RETRO_CHENG_SE_HUI_YI = "effect=retro_class,4";
    static final String PARAM_RETRO_YE_SE_MENG_LONG = "effect=retro_class,5";
    static final String PARAM_RETRO_MU_RAN_HUI_SHOU = "effect=retro_class,6";
    static final String PARAM_RETRO_FAN_HUANG_JI_YI = "effect=retro_class,8";
    static final String PARAM_RETRO_ZU_MU_LV = "effect=retro_class,9";
    static final String PARAM_RETRO_MI_MAN_SEN_LIN = "effect=retro_class,10";

    private static void obtainRetro(Context context, ArrayList<EffectInfo> retroClass) {
        final Resources res = context.getResources();

        final EffectInfo ziSeMiQing = new EffectInfo();
        ziSeMiQing.name = res.getString(R.string.retro_zi_se_mi_qing);
        ziSeMiQing.param = PARAM_RETRO_ZI_SE_MI_QING;
        ziSeMiQing.id = EffectId.retro_zi_se_mi_qing;
        ziSeMiQing.iconId = R.drawable.render_retro_zi_se_mi_qing;

        final EffectInfo fuGuNuanHuang = new EffectInfo();
        fuGuNuanHuang.name = res.getString(R.string.retro_fu_gu_nuan_huang);
        fuGuNuanHuang.param = PARAM_RETRO_FU_GU_NUAN_HUANG;
        fuGuNuanHuang.id = EffectId.retro_fu_gu_nuan_huang;
        fuGuNuanHuang.iconId = R.drawable.render_retro_fu_gu_nuan_huang;

        final EffectInfo jinSeNianHua = new EffectInfo();
        jinSeNianHua.name = res.getString(R.string.retro_jin_se_nian_hua);
        jinSeNianHua.param = PARAM_RETRO_JIN_SE_NIAN_HUA;
        jinSeNianHua.id = EffectId.retro_jin_se_nian_hua;
        jinSeNianHua.iconId = R.drawable.render_retro_jin_se_nian_hua;

        final EffectInfo chengSeHuiYi = new EffectInfo();
        chengSeHuiYi.name = res.getString(R.string.retro_cheng_se_hui_yi);
        chengSeHuiYi.param = PARAM_RETRO_CHENG_SE_HUI_YI;
        chengSeHuiYi.id = EffectId.retro_cheng_se_hui_yi;
        chengSeHuiYi.iconId = R.drawable.render_retro_cheng_se_hui_yi;

        final EffectInfo yeSeMengLong = new EffectInfo();
        yeSeMengLong.name = res.getString(R.string.retro_ye_se_meng_long);
        yeSeMengLong.param = PARAM_RETRO_YE_SE_MENG_LONG;
        yeSeMengLong.id = EffectId.retro_ye_se_meng_long;
        yeSeMengLong.iconId = R.drawable.render_retro_ye_se_meng_long;

        final EffectInfo muRanHuiShou = new EffectInfo();
        muRanHuiShou.name = res.getString(R.string.retro_mu_ran_hui_shou);
        muRanHuiShou.param = PARAM_RETRO_MU_RAN_HUI_SHOU;
        muRanHuiShou.id = EffectId.retro_mu_ran_hui_shou;
        muRanHuiShou.iconId = R.drawable.render_retro_mu_ran_hui_shou;

        final EffectInfo fanHuangJiYi = new EffectInfo();
        fanHuangJiYi.name = res.getString(R.string.retro_fan_huang_ji_yi);
        fanHuangJiYi.param = PARAM_RETRO_FAN_HUANG_JI_YI;
        fanHuangJiYi.id = EffectId.retro_fan_huang_ji_yi;
        fanHuangJiYi.iconId = R.drawable.render_retro_fan_huang_ji_yi;

        final EffectInfo zuMuLv = new EffectInfo();
        zuMuLv.name = res.getString(R.string.retro_zu_mu_lv);
        zuMuLv.param = PARAM_RETRO_ZU_MU_LV;
        zuMuLv.id = EffectId.retro_zu_mu_lv;
        zuMuLv.iconId = R.drawable.render_retro_zu_mu_lv;

        final EffectInfo miManSenLin = new EffectInfo();
        miManSenLin.name = res.getString(R.string.retro_mi_man_sen_lin);
        miManSenLin.param = PARAM_RETRO_MI_MAN_SEN_LIN;
        miManSenLin.id = EffectId.retro_mi_man_sen_lin;
        miManSenLin.iconId = R.drawable.render_retro_mi_man_sen_lin;

        retroClass.add(ziSeMiQing);
        retroClass.add(fuGuNuanHuang);
        retroClass.add(jinSeNianHua);
        retroClass.add(chengSeHuiYi);
        retroClass.add(yeSeMengLong);
        retroClass.add(muRanHuiShou);
        retroClass.add(fanHuangJiYi);
        retroClass.add(zuMuLv);
        retroClass.add(miManSenLin);
    }

    static final String PARAM_MAGIC_COLOURS_TAO_HUA_HONG = "effect=bw_class,100,235,54,157,40,50";
    static final String PARAM_MAGIC_COLOURS_ZHONG_GUO_HONG = "effect=bw_class,100,255,0,0,40,20";
    static final String PARAM_MAGIC_COLOURS_JI_ZI_CHENG = "effect=bw_class,100,245,80,0,30,50";
    static final String PARAM_MAGIC_COLOURS_SEN_ZHI_LV = "effect=bw_class,100,115,255,100,70,70";
    static final String PARAM_MAGIC_COLOURS_SHEN_HAI_LAN = "effect=bw_class,100,0,50,255,32,70";
    static final String PARAM_MAGIC_COLOURS_TIAN_KONG_LAN = "effect=bw_class,100,0,170,255,40,40";
    static final String PARAM_MAGIC_COLOURS_LIN_MENG_HUANG = "effect=bw_class,100,255,255,0,35,50";
    static final String PARAM_MAGIC_COLOURS_XUN_YI_ZI = "effect=bw_class,100,150,0,255,60,55";
    static final String PARAM_MAGIC_COLOURS_MO_FA_XIA_TIAN = "effect=bw_class,100,10,255,145,105,80";

    private static void obtainMagicColor(Context context, ArrayList<EffectInfo> coloursClass) {
        final Resources res = context.getResources();

        final EffectInfo taoHuaHong = new EffectInfo();
        taoHuaHong.name = res.getString(R.string.magic_colours_tao_hua_hong);
        taoHuaHong.param = PARAM_MAGIC_COLOURS_TAO_HUA_HONG;
        taoHuaHong.id = EffectId.magic_colours_tao_hua_hong;
        taoHuaHong.iconId = R.drawable.render_magic_colours_tao_hua_hong;

        final EffectInfo zhongGuoHong = new EffectInfo();
        zhongGuoHong.name = res.getString(R.string.magic_colours_zhong_guo_hong);
        zhongGuoHong.param = PARAM_MAGIC_COLOURS_ZHONG_GUO_HONG;
        zhongGuoHong.id = EffectId.magic_colours_zhong_guo_hong;
        zhongGuoHong.iconId = R.drawable.render_magic_colours_zhong_guo_hong;

        final EffectInfo jiZiCheng = new EffectInfo();
        jiZiCheng.name = res.getString(R.string.magic_colours_ji_zi_cheng);
        jiZiCheng.param = PARAM_MAGIC_COLOURS_JI_ZI_CHENG;
        jiZiCheng.id = EffectId.magic_colours_ji_zi_cheng;
        jiZiCheng.iconId = R.drawable.render_magic_colours_ji_zi_cheng;

        final EffectInfo senZhiLv = new EffectInfo();
        senZhiLv.name = res.getString(R.string.magic_colours_sen_zhi_lv);
        senZhiLv.param = PARAM_MAGIC_COLOURS_SEN_ZHI_LV;
        senZhiLv.id = EffectId.magic_colours_sen_zhi_lv;
        senZhiLv.iconId = R.drawable.render_magic_colours_sen_zhi_lv;

        final EffectInfo shenHaiLan = new EffectInfo();
        shenHaiLan.name = res.getString(R.string.magic_colours_shen_hai_lan);
        shenHaiLan.param = PARAM_MAGIC_COLOURS_SHEN_HAI_LAN;
        shenHaiLan.id = EffectId.magic_colours_shen_hai_lan;
        shenHaiLan.iconId = R.drawable.render_magic_colours_shen_hai_lan;

        final EffectInfo tianKongLan = new EffectInfo();
        tianKongLan.name = res.getString(R.string.magic_colours_tian_kong_lan);
        tianKongLan.param = PARAM_MAGIC_COLOURS_TIAN_KONG_LAN;
        tianKongLan.id = EffectId.magic_colours_tian_kong_lan;
        tianKongLan.iconId = R.drawable.render_magic_colours_tian_kong_lan;

        final EffectInfo linMengHuang = new EffectInfo();
        linMengHuang.name = res.getString(R.string.magic_colours_lin_meng_huang);
        linMengHuang.param = PARAM_MAGIC_COLOURS_LIN_MENG_HUANG;
        linMengHuang.id = EffectId.magic_colours_lin_meng_huang;
        linMengHuang.iconId = R.drawable.render_magic_colours_lin_meng_huang;

        final EffectInfo xunYiZi = new EffectInfo();
        xunYiZi.name = res.getString(R.string.magic_colours_xun_yi_zi);
        xunYiZi.param = PARAM_MAGIC_COLOURS_XUN_YI_ZI;
        xunYiZi.id = EffectId.magic_colours_xun_yi_zi;
        xunYiZi.iconId = R.drawable.render_magic_colours_xun_yi_zi;

        final EffectInfo moFaXiaTian = new EffectInfo();
        moFaXiaTian.name = res.getString(R.string.magic_colours_mo_fa_xia_tian);
        moFaXiaTian.param = PARAM_MAGIC_COLOURS_MO_FA_XIA_TIAN;
        moFaXiaTian.id = EffectId.magic_colours_mo_fa_xia_tian;
        moFaXiaTian.iconId = R.drawable.render_magic_colours_mo_fa_xia_tian;

        coloursClass.add(taoHuaHong);
        coloursClass.add(zhongGuoHong);
        coloursClass.add(jiZiCheng);
        coloursClass.add(senZhiLv);
        coloursClass.add(shenHaiLan);
        coloursClass.add(tianKongLan);
        coloursClass.add(linMengHuang);
        coloursClass.add(xunYiZi);
        coloursClass.add(moFaXiaTian);
    }

    static final String PARAM_LOMO_QING_SE = "effect=lomo_class,0";
    static final String PARAM_LOMO_DAN_QING = "effect=lomo_class,2";
    static final String PARAM_LOMO_DIAN_YING = "effect=lomo_class,1";
    static final String PARAM_LOMO_SHI_SHANG = "effect=lomo_class,9";
    static final String PARAM_LOMO_QIAN_HUI_YI = "effect=lomo_class,5";
    static final String PARAM_LOMO_LENG_YAN = "effect=lomo_class,10";
    static final String PARAM_LOMO_NUAN_QIU = "effect=lomo_class,7";
    static final String PARAM_LOMO_RE_QING = "effect=lomo_class,8";
    static final String PARAM_LOMO_FENG_YE = "effect=lomo_class,4";

    private static void obtainLOMO(Context context, ArrayList<EffectInfo> lomoClass) {
        final Resources res = context.getResources();

        final EffectInfo qingSe = new EffectInfo();
        qingSe.name = res.getString(R.string.lomo_qing_se);
        qingSe.param = PARAM_LOMO_QING_SE;
        qingSe.id = EffectId.lomo_qing_se;
        qingSe.iconId = R.drawable.render_lomo_qing_se;

        final EffectInfo danQing = new EffectInfo();
        danQing.name = res.getString(R.string.lomo_dan_qing);
        danQing.param = PARAM_LOMO_DAN_QING;
        danQing.id = EffectId.lomo_dan_qing;
        danQing.iconId = R.drawable.render_lomo_dan_qing;

        final EffectInfo dianYing = new EffectInfo();
        dianYing.name = res.getString(R.string.lomo_dian_ying);
        dianYing.param = PARAM_LOMO_DIAN_YING;
        dianYing.id = EffectId.lomo_dian_ying;
        dianYing.iconId = R.drawable.render_lomo_dian_ying;

        final EffectInfo shiShang = new EffectInfo();
        shiShang.name = res.getString(R.string.lomo_shi_shang);
        shiShang.param = PARAM_LOMO_SHI_SHANG;
        shiShang.id = EffectId.lomo_shi_shang;
        shiShang.iconId = R.drawable.render_lomo_shi_shang;

        final EffectInfo qianHuiYi = new EffectInfo();
        qianHuiYi.name = res.getString(R.string.lomo_qian_hui_yi);
        qianHuiYi.param = PARAM_LOMO_QIAN_HUI_YI;
        qianHuiYi.id = EffectId.lomo_qian_hui_yi;
        qianHuiYi.iconId = R.drawable.render_lomo_qian_hui_yi;

        final EffectInfo lengYan = new EffectInfo();
        lengYan.name = res.getString(R.string.lomo_leng_yan);
        lengYan.param = PARAM_LOMO_LENG_YAN;
        lengYan.id = EffectId.lomo_leng_yan;
        lengYan.iconId = R.drawable.render_lomo_leng_yan;

        final EffectInfo nuanQiu = new EffectInfo();
        nuanQiu.name = res.getString(R.string.lomo_nuan_qiu);
        nuanQiu.param = PARAM_LOMO_NUAN_QIU;
        nuanQiu.id = EffectId.lomo_nuan_qiu;
        nuanQiu.iconId = R.drawable.render_lomo_nuan_qiu;

        final EffectInfo reQing = new EffectInfo();
        reQing.name = res.getString(R.string.lomo_re_qing);
        reQing.param = PARAM_LOMO_RE_QING;
        reQing.id = EffectId.lomo_re_qing;
        reQing.iconId = R.drawable.render_lomo_re_qing;

        final EffectInfo fengYe = new EffectInfo();
        fengYe.name = res.getString(R.string.lomo_feng_ye);
        fengYe.param = PARAM_LOMO_FENG_YE;
        fengYe.id = EffectId.lomo_feng_ye;
        fengYe.iconId = R.drawable.render_lomo_feng_ye;

        lomoClass.add(qingSe);
        lomoClass.add(danQing);
        lomoClass.add(dianYing);
        lomoClass.add(shiShang);
        lomoClass.add(qianHuiYi);
        lomoClass.add(lengYan);
        lomoClass.add(nuanQiu);
        lomoClass.add(reQing);
        lomoClass.add(fengYe);
    }

    static final String PARAM_LIGHT_COLOR_TIAN_MEI = "effect=lightcolor_class,0";
    static final String PARAM_LIGHT_COLOR_QING_LIANG = "effect=lightcolor_class,2";
    static final String PARAM_LIGHT_COLOR_YANG_GUANG_CAN_LAN = "effect=lightcolor_class,9,1";
    static final String PARAM_LIGHT_COLOR_WEI_MEI = "effect=lightcolor_class,7";
    static final String PARAM_LIGHT_COLOR_YI_MI_YANG_GUANG = "effect=lightcolor_class,9,0";
    static final String PARAM_LIGHT_COLOR_GUO_DONG = "effect=lightcolor_class,6";
    static final String PARAM_LIGHT_COLOR_DAN_YA = "effect=lightcolor_class,13";
    static final String PARAM_LIGHT_COLOR_QING_XIN = "effect=lightcolor_class,14";
    static final String PARAM_LIGHT_COLOR_WEN_NUAN = "effect=lightcolor_class,15";

    private static void obtainLightColor(Context context, ArrayList<EffectInfo> lightClass) {
        final Resources res = context.getResources();

        final EffectInfo tianMei = new EffectInfo();
        tianMei.name = res.getString(R.string.light_color_tian_mei);
        tianMei.param = PARAM_LIGHT_COLOR_TIAN_MEI;
        tianMei.id = EffectId.light_color_tian_mei;
        tianMei.iconId = R.drawable.render_light_color_tian_mei;

        final EffectInfo qingLiang = new EffectInfo();
        qingLiang.name = res.getString(R.string.light_color_qing_liang);
        qingLiang.param = PARAM_LIGHT_COLOR_QING_LIANG;
        qingLiang.id = EffectId.light_color_qing_liang;
        qingLiang.iconId = R.drawable.render_light_color_qing_liang;

        final EffectInfo yangGuangCanLan = new EffectInfo();
        yangGuangCanLan.name = res.getString(R.string.light_color_yang_guang_can_lan);
        yangGuangCanLan.param = PARAM_LIGHT_COLOR_YANG_GUANG_CAN_LAN;
        yangGuangCanLan.id = EffectId.light_color_yang_guang_can_lan;
        yangGuangCanLan.iconId = R.drawable.render_light_color_yang_guang_can_lan;

        final EffectInfo weiMei = new EffectInfo();
        weiMei.name = res.getString(R.string.light_color_wei_mei);
        weiMei.param = PARAM_LIGHT_COLOR_WEI_MEI;
        weiMei.id = EffectId.light_color_wei_mei;
        weiMei.iconId = R.drawable.render_light_color_wei_mei;

        final EffectInfo yiMiYangGuang = new EffectInfo();
        yiMiYangGuang.name = res.getString(R.string.light_color_yi_mi_yang_guang);
        yiMiYangGuang.param = PARAM_LIGHT_COLOR_YI_MI_YANG_GUANG;
        yiMiYangGuang.id = EffectId.light_color_yi_mi_yang_guang;
        yiMiYangGuang.iconId = R.drawable.render_light_color_yi_mi_yang_guang;

        final EffectInfo guoDong = new EffectInfo();
        guoDong.name = res.getString(R.string.light_color_guo_dong);
        guoDong.param = PARAM_LIGHT_COLOR_GUO_DONG;
        guoDong.id = EffectId.light_color_guo_dong;
        guoDong.iconId = R.drawable.render_light_color_guo_dong;

        final EffectInfo danYa = new EffectInfo();
        danYa.name = res.getString(R.string.light_color_dan_ya);
        danYa.param = PARAM_LIGHT_COLOR_DAN_YA;
        danYa.id = EffectId.light_color_dan_ya;
        danYa.iconId = R.drawable.render_light_color_dan_ya;

        final EffectInfo qingXin = new EffectInfo();
        qingXin.name = res.getString(R.string.light_color_qing_xin);
        qingXin.param = PARAM_LIGHT_COLOR_QING_XIN;
        qingXin.id = EffectId.light_color_qing_xin;
        qingXin.iconId = R.drawable.render_light_color_qing_xin;

        final EffectInfo wenNuan = new EffectInfo();
        wenNuan.name = res.getString(R.string.light_color_wen_nuan);
        wenNuan.param = PARAM_LIGHT_COLOR_WEN_NUAN;
        wenNuan.id = EffectId.light_color_wen_nuan;
        wenNuan.iconId = R.drawable.render_light_color_wen_nuan;

        lightClass.add(tianMei);
        lightClass.add(qingLiang);
        lightClass.add(yangGuangCanLan);
        lightClass.add(weiMei);
        lightClass.add(yiMiYangGuang);
        lightClass.add(guoDong);
        lightClass.add(danYa);
        lightClass.add(qingXin);
        lightClass.add(wenNuan);
    }

    static final String PARAM_HDR_PRO_QING_ROU = "effect=hdrpro_class,0";
    static final String PARAM_HDR_PRO_XUAN_LI = "effect=hdrpro_class,2";
    static final String PARAM_HDR_PRO_JING_DIAN = "effect=hdrpro_class,4";
    static final String PARAM_HDR_PRO_GUANG_XUAN = "effect=hdrpro_class,100,2,6,18,20";
    static final String PARAM_HDR_PRO_FENG_BAO = "effect=hdrpro_class,100,4,5,40,40";

    private static void obtainHDR(Context context, ArrayList<EffectInfo> hdrClass) {
        final Resources res = context.getResources();

        final EffectInfo qingRou = new EffectInfo();
        qingRou.name = res.getString(R.string.hdr_pro_qing_rou);
        qingRou.param = PARAM_HDR_PRO_QING_ROU;
        qingRou.id = EffectId.hdr_pro_qing_rou;
        qingRou.iconId = R.drawable.render_hdr_pro_qing_rou;

        final EffectInfo xuanLi = new EffectInfo();
        xuanLi.name = res.getString(R.string.hdr_pro_xuan_li);
        xuanLi.param = PARAM_HDR_PRO_XUAN_LI;
        xuanLi.id = EffectId.hdr_pro_xuan_li;
        xuanLi.iconId = R.drawable.render_hdr_pro_xuan_li;

        final EffectInfo jingDian = new EffectInfo();
        jingDian.name = res.getString(R.string.hdr_pro_jing_dian);
        jingDian.param = PARAM_HDR_PRO_JING_DIAN;
        jingDian.id = EffectId.hdr_pro_jing_dian;
        jingDian.iconId = R.drawable.render_hdr_pro_jing_dian;

        final EffectInfo guangXuan = new EffectInfo();
        guangXuan.name = res.getString(R.string.hdr_pro_guang_xuan);
        guangXuan.param = PARAM_HDR_PRO_GUANG_XUAN;
        guangXuan.id = EffectId.hdr_pro_guang_xuan;
        guangXuan.iconId = R.drawable.render_hdr_pro_guang_xuan;

        final EffectInfo fengBao = new EffectInfo();
        fengBao.name = res.getString(R.string.hdr_pro_feng_bao);
        fengBao.param = PARAM_HDR_PRO_FENG_BAO;
        fengBao.id = EffectId.hdr_pro_feng_bao;
        fengBao.iconId = R.drawable.render_hdr_pro_feng_bao;

        hdrClass.add(qingRou);
        hdrClass.add(xuanLi);
        hdrClass.add(jingDian);
        hdrClass.add(guangXuan);
        hdrClass.add(fengBao);
    }

    static final String PARAM_FUNNY_SHANG_DUI_CHEN = "effect=funny_class,0";
    static final String PARAM_FUNNY_XIA_DUI_CHEN = "effect=funny_class,1";
    static final String PARAM_FUNNY_ZUO_DUI_CHEN = "effect=funny_class,2";
    static final String PARAM_FUNNY_YOU_DUI_CHEN = "effect=funny_class,3";
    static final String PARAM_FUNNY_YU_YAN = "effect=funny_class,4";
    static final String PARAM_FUNNY_XIANG_CHENG = "effect=funny_class,5";
    static final String PARAM_FUNNY_MEI_GUI = "effect=funny_class,6";
    static final String PARAM_FUNNY_HAI_LAN = "effect=funny_class,7";
    static final String PARAM_FUNNY_QING_PING_GUO = "effect=funny_class,8";

    private static void obtainFunny(Context context, ArrayList<EffectInfo> funnyClass) {
        final Resources res = context.getResources();

        final EffectInfo shangDuiChen = new EffectInfo();
        shangDuiChen.name = res.getString(R.string.funny_shang_dui_chen);
        shangDuiChen.param = PARAM_FUNNY_SHANG_DUI_CHEN;
        shangDuiChen.id = EffectId.funny_shang_dui_chen;
        shangDuiChen.iconId = R.drawable.render_funny_shang_dui_chen;

        final EffectInfo xiaDuiChen = new EffectInfo();
        xiaDuiChen.name = res.getString(R.string.funny_xia_dui_chen);
        xiaDuiChen.param = PARAM_FUNNY_XIA_DUI_CHEN;
        xiaDuiChen.id = EffectId.funny_xia_dui_chen;
        xiaDuiChen.iconId = R.drawable.render_funny_xia_dui_chen;

        final EffectInfo zuoDuiChen = new EffectInfo();
        zuoDuiChen.name = res.getString(R.string.funny_zuo_dui_chen);
        zuoDuiChen.param = PARAM_FUNNY_ZUO_DUI_CHEN;
        zuoDuiChen.id = EffectId.funny_zuo_dui_chen;
        zuoDuiChen.iconId = R.drawable.render_funny_zuo_dui_chen;

        final EffectInfo youDuiChen = new EffectInfo();
        youDuiChen.name = res.getString(R.string.funny_you_dui_chen);
        youDuiChen.param = PARAM_FUNNY_YOU_DUI_CHEN;
        youDuiChen.id = EffectId.funny_you_dui_chen;
        youDuiChen.iconId = R.drawable.render_funny_you_dui_chen;

        final EffectInfo yuYan = new EffectInfo();
        yuYan.name = res.getString(R.string.funny_yu_yan);
        yuYan.param = PARAM_FUNNY_YU_YAN;
        yuYan.id = EffectId.funny_yu_yan;
        yuYan.iconId = R.drawable.render_funny_yu_yan;

        final EffectInfo xiangCheng = new EffectInfo();
        xiangCheng.name = res.getString(R.string.funny_xiang_cheng);
        xiangCheng.param = PARAM_FUNNY_XIANG_CHENG;
        xiangCheng.id = EffectId.funny_xiang_cheng;
        xiangCheng.iconId = R.drawable.render_funny_xiang_cheng;

        final EffectInfo meiGui = new EffectInfo();
        meiGui.name = res.getString(R.string.funny_mei_gui);
        meiGui.param = PARAM_FUNNY_MEI_GUI;
        meiGui.id = EffectId.funny_mei_gui;
        meiGui.iconId = R.drawable.render_bw_funny_mei_gui;

        final EffectInfo haiLan = new EffectInfo();
        haiLan.name = res.getString(R.string.funny_hai_lan);
        haiLan.param = PARAM_FUNNY_HAI_LAN;
        haiLan.id = EffectId.funny_hai_lan;
        haiLan.iconId = R.drawable.render_bw_funny_hai_lan;

        final EffectInfo qingPingGuo = new EffectInfo();
        qingPingGuo.name = res.getString(R.string.funny_qing_ping_guo);
        qingPingGuo.param = PARAM_FUNNY_QING_PING_GUO;
        qingPingGuo.id = EffectId.funny_qing_ping_guo;
        qingPingGuo.iconId = R.drawable.render_funny_qing_ping_guo;

        funnyClass.add(shangDuiChen);
        funnyClass.add(xiaDuiChen);
        funnyClass.add(zuoDuiChen);
        funnyClass.add(youDuiChen);
        funnyClass.add(yuYan);
        funnyClass.add(xiangCheng);
        funnyClass.add(meiGui);
        funnyClass.add(haiLan);
        funnyClass.add(qingPingGuo);
    }

    static final String PARAM_ENHANCE_ZI_DONG_ZENG_QIANG = "effect=enhance_class,0";
    static final String PARAM_ENHANCE_YE_JIAN = "effect=enhance_class,6";
    static final String PARAM_ENHANCE_SHI_NEI = "effect=enhance_class,1";
    static final String PARAM_ENHANCE_WEN_NUAN = "effect=enhance_class,7";
    static final String PARAM_ENHANCE_KU = "effect=enhance_class,9";
    static final String PARAM_ENHANCE_FAN_ZHUAN_PIAN = "effect=enhance_class,4";
    static final String PARAM_ENHANCE_QIANG_LIE = "effect=enhance_class,3";
    static final String PARAM_ENHANCE_PING_HENG = "effect=enhance_class,5";
    static final String PARAM_ENHANCE_HAN = "effect=enhance_class,8";

    private static void obtainEnhance(Context context, ArrayList<EffectInfo> enhanceClass) {
        final Resources res = context.getResources();

        final EffectInfo ziDongZengQiang = new EffectInfo();
        ziDongZengQiang.name = res.getString(R.string.enhance_zi_dong_zeng_qiang);
        ziDongZengQiang.param = PARAM_ENHANCE_ZI_DONG_ZENG_QIANG;
        ziDongZengQiang.id = EffectId.enhance_zi_dong_zeng_qiang;
        ziDongZengQiang.iconId = R.drawable.render_enhance_zi_dong_zeng_qiang;

        final EffectInfo yeJian = new EffectInfo();
        yeJian.name = res.getString(R.string.enhance_ye_jian);
        yeJian.param = PARAM_ENHANCE_YE_JIAN;
        yeJian.id = EffectId.enhance_ye_jian;
        yeJian.iconId = R.drawable.render_enhance_ye_jian;

        final EffectInfo shiNei = new EffectInfo();
        shiNei.name = res.getString(R.string.enhance_shi_nei);
        shiNei.param = PARAM_ENHANCE_SHI_NEI;
        shiNei.id = EffectId.enhance_shi_nei;
        shiNei.iconId = R.drawable.render_enhance_shi_nei;

        final EffectInfo wenNuan = new EffectInfo();
        wenNuan.name = res.getString(R.string.enhance_wen_nuan);
        wenNuan.param = PARAM_ENHANCE_WEN_NUAN;
        wenNuan.id = EffectId.enhance_wen_nuan;
        wenNuan.iconId = R.drawable.render_enhance_wen_nuan;

        final EffectInfo ku = new EffectInfo();
        ku.name = res.getString(R.string.enhance_ku);
        ku.param = PARAM_ENHANCE_KU;
        ku.id = EffectId.enhance_ku;
        ku.iconId = R.drawable.render_enhance_ku;

        final EffectInfo fanZhuanPian = new EffectInfo();
        fanZhuanPian.name = res.getString(R.string.enhance_fan_zhuan_pian);
        fanZhuanPian.param = PARAM_ENHANCE_FAN_ZHUAN_PIAN;
        fanZhuanPian.id = EffectId.enhance_fan_zhuan_pian;
        fanZhuanPian.iconId = R.drawable.render_enhance_fan_zhuan_pian;

        final EffectInfo qiangLie = new EffectInfo();
        qiangLie.name = res.getString(R.string.enhance_qiang_lie);
        qiangLie.param = PARAM_ENHANCE_QIANG_LIE;
        qiangLie.id = EffectId.enhance_qiang_lie;
        qiangLie.iconId = R.drawable.render_enhance_qiang_lie;

        final EffectInfo pingHeng = new EffectInfo();
        pingHeng.name = res.getString(R.string.enhance_ping_heng);
        pingHeng.param = PARAM_ENHANCE_PING_HENG;
        pingHeng.id = EffectId.enhance_ping_heng;
        pingHeng.iconId = R.drawable.render_enhance_ping_heng;

        final EffectInfo han = new EffectInfo();
        han.name = res.getString(R.string.enhance_han);
        han.param = PARAM_ENHANCE_HAN;
        han.id = EffectId.enhance_han;
        han.iconId = R.drawable.render_enhance_han;

        enhanceClass.add(ziDongZengQiang);
        enhanceClass.add(yeJian);
        enhanceClass.add(shiNei);
        enhanceClass.add(wenNuan);
        enhanceClass.add(ku);
        enhanceClass.add(fanZhuanPian);
        enhanceClass.add(qiangLie);
        enhanceClass.add(pingHeng);
        enhanceClass.add(han);
    }

    static final String PARAM_COLORFUL_CAI_HONG = "effect=colorful_class,0,0";
    static final String PARAM_COLORFUL_SHUI_JING = "effect=colorful_class,0,1";
    static final String PARAM_COLORFUL_BI_KONG_RU_XI = "effect=colorful_class,0,2";
    static final String PARAM_COLORFUL_TIAN_GAO_YUN_DAN = "effect=colorful_class,0,3";
    static final String PARAM_COLORFUL_WEI_BO_DANG_YANG = "effect=colorful_class,0,4";
    static final String PARAM_COLORFUL_XUAN_LI_DUO_CAI = "effect=colorful_class,0,5";
    static final String PARAM_COLORFUL_LIU_YUN_LI_CAI = "effect=colorful_class,0,6";
    static final String PARAM_COLORFUL_CHA_ZI_YAN_HONG = "effect=colorful_class,0,7";
    static final String PARAM_COLORFUL_JIN_SE_QIU_TIAN = "effect=colorful_class,0,8";
    static final String PARAM_COLORFUL_ZI_SE_MI_QING = "effect=colorful_class,0,9";

    private static void obtainColorFul(Context context, ArrayList<EffectInfo> colorfulClass) {
        final Resources res = context.getResources();

        final EffectInfo caiHong = new EffectInfo();
        caiHong.name = res.getString(R.string.colorful_cai_hong);
        caiHong.param = PARAM_COLORFUL_CAI_HONG;
        caiHong.id = EffectId.colorful_cai_hong;
        caiHong.iconId = R.drawable.render_colorful_cai_hong;

        final EffectInfo shuiJing = new EffectInfo();
        shuiJing.name = res.getString(R.string.colorful_shui_jing);
        shuiJing.param = PARAM_COLORFUL_SHUI_JING;
        shuiJing.id = EffectId.colorful_shui_jing;
        shuiJing.iconId = R.drawable.render_colorful_shui_jing;

        final EffectInfo biKongRuXi = new EffectInfo();
        biKongRuXi.name = res.getString(R.string.colorful_bi_kong_ru_xi);
        biKongRuXi.param = PARAM_COLORFUL_BI_KONG_RU_XI;
        biKongRuXi.id = EffectId.colorful_bi_kong_ru_xi;
        biKongRuXi.iconId = R.drawable.render_colorful_bi_kong_ru_xi;

        final EffectInfo tianGaoYunDan = new EffectInfo();
        tianGaoYunDan.name = res.getString(R.string.colorful_tian_gao_yun_dan);
        tianGaoYunDan.param = PARAM_COLORFUL_TIAN_GAO_YUN_DAN;
        tianGaoYunDan.id = EffectId.colorful_tian_gao_yun_dan;
        tianGaoYunDan.iconId = R.drawable.render_colorful_tian_gao_yun_dan;

        final EffectInfo weiBoDangYang = new EffectInfo();
        weiBoDangYang.name = res.getString(R.string.colorful_wei_bo_dang_yang);
        weiBoDangYang.param = PARAM_COLORFUL_WEI_BO_DANG_YANG;
        weiBoDangYang.id = EffectId.colorful_wei_bo_dang_yang;
        weiBoDangYang.iconId = R.drawable.render_colorful_wei_bo_dang_yang;

        final EffectInfo xuanLiDuoCai = new EffectInfo();
        xuanLiDuoCai.name = res.getString(R.string.colorful_xuan_li_duo_cai);
        xuanLiDuoCai.param = PARAM_COLORFUL_XUAN_LI_DUO_CAI;
        xuanLiDuoCai.id = EffectId.colorful_xuan_li_duo_cai;
        xuanLiDuoCai.iconId = R.drawable.render_colorful_xuan_li_duo_cai;

        final EffectInfo liuYunLiCai = new EffectInfo();
        liuYunLiCai.name = res.getString(R.string.colorful_liu_yun_li_cai);
        liuYunLiCai.param = PARAM_COLORFUL_LIU_YUN_LI_CAI;
        liuYunLiCai.id = EffectId.colorful_liu_yun_li_cai;
        liuYunLiCai.iconId = R.drawable.render_colorful_liu_yun_li_cai;

        final EffectInfo chaZiYanHong = new EffectInfo();
        chaZiYanHong.name = res.getString(R.string.colorful_cha_zi_yan_hong);
        chaZiYanHong.param = PARAM_COLORFUL_CHA_ZI_YAN_HONG;
        chaZiYanHong.id = EffectId.colorful_cha_zi_yan_hong;
        chaZiYanHong.iconId = R.drawable.render_colorful_cha_zi_yan_hong;

        final EffectInfo jinSeQiuTian = new EffectInfo();
        jinSeQiuTian.name = res.getString(R.string.colorful_jin_se_qiu_tian);
        jinSeQiuTian.param = PARAM_COLORFUL_JIN_SE_QIU_TIAN;
        jinSeQiuTian.id = EffectId.colorful_jin_se_qiu_tian;
        jinSeQiuTian.iconId = R.drawable.render_colorful_jin_se_qiu_tian;

        final EffectInfo ziSeMiQing = new EffectInfo();
        ziSeMiQing.name = res.getString(R.string.colorful_zi_se_mi_qing);
        ziSeMiQing.param = PARAM_COLORFUL_ZI_SE_MI_QING;
        ziSeMiQing.id = EffectId.colorful_zi_se_mi_qing;
        ziSeMiQing.iconId = R.drawable.render_colorful_zi_se_mi_qing;

        colorfulClass.add(caiHong);
        colorfulClass.add(shuiJing);
        colorfulClass.add(biKongRuXi);
        colorfulClass.add(tianGaoYunDan);
        colorfulClass.add(weiBoDangYang);
        colorfulClass.add(xuanLiDuoCai);
        colorfulClass.add(liuYunLiCai);
        colorfulClass.add(chaZiYanHong);
        colorfulClass.add(jinSeQiuTian);
        colorfulClass.add(ziSeMiQing);
    }

    public static class EffectSearchRuslt {

        private EffectSearchRuslt() {
            this.success = true;
            this.index = -1;
            this.clsIndex = -1;
        }

        public boolean success;
        public EffectInfo eftInfo;
        public EffectInfo currClassEftInfo;
        public ArrayList<EffectInfo> eftClass;
        public int index;
        /**
         * 子分类的索引
         */
        public int clsIndex;

    }

    /**
     * 修复对智能清晰度的照片进行查询空指针的bug
     * @data 临时
     * @param eftParam
     * @param context
     * @return
     */
    public static EffectSearchRuslt search(String eftParam, Context context) {

        if (eftParam.contains(EffectParamFactory.SHARPNESS_BASE)) {
            int end = eftParam.indexOf(EffectParamFactory.SHARPNESS_BASE);
            eftParam = eftParam.substring(0, end - 1);
        }
        
        final EffectSearchRuslt esr = new EffectSearchRuslt();

        String parentParam = getParentParam(eftParam);
        if (parentParam == null) {
            // 不是分类
            ArrayList<EffectInfo> all = EffectInfoFactory.obtainMain(context);

            for (int i = 0; i < all.size(); i++) {
                final EffectInfo ei = all.get(i);
                if (ei.isClass())
                    continue;

                if (eftParam.equals(ei.param)) {
                    esr.eftInfo = ei;
                    esr.index = i;
                    return esr;
                }

                if (EffectInfoFactory.PARAM_BACK_TO_1839.equals(ei.param)) {
                    if (!EffectInfoFactory.PARAM_BACK_TO_1839.equals(eftParam)) {
                        continue;
                    }
                    esr.eftInfo = ei;
                    esr.index = i;
                    return esr;
                } else if (EffectInfoFactory.PARAM_DREAM.equals(ei.param)) {
                    if (!EffectInfoFactory.PARAM_DREAM.equals(eftParam)) {
                        continue;
                    }
                    esr.eftInfo = ei;
                    esr.index = i;
                    return esr;
                } else if (isGhost(ei.param)) {
                    if (!isGhost(eftParam)) {
                        continue;
                    }
                    esr.eftInfo = ei;
                    esr.index = i;
                    return esr;
                } else if (isAvata(ei.param)) {
                    if (!isAvata(eftParam)) {
                        continue;
                    }
                    esr.eftInfo = ei;
                    esr.index = i;
                    return esr;
                } else if (isTilfShift(ei.param)) {
                    if (!isTilfShift(eftParam)) {
                        continue;
                    }
                    esr.eftInfo = ei;
                    esr.index = i;
                    return esr;
                }

            }

        } else {
            // 是分类
            ArrayList<EffectInfo> all = EffectInfoFactory.obtainMain(context);

            int mainIndex = 0;
            for (EffectInfo parent : all) {
                if (parent.param.indexOf(parentParam) != -1) {// 查找大类
                    final ArrayList<EffectInfo> eftClass = obtainEffectClass(context, parent.param);
                    esr.eftClass = eftClass;
                    for (int clsIndex = 0; clsIndex < eftClass.size(); clsIndex++) {
                        final EffectInfo item = eftClass.get(clsIndex);
                        if (item.param.equals(eftParam)) {
                            // 找到子效果
                            esr.eftInfo = item;
                            esr.index = mainIndex;
                            esr.clsIndex = clsIndex;
                            esr.currClassEftInfo = item;
                            return esr;
                        }
                    }
                    // 如果查找失败，返回父效果
                    esr.eftInfo = parent;
                    esr.index = mainIndex;
                    esr.success = false;
                    break;
                }

                mainIndex++;
            }

        }
        return esr;
    }

    // "effect=enhance_class,3"
    /**
     * 大头特效：effect=avata,49,34,20,190,22,80;effect=enhance_class,0
     * 移轴特效：effect=tiltshift
     * ;direct=0;centerbl=50;sizebl=10;tsblurlv=1;tstype=0;tscolor=2;tsstep=30
     * 幽灵：effect=ghost;direct=3;posxbl=51;posybl=85;zoombl=29;alpha=25;pngfile=/
     * data/data/vStudio.Android.Camera360PlusPlus/test_ghost.png
     * 1839：effect=backto1839,/sdcard/Camera360/Data/test_old.png
     * 
     * @param param
     * @return
     */
    public static String getParentParam(String param) {
        if (param == null) {
            return null;
        }
        // param 是父类param
        if (EffectInfoFactory.isEffectClass(param)) {
            return param;
        }

        // 特殊效果的处理
        if (EffectInfoFactory.PARAM_BACK_TO_1839.equals(param)) {
            return null;
        } else if (EffectInfoFactory.PARAM_DREAM.equals(param)) {
            return null;
        } else if (isGhost(param)) {
            return null;
        } else if (isAvata(param)) {
            return null;
        } else if (isTilfShift(param)) {
            return null;
        } else if (isNone(param)) {
            return null;
        }
        try {
            String[] getHead = param.split(",");
            if (param.indexOf(BW_CLASS) >= 0 && getHead.length > 2) {
                // 黑白视觉 或者流光溢彩它们的 名字一样， 必须写死 ，我擦！
                return PARAM_CLASS_MAGIC_COLOR;
            } else {
                return getHead[0].split("=")[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getParentParamForPreview(String param) {

        // param 是父类param
        if (EffectInfoFactory.isEffectClass(param)) {
            return param;
        }

        // 特殊效果的处理
        if (EffectInfoFactory.PARAM_BACK_TO_1839.equals(param)) {
            return param;
        } else if (EffectInfoFactory.PARAM_DREAM.equals(param)) {
            return param;
        } else if (isGhost(param)) {
            return param;
        } else if (isAvata(param)) {
            return param;
        } else if (isTilfShift(param)) {
            return param;
        } else if (isNone(param)) {
            return param;
        }
        try {
            String[] getHead = param.split(",");
            if (param.indexOf(BW_CLASS) >= 0 && getHead.length > 2) {
                // 黑白视觉 或者流光溢彩它们的 名字一样， 必须写死 ，我擦！
                return PARAM_CLASS_MAGIC_COLOR;
            } else {
                return EFFECT_CLASS + getHead[0].split("=")[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
            return param;
        }
    }

    public static boolean isEffectClass(String param) {
        return param.startsWith(EFFECT_CLASS);
    }

    /**
     * 判断是否是大头
     * 
     * @param eftParam
     * @return
     * @ersion 1.0 修正 修复大头和效果增强判断混淆改未equals
     * @author lizhipeng
     */
    public static boolean isAvata(String eftParam) {
        return eftParam.startsWith(PARAM_AVATA);
    }

    public static boolean isTilfShift(String eftParam) {
        return eftParam.startsWith(PARAM_TILF_SHIFT);
    }

    public static boolean isGhost(String eftParam) {
        return eftParam.contains(PARAM_GHOST);
    }

    public static boolean is1839(String eftParam) {
        return eftParam.startsWith(PARAM_BACK_TO_1839);
    }
    
    public static boolean isRandom(String eftParam) {
        return eftParam.startsWith(PARAM_RADOM);
    }
    
    public static boolean isNone(String eftParam) {
        return eftParam.startsWith("effect=" + PARAM_NONE);
    }

    /**
     * 效果增强，魔法美肤，lomo，日系，HDR，复古，手绘，魔法色彩，黑白，流光溢彩
     * 
     * @param eftId
     * @param eftInfo
     * @return
     */
    public static boolean canUseInRandom(EffectId eftId) {
        if (eftId == null)
            return false;
        switch (eftId) {
            case CLASS_ENHANCE:
                return true;
            case CLASS_LOMO:
                return true;
            case CLASS_BW:
                return true;
            case CLASS_HDR:
                return true;
            case CLASS_COLORFUL:
                return true;
            case CLASS_SKIN:
                return true;
            case CLASS_LIGHT_COLOR:
                return true;
            case CLASS_MAGIC_COLOR:
                return true;
            case CLASS_SKETCH:
                return true;
            case CLASS_RETRO:
                return true;
            default:
                return false;
        }
    }

    /**
     * @param effectId
     * @return
     */
    public static boolean canNotUserInPano(EffectId effectId) {
        switch (effectId) {
            case BIG_HEAD:
            case CLASS_FUNNY:
            case TILF_SHIFT:
            case GOHST:

                return true;
            default:
                return false;
        }
    }

    public static ArrayList<EffectInfo> allEffectClass(Context context) {
        final EffectId[] eftIds = EffectId.getEftClassIds();
        ArrayList<EffectInfo> allEffect = new ArrayList<EffectInfo>();
        for (EffectId id : eftIds) {
            ArrayList<EffectInfo> ec = obtainEffectClass(context, id);
            allEffect.addAll(ec);
        }

        return allEffect;
    }

    public static final boolean isEffectInfoClass(String param) {
        // BUG 修改 所有拥有子特效的都以【effect_class=】开头，而非包含【effect_class=】
        return param.startsWith(EffectInfoFactory.EFFECT_CLASS);
    }

}
