package com.hitechno.sogoods;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.hitechno.sogoods.util.MySharePreference;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.testflightapp.lib.TestFlight;


public class SoGoodsApplication extends Application {
	private static SoGoodsApplication m_Instance = null;
	@Override
	public final void onCreate() {
		super.onCreate();
		TestFlight.takeOff(this, "ceba5ae5-e7e2-49d4-98ca-9d01b78c66b7");
		initImageLoader(getApplicationContext());
	}
	public static SoGoodsApplication getInstance() {
		if (m_Instance == null) {
			m_Instance = new SoGoodsApplication();
		}
		return m_Instance;
	}
	
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheSize(50 * 1024 * 1024)
				.memoryCacheSize(20 * 1024 * 1024)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	@Override
	public final void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public final void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	public String getApi_key() {
		// TODO Auto-generated method stub
		return MySharePreference.getApiKey(this);
	}

}
