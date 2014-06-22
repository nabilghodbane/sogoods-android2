package com.hitechno.sogoods.databases;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hitechno.sogoods.models.Category;

public class CategoryDBAdapter extends DBAdapter {

	// field categories table
	public static final String TAG_TABLE_NAME = "category_table";
	// column name
	public static final String TAG_ID_AUTO = "category_id_auto";
	public static final String TAG_ID = "category_id";
	public static final String TAG_TITLE = "categories_title";
	public static final String TAG_URL = "categories_url";
	public static final String TAG_UPDATE_AT = "update_at";
	public static final String TAG_SUBCHAR = "categories_subchar";
	public static final String TAG_BRAND_LIST = "categories_brand_list";
	public static final String TAG_STATUS = "categories_status";

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

	protected CategoryDBAdapter(Context ctx) {
		super(ctx);
		this.mCtx = ctx;
	}

	public CategoryDBAdapter open() throws SQLException {
		this.dbHelper = new DatabaseHelper(this.mCtx);
		this.db = this.dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		this.dbHelper.close();
	}

	public boolean create(int idcat, String name, String subchar, String url,
			String brandList, int status) {
		ContentValues values = new ContentValues();
		values.put(TAG_ID, idcat);
		values.put(TAG_TITLE, name);
		values.put(TAG_SUBCHAR, subchar);
		values.put(TAG_URL, url);
		values.put(TAG_BRAND_LIST, brandList);
		values.put(TAG_STATUS, status);

		return this.db.insert(TAG_TABLE_NAME, null, values) > 0;
	}

	public Category getCategoryById(int id) {
		String QUERY = "SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_ID
				+ " = '" + id + "'";

		Cursor cursor = db.rawQuery(QUERY, null);
		try {
			if (cursor != null)
				cursor.moveToFirst();
			return Category.initUnitFromCursor(cursor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	public Category getCategoryByIdAndStatus(int id, int status) {
		String QUERY = "SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_ID
				+ " = '" + id + "'" + " AND " + TAG_STATUS + " = " + status;

		Cursor cursor = db.rawQuery(QUERY, null);
		try {
			if (cursor != null)
				cursor.moveToFirst();
			return Category.initUnitFromCursor(cursor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	// get all categories
	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM " + TAG_TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					Category category = Category.initUnitFromCursor(cursor);
					categories.add(category);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return categories;
	}

	// get all categories
	public ArrayList<Category> getAllCategoriesWithBrand(int brandID) {
		ArrayList<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM " + TAG_TABLE_NAME + " WHERE "
				+ TAG_BRAND_LIST + " LIKE '%" + brandID + "%'";
		Cursor cursor = db.rawQuery(sql, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					Category category = Category.initUnitFromCursor(cursor);
					categories.add(category);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return categories;
	}

	public int updateCategory(String id, String name, String url,
			String brandList, String status) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(TAG_TITLE, name);
		initialValues.put(TAG_URL, url);
		initialValues.put(TAG_BRAND_LIST, brandList);
		initialValues.put(TAG_STATUS, status);
		return this.db.update(TAG_TABLE_NAME, initialValues, TAG_ID + "= ?",
				new String[] { id });
	}

	public boolean delete(String id) {
		return this.db.delete(TAG_TABLE_NAME, TAG_ID + "= ?",
				new String[] { id }) > 0;
	}

	public boolean deleteAll() {
		return this.db.delete(TAG_TABLE_NAME, null, null) > 0;
	}

	public boolean containsID(int id) {
		Cursor mCursor = db.query(true, TAG_TABLE_NAME, new String[] { TAG_ID,
				TAG_TITLE }, TAG_ID + "= ?",
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
