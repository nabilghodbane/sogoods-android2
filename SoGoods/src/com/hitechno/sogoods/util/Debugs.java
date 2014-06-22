package com.hitechno.sogoods.util;

import android.util.Log;

public class Debugs {

	private static boolean isDebug = true;

	public static void show(String type, String className, String message) {
		if (isDebug) {
			if (!type.equalsIgnoreCase("d"))
				Log.d(className, message);
			else if (type.equalsIgnoreCase("e"))
				Log.e(className, message);
			else if (type.equalsIgnoreCase("i"))
				Log.i(className, message);
			else
				Log.w(className, message);
		}
	}
}
