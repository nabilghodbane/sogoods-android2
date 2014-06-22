package com.hitechno.sogoods.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gms.internal.bs;
import com.google.android.gms.internal.cn;
import com.hitechno.sogoods.R;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Contains the application's global and model-related constants.
 * 
 * The global constants include a log tag, encoding, date format,
 * locale, database name, database version, the protocol and domain
 * for HTTP requests, the name of all database columns for models that
 * store assets ("url").
 * <p>
 * The global constants also include enumerated values for each model.
 * This <code>enum</code> is used to identify which model should be
 * assigned to either an HTTP request, asset download, or asset
 * caching. The <code>enum</code> is used in another global constant, 
 * a <code>HashMap</code> called MODELS.
 * <p>
 * For a model, the constants include the related database table name,
 * directory name for storing any related assets, e.g., images, the
 * authority for a {@link ContentProvider}, the URLs for obtaining its
 * data, and its schema for database columns.
 * <p>
 * Models extend {@link BaseColumns} which eliminates the need to
 * specify a <code>_id</code> member; this member is inherited.
 * <p>
 * Models also have their own serialization/deserialization classes,
 * located in <code>com.hitechno.sogoods.models</code>. These classes
 * match the remote database column names with the names of the database
 * columns in this application's SQLite database. When producing a
 * {@link ContentValues} object these classes reference the constants in
 * each model located in this class.
 *
 */
public class ConstantsHelper {

	/*
	 * Begin Development constants
	 */

	/*
	 * End development constants
	 */

	public static final String LOG_TAG = "SoGoods";
	public static final String ENCODING = "UTF-8";
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public static final Locale LOCALE = Locale.US;
	public static final String START_DATE = "2013-01-01T00:00:00";

	// TODO 
	public static final String DATE_FORMAT_NEW = "dd/MM/yyyy";

	public static final String CACHE_KEY_FOR_IMAGE_PLACEHOLDER = "http";
	public static final String IMAGE_PLACEHOLDER = "image_placeholder.png";

	public static final String DATABASE_NAME = "sogoods.db";
	public static int DATABASE_VERSION = 2;

	public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_ID = "_id";
	public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID = "true_id";
	public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_ASSET_URL = "url";
	public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT = "updated_at";

	public static final String SEPARATOR_FOR_IDS_AS_STRING = ",";

	public static final String EMAIL_REGEX = "(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)";
//	public static final int VALID_INPUT_LENGTH = 3;
	public static final int VALID_INPUT_LENGTH = 4;
	
	/* Indices for PostParams that create a product */
	public static final int CATEGORY_ID_INDEX = 0;
	public static final int BRAND_ID_INDEX = 1;
	public static final int NAME_INDEX = 2;
	public static final int DESCRIPTION_INDEX = 3;
	public static final int PROFILE_ID_INDEX = 4;
	public static final int LIKE_INDEX = 5;

	/* Keys for PostParams that create a product */
	public static final String PARAM_CATEGORY_ID = "category_id";
	public static final String PARAM_BRAND_ID = "brand_id";
	public static final String PARAM_PRODUCT_TITLE = "title";
	public static final String PARAM_PRODUCT_DESCRIPTION = "description";
	public static final String PARAM_PROFILE_ID = "profile_id";
	public static final String PARAM_LIKE_TYPE = "like_type";
	public static final String PARAM_FILE = "file";

	public static final int NUMBER_OF_PRODUCT_PARAMS = 6;

	/* Indices for PostParams that create a profile */
	public static final int TITLE_INDEX = 0;
	public static final int FIRSTNAME_INDEX = 1;
	public static final int LASTNAME_INDEX = 2;
	public static final int USERNAME_INDEX = 3;
	public static final int EMAIL_INDEX = 4;
	public static final int PASSWORD_INDEX = 5;
	public static final int LOCATION_INDEX = 6;
	public static final int BIRTHDAY_INDEX = 7;
	public static final int CAREER_INDEX = 8;
	public static final int QUESTIONS_INDEX = 9;

	/* Keys for PostParams that create or update a profile */
	public static final String PARAM_TITLE = "title";
	public static final String PARAM_FIRSTNAME = "firstname";
	public static final String PARAM_LASTNAME = "lastname";
	public static final String PARAM_USERNAME = "username";
	public static final String PARAM_EMAIL = "email";
	public static final String PARAM_PASSWORD = "password";
	public static final String PARAM_LOCATION = "location";
	public static final String PARAM_BIRTHDAY = "birthday";
	public static final String PARAM_CAREER = "career";
	public static final String PARAM_QUESTIONS = "questions";

	public static final int NUMBER_OF_SIGNUP_PARAMS = 10;
	public static final String QUESTIONS_SEPARATOR = "***";
	public static final int NUMBER_OF_UPDATE_PARAMS = 9;

	/*
	 * Animation
	 */
	public static final int SHORT_DURATION = 250;

	/*
	 * Browser
	 */
	public static final String BROWSER_PACKAGE = "com.android.browser";
	public static final String BROWSER_CLASS = "com.android.browser.BrowserActivity";

	/*
	 * Fragments
	 */
	public static final int SIGNIN_FRAGMENT = 0;
	public static final int SIGNUP_PAGE_ONE_FRAGMENT = 1;
	public static final int SIGNUP_PAGE_TWO_FRAGMENT = 2;
	public static final int NUMBER_OF_ACCOUNT_FRAGMENTS = 3;
	public static final int MY_PROFILE_FRAGMENT = 0;
	public static final int ADD_PRODUCTS_FRAGMENT = 1;
	public static final int MY_ACCOUNT_FRAGMENT = 2;
	public static final int NUMBER_OF_MY_PROFILE_FRAGMENTS = 3;

	public static final String LIKE_DIALOG_FRAGMENT_TAG = "like_dialog";

	/*
	 * Loaders
	 */
	public static final int ADVERTISEMENTS_LOADER = 0;
	public static final int BRAND_LOADER = 1; // Not included in MODELS
	public static final int BRANDS_LOADER = 2;
	public static final int CATALOGS_LOADER = 3;
	public static final int CATEGORIES_LOADER = 4;
	public static final int COMMENTS_LOADER = 5; // Not included in MODELS
	public static final int MY_PROFILE_LOADER = 6;
	public static final int NEWS_LOADER = 7;
	public static final int PHOTOS_LOADER = 8;
	public static final int PRODUCTS_LOADER = 9;
	public static final int PRODUCT_LOADER = 10; // Not included in MODELS
	public static final int PROFILE_LOADER = 11; // Not included in MODELS
	public static final int PROFILES_LOADER = 12;
	public static final int FOLLOWS_LOADER = 13;
	public static final int FOLLOWINGS_LOADER = 14;
	public static final int QUESTIONS_LOADER = 15;
	public static final int STORES_LOADER = 16;
	public static final int SEARCH_LOADER = 17;

	/*
	 * Models
	 */
	public static final int ADVERTISEMENTS_VALUE = 0;
	public static final int BRANDS_VALUE = 1;
	public static final int CATALOGS_VALUE = 2;
	public static final int CATEGORIES_VALUE = 3;
	public static final int COMMENTS_VALUE = 4;
	public static final int LIKES_VALUE = 5;
	public static final int NEWS_VALUE = 6;
	public static final int PHOTOS_VALUE = 7;
	public static final int PRODUCTS_VALUE = 8;
	public static final int PROFILES_VALUE = 9;
	public static final int PROFILE_FOLLOWS_VALUE = 10;
	public static final int PROFILE_FOLLOWINGS_VALUE = 11;
	public static final int QUESTIONS_VALUE = 12;
	public static final int SESSIONS_VALUE = 13;
	public static final int STORES_VALUE = 14;
	public static final int HOME_VALUE = 15;
	public static final int ID_PROFILE = 16;

	public static final Map<String, Integer> MODELS = new HashMap<String, Integer>();
	static {
		MODELS.put(ConstantsHelper.Advertisements.ADVERTISEMENTS_TABLE_NAME, ConstantsHelper.ADVERTISEMENTS_VALUE);
		MODELS.put(ConstantsHelper.Brands.BRANDS_TABLE_NAME, ConstantsHelper.BRANDS_VALUE);
		MODELS.put(ConstantsHelper.Catalogs.CATALOGS_TABLE_NAME, ConstantsHelper.CATALOGS_VALUE);
		MODELS.put(ConstantsHelper.Categories.CATEGORIES_TABLE_NAME, ConstantsHelper.CATEGORIES_VALUE);
		MODELS.put(ConstantsHelper.Comments.COMMENTS_TABLE_NAME, ConstantsHelper.COMMENTS_VALUE);
		MODELS.put(ConstantsHelper.Likes.LIKES_RESOURCE, ConstantsHelper.LIKES_VALUE);
		MODELS.put(ConstantsHelper.News.NEWS_TABLE_NAME, ConstantsHelper.NEWS_VALUE);
		MODELS.put(ConstantsHelper.Photos.PHOTOS_TABLE_NAME, ConstantsHelper.PHOTOS_VALUE);
		MODELS.put(ConstantsHelper.Products.PRODUCTS_TABLE_NAME, ConstantsHelper.PRODUCTS_VALUE);
		MODELS.put(ConstantsHelper.Profiles.PROFILES_TABLE_NAME, ConstantsHelper.PROFILES_VALUE);
		MODELS.put(ConstantsHelper.Profiles.PROFILES_FOLLOWS_MODELS_KEY_NAME, ConstantsHelper.PROFILE_FOLLOWS_VALUE);
		MODELS.put(ConstantsHelper.Profiles.PROFILES_FOLLOWINGS_MODELS_KEY_NAME, ConstantsHelper.PROFILE_FOLLOWINGS_VALUE);
		MODELS.put(ConstantsHelper.Questions.QUESTIONS_TABLE_NAME, ConstantsHelper.QUESTIONS_VALUE);
		MODELS.put(ConstantsHelper.Sessions.SESSIONS, ConstantsHelper.SESSIONS_VALUE);
		MODELS.put(ConstantsHelper.Stores.STORES_TABLE_NAME, ConstantsHelper.STORES_VALUE);

		MODELS.put(ConstantsHelper.Home.API_KEY, ConstantsHelper.HOME_VALUE);
		MODELS.put(ConstantsHelper.Home.ID_PROFILE, ConstantsHelper.ID_PROFILE);
	}

	/*
	 * Pager
	 */
	public static final String PAGE_POSITION = "page";

	/*
	 * Photos
	 */
	public static final int ACTIVITY_RESULT_DID_SELECT_IMAGE = 0;
	public static final int ACTIVITY_RESULT_DID_PROCESS_IMAGE = 1;

	/*
	 * Requests
	 */
	//	public static final String BASE_URL = "http://webservices.sogoods.alex/v1";
	public static final String BASE_URL ="http://webservices.preprod.sogoods.hitechno-ltd.com";

	public enum Methods { GET, POST, PUT, DELETE }

	public static final class StatusCodes {
		public static final int OK = 200;
		public static final int FAILURE = 500;
	}

	public static final String HEADER_MODEL_KEY = "X-SoGoods-Model-Id";
	public static final String HEADER_ERROR_KEY = "X-SoGoods-Error-Message";

	/*
	 * Screen
	 */
	public static int NUMBER_OF_COLUMNS_IN_GRID = -1;
	private static final int IMAGE_WIDTH = 128;

	public static void setNumberOfColumnsInGrid(Context context) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
		int width = displayMetrics.widthPixels;
		NUMBER_OF_COLUMNS_IN_GRID = (int) Math.floor(width/IMAGE_WIDTH);
		Log.i("aaaaaaa","dfhgdsfg "+NUMBER_OF_COLUMNS_IN_GRID);
	}

	/*
	 * Search
	 */
	public static final String SEARCH_LOADER_TYPE = "search_loader_type";

	/*
	 * Text
	 */
	public static final int NUMBER_OF_LINES_FOR_DETAIL_LIST_ITEM = 2;

	/**
	 * Contains the constants for an Advertisement model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class Advertisements implements BaseColumns {

		public static final String ADVERTISEMENTS_TABLE_NAME = "advertisements";
		public static final String ADVERTISEMENTS_DIRECTORY_NAME = "advertisements";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.advertisementcontentprovider";
		public static final String ADVERTISEMENT = "advertisement";

		public static final Uri ADVERTISEMENTS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Advertisements.ADVERTISEMENT);
		public static final Uri ADVERTISEMENT_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Advertisements.ADVERTISEMENT + "/1");

		public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/advertisements.json";

		/**
		 * The MIME type of a {@link ContentUri} of an advertisement.
		 */
		public static final String CONTENT_ADVERTISEMENT_TYPE =
				"application/json";

		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		public static final String URL = "url";
		public static final String TYPE = "type";
	}

	/**
	 * Contains the constants for a Brand model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class Brands implements BaseColumns {

		public static final String BRANDS_TABLE_NAME = "brands";
		public static final String BRANDS_DIRECTORY_NAME = "brands";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.brandcontentprovider";
		public static final String BRAND = "brand";

		public static final Uri BRANDS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Brands.BRAND);
		/*public static final Uri BRAND_CONTENT_URI = Uri.parse("content://" +
                AUTHORITY + "/" + Brands.BRAND + "/1");*/
		public static final Uri BRAND_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Brands.BRAND);

		/**
		 * The MIME type of a {@link ContentUri} of a brand.
		 */
		public static final String CONTENT_BRAND_TYPE =
				"application/json";

		public static final String NAME = "name";
		public static final String URL = "url";
		public static final String NUMBER_OF_PROFILES = "number_of_fans";
		public static final String PROFILE_IDS = "profile_ids";
		public static final String IS_FAN = "is_fan";
		public static final String CATEGORY_ID = "category_id";
		public static final String STORE_IDS = "store_ids";

		public static final String BRAND_STORES_FRAGMENT_TAG = "BRAND_STORES_FRAGMENT";

		// TODO hongmophi
		//end


		//        public static final String SHOW_URL = ConstantsHelper.BASE_URL + "/brands/";
		//        public static final String SEARCH_URL = ConstantsHelper.BASE_URL + "/brands/search";
		public static final String SHOW_URL = ConstantsHelper.BASE_URL + "/v.0.1/brands/";
		public static final String SEARCH_URL = ConstantsHelper.BASE_URL + "/v.0.1/brands/search";

		public static final String API_BRAND_LIST = ConstantsHelper.BASE_URL+"/v.0.1/brands/list.json";
		public static final String API_BRAND_GET = ConstantsHelper.BASE_URL+"/v.0.1/brands/get.json";
		public static final String API_GOOGLE_PLAY = "http://maps.googleapis.com/maps/v.0.1/geocode/json?sensor=false";
		public static final String API_PROFILE_BRAND_ADD = ConstantsHelper.BASE_URL+"/v.0.1/profile/add_brand.json";
		public static final String API_PROFILE_BRAND_DELETE =ConstantsHelper.BASE_URL+"/v.0.1/profile/delete_brand.json";
		// new api 
		public static final String API_PROFILE_BRAND_LIST =ConstantsHelper.BASE_URL+"/v.0.1/profile/get_brands.json";
		public static final String API_SUGGESTED_BRAND_LIST = ConstantsHelper.BASE_URL+"/v.0.1/brands/get_by_category.json";
//		public static final String API_GET_MINIMIZED_LIST = ConstantsHelper.BASE_URL+"/v.0.1/brands/get_minimized_list.json";
		public static final String API_BRAND_GET_MINIMIZED_LIST = ConstantsHelper.BASE_URL+"/v.0.1/brands/get_minimized_list.json";
		public static final String API_PROFILE_GET_BRAND_SUGGESTED =ConstantsHelper.BASE_URL +"/v.0.1/profile/get_brands_suggested.json";
		public static final String API_GET_BY_CATEGORY = ConstantsHelper.BASE_URL+"/v.0.1/brands/get_by_category.json";
		
		public static final String API_GENERAL_GET_UPDATED_INFO = ConstantsHelper.BASE_URL+"/v.0.1/general/get_updated_informations.json";
		
		/* Indexing stores */
		public static String CreateIndexUrlForStores(int brandId) {
			return ConstantsHelper.BASE_URL + "/brands/" + Integer.toString(brandId) + "/stores.json";
		}

		/* Indexing products */
		public static String CreateIndexUrlForProducts(int brandId) {
			return ConstantsHelper.BASE_URL + "/brands/" + Integer.toString(brandId) + "/products.json";
		}

		/* Indexing news */
		public static String CreateIndexUrlForNews(int brandId) {
			return ConstantsHelper.BASE_URL + "/brands/" + Integer.toString(brandId) + "/news.json";
		}

		/* Indexing photos */
		public static String CreateIndexUrlForPhotos(int brandId) {
			return ConstantsHelper.BASE_URL + "/brands/" + Integer.toString(brandId) + "/photos.json";
		}

		/* Indexing catalogs */
		public static String CreateIndexUrlForCatalogs(int brandId) {
			return ConstantsHelper.BASE_URL + "/brands/" + Integer.toString(brandId) + "/catalogs.json";
		}

		public static List<NameValuePair> postParamstoParams(int idprofile, String apikey, int offset, int maxresults, String language){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idprofile", String.valueOf(idprofile)));
			pairs.add(new BasicNameValuePair("apikey", apikey));
			pairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			pairs.add(new BasicNameValuePair("language", language));
			pairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			return pairs;
		}
		
		// new load fan by the new api
		public static List<NameValuePair> new_loadFansByProfile(int idprofile, String apikey, String language){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idprofile", String.valueOf(idprofile)));
			pairs.add(new BasicNameValuePair("apikey", apikey));
			pairs.add(new BasicNameValuePair("language", language));
			return pairs;
		}

		public static List<NameValuePair> getAllBrandPosttoParam(String api){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("apikey", api));
			return pairs;
		}
		
		// get all Brand (Quiz)
		public static List<NameValuePair> getAllBrandToParam(String api,String lang){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			return pairs;
		}
		// get item brand
		public static List<NameValuePair> getItemBrandPosttoParam(String api, String language,int brandid, String type, double latitude, double longtitude){
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("apikey", api));
			nameValuePairs.add(new BasicNameValuePair("language", language));
			nameValuePairs.add(new BasicNameValuePair("idbrand", String.valueOf(brandid)));
			nameValuePairs.add(new BasicNameValuePair("type", type));
			nameValuePairs.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));
			nameValuePairs.add(new BasicNameValuePair("longitude", String.valueOf(longtitude)));
			return nameValuePairs;
		}

		// load brand(store locator) by id Categories
		public static List<NameValuePair> getDetailCatePostToParams(String api, int catID, String lang,int maxresult, double latitude, double longtitude){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("idcategory", String.valueOf(catID)));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("maxresults", String.valueOf(maxresult)));
			lst.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));
			lst.add(new BasicNameValuePair("longitude", String.valueOf(longtitude)));
			return lst;
		}

		// get latitude, longtitude
		public static List<NameValuePair> getLongtitudetoParams(String cityName){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			//        	lst.add(new BasicNameValuePair("sensor",sensor));
			lst.add(new BasicNameValuePair("address", cityName));
			return lst;
		}
		
		// add brand
		public static final List<NameValuePair> addBrandToParams(String apikey,int idbrand,String language){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", apikey));
			lst.add(new BasicNameValuePair("idbrand", String.valueOf(idbrand)));
			lst.add(new BasicNameValuePair("language", language));
			return lst;
		}
		
		// delete brand
	}

	public static final class Catalogs implements BaseColumns {

		public static final String CATALOGS_TABLE_NAME = "catalogs";
		public static final String CATALOGS_DIRECTORY_NAME = "catalogs";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.catalogcontentprovider";
		public static final String CATALOG = "catalog";

		public static final Uri CATALOGS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Catalogs.CATALOG);
		public static final Uri CATALOG_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Catalogs.CATALOG + "/1");

		/**
		 * The MIME type of a {@link ContentUri} of a category.
		 */
		public static final String CONTENT_CATALOG_TYPE =
				"application/json";

		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		public static final String URL = "url";
		public static final String BRAND_ID = "brand_id";
	}

	/**
	 * Contains the constants for a Category model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class Categories implements BaseColumns {

		public static final String CATEGORIES_TABLE_NAME = "categories";
		public static final String CATEGORIES_DIRECTORY_NAME = "categories";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.categorycontentprovider";
		public static final String CATEGORY = "category";

		public static final Uri CATEGORIES_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Categories.CATEGORY);
		public static final Uri CATEGORY_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Categories.CATEGORY + "/1");
		public static final Uri CATEGORY_BRANDS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Categories.CATEGORY + "/" + Brands.BRAND);
		public static final Uri CATEGORY_BRAND_SHOPS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Categories.CATEGORY + "/" + Brands.BRAND + "/" + Stores.STORE);
		public static final Uri CATEGORY_BRAND_SHOP_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Categories.CATEGORY + "/" + Brands.BRAND + "/" + Stores.STORE + "/1");

		public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/categories.json";

		// TODO hongmophi
		public static final String API_PRODUCTCATEGORY_LIST = ConstantsHelper.BASE_URL+"/v.0.1/category/list.json";
		public static final String API_CITY_SEARCH = ConstantsHelper.BASE_URL+"/v.0.1/city/search.json";
		/**
		 * The MIME type of a {@link ContentUri} of a category.
		 */
		public static final String CONTENT_CATEGORY_TYPE =
				"application/json";

		public static final String TITLE = "title";
		public static final String URL = "url";
		public static final String CATEGORIES_ID ="categories_id";
		public static final String CATEGORIES_NAME = "categories_name";
		/**
		 * This column sideloads one level
		 * of subcategories as a comma-delimited
		 * array.
		 */
		public static final String CATEGORIES = "categories";

		/* Indexing brands */
		public static String CreateIndexUrlForBrand(int categoryId) {
			return ConstantsHelper.BASE_URL + "/categories/" + Integer.toString(categoryId) + "/brands.json";
		}

		// load all categories
		public static List<NameValuePair> load_all_categories_post_param(String type,String api,int maxresult,String lang){
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("type",type));
			nameValuePairs.add(new BasicNameValuePair("apikey", api));
			nameValuePairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresult)));
			nameValuePairs.add(new BasicNameValuePair("language", lang));
			return nameValuePairs;
		}
		public static List<NameValuePair> load_all_new_categories_post_param(String api,String lang, String terminal, int last_update){
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("terminal",terminal));
			nameValuePairs.add(new BasicNameValuePair("apikey", api));
			nameValuePairs.add(new BasicNameValuePair("last_update", String.valueOf(last_update)));
			nameValuePairs.add(new BasicNameValuePair("language", lang));
			return nameValuePairs;
		}

		// search city name
		public static List<NameValuePair> getListSearchCityNamePostParams(String term, String lang){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("term", term));
			lst.add(new BasicNameValuePair("language", lang));
			return lst;
		}
	}

	/**
	 * Contains the constants for a Comment model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class Comments implements BaseColumns {

		public static final String COMMENTS_TABLE_NAME = "comments";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.commentcontentprovider";
		public static final String COMMENT = "comment";

		public static final Uri COMMENTS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Comments.COMMENT);
		public static final Uri COMMENT_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Comments.COMMENT+ "/1");

		// TODO hongmophi
		public static final String API_COMMENT_LIST =ConstantsHelper.BASE_URL+"/v.0.1/comment/list.json";
		public static final String API_COMMENT_CREATE =ConstantsHelper.BASE_URL+"/v.0.1/comment/create.json";
		public static final String API_COMMENT_DELETE =ConstantsHelper.BASE_URL+"/v.0.1/comment/delete.json";
		public static final String API_COMMENT_UPDATE =ConstantsHelper.BASE_URL+"/v.0.1/comment/update.json";
		// end

		/**
		 * The MIME type of a {@link ContentUri} of a comment.
		 */
		public static final String CONTENT_COMMENT_TYPE =
				"application/json";

		public static final String BODY = "body";

		public static final String PRODUCT_ID = "product_id";
		public static final String PROFILE_ID = "profile_id";
		public static final String COMMENT_ID = "comment_id";

		public static List<NameValuePair> PostParamsFromParams(int productId, int profileId, String comment) {
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(PRODUCT_ID, Integer.toString(productId)));
			postParams.add(new BasicNameValuePair(PROFILE_ID, Integer.toString(profileId)));
			postParams.add(new BasicNameValuePair(BODY, comment));
			return postParams;
		}

		// load list comment by idproduct
		public static List<NameValuePair> loadListCommenttoParams(String api, int proid, String lang,String type, int offset, int maxresults){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("idproduct", String.valueOf(proid)));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("type", type));
			lst.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			lst.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			return lst;
		}
		// update comment product
		public static List<NameValuePair> updateCommentParams(String api,String lang, String content, int commID, String title){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language",lang));
			lst.add(new BasicNameValuePair("content",content));
			lst.add(new BasicNameValuePair("idcomment",String.valueOf(commID)));
			lst.add(new BasicNameValuePair("title", title));

			return lst;
		}
		// create comment product
		public static List<NameValuePair> createCommentParams(String api, String content, String title, String lang,int proid){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("content",content));
			lst.add(new BasicNameValuePair("idproduct", String.valueOf(proid)));
			lst.add(new BasicNameValuePair("title", title));

			return lst;
		}
		// delete comment product
		public static List<NameValuePair> deleteCommentParams(String api,int commentid,String lang){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("idcomment",String.valueOf(commentid)));
			lst.add(new BasicNameValuePair("language", lang));
			return lst;
		}

	}

	public static final class Likes {

		public static final String LIKES_RESOURCE = "likes";

		public static final String UPDATE_URL = ConstantsHelper.BASE_URL + "/likes.json";
		public static final String  API_LIKE_CREATE = ConstantsHelper.BASE_URL+"/v.0.1/like/create.json";
		public static final String API_LIKE_UPDATE = ConstantsHelper.BASE_URL+"/v.0.1/like/update.json";

		/*public static final int LIKE_DREAM = 0;
		public static final int LIKE_NEXT = 1;
		public static final int LIKE_HAVE = 2;
		public static final int LIKE_ILIKE = 3;
		public static final int LIKE_CANCEL = 4;*/
		public static final int LIKE_DREAM = 0;
		public static final int LIKE_ILIKE = 1;
		public static final int LIKE_NEXT = 2;
		public static final int LIKE_HAVE = 3;
		public static final int LIKE_CANCEL = 4;

		public static final String PRODUCT_ID = "product_id";
		public static final String PROFILE_ID = "profile_id";
		public static final String LIKE_TYPE = "like_type";

		public static List<NameValuePair> PostParamsFromParams(int productId, int profileId, int like_type) {
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(PRODUCT_ID, Integer.toString(productId)));
			postParams.add(new BasicNameValuePair(PROFILE_ID, Integer.toString(profileId)));
			postParams.add(new BasicNameValuePair(LIKE_TYPE, Integer.toString(like_type)));
			return postParams;
		}

		// create like
		public static List<NameValuePair> createLikeFromParams(String api, String type, String mess, int idproduct,String lang){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("type", type));
			lst.add(new BasicNameValuePair("message", mess));
			lst.add(new BasicNameValuePair("idproduct", String.valueOf(idproduct)));
			return lst;
		}

		// update like
		public static List<NameValuePair> updateLikeFromParams(String api, String type, String mess, int idproduct,String lang,String idlike){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("type", type));
			lst.add(new BasicNameValuePair("message", mess));
			lst.add(new BasicNameValuePair("idproduct", String.valueOf(idproduct)));
			lst.add(new BasicNameValuePair("idlike", idlike));
			return lst;
		}
	}

	/**
	 * Contains the constants for a News model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class News implements BaseColumns {

		public static final String NEWS_TABLE_NAME = "news";
		public static final String NEWS_DIRECTORY_NAME = "news";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.newscontentprovider";
		public static final String NEWS = "news";

		public static final Uri NEWS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + News.NEWS);
		public static final Uri NEW_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + News.NEWS + "/1");

		public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/news.json";
		public static final String SEARCH_URL = ConstantsHelper.BASE_URL + "/news/search";
		
		public static final String API_NEWS_LIST = ConstantsHelper.BASE_URL+"/v.0.1/news/list.json";

		/**
		 * The MIME type of a {@link ContentUri} of news.
		 */
		public static final String CONTENT_NEWS_TYPE =
				"application/json";

		public static final String TITLE = "title";
		public static final String CONTENT = "content";
		public static final String URL = "url";
		public static final String BRAND_ID = "brand_id";
		
		// load new news
		public static List<NameValuePair> loadNewNewsPostParam(String type,String api,int maxresult, String language, int offset){
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("type",type));
			nameValuePairs.add(new BasicNameValuePair("apikey", api));
			nameValuePairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresult)));
			nameValuePairs.add(new BasicNameValuePair("language", language));
			nameValuePairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			return nameValuePairs;
		}
	}


	public static final class Photos implements BaseColumns {

		public static final String PHOTOS_TABLE_NAME = "photos";
		public static final String PHOTOS_DIRECTORY_NAME = "photos";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.photocontentprovider";
		public static final String PHOTOS = "photos";

		public static final Uri PHOTOS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Photos.PHOTOS);
		public static final Uri PHOTO_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Photos.PHOTOS + "/1");

		public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/photos.json";

		/**
		 * The MIME type of a {@link ContentUri} of news.
		 */
		public static final String CONTENT_PHOTO_TYPE =
				"application/json";

		public static final String URL = "url";
		public static final String BRAND_ID = "brand_id";
		public static final String CATEGORY_ID = "category_id";

		// TODO hongmophi
		public static final String API_GALLERYPHOTO_LIST =ConstantsHelper.BASE_URL+"/v.0.1/user_gallery/list.json";
		public static final String API_GALLERYPHOTO_CREATE =ConstantsHelper.BASE_URL+"/v.0.1/user_gallery/create.json";
		// load galaries profile
		public static List<NameValuePair> photos_profile_paramtoparam(int idprofile, String api, String lang, String type, int maxreuslt){
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("iduser", String.valueOf(idprofile)));
			nameValuePairs.add(new BasicNameValuePair("apikey", api));
			nameValuePairs.add(new BasicNameValuePair("language", lang));
			nameValuePairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxreuslt)));
			nameValuePairs.add(new BasicNameValuePair("type", type));
			return nameValuePairs;
		}
	}

	/**
	 * Contains the constants for a Product model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class Products implements BaseColumns {

		public static final String PRODUCTS_TABLE_NAME = "products";
		public static final String PRODUCTS_DIRECTORY_NAME = "products";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.productcontentprovider";
		public static final String PRODUCT = "product";
		public static final String NEW = "new";
		public static final String SUGGESTED = "suggested";
		public static final String POPULAR = "popular";

		public static final String FILTER = "filter";
		public static final int NEW_VALUE = 0;
		public static final int SUGGESTED_VALUE = 1;
		public static final int POPULAR_VALUE = 2;

		public static final Uri PRODUCTS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Products.PRODUCT);
		/*public static final Uri PRODUCT_CONTENT_URI = Uri.parse("content://" +
                AUTHORITY + "/" + Products.PRODUCT + "/1");*/
		public static final Uri PRODUCT_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Products.PRODUCT);
		public static final Uri PRODUCTS_NEW_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Products.PRODUCT + "/" + Products.NEW);
		public static final Uri PRODUCTS_SUGGESTED_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Products.PRODUCT + "/" + Products.SUGGESTED);
		public static final Uri PRODUCTS_POPULAR_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Products.PRODUCT + "/" + Products.POPULAR);

		public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/v.0.1/list.json";
		public static final String SHOW_URL = ConstantsHelper.BASE_URL + "/v.0.1/product/";
		public static final String SEARCH_URL = ConstantsHelper.BASE_URL + "/v.0.1/product/search";

		// TODO hongmophi
		public static final String API_PRODUCT_LIST = ConstantsHelper.BASE_URL + "/v.0.1/product/list.json";
		public static final String API_LIKE_LIST = ConstantsHelper.BASE_URL+"/v.0.1/like/list.json";
		public static final String API_PRODUCT_GET = ConstantsHelper.BASE_URL+"/v.0.1/product/get.json";
		/**
		 * The MIME type of a {@link ContentUri} of a product.
		 */
		public static final String CONTENT_PRODUCT_TYPE =
				"application/json";

		public static final String TITLE = "title";
		public static final String ADDED_BY = "added_by";
		public static final String URL = "url";
		public static final String TYPE = "type";
		public static final String NUMBER_OF_COMMENTS = "number_of_comments";
		public static final String NUMBER_OF_LIKES = "number_of_likes";
		public static final String LIKE_TYPE = "like_type";
		public static final String BRAND_ID = "brand_id";
		public static final String PRODUCT_USER = "product_user";
		public static final String TYPE_LIKE = "type_like";
		public static final String NAME_PRODUCT = "product_name";

		/* Creating a product */
		public static String CreateUploadUrlForProduct() {
			return ConstantsHelper.BASE_URL + "/products.json";
		}

		/* Indexing comments */
		public static String CreateIndexUrlForComment(int productId) {
			return ConstantsHelper.BASE_URL + "/products/" + Integer.toString(productId) + "/comments.json";
		}

		/* Creating a comment */
		public static String CreatePostUrlForComment(int productId) {
			return ConstantsHelper.BASE_URL + "/products/" + Integer.toString(productId) + "/comments.json";
		}

		public static List<NameValuePair> postParamstoParams(String producttype, String apikey,int maxresults,String langugage,int offset){
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
			valuePairs.add(new BasicNameValuePair("producttype",producttype));
			valuePairs.add(new BasicNameValuePair("apikey", apikey));
			valuePairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			valuePairs.add(new BasicNameValuePair("language", langugage));
			valuePairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			return valuePairs;
		}
		// load product by filter categories
		public static List<NameValuePair> filterCatepostParamstoParams(String producttype, String apikey,int category,int maxresults,String langugage,int offset){
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
			valuePairs.add(new BasicNameValuePair("producttype",producttype));
			valuePairs.add(new BasicNameValuePair("apikey", apikey));
			valuePairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			valuePairs.add(new BasicNameValuePair("language", langugage));
			valuePairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			valuePairs.add(new BasicNameValuePair("idcategory", String.valueOf(category)));
			return valuePairs;
		}
		// load product by filter brands
		public static List<NameValuePair> filterBrandParamstoParams(String productType, String api, int idbrand, int maxresults,String langugage,int offset){
			List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
			valuePairs.add(new BasicNameValuePair("producttype",productType));
			valuePairs.add(new BasicNameValuePair("apikey", api));
			valuePairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			valuePairs.add(new BasicNameValuePair("language", langugage));
			valuePairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			valuePairs.add(new BasicNameValuePair("idbrand", String.valueOf(idbrand)));
			return valuePairs;
		}
		// profile like 
		public static List<NameValuePair> like_profile_postParamtoparam(int idprofile, String api, String lang, int offset, int maxresults){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idprofile",String.valueOf(idprofile)));
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			pairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			pairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			return pairs;
		}

		public static List<NameValuePair> getProduct(String api,int productid, String lang,String type){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("idproduct", String.valueOf(productid)));
			pairs.add(new BasicNameValuePair("language", lang));
			pairs.add(new BasicNameValuePair("type", type));
			return pairs;
		}
		// product likes (product page)
		// profile like 
		public static List<NameValuePair> productLike_postParamtoparam(int idprofile, String api, String lang, int offset, int maxresults){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idproduct",String.valueOf(idprofile)));
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			pairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			pairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			return pairs;
		}
		// get product by brand id
		public static List<NameValuePair> productByBrandIDToParams(String api, int idBrand, String lang, String type,int maxresults, int offset){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idbrand",String.valueOf(idBrand)));
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			pairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			pairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			return pairs;
		}

		// product update like
		public static List<NameValuePair> productUpdateLikeToParams(String api,String lang, int idproduct, int iduser){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			return lst;
		}
	}

	/**
	 * Contains the constants for a Profile model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class Profiles implements BaseColumns {

		public static final String PROFILES_TABLE_NAME = "profiles";
		public static final String PROFILES_FOLLOWS_MODELS_KEY_NAME = "follows";
		public static final String PROFILES_FOLLOWINGS_MODELS_KEY_NAME = "followings";
		public static final String PROFILES_DIRECTORY_NAME = "profiles";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.profilecontentprovider";

		public static final String PROFILE = "profile";
		public static final String PASSWORD = "password";
		public static final String NEW = "new";
		public static final String SUGGESTED = "suggested";
		public static final String POPULAR = "popular";

		public static final String FILTER = "filter";
		public static final int NEW_VALUE = 0;
		public static final int SUGGESTED_VALUE = 1;
		public static final int POPULAR_VALUE = 2;

		public static final Uri PROFILES_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Profiles.PROFILE);
		/*public static final Uri PROFILE_CONTENT_URI = Uri.parse("content://" +
                AUTHORITY + "/" + Profiles.PROFILE + "/1");*/
		public static final Uri PROFILE_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Profiles.PROFILE);
		public static final Uri PROFILES_NEW_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Profiles.PROFILE + "/" + Profiles.NEW);
		public static final Uri PROFILES_SUGGESTED_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Profiles.PROFILE + "/" + Profiles.SUGGESTED);
		public static final Uri PROFILES_POPULAR_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Profiles.PROFILE + "/" + Profiles.POPULAR);

		/*public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/profiles.json";
		public static final String CREATE_URL = ConstantsHelper.BASE_URL + "/profiles.json";
		public static final String SHOW_URL = ConstantsHelper.BASE_URL + "/profiles/";
		public static final String SEARCH_URL = ConstantsHelper.BASE_URL + "/profiles/search.json";*/

		// TODO hongmophi
		public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/v.0.1/profile/create.json";
		public static final String CREATE_URL = ConstantsHelper.BASE_URL + "/v.0.1/profile/create.json";
		public static final String SHOW_URL = ConstantsHelper.BASE_URL + "/v.0.1/profile/";
		public static final String SEARCH_URL = ConstantsHelper.BASE_URL + "/v.0.1/profile/search.json";

		public static final String API_PROFILE_LIST = ConstantsHelper.BASE_URL+"/v.0.1/profile/list.json";
		public static final String API_PROFILE_GET = ConstantsHelper.BASE_URL +"/v.0.1/profile/get.json";
		public static final String API_PROFILE_FOLLOWING_ADD = ConstantsHelper.BASE_URL+"/v.0.1/profile/add_following.json";
		public static final String API_PROFILE_LOGOUT = ConstantsHelper.BASE_URL+"/v.0.1/profile/logout.json";
		public static final String API_PROFILE_FOLLOWING_DELETE = ConstantsHelper.BASE_URL+"/v.0.1/profile/delete_following.json";

		public static final String API_SEARCH_LIST = ConstantsHelper.BASE_URL+"/v.0.1/search/list.json";
		public static final String API_PROFILE_UPDATE = ConstantsHelper.BASE_URL+"/v.0.1/profile/update.json";
		public static final String API_RESET_PASSWORD = ConstantsHelper.BASE_URL+"/v.0.1/profile/reset_password.json";
		public static final String API_EMAIL_USED = ConstantsHelper.BASE_URL+"/v.0.1/profile/email_used.json";
		public static final String API_USERNAME_USED = ConstantsHelper.BASE_URL+"/v.0.1/profile/username_used.json";
		public static final String API_PROFILE_CREATION = ConstantsHelper.BASE_URL+"/v.0.1/profile/create.json";
		public static final String API_PROFILE_GET_LIKES = ConstantsHelper.BASE_URL+"/v.0.1/profile/get_likes.json";
		
		// new api
		public static final String API_PROFILE_GET_FOLLOWERS = ConstantsHelper.BASE_URL+"/v.0.1/profile/get_followers.json";
		public static final String API_PROFILE_GET_FOLLOWINGS = ConstantsHelper.BASE_URL+"/v.0.1/profile/get_followings.json";
		
		public static final String API_PROFILE_GET_PROFESSION = ConstantsHelper.BASE_URL+"/v.0.1/profile/get_profession.json";
		/**
		 * The MIME type of a {@link ContentUri} of a profile.
		 */
		public static final String CONTENT_PROFILE_TYPE =
				"application/json";

		public static final String FIRST_NAME = "firstname";
		public static final String LAST_NAME = "lastname";
		public static final String USERNAME = "username";
		public static final String DATE_OF_BIRTH = "date_of_birth";
		public static final String EMAIL = "email";
		public static final String CAREER = "career";
		public static final String URL = "url";
		public static final String NUMBER_OF_BRANDS = "number_of_brands";
		public static final String BRAND_IDS = "brand_ids";
		public static final String NUMBER_OF_PRODUCTS = "number_of_products";
		public static final String PRODUCT_IDS = "product_ids";
		public static final String NUMBER_OF_PHOTOS = "number_of_photos";
		public static final String PHOTO_IDS = "photo_ids";
		public static final String NUMBER_OF_FOLLOWS = "number_of_follows";
		public static final String FOLLOW_IDS = "follow_ids";
		public static final String NUMBER_OF_FOLLOWINGS = "number_of_followings";
		public static final String FOLLOWING_IDS = "following_ids";
		public static final String TYPE = "type";

		/* Show a profile */
		public static String CreateGetUrlToShowAProfile(int profileId) {
			//        	return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + ".json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profile/" + Integer.toString(profileId) + ".json";
		}

		/* Creating a fan */
		public static String CreatePutUrlToLikeABrand(int profileId, int brandId) {
			//return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + "/brands/" + Integer.toString(brandId) + ".json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profile/" + Integer.toString(profileId) + "/brands/" + Integer.toString(brandId) + ".json";
		}

		/* Following a profile */
		public static String CreatePostUrlToFollowAProfile(int profileId) {
			//return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + "/follows.json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profiles/" + Integer.toString(profileId) + "/follows.json";
		}

		/* Indexing follows */
		public static String CreateIndexUrlForFollows(int profileId) {
			//	return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + "/follows.json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profile/" + Integer.toString(profileId) + "/follows.json";
		}

		/* Indexing followings */
		public static String CreateIndexUrlForFollowings(int profileId) {
			//return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + "/followings.json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profile/" + Integer.toString(profileId) + "/followings.json";
		}

		/* Indexing brands */
		public static String CreateIndexUrlForBrands(int profileId) {
			//	return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + "/brands.json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profile/" + Integer.toString(profileId) + "/brands.json";
		}

		/* Indexing products */
		public static String CreateIndexUrlForProducts(int profileId) {
			//	return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + "/products.json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profile/" + Integer.toString(profileId) + "/products.json";
		}

		/* Indexing photos */
		public static String CreateIndexUrlForPhotos(int profileId) {
			//return ConstantsHelper.BASE_URL + "/profiles/" + Integer.toString(profileId) + "/photos.json";
			return ConstantsHelper.BASE_URL + "/v.0.1/profile/" + Integer.toString(profileId) + "/photos.json";
		}

		public static final String BRAND_ID = "brand_id";
		public static final String FOLLOW_ID = "follow_id";
		public static final String PROFILE_ID = "profile_id";

		public static List<NameValuePair> PutParamsFromParams(int brandId, int profileId) {
			List<NameValuePair> putParams = new ArrayList<NameValuePair>();
			putParams.add(new BasicNameValuePair(BRAND_ID, Integer.toString(brandId)));
			putParams.add(new BasicNameValuePair(PROFILE_ID, Integer.toString(profileId)));
			return putParams;
		}

		public static List<NameValuePair> PostParamsFromParams(int followId) {
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(FOLLOW_ID, Integer.toString(followId)));
			return postParams;
		}
		// TODO hongmophi
		// get news
		public static List<NameValuePair> post_new_paramsfromparams(String searchtype, String apikey, String language,int maxresults, int offset, String type, String terminal){
			List<NameValuePair> postparams = new ArrayList<NameValuePair>();
			postparams.add(new BasicNameValuePair("searchtype",searchtype));
			postparams.add(new BasicNameValuePair("apikey", apikey));
			postparams.add(new BasicNameValuePair("language", language));
			postparams.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			postparams.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			postparams.add(new BasicNameValuePair("type", type));
			postparams.add(new BasicNameValuePair("terminal", terminal));
			return  postparams;
		}
		// load follower, 
		public static List<NameValuePair> post_followers_paramtoparam(int idfollower, String api, int offset,int maxresult, String lang){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idfollowing",String.valueOf(idfollower)));
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			pairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			pairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresult)));
			return pairs;
		}
		
		// load followings
		public static List<NameValuePair> post_following_paramtoparam(int idfollowing, String api, int offset,int maxresults, String lang){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idfollower",String.valueOf(idfollowing)));
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			pairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			pairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			return pairs;
		}
		// change follow the new api : load follower, following
		public static List<NameValuePair> new_post_followers_following_toparams(String api, int idprofile, int maxresult, int offset){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("idprofile",String.valueOf(idprofile)));
			pairs.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			pairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresult)));
			return pairs;
		}
		// load profile diary 
		public static List<NameValuePair> post_profileDiary_paramtoparam(String idprofile, int revision, String type, String api, String lang){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("idprofile",idprofile));
			pairs.add(new BasicNameValuePair("revision", String.valueOf(revision)));
			pairs.add(new BasicNameValuePair("type",type));
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			return pairs;
		}

		// load profile follow menu 
		public static List<NameValuePair> post_profile_following_add(String idprofile, String idfollowing, String api, String lang){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("iduser", idprofile));
			pairs.add(new BasicNameValuePair("idfollowing", idfollowing));
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("language", lang));
			return pairs;
		}
		// remove follow
		public static List<NameValuePair> unfollow_follow_profile(String api,String idfollowing, String lang){
			List<NameValuePair>  pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("apikey", api));
			pairs.add(new BasicNameValuePair("idfollowing", idfollowing));
			pairs.add(new BasicNameValuePair("language", lang));
			return pairs;
		}
		// search list in menu left
		public static List<NameValuePair> searchListProfilePosttoParams(String api, int maxresult, String lang, String textkey, double latitude, double longlatitude){
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(); 
			nameValuePairs.add(new BasicNameValuePair("apikey", api));
			nameValuePairs.add(new BasicNameValuePair("maxresults", String.valueOf(maxresult)));
			nameValuePairs.add(new BasicNameValuePair("language", lang));
			nameValuePairs.add(new BasicNameValuePair("term", textkey));
			nameValuePairs.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));
			nameValuePairs.add(new BasicNameValuePair("longitude", String.valueOf(longlatitude)));
			return nameValuePairs;
		}

		// update profile
		public static List<NameValuePair> updateProfileToParams(String api,String lang,String firstname, String lastName, String birth, String gender,String email, int idcity, String acticity, String usern, String picture, int disReal, int disBirth,String proPicRec,String proPicSquare){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("firstname", firstname));
			lst.add(new BasicNameValuePair("lastname",lastName));
			lst.add(new BasicNameValuePair("birthdate",birth));
			lst.add(new BasicNameValuePair("gender",gender));
			lst.add(new BasicNameValuePair("email",email));
//			lst.add(new BasicNameValuePair("password",passw));
			lst.add(new BasicNameValuePair("idcity",String.valueOf(idcity)));
			lst.add(new BasicNameValuePair("activity",acticity));
//			lst.add(new BasicNameValuePair("quizz",);
			lst.add(new BasicNameValuePair("username",usern));
			lst.add(new BasicNameValuePair("picture",picture));
			lst.add(new BasicNameValuePair("display_real_name",String.valueOf(disReal)));
			lst.add(new BasicNameValuePair("display_birthdate",String.valueOf(disBirth)));
			lst.add(new BasicNameValuePair("profile_picture_rectangle",proPicRec));
			lst.add(new BasicNameValuePair("profile_picture_square",proPicSquare));
			return lst;
		}
		// update avatar
		public static List<NameValuePair> updateAvatar(String api,String lang,String iduser,String picLar, String picSmal){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("iduser", iduser));
			lst.add(new BasicNameValuePair("profile_picture_rectangle",picLar));
			lst.add(new BasicNameValuePair("profile_picture_square",picSmal));
			return lst;
		}
		// reset password
		public static List<NameValuePair> resetPasswordToParams(String email){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("email", email));
			return lst;
		}
		// check email existed or not
		public static List<NameValuePair> checkExistedEmailToParams(String email){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("email", email));
			return lst;
		}
		// check username existed or not
		public static List<NameValuePair> checkExistedUserToParams(String username){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("username", username));
			return lst;
		}
		// create user
		public static List<NameValuePair> createProfileToParams(String firstname, String lastName, String birth, String gender,String email,String passw, int idcity, String acticity, String usern, String picture, int disReal, int disBirth,String proPicRec,String proPicSquare,String lang){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("firstname", firstname));
			lst.add(new BasicNameValuePair("lastname",lastName));
			lst.add(new BasicNameValuePair("birthdate",birth));
			lst.add(new BasicNameValuePair("gender",gender));
			lst.add(new BasicNameValuePair("email",email));
			lst.add(new BasicNameValuePair("password",passw));
			lst.add(new BasicNameValuePair("idcity",String.valueOf(idcity)));
			lst.add(new BasicNameValuePair("activity",acticity));
//			lst.add(new BasicNameValuePair("quizz",);
			lst.add(new BasicNameValuePair("username",usern));
			lst.add(new BasicNameValuePair("picture",picture));
			lst.add(new BasicNameValuePair("display_real_name",String.valueOf(disReal)));
			lst.add(new BasicNameValuePair("display_birthdate",String.valueOf(disBirth)));
			lst.add(new BasicNameValuePair("profile_picture_rectangle",proPicRec));
			lst.add(new BasicNameValuePair("profile_picture_square",proPicSquare));
			return lst;
		}
		// get like profile
		public static List<NameValuePair> getLikeProfileToParams(String api, int profile, String language, int kmax, int offset){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("idprofile", String.valueOf(profile)));
			lst.add(new BasicNameValuePair("language", language));
			lst.add(new BasicNameValuePair("maxresults", String.valueOf(kmax)));
			lst.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			return lst;
		}
		// logout 
		public static List<NameValuePair> post_logout_profile(String api){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("apikey", api));
			return pairs;
		}
		
		// profession user
		public static List<NameValuePair> getProfessionUser(String language){
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("language", language));
			return pairs;
		}
		// end
	}

	public static final class Questions implements BaseColumns {

		public static final String QUESTIONS_TABLE_NAME = "questions";
		public static final String QUESTIONS_DIRECTORY_NAME = "questions";
		public static final String QUESTION = "question"; // Is also a table column

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.questioncontentprovider";

		public static final Uri QUESTIONS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Questions.QUESTION);
		public static final Uri QUESTION_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Questions.QUESTION + "/1");

		/**
		 * The MIME type of a {@link ContentUri} of news.
		 */
		public static final String CONTENT_QUESTION_TYPE =
				"application/json";

		public static final String INDEX_URL = ConstantsHelper.BASE_URL + "/questions.json";
		
		// TODO hongmopho
		public static final String API_QUIZZ_GET = ConstantsHelper.BASE_URL+ "/v.0.1/quizz/get_for_user.json";
		public static final String API_QUIZZ_ANSWER =ConstantsHelper.BASE_URL+"/v.0.1/quizz/set_answers_for_user.json";

		/*
		 * A question has many images, so this column
		 * will contain a comma-delimited string or
		 * images. 
		 */
		public static final String URL = "url";
		
		// get question user
		public static List<NameValuePair> getQuestionUserToParam(String api,String language){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", language));
			return lst;
		}
		// set answer for user
		public static List<NameValuePair> setAnswerForUserToParams(String api, String lang, String answer){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("answers", answer));
			return lst;
		}
	}

	public static final class Search implements BaseColumns {

		public static final String SEARCH_TABLE_NAME = "searches";
		public static final String SEARCH = "search";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.searchcontentprovider";

		public static final Uri SEARCH_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + SEARCH);

		/**
		 * The MIME type of a {@link ContentUri} of news.
		 */
		public static final String CONTENT_SEARCH_TYPE =
				"application/json"; 

		public static final String QUERY = "query";
		public static final String RESULT = "result";
		public static final String MODEL = "model";
		public static final String MODEL_ID = "model_id";
		public static final String URL = "url";
		public static final String UPDATED_AT = "updated_at";

		public static final String BASE_URL_KEY = "base_url_key";
	}

	public static final class Sessions {

		public static final String SESSIONS = "sessions";
		//		public static final String CREATE_URL = ConstantsHelper.BASE_URL + "/sessions.json";
		public static final String CREATE_URL = ConstantsHelper.BASE_URL + "/v.0.1/profile/login.json";

		public static final String SESSION_ID = "session_id";
		public static final String EMAIL = "email";
		public static final String PASSWORD = "password";
		// TODO hongmophi
		public static final String LANGUAGE =Locale.getDefault().getLanguage();
		public static final String REVISION = "1";


		/*public static List<NameValuePair> PostParamsFromParams(String email, String password) {
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(EMAIL, email));
			postParams.add(new BasicNameValuePair(PASSWORD, password));
			return postParams;
		}*/
		public static List<NameValuePair> PostParamsFromParams(String email, String password,String language,String revision) {
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			postParams.add(new BasicNameValuePair(EMAIL, email));
			postParams.add(new BasicNameValuePair(PASSWORD, password));
			postParams.add(new BasicNameValuePair("language", LANGUAGE));
			postParams.add(new BasicNameValuePair("revision", REVISION));
			return postParams;
		}
	}

	/**
	 * Contains the constants for a Store model, {@link ContentProvider},
	 * and database table.
	 * 
	 */
	public static final class Stores implements BaseColumns {

		public static final String STORES_TABLE_NAME = "stores";
		public static final String STORE = "store";
		public static final String STORES_DIRECTORY_NAME = "stores";

		public static final String AUTHORITY = "com.hitechno.sogoods.providers.storecontentprovider";

		public static final Uri STORES_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Stores.STORE);
		public static final Uri STORE_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Stores.STORE + "/1");
		public static final Uri STORE_BRANDS_CONTENT_URI = Uri.parse("content://" +
				AUTHORITY + "/" + Stores.STORE + "/" + Brands.BRAND);

		/**
		 * The MIME type of a {@link ContentUri} of news.
		 */
		public static final String CONTENT_STORE_TYPE =
				"application/json";
		// TODO hongmophi
		public static final String API_SHOPS_LIST = ConstantsHelper.BASE_URL +"/v.0.1/shop/list.json"; 
		public static final String API_SHOPS_GET =ConstantsHelper.BASE_URL+ "/v.0.1/shop/get.json";
		// end

		public static final String NAME = "name";
		public static final String ADDRESS = "address";
		public static final String ADDRESS_URL = "address_url";
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
		public static final String BRAND_IDS = "brand_ids";

		/* Indexing brands */
		public static String CreateIndexUrlForBrands(int storeId) {
			return ConstantsHelper.BASE_URL + "/stores/" + Integer.toString(storeId) + "/brands.json";
		}
		// get shop by id shop
		public static List<NameValuePair> getStoreByidPosttoParam(String api,int idshop, String lang,String type){
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("apikey", api));
			list.add(new BasicNameValuePair("idshop", String.valueOf(idshop)));
			list.add(new BasicNameValuePair("language", lang));
			list.add(new BasicNameValuePair("type", type));
			return list;
		}
		// set shop by idbrand
		public static List<NameValuePair> getStoreByIDbrandToParams(String api, int idbrand, String lang, String type, int maxresults, int offset, double latitude, double longitude){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("idbrand", String.valueOf(idbrand)));
			lst.add(new BasicNameValuePair("language", lang));
			lst.add(new BasicNameValuePair("type", type));
			lst.add(new BasicNameValuePair("maxresults",String.valueOf(maxresults)));
			lst.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			lst.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));
			lst.add(new BasicNameValuePair("longitude", String.valueOf(longitude)));
			return lst;
		}
	}

	// TODO hongmophi
	public static final class Home implements BaseColumns{
		public static final String CREATE_URL = ConstantsHelper.BASE_URL+"/v.0.1/advertising/list.json";
		public static final String API_KEY = "api_key";
		public static final String ID_PROFILE = "id_profile";
		public static final String USERNAME_PROFILE = "username_session";
		public static final String AVATAR_PROFILE = "avatar_session";
		public static final String IMAGE_AVERTISING = "avertising";


		public static List<NameValuePair> loadAdvertisingToParams(String api, String lang){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("language", lang));
			return lst;
		}
	}
// class notification
	public static final class Notification implements BaseColumns{
		public static final String API_GET_NOTIFICATION = ConstantsHelper.BASE_URL+"/v.0.1/profile/get_notifications.json";
		public static final String API_GET_NOTIFICATION_SETTING = ConstantsHelper.BASE_URL+"/v.0.1/profile/get_notifications_settings.json";
		public static final String API_SET_NOTIFICATION = ConstantsHelper.BASE_URL+"/v.0.1/profile/set_notifications_settings.json";
		
		public static List<NameValuePair> getNoticationToParams(String api, String dateStart, int maxresults, int offset, String type){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("date_start", dateStart));
			lst.add(new BasicNameValuePair("maxresults", String.valueOf(maxresults)));
			lst.add(new BasicNameValuePair("offset", String.valueOf(offset)));
			lst.add(new BasicNameValuePair("type", type));
			return lst;
		}
		
		// get_notifications_settings 
		public static List<NameValuePair> getNotificationSetting(String api){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			return lst;
		}
		
		// set notifaication setting
		public static List<NameValuePair> setNotificationSettingToParams(String api,int newfoll, int newComm, int follPro, int follComm, int follPic){
			List<NameValuePair> lst = new ArrayList<NameValuePair>();
			lst.add(new BasicNameValuePair("apikey", api));
			lst.add(new BasicNameValuePair("new_follower", String.valueOf(newfoll)));
			lst.add(new BasicNameValuePair("new_comment_on_my_product",  String.valueOf(newComm)));
			lst.add(new BasicNameValuePair("following_add_product",  String.valueOf(follPro)));
			lst.add(new BasicNameValuePair("following_add_comment",  String.valueOf(follComm)));
			lst.add(new BasicNameValuePair("following_add_picture",  String.valueOf(follPic)));
			return lst;
		}
		
	}
	public static String inputStreamToString(InputStream is) {
		String s = "";
		String line = "";
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		try {
			while ((line = rd.readLine()) != null) { s += line; }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static void animationPro(FragmentActivity context) {
		context.overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_left);
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		if ((display.getRotation() == Surface.ROTATION_0)
				|| (display.getRotation() == Surface.ROTATION_180)) {
			context.overridePendingTransition(R.anim.slide_in_left,
					R.anim.slide_out_left);
		} else if ((display.getRotation() == Surface.ROTATION_90)
				|| (display.getRotation() == Surface.ROTATION_270)) {
			context.overridePendingTransition(R.anim.slide_up,
					R.anim.slide_down);
		}
	}

	public static void animationFragment(Context context) {
		((Activity) context).overridePendingTransition(R.anim.slide_in_left,
				R.anim.slide_out_left);
		Display display = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		if ((display.getRotation() == Surface.ROTATION_0)
				|| (display.getRotation() == Surface.ROTATION_180)) {
			((Activity) context).overridePendingTransition(
					R.anim.slide_in_left, R.anim.slide_out_left);
		} else if ((display.getRotation() == Surface.ROTATION_90)
				|| (display.getRotation() == Surface.ROTATION_270)) {
			((Activity) context).overridePendingTransition(R.anim.slide_up,
					R.anim.slide_down);
		}
	}
	
	 // check network connection
    public boolean isConnected(FragmentActivity activity){
        ConnectivityManager connMgr = (ConnectivityManager)activity.getSystemService(activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return true;
            else
                return false;  
    }
}
