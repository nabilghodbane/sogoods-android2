package com.hitechno.sogoods.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Product;
import com.hitechno.sogoods.util.MySharePreference;

public class APIProduct {

	private String apiKey;
	private String language;
	private String typePhone;
	private int brandFilter;
	private int categoryFilter;
	private Context context;
	private int FILTER;

	public APIProduct(Context context, String apiKey, String language,
			String typePhone) {
		this.apiKey = apiKey;
		this.language = language;
		this.typePhone = typePhone;
		this.context = context;
	}

	private void getFilter() {
		brandFilter = MySharePreference.getBrandFilter(context);
		categoryFilter = MySharePreference.getBrandFilter(context);
		if (categoryFilter != 0) {
			FILTER = 2;
		} else if (brandFilter != 0) {
			FILTER = 1;
		} else {
			FILTER = 0;
		}
	}

	// new product
	public ArrayList<Product> onLoadNewsProduct(int kmax, int offset, int idcate) {
		getFilter();
		ArrayList<Product> items = new ArrayList<Product>();
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			switch (FILTER) {
			case 2:
				nameValuePairs = ConstantsHelper.Products
						.filterCatepostParamstoParams("new", apiKey, idcate,
								kmax, language, offset);
				break;
			case 1:
				nameValuePairs = ConstantsHelper.Products
						.filterBrandParamstoParams("new", apiKey, idcate, kmax,
								language, offset);
				break;
			case 0:
				nameValuePairs = ConstantsHelper.Products.postParamstoParams(
						"new", apiKey, kmax, language, offset, typePhone);
				break;
			}

			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Products.API_PRODUCT_LIST, nameValuePairs);

			JSONObject jsonObject = new JSONObject(result);
			String strProfile = jsonObject.getString("products");
			JSONArray array = new JSONArray(strProfile);
			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);
				Product pro = new Product();
				pro.productID = object.getInt("idproduct");
				pro.title = object.getString("title");
				pro.added_by = object.getString("username");
				pro.category_name = object.getString("category_name");
				pro.brandName = object.getString("brand_name");
				pro.numberOfComments = object.getInt("nbcomment");
				pro.numberOfLikes = object.getInt("nblike");
				pro.updatedAt = object.getString("date_created");
				pro.idUser = object.getInt("iduser");
				pro.brandId = object.getInt("idbrand");

				JSONObject jsonLike;
				if (object.has("current_user_like")) {
					jsonLike = object.getJSONObject("current_user_like");
					pro.idLike = jsonLike.getString("idlike");
					pro.typeLike = jsonLike.getString("type");
				}

				JSONObject img;
				if (object.has("product_picture_square")) {
					Object object2 = object.get("product_picture_square");
					if (object2 instanceof JSONObject) {
						img = object.getJSONObject("product_picture_square");
						if (typePhone.equals("tablet_large")) {
							if (img.has("0x0")) {
								pro.urlPopup = img.getString("0x0");
							}

							if (img.has("250x250")) {
								pro.urlAvatarSquare = img.getString("250x250");
								pro.urlHome = img.getString("250x250");
							}
						} else if (typePhone.equals("tablet_normal")) {
							if (img.has("0x0")) {
								pro.urlPopup = img.getString("0x0");
							} else if (img.has("320x480")) {
								pro.urlPopup = img.getString("320x480");
							}

							if (img.has("320x480")) {
								pro.urlHome = img.getString("320x480");
							}

							if (img.has("125x125")) {
								pro.urlAvatarSquare = img.getString("125x125");
							}
						} else if (typePhone.equals("phone_xlarge")) {
							if (img.has("250x250")) {
								pro.urlAvatarSquare = img.getString("250x250");
							}
							if (img.has("600x510")) {
								pro.urlHome = img.getString("600x510");
							}

							if (img.has("0x0")) {
								pro.urlPopup = img.getString("0x0");
							} else if (img.has("640x960")) {
								pro.urlPopup = img.getString("640x960");
							} else if (img.has("640x1136")) {
								pro.urlPopup = img.getString("640x1136");
							}
						} else if (typePhone.equals("phone_normal")) {
							if (img.has("125x125")) {
								pro.urlAvatarSquare = img.getString("125x125");
							}
							if (img.has("300x255")) {
								pro.urlHome = img.getString("300x255");
							}
							if (img.has("0x0")) {
								pro.urlPopup = img.getString("0x0");
							} else if (img.has("320x568")) {
								pro.urlPopup = img.getString("320x568");
							}
						}
					}
				}
				items.add(pro);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	// load suggested
	public ArrayList<Product> onLoadSuggestProduct(int kmax, int offset,
			int idcate) {
		ArrayList<Product> items = new ArrayList<Product>();
		getFilter();
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			switch (FILTER) {
			case 2:
				nameValuePairs = ConstantsHelper.Products
						.filterCatepostParamstoParams("suggested", apiKey,
								idcate, kmax, language, offset);
				break;
			case 1:
				nameValuePairs = ConstantsHelper.Products
						.filterBrandParamstoParams("suggested", apiKey, idcate,
								kmax, language, offset);
				break;
			case 0:
				nameValuePairs = ConstantsHelper.Products.postParamstoParams(
						"suggested", apiKey, kmax, Locale.getDefault()
								.getLanguage(), offset, typePhone);
				break;
			}

			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Products.API_PRODUCT_LIST, nameValuePairs);
			try {
				JSONObject jsonObject = new JSONObject(result);
				String strProfile = jsonObject.getString("products");
				JSONArray array = new JSONArray(strProfile);
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					Product pro = new Product();
					pro.productID = object.getInt("idproduct");
					pro.title = object.getString("title");
					pro.added_by = object.getString("username");
					pro.category_name = object.getString("category_name");
					pro.brandName = object.getString("brand_name");
					pro.numberOfComments = object.getInt("nbcomment");
					pro.numberOfLikes = object.getInt("nblike");
					pro.updatedAt = object.getString("date_created");
					pro.brandId = object.getInt("idbrand");

					JSONObject img;
					if (object.has("product_picture_square")) {
						Object object2 = object.get("product_picture_square");
						if (object2 instanceof JSONObject) {
							img = object
									.getJSONObject("product_picture_square");
							if (typePhone.equals("tablet_large")) {
								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								}

								if (img.has("250x250")) {
									pro.urlAvatarSquare = img
											.getString("250x250");
									pro.urlHome = img.getString("250x250");
								}
							} else if (typePhone.equals("tablet_normal")) {
								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								} else if (img.has("320x480")) {
									pro.urlPopup = img.getString("320x480");
								}

								if (img.has("320x480")) {
									pro.urlHome = img.getString("320x480");
								}

								if (img.has("125x125")) {
									pro.urlAvatarSquare = img
											.getString("125x125");
								}

							} else if (typePhone.equals("phone_xlarge")) {
								if (img.has("250x250")) {
									pro.urlAvatarSquare = img
											.getString("250x250");
								}

								if (img.has("600x510")) {
									pro.urlHome = img.getString("600x510");
								}

								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								} else if (img.has("640x960")) {
									pro.urlPopup = img.getString("640x960");
								} else if (img.has("640x1136")) {
									pro.urlPopup = img.getString("640x1136");
								}
							} else if (typePhone.equals("phone_normal")) {
								if (img.has("125x125")) {
									pro.urlAvatarSquare = img
											.getString("125x125");
								}

								if (img.has("300x255")) {
									pro.urlHome = img.getString("300x255");
								}

								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								} else if (img.has("320x568")) {
									pro.urlPopup = img.getString("320x568");
								}
							}
						}
					}
					JSONObject jsonLike;
					if (object.has("current_user_like")) {
						jsonLike = object.getJSONObject("current_user_like");
						pro.idLike = jsonLike.getString("idlike");
						pro.typeLike = jsonLike.getString("type");
					}

					items.add(pro);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	// popular product
	public ArrayList<Product> onLoadPopularProduct(int kmax, int offset,
			int idcate) {
		ArrayList<Product> items = new ArrayList<Product>();
		getFilter();
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			switch (FILTER) {
			case 2:
				nameValuePairs = ConstantsHelper.Products
						.filterCatepostParamstoParams("popular", apiKey,
								idcate, kmax, language, offset);
				break;
			case 1:
				nameValuePairs = ConstantsHelper.Products
						.filterBrandParamstoParams("popular", apiKey, idcate,
								kmax, language, offset);
				break;
			case 0:
				nameValuePairs = ConstantsHelper.Products.postParamstoParams(
						"popular", apiKey, kmax, Locale.getDefault()
								.getLanguage(), offset, typePhone);
				break;
			}
			String result = Connectivity.loadContentFromAPI(
					ConstantsHelper.Products.API_PRODUCT_LIST, nameValuePairs);
			try {
				JSONObject jsonObject = new JSONObject(result);
				String strProfile = jsonObject.getString("products");
				JSONArray array = new JSONArray(strProfile);
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					Product pro = new Product();
					pro.productID = object.getInt("idproduct");
					pro.title = object.getString("title");
					pro.added_by = object.getString("username");
					pro.category_name = object.getString("category_name");
					pro.brandName = object.getString("brand_name");
					pro.numberOfComments = object.getInt("nbcomment");
					pro.numberOfLikes = object.getInt("nblike");
					pro.updatedAt = object.getString("date_created");
					pro.brandId = object.getInt("idbrand");

					JSONObject img;
					if (object.has("product_picture_square")) {
						Object object2 = object.get("product_picture_square");
						if (object2 instanceof JSONObject) {
							img = object
									.getJSONObject("product_picture_square");
							if (typePhone.equals("tablet_large")) {
								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								}

								if (img.has("250x250")) {
									pro.urlAvatarSquare = img
											.getString("250x250");
									pro.urlHome = img.getString("250x250");
								}
							} else if (typePhone.equals("tablet_normal")) {
								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								} else if (img.has("320x480")) {
									pro.urlPopup = img.getString("320x480");
								}

								if (img.has("320x480")) {
									pro.urlHome = img.getString("320x480");
								}

								if (img.has("125x125")) {
									pro.urlAvatarSquare = img
											.getString("125x125");
								}

							} else if (typePhone.equals("phone_xlarge")) {
								if (img.has("250x250")) {
									pro.urlAvatarSquare = img
											.getString("250x250");
								}

								if (img.has("600x510")) {
									pro.urlHome = img.getString("600x510");
								}

								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								} else if (img.has("640x960")) {
									pro.urlPopup = img.getString("640x960");
								} else if (img.has("640x1136")) {
									pro.urlPopup = img.getString("640x1136");
								}
							} else if (typePhone.equals("phone_normal")) {
								if (img.has("125x125")) {
									pro.urlAvatarSquare = img
											.getString("125x125");
								}

								if (img.has("300x255")) {
									pro.urlHome = img.getString("300x255");
								}

								if (img.has("0x0")) {
									pro.urlPopup = img.getString("0x0");
								} else if (img.has("320x568")) {
									pro.urlPopup = img.getString("320x568");
								}
							}
						}
					}
					JSONObject jsonLike;
					if (object.has("current_user_like")) {
						jsonLike = object.getJSONObject("current_user_like");
						pro.idLike = jsonLike.getString("idlike");
						pro.typeLike = jsonLike.getString("type");
					}

					items.add(pro);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

}
