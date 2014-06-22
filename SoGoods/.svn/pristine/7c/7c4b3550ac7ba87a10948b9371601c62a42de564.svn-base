package com.hitechno.sogoods.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.jsoup.Jsoup;

import android.net.Uri;

import com.hitechno.sogoods.util.Debugs;

public class Connectivity {

	public static String loadContentFromAPIGet(String url, List<NameValuePair> nameValuePairs) {
		String result = null;
		HttpClient httpclient = new DefaultHttpClient();
		Uri.Builder builder = Uri.parse(url).buildUpon();
		if (nameValuePairs != null) {
			for (NameValuePair n : nameValuePairs) {
				builder.appendQueryParameter(n.getName(), n.getValue());
			}
		}
		HttpGet request = new HttpGet(builder.build().toString());
		try {
			HttpResponse response = httpclient.execute(request);
			result = inputStreamToString(response.getEntity().getContent());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	public static String loadContentFromAPI(String api,
			List<NameValuePair> nameValuePairs) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(api);
			httppost.addHeader("Accept", "application/json");
			Debugs.show("i", "Connectivity", "calling api : " + api);
			if (nameValuePairs != null) {
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
						HTTP.UTF_8));
				Debugs.show("i", "Connectivity", "nameValuePairs =" + api
						+ nameValuePairs.toString());
			}
			HttpResponse response = httpclient.execute(httppost);
			String result = inputStreamToString(response.getEntity()
					.getContent());
			Debugs.show("i", "Connectivity", "result: " + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;
	}

	public static String inputStreamToString(InputStream is) {
		String s = "";
		String line = "";
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(is, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			reader = new InputStreamReader(is);
		}
		BufferedReader rd = new BufferedReader(reader);
		try {
			while ((line = rd.readLine()) != null) {
				s += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Jsoup.parse(s).text();
	}
}
