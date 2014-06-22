package com.hitechno.sogoods.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;
import com.hitechno.sogoods.HomeActivity;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.adapters.ProductsAdapter;
import com.hitechno.sogoods.api.APIProduct;
import com.hitechno.sogoods.models.Product;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;
import com.hitechno.sogoods.views.FuturaButton;

public class ProductsFragment extends Fragment {

	private ArrayList<Product> newProducts;
	private ArrayList<Product> suggestedProducts;
	private ArrayList<Product> popularProducts;
	private ProductsAdapter adapter;

	private int newOffset;
	private int suggestedOffset;
	private int popularOffset;

	private Context context;

	public static int CLICK_TAB = 1;
	private int idFilter;

	private FuturaButton buttonNew, buttonSuggested, buttonPopular;
	private PullAndLoadListView listView;
	private ProgressBar progressBar;

	private APIProduct productContentProvider;

	private NewDataAsync newDataAsync;
	private Configuration config;

	private static ProductsFragment instance;

	public static ProductsFragment getInstance() {
		if (instance == null) {
			instance = new ProductsFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = getActivity();
		newProducts = new ArrayList<Product>();
		suggestedProducts = new ArrayList<Product>();
		popularProducts = new ArrayList<Product>();
		config = Configuration.getInstance(context);

		String apiKey = MySharePreference.getApiKey(context);

		productContentProvider = new APIProduct(context, apiKey,
				config.currentLanguage, config.typePhone);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_products, container,
				false);

		buttonNew = (FuturaButton) view.findViewById(R.id.new_products_button);
		buttonSuggested = (FuturaButton) view
				.findViewById(R.id.suggested_products_button);
		buttonPopular = (FuturaButton) view
				.findViewById(R.id.popular_products_button);
		listView = (PullAndLoadListView) view
				.findViewById(R.id.products_listview);
		progressBar = (ProgressBar) view
				.findViewById(R.id.products_frag_progressbar);

		getIDFilter();

		newDataAsync = new NewDataAsync();
		newDataAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

		listView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new NewDataAsync().execute();
			}
		});

		listView.setOnLoadMoreListener(new OnLoadMoreListener() {

			@Override
			public void onLoadMore() {
				new LoadMoreDataAsync().execute();
			}
		});

		buttonNew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CLICK_TAB = 1;
				buttonNew
						.setBackgroundResource(R.drawable.background_white_with_line_gray);
				buttonSuggested.setBackgroundColor(Color.TRANSPARENT);
				buttonPopular.setBackgroundColor(Color.TRANSPARENT);

				loadData();
				loadNewProductsList();
			}
		});

		buttonSuggested.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CLICK_TAB = 2;
				buttonNew.setBackgroundColor(Color.TRANSPARENT);
				buttonPopular.setBackgroundColor(Color.TRANSPARENT);
				buttonSuggested
						.setBackgroundResource(R.drawable.background_white_with_line_gray);

				loadData();
				loadSuggestedProductsList();
			}
		});

		buttonPopular.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CLICK_TAB = 3;
				buttonNew.setBackgroundColor(Color.TRANSPARENT);
				buttonSuggested.setBackgroundColor(Color.TRANSPARENT);
				buttonPopular
						.setBackgroundResource(R.drawable.background_white_with_line_gray);

				loadData();
				loadPopularProductsList();
			}
		});

		return view;
	}

	private void loadNewProductsList() {
		if (newProducts != null && newProducts.size() > 0) {
			ProductsAdapter adapter = new ProductsAdapter(context,
					R.layout.item_product_list, newProducts);
			listView.setAdapter(adapter);
		} else {
			listView.setAdapter(null);
		}
		listView.invalidateViews();
	}

	private void loadSuggestedProductsList() {
		if (suggestedProducts != null && suggestedProducts.size() > 0) {
			ProductsAdapter adapter = new ProductsAdapter(context,
					R.layout.item_product_list, suggestedProducts);
			listView.setAdapter(adapter);
		} else {
			listView.setAdapter(null);
		}
		listView.invalidateViews();
	}

	private void loadPopularProductsList() {
		if (popularProducts != null && popularProducts.size() > 0) {
			ProductsAdapter adapter = new ProductsAdapter(context,
					R.layout.item_product_list, popularProducts);
			listView.setAdapter(adapter);
		} else {
			listView.setAdapter(null);
		}
		listView.invalidateViews();
	}

	public void loadData() {
		getIDFilter();
		if (newDataAsync != null) {
			newDataAsync.cancel(true);
		}
		newDataAsync = new NewDataAsync();
		newDataAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	public void reloadDataFilter(String filter) {
		Debugs.show("d", "ProductsFragment", "reloadDataFilter with id"
				+ idFilter);
		loadData();
		HomeActivity activity = (HomeActivity) context;
		activity.setFilterStatus(true, filter);
	}

	private void getIDFilter() {
		if (MySharePreference.getProductFilter(context) != 0) {
			idFilter = MySharePreference.getProductFilter(context);
		} else if (MySharePreference.getBrandFilter(context) != 0) {
			idFilter = MySharePreference.getBrandFilter(context);
		} else {
			idFilter = 0;
		}
	}

	private class NewDataAsync extends
			AsyncTask<Void, Void, ArrayList<Product>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected ArrayList<Product> doInBackground(Void... params) {
			if (isCancelled()) {
				return null;
			}
			try {
				switch (CLICK_TAB) {
				case 1:
					newOffset = 0;
					return productContentProvider.onLoadNewsProduct(10,
							newOffset, idFilter);
				case 2:
					suggestedOffset = 0;
					return productContentProvider.onLoadSuggestProduct(10,
							suggestedOffset, idFilter);
				case 3:
					popularOffset = 0;
					return productContentProvider.onLoadPopularProduct(10,
							popularOffset, idFilter);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Product> result) {
			super.onPostExecute(result);
			if (result != null && result.size() > 0) {
				switch (CLICK_TAB) {
				case 1:
					newProducts = result;
					adapter = new ProductsAdapter(context,
							R.layout.item_product_list, newProducts);
					break;
				case 2:
					suggestedProducts = result;
					adapter = new ProductsAdapter(context,
							R.layout.item_product_list, suggestedProducts);
					break;
				case 3:
					popularProducts = result;
					adapter = new ProductsAdapter(context,
							R.layout.item_product_list, popularProducts);
					break;
				}
				listView.setAdapter(adapter);
			}
			progressBar.setVisibility(View.GONE);

			listView.invalidateViews();
			listView.onRefreshComplete();
		}

		@Override
		protected void onCancelled() {
			listView.onLoadMoreComplete();
		}
	}

	private class LoadMoreDataAsync extends
			AsyncTask<Void, Void, ArrayList<Product>> {

		@Override
		protected ArrayList<Product> doInBackground(Void... params) {
			if (isCancelled()) {
				return null;
			}
			try {
				switch (CLICK_TAB) {
				case 1:
					newOffset += 1;
					return productContentProvider.onLoadNewsProduct(10,
							newOffset, idFilter);
				case 2:
					suggestedOffset += 1;
					return productContentProvider.onLoadSuggestProduct(10,
							suggestedOffset, idFilter);
				case 3:
					popularOffset += 1;
					return productContentProvider.onLoadPopularProduct(10,
							popularOffset, idFilter);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Product> result) {
			super.onPostExecute(result);
			if (result != null && result.size() > 0) {
				switch (CLICK_TAB) {
				case 1:
					for (Product p : result) {
						newProducts.add(p);
					}
					adapter = new ProductsAdapter(context,
							R.layout.item_product_list, newProducts);
					listView.post(new Runnable() {

						@Override
						public void run() {
							listView.setSelection(newProducts.size() - 10);
						}
					});
					break;
				case 2:
					for (Product p : result) {
						suggestedProducts.add(p);
					}
					adapter = new ProductsAdapter(context,
							R.layout.item_product_list, suggestedProducts);
					listView.post(new Runnable() {

						@Override
						public void run() {
							listView.setSelection(suggestedProducts.size() - 10);
						}
					});
					break;
				case 3:
					for (Product p : result) {
						popularProducts.add(p);
					}
					adapter = new ProductsAdapter(context,
							R.layout.item_product_list, popularProducts);
					listView.post(new Runnable() {

						@Override
						public void run() {
							listView.setSelection(popularProducts.size() - 10);
						}
					});
					break;
				}

				listView.setAdapter(adapter);
			}
			progressBar.setVisibility(View.GONE);

			// adapter.notifyDataSetChanged();
			listView.invalidateViews();
			listView.onLoadMoreComplete();
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			listView.onLoadMoreComplete();
		}

	}
}