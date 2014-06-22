package com.hitechno.sogoods;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.hitechno.sogoods.R;

public class SplashActivity extends FragmentActivity implements Runnable {

	private final int SPLASH_TIME_OUT = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		new Handler().postDelayed(this, SPLASH_TIME_OUT);
	}

	@Override
	public void run() {
		SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, FragmentActivity.MODE_PRIVATE);
		Intent intent;
		if (prefs.contains(Constants.PREFS_KEY_API_KEY) && prefs.contains(Constants.PREFS_KEY_PROFILE_ID)) {
			intent = new Intent(this, HomeActivity.class);
		} else {
			intent = new Intent(this, LoginActivity.class);
		}
		startActivity(intent);
	}
}
