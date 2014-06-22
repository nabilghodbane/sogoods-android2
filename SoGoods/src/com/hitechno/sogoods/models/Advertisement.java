package com.hitechno.sogoods.models;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.hitechno.sogoods.helpers.ConstantsHelper;

/**
 * Deserializes data that represents the <code>Advertisement</code> model.
 * 
 */
public class Advertisement extends Base {

	public Advertisement() {
		super();
	}

	@SerializedName("title")
	public String title;

	@SerializedName("content")
	public String content;

	@SerializedName("url")
	public String url;

	// TODO Decide if advertisements should be filtered from their table
	// or filtered from the Products table
	// @SerializedName("filter_type")
	// public String type;

	/**
	 * Transforms the deserialized data into {@link ContentValues}.
	 * 
	 * @return the <code>ContentValues</code> representation of the model
	 */
	public ContentValues getContentValues() {
		ContentValues values = super.getContentValues();
		values.put(ConstantsHelper.Advertisements.TITLE, title);
		values.put(ConstantsHelper.Advertisements.CONTENT, content);
		values.put(ConstantsHelper.Advertisements.URL, url);
		return values;
	}
}
