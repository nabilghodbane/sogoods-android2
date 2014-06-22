package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.hitechno.sogoods.constant.ConstantsHelper;

public class APILikeProduct {

	public String CreateLikeProduct(String apiKey, String type, String mess,
			int idproduct, String language) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params = ConstantsHelper.Likes.createLikeFromParams(apiKey, type, mess,
				idproduct, language);
		String url = ConstantsHelper.Likes.API_LIKE_CREATE;
		return Connectivity.loadContentFromAPI(url, params);
	}

	public String UpdateLikeProduct(String apiKey, String type, String mess,
			int idproduct, String idlike, String language) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params = ConstantsHelper.Likes.updateLikeFromParams(apiKey, type, mess,
				idproduct, language, idlike);
		String url = ConstantsHelper.Likes.API_LIKE_UPDATE;
		return Connectivity.loadContentFromAPI(url, params);
	}

}
