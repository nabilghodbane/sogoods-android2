package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.ProfessionUser;

public class ProfessionUserAPI {

	public static ArrayList<ProfessionUser> getAllProfessionUser() {
		ArrayList<ProfessionUser> lstProfessionUsers = new ArrayList<ProfessionUser>();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//get the device lang
			nameValuePairs = ConstantsHelper.Profiles.getProfessionUser(Locale
					.getDefault().getLanguage());
//request profission json file from server
			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Profiles.API_PROFILE_GET_PROFESSION,
					nameValuePairs);
			JSONObject jsonObject = new JSONObject(result);
			String jsonProfes = jsonObject.getString("users_professions");
			JSONArray jsonArray = new JSONArray(jsonProfes);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				ProfessionUser professionUser = new ProfessionUser();
				professionUser.id = object.getInt("id");
				professionUser.code = object.getString("code");
				lstProfessionUsers.add(professionUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lstProfessionUsers;
	}

}
