package com.hitechno.sogoods.databases;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hitechno.sogoods.models.Brand;

public class BrandDBAdapter extends DBAdapter {

	// field brand table
	public static final String TAG_TABLE_NAME = "brand_table";
	// brand columns
	public static final String TAG_ID_AUTO = "brand_id_auto";
	public static final String TAG_ID = "brand_id";
	public static final String TAG_NAME = "brand_name";
	public static final String TAG_LOGO_SQUARE = "brand_logo_square";
	public static final String TAG_STATUS = "brand_status";

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

	protected BrandDBAdapter(Context ctx) {
		super(ctx);
		this.mCtx = ctx;
	}

	public BrandDBAdapter open() throws SQLException {
		this.dbHelper = new DatabaseHelper(this.mCtx);
		this.db = this.dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		this.dbHelper.close();
	}

	public boolean create(int brandid, String name, String url, int status) {
		ContentValues values = new ContentValues();
		values.put(TAG_ID, brandid);
		values.put(TAG_NAME, name);
		values.put(TAG_LOGO_SQUARE, url);
		values.put(TAG_STATUS, status);
		return this.db.insert(TAG_TABLE_NAME, null, values) > 0;
	}

	public Brand select(int id) {
		String QUERY = "SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_ID
				+ " = " + id;
		Cursor cursor = db.rawQuery(QUERY, null);
		try {
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				return Brand.initUnitFromCursor(cursor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	public ArrayList<Brand> getAllBrands() {
		ArrayList<Brand> brands = new ArrayList<Brand>();
		String sql = "SELECT * FROM " + TAG_TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					Brand brand = Brand.initUnitFromCursor(cursor);
					if(!brand.name.trim().equals("0") && (brand.name!=null))
					brands.add(brand);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return brands;
	}

	public ArrayList<Brand> getAllBrandsByName(String name) {
		ArrayList<Brand> brands = new ArrayList<Brand>();
		String sql = "SELECT * FROM " + TAG_TABLE_NAME + " WHERE " + TAG_NAME
				+ " like" + "'%" + name + "%'";
		Cursor cursor = db.rawQuery(sql, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					Brand brand = Brand.initUnitFromCursor(cursor);
					brands.add(brand);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return brands;
	}

	public int updateCategory(String id, String name, String url, String status) {
		ContentValues values = new ContentValues();
		values.put(TAG_NAME, name);
		values.put(TAG_LOGO_SQUARE, url);
		values.put(TAG_STATUS, status);
		return this.db.update(TAG_TABLE_NAME, values, TAG_ID + "= ?",
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
				TAG_NAME }, TAG_ID + "= ?",
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
