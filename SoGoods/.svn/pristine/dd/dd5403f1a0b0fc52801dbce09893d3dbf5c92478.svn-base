package com.hitechno.sogoods.tasks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.content.ContentProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.hitechno.sogoods.caches.AssetCache;
import com.hitechno.sogoods.helpers.ConstantsHelper;

/**
 * Loads an asset on a background thread, caches the asset, and notifies the
 * asset's model adapter to update the UI.
 */
public class AssetCacheTask extends AsyncTask<String, Integer, Bitmap> {

	private Context context;
	private String url;
	
	private AssetCache imageCache;
	
	private Map<String, Integer> models = ConstantsHelper.MODELS;
	private Iterator<Entry<String, Integer>> iterator = models.entrySet().iterator();
	
	/**
	 * Default constructor. A reference to the singleton cache
	 * is created.
	 * 
	 * @param context	the application context
	 */
	public AssetCacheTask(Context context) {
		this.context = context;
		
		imageCache = AssetCache.getInstance(context);
	}

	/**
	 * Loads either a placeholder or an asset. Given a <code>url</code> this
	 * loads a placeholder or an asset; if the <code>url</code> contains a
	 * HTTP protocol it is interpreted as a non-local asset and a placeholder
	 * is used; otherwise, an asset on the local filesystem is cached.
	 * 
	 * @param params	a list of <code>String</code> params. The list contains
	 * 					one <code>String</code> that describes the asset's <code>url</code>.
	 */
	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = null;
		InputStream inputStream = null;
		
		url = params[0];
		
		if (url.matches("^http.*")) {
			try {
				inputStream = context.getResources().getAssets().open(ConstantsHelper.IMAGE_PLACEHOLDER);
		        bitmap = BitmapFactory.decodeStream(inputStream);
			} catch (IOException e) {
				Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": " + e.getMessage());
			} finally {
				if (inputStream != null) {
	            	try {
						inputStream.close();
					} catch (IOException e) {
						Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": " + e.getMessage());
					}
	            }
			}
		} else {
			bitmap = BitmapFactory.decodeFile(url);
		}
		return bitmap;
	}

	/**
	 * Caches the image and for assets notifies the asset model's {@link ContentProvider}.
	 */
	@Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null) {
        	synchronized (imageCache) {
        		imageCache.put(url, bitmap);
        	}
        	
        	if (!url.equals(ConstantsHelper.CACHE_KEY_FOR_IMAGE_PLACEHOLDER))
        		context.getContentResolver().notifyChange(getContentUriForUrl(url), null);
        }
    }
	
	/**
	 * Identifies the correct {@link Uri} to notify for a given <code>url</code>.
	 * <p>
	 * For example, the following url
	 * <p>
	 * <code>
	 * 	"http://www.test.com/advertisements/advertisement_1.png"
	 * </code>
	 * <p>
	 * will be identified with the Advertisement model via "advertisements". This
	 * method will return the correct <code>Uri</code> for the model.
	 * 
	 * @param url	the url of the asset from the database
	 * @return a {@link Uri} for the model that was identified
	 */
	public Uri getContentUriForUrl(String url) {
		Uri contentUri = null;
		while (iterator.hasNext()) {
			Entry<String, Integer> model = iterator.next();
			if (urlMatchesModel(url, model.getKey())) {
				switch (model.getValue()) {
				case ConstantsHelper.ADVERTISEMENTS_VALUE:
					contentUri = ConstantsHelper.Advertisements.ADVERTISEMENTS_CONTENT_URI;
					break;
				case ConstantsHelper.BRANDS_VALUE:
					contentUri = ConstantsHelper.Brands.BRANDS_CONTENT_URI;
					break;
				case ConstantsHelper.CATEGORIES_VALUE:
					contentUri = ConstantsHelper.Categories.CATEGORIES_CONTENT_URI;
					break;
				case ConstantsHelper.NEWS_VALUE:
					contentUri = ConstantsHelper.News.NEWS_CONTENT_URI;
					break;
				case ConstantsHelper.PHOTOS_VALUE:
					contentUri = ConstantsHelper.Photos.PHOTOS_CONTENT_URI;
					break;
				case ConstantsHelper.PRODUCTS_VALUE:
					contentUri = ConstantsHelper.Products.PRODUCTS_CONTENT_URI;
					break;
				case ConstantsHelper.PROFILES_VALUE:
					contentUri = ConstantsHelper.Profiles.PROFILES_CONTENT_URI;
					break;
				case ConstantsHelper.QUESTIONS_VALUE:
					contentUri = ConstantsHelper.Questions.QUESTIONS_CONTENT_URI;
					break;
				default:
					Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": Default getContentUriForUrl " + url);
				}
			}
		}
		return contentUri;
	}
	
	/**
	 * Performs a regular expression comparison between two <code>String</code>s
	 * to determine if one contains a reference to a model class. For example, the
	 * following <code>url</code>:
	 * <p>
	 * <code>
	 * 	"http://www.test.com/advertisements/advertisement.png"
	 * </code>
	 * <p>
	 * will match with the <code>model</code> <code>"advertisments"</code> and
	 * identify the <code>url</code> with the <code>Advertisement</code> model.
	 * 
	 * @param url	the url for a <code>HttpRequestTask</code> or <code>AssetRequestTask</code>
	 * @param model	the pluralized name of a model
	 * @return whether the <code>url</code> matches the <code>model</code>
	 */
	public Boolean urlMatchesModel(String url, String model) {
		Boolean didMatchModel = false;
		didMatchModel = url.matches("^.*(" + model + ").*$");
		return didMatchModel;
	}
}
