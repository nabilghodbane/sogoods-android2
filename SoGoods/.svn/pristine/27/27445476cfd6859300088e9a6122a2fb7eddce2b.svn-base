package com.hitechno.sogoods.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "testsogoods.db";
	public static final int DATABASE_VERSION = 1;
	// field categories table
	public static final String CATEGORIES_TABLE_NAME = "category_table";
	public static final String CATEGORIES_ID_AUTO ="category_id_auto";
	public static final String CATEGORIES_ID = "category_id";
	public static final String CATEGORIES_TITLE = "categories_title";
	public static final String CATEGORIES_URL = "categories_url";
	public static final String CATEGORIES_UPDATE_AT ="update_at";
	public static final String CATEGORIES_SUBCHAR = "categories_subchar";
	public static final String CATEGORIES_BRAND_LIST = "categories_brand_list";
	public static final String CATEGORIES_STATUS = "categories_status";
	
	// field brand table
	public static final String BRAND_TABLE_NAME = "brand_table";
	public static final String BRAND_ID_AUTO = "brand_id_auto";
	public static final String BRAND_ID = "brand_id";
	public static final String BRAND_NAME = "brand_name";
//	public static final String BRAND_CATEGORIES_LIST = "brand_categories_list";
	public static final String BRAND_LOGO_SQUARE = "brand_logo_square";
	public static final String BRAND_STATUS = "brand_status";
	
	
	// field brand_current_user;
	public static final String BRAND_CURRENT_USER_TABLE_NAME = "brand_current_user_table";
	public static final String BRAND_CURRENT_ID_AUTO = "brand_current_id_auto";
	public static final String BRAND_CURRENT_ID = "brand_current_id";
	
	// field Profession user
	public static final String PROFILE_PROFESSION_TABLE_NAME = "profile_profession_table";
	public static final String PROFILE_PROFESSION_ID_AUTO = "profile_profession_id_auto";
	public static final String PROFILE_PROFESSION_ID = "profile_profession_id";
	public static final String PROFILE_PROFESSION_CODE = "profile_profession_code";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createCategoriesTable(db);
		createBrandTable(db);
		createBrandCurrentUserTable(db);
		createProfessionUser(db);
	}


	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS "
				+ CATEGORIES_TABLE_NAME
				+ ";");

		database.execSQL("DROP TABLE IF EXISTS "
				+ BRAND_TABLE_NAME
				+ ";");
		
		database.execSQL("DROP TABLE IF EXISTS "
				+ BRAND_CURRENT_USER_TABLE_NAME 
				+ ";");
	
		database.execSQL("DROP TABLE IF EXISTS "
				+ PROFILE_PROFESSION_TABLE_NAME
				+";");
		
		onCreate(database);
	}

	// create categories table
	private void createCategoriesTable(SQLiteDatabase database){
		String createCategoriesTable = "CREATE TABLE "
				+ CATEGORIES_TABLE_NAME + " ("
				+ CATEGORIES_ID_AUTO
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ CATEGORIES_ID +" INT, "
				+  CATEGORIES_TITLE + " TEXT NULL, "
				+ CATEGORIES_SUBCHAR +" TEXT NULL,"
				+ CATEGORIES_URL + " TEXT NULL, " 
				+ CATEGORIES_BRAND_LIST +" TEXT NULL, "
				+ CATEGORIES_STATUS +" TEXT NULL" + ");";
		database.execSQL(createCategoriesTable);

	}
	// create brand table
	private void createBrandTable(SQLiteDatabase database){
		String createBrandsTable = "CREATE TABLE "
				+ BRAND_TABLE_NAME + " ("
				+ BRAND_ID_AUTO
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ BRAND_ID +" INT, "
				+ BRAND_NAME + " TEXT NULL, "
//				+ BRAND_CATEGORIES_LIST + " TEXT NULL, "
				+ BRAND_LOGO_SQUARE + " TEXT NULL, "
				+ BRAND_STATUS +" TEXT NULL" + ");";
		database.execSQL(createBrandsTable);
	}
	
	// create table brand current user
	private void createBrandCurrentUserTable(SQLiteDatabase database){
		String createBrand = "CREATE TABLE "
				+ BRAND_CURRENT_USER_TABLE_NAME + " ("
				+ BRAND_CURRENT_ID_AUTO 
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ BRAND_CURRENT_ID +" INT " +") ;";
		database.execSQL(createBrand);
	}
	
	// create table profession user
	public void createProfessionUser(SQLiteDatabase database){
		String createProfession = "CREATE TABLE "
				+ PROFILE_PROFESSION_TABLE_NAME + " ("
				+ PROFILE_PROFESSION_ID_AUTO
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ PROFILE_PROFESSION_ID +" INT, "
				+ PROFILE_PROFESSION_CODE +" TEXT NULL" +");";
		database.execSQL(createProfession);
	}
}
