package com.pinguo.mcamera4fun.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import com.pinguo.mcamera4fun.ui.Rotatable;

public class ModePicker extends RelativeLayout implements Rotatable, OnClickListener, OnDismissListener {

	public ModePicker(Context context) {
		super(context);
	}
	
	public ModePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onDismiss() {
		
	}

	@Override
	public void setOrientation(int orientation, boolean animation) {
		
	}

	@Override
	public void onClick(View v) {
		
	}

}
