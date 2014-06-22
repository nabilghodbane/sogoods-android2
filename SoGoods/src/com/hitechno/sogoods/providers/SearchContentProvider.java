package com.hitechno.sogoods.providers;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.helpers.DatabaseHelper;

/**
 * Implements a RESTful interface to the Search resource.
 * 
 */
public class SearchContentProvider extends RestfulContentProvider {

	public SearchContentProvider() {
		super();
	}

	private static final int SEARCH = 1;

	private static UriMatcher sUriMatcher;

	/**
	 * Statically creates a URI matcher that can detect URIs referencing more
	 * than 1 news item, a single news item.
	 */
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(ConstantsHelper.Search.AUTHORITY,
				ConstantsHelper.Search.SEARCH, SEARCH);
	}

	private DatabaseHelper databaseHelper;
	private SQLiteDatabase database;

	@Override
	public boolean onCreate() {
		initialize();
		return true;
	}

	private void initialize() {
		databaseHelper = new DatabaseHelper(getContext(),
				ConstantsHelper.DATABASE_NAME, null,
				ConstantsHelper.DATABASE_VERSION);
		database = databaseHelper.getWritableDatabase();
	}

	/**
	 * Returns an instance of the database that contains this
	 * <code>ContentProvider</code>'s resource.
	 */
	@Override
	public SQLiteDatabase getDatabase() {
		return database;
	}

	@Override
	public Uri insert(Uri uri, ContentValues cv, SQLiteDatabase db) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case SEARCH:
			return ConstantsHelper.Search.CONTENT_SEARCH_TYPE;

		default:
			throw new IllegalArgumentException("Unknown comment type: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Verify the values
		if (sUriMatcher.match(uri) != SEARCH) {
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		long rowId = getDatabase().insert(
				ConstantsHelper.Search.SEARCH_TABLE_NAME, null, values);
		if (rowId >= 0) {
			Uri insertUri = ContentUris.withAppendedId(
					ConstantsHelper.Search.SEARCH_CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(insertUri, null);
			return insertUri;
		}

		throw new IllegalStateException("could not insert "
				+ "content values: " + values);
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		if (sUriMatcher.match(uri) != SEARCH) {
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		for (ContentValues value : values) {
			if (value != null)
				insert(uri, value);
		}
		return 0;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor queryCursor;

		int match = sUriMatcher.match(uri);
		switch (match) {
		case SEARCH:
			queryCursor = database.query(
					ConstantsHelper.Search.SEARCH_TABLE_NAME, projection,
					selection, selectionArgs, null, null, sortOrder);

			// Cursor should observe the requested query
			queryCursor.setNotificationUri(getContext().getContentResolver(),
					uri);
			break;
		default:
			throw new IllegalArgumentException("Invalid URI: " + uri);
		}

		return queryCursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] selectionArgs) {
		return 0;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
}
