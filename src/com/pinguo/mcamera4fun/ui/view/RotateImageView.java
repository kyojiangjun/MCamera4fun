package com.pinguo.mcamera4fun.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.ThumbnailUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pinguo.mcamera4fun.ui.Rotatable;

public class RotateImageView extends CheckableImageView implements Rotatable {
	
	private static final int ANIMATION_SPEED = 270;
	private static final int DEGREE_ROUND = 360;
	private boolean isAnimate = false;
	
    protected int mCurrentDegree = 0;
    private int mStartDegree = 0;
    private int mTargetDegree = 0;
    
    private boolean clockWise = false;
    private long mAnimationStartTime = 0;
    private long mAnimationEndTime = 0;
    
    private Bitmap mThumb;
    private Drawable[] mThumbs;
    private TransitionDrawable mThumbTransition;

	public RotateImageView(Context context) {
		super(context);
	}
	
	public RotateImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setOrientation(int orientation, boolean animation) {
		isAnimate = animation;
		clockWise = orientation >= 0;
		orientation = orientation >= 0 ? orientation % DEGREE_ROUND : orientation % DEGREE_ROUND + DEGREE_ROUND;
		if(mTargetDegree == orientation)
			return;
		mTargetDegree = orientation;
		if(isAnimate) {
			mStartDegree = mCurrentDegree;
			mAnimationStartTime = AnimationUtils.currentAnimationTimeMillis();
			int diff = mTargetDegree - mStartDegree;
			diff = diff >= 0 ? diff % DEGREE_ROUND : diff % DEGREE_ROUND + DEGREE_ROUND;
			mAnimationEndTime = mAnimationStartTime + diff  * 1000 / ANIMATION_SPEED;
		} else {
			mCurrentDegree = mTargetDegree;
		}
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Drawable drawable = getDrawable();
		if(null == drawable)
			return;
		Rect bound = drawable.getBounds();
		int w = bound.right - bound.left;
		int h = bound.bottom - bound.top;
		if(w == 0 || h == 0)
			return;
		if(mCurrentDegree != mTargetDegree) {
			long time = AnimationUtils.currentAnimationTimeMillis();
			if(time < mAnimationEndTime) {
				 int deltaTime = (int) (time - mAnimationStartTime);
	                int degree = mStartDegree + ANIMATION_SPEED * (clockWise ? deltaTime : -deltaTime)
	                        / 1000;
	                degree = degree >= 0 ? degree % DEGREE_ROUND 
	                        : degree % DEGREE_ROUND + DEGREE_ROUND;
	                mCurrentDegree = degree;
	                invalidate();
			} else {
				mCurrentDegree = mTargetDegree;
			}
		}
		
		int left = getPaddingLeft();
        int top = getPaddingTop();
        int right = getPaddingRight();
        int bottom = getPaddingBottom();
        int width = getWidth() - left - right;
        int height = getHeight() - top - bottom;

        int saveCount = canvas.getSaveCount();

        if ((getScaleType() == ImageView.ScaleType.FIT_CENTER) && ((width < w) || (height < h))) {
            float ratio = Math.min((float) width / w, (float) height / h);
            canvas.scale(ratio, ratio,left+width / 2.0f,top+ height / 2.0f);
        }
        canvas.translate(left + width / 2, top + height / 2);
        canvas.rotate(-mCurrentDegree);
        canvas.translate(-w / 2, -h / 2);
        drawable.draw(canvas);
        canvas.restoreToCount(saveCount);
	}
	
	public void setBitmap(Bitmap bitmap) {
        // Make sure uri and original are consistently both null or both
        // non-null.
        if (bitmap == null) {
            mThumb = null;
            mThumbs = null;
            setImageDrawable(null);
            setVisibility(GONE);
            return;
        }

        LayoutParams param = getLayoutParams();
        final int miniThumbWidth = param.width - getPaddingLeft() - getPaddingRight();
        final int miniThumbHeight = param.height - getPaddingTop() - getPaddingBottom();
        mThumb = ThumbnailUtils.extractThumbnail(bitmap, miniThumbWidth, miniThumbHeight);
        if (mThumbs == null || !isAnimate) {
            mThumbs = new Drawable[2];
            mThumbs[1] = new BitmapDrawable(getContext().getResources(), mThumb);
            setImageDrawable(mThumbs[1]);
        } else {
            mThumbs[0] = mThumbs[1];
            mThumbs[1] = new BitmapDrawable(getContext().getResources(), mThumb);
            mThumbTransition = new TransitionDrawable(mThumbs);
            setImageDrawable(mThumbTransition);
            mThumbTransition.startTransition(500);
        }
        setVisibility(VISIBLE);
    }

}
