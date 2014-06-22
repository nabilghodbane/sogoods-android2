package com.hitechno.sogoods.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.hitechno.sogoods.R;

public class MenuRightFragment extends Fragment {

	private Button buttonBrandFilter;
	private Button buttonCategoryFilter;

	private int tab = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu_right, container,
				false);

		buttonCategoryFilter = (Button) view
				.findViewById(R.id.menuright_category_button);
		buttonBrandFilter = (Button) view
				.findViewById(R.id.menuright_brand_button);
		loadCategoryFragment();

		buttonCategoryFilter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (tab != 0) {
					tab = 0;
					buttonCategoryFilter
							.setBackgroundResource(R.drawable.background_white_with_line_gray);
					buttonBrandFilter.setBackgroundColor(Color.TRANSPARENT);
					loadCategoryFragment();
				}
			}
		});

		buttonBrandFilter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (tab != 1) {
					tab = 1;
					buttonBrandFilter
							.setBackgroundResource(R.drawable.background_white_with_line_gray);
					buttonCategoryFilter.setBackgroundColor(Color.TRANSPARENT);
					loadCategoryFragment();
				}
			}
		});

		return view;
	}

	public void loadCategoryFragment() {
		FragmentManager fragmentManager = getActivity()
				.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		if (tab == 0) {
			fragmentTransaction.replace(R.id.filter_product_detail_container,
					new MenuRightFilterCategoryFragment());
			fragmentTransaction.commit();
		} else {
			fragmentTransaction.replace(R.id.filter_product_detail_container,
					new MenuRightFilterBrandFragment());
			fragmentTransaction.commit();
		}
	}
}
