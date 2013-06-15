package com.pinguo.mcamera4fun.test;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import android.test.AndroidTestCase;
import android.util.Log;

public class MainTest extends AndroidTestCase {
	
	static final String TAG = "MainTest";
	private float f1;
	private float f2;
	
	private HashMap<Integer, Integer> mHashMap = null;
	private ConcurrentHashMap<Integer, Integer> mConcurrentHashMap = null;
	private static final int tCount = 10;
	public static boolean ctnu = true;
	
	class task implements Runnable {
		@Override
		public void run() {
			while(ctnu) {
				Random ran = new Random(System.currentTimeMillis());
				int ri = ran.nextInt(10000);
				assertTrue(TAG, (mHashMap.get(ri) != ri));
			}
		}
	};
	class task2 implements Runnable {
		@Override
		public void run() {
			while(ctnu) {
				Random ran = new Random(System.currentTimeMillis());
				int ri = ran.nextInt(10000);
				int reti = mHashMap.put(ri, ri);
				assertTrue(TAG, (reti != ri));
			}
		}
	}
	
	@Override
	protected void setUp() throws Exception {
		Log.i(TAG, "MainTest | setUp");
		mHashMap = new HashMap<Integer, Integer>();
		mConcurrentHashMap = new ConcurrentHashMap<Integer, Integer>();
		for(int i = 0; i < 10000; i++) {
			mHashMap.put(i, i);
			mConcurrentHashMap.put(i, i);
		}
	}
	
	public void testAdd() {
		Log.i(TAG, "MainTest | testAdd");
		for(int i = 0; i < tCount; i++) {
			Thread t = new Thread(new task());
			t.start();
			Thread t2 = new Thread(new task2());
			t2.start();
		}
	}
	
	@Override
	public void testAndroidTestCaseSetupProperly() {
		super.testAndroidTestCaseSetupProperly();
		Log.i(TAG, "MainTest | testAndroidTestCaseSetupProperly");
	}

}
