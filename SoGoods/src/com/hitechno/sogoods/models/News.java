package com.hitechno.sogoods.models;

import java.io.Serializable;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.hitechno.sogoods.helpers.ConstantsHelper;

/**
 * Deserializes data that represents the <code>News</code> model.
 * 
 */
public class News extends Base implements Serializable{

	public News() {
	}

	@SerializedName("title")
	public String title;

	@SerializedName("content")
	public String content;

	@SerializedName("url")
	public String url;

	@SerializedName("brand_id")
	public int brandId;
	
	@SerializedName ("resume")
	public String resume;
	
	@SerializedName("date_create")
	public String dateCreate;
	
	@SerializedName("date_published")
	public String datePublished;
	
	@SerializedName("current_user_view")
	public int currentUserView;
	
	@SerializedName("iduser")
	public String iduser;
	
	@SerializedName("idnews")
	public int idnews;
	
	@SerializedName("url_large")
	public String urlLarge;

	/**
	 * Transforms the deserialized data into {@link ContentValues}.
	 * 
	 * @return the <code>ContentValues</code> representation of the model
	 */
	public ContentValues getContentValues() {
		ContentValues values = super.getContentValues();
		values.put(ConstantsHelper.News.TITLE, title);
		values.put(ConstantsHelper.News.CONTENT, content);
		values.put(ConstantsHelper.News.URL, url);
		values.put(ConstantsHelper.News.BRAND_ID, brandId);
		return values;
	}
}
