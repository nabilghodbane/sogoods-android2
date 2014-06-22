package com.hitechno.sogoods.fragments;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.hitechno.sogoods.AddProductActivity;
import com.hitechno.sogoods.HomeActivity;
import com.hitechno.sogoods.NotificationsActivity;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.SplashActivity;
import com.hitechno.sogoods.SupportActivity;
import com.hitechno.sogoods.AddBrandActivity;
import com.hitechno.sogoods.adapters.SearchBrandAdapter;
import com.hitechno.sogoods.adapters.SearchMemberAdapter;
import com.hitechno.sogoods.adapters.SearchProductAdapter;
import com.hitechno.sogoods.adapters.SearchShopAdapter;
import com.hitechno.sogoods.api.APILogin;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.api.APISearch;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.models.SearchMenuLeft;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;
import com.mttam.toollibrary.tools.Tools;

//Left Menu 
@SuppressLint("NewApi")
public class LeftMenuFragment extends Fragment {

	HomeActivity applicationActivity;
	TextView usname;
	Profile profile;
	ImageView avatar;
	String imageUrl;

	static SearchView searchView;
	public static int ONCLICK_SEARCH;
	LinearLayout linearLayout;

	// declare variable id view
	RelativeLayout relUsername, relProducts, myAccountButton,
			notificationButton, relSupport, relCategories, memberButton,
			relNews, relLogout, addProductButton, addBrandButton;

	private CategoryDBAdapter categoryDB;

	private Context context;

	private LocationManager locationManager;
	private double latitude;
	private double longtitude;
	private String apiKey;

	private Configuration config;

	private SearchAsync searchAsync;

	private RelativeLayout searchLayout;
	private ProgressBar progressBar;
	private TextView productCount;
	private TextView brandCount;
	private TextView shopCount;
	private TextView memberCount;
	private ListView productList;
	private ListView brandList;
	private ListView shopList;
	private ListView memberList;

	private String searchInput;

	private boolean productShow;
	private boolean brandShow;
	private boolean shopShow;
	private boolean memberShow;

	private RelativeLayout productLayout;
	private RelativeLayout brandLayout;
	private RelativeLayout shopLayout;
	private RelativeLayout memberLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = getActivity();

		categoryDB = DBManager.getCategoryDBAdapter(context);
		config = Configuration.getInstance(context);

		applicationActivity = (HomeActivity) getActivity();
		profile = new Profile();

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
		Debugs.show("d", "LocationManager", "location = " + latitude + "-"
				+ longtitude);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		container = (ViewGroup) inflater.inflate(R.layout.fragment_menu_left,
				container, false);
		linearLayout = (LinearLayout) container
				.findViewById(R.id.pager_with_filter);
		linearLayout.setVisibility(View.VISIBLE);

		usname = (TextView) container.findViewById(R.id.textUsname);
		avatar = (ImageView) container.findViewById(R.id.imageusname);
		searchView = (SearchView) container
				.findViewById(R.id.searchmenuProfile);

		// Search layout
		searchLayout = (RelativeLayout) container
				.findViewById(R.id.menuleft_search_layout);
		progressBar = (ProgressBar) container
				.findViewById(R.id.menuleft_search_progressBar);
		productCount = (TextView) container
				.findViewById(R.id.search_product_count);
		brandCount = (TextView) container.findViewById(R.id.search_brand_count);
		shopCount = (TextView) container.findViewById(R.id.search_shop_count);
		memberCount = (TextView) container
				.findViewById(R.id.search_member_count);
		productList = (ListView) container
				.findViewById(R.id.search_list_product);
		brandList = (ListView) container.findViewById(R.id.search_list_brand);
		shopList = (ListView) container.findViewById(R.id.search_list_shop);
		memberList = (ListView) container.findViewById(R.id.search_list_member);
		productLayout = (RelativeLayout) container
				.findViewById(R.id.search_product_layout);
		brandLayout = (RelativeLayout) container
				.findViewById(R.id.search_brand_layout);
		shopLayout = (RelativeLayout) container
				.findViewById(R.id.search_shop_layout);
		memberLayout = (RelativeLayout) container
				.findViewById(R.id.search_member_layout);

		// Main layout
		relUsername = (RelativeLayout) container.findViewById(R.id.relUsername);
//		myAccountButton = (RelativeLayout) container
//				.findViewById(R.id.menuleft_myaccount_layout);
		relProducts = (RelativeLayout) container.findViewById(R.id.relProducts);
		notificationButton = (RelativeLayout) container
				.findViewById(R.id.relNotifi);
		relSupport = (RelativeLayout) container.findViewById(R.id.relSupport);
		relCategories = (RelativeLayout) container
				.findViewById(R.id.relCategories);
		memberButton = (RelativeLayout) container.findViewById(R.id.relMember);
		relLogout = (RelativeLayout) container.findViewById(R.id.relLogout);
		addProductButton = (RelativeLayout) container
				.findViewById(R.id.leftmenu_layout_addproduct);
		addBrandButton = (RelativeLayout) container
				.findViewById(R.id.leftmenu_layout_addbrand);

		String profileJson = MySharePreference.getProfileContent(context);
		// si avatr stocké dans cache
		if (!Tools.isEmpty(profileJson)) {
			try {
				profile = Profile.initFromJson(new JSONObject(profileJson),
						config.typePhone);

				if (profile != null) {
					if (!Tools.isImageEmpty(context, profile.urlAvatarSmall))
						avatar.setImageBitmap(Tools.getImageFromStorage(
								context, profile.urlAvatarSmall));
					new LoadAvartarImage().executeOnExecutor(
							AsyncTask.THREAD_POOL_EXECUTOR,
							profile.urlAvatarSmall);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			// Load user avatar
			new AsyncTask<Integer, Void, String>() {

				@Override
				protected String doInBackground(Integer... params) {
					try {
						APIProfile profileContentProvider = new APIProfile();
						return profileContentProvider.loadContentProfileUser(
								context, params[0], apiKey);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}

				protected void onPostExecute(String result) {
					if (result != null) {
						if (MySharePreference.saveProfileContent(context,
								result))
							Debugs.show("d", "MySharePreference",
									"saveProfileContent DONE");

						try {
							profile = Profile.initFromJson(new JSONObject(
									result), config.typePhone);
	
							
							new LoadAvartarImage().executeOnExecutor(
									AsyncTask.THREAD_POOL_EXECUTOR,
									profile.urlAvatarSmall);
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				};
			}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
					profile.profileID);
		}

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String arg0) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String arg0) {
				ONCLICK_SEARCH = 2;
				searchInput = arg0;

				if (!Tools.isEmpty(arg0)) {
					searchLayout.setVisibility(View.VISIBLE);
					if (searchAsync != null) {
						searchAsync.isCancelled();
						searchAsync = null;
					}
					searchAsync = new SearchAsync();
					searchAsync.execute(arg0);
				} else {
					if (searchAsync != null) {
						searchAsync.isCancelled();
						searchAsync = null;
					}
					searchLayout.setVisibility(View.GONE);
				}
				return true;
			}
		});

		searchView.setOnCloseListener(new SearchView.OnCloseListener() {

			@Override
			public boolean onClose() {
				searchView.setIconifiedByDefault(true);
				searchLayout.setVisibility(View.GONE);
				return false;
			}
		});

		if (profile.displayRealName == 1) {
			usname.setText(profile.firstName + " " + profile.lastName);
		} else {
			usname.setText(profile.username);
		}

		// button diary
		relUsername.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				linearLayout.setVisibility(View.VISIBLE);
				// Intent intent = new Intent(context, MyDiaryActivity.class);
				// startActivity(intent);
				// ConstantsHelper.animationFragment(getActivity());
			}
		});

		relProducts.setOnClickListener(onclickProduct());
		notificationButton.setOnClickListener(loadNotifi());
		relCategories.setOnClickListener(onclickCate());
		memberButton.setOnClickListener(onclickMember());
		addProductButton.setOnClickListener(clickAddproduct());
		relLogout.setOnClickListener(logOut());
		relSupport.setOnClickListener(onClickSupport());

		addBrandButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(context, AddBrandActivity.class);
				 startActivity(intent);
				 ConstantsHelper.animationPro(getActivity());
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

		return container;
	}

	private View.OnClickListener onClickSupport() {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(getActivity(),
				 SupportActivity.class);
				 startActivity(intent);
				ConstantsHelper.animationPro(getActivity());
			}
		};
	}

	private View.OnClickListener onclickProduct() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// relProducts.setBackgroundColor(getResources().getColor(
				// R.color.background1));
				linearLayout.setVisibility(View.VISIBLE);
				switchMenu(1);
			}
		};
	}

	private View.OnClickListener onclickCate() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// relCategories.setBackgroundColor(getResources().getColor(
				// R.color.background1));
				linearLayout.setVisibility(View.VISIBLE);

				switchMenu(0);
			}
		};
	}

	private View.OnClickListener onclickMember() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// relMember.setBackgroundColor(getResources().getColor(
				// R.color.background1));
				linearLayout.setVisibility(View.VISIBLE);

				switchMenu(2);
			}
		};
	}

	private OnClickListener loadNotifi() {
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				linearLayout.setVisibility(View.VISIBLE);

				 Intent intent = new Intent(getActivity(),
				 NotificationsActivity.class);
				 startActivity(intent);
				 ConstantsHelper.animationPro(getActivity());
			}
		};
		return listener;
	}

	private OnClickListener logOut() {
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				linearLayout.setVisibility(View.VISIBLE);
				switchMenu(0);
				// alert dialog
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				builder.setTitle(getResources().getString(
						R.string.confirm_disconnection));
				builder.setCancelable(false);
				builder.setInverseBackgroundForced(true);
				builder.setPositiveButton(
						getResources().getString(R.string.OK),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								new logOutAsync().executeOnExecutor(
										AsyncTask.THREAD_POOL_EXECUTOR, apiKey);
								dialog.dismiss();
							}
						});
				builder.setNegativeButton(
						getResources().getString(R.string.cancel),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			}
		};
		return listener;
	}

	void switchMenu(int currentItem) {
		// ApplicationActivity activity = new ApplicationActivity();
		// activity.isExpanded = false;
		// // Collapse
		// new CollapseAnimation(activity.slidingPanel, activity.panelWidth1,
		// TranslateAnimation.RELATIVE_TO_SELF, 0.75f,
		// TranslateAnimation.RELATIVE_TO_SELF, 0.0f, 0, 0.0f, 0, 0.0f);
		// activity.mViewPager.setCurrentItem(currentItem);
		HomeActivity activity = (HomeActivity) getActivity();
		activity.switchMenu(currentItem);
		activity.toggle();
	}

	public View.OnClickListener clickAddproduct() {
		View.OnClickListener view = new OnClickListener() {

			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(getActivity(),
				 AddProductActivity.class);
				 startActivity(intent);
				 ConstantsHelper.animationPro(getActivity());
			}
		};
		return view;
	}

	private class LoadAvartarImage extends AsyncTask<String, Void, Boolean> {

		private String url;

		@Override
		protected Boolean doInBackground(String... params) {
			url = params[0];
			return Tools.DownLoadImage(context, url);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				avatar.setImageBitmap(Tools.getImageFromStorage(context, url));
			} else {
				Debugs.show("w", "LoadAvartarImage",
						"Download Avatar for user have problem");
			}
		}
	}

	private class logOutAsync extends AsyncTask<String, Void, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			APILogin api = new APILogin();
			return api.logout(params[0]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				MySharePreference.saveAPIKEY(context, "");
				MySharePreference.savePass(context, "");
				Intent intent = new Intent(context, SplashActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				ConstantsHelper.animationFragment(getActivity());
			}
		}
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
					com.mttam.toollibrary.tools.Tools
							.setListViewHeightBasedOnChildren(productList, 0);
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
					com.mttam.toollibrary.tools.Tools
							.setListViewHeightBasedOnChildren(brandList, 0);
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
					com.mttam.toollibrary.tools.Tools
							.setListViewHeightBasedOnChildren(shopList, 0);
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
					com.mttam.toollibrary.tools.Tools
							.setListViewHeightBasedOnChildren(memberList, 0);
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
