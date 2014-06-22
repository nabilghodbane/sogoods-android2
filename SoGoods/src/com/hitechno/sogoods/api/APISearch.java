package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Product;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.models.SearchMenuLeft;
import com.hitechno.sogoods.models.Store;

public class APISearch {

	public static SearchMenuLeft getSearchMenuLeft(String api, String textkey,
			double latitude, double longtitude, String typePhone) {
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Profiles
					.searchListProfilePosttoParams(api, 99, Locale.getDefault()
							.getLanguage(), textkey, latitude, longtitude);

			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Profiles.API_SEARCH_LIST, nameValuePairs);
			JSONObject jsonObject = new JSONObject(result);

			if (jsonObject.has("searchresults")) {
				JSONObject searchResult = jsonObject
						.getJSONObject("searchresults");
				SearchMenuLeft searchMenuLeft = new SearchMenuLeft();

				if (searchResult.has("brands")) {
					JSONArray jsonArrayBrands = searchResult
							.getJSONArray("brands");
					Log.d("JSONBrand", jsonArrayBrands.getJSONObject(0)
							.toString());
					for (int i = 0; i < jsonArrayBrands.length(); i++) {
						JSONObject jsonBrand = jsonArrayBrands.getJSONObject(i);
						Brand brand = new Brand();

						brand.brandID = jsonBrand.getInt("idbrand");
						brand.name = jsonBrand.getString("name");

						searchMenuLeft.lstBrands.add(brand);
					}
				}

				if (searchResult.has("users")) {
					JSONArray jsonArrayUser = searchResult
							.getJSONArray("users");
					Log.d("JSONProfile", jsonArrayUser.getJSONObject(0)
							.toString());
					for (int i = 0; i < jsonArrayUser.length(); i++) {
						JSONObject jsonProfile = jsonArrayUser.getJSONObject(i);
						Profile pro = Profile.initFromJsonSearch(jsonProfile,
								typePhone);

						searchMenuLeft.lstProfiles.add(pro);
					}
				}

				if (searchResult.has("shops")) {
					JSONArray jsonArrayShop = searchResult
							.getJSONArray("shops");
					Log.d("JSONStore", jsonArrayShop.getJSONObject(0)
							.toString());
					for (int i = 0; i < jsonArrayShop.length(); i++) {
						JSONObject jsonShops = jsonArrayShop.getJSONObject(i);
						Store store = Store.initUnitFromJson(jsonShops);

						searchMenuLeft.lstStores.add(store);
					}
				}

				if (searchResult.has("products")) {
					JSONArray jsonArrayProducts = searchResult
							.getJSONArray("products");
					Log.d("JSONProduct", jsonArrayProducts.getJSONObject(0)
							.toString());
					for (int i = 0; i < jsonArrayProducts.length(); i++) {
						JSONObject jsonProduct = jsonArrayProducts
								.getJSONObject(i);
						Product product = Product.initUnitFromJson(jsonProduct,
								typePhone);

						searchMenuLeft.lstProducts.add(product);
					}
				}

				return searchMenuLeft;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// get shop by id
	public String getStoreById(String api, int storeid) {

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Stores.getStoreByidPosttoParam(
					api, storeid, Locale.getDefault().getLanguage(), "full");

			Connectivity.loadContentFromAPI(
					ConstantsHelper.Stores.API_SHOPS_GET, nameValuePairs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
