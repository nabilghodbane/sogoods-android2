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
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Product;
import com.hitechno.sogoods.models.Profile;

public class ShowResponseDelegate extends BaseResponseDelegate {

	private ContentValues contentValues;

	public ShowResponseDelegate() {
		super();
	}

	public ContentValues getContentValues() {
		return this.contentValues;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hitechno.sogoods.delegates.BaseResponseDelegate#handleResponse(java.lang.String, org.apache.http.HttpResponse)
	 */
	@Override
	public Boolean handleResponse(String url, HttpResponse response) {
		BufferedReader reader = (BufferedReader) jsonFromResponse(response);
		Uri uri = null;
		try {
			uri = getContentValuesForModelInUrlUsingReader(url, reader);
		} catch (JsonSyntaxException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
					+ e.getMessage());
			return false;
		}

		String where = ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " = ?";
		String[] selectionArgs = new String[] { contentValues
				.getAsString(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID) };
		getContext().getContentResolver().update(uri, contentValues, where,
				selectionArgs);
		return true;
	}

	/**
	 * Parses <code>Reader</code> into the {@link ContentValues} for the
	 * appropriate model based on <code>url</code>. For example, the following
	 * url
	 * <p>
	 * <code>
	 * 	"http://www.test.com/advertisements/advertisement_1.png"
	 * </code>
	 * <p>
	 * will be identified with the Advertisement model via "advertisements".
	 * This method will return the correct <code>ContentUri</code> for the
	 * model.
	 * 
	 * @param url
	 *            the url of the <code>HttpRequest</code>
	 * @param reader
	 *            the data to be parsed
	 * @return a {@link ContentUri} for the model that was parsed
	 * @throws JsonSyntaxException
	 */
	public Uri getContentValuesForModelInUrlUsingReader(String url,
			Reader reader) throws JsonSyntaxException {
		Uri contentUri = null;
		while (iterator.hasNext()) {
			Entry<String, Integer> model = iterator.next();
			if (urlMatchesModel(url, model.getKey())) {
				switch (model.getValue()) {
				case ConstantsHelper.BRANDS_VALUE:
					contentValues = parseBrand(reader);
					contentUri = ConstantsHelper.Brands.BRAND_CONTENT_URI;
					break;
				case ConstantsHelper.PRODUCTS_VALUE:
					contentValues = parseProduct(reader);
					contentUri = ConstantsHelper.Products.PRODUCT_CONTENT_URI;
					break;
				case ConstantsHelper.PROFILES_VALUE:
					contentValues = parseProfile(reader);
					contentUri = ConstantsHelper.Profiles.PROFILE_CONTENT_URI;
					break;
				default:

				}
			}
		}
		return contentUri;
	}

	/**
	 * Deserializes <code>JSON</code> data from <code>reader</code> into a model
	 * instance.
	 * 
	 * @param reader
	 *            the reader of the <code>HttpResponse</code>
	 * @return the array of <code>ContentValues</code> that contain new data
	 * @throws JsonSyntaxException
	 */
	public ContentValues parseBrand(Reader reader) throws JsonSyntaxException {
		Type listType = new TypeToken<ArrayList<Brand>>() {
		}.getType();
		List<Brand> brands = getGson().fromJson(reader, listType);

		ContentValues[] values = new ContentValues[brands.size()];

		for (int i = 0; i < brands.size(); i++) {
			Brand brand = brands.get(i);
			values[i] = brand.getContentValuesForSafeUpdate();
		}
		return values[0];
	}

	/**
	 * Deserializes <code>JSON</code> data from <code>reader</code> into a model
	 * instance.
	 * 
	 * @param reader
	 *            the reader of the <code>HttpResponse</code>
	 * @return the array of <code>ContentValues</code> that contain new data
	 * @throws JsonSyntaxException
	 */
	public ContentValues parseProduct(Reader reader) throws JsonSyntaxException {
		Type listType = new TypeToken<ArrayList<Product>>() {
		}.getType();
		List<Product> products = getGson().fromJson(reader, listType);

		ContentValues[] values = new ContentValues[products.size()];

		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			values[i] = product.getContentValuesForSafeUpdate();
		}
		return values[0];
	}

	/**
	 * Deserializes <code>JSON</code> data from <code>reader</code> into a model
	 * instance.
	 * 
	 * @param reader
	 *            the reader of the <code>HttpResponse</code>
	 * @return the array of <code>ContentValues</code> that contain new data
	 * @throws JsonSyntaxException
	 */
	public ContentValues parseProfile(Reader reader) throws JsonSyntaxException {
		Type listType = new TypeToken<ArrayList<Profile>>() {
		}.getType();
		List<Profile> profiles = getGson().fromJson(reader, listType);

		ContentValues[] values = new ContentValues[profiles.size()];

		for (int i = 0; i < profiles.size(); i++) {
			Profile profile = profiles.get(i);
			values[i] = profile.getContentValuesForSafeUpdate();
		}
		return values[0];
	}

	/**
	 * Reads the <code>response</code>'s content, which is <code>JSON</code>
	 * data.
	 * 
	 * @param response
	 *            the response from a <code>HttpRequest</code>
	 * @return the reader that contains <code>JSON</code> data
	 */
	public Reader jsonFromResponse(HttpResponse response) {
		InputStreamReader responseBody = null;
		InputStream inputStream = null;

		try {
			// NOTE: For filesystem testing. inputStream =
			// getContext().getResources().getAssets().open("new_advertisements.json");
			inputStream = response.getEntity().getContent();
		} catch (IOException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
					+ e.getMessage());
		}

		try {
			responseBody = new InputStreamReader(inputStream, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
					+ e.getMessage());
		}

		BufferedReader reader = new BufferedReader(responseBody);
		return reader;
	}
}
