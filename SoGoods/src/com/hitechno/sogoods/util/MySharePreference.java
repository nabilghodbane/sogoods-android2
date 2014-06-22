package com.hitechno.sogoods.util;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;

import com.hitechno.sogoods.models.Profile;

public class MySharePreference {

	private static String TAG_BRAND_FILTER = "BRAND_FILTER";
	private static String TAG_NOTIFICATION_FILTER = "NOTIFICATION_FILTER";
	private static String TAG_PRODUCT_FILTER = "PRODUCT_FILTER";
	private static String TAG_PROFILE = "PROFILE";

	private static String TAG_USERNAME = "USERNAME";
	private static String TAG_PASS = "PASSWORD";
	private static String TAG_PROFILE_ID = "PROFILE_ID";
	private static String TAG_APIKEY = "APIKEY";

	private static SharedPreferences mAppPreferences;
	private static SharedPreferences.Editor mEditor;

	private static void initPrefs(Context context) {
		if (mAppPreferences == null) {
			mAppPreferences = context.getSharedPreferences("sogoods",
					Context.MODE_PRIVATE);
		}
	}

	public static String getUserName(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getString(TAG_USERNAME, "");
	}

	public static boolean saveUserName(Context paramContext, String paramString) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putString(TAG_USERNAME, paramString);
		return mEditor.commit();
	}

	public static String getPass(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getString(TAG_PASS, "");
	}

	public static boolean savePass(Context paramContext, String paramString) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putString(TAG_PASS, paramString);
		return mEditor.commit();
	}

	public static int getProfileID(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getInt(TAG_PROFILE_ID, 0);
	}

	public static boolean saveProfileID(Context paramContext, int paramString) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putInt(TAG_PROFILE_ID, paramString);
		return mEditor.commit();
	}

	public static String getApiKey(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getString(TAG_APIKEY, "");
	}

	public static boolean saveAPIKEY(Context paramContext, String paramString) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putString(TAG_APIKEY, paramString);
		return mEditor.commit();
	}

	public static int getBrandFilter(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getInt(TAG_BRAND_FILTER, 0);
	}

	public static String getNotificationFilter(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getString(TAG_NOTIFICATION_FILTER, "11111");
	}

	public static int getProductFilter(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getInt(TAG_PRODUCT_FILTER, 0);
	}

	public static Profile getProfile(Context paramContext, String typePhone) {
		try {
			Profile mProfile = Profile.initFromJson(new JSONObject(
					getProfileContent(paramContext)), typePhone);
			return mProfile;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getProfileContent(Context paramContext) {
		initPrefs(paramContext);
		return mAppPreferences.getString(TAG_PROFILE, "");
	}

	public static boolean saveBrandFilter(Context paramContext, int paramInt) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putInt(TAG_BRAND_FILTER, paramInt);
		return mEditor.commit();
	}

	public static boolean saveNotificationFilter(Context paramContext,
			String paramString) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putString(TAG_NOTIFICATION_FILTER, paramString);
		return mEditor.commit();
	}

	public static boolean saveProductFilter(Context paramContext, int paramInt) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putInt(TAG_PRODUCT_FILTER, paramInt);
		return mEditor.commit();
	}

	public static boolean saveProfileContent(Context paramContext,
			String paramString) {
		initPrefs(paramContext);
		mEditor = mAppPreferences.edit();
		mEditor.putString(TAG_PROFILE, paramString);
		return mEditor.commit();
	}
}
