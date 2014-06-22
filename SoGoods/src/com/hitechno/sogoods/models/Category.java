package com.hitechno.sogoods.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

import org.json.JSONArray;

import android.content.ContentValues;
import android.database.Cursor;

import com.hitechno.sogoods.constant.ConstantsHelper;

public class Category implements Comparable<Category> {

	public String title;
	public String url;
	public int categoryID;
	public ArrayList<Category> categories;
	public String subChar;
	public ArrayList<String> lstStringName;
	public String lstStringBrand;
	public int status;
	public int id;
	public int trueId;
	public String updatedAt;

	public Category() {
		lstStringName = new ArrayList<String>();
		categories = new ArrayList<Category>();
	}

	public ContentValues getContentValues() {
		ContentValues values = new ContentValues();
		values.put(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID, id);
		values.put(ConstantsHelper.Categories.TITLE, title);
		values.put(ConstantsHelper.Categories.URL, url);
		return values;
	}

	/**
	 * Transforms the deserialized title and url data into {@link ContentValues}
	 * .
	 * 
	 * @return the <code>ContentValues</code> representation of the model
	 */
	public ContentValues getTitleAndUrlValues() {
		ContentValues values = new ContentValues();
		values.put(ConstantsHelper.Categories.TITLE, title);
		values.put(ConstantsHelper.Categories.URL, url);
		return values;
	}

	/**
	 * Transforms the deserialized subcategory data into {@link ContentValues}.
	 * These values are sideloaded into their parent category's database row.
	 * 
	 * @return the <code>ContentValues</code> representation of the model
	 */
	public ContentValues getTitleAndCategoriesValues() {
		ContentValues values = new ContentValues();

		String concatenatedCategoryTitles = "";
		ListIterator<Category> categoriesIterator = categories.listIterator();
		while (categoriesIterator.hasNext()) {
			Category currentCategory = categoriesIterator.next();

			if (categoriesIterator.hasNext()) {
				concatenatedCategoryTitles = concatenatedCategoryTitles
						.concat(currentCategory.title + ",");
			} else {
				concatenatedCategoryTitles = concatenatedCategoryTitles
						.concat(currentCategory.title);
			}
		}

		values.put(ConstantsHelper.Categories.TITLE, title);
		values.put(ConstantsHelper.Categories.CATEGORIES,
				concatenatedCategoryTitles);
		return values;
	}

	public static Comparator<Category> catNameComparator = new Comparator<Category>() {
		@Override
		public int compare(Category cat1, Category cat2) {
			String cate1 = cat1.title.toUpperCase();
			String cate2 = cat2.title.toUpperCase();
			return cate1.compareTo(cate2);
		}
	};

	@Override
	public int compareTo(Category arg0) {
		return 0;
	}

	public static Category initUnitFromCursor(Cursor cursor) {
		Category category = new Category();
		category.categoryID = cursor.getInt(1);
		category.title = cursor.getString(2);
		category.subChar = cursor.getString(3);
		category.url = cursor.getString(4);
		category.lstStringBrand = cursor.getString(5);
		category.status = cursor.getInt(6);
		return category;
	}

	public ArrayList<String> initArrayBrandId() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			JSONArray arrayJ = new JSONArray(lstStringBrand);
			for (int i = 0; i < arrayJ.length(); i++) {
				result.add(arrayJ.getString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
