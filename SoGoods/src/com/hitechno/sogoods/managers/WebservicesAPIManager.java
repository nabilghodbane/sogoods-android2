package com.hitechno.sogoods.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.AbstractContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class WebservicesAPIManager {
	public static final String API_PROFILE_CREATE = "profile/create.json";

	public static class PostContentObject {
		public static final int TYPE_STRING = 0;
		public static final int TYPE_FILE = 1;
		
		private String m_Name;
		private int m_Type;
		private String m_ContentType;
		private String m_Charset;
		private Object m_Content;
		
		public PostContentObject(int type, String name, String contentType, String charset, Object content) {
			m_Type = type;
			m_Name = name;
			m_ContentType = contentType;
			m_Charset = charset;
			m_Content = content;
		}
	}
	
	public static interface OnResponseReceived {
		public void onResponseReceived(int reqID, String response);
	}
	
	public static interface OnResponseReceivedJSON {
		public void onResponseReceived(int reqID, JSONObject response);
	}
	
	private static final String API_VERSION = "v.0.4";
	// Development
	private static final String API_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com";
	// Production
	//private static final String API_URL = "http://webservices.sogoods.hitechno-ltd.com";
	private static WebservicesAPIManager m_Instance = null;
	
	public static final WebservicesAPIManager getInstance() {
		if (m_Instance == null) {
			m_Instance = new WebservicesAPIManager();
		}
		return m_Instance;
	}
	
	private String m_APIPrefix;
	
	private WebservicesAPIManager() {
		m_APIPrefix = API_URL + "/" + API_VERSION + "/";
	}
	
	public boolean sendGetRequest(Map<String, String> params) {
		return false;
	}
	
	public String sendPostRequest(String service, List<PostContentObject> params) {
		Iterator<PostContentObject> iter = params.iterator();
		MultipartEntity entity = new MultipartEntity();
		PostContentObject object;
		AbstractContentBody contentBody;
		String result = null;
		boolean successEntities = true;
		while (iter.hasNext()) {
			object = iter.next();
			Charset charset = object.m_Charset != null ? Charset.forName(object.m_Charset) : Charset.forName("UTF-8");
			String mimeType = object.m_ContentType != null ? object.m_ContentType : "text/plain";
			if (object.m_Type == PostContentObject.TYPE_STRING) {
				String text = (String)object.m_Content;
				try {
					contentBody = new StringBody(text, mimeType, charset);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					successEntities = false;
					break;
				}
			} else if (object.m_Type == PostContentObject.TYPE_FILE) {
				contentBody = new FileBody((File)object.m_Content, mimeType);
			} else {
				successEntities = false;
				break;
			}
			entity.addPart(object.m_Name, contentBody);
		}
		if (successEntities) {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(m_APIPrefix + service);
			try {
				InputStream response = client.execute(request).getEntity().getContent();
				BufferedReader rdr = new BufferedReader(new InputStreamReader(response));
				String line;
				result = "";
				while ((line = rdr.readLine()) != null) {
					result += line;
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void sendPostRequestAsync(String service, List<PostContentObject> params, OnResponseReceived resultCB, int reqID) {
		PostRequestTask task = new PostRequestTask(service, params, resultCB, reqID);
		task.execute();
	}
	
	private static class PostRequestTask extends AsyncTask<Void, Void, String> {
		private String m_Service;
		private List<PostContentObject> m_Params;
		private OnResponseReceived m_ResultCB;
		private int m_ReqID;
		
		public PostRequestTask(String service, List<PostContentObject> params, OnResponseReceived resultCB, int reqID) {
			m_Service = service;
			m_Params = params;
			m_ResultCB = resultCB;
			m_ReqID = reqID;
		}
		
		@Override
		protected void onPostExecute(String result) {
			if (m_ResultCB != null) {
				m_ResultCB.onResponseReceived(m_ReqID, result);
			}
		}

		@Override
		protected String doInBackground(Void... params) {
			return WebservicesAPIManager.getInstance().sendPostRequest(m_Service, m_Params);
		}
		
	}

	public JSONObject sendPostRequestJSON(String service, List<PostContentObject> params) {
		JSONObject result = null;
		String response = sendPostRequest(service, params);
		if (response != null) {
			try {
				result = new JSONObject(response);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void sendPostRequestAsyncJSON(String service, List<PostContentObject> params, OnResponseReceivedJSON resultCB, int reqID) {
		PostRequestTaskJSON task = new PostRequestTaskJSON(service, params, resultCB, reqID);
		task.execute();
	}
	
	private static class PostRequestTaskJSON extends AsyncTask<Void, Void, JSONObject> {
		private String m_Service;
		private List<PostContentObject> m_Params;
		private OnResponseReceivedJSON m_ResultCB;
		private int m_ReqID;
		
		public PostRequestTaskJSON(String service, List<PostContentObject> params, OnResponseReceivedJSON resultCB, int reqID) {
			m_Service = service;
			m_Params = params;
			m_ResultCB = resultCB;
			m_ReqID = reqID;
		}
		
		@Override
		protected void onPostExecute(JSONObject result) {
			if (m_ResultCB != null) {
				m_ResultCB.onResponseReceived(m_ReqID, result);
			}
		}

		@Override
		protected JSONObject doInBackground(Void... params) {
			return WebservicesAPIManager.getInstance().sendPostRequestJSON(m_Service, m_Params);
		}
	}
	
	public void createUser(long gender, String firstName, String lastName, String birthDate, String email, String password, int idCity,
			boolean acceptPushNotifications, String description, long job, String userName, boolean displayUserName,
			boolean displayBirthDate, File picture, OnResponseReceivedJSON callback, int reqID)
	{
		ArrayList<PostContentObject> content = new ArrayList<PostContentObject>();
		String genderStr = "";
		if (gender == 1) {
			genderStr = "mss";
		} else if (gender == 2) {
			genderStr = "mr";
		} else if (gender == 3) {
			genderStr = "mrs";
		}
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "gender", null, null, genderStr));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "firstname", null, null, firstName));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "lastname", null, null, lastName));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "birthdate", null, null, birthDate));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "email", null, null, email));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "password", null, null, password));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "idcity", null, null, String.valueOf(idCity)));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "accept_push_notifications", null, null, String.valueOf(acceptPushNotifications)));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "description", null, null, description));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "idusers_profession", null, null, String.valueOf(job)));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "username", null, null, userName));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "display_real_name", null, null, String.valueOf(displayUserName)));
		content.add(new PostContentObject(PostContentObject.TYPE_STRING, "display_birthdate", null, null, String.valueOf(displayBirthDate)));
		content.add(new PostContentObject(PostContentObject.TYPE_FILE, "profile_picture_square", null, null, picture));
		sendPostRequestAsyncJSON(API_PROFILE_CREATE, content, callback, reqID);
	}
}
