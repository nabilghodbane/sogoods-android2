package com.hitechno.sogoods.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.hitechno.sogoods.HomeActivity;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.adapters.FilterCategoryAdapter;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;

public class MenuRightFilterCategoryFragment extends Fragment {

	private ArrayList<Category> categories;
	private CategoryDBAdapter categoryDB;
	private ListView categoryListView;
	private Context context;
	private TextView txtNoresult;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.context = getActivity();
		this.categoryDB = DBManager.getCategoryDBAdapter(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View localView = inflater.inflate(R.layout.fragment_menu_right_filter,
				container, false);
		categoryListView = (ListView) localView
				.findViewById(R.id.filter_product_list);
		txtNoresult = (TextView) localView.findViewById(R.id.txtNoresult);

		loadCategoryList();
		categoryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Category localCategory = categories.get(arg2);
				int i = localCategory.categoryID;
				Debugs.show("d", "MenuRightFilterCategoryFragment",
						"Filter with category " + localCategory.title);
				MySharePreference.saveProductFilter(context, i);
				closeRightMenu();
				ProductsFragment.getInstance().reloadDataFilter(
						localCategory.title);
			}
		});
		return localView;

	}

	public void closeRightMenu() {
		((HomeActivity) getActivity()).closeRightMenu();
	}

	public void loadCategoryList() {
		try {
			categories = categoryDB.getAllCategories();
			if ((categories != null) && (categories.size() > 0)) {
				txtNoresult.setVisibility(View.GONE);
				FilterCategoryAdapter localFilterCategoryAdapter = new FilterCategoryAdapter(
						context, R.layout.item_menuright_category_filter,
						categories);
				categoryListView.setAdapter(localFilterCategoryAdapter);
			} else {
				txtNoresult.setVisibility(View.VISIBLE);
				categoryListView.setAdapter(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
