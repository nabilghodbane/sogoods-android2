package com.hitechno.sogoods.caches;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.tasks.AssetCacheTask;

/**
 * Caches assets in the application. This class is a singleton.
 * 
 */
public class AssetCache extends LruCache<String, Bitmap> {

	private static AssetCache instance;
	private Context context;

	private static final int CACHE_SIZE = 20;

	public static AssetCache getInstance(Context context) {
		if (instance == null) {
			instance = new AssetCache(context);
		}
		return instance;
	}

	/**
	 * Initializes the cache to <code>CACHE_SIZE</code> elements.
	 * 
	 * @param context
	 *            the application context
	 */
	protected AssetCache(Context context) {
		super(CACHE_SIZE);
		this.context = context;
	}

	/**
	 * Stores a bitmap to be retrieved in from the cache. Uses an
	 * <code>AsyncTask</code> to obtain the bitmap from the filesystem.
	 * If the <code>key</code> is a URL (i.e., begins with "http") display
	 * a placeholder while the remote asset is being fetched.
	 * 
	 * @param key
	 *            the key that retrieves a bitmap. The key references a URL (
	 *            <code>http://...</code>) or an absolute file path (
	 *            <code>/data/data/com...</code>).
	 * 
	 * @return the bitmap to be stored in the cache
	 */
	@Override
	protected Bitmap create(String key) {
		new AssetCacheTask(context).execute(key);

		if (key != ConstantsHelper.CACHE_KEY_FOR_IMAGE_PLACEHOLDER)
			return this.get(ConstantsHelper.CACHE_KEY_FOR_IMAGE_PLACEHOLDER);

		return null;
	}
}
