package com.hitechno.sogoods.models;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.hitechno.sogoods.helpers.ConstantsHelper;

/**
 * Deserializes data that represents the <code>Photo</code> model.
 * 
 */
public class Photo extends Base {

	public Photo() {
	}

	@SerializedName("url")
	public String url;

	@SerializedName("brand_id")
	public int brandId;

	@SerializedName("category_id")
	public int categoryId;

	/**
	 * Transforms the deserialized data into {@link ContentValues}.
	 * 
	 * @return the <code>ContentValues</code> representation of the model
	 */
	public ContentValues getContentValues() {
		ContentValues values = super.getContentValues();
		values.put(ConstantsHelper.Photos.URL, url);
		values.put(ConstantsHelper.Photos.BRAND_ID, brandId);
		values.put(ConstantsHelper.Photos.CATEGORY_ID, categoryId);
		return values;
	}
}
