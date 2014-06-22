package com.hitechno.sogoods.managers;
//package com.hitechno.sogoods.managers;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v4.app.LoaderManager.LoaderCallbacks;
//import android.support.v4.content.CursorLoader;
//import android.support.v4.content.Loader;
//import android.util.Log;
//
//import com.hitechno.sogoods.adapters.BrandsAdapter;
//import com.hitechno.sogoods.helpers.ConstantsHelper;
//import com.hitechno.sogoods.interfaces.GridItemModelInterface;
//import com.hitechno.sogoods.models.Brand;
//
//public class BrandsLoaderManager implements LoaderCallbacks<Cursor> {
//
//	private Context context;
//	private RequestTasksManager tasksManager;
//
//	private int categoryId;
//	private ArrayList<GridItemModelInterface> brands;
//	private BrandsAdapter adapter;
//
//	public BrandsLoaderManager(Context context) {
//		this.context = context;
//	}
//
//	public BrandsLoaderManager(Context context, BrandsAdapter adapter) {
//		this.context = context;
//		this.adapter = adapter;
//		tasksManager = RequestTasksManager.defaultManager(context);
//
//		brands = new ArrayList<GridItemModelInterface>();
//	}
//
//	@Override
//	public Loader<Cursor> onCreateLoader(int id, Bundle savedInstanceState) {
//		CursorLoader loader;
//		if (savedInstanceState != null) {
//			categoryId = savedInstanceState
//					.getInt(ConstantsHelper.Brands.CATEGORY_ID);
//
//			if (categoryId == 0) {
//				loader = new CursorLoader(context,
//						ConstantsHelper.Brands.BRANDS_CONTENT_URI, null, null,
//						null, null);
//			} else {
//				loader = new CursorLoader(context,
//						ConstantsHelper.Brands.BRANDS_CONTENT_URI, null,
//						ConstantsHelper.Brands.CATEGORY_ID + " = ?",
//						new String[] { Integer.toString(categoryId) }, null);
//			}
//		} else {
//			loader = new CursorLoader(context,
//					ConstantsHelper.Brands.BRANDS_CONTENT_URI, null, null,
//					null, null);
//		}
//		return loader;
//	}
//
//	@Override
//	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//		brands.clear();
//
//		Cursor mostRecentBrandCursor = tasksManager
//				.getContext()
//				.getContentResolver()
//				.query(ConstantsHelper.Brands.BRANDS_CONTENT_URI,
//						new String[] { "updated_at" }, null, null,
//						"updated_at DESC LIMIT 1");
//		Date mostRecentBrand = null;
//		SimpleDateFormat dateFormatter = new SimpleDateFormat(
//				ConstantsHelper.DATE_FORMAT, ConstantsHelper.LOCALE);
//		try {
//			mostRecentBrand = dateFormatter.parse(ConstantsHelper.START_DATE);
//		} catch (ParseException e) {
//			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
//					+ e.getMessage());
//		}
//
//		int mostRecentlyUpdatedAtColumnIndex = mostRecentBrandCursor
//				.getColumnIndexOrThrow(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT);
//		try {
//			if (mostRecentBrandCursor.getCount() > 0) {
//				mostRecentBrandCursor.moveToFirst();
//				String updatedAt = mostRecentBrandCursor
//						.getString(mostRecentlyUpdatedAtColumnIndex);
//				mostRecentBrand = new SimpleDateFormat(
//						ConstantsHelper.DATE_FORMAT, ConstantsHelper.LOCALE)
//						.parse(updatedAt);
//			}
//		} catch (ParseException e) {
//			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
//					+ e.getMessage());
//		}
//
//		/*
//		 * A model's #id method will reference the remote :id, not the local
//		 * :id.
//		 */
//		int idColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID);
//		int nameColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.Brands.NAME);
//		int fansColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.Brands.NUMBER_OF_PROFILES);
//		int isFanColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.Brands.IS_FAN);
//		int urlColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.Brands.URL);
//		int storeIdsColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.Brands.STORE_IDS);
//		int updatedAtColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT);
//
//		while (cursor.moveToNext()) {
//			Brand brand = new Brand();
//			brand.id = cursor.getInt(idColumnIndex);
//			brand.name = cursor.getString(nameColumnIndex);
//			brand.numberOfProfiles = cursor.getInt(fansColumnIndex);
//			brand.isFan = cursor.getInt(isFanColumnIndex);
//			brand.url = cursor.getString(urlColumnIndex);
//			brand.storeIdsString = cursor.getString(storeIdsColumnIndex);
//			brand.updatedAt = cursor.getString(updatedAtColumnIndex);
//			brands.add(brand);
//		}
//
//		adapter.setModels(brands);
//
//		/**
//		 * The current queryCursor will contain the cached data. We make a
//		 * request using the most recently added data, identified by its
//		 * updated_at column, to fetch new data.
//		 */
//
//		/*
//		 * The BrandFragment is inflated from CategoriesBrandFragment and this
//		 * LoaderManager is instantiated initially without the user selecting a
//		 * category. Avoid making a call in this case.
//		 */
//		if (categoryId == 0)
//			return;
//
//		String url = tasksManager.setUrlUsingDate(
//				ConstantsHelper.Categories.CreateIndexUrlForBrand(categoryId),
//				mostRecentBrand);
//		tasksManager.setUpdatedAtForRequest(mostRecentBrand, url);
//		tasksManager.setContentUriForRequest(
//				ConstantsHelper.Brands.BRAND_CONTENT_URI, url);
//		tasksManager.index(url);
//	}
//
//	@Override
//	public void onLoaderReset(Loader<Cursor> loader) {
//		// Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() +
//		// ": onLoaderReset");
//	}
//}
