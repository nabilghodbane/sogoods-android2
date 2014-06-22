package com.hitechno.sogoods.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Abstract class that defines several methods for subclasses to define.
 * 
 */
public abstract class RestfulContentProvider extends ContentProvider {

	public abstract Uri insert(Uri uri, ContentValues cv, SQLiteDatabase db);

	/**
	 * Allows a subclass to define the database for a particular resource.
	 * 
	 * @return the database for a particular resource
	 */
	public abstract SQLiteDatabase getDatabase();

	public RestfulContentProvider() {
		super();
	}
}
