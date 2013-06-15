/*
 * Copyright (C) 2010 The Android Open Source Project
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

package com.pinguo.mcamera4fun.ui;

import com.pinguo.mcamera4fun.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

// A view that indicates the focus area or the metering area.
public class FocusIndicatorRotateLayout extends View implements FocusIndicator {
    // Sometimes continuous autofucus starts and stops several times quickly.
    // These states are used to make sure the animation is run for at least some
    // time.
	private final String TAG = "FocusIndicatorRotateLayout";
    private int mState;
    private static final int STATE_IDLE = 0;
    private static final int STATE_FOCUSING = 1;
    private static final int STATE_FINISHING = 2;

    private Runnable mDisappear = new Disappear();
    private static final int SCALING_UP_TIME = 1000;
    private static final int SCALING_DOWN_TIME = 200;
    private static final int DISAPPEAR_TIMEOUT = 200;

    public FocusIndicatorRotateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void setDrawable(int resid) {
        setBackgroundDrawable(getResources().getDrawable(resid));
    }

    @Override
    public void showStart() {
    	Log.i(TAG, "FocusView | showStart | " + (mState == STATE_IDLE? "mState == STATE_IDLE" : ""));
        if (mState == STATE_IDLE) {
            setDrawable(R.drawable.focus);
            mState = STATE_FOCUSING;
        }
    }

    @Override
    public void showSuccess(boolean timeout) {
        if (mState == STATE_FOCUSING) {
            setDrawable(R.drawable.focus_succeed);
            if (timeout)
                postDelayed(mDisappear, DISAPPEAR_TIMEOUT);
            mState = STATE_FINISHING;
        }
    }

    @Override
    public void showFail(boolean timeout) {
        if (mState == STATE_FOCUSING) {
            setDrawable(R.drawable.focus_failed);
            if (timeout)
                postDelayed(mDisappear, DISAPPEAR_TIMEOUT);
            mState = STATE_FINISHING;
        }
    }

    @Override
    public void clear() {
        setBackgroundDrawable(null);
        mState = STATE_IDLE;
    }

    private class Disappear implements Runnable {
        @Override
        public void run() {
            setBackgroundDrawable(null);
            mState = STATE_IDLE;
        }
    }
}
