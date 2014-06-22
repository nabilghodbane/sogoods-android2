package com.hitechno.sogoods.providers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.models.ProfessionUser;

public class ProfessionUserContentProvider {
	
	List<ProfessionUser> lstProfessionUsers;
	
	public ProfessionUserContentProvider(){
		lstProfessionUsers = new ArrayList<ProfessionUser>();
	}
	
	// get profession user
	public List<ProfessionUser> getAllProfessionUser(){
		//getProfessionUser
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(ConstantsHelper.Profiles.API_PROFILE_LOGOUT);

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Profiles.getProfessionUser(Locale.getDefault().getLanguage());

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			String result = ConstantsHelper.inputStreamToString(response.getEntity().getContent());
			JSONObject jsonObject = new JSONObject(result);
			String jsonProfes = jsonObject.getString("users_professions");
			JSONArray jsonArray = new JSONArray(jsonProfes);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				int id = object.getInt("id");
				String code = object.getString("code");
				Log.e("profess", "profession -  "+ id+"------"+code);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
		return lstProfessionUsers;
	}

}
