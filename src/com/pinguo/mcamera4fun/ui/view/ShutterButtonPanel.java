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
 * FileName:ShutterButtonViewGroup.java
 * Author:
 * Date:2012-10-22 下午1:37:29 
 * 
 */

package com.pinguo.mcamera4fun.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.pinguo.mcamera4fun.R;
import com.pinguo.mcamera4fun.camera.scene.SceneTemplateModel;
import com.pinguo.mcamera4fun.settings.CommonSharedPreferences;
import com.pinguo.mcamera4fun.ui.Rotatable;
import com.pinguo.mcamera4fun.utils.CameraUtil;

/* autor:kangyu
 * 此类为底部滚动按钮的view类
 * 
 */
public class ShutterButtonPanel extends ViewGroup implements OnTouchListener, OnClickListener, Rotatable {

    public static final String TAG = ShutterButtonPanel.class.getSimpleName();

    public static final int THRESHOLD = 0;

    private RelativeLayout mCameraLayout; // 整个滑动按钮的layout

    private boolean isZoomButtonTouch = false; // 按钮是否被触碰的标记

    private static final int SCROLL_BACK_TIME = 100; // 回滚动画时间

    private int downX = 0;

    private int downY = 0;

    private boolean isMove = false; // 标记按钮是否移动

    private ShutterButtonPanelListener mOnShuttButtonListener; // button
    
    private RotateImageView mTopicImageView; // 情景image

    private RotateImageView mCameraImageView;

    private RotateImageView mSpecialImageView; // 特效image

    private RotateImageView mScrollCameraBtnBg;

    private int mPopViewType = 0; // 当前界面显示的类型

    private static final int NONE_POP_VIEW = 0; // 普通预览

    private static final int SPECIAL_POP_VIEW = 1; // 特效选择

    private static final int TOPIC_POP_VIEW = 2; // 情景选择

    private int mShutterMode = CameraUtil.CAMERA_MODE_EFFECT; // 当前拍摄模式

    public int mShutterButtonImageResId = 0; // button的image id，根据当前选择的拍摄模式变化

    public int mShutterButtonRightImageResId = 0; // button 右边的image
                                                  // id，根据当前选择的拍摄模式变化

    public int mShutterButtonLeftImageResId = 0; // button 左边的image
                                                 // id，根据当前选择的拍摄模式变化

    private View ivShutterButtonLine;

    private View mIconNew;

    private RelativeLayout mTopClickLayout;

    private RelativeLayout mBottomClickLayout;
    
    public ShutterButtonPanel(Context context) {
        super(context);
        initView(context);
    }

    public ShutterButtonPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initListener();
    }

    private void initListener() {
        mCameraLayout.setOnTouchListener(this);

    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View zoomView = inflater.inflate(R.layout.layout_shutter_button, null);
        mCameraLayout = (RelativeLayout) zoomView.findViewById(R.id.camera_icon_layout);

        mTopClickLayout = (RelativeLayout) zoomView.findViewById(R.id.top_click_layout);
        mTopClickLayout.setOnClickListener(this);

        mBottomClickLayout = (RelativeLayout) zoomView.findViewById(R.id.bottom_click_layout);
        mBottomClickLayout.setOnClickListener(this);

        mTopicImageView = (RotateImageView) zoomView.findViewById(R.id.iv_topic_icon);
        mCameraImageView = (RotateImageView) zoomView.findViewById(R.id.iv_scroll_button);
        mScrollCameraBtnBg = (RotateImageView) zoomView.findViewById(R.id.iv_scrollbar_background);

        ivShutterButtonLine = zoomView.findViewById(R.id.iv_scroll_bar);

        mSpecialImageView = (RotateImageView) zoomView.findViewById(R.id.iv_special_icon);
        addView(zoomView); // FIXME 内存泄漏
        mShutterButtonImageResId = R.drawable.ic_special_small;
        mShutterButtonRightImageResId = R.drawable.ic_topic;
        mShutterButtonLeftImageResId = R.drawable.ic_effect;
        mIconNew = zoomView.findViewById(R.id.iv_new_icon);
        updateCurrentModeShutterUI(context);
        showSceneNewIconSwitch();
    }

    public void showSceneNewIconSwitch() {
        mIconNew.post(new Runnable() {

            @Override
            public void run() {
                if (SceneTemplateModel.hasNew(getActivity())) {
                    showNewIcon();
                } else {
                    hideNewIcon();
                }
            }
        });

    }

    protected Activity getActivity() {
    	Context context = getContext();
    	if(context instanceof Activity)
    		return (Activity) context;
    	else
    		return null;
	}

	public void showNewIcon() {
        mIconNew.setVisibility(View.VISIBLE);
    }

    public void hideNewIcon() {
        mIconNew.setVisibility(View.INVISIBLE);
    }

    /**
     * @Description 设置当前模式的拍照按钮UI.
     * @author zengchuanmeng
     * @param context
     * @param
     * @return void
     */
    public void updateCurrentModeShutterUI(Context context) {
        int cameraMode = CommonSharedPreferences.getInstance(context).getCameraMode();
        setCurrentMode(cameraMode);
        switch (cameraMode) {
            case CameraUtil.CAMERA_MODE_NOMARL:// 普通
                setScrollButtonImage(R.drawable.ic_normal_small);
                break;
            case CameraUtil.CAMERA_MODE_EFFECT:// 特效
                setScrollButtonImage(R.drawable.ic_special_small);
                break;
            case CameraUtil.CAMERA_MODE_FUN:// 趣味
                setScrollButtonImage(R.drawable.ic_fun_small);
                break;
            case CameraUtil.CAMERA_MODE_SELF_TIMER:// 自拍
                setScrollButtonImage(R.drawable.ic_self_timer_small);
                break;
            case CameraUtil.CAMERA_MODE_COLOR_SHIFT:// 移色
                setScrollButtonImage(R.drawable.ic_color_shift_small);
                break;
            case CameraUtil.CAMERA_MODE_TILF_SHIFT:// 移轴
                setScrollButtonImage(R.drawable.ic_tilt_shift_small);
                break;
            case CameraUtil.CAMERA_MODE_SOUND:
                //TODO 设置留声相机UI
                break;
            default:
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            childView.layout(left, top, right, bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            // measure
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /*
     * 在touch时间中判断当前按钮是否被按到
     */

    public boolean onTouch(View v, MotionEvent event) {
    	Log.i(TAG, TAG + " | onTouch");
        switch (v.getId()) {
            case R.id.camera_icon_layout:
                isZoomButtonTouch = true;
                break;
            default:
                isZoomButtonTouch = false;
                break;
        }
        return false;
    }

    private boolean mIsEnable = true;

    public void setEnabled(boolean isEnable) {
        super.setEnabled(isEnable);
        mIsEnable = isEnable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	Log.i(TAG, TAG + " | onTouchEvent");
        if (isZoomButtonTouch && mIsEnable) {
            autoMouse(event);
        }
        return true;
    }

    /*
     * 按钮移动的逻辑处理
     * @return void
     */

    boolean mCanShoot = true;// 记录当前拍照按钮是否可拍照

    public boolean autoMouse(MotionEvent event) {
        // 1.整个布局高度
        int barWidth = getHeight();
        // 2.底部滑动条view的中点坐标
        int zoomBarCenterPoint = barWidth / 2;

        // 按钮上部边缘的坐标
        // 由于之前是按竖屏写的所以命名上有些歧义
        int startLeft = zoomBarCenterPoint - mCameraLayout.getHeight() / 2;
        // 当前Y坐标
        int positionY = 0;

        // view的总长度
        int maxLength = barWidth / 2 - mCameraLayout.getHeight() / 2;
        // 基于中点的当前移动距离
        int moveLength = (int) event.getY() - zoomBarCenterPoint;

        switch (event.getAction()) {

        // down的时候只改变按钮背景
            case MotionEvent.ACTION_DOWN:
                mCanShoot = true;
                downX = (int) event.getX();
                downY = (int) event.getY();
                mScrollCameraBtnBg.setImageResource(R.drawable.ic_shutter_button_background_press);

                break;
            // MOVE的时候主要做移动位置的确定
            case MotionEvent.ACTION_MOVE:
                if (hasShutterBtnScroll) {
                    return true;
                }
                if (Math.abs(event.getY() - downY) > 10) { // 当移动距离超过10pix才被认定为移动
                    if (moveLength > (maxLength / 2)) { // 当向下移动距离超过最大距离的50%，确认为情景界面pop显示的触发
                        positionY = startLeft + maxLength;
                        mPopViewType = TOPIC_POP_VIEW;
                    } else if (moveLength < (-maxLength / 2)) { // 当向上移动距离超过最大距离的50%，确认为特效界面pop显示的触发
                        positionY = startLeft - maxLength;
                        mPopViewType = SPECIAL_POP_VIEW;
                    } else {
                        mPopViewType = NONE_POP_VIEW; // 未触发任何view
                    }
                    positionY = (int) event.getY() - mCameraLayout.getHeight() / 2; // 当前button的y坐标
                    if (positionY < startLeft - maxLength) { // 将button的移动位置限制在最大距离内
                        positionY = startLeft - maxLength;
                    } else if ((positionY > startLeft + maxLength)) {
                        positionY = startLeft + maxLength;
                    }
                    setLocation((int) mCameraLayout.getLeft(), positionY); // 重绘当前button的位置
                    // 判断当前button是否在移动状态，如移动距离小于20%最大距离，会认定为点击，进而触发拍摄事件
                    if (Math.abs(moveLength) > (maxLength * 0.15f) || mCanShoot) {
                        isMove = true;
                    } else {
                        isMove = false;
                    }

                }
                break;
            case MotionEvent.ACTION_UP:

                if (isMove) { // 按钮在移动状态
                    int animationStart = 0;
                    animationStart = moveLength; // 回弹动画的起始位置

                    if (moveLength > (maxLength)) {
                        animationStart = maxLength;
                    } else if (moveLength < (-(maxLength))) {
                        animationStart = -maxLength;
                    }
                    Animation backAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, // 基于当前位置的回弹动画
                            0f, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE, animationStart, Animation.ABSOLUTE, 0);
                    backAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), // 设置加速器
                            android.R.anim.decelerate_interpolator));
                    backAnimation.setDuration(SCROLL_BACK_TIME); // 动画时间
                    mCameraLayout.startAnimation(backAnimation);
                    setLocation(mCameraLayout.getLeft(), (int) zoomBarCenterPoint - mCameraLayout.getHeight() / 2);

                    // ZoomBarImage.setBackgroundDrawable(getResources().getDrawable(
                    // R.drawable.hello_btn_apply_nor));
                    if(null != mOnShuttButtonListener) {
                    	if (mPopViewType == TOPIC_POP_VIEW) { // 根据move的判断触发情景模式显示
                            mOnShuttButtonListener.onEffectEventDown();
                        } else if (mPopViewType == SPECIAL_POP_VIEW) { // 根据move的判断触发特效模式显示
                            mOnShuttButtonListener.onSceneEventDown();
                        }
                    }
                } else {
                    if (Math.abs(event.getX() - downX) > 110) {
                        mCanShoot = false;
                    }
				if (mCanShoot && null != mOnShuttButtonListener) {
					mOnShuttButtonListener.onShutterButtonClickListener();
				} else {
                        mCanShoot = true;
                    }
                }
                mScrollCameraBtnBg.setImageResource(R.drawable.ic_shutter_button_background); // 参数回复初始状态
                isZoomButtonTouch = false;
                isMove = false;
                break;
            default:
                break;
        }
        return false;
    }

    /*
     * 绘制button的位置
     */
    public void setLocation(int x, int y) {
        mCameraLayout.layout(x, y, x + mCameraLayout.getMeasuredWidth(), y + mCameraLayout.getMeasuredHeight());
    }

    /*
     * button的事件回调
     */
    public interface ShutterButtonPanelListener {
    	public final int SHUTTER_EXCEPTION_NOT_ENOUGH_MEMORY = 0; 
        /**
         * 触发特效显示
         * 
         * @author lizhipeng
         */
        void onSceneEventDown(); //

        /**
         * 触发情景显示
         * 
         * @author lizhipeng
         */
        void onEffectEventDown(); //

        /**
         * 点击触发
         * 
         * @author lizhipeng
         */
        void onShutterButtonClickListener();

    }

    public void setOnShutterButtonListener(ShutterButtonPanelListener listener) {
        mOnShuttButtonListener = listener;
    }

     
    @Override
    public void setOrientation(int orientation, boolean animation) {
        Rotatable[] indicators = {
                mTopicImageView, mCameraImageView, mSpecialImageView, mScrollCameraBtnBg
        };
        for (Rotatable indicator : indicators) {
            if (indicator != null) {
                indicator.setOrientation(orientation, animation);
            }
        }
    }

    public void setScrollButtonImage(int resId) {
        mCameraImageView.setImageResource(resId);
    }

    /**
     * @Description 根据当前模式改变拍照button的image显示.
     * @author zengchuanmeng
     * @param
     * @return void
     */
    public void changeToEffectMode() {
        switch (mShutterMode) {
            case CameraUtil.CAMERA_MODE_EFFECT:
                setScrollButtonImage(R.drawable.ic_effect_scene_select_back);
                setLeftIconImage(R.drawable.ic_effect);
                break;
            case CameraUtil.CAMERA_MODE_SELF_TIMER:
                setScrollButtonImage(R.drawable.ic_self_timer_small);
                break;
            case CameraUtil.CAMERA_MODE_NOMARL:
                setScrollButtonImage(R.drawable.ic_normal_small);
                break;
            case CameraUtil.CAMERA_MODE_FUN:
                setScrollButtonImage(R.drawable.ic_fun_small);
                break;
            case CameraUtil.CAMERA_MODE_TILF_SHIFT:
                setScrollButtonImage(R.drawable.ic_tilt_shift_small);
                break;
            case CameraUtil.CAMERA_MODE_COLOR_SHIFT:
                setScrollButtonImage(R.drawable.ic_color_shift_small);
                break;
            case CameraUtil.CAMERA_MODE_SOUND:
                //TODO 添加留声相机代码
                break;
            default:
                break;
        }
    }

    /**
     * @Description 根据当前模式改变拍照button的image显示为情景.
     * @author zengchuanmeng
     * @param
     * @return void
     */
    public void changeToSceneTemplateMode() {
        switch (mShutterMode) {
            case CameraUtil.CAMERA_MODE_EFFECT:
                setScrollButtonImage(R.drawable.ic_effect_scene_select_back);
                break;
            default:
                break;
        }
    }

    /*
     * 此方法用于使恢复到最初的拍摄预览状态
     */
    public void changeToPreViewMode() {
        setScrollButtonImage(mShutterButtonImageResId);
        setRightIconImage(mShutterButtonRightImageResId);
        setLeftIconImage(mShutterButtonLeftImageResId);
    }

    public void setLeftIconImage(int resId) {
        mSpecialImageView.setImageResource(resId);
    }

    public void setRightIconImage(int resId) {
        mTopicImageView.setImageResource(resId);
    }

    /**
     * @Description 拍照按钮滑动.
     */
    private boolean hasShutterBtnScroll = false;

    /**
     * @Description 显示拍照按钮旁边的UI.
     * @author zengchuanmeng
     * @param
     * @return void
     */
    public void showShutBtnLeftRightView() {
        hasShutterBtnScroll = false;
        ivShutterButtonLine.setVisibility(View.VISIBLE);
        mSpecialImageView.setVisibility(View.VISIBLE);
        mTopicImageView.setVisibility(View.VISIBLE);
    }

    /**
     * @Description 隐藏拍照按钮旁边的UI.
     * @author zengchuanmeng
     * @param
     * @return void
     */
    public void hideShutBtnLeftRightView() {
        hasShutterBtnScroll = true;
        ivShutterButtonLine.setVisibility(View.INVISIBLE);
        mSpecialImageView.setVisibility(View.INVISIBLE);
        mTopicImageView.setVisibility(View.INVISIBLE);
    }

    /*
     * 根据当前选中的拍摄模式，设置button的imageId，同时改变模式变量
     */
    public void setCurrentMode(int mode) {
        mShutterMode = mode;
        switch (mShutterMode) {
        // 特效模式
            case CameraUtil.CAMERA_MODE_EFFECT:
                hideSoundWave();
                showShutBtnLeftRightView();
                mShutterButtonImageResId = R.drawable.ic_special_small;
                mShutterButtonRightImageResId = R.drawable.ic_topic;
                mShutterButtonLeftImageResId = R.drawable.ic_effect;
                mShutterMode = CameraUtil.CAMERA_MODE_EFFECT;
                break;
            // 自拍模式
            case CameraUtil.CAMERA_MODE_SELF_TIMER:
                hideSoundWave();
                hideShutBtnLeftRightView();
                mShutterButtonImageResId = R.drawable.ic_self_timer_small;
                mShutterMode = CameraUtil.CAMERA_MODE_SELF_TIMER;
                break;
            // 趣味模式
            case CameraUtil.CAMERA_MODE_FUN:
                mShutterButtonImageResId = R.drawable.ic_fun_small;
                hideShutBtnLeftRightView();
                hideSoundWave();
                mShutterMode = CameraUtil.CAMERA_MODE_FUN;
                break;
            // 零快门模式
            case CameraUtil.CAMERA_MODE_NOMARL:
                mShutterButtonImageResId = R.drawable.ic_normal_small;
                hideShutBtnLeftRightView();
                hideSoundWave();
                mShutterMode = CameraUtil.CAMERA_MODE_NOMARL;
                break;
            // 移轴模式
            case CameraUtil.CAMERA_MODE_TILF_SHIFT:
                mShutterButtonImageResId = R.drawable.ic_tilt_shift_small;
                hideShutBtnLeftRightView();
                hideSoundWave();
                mShutterMode = CameraUtil.CAMERA_MODE_TILF_SHIFT;
                break;
            // 移色模式
            case CameraUtil.CAMERA_MODE_COLOR_SHIFT:
                mShutterButtonImageResId = R.drawable.ic_color_shift_small;
                hideShutBtnLeftRightView();
                hideSoundWave();
                mShutterMode = CameraUtil.CAMERA_MODE_COLOR_SHIFT;
                break;
            case CameraUtil.CAMERA_MODE_SOUND:
                mShutterButtonImageResId = R.drawable.ic_fun_small;
                hideShutBtnLeftRightView();
                showSoundWave();
                mShutterMode = CameraUtil.CAMERA_MODE_SOUND;
                break;
            default:
                break;
        }
    }

    /**
     * @Description 隐藏留声相机的声音波形图.  
     *
     * @author zengchuanmeng
     * @param 
     * @return void
     */
    private void hideSoundWave() {
        // TODO Auto-generated method stub
        
    }

    /**
     * @Description 方法描述.  
     *
     * @author zengchuanmeng
     * @param 
     * @return void
     */
    private void showSoundWave() {
        // TODO Auto-generated method stub
        
    }

    /*
     * 返回当前的拍摄模式
     */
    public int getCurrentMode() {
        return mShutterMode;
    }

    private static final int MOVE_TOP = 0;
    private static final int MOVE_BOTTOM = 1;

    private int mMoveDir = MOVE_TOP;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {

        if (isMove || getCurrentMode() != CameraUtil.CAMERA_MODE_EFFECT) {
            return;
        }

        mCanShoot = false;
        int maxOffset = 0;
        switch (v.getId()) {
            case R.id.top_click_layout:
                mMoveDir = MOVE_TOP;
                maxOffset = -mCameraLayout.getHeight() / 3 * 2;
                break;
            case R.id.bottom_click_layout:
                mMoveDir = MOVE_BOTTOM;
                maxOffset = mCameraLayout.getHeight() / 3 * 2;
                break;
            default:
                break;
        }

        AnimationSet animGroup = new AnimationSet(false);

        Animation forwardAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, maxOffset);
        forwardAnimation.setDuration(SCROLL_BACK_TIME); // 动画时间
        forwardAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), // 设置加速器
                android.R.anim.decelerate_interpolator));
        forwardAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mMoveDir == MOVE_TOP) {
                    mOnShuttButtonListener.onSceneEventDown();
                } else {
                    mOnShuttButtonListener.onEffectEventDown();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });

        Animation backAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0, Animation.ABSOLUTE,
                0, Animation.ABSOLUTE, -maxOffset);
        backAnimation.setDuration(SCROLL_BACK_TIME); // 动画时间
        backAnimation.setStartOffset(SCROLL_BACK_TIME);
        backAnimation.setInterpolator(AnimationUtils.loadInterpolator(getContext(), // 设置加速器
                android.R.anim.decelerate_interpolator));

        animGroup.addAnimation(forwardAnimation);
        animGroup.addAnimation(backAnimation);

        mCameraLayout.startAnimation(animGroup);

    }
}
