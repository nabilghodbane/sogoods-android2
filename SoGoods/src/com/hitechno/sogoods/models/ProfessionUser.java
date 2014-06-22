package com.hitechno.sogoods.models;

import android.database.Cursor;
import android.util.SparseArray;

import com.hitechno.sogoods.databases.ProfessionDBAdapter;

public class ProfessionUser {
	public String code;
	public int id;

	public static SparseArray<String> initFromCursor(Cursor paramCursor) {
		SparseArray<String> result = new SparseArray<String>();
		int columnIDIndex = paramCursor
				.getColumnIndex(ProfessionDBAdapter.KEY_ID);
		int columnJobIndex = paramCursor
				.getColumnIndex(ProfessionDBAdapter.KEY_CONTENT);
		if (paramCursor.moveToFirst()) {
			do {
				result.put(paramCursor.getInt(columnIDIndex), paramCursor.getString(columnJobIndex));
			} while (paramCursor.moveToNext());
		}
		return result;
	}
}
