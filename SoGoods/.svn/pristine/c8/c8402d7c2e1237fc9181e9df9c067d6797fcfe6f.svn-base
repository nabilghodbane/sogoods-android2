package com.hitechno.sogoods.models;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.hitechno.sogoods.helpers.ConstantsHelper;

public class Comment extends Base {

	public Comment() { }

	@SerializedName("body")
	public String body;
	
	@SerializedName("product_id")
	public int productId;
	
	// TODO hongmophi
	@SerializedName("user_id")
	public int profileID;
	
	@SerializedName("comment_id")
	public int commentID;
	
	@SerializedName("date_create")
	public String dateCreate;
	
	@SerializedName("url")
	public String url;
	
	public ContentValues getContentValues() {
		ContentValues values = super.getContentValues();
		values.put(ConstantsHelper.Comments.BODY, body);
		values.put(ConstantsHelper.Comments.PRODUCT_ID, productId);
		return values;
	}
}
