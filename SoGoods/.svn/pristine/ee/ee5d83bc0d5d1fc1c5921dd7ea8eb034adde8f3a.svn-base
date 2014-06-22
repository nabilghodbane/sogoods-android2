package com.hitechno.sogoods.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.models.Search;

/**
 * Transforms a <code>HttpResponse</code> into <code>JSON</code> for each
 * model in the application that can be searched.
 */
public class SearchResponseDelegate extends BaseResponseDelegate {
	
	private Uri contentUri;
	private Date updatedAt;
	private ContentValues[] contentValues;
	
	public SearchResponseDelegate() {
		super();
		contentValues = new ContentValues[0];
	}
	
	public Uri getContentUri() {
		return this.contentUri;
	}
	
	public void setContentUri(Uri uri) {
		this.contentUri = uri;
	}
	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = null;
		this.updatedAt = updatedAt;
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
		try {
			getContentValuesForModelInUrlUsingReader(url, reader);
		} catch (JsonSyntaxException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": " + e.getMessage());
			return false;
		}
		
		if (willInsertContentValues(contentValues)) {
			// TODO Refresh the database, but handle user experience!
			getContext().getContentResolver().bulkInsert(getContentUri(), contentValues);
			return true;
		}
		// If nothing was parsed don't do anything
		return false;
	}
	
	public void getContentValuesForModelInUrlUsingReader(String url, Reader reader) throws JsonSyntaxException {
		this.setContentUri(ConstantsHelper.Search.SEARCH_CONTENT_URI);
		String query = queryFromUrl(url);
		contentValues = parseResults(reader, query);
	}
	
	/**
	 * Identifies any non-<code>null</code> {@link ContentValues} in <code>values</code> and
	 * returns <code>true</code> if at least one <code>ContentValues</code> is
	 * not <code>null</code>.
	 * 
	 * @param values an array of <code>ContentValues</code>
	 * @return if the array of <code>ContentValues</code> contains a non-<code>null ContentValues</code>
	 */
	public Boolean willInsertContentValues(ContentValues[] values) {
		int willInsertContentValues = 0;
		for (ContentValues value : values) {
			if (value != null) willInsertContentValues++;
		}
		return willInsertContentValues > 0;
	}
	
	public Boolean searchUrlMatchesModel(String url, String model) {
		Boolean didMatchModel = false;
		didMatchModel = url.matches("^.*/(" + model + ")/search.*");
		return didMatchModel;
	}
	
	/**
	 * Obtains the value of the query parameter from a <code>url</code>
	 * 
	 * @param url the url that contains a ?query= parameter
	 * @return the query parameter's value
	 */
	public String queryFromUrl(String url) {
		String query = "";
		Pattern pattern = Pattern.compile(".*query=([a-zA-Z0-9]+)$");
		Matcher matcher = pattern.matcher(url);
		if (matcher.matches())
			query = matcher.group(1);
		return query;
	}
	
	/**
	 * Deserializes <code>JSON</code> data from <code>reader</code> into model
	 * instances.
	 * <p>
	 * The array of <code>ContentValues</code> is initialized to contain the
	 * number of deserialized model instances. If any of these instances are not
	 * included in the returned array the array will contain <code>null</code>s.
	 * This situation should be handled by the model's
	 * <code>ContentProvider</code>'s <code>bulkInsert</code> method.
	 * 
	 * @param reader
	 *            the reader of the <code>HttpResponse</code>
	 * @return the array of <code>ContentValues</code> that contain new data
	 * @throws JsonSyntaxException
	 */
	public ContentValues[] parseResults(Reader reader, String query) throws JsonSyntaxException {
		Type listType = new TypeToken<ArrayList<Search>>() {}.getType();
		List<Search> results = getGson().fromJson(reader, listType);
		
		ContentValues[] values = new ContentValues[results.size()];
		
		for (int i = 0; i < results.size(); i++) {
			Search result = results.get(i);
			values[i] = result.getContentValues(query);
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