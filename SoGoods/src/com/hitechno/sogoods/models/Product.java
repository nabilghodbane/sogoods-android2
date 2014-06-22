package com.hitechno.sogoods.models;

import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;

import com.hitechno.sogoods.constant.ConstantsHelper;

public class Product {

	public int id;
	public int trueId;
	public String updatedAt;

	public String added_by;
	public int brandId;
	public String brandName;
	public String idLike;
	public int idUser;
	public String likeType;
	public int numberOfComments;
	public int numberOfLikes;
	public String productContent;
	public int productID;
	public String title;
	public String category_name;
	public String typeLike;
	public String url;
	public String urlAvatarSquare;
	public String urlHome;
	public String urlPopup;
	public String content;

	public static Product initUnitFromJson(JSONObject json, String typePhone) {
		Product p = new Product();
		JSONObject imageJson;
		try {
			p.idUser = Integer.parseInt(json.getString("iduser"));
			p.title = json.getString("title");
			p.brandName = json.getString("brand_name");
			p.category_name = json.getString("category_name");
			p.updatedAt = json.getString("date_created");
			p.productContent = json.getString("content");
			p.added_by = json.getString("username");
			p.numberOfComments = json.getInt("nbcomment");
			p.numberOfLikes = json.getInt("nblike");
			if (json.has("content"))
				p.content = json.getString("content");

			if (json.has("product_picture_square")) {
				imageJson = json.getJSONObject("product_picture_square");
				if (typePhone.equals("tablet_large")) {
					if (imageJson.has("0x0"))
						p.urlPopup = imageJson.getString("0x0");
					else if (imageJson.has("320x480"))
						p.urlPopup = imageJson.getString("320x480");
					else if (imageJson.has("125x125"))
						p.urlPopup = imageJson.getString("125x125");
				} else if (typePhone.equals("tablet_normal")) {
					if (imageJson.has("0x0"))
						p.urlPopup = imageJson.getString("0x0");
					else if (imageJson.has("320x480"))
						p.urlPopup = imageJson.getString("320x480");
					else if (imageJson.has("125x125"))
						p.urlPopup = imageJson.getString("125x125");
				} else if (typePhone.equals("phone_xlarge")) {
					if (imageJson.has("0x0"))
						p.urlPopup = imageJson.getString("0x0");
					else if (imageJson.has("640x960"))
						p.urlPopup = imageJson.getString("640x960");
					else if (imageJson.has("640x1136"))
						p.urlPopup = imageJson.getString("640x1136");
				} else if (typePhone.equals("phone_normal"))
					if (imageJson.has("0x0"))
						p.urlPopup = imageJson.getString("0x0");
					else if (imageJson.has("320x568"))
						p.urlPopup = imageJson.getString("320x568");
			}

			if (json.has("current_user_like")) {
				JSONObject likeJson = json.getJSONObject("current_user_like");
				p.idLike = likeJson.getString("idlike");
				p.likeType = likeJson.getString("type");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID, id);
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT,
				updatedAt);
		values.put(ConstantsHelper.Products.TITLE, title);
		values.put(ConstantsHelper.Products.ADDED_BY, added_by);
		values.put(ConstantsHelper.Products.URL, url);
		values.put(ConstantsHelper.Products.TYPE, category_name);
		values.put(ConstantsHelper.Products.NUMBER_OF_LIKES, numberOfLikes);
		values.put(ConstantsHelper.Products.LIKE_TYPE, likeType);
		values.put(ConstantsHelper.Products.NUMBER_OF_COMMENTS,
				numberOfComments);
		values.put(ConstantsHelper.Products.BRAND_ID, brandId);
		return values;
	}

	public String stringFromList(List<Integer> ids) {
		String idsAsString = "(";
		for (int i = 0; i < ids.size(); i++) {
			Integer idAtIndex = ids.get(i);
			idsAsString = idsAsString.concat(Integer.toString(idAtIndex));
			if (i != ids.size() - 1)
				idsAsString = idsAsString
						.concat(ConstantsHelper.SEPARATOR_FOR_IDS_AS_STRING);
		}
		idsAsString = idsAsString.concat(")");
		return idsAsString;
	}

	public ContentValues getContentValuesForSafeUpdate() {
		ContentValues values = new ContentValues();
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID, id);
		values.put(ConstantsHelper.Products.TITLE, title);
		values.put(ConstantsHelper.Products.ADDED_BY, added_by);
		values.put(ConstantsHelper.Products.TYPE, category_name);
		values.put(ConstantsHelper.Products.LIKE_TYPE, likeType);
		values.put(ConstantsHelper.Products.NUMBER_OF_LIKES, numberOfLikes);
		values.put(ConstantsHelper.Products.NUMBER_OF_COMMENTS,
				numberOfComments);
		values.put(ConstantsHelper.Products.BRAND_ID, brandId);
		return values;
	}
}
