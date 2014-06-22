package com.hitechno.sogoods.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import android.net.Uri;

public class CommTools {
	public static class FileDataParam {
		public File m_File;
		public String m_FileName;
		public String m_ContentType;
	}

	public static InputStream callPostWebService(String ws, Map<String, Object> params) {
		InputStream result = null;
		boolean isMultiPart = false;
		for (String key : params.keySet()) {
			if (params.get(key) instanceof FileDataParam) {
				isMultiPart = true;
				break;
			}
		}
		HttpEntity entity = null;
		String contentType = null;
		if (isMultiPart) {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			for (String key : params.keySet()) {
				if (params.get(key) instanceof FileDataParam) {
					builder.addBinaryBody(key, ((FileDataParam)params.get(key)).m_File,
							ContentType.create(((FileDataParam)params.get(key)).m_ContentType),
							((FileDataParam)params.get(key)).m_ContentType);
				} else {
					builder.addTextBody(key, params.get(key).toString());
				}
			}
			entity = builder.build();
		} else {
			StringBuilder strParams = new StringBuilder();
			boolean firstParam = true;
			for (String key : params.keySet()) {
				try {
					strParams.append(URLEncoder.encode(key, "UTF-8"))
						.append(firstParam ? '?' : '&')
						.append(URLEncoder.encode(params.get(key).toString(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				firstParam = false;
			}
			try {
				entity = new StringEntity(strParams.toString());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			contentType = "application/x-www-form-urlencoded";
		}
		if (entity != null) {
			HttpPost request = new HttpPost(ws);
			request.setEntity(entity);
			if (contentType != null) {
				request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			}
			DefaultHttpClient client = new DefaultHttpClient();
			try {
				HttpResponse response = client.execute(request);
				result = response.getEntity().getContent();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static InputStream callWebService(String ws, Map<String, String> params) {
		InputStream result = null;
		Uri.Builder builder = Uri.parse(ws).buildUpon();
		if (params != null) {
			for (String key : params.keySet()) {
				builder.appendQueryParameter(key, params.get(key));
			}
		}
		HttpGet request = new HttpGet(builder.build().toString());
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(request);
			result = response.getEntity().getContent();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String stringuifyIS(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		String result = "";
		try {
			while ((line = reader.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
