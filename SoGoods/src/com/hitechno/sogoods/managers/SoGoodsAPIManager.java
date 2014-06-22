package com.hitechno.sogoods.managers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

import lombok.Getter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.hitechno.sogoods.model.City;
import com.hitechno.sogoods.model.Job;
import com.hitechno.sogoods.util.CommTools;

public class SoGoodsAPIManager {
	private static final String API_VERSION = "v.0.4";
	// Development
	private static final String API_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com";
	// Production
	//private static final String API_URL = "http://webservices.sogoods.hitechno-ltd.com";
	
	// Brands by location and category request (Page 65)
	public static final String BRAND_BY_LOCATION_AND_CATEGORY = "brands/get_by_category_by_location.json";
	// Brand details (Page 61)
	public static final String BRAND_DETAILS = "brands/get.json";
	// City search (Page 78)
	public static final String CITY_SERACH = "city/search.json";
	// Possible jobs listing (Page 23)
	public static final String JOBS_LIST = "profile/get_profession.json";
	// Create user (Page 7)
	public static final String PROFILE_CREATE = "profile/create.json";
	// Check if email is used
	public static final String EMAIL_IS_USED = "profile/email_used.json";
	// Check if email is used
	public static final String PRODUCT_CREATE = "product/create.json";

	private static SoGoodsAPIManager m_Instance = null;
	
	public static SoGoodsAPIManager getInstance() {
		if (m_Instance == null) {
			m_Instance = new SoGoodsAPIManager();
		}
		return m_Instance;
	}
	
	private String m_APIPrefix;
	private String m_APIKey = null;
	
	private SoGoodsAPIManager() {
		m_APIPrefix = API_URL + "/" + API_VERSION + "/";
	}
	
	private class APICallResponse {
		public boolean m_Success;
		public String[] m_Errors;
		public String[] m_Warnings;
		public JSONObject m_Response;
	}
	
	public static class APIResponse {
		@Getter private boolean success;
		@Getter private String[] errors;
		@Getter private String[] warnings;
		@Getter private Object response;

		private APIResponse(APICallResponse resp, Object respContent) {
			success = resp.m_Success;
			if (resp.m_Errors != null) {
				errors = Arrays.copyOf(resp.m_Errors, resp.m_Errors.length);
			} else {
				errors = null;
			}
			if (resp.m_Warnings != null) {
				warnings = Arrays.copyOf(resp.m_Warnings, resp.m_Warnings.length);
			} else {
				warnings = null;
			}
			response = respContent;
		}
	}
	
	private APICallResponse parseResponse(InputStream response) {
		APICallResponse result = null;
		do {
			String strResult;
			strResult = CommTools.stringuifyIS(response);
			if (strResult == null) {
				break;
			}
			JSONObject json;
			try {
				json = new JSONObject(strResult);
			} catch (JSONException e) {
				e.printStackTrace();
				break;
			}
			result = new APICallResponse();
			try {
				result.m_Success = json.getBoolean("success");
				if (result.m_Success) {
					result.m_Errors = null;
					result.m_Warnings = null;
					result.m_Response = json;
				} else {
					JSONArray errors = json.getJSONArray("errors");
					if (errors.length() > 0) {
						result.m_Errors = new String[errors.length()];
						for (int i = 0; i < errors.length(); i++) {
							result.m_Errors[i] = errors.getString(i);
						}
					} else {
						result.m_Errors = null;
					}
					JSONArray warnings = json.getJSONArray("warnings");
					if (warnings.length() > 0) {
						result.m_Warnings = new String[warnings.length()];
						for (int i = 0; i < warnings.length(); i++) {
							result.m_Warnings[i] = warnings.getString(i);
						}
					} else {
						result.m_Warnings = null;
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
				result.m_Success = false;
				break;
			}
		} while (false);
		return result;
	}
	
	private APICallResponse callAPIPost(String api, HashMap<String, Object> reqParams, boolean needsKey) {
		APICallResponse result = null;
		if (needsKey) {
			reqParams.put("apikey", m_APIKey);
		}
		InputStream reqResult = CommTools.callPostWebService(m_APIPrefix + api, reqParams);
		if (reqResult != null) {
			result = parseResponse(reqResult);
			try {
				reqResult.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private APICallResponse callAPI(String api, HashMap<String, String> reqParams, boolean needsKey) {
		APICallResponse result = null;
		if (needsKey) {
			reqParams.put("apikey", m_APIKey);
		}
		InputStream reqResult = CommTools.callWebService(m_APIPrefix + api, reqParams);
		if (reqResult != null) {
			result = parseResponse(reqResult);
			try {
				reqResult.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String getAPIKey() {
		return m_APIKey;
	}
	
	public void setAPIKey(String apiKey) {
		m_APIKey = apiKey;
	}
	
	public int[] getBrandsByCategoryAndLocation(String apiKey, int catID, double latitude, double longitude) {
		HashMap<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("apikey", apiKey);
		reqParams.put("idcategory", Integer.toString(catID));
		reqParams.put("latitude", Double.toString(latitude));
		reqParams.put("longitude", Double.toString(longitude));
		InputStream reqResult = null;
		String strResult;
		JSONObject json = null;
		int[] result = null;
		do {
			reqResult = CommTools.callWebService(m_APIPrefix + BRAND_BY_LOCATION_AND_CATEGORY, reqParams);
			if (reqResult == null) {
				break;
			}
			strResult = CommTools.stringuifyIS(reqResult);
			if (strResult == null) {
				break;
			}
			try {
				json = new JSONObject(strResult);
			} catch (JSONException e) {
				e.printStackTrace();
				break;
			}
			JSONArray brandsIDs = json.names();
			result = new int[brandsIDs.length()];
			for (int i = 0; i < brandsIDs.length(); i++) {
				try {
					result[i] = brandsIDs.getInt(i);
				} catch (JSONException e) {
					e.printStackTrace();
					result = null;
					break;
				}
			}
		} while (false);
		return result;
	}
	
	public City[] getCities(String term, int maxResults, int offset) {
		HashMap<String, String> reqParams = new HashMap<String, String>();
		reqParams.put("term", term);
		if (maxResults > 0) {
			reqParams.put("maxresults", Integer.toString(maxResults));
		}
		if (offset > 0) {
			reqParams.put("offset", Integer.toString(offset));
		}
		City[] result = null;
		APICallResponse response = callAPI(CITY_SERACH, reqParams, false);
		if ((response != null) && (response.m_Success)) {
			try {
				JSONArray cities = response.m_Response.getJSONArray("cities");
				result = new City[cities.length()];
				for (int i = 0; i < cities.length(); i++) {
					result[i] = new City(cities.getJSONObject(i));
				}
			} catch (JSONException e) {
				e.printStackTrace();
				result = null;
			}
		}
		return result;
	}
	
	public Job[] getJobs(String language) {
		Job[] result = null;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("language", language);
		APICallResponse response = callAPI(JOBS_LIST, params, false);
		if ((response != null) && (response.m_Success)) {
			try {
				JSONArray jobs = response.m_Response.getJSONArray("users_professions");
				result = new Job[jobs.length()];
				for (int i = 0; i < jobs.length(); i++) {
					result[i] = new Job(jobs.getJSONObject(i));
				}
			} catch (JSONException e) {
				e.printStackTrace();
				result = null;
			}
		}
		return result;
	}
	
	public static class CreateUserResponse {
		@Getter private int profileID;
		@Getter private int revision;
		
		private CreateUserResponse(int profileID, int revision) {
			this.profileID = profileID;
			this.revision = revision;
		}
	}
	
	public APIResponse createUser(long gender, String firstName, String lastName, String birthDate, String email, String password, int idCity,
			boolean acceptPushNotifications, String description, long job, String userName, boolean displayUserName,
			boolean displayBirthDate, File picture)
	{
		APIResponse result = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		String genderStr = "";
		if (gender == 1) {
			genderStr = "mss";
		} else if (gender == 2) {
			genderStr = "mr";
		} else if (gender == 3) {
			genderStr = "mrs";
		}
		params.put("gender", genderStr);
		params.put("firstname", firstName);
		params.put("birthdate", birthDate);
		params.put("email", email);
		params.put("password", password);
		params.put("idcity", idCity);
		params.put("accept_push_notifications", acceptPushNotifications);
		params.put("description", description);
		params.put("idusers_profession", job);
		params.put("username", userName);
		params.put("display_real_name", displayUserName);
		params.put("display_birthdate", displayBirthDate);
		params.put("lastname", lastName);
		CommTools.FileDataParam pictureParam = new CommTools.FileDataParam();
		pictureParam.m_File = picture;
		pictureParam.m_FileName = "profile_picture_square";
		pictureParam.m_ContentType = "image/jpeg";
		params.put("profile_picture_square", pictureParam);
		APICallResponse response = callAPIPost(PROFILE_CREATE, params, false);
		result = new APIResponse(response, null);
		if (response.m_Success) {
			try {
				result.response = new CreateUserResponse(response.m_Response.getInt("iduser"),
						response.m_Response.getInt("revision"));
			} catch (JSONException e) {
				String[] errors;
				if (result.errors != null) {
					errors = Arrays.copyOf(result.errors, result.errors.length + 1);
				} else {
					errors = new String[1];
				}
				errors[errors.length - 1] = "Parsing error";
				result.errors = errors;
				result.success = false;
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean isEmailUsed(String email) {
		boolean result = true;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		APICallResponse response = callAPI(EMAIL_IS_USED, params, false);
		if (response.m_Success) {
			try {
				result = response.m_Response.getBoolean("email_used");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public APIResponse createProduct(File picture, String title, int brandID, int categoryID, String content) {
		APIResponse result = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		CommTools.FileDataParam pictureParam = new CommTools.FileDataParam();
		pictureParam.m_File = picture;
		pictureParam.m_FileName = "profile_picture_square";
		pictureParam.m_ContentType = "image/jpeg";
		params.put("product_picture_square", pictureParam);
		params.put("title", title);
		params.put("idbrand", brandID);
		params.put("idcategory", categoryID);
		params.put("Content", content);
		APICallResponse response = callAPIPost(PRODUCT_CREATE, params, true);
		result = new APIResponse(response, null);
		if (response.m_Success) {
			try {
				result.response = Integer.valueOf(response.m_Response.getInt("idproduct"));
			} catch (JSONException e) {
				String[] errors;
				if (result.errors != null) {
					errors = Arrays.copyOf(result.errors, result.errors.length + 1);
				} else {
					errors = new String[1];
				}
				errors[errors.length - 1] = "Parsing error";
				result.errors = errors;
				result.success = false;
				e.printStackTrace();
			}
		}
		return result;
	}
}