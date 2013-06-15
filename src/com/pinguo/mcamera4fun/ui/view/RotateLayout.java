package com.pinguo.mcamera4fun.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.pinguo.mcamera4fun.ui.Rotatable;
import com.pinguo.mcamera4fun.utils.SdkUtil;

public class RotateLayout extends ViewGroup implements Rotatable {

	public RotateLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setBackgroundResource(android.R.color.transparent);
	}

	public RotateLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundResource(android.R.color.transparent);
	}

	public RotateLayout(Context context) {
		super(context);
		setBackgroundResource(android.R.color.transparent);
	}
	
	private int orientation;
	private static boolean isOldVersion;
	protected View mChild;
	
	static {
		isOldVersion = SdkUtil.is3x0();
	}

	@Override
	public void setOrientation(int orientation, boolean animation) {

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

	}
	
	@Override
	protected void onFinishInflate() {
		mChild = getChildAt(0);
		if(!isOldVersion) {
			
		}
	}

}
