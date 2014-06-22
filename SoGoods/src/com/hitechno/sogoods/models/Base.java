package com.hitechno.sogoods.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.hitechno.sogoods.helpers.ConstantsHelper;

/**
 * Deserializes JSON data that contains an "updated_at" key. This is subclassed
 * by any model that requires an update from a remote resource. This class
 * provides the <code>id</code> and <code>updatedAt</code> instance variables.
 */
public class Base {
//	private final SimpleDateFormat dateParser = new SimpleDateFormat(
//			ConstantsHelper.DATE_FORMAT, ConstantsHelper.LOCALE);
	private final SimpleDateFormat dateParser = new SimpleDateFormat(
			ConstantsHelper.DATE_FORMAT_NEW, ConstantsHelper.LOCALE);

	public int id;
	public int trueId;

	@SerializedName("updated_at")
	public String updatedAt;

	public Base() {
		super();
	}

	/**
	 * Parses a date that is formatted using ISO8601 format. This format is of
	 * the form:
	 * <p>
	 * <code>"yyyy-MM-ddTHH:mm:ssZ"</code>
	 * 
	 * @return the date representation of <code>updatedAt</code>
	 */
	public Date getUpdatedAt() {
		Date mostRecent = null;
		try {
			mostRecent = dateParser.parse(updatedAt);
		} catch (ParseException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
					+ e.getMessage());
		}
		return mostRecent;
	}

	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID, id);
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT,
				updatedAt);
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}