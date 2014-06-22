package com.hitechno.sogoods.models;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.hitechno.sogoods.helpers.ConstantsHelper;

/**
 * Deserializes data that represents the <code>Catalog</code> model.
 * 
 */
public class Catalog extends Base {

	public Catalog() {
	}

	@SerializedName("title")
	public String title;

	@SerializedName("content")
	public String content;

	@SerializedName("url")
	public String url;

	@SerializedName("brand_id")
	public int brandId;

	/**
	 * Transforms the deserialized data into {@link ContentValues}.
	 * 
	 * @return the <code>ContentValues</code> representation of the model
	 */
	public ContentValues getContentValues() {
		ContentValues values = super.getContentValues();
		values.put(ConstantsHelper.Catalogs.TITLE, title);
		values.put(ConstantsHelper.Catalogs.CONTENT, content);
		values.put(ConstantsHelper.Catalogs.URL, url);
		values.put(ConstantsHelper.Catalogs.BRAND_ID, brandId);
		return values;
	}
}
