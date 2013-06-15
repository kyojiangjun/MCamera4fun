package com.pinguo.mcamera4fun.test;

import com.pinguo.mcamera4fun.R;
import com.pinguo.mcamera4fun.test.TestListener.EDisplayEvent;
import com.pinguo.mcamera4fun.test.TestListener.IViewUpdater;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestResult;
import android.app.Activity;
import android.os.Bundle;
import android.test.AndroidTestRunner;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements IViewUpdater {

	private static final String TAG = "MainActivity";
	Thread testRunnerThread = null;
	AndroidTestRunner testRunner = null;
	TestResult testResult = null;

	static int testCounter = 0;
	static int errorCounter = 0;
	static int failureCounter = 0;
	TextView statusText;
	TextView testCounterText;
	TextView errorCounterText;
	TextView failureCounterText;

	TestListener listener = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		statusText = (TextView) findViewById(R.id.status);
		testCounterText = (TextView) findViewById(R.id.testCounter);
		errorCounterText = (TextView) findViewById(R.id.errorCounter);
		failureCounterText = (TextView) findViewById(R.id.failureCounter);

		((Button) findViewById(R.id.launch_button))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						startTest();
					}
				});
		((Button) findViewById(R.id.exit_button))
		.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				endTest();
			}
		});
		
		listener = new TestListener();
		listener.setUpdater(this);
	}

	@Override
	protected void onDestroy() {
		testRunnerThread = null;
		super.onDestroy();
	}

	private void startTest() {
		Log.i(TAG, "startTest...");
		MainTest.ctnu = true;
		testRunner = new AndroidTestRunner();
		testResult = new TestResult();
		testRunner.setTest(new MainSuit());
		testRunner.addTestListener(listener);
		testRunner.setContext(this);
		testRunner.runTest(testResult);
	}
	
	private void endTest() {
		Log.i(TAG, "endTest...");
		MainTest.ctnu = false;
	}

	@Override
	public void onUpdate(EDisplayEvent ev) {
		switch (ev) {
		case START_TEST:
			statusText.setText("START_TEST");
			break;
		case END_TEST:
			statusText.setText("END_TEST");
			break;
		case ERROR:
			statusText.setText("ERROR");
			errorCounter++;
			errorCounterText.setText("ERROR : " + errorCounter);
			break;
		case FAILURE:
			failureCounter++;
			failureCounterText.setText("ERROR : " + failureCounter);
			break;
		}
	}

}
