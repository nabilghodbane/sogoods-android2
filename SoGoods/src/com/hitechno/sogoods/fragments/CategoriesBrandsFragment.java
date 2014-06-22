package com.hitechno.sogoods.fragments;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.helpers.ConstantsHelper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class CategoriesBrandsFragment extends Fragment {

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static int CLICK_SEARCH_CAT;
	
	public CategoriesBrandsFragment() {	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " creating");
	}
	
	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " creating view");
		
		View rootView = getActivity().findViewById(R.id.categories_brands);
		
		if (rootView == null) {
			rootView = inflater.inflate(R.layout.categories_brands_parent_fragment,
					container, false);
			
			EditText editText = (EditText)rootView.findViewById(R.id.searchCategories);
			
			editText.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					CLICK_SEARCH_CAT = 1;
					FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					CategoriesSearchFragment leftFragment = new CategoriesSearchFragment();
					fragmentTransaction.replace(R.id.linear_search_cate, leftFragment,"TAG");
					fragmentTransaction.commit();
				}
			});
			
			/*SearchView searchView = (SearchView)rootView.findViewById(R.id.searchCategories);
			searchView.setQueryHint(getString(R.string.search_categories));
			searchView.setOnSearchClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CLICK_SEARCH_CAT = 1;
					FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					CategoriesSearchFragment leftFragment = new CategoriesSearchFragment();
					fragmentTransaction.replace(R.id.linear_search_cate, leftFragment,"TAG");
					fragmentTransaction.commit();
				}
			});
			searchView.setOnCloseListener(new OnCloseListener() {
				
				@Override
				public boolean onClose() {
					return false;
				}
			});*/
		}
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " resuming");
	}
	
	@Override
	public void onPause() {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " pausing");
		super.onPause();
	}
	
	@Override
	public void onStop() {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " stopping");
		super.onStop();
	}
	
	@Override
	public void onDestroyView() {
		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " destroying view brand");
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " destroying view brand");
		super.onDestroy();
	}
	
	@Override
	public void onDetach() {
//		Log.d(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + " detaching");
		super.onDetach();
	}
	
}
