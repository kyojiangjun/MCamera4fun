package com.pinguo.mcamera4fun.utils;

public class SdkUtil {

	private static int SDK_INT;
	private static final int V2X2 = 8;
	private static final int V2X3 = 9;
	private static final int V3X0 = 11;
	private static final int V4X0 = 14;

	static {
		try {
			SDK_INT = android.os.Build.VERSION.class.getField("SDK_INT").getInt(null);
		} catch (Exception e) {
			try {
				SDK_INT = Integer.parseInt((String) android.os.Build.VERSION.class
								.getField("SDK").get(null));
			} catch (Exception e2) {
				SDK_INT = 2;
			}
		}
	}

	public static boolean is2x2() {
		return SDK_INT >= V2X2;
	}

	public static boolean is2x3() {
		return SDK_INT >= V2X3;
	}

	public static boolean is4x0() {
		return SDK_INT >= V4X0;
	}
	
	public static boolean is3x0() {
		return SDK_INT >= V3X0;
	}
}
