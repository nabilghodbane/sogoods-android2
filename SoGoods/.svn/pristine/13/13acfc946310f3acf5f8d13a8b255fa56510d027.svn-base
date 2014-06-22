package com.hitechno.sogoods.constant;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ConstantsHelper
{
  public static final int ACTIVITY_RESULT_DID_PROCESS_IMAGE = 1;
  public static final int ACTIVITY_RESULT_DID_SELECT_IMAGE = 0;
  public static final int ADD_PRODUCTS_FRAGMENT = 1;
  public static final int ADVERTISEMENTS_LOADER = 0;
  public static final int ADVERTISEMENTS_VALUE = 0;
  public static final String BASE_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com";
  public static final int BIRTHDAY_INDEX = 7;
  public static final int BRANDS_LOADER = 2;
  public static final int BRANDS_VALUE = 1;
  public static final int BRAND_ID_INDEX = 1;
  public static final int BRAND_LOADER = 1;
  public static final String BROWSER_CLASS = "com.android.browser.BrowserActivity";
  public static final String BROWSER_PACKAGE = "com.android.browser";
  public static final String CACHE_KEY_FOR_IMAGE_PLACEHOLDER = "http";
  public static final int CAREER_INDEX = 8;
  public static final int CATALOGS_LOADER = 3;
  public static final int CATALOGS_VALUE = 2;
  public static final int CATEGORIES_LOADER = 4;
  public static final int CATEGORIES_VALUE = 3;
  public static final int CATEGORY_ID_INDEX = 0;
  public static final int COMMENTS_LOADER = 5;
  public static final int COMMENTS_VALUE = 4;
  public static final String DATABASE_NAME = "sogoods.db";
  public static int DATABASE_VERSION = 0;
  public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
  public static final String DATE_FORMAT_NEW = "dd/MM/yyyy";
  public static final int DESCRIPTION_INDEX = 3;
  public static final int EMAIL_INDEX = 4;
  public static final String EMAIL_REGEX = "(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)";
  public static final String ENCODING = "UTF-8";
  public static final int FIRSTNAME_INDEX = 1;
  public static final int FOLLOWINGS_LOADER = 14;
  public static final int FOLLOWS_LOADER = 13;
  public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_ASSET_URL = "url";
  public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_ID = "_id";
  public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID = "true_id";
  public static final String GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT = "updated_at";
  public static final String HEADER_ERROR_KEY = "X-SoGoods-Error-Message";
  public static final String HEADER_MODEL_KEY = "X-SoGoods-Model-Id";
  public static final int HOME_VALUE = 15;
  public static final int ID_PROFILE = 16;
  public static final String IMAGE_PLACEHOLDER = "image_placeholder.png";
  private static final int IMAGE_WIDTH = 100;
  public static final int LASTNAME_INDEX = 2;
  public static final int LIKES_VALUE = 5;
  public static final String LIKE_DIALOG_FRAGMENT_TAG = "like_dialog";
  public static final int LIKE_INDEX = 5;
  public static final Locale LOCALE = Locale.US;
  public static final int LOCATION_INDEX = 6;
  public static final String LOG_TAG = "SoGoods";
  public static final Map<String, Integer> MODELS;
  public static final int MY_ACCOUNT_FRAGMENT = 2;
  public static final int MY_PROFILE_FRAGMENT = 0;
  public static final int MY_PROFILE_LOADER = 6;
  public static final int NAME_INDEX = 2;
  public static final int NEWS_LOADER = 7;
  public static final int NEWS_VALUE = 6;
  public static final int NUMBER_OF_ACCOUNT_FRAGMENTS = 3;
  public static int NUMBER_OF_COLUMNS_IN_GRID = -1;
  public static final int NUMBER_OF_LINES_FOR_DETAIL_LIST_ITEM = 2;
  public static final int NUMBER_OF_MY_PROFILE_FRAGMENTS = 3;
  public static final int NUMBER_OF_PRODUCT_PARAMS = 6;
  public static final int NUMBER_OF_SIGNUP_PARAMS = 10;
  public static final int NUMBER_OF_UPDATE_PARAMS = 9;
  public static final String PAGE_POSITION = "page";
  public static final String PARAM_BIRTHDAY = "birthday";
  public static final String PARAM_BRAND_ID = "brand_id";
  public static final String PARAM_CAREER = "career";
  public static final String PARAM_CATEGORY_ID = "category_id";
  public static final String PARAM_EMAIL = "email";
  public static final String PARAM_FILE = "file";
  public static final String PARAM_FIRSTNAME = "firstname";
  public static final String PARAM_LASTNAME = "lastname";
  public static final String PARAM_LIKE_TYPE = "like_type";
  public static final String PARAM_LOCATION = "location";
  public static final String PARAM_PASSWORD = "password";
  public static final String PARAM_PRODUCT_DESCRIPTION = "description";
  public static final String PARAM_PRODUCT_TITLE = "title";
  public static final String PARAM_PROFILE_ID = "profile_id";
  public static final String PARAM_QUESTIONS = "questions";
  public static final String PARAM_TITLE = "title";
  public static final String PARAM_USERNAME = "username";
  public static final int PASSWORD_INDEX = 5;
  public static final int PHOTOS_LOADER = 8;
  public static final int PHOTOS_VALUE = 7;
  public static final int PRODUCTS_LOADER = 9;
  public static final int PRODUCTS_VALUE = 8;
  public static final int PRODUCT_LOADER = 10;
  public static final int PROFILES_LOADER = 12;
  public static final int PROFILES_VALUE = 9;
  public static final int PROFILE_FOLLOWINGS_VALUE = 11;
  public static final int PROFILE_FOLLOWS_VALUE = 10;
  public static final int PROFILE_ID_INDEX = 4;
  public static final int PROFILE_LOADER = 11;
  public static final int QUESTIONS_INDEX = 9;
  public static final int QUESTIONS_LOADER = 15;
  public static final String QUESTIONS_SEPARATOR = "***";
  public static final int QUESTIONS_VALUE = 12;
  public static final int SEARCH_LOADER = 17;
  public static final String SEARCH_LOADER_TYPE = "search_loader_type";
  public static final String SEPARATOR_FOR_IDS_AS_STRING = ",";
  public static final int SESSIONS_VALUE = 13;
  public static final int SHORT_DURATION = 250;
  public static final int SIGNIN_FRAGMENT = 0;
  public static final int SIGNUP_PAGE_ONE_FRAGMENT = 1;
  public static final int SIGNUP_PAGE_TWO_FRAGMENT = 2;
  public static final String START_DATE = "2013-01-01T00:00:00";
  public static final int STORES_LOADER = 16;
  public static final int STORES_VALUE = 14;
  public static final int TITLE_INDEX = 0;
  public static final int USERNAME_INDEX = 3;
  public static final int VALID_INPUT_LENGTH = 4;
  
  static
  {
    DATABASE_VERSION = 2;
    MODELS = new HashMap<String, Integer>();
    MODELS.put("advertisements", Integer.valueOf(0));
    MODELS.put("brands", Integer.valueOf(1));
    MODELS.put("catalogs", Integer.valueOf(2));
    MODELS.put("categories", Integer.valueOf(3));
    MODELS.put("comments", Integer.valueOf(4));
    MODELS.put("likes", Integer.valueOf(5));
    MODELS.put("news", Integer.valueOf(6));
    MODELS.put("photos", Integer.valueOf(7));
    MODELS.put("products", Integer.valueOf(8));
    MODELS.put("profiles", Integer.valueOf(9));
    MODELS.put("follows", Integer.valueOf(10));
    MODELS.put("followings", Integer.valueOf(11));
    MODELS.put("questions", Integer.valueOf(12));
    MODELS.put("sessions", Integer.valueOf(13));
    MODELS.put("stores", Integer.valueOf(14));
    MODELS.put("api_key", Integer.valueOf(15));
    MODELS.put("id_profile", Integer.valueOf(16));
  }
  
  public static void animationFragment(Context paramContext)
  {
    ((Activity)paramContext).overridePendingTransition(2130968592, 2130968594);
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    if ((localDisplay.getRotation() == 0) || (localDisplay.getRotation() == 2)) {
      ((Activity)paramContext).overridePendingTransition(2130968592, 2130968594);
    }
    while ((localDisplay.getRotation() != 1) && (localDisplay.getRotation() != 3)) {
      return;
    }
    ((Activity)paramContext).overridePendingTransition(2130968595, 2130968591);
  }
  
  public static void animationPro(FragmentActivity paramFragmentActivity)
  {
    paramFragmentActivity.overridePendingTransition(2130968592, 2130968594);
    Display localDisplay = ((WindowManager)paramFragmentActivity.getSystemService("window")).getDefaultDisplay();
    if ((localDisplay.getRotation() == 0) || (localDisplay.getRotation() == 2)) {
      paramFragmentActivity.overridePendingTransition(2130968592, 2130968594);
    }
    while ((localDisplay.getRotation() != 1) && (localDisplay.getRotation() != 3)) {
      return;
    }
    paramFragmentActivity.overridePendingTransition(2130968595, 2130968591);
  }
  
  public static void setNumberOfColumnsInGrid(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    NUMBER_OF_COLUMNS_IN_GRID = (int)Math.floor(localDisplayMetrics.widthPixels / 100);
  }
  
  public static final class Advertisements
    implements BaseColumns
  {
    public static final String ADVERTISEMENT = "advertisement";
    public static final Uri ADVERTISEMENTS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.advertisementcontentprovider/advertisement");
    public static final String ADVERTISEMENTS_DIRECTORY_NAME = "advertisements";
    public static final String ADVERTISEMENTS_TABLE_NAME = "advertisements";
    public static final Uri ADVERTISEMENT_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.advertisementcontentprovider/advertisement/1");
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.advertisementcontentprovider";
    public static final String CONTENT = "content";
    public static final String CONTENT_ADVERTISEMENT_TYPE = "application/json";
    public static final String INDEX_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/advertisements.json";
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String URL = "url";
  }
  
  public static final class Brands
    implements BaseColumns
  {
    public static final String API_BRAND_GET = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/get.json";
    public static final String API_BRAND_GET_MINIMIZED_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/get_minimized_list.json";
    public static final String API_BRAND_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/list.json";
    public static final String API_GENERAL_GET_UPDATED_INFO = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/general/get_updated_informations.json";
    public static final String API_GET_BY_CATEGORY_BY_LOCATION = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/get_by_category_by_location.json";
    public static final String API_GOOGLE_PLAY = "http://maps.googleapis.com/maps/v.0.1/geocode/json?sensor=false";
    public static final String API_PROFILE_BRAND_ADD = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/add_brand.json";
    public static final String API_PROFILE_BRAND_DELETE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/delete_brand.json";
    public static final String API_PROFILE_BRAND_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_brands.json";
    public static final String API_PROFILE_GET_BRAND_SUGGESTED = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_brands_suggested.json";
    public static final String API_SUGGESTED_BRAND_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/get_by_category.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.brandcontentprovider";
    public static final String BRAND = "brand";
    public static final Uri BRANDS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.brandcontentprovider/brand");
    public static final String BRANDS_DIRECTORY_NAME = "brands";
    public static final String BRANDS_TABLE_NAME = "brands";
    public static final Uri BRAND_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.brandcontentprovider/brand");
    public static final String BRAND_STORES_FRAGMENT_TAG = "BRAND_STORES_FRAGMENT";
    public static final String CATEGORY_ID = "category_id";
    public static final String CONTENT_BRAND_TYPE = "application/json";
    public static final String IS_FAN = "is_fan";
    public static final String NAME = "name";
    public static final String NUMBER_OF_PROFILES = "number_of_fans";
    public static final String PROFILE_IDS = "profile_ids";
    public static final String SEARCH_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/search";
    public static final String SHOW_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/";
    public static final String STORE_IDS = "store_ids";
    public static final String URL = "url";
    
    public static String CreateIndexUrlForCatalogs(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/brands/" + Integer.toString(paramInt) + "/catalogs.json";
    }
    
    public static String CreateIndexUrlForNews(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/brands/" + Integer.toString(paramInt) + "/news.json";
    }
    
    public static String CreateIndexUrlForPhotos(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/brands/" + Integer.toString(paramInt) + "/photos.json";
    }
    
    public static String CreateIndexUrlForProducts(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/brands/" + Integer.toString(paramInt) + "/products.json";
    }
    
    public static String CreateIndexUrlForStores(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/brands/" + Integer.toString(paramInt) + "/stores.json";
    }
    
    public static final List<NameValuePair> addBrandToParams(String paramString1, int paramInt, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idbrand", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      return localArrayList;
    }
    
    public static List<NameValuePair> getAllBrandPosttoParam(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> getAllBrandToParam(String paramString1, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      return localArrayList;
    }
    
    public static List<NameValuePair> getDetailCatePostToParams(String paramString1, int paramInt1, String paramString2, int paramInt2, double paramDouble1, double paramDouble2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idcategory", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("latitude", String.valueOf(paramDouble1)));
      localArrayList.add(new BasicNameValuePair("longitude", String.valueOf(paramDouble2)));
      return localArrayList;
    }
    
    public static List<NameValuePair> getItemBrandPosttoParam(String paramString1, String paramString2, int paramInt, String paramString3, double paramDouble1, double paramDouble2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("idbrand", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("type", paramString3));
      localArrayList.add(new BasicNameValuePair("latitude", String.valueOf(paramDouble1)));
      localArrayList.add(new BasicNameValuePair("longitude", String.valueOf(paramDouble2)));
      return localArrayList;
    }
    
    public static List<NameValuePair> getLongtitudetoParams(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("address", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> new_loadFansByProfile(int paramInt, String paramString1, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idprofile", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      return localArrayList;
    }
    
    public static List<NameValuePair> postParamstoParams(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idprofile", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt3)));
      return localArrayList;
    }
  }
  
  public static final class Catalogs
    implements BaseColumns
  {
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.catalogcontentprovider";
    public static final String BRAND_ID = "brand_id";
    public static final String CATALOG = "catalog";
    public static final Uri CATALOGS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.catalogcontentprovider/catalog");
    public static final String CATALOGS_DIRECTORY_NAME = "catalogs";
    public static final String CATALOGS_TABLE_NAME = "catalogs";
    public static final Uri CATALOG_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.catalogcontentprovider/catalog/1");
    public static final String CONTENT = "content";
    public static final String CONTENT_CATALOG_TYPE = "application/json";
    public static final String TITLE = "title";
    public static final String URL = "url";
  }
  
  public static final class Categories
    implements BaseColumns
  {
    public static final String API_CITY_SEARCH = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/city/search.json";
    public static final String API_PRODUCTCATEGORY_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/category/list.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.categorycontentprovider";
    public static final String CATEGORIES = "categories";
    public static final Uri CATEGORIES_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.categorycontentprovider/category");
    public static final String CATEGORIES_DIRECTORY_NAME = "categories";
    public static final String CATEGORIES_ID = "categories_id";
    public static final String CATEGORIES_NAME = "categories_name";
    public static final String CATEGORIES_TABLE_NAME = "categories";
    public static final String CATEGORY = "category";
    public static final Uri CATEGORY_BRANDS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.categorycontentprovider/category/brand");
    public static final Uri CATEGORY_BRAND_SHOPS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.categorycontentprovider/category/brand/store");
    public static final Uri CATEGORY_BRAND_SHOP_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.categorycontentprovider/category/brand/store/1");
    public static final Uri CATEGORY_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.categorycontentprovider/category/1");
    public static final String CONTENT_CATEGORY_TYPE = "application/json";
    public static final String INDEX_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/categories.json";
    public static final String TITLE = "title";
    public static final String URL = "url";
    
    public static String CreateIndexUrlForBrand(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/categories/" + Integer.toString(paramInt) + "/brands.json";
    }
    
    public static List<NameValuePair> getListSearchCityNamePostParams(String paramString1, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("term", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      return localArrayList;
    }
    
    public static List<NameValuePair> load_all_categories_post_param(String paramString1, String paramString2, int paramInt, String paramString3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("type", paramString1));
      localArrayList.add(new BasicNameValuePair("apikey", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("language", paramString3));
      return localArrayList;
    }
    
    public static List<NameValuePair> load_all_new_categories_post_param(String paramString1, String paramString2, String paramString3, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("terminal", paramString3));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("last_update", String.valueOf(paramString4)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      return localArrayList;
    }
  }
  
  public static final class Comments
    implements BaseColumns
  {
    public static final String API_COMMENT_CREATE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/comment/create.json";
    public static final String API_COMMENT_DELETE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/comment/delete.json";
    public static final String API_COMMENT_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/comment/list.json";
    public static final String API_COMMENT_UPDATE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/comment/update.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.commentcontentprovider";
    public static final String BODY = "body";
    public static final String COMMENT = "comment";
    public static final Uri COMMENTS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.commentcontentprovider/comment");
    public static final String COMMENTS_TABLE_NAME = "comments";
    public static final Uri COMMENT_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.commentcontentprovider/comment/1");
    public static final String COMMENT_ID = "comment_id";
    public static final String CONTENT_COMMENT_TYPE = "application/json";
    public static final String PRODUCT_ID = "product_id";
    public static final String PROFILE_ID = "profile_id";
    
    public static List<NameValuePair> PostParamsFromParams(int paramInt1, int paramInt2, String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("product_id", Integer.toString(paramInt1)));
      localArrayList.add(new BasicNameValuePair("profile_id", Integer.toString(paramInt2)));
      localArrayList.add(new BasicNameValuePair("body", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> createCommentParams(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString4));
      localArrayList.add(new BasicNameValuePair("content", paramString2));
      localArrayList.add(new BasicNameValuePair("idproduct", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("title", paramString3));
      return localArrayList;
    }
    
    public static List<NameValuePair> deleteCommentParams(String paramString1, int paramInt, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idcomment", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      return localArrayList;
    }
    
    public static List<NameValuePair> loadListCommenttoParams(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idproduct", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("type", paramString3));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("terminal", paramString4));
      return localArrayList;
    }
    
    public static List<NameValuePair> updateCommentParams(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("content", paramString3));
      localArrayList.add(new BasicNameValuePair("idcomment", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("title", paramString4));
      return localArrayList;
    }
  }
  
  public static final class Home
    implements BaseColumns
  {
    public static final String API_KEY = "api_key";
    public static final String AVATAR_PROFILE = "avatar_session";
    public static final String CREATE_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/advertising/list.json";
    public static final String ID_PROFILE = "id_profile";
    public static final String IMAGE_AVERTISING = "avertising";
    public static final String USERNAME_PROFILE = "username_session";
    
    public static List<NameValuePair> loadAdvertisingToParams(String paramString1, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      return localArrayList;
    }
  }
  
  public static final class Likes
  {
    public static final String API_LIKE_CREATE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/like/create.json";
    public static final String API_LIKE_UPDATE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/like/update.json";
    public static final String LIKES_RESOURCE = "likes";
    public static final int LIKE_CANCEL = 4;
    public static final int LIKE_DREAM = 0;
    public static final int LIKE_GOT = 3;
    public static final int LIKE_ILIKE = 1;
    public static final int LIKE_NEXT = 2;
    public static final String LIKE_TYPE = "like_type";
    public static final String PRODUCT_ID = "product_id";
    public static final String PROFILE_ID = "profile_id";
    public static final String UPDATE_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/likes.json";
    
    public static List<NameValuePair> PostParamsFromParams(int paramInt1, int paramInt2, int paramInt3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("product_id", Integer.toString(paramInt1)));
      localArrayList.add(new BasicNameValuePair("profile_id", Integer.toString(paramInt2)));
      localArrayList.add(new BasicNameValuePair("like_type", Integer.toString(paramInt3)));
      return localArrayList;
    }
    
    public static List<NameValuePair> createLikeFromParams(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString4));
      localArrayList.add(new BasicNameValuePair("type", paramString2));
      localArrayList.add(new BasicNameValuePair("message", paramString3));
      localArrayList.add(new BasicNameValuePair("idproduct", String.valueOf(paramInt)));
      return localArrayList;
    }
    
    public static List<NameValuePair> updateLikeFromParams(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString4));
      localArrayList.add(new BasicNameValuePair("type", paramString2));
      localArrayList.add(new BasicNameValuePair("message", paramString3));
      localArrayList.add(new BasicNameValuePair("idproduct", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("idlike", paramString5));
      return localArrayList;
    }
  }
  
  public static enum Methods
  {
    DELETE,  PUT,  POST,  GET;
  }
  
  public static final class News
    implements BaseColumns
  {
    public static final String API_NEWS_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/news/list.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.newscontentprovider";
    public static final String BRAND_ID = "brand_id";
    public static final String CONTENT = "content";
    public static final String CONTENT_NEWS_TYPE = "application/json";
    public static final String INDEX_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/news.json";
    public static final String NEWS = "news";
    public static final Uri NEWS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.newscontentprovider/news");
    public static final String NEWS_DIRECTORY_NAME = "news";
    public static final String NEWS_TABLE_NAME = "news";
    public static final Uri NEW_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.newscontentprovider/news/1");
    public static final String SEARCH_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/news/search";
    public static final String TITLE = "title";
    public static final String URL = "url";
    
    public static List<NameValuePair> loadNewNewsPostParam(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("type", paramString1));
      localArrayList.add(new BasicNameValuePair("apikey", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("language", paramString3));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      return localArrayList;
    }
  }
  
  public static final class Notification
    implements BaseColumns
  {
    public static final String API_GET_NOTIFICATION = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_notifications.json";
    public static final String API_GET_NOTIFICATION_SETTING = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_notifications_settings.json";
    public static final String API_SET_NOTIFICATION = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/set_notifications_settings.json";
    
    public static List<NameValuePair> getNoticationToParams(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("date_start", String.valueOf(paramString2)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("type", paramString3));
      localArrayList.add(new BasicNameValuePair("terminal", paramString4));
      return localArrayList;
    }
    
    public static List<NameValuePair> getNotificationSetting(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> setNotificationSettingToParams(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString));
      localArrayList.add(new BasicNameValuePair("new_follower", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("new_comment_on_my_product", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("following_add_product", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("following_add_comment", String.valueOf(paramInt4)));
      localArrayList.add(new BasicNameValuePair("following_add_picture", String.valueOf(paramInt5)));
      return localArrayList;
    }
  }
  
  public static final class Photos
    implements BaseColumns
  {
    public static final String API_GALLERYPHOTO_CREATE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/user_gallery/create.json";
    public static final String API_GALLERYPHOTO_DELETE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/user_gallery/delete.json";
    public static final String API_GALLERYPHOTO_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/user_gallery/list.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.photocontentprovider";
    public static final String BRAND_ID = "brand_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String CONTENT_PHOTO_TYPE = "application/json";
    public static final String INDEX_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/photos.json";
    public static final String PHOTOS = "photos";
    public static final Uri PHOTOS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.photocontentprovider/photos");
    public static final String PHOTOS_DIRECTORY_NAME = "photos";
    public static final String PHOTOS_TABLE_NAME = "photos";
    public static final Uri PHOTO_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.photocontentprovider/photos/1");
    public static final String URL = "url";
    
    public static List<NameValuePair> photos_profile_paramtoparam(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("iduser", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("type", paramString3));
      localArrayList.add(new BasicNameValuePair("terminal", paramString4));
      return localArrayList;
    }
  }
  
  public static final class Products
    implements BaseColumns
  {
    public static final String ADDED_BY = "added_by";
    public static final String API_LIKE_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/like/list.json";
    public static final String API_PRODUCT_GET = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/product/get.json";
    public static final String API_PRODUCT_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/product/list.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.productcontentprovider";
    public static final String BRAND_ID = "brand_id";
    public static final String CONTENT_PRODUCT_TYPE = "application/json";
    public static final String FILTER = "filter";
    public static final String INDEX_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/list.json";
    public static final String LIKE_TYPE = "like_type";
    public static final String NAME_PRODUCT = "product_name";
    public static final String NEW = "new";
    public static final int NEW_VALUE = 0;
    public static final String NUMBER_OF_COMMENTS = "number_of_comments";
    public static final String NUMBER_OF_LIKES = "number_of_likes";
    public static final String POPULAR = "popular";
    public static final int POPULAR_VALUE = 2;
    public static final String PRODUCT = "product";
    public static final Uri PRODUCTS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.productcontentprovider/product");
    public static final String PRODUCTS_DIRECTORY_NAME = "products";
    public static final Uri PRODUCTS_NEW_CONTENT_URI;
    public static final Uri PRODUCTS_POPULAR_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.productcontentprovider/product/popular");
    public static final Uri PRODUCTS_SUGGESTED_CONTENT_URI;
    public static final String PRODUCTS_TABLE_NAME = "products";
    public static final Uri PRODUCT_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.productcontentprovider/product");
    public static final String PRODUCT_USER = "product_user";
    public static final String SEARCH_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/product/search";
    public static final String SHOW_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/product/";
    public static final String SUGGESTED = "suggested";
    public static final int SUGGESTED_VALUE = 1;
    public static final String TITLE = "title";
    public static final String TYPE = "type";
    public static final String TYPE_LIKE = "type_like";
    public static final String URL = "url";
    
    static
    {
      PRODUCTS_NEW_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.productcontentprovider/product/new");
      PRODUCTS_SUGGESTED_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.productcontentprovider/product/suggested");
    }
    
    public static String CreateIndexUrlForComment(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/products/" + Integer.toString(paramInt) + "/comments.json";
    }
    
    public static String CreatePostUrlForComment(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/products/" + Integer.toString(paramInt) + "/comments.json";
    }
    
    public static String CreateUploadUrlForProduct()
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/products.json";
    }
    
    public static List<NameValuePair> filterBrandParamstoParams(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, int paramInt3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("producttype", paramString1));
      localArrayList.add(new BasicNameValuePair("apikey", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("language", paramString3));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("idbrand", String.valueOf(paramInt1)));
      return localArrayList;
    }
    
    public static List<NameValuePair> filterCatepostParamstoParams(String paramString1, String paramString2, int paramInt1, int paramInt2, String paramString3, int paramInt3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("producttype", paramString1));
      localArrayList.add(new BasicNameValuePair("apikey", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("language", paramString3));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("idcategory", String.valueOf(paramInt1)));
      return localArrayList;
    }
    
    public static List<NameValuePair> getProduct(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idproduct", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("type", paramString3));
      localArrayList.add(new BasicNameValuePair("terminal", paramString4));
      return localArrayList;
    }
    
    public static List<NameValuePair> like_profile_postParamtoparam(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idprofile", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt3)));
      return localArrayList;
    }
    
    public static List<NameValuePair> postParamstoParams(String paramString1, String paramString2, int paramInt1, String paramString3, int paramInt2, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("producttype", paramString1));
      localArrayList.add(new BasicNameValuePair("apikey", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("language", paramString3));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("terminal", paramString4));
      return localArrayList;
    }
    
    public static List<NameValuePair> productByBrandIDToParams(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idbrand", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("terminal", paramString4));
      return localArrayList;
    }
    
    public static List<NameValuePair> productLike_postParamtoparam(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, String paramString3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idproduct", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("terminal", paramString3));
      return localArrayList;
    }
    
    public static List<NameValuePair> productUpdateLikeToParams(String paramString1, String paramString2, int paramInt1, int paramInt2)
    {
      return new ArrayList<NameValuePair>();
    }
  }
  
  public static final class Profiles
    implements BaseColumns
  {
    public static final String API_EMAIL_USED = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/email_used.json";
    public static final String API_PROFILE_CREATION = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/create.json";
    public static final String API_PROFILE_FOLLOWING_ADD = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/add_following.json";
    public static final String API_PROFILE_FOLLOWING_DELETE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/delete_following.json";
    public static final String API_PROFILE_GET = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get.json";
    public static final String API_PROFILE_GET_FOLLOWERS = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_followers.json";
    public static final String API_PROFILE_GET_FOLLOWINGS = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_followings.json";
    public static final String API_PROFILE_GET_LIKES = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_likes.json";
    public static final String API_PROFILE_GET_PROFESSION = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_profession.json";
    public static final String API_PROFILE_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/list.json";
    public static final String API_PROFILE_LOGOUT = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/logout.json";
    public static final String API_PROFILE_UPDATE = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/update.json";
    public static final String API_RESET_PASSWORD = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/reset_password.json";
    public static final String API_SEARCH_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/search/list.json";
    public static final String API_USERNAME_USED = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/username_used.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.profilecontentprovider";
    public static final String BRAND_ID = "brand_id";
    public static final String BRAND_IDS = "brand_ids";
    public static final String CAREER = "career";
    public static final String CONTENT_PROFILE_TYPE = "application/json";
    public static final String CREATE_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/create.json";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String EMAIL = "email";
    public static final String FILTER = "filter";
    public static final String FIRST_NAME = "firstname";
    public static final String FOLLOWING_IDS = "following_ids";
    public static final String FOLLOW_ID = "follow_id";
    public static final String FOLLOW_IDS = "follow_ids";
    public static final String INDEX_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/create.json";
    public static final String LAST_NAME = "lastname";
    public static final String NEW = "new";
    public static final int NEW_VALUE = 0;
    public static final String NUMBER_OF_BRANDS = "number_of_brands";
    public static final String NUMBER_OF_FOLLOWINGS = "number_of_followings";
    public static final String NUMBER_OF_FOLLOWS = "number_of_follows";
    public static final String NUMBER_OF_PHOTOS = "number_of_photos";
    public static final String NUMBER_OF_PRODUCTS = "number_of_products";
    public static final String PASSWORD = "password";
    public static final String PHOTO_IDS = "photo_ids";
    public static final String POPULAR = "popular";
    public static final int POPULAR_VALUE = 2;
    public static final String PRODUCT_IDS = "product_ids";
    public static final String PROFILE = "profile";
    public static final Uri PROFILES_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.profilecontentprovider/profile");
    public static final String PROFILES_DIRECTORY_NAME = "profiles";
    public static final String PROFILES_FOLLOWINGS_MODELS_KEY_NAME = "followings";
    public static final String PROFILES_FOLLOWS_MODELS_KEY_NAME = "follows";
    public static final Uri PROFILES_NEW_CONTENT_URI;
    public static final Uri PROFILES_POPULAR_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.profilecontentprovider/profile/popular");
    public static final Uri PROFILES_SUGGESTED_CONTENT_URI;
    public static final String PROFILES_TABLE_NAME = "profiles";
    public static final Uri PROFILE_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.profilecontentprovider/profile");
    public static final String PROFILE_ID = "profile_id";
    public static final String SEARCH_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/search.json";
    public static final String SHOW_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/";
    public static final String SUGGESTED = "suggested";
    public static final int SUGGESTED_VALUE = 1;
    public static final String TYPE = "type";
    public static final String URL = "url";
    public static final String USERNAME = "username";
    
    static
    {
      PROFILES_NEW_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.profilecontentprovider/profile/new");
      PROFILES_SUGGESTED_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.profilecontentprovider/profile/suggested");
    }
    
    public static String CreateGetUrlToShowAProfile(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/" + Integer.toString(paramInt) + ".json";
    }
    
    public static String CreateIndexUrlForBrands(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/" + Integer.toString(paramInt) + "/brands.json";
    }
    
    public static String CreateIndexUrlForFollowings(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/" + Integer.toString(paramInt) + "/followings.json";
    }
    
    public static String CreateIndexUrlForFollows(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/" + Integer.toString(paramInt) + "/follows.json";
    }
    
    public static String CreateIndexUrlForPhotos(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/" + Integer.toString(paramInt) + "/photos.json";
    }
    
    public static String CreateIndexUrlForProducts(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/" + Integer.toString(paramInt) + "/products.json";
    }
    
    public static String CreatePostUrlToFollowAProfile(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profiles/" + Integer.toString(paramInt) + "/follows.json";
    }
    
    public static String CreatePutUrlToLikeABrand(int paramInt1, int paramInt2)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/" + Integer.toString(paramInt1) + "/brands/" + Integer.toString(paramInt2) + ".json";
    }
    
    public static List<NameValuePair> PostParamsFromParams(int paramInt)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("follow_id", Integer.toString(paramInt)));
      return localArrayList;
    }
    
    public static List<NameValuePair> PutParamsFromParams(int paramInt1, int paramInt2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("brand_id", Integer.toString(paramInt1)));
      localArrayList.add(new BasicNameValuePair("profile_id", Integer.toString(paramInt2)));
      return localArrayList;
    }
    
    public static List<NameValuePair> checkExistedEmailToParams(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("email", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> checkExistedUserToParams(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("username", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> createProfileToParams(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt1, String paramString7, String paramString8, String paramString9, int paramInt2, int paramInt3, String paramString10, String paramString11, String paramString12)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("language", paramString12));
      localArrayList.add(new BasicNameValuePair("firstname", paramString1));
      localArrayList.add(new BasicNameValuePair("lastname", paramString2));
      localArrayList.add(new BasicNameValuePair("birthdate", paramString3));
      localArrayList.add(new BasicNameValuePair("gender", paramString4));
      localArrayList.add(new BasicNameValuePair("email", paramString5));
      localArrayList.add(new BasicNameValuePair("password", paramString6));
      localArrayList.add(new BasicNameValuePair("idcity", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("activity", paramString7));
      localArrayList.add(new BasicNameValuePair("username", paramString8));
      localArrayList.add(new BasicNameValuePair("picture", paramString9));
      localArrayList.add(new BasicNameValuePair("display_real_name", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("display_birthdate", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("profile_picture_rectangle", paramString10));
      localArrayList.add(new BasicNameValuePair("profile_picture_square", paramString11));
      return localArrayList;
    }
    
    public static List<NameValuePair> getLikeProfileToParams(String paramString1, int paramInt1, String paramString2, int paramInt2, int paramInt3, String paramString3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idprofile", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("terminal", paramString3));
      return localArrayList;
    }
    
    public static List<NameValuePair> getProfessionUser(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("language", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> new_post_followers_following_toparams(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idprofile", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("terminal", paramString2));
      return localArrayList;
    }
    
    public static List<NameValuePair> post_followers_paramtoparam(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idfollowing", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt3)));
      return localArrayList;
    }
    
    public static List<NameValuePair> post_following_paramtoparam(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idfollower", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt3)));
      return localArrayList;
    }
    
    public static List<NameValuePair> post_logout_profile(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> post_new_paramsfromparams(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4, String paramString5)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("searchtype", paramString1));
      localArrayList.add(new BasicNameValuePair("apikey", paramString2));
      localArrayList.add(new BasicNameValuePair("language", paramString3));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("type", paramString4));
      localArrayList.add(new BasicNameValuePair("terminal", paramString5));
      return localArrayList;
    }
    
    public static List<NameValuePair> post_profileDiary_paramtoparam(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("idprofile", paramString1));
      localArrayList.add(new BasicNameValuePair("revision", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("type", paramString2));
      localArrayList.add(new BasicNameValuePair("apikey", paramString3));
      localArrayList.add(new BasicNameValuePair("language", paramString4));
      localArrayList.add(new BasicNameValuePair("terminal", paramString5));
      return localArrayList;
    }
    
    public static List<NameValuePair> post_profile_following_add(String paramString1, String paramString2, String paramString3, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("iduser", paramString1));
      localArrayList.add(new BasicNameValuePair("idfollowing", paramString2));
      localArrayList.add(new BasicNameValuePair("apikey", paramString3));
      localArrayList.add(new BasicNameValuePair("language", paramString4));
      return localArrayList;
    }
    
    public static List<NameValuePair> resetPasswordToParams(String paramString)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("email", paramString));
      return localArrayList;
    }
    
    public static List<NameValuePair> searchListProfilePosttoParams(String paramString1, int paramInt, String paramString2, String paramString3, double paramDouble1, double paramDouble2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("term", paramString3));
      localArrayList.add(new BasicNameValuePair("latitude", String.valueOf(paramDouble1)));
      localArrayList.add(new BasicNameValuePair("longitude", String.valueOf(paramDouble2)));
      return localArrayList;
    }
    
    public static List<NameValuePair> unfollow_follow_profile(String paramString1, String paramString2, String paramString3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idfollowing", paramString2));
      localArrayList.add(new BasicNameValuePair("language", paramString3));
      return localArrayList;
    }
    
    public static List<NameValuePair> updateAvatar(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("iduser", paramString3));
      localArrayList.add(new BasicNameValuePair("profile_picture_rectangle", paramString4));
      localArrayList.add(new BasicNameValuePair("profile_picture_square", paramString5));
      return localArrayList;
    }
    
    public static List<NameValuePair> updateProfileToParams(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, String paramString8, String paramString9, String paramString10, int paramInt2, int paramInt3, String paramString11, String paramString12)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("firstname", paramString3));
      localArrayList.add(new BasicNameValuePair("lastname", paramString4));
      localArrayList.add(new BasicNameValuePair("birthdate", paramString5));
      localArrayList.add(new BasicNameValuePair("gender", paramString6));
      localArrayList.add(new BasicNameValuePair("email", paramString7));
      localArrayList.add(new BasicNameValuePair("idcity", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("activity", paramString8));
      localArrayList.add(new BasicNameValuePair("username", paramString9));
      localArrayList.add(new BasicNameValuePair("picture", paramString10));
      localArrayList.add(new BasicNameValuePair("display_real_name", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("display_birthdate", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("profile_picture_rectangle", paramString11));
      localArrayList.add(new BasicNameValuePair("profile_picture_square", paramString12));
      return localArrayList;
    }
    
    public static List<NameValuePair> updateStatus(String paramString1, String paramString2, int paramInt, String paramString3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("iduser", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("description", paramString3));
      return localArrayList;
    }
  }
  
  public static final class Questions
    implements BaseColumns
  {
    public static final String API_QUIZZ_ANSWER = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/quizz/set_answers_for_user.json";
    public static final String API_QUIZZ_GET = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/quizz/get_for_user.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.questioncontentprovider";
    public static final String CONTENT_QUESTION_TYPE = "application/json";
    public static final String INDEX_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/questions.json";
    public static final String QUESTION = "question";
    public static final Uri QUESTIONS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.questioncontentprovider/question");
    public static final String QUESTIONS_DIRECTORY_NAME = "questions";
    public static final String QUESTIONS_TABLE_NAME = "questions";
    public static final Uri QUESTION_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.questioncontentprovider/question/1");
    public static final String URL = "url";
    
    public static List<NameValuePair> getQuestionUserToParam(String paramString1, String paramString2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("terminal", paramString2));
      return localArrayList;
    }
    
    public static List<NameValuePair> setAnswerForUserToParams(String paramString1, String paramString2, String paramString3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("answers", paramString3));
      return localArrayList;
    }
  }
  
  public static final class Search
    implements BaseColumns
  {
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.searchcontentprovider";
    public static final String BASE_URL_KEY = "base_url_key";
    public static final String CONTENT_SEARCH_TYPE = "application/json";
    public static final String MODEL = "model";
    public static final String MODEL_ID = "model_id";
    public static final String QUERY = "query";
    public static final String RESULT = "result";
    public static final String SEARCH = "search";
    public static final Uri SEARCH_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.searchcontentprovider/search");
    public static final String SEARCH_TABLE_NAME = "searches";
    public static final String UPDATED_AT = "updated_at";
    public static final String URL = "url";
  }
  
  public static final class Sessions
  {
    public static final String CREATE_URL = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/login.json";
    public static final String EMAIL = "email";
    public static final String LANGUAGE = Locale.getDefault().getLanguage();
    public static final String PASSWORD = "password";
    public static final String REVISION = "1";
    public static final String SESSIONS = "sessions";
    public static final String SESSION_ID = "session_id";
    
    public static List<NameValuePair> PostParamsFromParams(String paramString1, String paramString2, String paramString3, String paramString4)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("email", paramString1));
      localArrayList.add(new BasicNameValuePair("password", paramString2));
      localArrayList.add(new BasicNameValuePair("language", LANGUAGE));
      localArrayList.add(new BasicNameValuePair("revision", "1"));
      return localArrayList;
    }
  }
  
  public static final class StatusCodes
  {
    public static final int FAILURE = 500;
    public static final int OK = 200;
  }
  
  public static final class Stores
    implements BaseColumns
  {
    public static final String ADDRESS = "address";
    public static final String ADDRESS_URL = "address_url";
    public static final String API_SHOPS_GET = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/shop/get.json";
    public static final String API_SHOPS_LIST = "http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/shop/list.json";
    public static final String AUTHORITY = "com.hitechno.sogoods.providers.storecontentprovider";
    public static final String BRAND_IDS = "brand_ids";
    public static final String CONTENT_STORE_TYPE = "application/json";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String NAME = "name";
    public static final String STORE = "store";
    public static final Uri STORES_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.storecontentprovider/store");
    public static final String STORES_DIRECTORY_NAME = "stores";
    public static final String STORES_TABLE_NAME = "stores";
    public static final Uri STORE_BRANDS_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.storecontentprovider/store/brand");
    public static final Uri STORE_CONTENT_URI = Uri.parse("content://com.hitechno.sogoods.providers.storecontentprovider/store/1");
    
    public static String CreateIndexUrlForBrands(int paramInt)
    {
      return "http://webservices.preprod.sogoods.hitechno-ltd.com/stores/" + Integer.toString(paramInt) + "/brands.json";
    }
    
    public static List<NameValuePair> getStoreByIDbrandToParams(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idbrand", String.valueOf(paramInt1)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("type", paramString3));
      localArrayList.add(new BasicNameValuePair("maxresults", String.valueOf(paramInt2)));
      localArrayList.add(new BasicNameValuePair("offset", String.valueOf(paramInt3)));
      localArrayList.add(new BasicNameValuePair("latitude", String.valueOf(paramDouble1)));
      localArrayList.add(new BasicNameValuePair("longitude", String.valueOf(paramDouble2)));
      return localArrayList;
    }
    
    public static List<NameValuePair> getStoreByidPosttoParam(String paramString1, int paramInt, String paramString2, String paramString3)
    {
      ArrayList<NameValuePair> localArrayList = new ArrayList<NameValuePair>();
      localArrayList.add(new BasicNameValuePair("apikey", paramString1));
      localArrayList.add(new BasicNameValuePair("idshop", String.valueOf(paramInt)));
      localArrayList.add(new BasicNameValuePair("language", paramString2));
      localArrayList.add(new BasicNameValuePair("type", paramString3));
      return localArrayList;
    }
  }
}
