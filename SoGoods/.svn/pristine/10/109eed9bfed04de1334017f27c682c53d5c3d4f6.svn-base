package com.hitechno.sogoods.interfaces;

import java.io.IOException;

import org.apache.http.HttpResponse;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;

/**
 * Implemented by delegate classes that handle responses from <code>HttpRequest</code>s
 * and that write data to the filesystem.
 */
public interface ResponseDelegateInterface {
	public void setContext(Context context);
	
	/**
	 * Handles a <code>HttpResponse</code> from <code>HttpRequestTask</code> by
	 * reading the response, parsing it into {@link ContentValues}, and updating
	 * the appropriate {@link ContentProvider}.
	 * 
	 * @param url		a key that uniquely identifies the request, background task,
	 * 					and database model
	 * @param response	the response from the server
	 * 
	 * @return whether the response was handled successfully
	 */
	public Boolean handleResponse(String url, HttpResponse response) throws IOException;
}
