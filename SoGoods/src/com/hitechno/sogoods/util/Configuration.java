package com.hitechno.sogoods.util;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class Configuration {

	public int sessionId = 1;
	public String apiKey;
	public int idprofile;
	public String nameProfile;
	public String avatarProfile;
	public String imageHome;

	public int widthPhone;
	public int heightPhone;

	public String currentLanguage;
	public String typePhone;

	private static Configuration instance;

	public static Configuration getInstance(Context context) {
		if (instance == null) {
			instance = new Configuration(context);
		}
		return instance;
	}

	private Configuration(Context context) {
		getTypePhone((Activity) context);
		getLanguage();
	}

	private void getLanguage() {
		currentLanguage = Locale.getDefault().getLanguage();
	}

	private void getTypePhone(Activity activity) {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		heightPhone = displaymetrics.heightPixels;
		widthPhone = displaymetrics.widthPixels;

		if (widthPhone >= 1024) {
			typePhone = "tablet_large";
		} else if (widthPhone >= 720) {
			typePhone = "tablet_normal";
		} else if (widthPhone >= 480) {
			typePhone = "phone_xlarge";
		} else {
			typePhone = "phone_normal";
		}
	}

}
