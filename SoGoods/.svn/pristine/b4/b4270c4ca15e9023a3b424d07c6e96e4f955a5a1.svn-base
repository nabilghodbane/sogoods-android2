package com.hitechno.sogoods.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hitechno.sogoods.models.ProfessionUser;

public class ProfessionDBAdapter extends DBAdapter {

	// Contacts table name
	public static final String TAG_TABLE_NAME = "profession";

	// Contacts Table Columns names
	public static final String KEY_ID = "id";
	public static final String KEY_CONTENT = "content";

	private final Context mCtx;
	private DatabaseHelper dbHelper;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DBAdapter.DATABASE_NAME, null,
					DBAdapter.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

	protected ProfessionDBAdapter(Context ctx) {
		super(ctx);
		this.mCtx = ctx;
	}

	public ProfessionDBAdapter open() throws SQLException {
		this.dbHelper = new DatabaseHelper(this.mCtx);
		this.db = this.dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		this.dbHelper.close();
	}

	public boolean createProfession(ProfessionUser pro) {
		ContentValues values = new ContentValues();
		values.put(KEY_ID, pro.id);
		values.put(KEY_CONTENT, pro.code);

		return this.db.insert(TAG_TABLE_NAME, null, values) > 0;
	}

	public Cursor selectAll() {
		String QUERY = "SELECT * FROM " + TAG_TABLE_NAME;

		return this.db.rawQuery(QUERY, null);
	}

	public String getCode(int id) {
		String QUERY = "SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + KEY_ID
				+ " = '" + id + "'";

		Cursor cursor = db.rawQuery(QUERY, null);
		try {
			if (cursor != null)
				cursor.moveToFirst();
			return cursor.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	public int updateCode(ProfessionUser pro) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CONTENT, pro.code);
		return this.db.update(TAG_TABLE_NAME, initialValues, KEY_ID + "= ?",
				new String[] { String.valueOf(pro.id) });
	}

	public boolean deleteContent(String id) {
		return this.db.delete(TAG_TABLE_NAME, KEY_ID + "= ?",
				new String[] { id }) > 0;
	}

	public boolean deleteAll() {
		return this.db.delete(TAG_TABLE_NAME, null, null) > 0;
	}

	public boolean containsID(int id) {
		Cursor mCursor = db.query(true, TAG_TABLE_NAME, new String[] { KEY_ID,
				KEY_CONTENT }, KEY_ID + "= ?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		try {
			if (mCursor != null) {
				mCursor.moveToFirst();
			} else {
				return false;
			}
			return mCursor.getCount() > 0;
		} finally {
			if (mCursor != null)
				mCursor.close();
		}
	}
}
