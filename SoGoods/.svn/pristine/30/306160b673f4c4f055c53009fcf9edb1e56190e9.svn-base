package com.hitechno.sogoods.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.models.Product;

/**
 * Transforms a <code>HttpResponse</code> into <code>JSON</code> for each
 * model in the application that requires remote data.
 */
public class UpdateResponseDelegate extends BaseResponseDelegate {
	
	private ContentValues[] contentValues;
	
	public UpdateResponseDelegate() {
		super();
		contentValues = new ContentValues[0];
	}
	
	public ContentValues[] getContentValues() {
		return this.contentValues;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hitechno.sogoods.delegates.BaseResponseDelegate#handleResponse(java.lang.String, org.apache.http.HttpResponse)
	 */
	@Override
	public Boolean handleResponse(String url, HttpResponse response) {
		BufferedReader reader = (BufferedReader)jsonFromResponse(response);
		Uri uri = null;
		try {
			uri = getContentValuesForModelInUrlUsingReader(url, reader);
		} catch (JsonSyntaxException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": " + e.getMessage());
			return false;
		}
		
		if (willUpdateContentValues(contentValues)) {
			// TODO Refresh the database, but handle user experience!
//			getContext().getContentResolver().update(uri, contentValues);
			return true;
		}
		// If nothing was parsed don't do anything
		return false;
	}
	
	/**
	 * Parses <code>Reader</code> into the {@link ContentValues} for the appropriate
	 * model based on <code>url</code>. For example, the following url
	 * <p>
	 * <code>
	 * 	"http://www.test.com/advertisements/advertisement_1.png"
	 * </code>
	 * <p>
	 * will be identified with the Advertisement model via "advertisements". This
	 * method will return the correct <code>ContentUri</code> for the model.
	 * 
	 * @param url		the url of the <code>HttpRequest</code>
	 * @param reader	the data to be parsed
	 * @return a {@link ContentUri} for the model that was parsed
	 * @throws JsonSyntaxException
	 */
	public Uri getContentValuesForModelInUrlUsingReader(String url, Reader reader) throws JsonSyntaxException {
		Uri contentUri = null;
		while (iterator.hasNext()) {
			Entry<String, Integer> model = iterator.next();
			if (urlMatchesModel(url, model.getKey())) {
				switch (model.getValue()) {
				case ConstantsHelper.PRODUCTS_VALUE:
					contentValues = parseProducts(reader);
					contentUri = ConstantsHelper.Products.PRODUCTS_CONTENT_URI;
					break;
				default:
					Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": Default getContentValuesForModelInUrlUsingReader");
				}
			}
		}
		return contentUri;
	}
	
	/**
	 * Identifies any non-<code>null</code> {@link ContentValues} in <code>values</code> and
	 * returns <code>true</code> if at least one <code>ContentValues</code> is
	 * not <code>null</code>.
	 * 
	 * @param values an array of <code>ContentValues</code>
	 * @return if the array of <code>ContentValues</code> contains a non-<code>null ContentValues</code>
	 */
	public Boolean willUpdateContentValues(ContentValues[] values) {
		int willUpdateContentValues = 0;
		for (ContentValues value : values) {
			if (value != null) willUpdateContentValues++;
		}
		return willUpdateContentValues > 0;
	}
	
	/**
	 * Deserializes <code>JSON</code> data from <code>reader</code> into
	 * model instances.
	 * <p>
	 * The array of <code>ContentValues</code> is initialized to contain the
	 * number of deserialized model instances. If any of these instances are
	 * not included in the returned array the array will contain <code>null</code>s.
	 * This situation should be handled by the model's <code>ContentProvider</code>'s
	 * <code>bulkInsert</code> method.
	 * 
	 * @param reader	the reader of the <code>HttpResponse</code>
	 * @return the array of <code>ContentValues</code> that contain new data 
	 * @throws JsonSyntaxException
	 */
	public ContentValues[] parseProducts(Reader reader) throws JsonSyntaxException {
		Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
		List<Product> products = getGson().fromJson(reader, listType);
		
		ContentValues[] values = new ContentValues[products.size()];
		
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			values[i] = product.getContentValues();
		}
		return values;
	}
	
	/**
	 * Reads the <code>response</code>'s content, which is <code>JSON</code> data.
	 * 
	 * @param response	the response from a <code>HttpRequest</code>
	 * @return the reader that contains <code>JSON</code> data
	 */
	public Reader jsonFromResponse(HttpResponse response) {
		InputStreamReader responseBody = null;
		InputStream inputStream = null;
		
		try {
			// NOTE: For filesystem testing. inputStream = getContext().getResources().getAssets().open("new_advertisements.json");
			inputStream = response.getEntity().getContent();
		} catch (IOException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		try {
			responseBody = new InputStreamReader(inputStream, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		BufferedReader reader = new BufferedReader(responseBody);
		return reader;
	}
}
