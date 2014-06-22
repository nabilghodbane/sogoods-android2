package com.hitechno.sogoods.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.adapters.SearchBrandAdapter;
import com.hitechno.sogoods.adapters.SearchMemberAdapter;
import com.hitechno.sogoods.adapters.SearchProductAdapter;
import com.hitechno.sogoods.adapters.SearchShopAdapter;
import com.hitechno.sogoods.api.APISearch;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.models.SearchMenuLeft;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;
import com.mttam.toollibrary.tools.Tools;

public class CategoriesSearchFragment extends Fragment {

	private ImageView backButton;
	private TextView brandCount;
	private RelativeLayout brandLayout;
	private ListView brandList;
	private boolean brandShow;
	private Button bttCancel;
	private CategoryDBAdapter categoryDB;
	private Context context;
	private EditText editText;
	private ImageView imageViewCut;
	private double latitude;
	private LocationManager locationManager;
	private double longtitude;
	private TextView memberCount;
	private RelativeLayout memberLayout;
	private ListView memberList;
	private boolean memberShow;
	private TextView productCount;
	private RelativeLayout productLayout;
	private ListView productList;
	private boolean productShow;
	private ProgressBar progressBar;
	private SearchAsync searchAsync;
	private String searchInput;
	private TextView shopCount;
	private RelativeLayout shopLayout;
	private ListView shopList;
	private boolean shopShow;

	private String apiKey;

	private Configuration config;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = getActivity();
		categoryDB = DBManager.getCategoryDBAdapter(context);

		config = Configuration.getInstance(context);
		apiKey = MySharePreference.getApiKey(context);

		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);
		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null) {
			latitude = 0;
			longtitude = 0;
		} else {
			latitude = location.getLatitude();
			longtitude = location.getLongitude();
		}
	}

	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		View view = paramLayoutInflater.inflate(
				R.layout.fragment_search_categories, paramViewGroup, false);

		imageViewCut = (ImageView) view.findViewById(R.id.imageViewCut);
		bttCancel = (Button) view.findViewById(R.id.bttCancel);
		editText = (EditText) view.findViewById(R.id.searchmenuCate);
		backButton = (ImageView) view.findViewById(R.id.header_menu_button);

		progressBar = (ProgressBar) view
				.findViewById(R.id.menuleft_search_progressBar);
		productCount = (TextView) view.findViewById(R.id.search_product_count);
		brandCount = (TextView) view.findViewById(R.id.search_brand_count);
		shopCount = (TextView) view.findViewById(R.id.search_shop_count);
		memberCount = (TextView) view.findViewById(R.id.search_member_count);
		productList = (ListView) view.findViewById(R.id.search_list_product);
		brandList = (ListView) view.findViewById(R.id.search_list_brand);
		shopList = (ListView) view.findViewById(R.id.search_list_shop);
		memberList = (ListView) view.findViewById(R.id.search_list_member);
		productLayout = (RelativeLayout) view
				.findViewById(R.id.search_product_layout);
		brandLayout = (RelativeLayout) view
				.findViewById(R.id.search_brand_layout);
		shopLayout = (RelativeLayout) view
				.findViewById(R.id.search_shop_layout);
		memberLayout = (RelativeLayout) view
				.findViewById(R.id.search_member_layout);

		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				searchInput = editText.getText().toString();
				if (!Tools.isEmpty(searchInput)) {
					if (searchAsync != null) {
						searchAsync.isCancelled();
						searchAsync = null;
					}
					searchAsync = new SearchAsync();
					searchAsync.execute(searchInput);
				} else {
					if (searchAsync != null) {
						searchAsync.isCancelled();
						searchAsync = null;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getActivity()
						.getSupportFragmentManager();
				CategoriesSearchFragment fragment = (CategoriesSearchFragment) getActivity()
						.getSupportFragmentManager().findFragmentByTag("TAG");
				FragmentTransaction transaction = fragmentManager
						.beginTransaction();
				transaction.hide(fragment);
				transaction.commit();
				StoreLocatorFragment.CLICK_SEARCH_CAT = 2;
				Tools.hideKeyboard(getActivity(), editText);
			}
		});

		imageViewCut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				editText.setText("");
				imageViewCut.setVisibility(View.GONE);
			}
		});

		bttCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (searchAsync != null) {
					searchAsync.isCancelled();
					searchAsync = null;
				}
				progressBar.setVisibility(View.GONE);
			}
		});

		productLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (productShow) {
					productList.setVisibility(View.GONE);
					productShow = false;
				} else {
					productList.setVisibility(View.VISIBLE);
					productShow = true;
				}
			}
		});

		brandLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (brandShow) {
					brandList.setVisibility(View.GONE);
					brandShow = false;
				} else {
					brandList.setVisibility(View.VISIBLE);
					brandShow = true;
				}
			}
		});

		shopLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (shopShow) {
					shopList.setVisibility(View.GONE);
					shopShow = false;
				} else {
					shopList.setVisibility(View.VISIBLE);
					shopShow = true;
				}
			}
		});

		memberLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (memberShow) {
					memberList.setVisibility(View.GONE);
					memberShow = false;
				} else {
					memberList.setVisibility(View.VISIBLE);
					memberShow = true;
				}
			}
		});

		return view;
	}

	private class SearchAsync extends AsyncTask<String, Void, SearchMenuLeft> {

		private String search;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			CollapseAllList();
			memberCount.setVisibility(View.GONE);
			shopCount.setVisibility(View.GONE);
			brandCount.setVisibility(View.GONE);
			productCount.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected SearchMenuLeft doInBackground(String... params) {
			search = params[0];
			if (isCancelled()) {
				return null;
			}
			try {
				return APISearch.getSearchMenuLeft(apiKey, search, latitude,
						longtitude, config.typePhone);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPostExecute(SearchMenuLeft result) {
			super.onPostExecute(result);
			if (result != null && searchInput.equals(search)) {
				memberCount.setVisibility(View.VISIBLE);
				shopCount.setVisibility(View.VISIBLE);
				brandCount.setVisibility(View.VISIBLE);
				productCount.setVisibility(View.VISIBLE);
				if (result.lstProducts != null && result.lstProducts.size() > 0) {
					productCount.setText("" + result.lstProducts.size());
					SearchProductAdapter pAdapter = new SearchProductAdapter(
							context, R.layout.item_search_product,
							result.lstProducts);
					productList.setAdapter(pAdapter);
					Tools.setListViewHeightBasedOnChildren(productList, 0);
				} else {
					productCount.setText("0");
					productList.setAdapter(null);
					productList.setVisibility(View.GONE);
				}

				if (result.lstBrands != null && result.lstBrands.size() > 0) {
					brandCount.setText("" + result.lstBrands.size());
					for (int j = 0; j < result.lstBrands.size(); j++) {
						ArrayList<Category> categories = categoryDB
								.getAllCategoriesWithBrand(result.lstBrands
										.get(j).brandID);
						String des = "";
						for (int i = 0; i < categories.size(); i++) {
							if (i != categories.size() - 1)
								des += categories.get(i).title + ", ";
							else
								des += categories.get(i).title;
						}
						result.lstBrands.get(j).listCategory = des;
					}

					SearchBrandAdapter adapter = new SearchBrandAdapter(
							context, R.layout.item_search_brand,
							result.lstBrands);
					brandList.setAdapter(adapter);
					Tools.setListViewHeightBasedOnChildren(brandList, 0);
				} else {
					brandCount.setText("0");
					brandList.setAdapter(null);
					brandList.setVisibility(View.GONE);
				}

				if (result.lstStores != null && result.lstStores.size() > 0) {
					shopCount.setText("" + result.lstStores.size());
					SearchShopAdapter adapter = new SearchShopAdapter(context,
							R.layout.item_search_shop, result.lstStores);
					shopList.setAdapter(adapter);
					Tools.setListViewHeightBasedOnChildren(shopList, 0);
				} else {
					shopCount.setText("0");
					shopList.setAdapter(null);
					shopList.setVisibility(View.GONE);
				}

				if (result.lstProfiles != null && result.lstProfiles.size() > 0) {
					memberCount.setText("" + result.lstProfiles.size());
					SearchMemberAdapter adapter = new SearchMemberAdapter(
							context, R.layout.item_search_member,
							result.lstProfiles);
					memberList.setAdapter(adapter);
					Tools.setListViewHeightBasedOnChildren(memberList, 0);
				} else {
					memberCount.setText("0");
					memberList.setAdapter(null);
					memberList.setVisibility(View.GONE);
				}
			} else {
				CollapseAllList();
				memberCount.setVisibility(View.GONE);
				shopCount.setVisibility(View.GONE);
				brandCount.setVisibility(View.GONE);
				productCount.setVisibility(View.GONE);
				Debugs.show("d", "LeftMenuFragment", "Search NULL");
			}
			progressBar.setVisibility(View.GONE);
		}
	}

	private void CollapseAllList() {
		productShow = false;
		brandShow = false;
		shopShow = false;
		memberShow = false;
		memberList.setVisibility(View.GONE);
		shopList.setVisibility(View.GONE);
		brandList.setVisibility(View.GONE);
		productList.setVisibility(View.GONE);
	}
}
