package com.hitechno.sogoods.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.StoreLocatorAcivity;
import com.hitechno.sogoods.adapters.CategoriesAdapter;
import com.hitechno.sogoods.api.APIProductAndBrand;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.databases.BrandDBAdapter;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.models.CategoryBrand;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;

public class StoreLocatorFragment extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";
	public static int CLICK_SEARCH_CAT;

	private ListView listView;
	private ProgressBar progressBar;

	private CategoryDBAdapter categoryDB;
	private BrandDBAdapter brandDB;

	private Context context;

	private String apiKey;
	private Configuration config;

	private ArrayList<Category> categories;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();

		apiKey = MySharePreference.getApiKey(context);
		config = Configuration.getInstance(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_store_locator, null);

		EditText editText = (EditText) view.findViewById(R.id.searchCategories);
		listView = (ListView) view.findViewById(R.id.storelocator_listview);
		progressBar = (ProgressBar) view
				.findViewById(R.id.storelocator_progressbar);

		categoryDB = DBManager.getCategoryDBAdapter(context);
		brandDB = DBManager.getBrandDBAdapter(context);

		try {
			categories = categoryDB.getAllCategories();
			if (categories != null && categories.size() > 0) {
				Debugs.show("d", "CategoriesBrandsFragment",
						"database have categories : " + categories.size());
				CategoriesAdapter adapter = new CategoriesAdapter(context,
						R.layout.category_and_brand_grid_item, categories);
				listView.setAdapter(adapter);
			} else {
				Debugs.show("d", "CategoriesBrandsFragment", "NO categories");
				new LoadCategoryAndBrandAsync()
						.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 Intent intent = new Intent(context,
						 StoreLocatorAcivity.class);
				 intent.putExtra(ConstantsHelper.Categories.CATEGORIES_ID,
				 categories.get(arg2).categoryID);
				 intent.putExtra(ConstantsHelper.Categories.CATEGORIES_NAME,
				 categories.get(arg2).title);
				 context.startActivity(intent);
				// ConstantsHelper.animationFragment(context);

			}
		});

		editText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				CLICK_SEARCH_CAT = 1;
				FragmentManager fragmentManager = getActivity()
						.getSupportFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();

				CategoriesSearchFragment leftFragment = new CategoriesSearchFragment();
				fragmentTransaction.replace(R.id.linear_search_cate,
						leftFragment, "TAG");
				fragmentTransaction.commit();
			}
		});
		return view;
	}

	private class LoadCategoryAndBrandAsync extends
			AsyncTask<Void, Void, CategoryBrand> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected CategoryBrand doInBackground(Void... params) {
			try {
				APIProductAndBrand responder = new APIProductAndBrand();
				return responder.onLoadCategoriesAndBrand("1", apiKey,
						config.typePhone);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(CategoryBrand result) {
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			if (result != null) {
				if (result.categories != null && result.categories.size() > 0) {
					CategoriesAdapter adapter = new CategoriesAdapter(context,
							R.layout.category_and_brand_grid_item,
							result.categories);
					listView.setAdapter(adapter);
					insertCategoryDatabase(result.categories);
					categories = result.categories;
				}

				if (result.brands != null && result.brands.size() > 0) {
					insertBrandDatabase(result.brands);
				}
			} else {
				listView.setAdapter(null);
			}
		}
	}

	private void insertCategoryDatabase(ArrayList<Category> result) {
		categoryDB.deleteAll();
		for (Category c : result) {
			String valTitle = "";
			String subTitle = "";

			if (c.title != "") {
				valTitle = c.title;
				subTitle = c.title.substring(0, 1);
			}
			categoryDB.create(c.categoryID, valTitle, subTitle, c.url,
					c.lstStringBrand, c.status);
		}
	}

	private void insertBrandDatabase(ArrayList<Brand> result) {
		brandDB.deleteAll();
		for (Brand brd : result) {
			brandDB.create(brd.brandID, brd.name, brd.url, brd.status);
		}
	}
}
