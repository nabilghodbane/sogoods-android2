package com.hitechno.sogoods.models;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;
import com.hitechno.sogoods.helpers.ConstantsHelper;

public class Search extends Base {

	public Search() {
	}

	@SerializedName("result")
	public String result;

	@SerializedName("model")
	public String model;

	@SerializedName("model_id")
	public int modelId;

	@SerializedName("url")
	public String url;

	public ContentValues getContentValues(String query) {
		ContentValues values = new ContentValues();
		values.put(ConstantsHelper.Search.QUERY, query);
		values.put(ConstantsHelper.Search.RESULT, result);
		values.put(ConstantsHelper.Search.MODEL, model);
		values.put(ConstantsHelper.Search.MODEL_ID, id);
		values.put(ConstantsHelper.Search.URL, url);
		values.put(ConstantsHelper.Search.UPDATED_AT, updatedAt);
		return values;
	}
}
