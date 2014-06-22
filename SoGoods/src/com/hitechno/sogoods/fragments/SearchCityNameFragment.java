package com.hitechno.sogoods.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hitechno.sogoods.MyAccountActivity;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.managers.SoGoodsAPIManager;
import com.hitechno.sogoods.model.City;
import com.hitechno.sogoods.views.CustomFontTextView;

public class SearchCityNameFragment extends Fragment implements
	View.OnClickListener,
	TextWatcher,
	AdapterView.OnItemClickListener
{
	public static interface SearchCityNameFragmentResult {
		public void onCitySelected(City city);
	}

	private FragmentActivity m_Parent = null;
	private SearchCityNameFragmentResult m_Callback = null;
	private SearchCity m_Search = null;
	private ProgressBar m_Loading;
	private ListView m_CitiesList;
	private CitiesAdapter m_Adapter;
	private City[] m_Cities = null;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		m_Parent = (FragmentActivity)activity;
		m_Callback = (SearchCityNameFragmentResult)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.fragment_search_cityname, null);
		result.findViewById(R.id.searchcity_back_layout).setOnClickListener(this);
		((TextView)result.findViewById(R.id.searchmenuCityName)).addTextChangedListener(this);
		m_Loading = (ProgressBar)result.findViewById(R.id.pbLoading);
		m_CitiesList = (ListView)result.findViewById(R.id.listSearchCityName);
		m_Adapter = new CitiesAdapter();
		m_CitiesList.setAdapter(m_Adapter);
		m_CitiesList.setOnItemClickListener(this);
		return result;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchcity_back_layout:
			m_Parent.getSupportFragmentManager().popBackStack();
			break;
		}
	}

	@Override
	public void afterTextChanged(Editable arg0) {
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (m_Search != null) {
			m_Search.cancel(false);
		}
		if (s.length() > 0) {
			m_Search = new SearchCity();
			m_Search.execute(s.toString());
		}
	}
	
	private class SearchCity extends AsyncTask<String, Void, City[]> {
		@Override
		protected void onPostExecute(City[] result) {
			m_Cities = result;
			m_Adapter.notifyDataSetChanged();
			m_Loading.setVisibility(View.INVISIBLE);
			m_CitiesList.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPreExecute() {
			m_Loading.setVisibility(View.VISIBLE);
			m_CitiesList.setVisibility(View.INVISIBLE);
		}

		@Override
		protected City[] doInBackground(String... params) {
			City[] result = SoGoodsAPIManager.getInstance().getCities(params[0], 0, 0);
			return result;
		}
		
	}
	
	private class CitiesAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			if (m_Cities == null) {
				return 0;
			}
			return m_Cities.length;
		}

		@Override
		public Object getItem(int position) {
			return m_Cities[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			CustomFontTextView result;
			if (convertView != null) {
				result = (CustomFontTextView)convertView;
			} else {
				result = new CustomFontTextView(getActivity());
			}
			result.setCustomFont("fonts/HelveticaNeue-Light.ttf");
			result.setTextSize(16);
			result.setTextColor(Color.BLACK);
			AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
					AbsListView.LayoutParams.WRAP_CONTENT);
			result.setLayoutParams(params);
			int padding = (int)MyAccountActivity.dipToPixels(getActivity(), 8);
			result.setPadding(padding, padding, padding, padding);
			result.setText(m_Cities[position].getName());
			return result;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		if (m_Callback != null) {
			m_Callback.onCitySelected(m_Cities[arg2]);
		}
		m_Parent.getSupportFragmentManager().popBackStack();
	}

//	public static int CITY_ID;
//	public static String CITY_NAME;
//	private LinearLayout backButton;
//	private APIBrand brandContentProvider;
//	private ImageView clearButton;
//	private Context context;
//	private DoSearchAsync getSearchAsync;
//	private EditText inputSearch;
//	private ArrayList<Brand> lstBrand;
//	private ListView lstViewSearch;
//	private ProgressBar progressBar;
//	private int source;
//
//	public void onCreate(Bundle paramBundle) {
//		super.onCreate(paramBundle);
//		this.context = getActivity();
//		this.brandContentProvider = new APIBrand();
//		this.lstBrand = new ArrayList<Brand>();
//	}
//
//	public View onCreateView(LayoutInflater paramLayoutInflater,
//			ViewGroup paramViewGroup, Bundle paramBundle) {
//		ViewGroup localViewGroup = (ViewGroup) paramLayoutInflater.inflate(
//				R.layout.fragment_search_cityname, paramViewGroup, false);
//		this.inputSearch = ((EditText) localViewGroup
//				.findViewById(R.id.searchmenuCityName));
//		this.lstViewSearch = ((ListView) localViewGroup
//				.findViewById(R.id.listSearchCityName));
//		this.progressBar = ((ProgressBar) localViewGroup
//				.findViewById(R.id.ProgressBar01));
//		this.clearButton = ((ImageView) localViewGroup
//				.findViewById(R.id.imageViewCut));
//		this.backButton = ((LinearLayout) localViewGroup
//				.findViewById(R.id.searchcity_back_layout));
//		Bundle localBundle = getArguments();
//		if ((localBundle != null) && (localBundle.containsKey("source"))) {
//			this.source = localBundle.getInt("source");
//		}
//		this.inputSearch.addTextChangedListener(new TextWatcher() {
//			public void afterTextChanged(Editable paramAnonymousEditable) {
//			}
//
//			public void beforeTextChanged(
//					CharSequence paramAnonymousCharSequence,
//					int paramAnonymousInt1, int paramAnonymousInt2,
//					int paramAnonymousInt3) {
//			}
//
//			public void onTextChanged(CharSequence paramAnonymousCharSequence,
//					int paramAnonymousInt1, int paramAnonymousInt2,
//					int paramAnonymousInt3) {
//				String str = paramAnonymousCharSequence.toString();
//				if (str.length() > 0) {
//					SearchCityNameFragment.this.clearButton.setVisibility(0);
//					if (SearchCityNameFragment.this.getSearchAsync != null) {
//						SearchCityNameFragment.this.getSearchAsync.cancel(true);
//						SearchCityNameFragment.this.getSearchAsync = null;
//					}
//					SearchCityNameFragment.this.getSearchAsync = new SearchCityNameFragment.DoSearchAsync();
//					SearchCityNameFragment.this.getSearchAsync
//							.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
//									new String[] { str });
//				}
//			}
//		});
//		this.backButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				if (SearchCityNameFragment.this.source == 1) {
//					((MyAccountActivity) SearchCityNameFragment.this
//							.getActivity()).onBackPressed();
//				}
//			}
//		});
//		this.lstViewSearch
//				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//					public void onItemClick(
//							AdapterView<?> paramAnonymousAdapterView,
//							View paramAnonymousView, int paramAnonymousInt,
//							long paramAnonymousLong) {
//						Brand localBrand = (Brand) SearchCityNameFragment.this.lstBrand
//								.get(paramAnonymousInt);
//						SearchCityNameFragment.CITY_ID = localBrand.cityID;
//						SearchCityNameFragment.CITY_NAME = localBrand.cityName;
//						if (SearchCityNameFragment.this.source == 1) {
//							if (localBrand.cityName.indexOf(",") <= 0) {
//								return;
//							}
//						}
//						String str = localBrand.cityName.substring(0,
//								localBrand.cityName.indexOf(","));
//						// str = localBrand.cityName;
//
//						((MyAccountActivity) SearchCityNameFragment.this
//								.getActivity()).cityInput.setText(str);
//						((MyAccountActivity) SearchCityNameFragment.this
//								.getActivity()).onBackPressed();
//
//					}
//				});
//		this.clearButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				SearchCityNameFragment.this.inputSearch.setText("");
//				SearchCityNameFragment.this.clearButton.setVisibility(8);
//			}
//		});
//		return localViewGroup;
//	}
//
//	private class DoSearchAsync extends
//			AsyncTask<String, Void, ArrayList<Brand>> {
//		private DoSearchAsync() {
//		}
//
//		protected ArrayList<Brand> doInBackground(String... paramVarArgs) {
//			String str = paramVarArgs[0];
//			if (isCancelled()) {
//				return null;
//			}
//			return SearchCityNameFragment.this.brandContentProvider
//					.getAllCityName(str);
//		}
//
//		protected void onPostExecute(ArrayList<Brand> paramArrayList) {
//			super.onPostExecute(paramArrayList);
//			// Log.w("kjkhk", ""+paramArrayList.toString());
//			SearchCityNameFragment.this.progressBar.setVisibility(8);
//			if ((paramArrayList != null) && (paramArrayList.size() > 0)) {
//				SearchCityNameFragment.this.lstBrand = paramArrayList;
//				SearchCityNameAdapter localSearchCityNameAdapter = new SearchCityNameAdapter(
//						SearchCityNameFragment.this.context,
//						R.layout.search_list_item, paramArrayList);
//				SearchCityNameFragment.this.lstViewSearch.invalidate();
//				SearchCityNameFragment.this.lstViewSearch
//						.setAdapter(localSearchCityNameAdapter);
//				return;
//			}
//			SearchCityNameFragment.this.lstViewSearch.setAdapter(null);
//		}
//
//		protected void onPreExecute() {
//			super.onPreExecute();
//			SearchCityNameFragment.this.progressBar.setVisibility(0);
//			SearchCityNameFragment.this.lstBrand.clear();
//			SearchCityNameFragment.this.lstViewSearch.setAdapter(null);
//		}
//	}
}
