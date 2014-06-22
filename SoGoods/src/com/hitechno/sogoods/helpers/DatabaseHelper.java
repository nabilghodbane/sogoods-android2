package com.hitechno.sogoods.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Creates and upgrades a SQLite database.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		createAdvertisementsTable(database);
		createBrandsTable(database);
		createCatalogsTable(database);
		createCategoriesTable(database);
		createCommentsTable(database);
		createNewsTable(database);
		createPhotosTable(database);
		createProductsTable(database);
		createProfilesTable(database);
		createQuestionsTable(database);
		createSearchTable(database);
		createStoresTable(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Advertisements.ADVERTISEMENTS_TABLE_NAME
				+ ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Brands.BRANDS_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Catalogs.CATALOGS_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Categories.CATEGORIES_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Comments.COMMENTS_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.News.NEWS_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Photos.PHOTOS_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Products.PRODUCTS_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Profiles.PROFILES_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Questions.QUESTIONS_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXISTS "
				+ ConstantsHelper.Search.SEARCH_TABLE_NAME + ";");
		database.execSQL("DROP TABLE IF EXITS "
				+ ConstantsHelper.Stores.STORES_TABLE_NAME + ";");

		createAdvertisementsTable(database);
		createBrandsTable(database);
		createCatalogsTable(database);
		createCategoriesTable(database);
		createCommentsTable(database);
		createNewsTable(database);
		createPhotosTable(database);
		createProductsTable(database);
		createProfilesTable(database);
		createQuestionsTable(database);
		createSearchTable(database);
		createStoresTable(database);
	}

	private void createAdvertisementsTable(SQLiteDatabase database) {
		String createAdvertisementsTable = "CREATE TABLE "
				+ ConstantsHelper.Advertisements.ADVERTISEMENTS_TABLE_NAME
				+ " (" + ConstantsHelper.Advertisements._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.Advertisements.TITLE + " TEXT, "
				+ ConstantsHelper.Advertisements.CONTENT + " TEXT, "
				+ ConstantsHelper.Advertisements.URL + " TEXT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createAdvertisementsTable);
	}

	private void createBrandsTable(SQLiteDatabase database) {
		String createBrandsTable = "CREATE TABLE "
				+ ConstantsHelper.Brands.BRANDS_TABLE_NAME + " ("
				+ ConstantsHelper.Brands._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Brands.NAME + " TEXT, "
				+ ConstantsHelper.Brands.URL + " TEXT, "
				+ ConstantsHelper.Brands.NUMBER_OF_PROFILES + " INT, "
				+ ConstantsHelper.Brands.PROFILE_IDS + " TEXT, "
				+ ConstantsHelper.Brands.IS_FAN + " INT, "
				+ ConstantsHelper.Brands.CATEGORY_ID + " INT, "
				+ ConstantsHelper.Brands.STORE_IDS + " TEXT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createBrandsTable);
	}

	private void createCatalogsTable(SQLiteDatabase database) {
		String createCatalogsTable = "CREATE TABLE "
				+ ConstantsHelper.Catalogs.CATALOGS_TABLE_NAME + " ("
				+ ConstantsHelper.Catalogs._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Catalogs.TITLE + " TEXT, "
				+ ConstantsHelper.Catalogs.CONTENT + " TEXT, "
				+ ConstantsHelper.Catalogs.URL + " TEXT, "
				+ ConstantsHelper.Catalogs.BRAND_ID + " INT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createCatalogsTable);
	}

	private void createCategoriesTable(SQLiteDatabase database) {
		String createCategoriesTable = "CREATE TABLE "
				+ ConstantsHelper.Categories.CATEGORIES_TABLE_NAME + " ("
				+ ConstantsHelper.Categories._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Categories.TITLE + " TEXT, "
				+ ConstantsHelper.Categories.URL + " TEXT, "
				+ ConstantsHelper.Categories.CATEGORIES + " TEXT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createCategoriesTable);
	}

	private void createCommentsTable(SQLiteDatabase database) {
		String createCommentsTable = "CREATE TABLE "
				+ ConstantsHelper.Comments.COMMENTS_TABLE_NAME + " ("
				+ ConstantsHelper.Comments._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Comments.BODY + " TEXT, "
				+ ConstantsHelper.Comments.PRODUCT_ID + " INT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createCommentsTable);
	}

	private void createNewsTable(SQLiteDatabase database) {
		String createNewsTable = "CREATE TABLE "
				+ ConstantsHelper.News.NEWS_TABLE_NAME + " ("
				+ ConstantsHelper.News._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.News.TITLE + " TEXT, "
				+ ConstantsHelper.News.CONTENT + " TEXT, "
				+ ConstantsHelper.News.URL + " TEXT, "
				+ ConstantsHelper.News.BRAND_ID + " INT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createNewsTable);
	}

	private void createPhotosTable(SQLiteDatabase database) {
		String createPhotosTable = "CREATE TABLE "
				+ ConstantsHelper.Photos.PHOTOS_TABLE_NAME + " ("
				+ ConstantsHelper.Photos._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Photos.URL + " TEXT, "
				+ ConstantsHelper.Photos.BRAND_ID + " INT, "
				+ ConstantsHelper.Photos.CATEGORY_ID + " INT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createPhotosTable);
	}

	private void createProductsTable(SQLiteDatabase database) {
		String createProductsTable = "CREATE TABLE "
				+ ConstantsHelper.Products.PRODUCTS_TABLE_NAME + " ("
				+ ConstantsHelper.Products._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Products.TITLE + " TEXT, "
				+ ConstantsHelper.Products.ADDED_BY + " TEXT, "
				+ ConstantsHelper.Products.URL + " TEXT, "
				+ ConstantsHelper.Products.TYPE + " TEXT, "
				+ ConstantsHelper.Products.NUMBER_OF_LIKES + " INT, "
				+ ConstantsHelper.Products.NUMBER_OF_COMMENTS + " INT, "
				+ ConstantsHelper.Products.LIKE_TYPE + " INT, "
				+ ConstantsHelper.Products.BRAND_ID + " INT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createProductsTable);
	}

	private void createProfilesTable(SQLiteDatabase database) {
		String createProfilesTable = "CREATE TABLE "
				+ ConstantsHelper.Profiles.PROFILES_TABLE_NAME + " ("
				+ ConstantsHelper.Profiles._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Profiles.EMAIL + " TEXT, "
				+ ConstantsHelper.Profiles.PASSWORD + " TEXT, "
				+ ConstantsHelper.Profiles.FIRST_NAME + " TEXT, "
				+ ConstantsHelper.Profiles.LAST_NAME + " TEXT, "
				+ ConstantsHelper.Profiles.USERNAME + " TEXT, "
				+ ConstantsHelper.Profiles.DATE_OF_BIRTH + " TEXT, "
				+ ConstantsHelper.Profiles.CAREER + " TEXT, "
				+ ConstantsHelper.Profiles.URL + " TEXT, "
				+ ConstantsHelper.Profiles.TYPE + " TEXT, "
				+ ConstantsHelper.Profiles.NUMBER_OF_BRANDS + " INT, "
				+ ConstantsHelper.Profiles.BRAND_IDS + " TEXT, "
				+ ConstantsHelper.Profiles.NUMBER_OF_PRODUCTS + " INT, "
				+ ConstantsHelper.Profiles.PRODUCT_IDS + " TEXT, "
				+ ConstantsHelper.Profiles.NUMBER_OF_PHOTOS + " INT, "
				+ ConstantsHelper.Profiles.PHOTO_IDS + " TEXT, "
				+ ConstantsHelper.Profiles.NUMBER_OF_FOLLOWS + " INT, "
				+ ConstantsHelper.Profiles.FOLLOW_IDS + " TEXT, "
				+ ConstantsHelper.Profiles.FOLLOWING_IDS + " TEXT, "
				+ ConstantsHelper.Profiles.NUMBER_OF_FOLLOWINGS + " INT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createProfilesTable);
	}

	private void createQuestionsTable(SQLiteDatabase database) {
		String createQuestionsTable = "CREATE TABLE "
				+ ConstantsHelper.Questions.QUESTIONS_TABLE_NAME + " ("
				+ ConstantsHelper.Questions._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Questions.QUESTION + " TEXT, "
				+ ConstantsHelper.Questions.URL + " TEXT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createQuestionsTable);
	}

	private void createSearchTable(SQLiteDatabase database) {
		String createSearchTable = "CREATE TABLE "
				+ ConstantsHelper.Search.SEARCH_TABLE_NAME + " ("
				+ ConstantsHelper.Search._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.Search.QUERY + " TEXT, "
				+ ConstantsHelper.Search.RESULT + " TEXT, "
				+ ConstantsHelper.Search.MODEL + " TEXT, "
				+ ConstantsHelper.Search.MODEL_ID + " INT, "
				+ ConstantsHelper.Search.URL + " TEXT, "
				+ ConstantsHelper.Search.UPDATED_AT + " TEXT" + ");";
		database.execSQL(createSearchTable);
	}

	private void createStoresTable(SQLiteDatabase database) {
		String createStoresTable = "CREATE TABLE "
				+ ConstantsHelper.Stores.STORES_TABLE_NAME + " ("
				+ ConstantsHelper.Stores._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID
				+ " INT, " + ConstantsHelper.Stores.NAME + " TEXT, "
				+ ConstantsHelper.Stores.ADDRESS + " TEXT, "
				+ ConstantsHelper.Stores.ADDRESS_URL + " TEXT, "
				+ ConstantsHelper.Stores.LATITUDE + " REAL, "
				+ ConstantsHelper.Stores.LONGITUDE + " REAL, "
				+ ConstantsHelper.Stores.BRAND_IDS + " TEXT, "
				+ ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT
				+ " TEXT" + ");";
		database.execSQL(createStoresTable);
	}
}