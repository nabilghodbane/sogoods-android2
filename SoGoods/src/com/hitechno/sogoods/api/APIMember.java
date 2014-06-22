package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Profile;

public class APIMember {

	private ArrayList<Profile> getOnApi(List<NameValuePair> nameValuePairs,
			String typePhone) {
		ArrayList<Profile> items = new ArrayList<Profile>();
		String result = Connectivity.loadContentFromAPI(
				ConstantsHelper.Profiles.API_PROFILE_LIST, nameValuePairs);
		try {
			JSONObject jsonObject = new JSONObject(result);
			String strProfile = jsonObject.getString("profiles");
			JSONArray array = new JSONArray(strProfile);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);

				Profile pro = new Profile();
				pro.profileID = object.getInt("iduser");
				pro.username = object.getString("username");
				pro.followIdsString = object.getString("nbfollower");
				pro.followingIdsString = object.getString("nbfollowing");
				pro.nblike = object.getInt("nblike");
				pro.cityId = object.getString("idcountry");
				pro.percent = object.getString("match_rate");
				pro.cityName = object.getString("country_name");

				pro.numberOfFollows = object.getInt("nbfollower");
				pro.numberOfFollowings = object.getInt("nbfollowing");
				pro.numberOfProducts = object.getInt("nblike");
				pro.numberOfBrands = object.getInt("nbbrand");
				if (object.has("nbphoto")) {
					pro.numberOfPhotos = object.getInt("nbphoto");
				}

				JSONObject img;
				if (object.has("profile_picture_rectangle")) {
					Object object2 = object.get("profile_picture_rectangle");
					if (object2 instanceof JSONObject) {
						img = object.getJSONObject("profile_picture_rectangle");
						if (typePhone.equals("tablet_large")) {
							if (img.has("1000x250")) {
								pro.urlAvatarLarge = img.getString("1000x250");
							}
						} else if (typePhone.equals("tablet_normal")) {
							if (img.has("640x160")) {
								pro.urlAvatarLarge = img.getString("640x160");
							}
						} else if (typePhone.equals("phone_xlarge")) {
							if (img.has("500x125")) {
								pro.urlAvatarLarge = img.getString("500x125");
							}
						} else if (typePhone.equals("phone_normal")) {
							if (img.has("320x80")) {
								pro.urlAvatarLarge = img.getString("320x80");
							}
						}
					}
				}

				if (object.has("profile_picture_square")) {
					Object object2 = object.get("profile_picture_square");
					
					if (object2 instanceof JSONObject) {

						img = object.getJSONObject("profile_picture_square");
						if (img.has("440x440")) {
							pro.urlHome = img.getString("440x440");
						}

						if (img.has("90x90")) {
							pro.urlAvatarSmall = img.getString("90x90");
						}
						if (img.has("250x250")) {
							pro.urlHome = img.getString("250x250");
						}

						if (img.has("80x80")) {
							pro.urlAvatarSmall = img.getString("80x80");
						}
						if (img.has("220x220")) {
							pro.urlHome = img.getString("220x220");
						}

						if (img.has("45x45")) {
							pro.urlAvatarSmall = img.getString("45x45");
						}
						if (img.has("125x125")) {
							pro.urlHome = img.getString("125x125");
						}

						if (img.has("40x40")) {
							pro.urlAvatarSmall = img.getString("40x40");
						}
//						if (typePhone.equals("tablet_large")) {
//						
//						} else if (typePhone.equals("tablet_normal")) {
//						
//						} else if (typePhone.equals("phone_xlarge")) {
//						
//						} else if (typePhone.equals("phone_normal")) {
//
//						
//						}
					}
				}
				items.add(pro);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return items;
	}

	public ArrayList<Profile> onLoadNews(int kmax, int offset, String apiKey,
			String typePhone) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs = ConstantsHelper.Profiles.post_new_paramsfromparams(
				"new", apiKey, Locale.getDefault().getLanguage(), kmax, offset,
				"full", typePhone);
		return getOnApi(nameValuePairs, typePhone);
	}

	// suggest profile
	public ArrayList<Profile> onLoadSuggested(int kmax, int offset,
			String apiKey, String typePhone) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs = ConstantsHelper.Profiles.post_new_paramsfromparams(
				"suggested", apiKey, Locale.getDefault().getLanguage(), kmax,
				offset, "full", typePhone);
		return getOnApi(nameValuePairs, typePhone);
	}

	// popular profile
	public ArrayList<Profile> onLoadPopular(int kmax, int offset,
			String apiKey, String typePhone) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs = ConstantsHelper.Profiles.post_new_paramsfromparams(
				"popular", apiKey, Locale.getDefault().getLanguage(), kmax,
				offset, "full", typePhone);
		return getOnApi(nameValuePairs, typePhone);
	}

}
