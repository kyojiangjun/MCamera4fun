package com.pinguo.mcamera4fun.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CheckableImageView extends ImageView {
	
	private boolean mCheckable = true;
	private static final int CHECKED_ALPHA = 255;
	private static final int UNCHECKED_ALPHA = (int) (0.4 * 255);

	public CheckableImageView(Context context) {
		super(context);
	}
	
	public CheckableImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setCheckable(boolean checkable) {
		mCheckable = checkable;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if(mCheckable) {
			if(enabled) {
				setAlpha(CHECKED_ALPHA);
			} else {
				setAlpha(UNCHECKED_ALPHA);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void setClickable(boolean clickable) {
		super.setClickable(clickable);
		if(mCheckable) {
			if(clickable) {
				setAlpha(CHECKED_ALPHA);
			} else {
				setAlpha(UNCHECKED_ALPHA);
			}
		}
	}

}
