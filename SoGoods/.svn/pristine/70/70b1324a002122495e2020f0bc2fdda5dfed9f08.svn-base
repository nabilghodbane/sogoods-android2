package com.hitechno.sogoods.databases;

import android.content.Context;

public class DBManager {

	private static ProfessionDBAdapter professionTable;
	private static CategoryDBAdapter categoryTable;
	private static BrandDBAdapter brandTable;

	private static DBAdapter mainAdapter;

	private DBManager() {
	}

	private static void createDatabases(Context context) {
		if (mainAdapter == null) {
			mainAdapter = new DBAdapter(context);
			mainAdapter.open();
		}
	}

	public static ProfessionDBAdapter getProfessionDBAdapter(Context context) {
		createDatabases(context);
		if (professionTable == null) {
			professionTable = new ProfessionDBAdapter(context);
			professionTable.setDatabase(mainAdapter.getDatabase());
		}
		return professionTable;
	}

	public static CategoryDBAdapter getCategoryDBAdapter(Context context) {
		createDatabases(context);
		if (categoryTable == null) {
			categoryTable = new CategoryDBAdapter(context);
			categoryTable.setDatabase(mainAdapter.getDatabase());
		}
		return categoryTable;
	}

	public static BrandDBAdapter getBrandDBAdapter(Context context) {
		createDatabases(context);
		if (brandTable == null) {
			brandTable = new BrandDBAdapter(context);
			brandTable.setDatabase(mainAdapter.getDatabase());
		}
		return brandTable;
	}
}
