package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.models.CategoryBrand;
import com.hitechno.sogoods.util.Debugs;
import com.mttam.toollibrary.tools.Tools;

public class APIProductAndBrand {

	public CategoryBrand onLoadCategoriesAndBrand(String lastUpdate,
			String apiKey, String typePhone) {
		CategoryBrand categoryBrand = new CategoryBrand();
		ArrayList<Category> lstCategory = new ArrayList<Category>();
		ArrayList<Brand> lsBrands = new ArrayList<Brand>();

		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs = ConstantsHelper.Categories
					.load_all_new_categories_post_param(apiKey, Locale
							.getDefault().getLanguage(), typePhone, lastUpdate);
			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Brands.API_GENERAL_GET_UPDATED_INFO,
					nameValuePairs);

			JSONObject jsonObject = new JSONObject(result);
			/* Categories */
			String strProfile = jsonObject.getString("categories");
			Debugs.show("i", "APIProductAndBrand", strProfile);

			JSONArray array = new JSONArray(strProfile);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				if (object.getInt("status") == 1) {
					Category categ = new Category();
					categ.categoryID = object.getInt("idcategory");
					if (object.has("name")) {
						categ.title = object.getString("name");
						String sub = categ.title.substring(0, 1);
						categ.subChar = sub;

						categ.lstStringName.add(categ.title);
						categ.lstStringName.add(categ.subChar);
					}

					categ.status = object.getInt("status");

					JSONObject img;
					if (object.has("category_picture_square")) {
						Object obj = object.get("category_picture_square");
						if (obj instanceof JSONObject) {
							img = object
									.getJSONObject("category_picture_square");
							if (typePhone.equals("tablet_large")) {
								if (img.has("150x150")) {
									categ.url = img.getString("150x150");
								}
							} else if (typePhone.equals("tablet_normal")) {
								if (img.has("75x75")) {
									categ.url = img.getString("75x75");
								}
							} else if (typePhone.equals("phone_xlarge")) {
								if (img.has("110x110")) {
									categ.url = img.getString("110x110");
								}
							} else if (typePhone.equals("phone_normal")) {
								if (img.has("55x55")) {
									categ.url = img.getString("55x55");
								}
							}
						}
					}

					if (object.has("brand_list")) {
						String strBrandList = object.getString("brand_list");
						categ.lstStringBrand = strBrandList;
					}
					lstCategory.add(categ);
					new Category();
					Collections.sort(lstCategory, Category.catNameComparator);
				}
			}

			/* Brand */
			String strBrand = jsonObject.getString("brands");
			Debugs.show("i", "APIProductAndBrand", strBrand);

			JSONArray jsonArrBrand = new JSONArray(strBrand);
			for (int i1 = 0; i1 < jsonArrBrand.length(); i1++) {
				JSONObject objectBrand = jsonArrBrand.getJSONObject(i1);
				Brand brand = new Brand();
				brand.brandID = objectBrand.getInt("idbrand");

				if (objectBrand.has("name")) {
					brand.name = objectBrand.getString("name");
				}

				if (objectBrand.has("status")) {
					brand.status = objectBrand.getInt("status");
				}

				JSONObject jsonCatList;
				if (objectBrand.has("brand_logo_square")) {
					Object o = objectBrand.get("brand_logo_square");
					if (o instanceof JSONObject) {
						jsonCatList = objectBrand
								.getJSONObject("brand_logo_square");
						Map<String, String> map = new HashMap<String, String>();
						Iterator<?> iter = jsonCatList.keys();
						while (iter.hasNext()) {
							String key = (String) iter.next();
							String value = jsonCatList.getString(key);
							map.put(key, value);
							brand.url = key + ":" + value + " ";
						}
					}
				}
				if (!Tools.isEmpty(brand.name))
					lsBrands.add(brand);
			}
			new Brand();
			Collections.sort(lsBrands, Brand.nameBrandComparator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		categoryBrand.brands = lsBrands;
		categoryBrand.categories = lstCategory;
		return categoryBrand;
	}
}
