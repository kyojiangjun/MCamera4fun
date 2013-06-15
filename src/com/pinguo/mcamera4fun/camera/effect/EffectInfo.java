package com.pinguo.mcamera4fun.camera.effect;

import android.graphics.Bitmap;

public class EffectInfo implements Cloneable {

    public static class SpecEffect {
        /**
         * 镜像参数，沿X轴坐标线翻转
         */
        public static final String MIRROR_X = "effect=mirror,1,0";
        /**
         * 镜像参数，沿Y轴坐标线翻转
         */
        public static final String MIRROR_Y = "effect=mirror,0,1";
        /**
         * 镜像参数，沿X, Y轴同时坐标线翻转
         */
        public static final String MIRROR_X_Y = "effect=mirror,1,1";
    }

    public String renderName;

    public String renderParam;

    /** 特效名称 */
    public String name;
    /** 特效参数 */
    public String param;

    public int description;

    public int summary;

    /** 特效的示例图 */
    public Bitmap iconBitmap;

    /** 图标id */
    public int iconId;

    /** 效果对应的id */
    public EffectId id;

    public boolean isClass() {
        //BUG 修改  所有拥有子特效的都以【effect_class=】开头，而非包含【effect_class=】
        return this.param.startsWith(EffectInfoFactory.EFFECT_CLASS);
    }

    /**
     * 判断当前效果是否是分类
     */
    public static boolean isEftClass(String effectParam) {
        //BUG 修改  所有拥有子特效的都以【effect_class=】开头，而非包含【effect_class=】
        return effectParam.startsWith(EffectInfoFactory.EFFECT_CLASS);
    }

    public void clean() {
        renderName = null;
        renderParam = null;
        name = null;
        param = null;
        description = 0;
        summary = 0;
        if (null != iconBitmap) {
            iconBitmap.recycle();
            iconBitmap = null;
        }
        iconId = 0;
        id = null;
    }
    
    public enum EffectId {
        /**
         * 3_5特效_无特效
         */
        NONE,
        /**
         * 3_5特效_随机
         */
        RADOM,
        /**
         * 3_5特效_梦幻
         */
        DREAM,
        /**
         * 3_5特效_梦回1839
         */
        BACK_TO_1839,
        /**
         * 3_5特效_幽灵
         */
        GOHST,

        // *********************分类 ****************//
            /**
             * 3_5特效_效果增强分类
             */
            CLASS_ENHANCE,
            /**
             * 3_5特效_美肤分类
             */
            CLASS_SKIN,
            /**
             * 3_5特效_LOMO分类
             */
            CLASS_LOMO,
            /**
             * 3_5特效_日系分类
             */
            CLASS_LIGHT_COLOR,
            /**
             * 3_5特效_HDR分类
             */
            CLASS_HDR,
            /**
             * 3_5特效_复古分类
             */
            CLASS_RETRO,
            /**
             * 3_5特效_手绘分类
             */
            CLASS_SKETCH,
            /**
             * 3_5特效_流光溢彩分类
             */
            CLASS_COLORFUL,
            /**
             * 3_5特效_趣味分类
             */
            CLASS_FUNNY,
            /**
             * 3_5特效_魔法色彩分类
             */
            CLASS_MAGIC_COLOR,
            /**
             * 3_5特效_黑白分类
             */
            CLASS_BW,
            /**
             * 3_5特效_移轴
             */
            TILF_SHIFT,
            /**
             * 3_5特效_大头
             */
            BIG_HEAD,
            // ------------------------黑白分类---------------------------
            /**
             * 3_5特效_黑白_标准
             */
            bw_biao_zhun,
            /**
             * 3_5特效_黑白_雅黑
             */
            bw_ya_hei,
            /**
             * 3_5特效_黑白_强烈
             */
            bw_qiang_lie,
            /**
             * 3_5特效_黑白_风暴
             */
            bw_feng_bao,
            /**
             * 3_5特效_黑白_淡彩
             */
            bw_dan_cai,
            /**
		 * 
		 */
            // -----------------------美肤分类---------------------------------------
            /**
             * 3_5特效_美肤_自然美肤
             */
            skin_zi_ran_mei_fu,
            /**
             * 3_5特效_美肤_光滑皮肤
             */
            skin_guang_hua_pi_fu,
            /**
             * 3_5特效_美肤_自然美肤
             */
            skin_zi_ran_mei_bai,
            /**
             * 3_5特效_美肤_深度美白
             */
            skin_shen_du_mei_bai,
            /**
             * 3_5特效_美肤_艺术黑白
             */
            skin_yi_shu_hei_bai,
            /**
             * 3_5特效_美肤_暖暖阳光
             */
            skin_nuan_nuan_yang_guang,
            /**
             * 3_5特效_美肤_清新丽人
             */
            skin_qing_xin_li_ren,
            /**
             * 3_5特效_美肤_香艳红唇
             */
            skin_xiang_yan_hong_chun,
            /**
             * 3_5特效_美肤_甜美可人
             */
            skin_tian_mei_ke_ren,
            // --------------------------彩绘分类-----------------------------------------------
            /**
             * 3_5特效_彩绘_黑白线条
             */
            sketch_hei_bai_xian_tiao,
            /**
             * 3_5特效_彩绘_超现实
             */
            sketch_chao_xian_shi,
            /**
             * 3_5特效_彩绘_那些年
             */
            sketch_na_xie_nian,
            /**
             * 3_5特效_彩绘_彩色
             */
            sketch_cai_se,
            /**
             * 3_5特效_彩绘_油彩画
             */
            sketch_you_cai_hua,
            /**
             * 3_5特效_彩绘_霓虹蜡笔
             */
            sketch_mi_hong_la_bi,
            /**
             * 3_5特效_彩绘_炭笔画
             */
            sketch_tan_bi_hua,
            /**
             * 3_5特效_彩绘_亮彩
             */
            sketch_liang_cai,
            /**
             * 3_5特效_彩绘_淡彩
             */
            sketch_dan_cai,
            // ---------------------------复古分类---------------------------------------------
            /**
             * 3_5特效_复古_紫色迷情
             */
            retro_zi_se_mi_qing,
            /**
             * 3_5特效_复古_复古暖黄
             */
            retro_fu_gu_nuan_huang,
            /**
             * 3_5特效_复古_金色年华
             */
            retro_jin_se_nian_hua,
            /**
             * 3_5特效_复古_橙色回忆
             */
            retro_cheng_se_hui_yi,
            /**
             * 3_5特效_复古_夜色朦胧
             */
            retro_ye_se_meng_long,
            /**
             * 3_5特效_复古_暮然回首
             */
            retro_mu_ran_hui_shou,
            /**
             * 3_5特效_复古_泛黄记忆
             */
            retro_fan_huang_ji_yi,
            /**
             * 3_5特效_复古_祖母绿
             */
            retro_zu_mu_lv,
            /**
             * 3_5特效_复古_弥漫森林
             */
            retro_mi_man_sen_lin,
            // --------------------------魔法色彩分类---------------------------------------------------
            /**
             * 3_5特效_魔法色彩_桃花红
             */
            magic_colours_tao_hua_hong,
            /**
             * 3_5特效_魔法色彩_中国红
             */
            magic_colours_zhong_guo_hong,
            /**
             * 3_5特效_魔法色彩_橘子橙
             */
            magic_colours_ji_zi_cheng,
            /**
             * 3_5特效_魔法色彩_深紫绿
             */
            magic_colours_sen_zhi_lv,
            /**
             * 3_5特效_魔法色彩_深海蓝
             */
            magic_colours_shen_hai_lan,
            /**
             * 3_5特效_魔法色彩_天空蓝
             */
            magic_colours_tian_kong_lan,
            /**
             * 3_5特效_魔法色彩_柠檬黄
             */
            magic_colours_lin_meng_huang,
            /**
             * 3_5特效_魔法色彩_熏衣紫
             */
            magic_colours_xun_yi_zi,
            /**
             * 3_5特效_魔法色彩_魔法夏天
             */
            magic_colours_mo_fa_xia_tian,
            // ------------------------------------Lomo分类---------------------------------------------------------
            /**
             * 3_5特效_Lomo_青色
             */
            lomo_qing_se,
            /**
             * 3_5特效_Lomo_淡青
             */
            lomo_dan_qing,
            /**
             * 3_5特效_Lomo_电影
             */
            lomo_dian_ying,
            /**
             * 3_5特效_Lomo_时尚
             */
            lomo_shi_shang,
            /**
             * 3_5特效_Lomo_浅回忆
             */
            lomo_qian_hui_yi,
            /**
             * 3_5特效_Lomo_冷艳
             */
            lomo_leng_yan,
            /**
             * 3_5特效_Lomo_暖秋
             */
            lomo_nuan_qiu,
            /**
             * 3_5特效_Lomo_热情
             */
            lomo_re_qing,
            /**
             * 3_5特效_Lomo_枫叶
             */
            lomo_feng_ye,
            // -----------------------------日系分类----------------------------------------
            /**
             * 3_5特效_日系_甜美
             */
            light_color_tian_mei,
            /**
             * 3_5特效_日系_清凉
             */
            light_color_qing_liang,
            /**
             * 3_5特效_日系_阳光灿烂
             */
            light_color_yang_guang_can_lan,
            /**
             * 3_5特效_日系_唯美
             */
            light_color_wei_mei,
            /**
             * 3_5特效_日系_一米阳光
             */
            light_color_yi_mi_yang_guang,
            /**
             * 3_5特效_日系_果冻
             */
            light_color_guo_dong,
            /**
             * 3_5特效_日系_淡雅
             */
            light_color_dan_ya,
            /**
             * 3_5特效_日系_清新
             */
            light_color_qing_xin,
            /**
             * 3_5特效_日系_温暖
             */
            light_color_wen_nuan,
            // ----------------------------HDR分类------------------------------------
            /**
             * 3_5特效_HDR_轻柔
             */
            hdr_pro_qing_rou,
            /**
             * 3_5特效_HDR_绚丽
             */
            hdr_pro_xuan_li,
            /**
             * 3_5特效_HDR_经典
             */
            hdr_pro_jing_dian,
            /**
             * 3_5特效_HDR_光绚
             */
            hdr_pro_guang_xuan,
            /**
             * 3_5特效_HDR_风暴
             */
            hdr_pro_feng_bao,
            // -----------------------------------趣味-------------------------------------
            /**
             * 3_5特效_趣味_上对称
             */
            funny_shang_dui_chen,
            /**
             * 3_5特效_趣味_下对称
             */
            funny_xia_dui_chen,
            /**
             * 3_5特效_趣味_左对称
             */
            funny_zuo_dui_chen,
            /**
             * 3_5特效_趣味_右对称
             */
            funny_you_dui_chen,
            /**
             * 3_5特效_趣味_鱼眼
             */
            funny_yu_yan,
            /**
             * 3_5特效_趣味_香橙
             */
            funny_xiang_cheng,
            /**
             * 3_5特效_趣味_玫瑰
             */
            funny_mei_gui,
            /**
             * 3_5特效_趣味_海蓝
             */
            funny_hai_lan,
            /**
             * 3_5特效_趣味_青苹果
             */
            funny_qing_ping_guo,
            // ----------------------------效果增强分类-------------------------------------
            /**
             * 3_5特效_效果增强_自动增强
             */
            enhance_zi_dong_zeng_qiang,
            /**
             * 3_5特效_效果增强_夜间
             */
            enhance_ye_jian,
            /**
             * 3_5特效_效果增强_室内
             */
            enhance_shi_nei,
            /**
             * 3_5特效_效果增强_温暖
             */
            enhance_wen_nuan,
            /**
             * 3_5特效_效果增强_酷
             */
            enhance_ku,
            /**
             * 3_5特效_效果增强_反转片
             */
            enhance_fan_zhuan_pian,
            /**
             * 3_5特效_效果增强_强烈
             */
            enhance_qiang_lie,
            /**
             * 3_5特效_效果增强_平衡
             */
            enhance_ping_heng,
            /**
             * 3_5特效_效果增强_寒
             */
            enhance_han,
            // -----------------------------流光溢彩分类----------------------------------------
            /**
             * 3_5特效_流光溢彩_彩虹
             */
            colorful_cai_hong,
            /**
             * 3_5特效_流光溢彩_水晶
             */
            colorful_shui_jing,
            /**
             * 3_5特效_流光溢彩_碧空如洗
             */
            colorful_bi_kong_ru_xi,
            /**
             * 3_5特效_流光溢彩_天高云淡
             */
            colorful_tian_gao_yun_dan,
            /**
             * 3_5特效_流光溢彩_微波荡漾
             */
            colorful_wei_bo_dang_yang,
            /**
             * 3_5特效_流光溢彩_绚丽多彩
             */
            colorful_xuan_li_duo_cai,
            /**
             * 3_5特效_流光溢彩_流光溢彩
             */
            colorful_liu_yun_li_cai,
            /**
             * 3_5特效_流光溢彩_姹紫嫣红
             */
            colorful_cha_zi_yan_hong,
            /**
             * 3_5特效_流光溢彩_金色秋天
             */
            colorful_jin_se_qiu_tian,
            /**
             * 3_5特效_流光溢彩_紫色迷情
             */
            colorful_zi_se_mi_qing;

        public static EffectId[] getEftClassIds() {
            return new EffectId[] { CLASS_ENHANCE,
            /**
             * 3_5特效_美肤分类
             */
            CLASS_SKIN,
            /**
             * 3_5特效_LOMO分类
             */
            CLASS_LOMO,
            /**
             * 3_5特效_日系分类
             */
            CLASS_LIGHT_COLOR,
            /**
             * 3_5特效_HDR分类
             */
            CLASS_HDR,
            /**
             * 3_5特效_复古分类
             */
            CLASS_RETRO,
            /**
             * 3_5特效_手绘分类
             */
            CLASS_SKETCH,
            /**
             * 3_5特效_流光溢彩分类
             */
            CLASS_COLORFUL,
            /**
             * 3_5特效_趣味分类
             */
            CLASS_FUNNY,
            /**
             * 3_5特效_魔法色彩分类
             */
            CLASS_MAGIC_COLOR,
            /**
             * 3_5特效_黑白分类
             */
            CLASS_BW };
        }
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
