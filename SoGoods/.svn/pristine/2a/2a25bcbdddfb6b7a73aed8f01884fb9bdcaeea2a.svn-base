package com.hitechno.sogoods.databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	public static final String DATABASE_NAME = "sogoods.sqlite";
	public static final int DATABASE_VERSION = 1;

	private static final String CREATE_TABLE_PROFESSION = "CREATE TABLE "
			+ ProfessionDBAdapter.TAG_TABLE_NAME + "("
			+ ProfessionDBAdapter.KEY_ID + " INTEGER PRIMARY KEY,"
			+ ProfessionDBAdapter.KEY_CONTENT + " TEXT" + ");";

	private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
			+ CategoryDBAdapter.TAG_TABLE_NAME + " ("
			+ CategoryDBAdapter.TAG_ID_AUTO
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + CategoryDBAdapter.TAG_ID
			+ " INTEGER, " + CategoryDBAdapter.TAG_TITLE + " TEXT NULL, "
			+ CategoryDBAdapter.TAG_SUBCHAR + " TEXT NULL,"
			+ CategoryDBAdapter.TAG_URL + " TEXT NULL, "
			+ CategoryDBAdapter.TAG_BRAND_LIST + " TEXT NULL, "
			+ CategoryDBAdapter.TAG_STATUS + " TEXT NULL" + ");";

	private static final String CREATE_TABLE_BRAND = "CREATE TABLE "
			+ BrandDBAdapter.TAG_TABLE_NAME + " ("
			+ BrandDBAdapter.TAG_ID_AUTO
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + BrandDBAdapter.TAG_ID
			+ " INTEGER, " + BrandDBAdapter.TAG_NAME + " TEXT NULL, "
			// + BrandDBAdapter.BRAND_CATEGORIES_LIST + " TEXT NULL, "
			+ BrandDBAdapter.TAG_LOGO_SQUARE + " TEXT NULL, "
			+ BrandDBAdapter.TAG_STATUS + " TEXT NULL" + ");";

	private final Context context;
	private DatabaseHelper DBHelper;
	protected SQLiteDatabase db;

	public void beginTransaction() {
		if (db != null) {
			db.beginTransaction();
		}
	}

	public void endTransaction() {
		if (db != null) {
			db.endTransaction();
		}
	}

	public void setTransactionSuccessful() {
		if (db != null) {
			db.setTransactionSuccessful();
		}
	}

	public DBAdapter(Context ctx) {
		this.context = ctx;
		this.DBHelper = new DatabaseHelper(this.context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_PROFESSION);
			db.execSQL(CREATE_TABLE_CATEGORY);
			db.execSQL(CREATE_TABLE_BRAND);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "
					+ ProfessionDBAdapter.TAG_TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS "
					+ CategoryDBAdapter.TAG_TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS "
					+ BrandDBAdapter.TAG_TABLE_NAME);
			onCreate(db);
		}
	}

	/**
	 * open the db
	 * 
	 * @return this
	 * @throws SQLException
	 *             return type: DBAdapter
	 */
	public DBAdapter open() throws SQLException {
		this.db = this.DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * close the db return type: void
	 */
	public void close() {
		this.DBHelper.close();
	}

	public SQLiteDatabase getDatabase() {
		return db;
	}

	public void setDatabase(SQLiteDatabase database) {
		this.db = database;
	}

}