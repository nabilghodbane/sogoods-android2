package com.hitechno.sogoods.fragments;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.adapters.MemberAdapter;
import com.hitechno.sogoods.api.APIMember;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MembersFragment extends Fragment {

	private ArrayList<Profile> newMembers;
	private ArrayList<Profile> suggestedMembers;
	private ArrayList<Profile> popularMembers;
	private MemberAdapter adapter;

	private int newOffset;
	private int suggestedOffset;
	private int popularOffset;

	public int CLICK_TAB = 1;

	private Button newButton, suggestedButton, popularButton;
	private PullAndLoadListView listView;
	private ProgressBar progressBar;

	private APIMember profileContentProvider;

	private static MembersFragment instance;

	private NewDataAsync newDataAsync;

	private String apiKey;
	private Configuration config;

	private Context context;

	public static MembersFragment getInstance() {
		if (instance == null) {
			instance = new MembersFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		context = getActivity();

		newMembers = new ArrayList<Profile>();
		suggestedMembers = new ArrayList<Profile>();
		popularMembers = new ArrayList<Profile>();
		profileContentProvider = new APIMember();

		apiKey = MySharePreference.getApiKey(context);
		config = Configuration.getInstance(context);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_members, container,
				false);

		newButton = (Button) rootView
				.findViewById(R.id.frag_members_new_button);
		suggestedButton = (Button) rootView
				.findViewById(R.id.frag_members_suggested_button);
		popularButton = (Button) rootView
				.findViewById(R.id.frag_members_popular_button);
		listView = (PullAndLoadListView) rootView
				.findViewById(R.id.frag_members_listview);
		progressBar = (ProgressBar) rootView
				.findViewById(R.id.frag_members_progressbar);

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

		newButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CLICK_TAB = 1;
				newButton
						.setBackgroundResource(R.drawable.background_white_with_line_gray);
				suggestedButton.setBackgroundColor(Color.TRANSPARENT);
				popularButton.setBackgroundColor(Color.TRANSPARENT);

				loadData();
				loadNewMembersList();
			}
		});

		suggestedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CLICK_TAB = 2;
				newButton.setBackgroundColor(Color.TRANSPARENT);
				suggestedButton
						.setBackgroundResource(R.drawable.background_white_with_line_gray);
				popularButton.setBackgroundColor(Color.TRANSPARENT);

				loadData();
				loadSuggestedMembersList();
			}
		});

		popularButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CLICK_TAB = 3;
				newButton.setBackgroundColor(Color.TRANSPARENT);
				suggestedButton.setBackgroundColor(Color.TRANSPARENT);
				popularButton
						.setBackgroundResource(R.drawable.background_white_with_line_gray);

				loadData();
				loadPopularMembersList();
			}
		});

		return rootView;
	}

	private void loadNewMembersList() {
		if (newMembers != null && newMembers.size() > 0) {
			MemberAdapter adapter = new MemberAdapter(getActivity(),
					R.layout.item_profile_list, newMembers);
			listView.setAdapter(adapter);
		} else {
			listView.setAdapter(null);
		}
	}

	private void loadSuggestedMembersList() {
		if (suggestedMembers != null && suggestedMembers.size() > 0) {
			MemberAdapter adapter = new MemberAdapter(getActivity(),
					R.layout.item_profile_list, suggestedMembers);
			listView.setAdapter(adapter);
		} else {
			listView.setAdapter(null);
		}
	}

	private void loadPopularMembersList() {
		if (popularMembers != null && popularMembers.size() > 0) {
			MemberAdapter adapter = new MemberAdapter(getActivity(),
					R.layout.item_profile_list, popularMembers);
			listView.setAdapter(adapter);
		} else {
			listView.setAdapter(null);
		}
	}

	@SuppressLint("NewApi")
	public void loadData() {
		if (newDataAsync != null) {
			newDataAsync.cancel(true);
		}
		newDataAsync = new NewDataAsync();
		newDataAsync.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	private class NewDataAsync extends
			AsyncTask<Void, Void, ArrayList<Profile>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected ArrayList<Profile> doInBackground(Void... params) {
			if (isCancelled()) {
				return null;
			}
			try {
				switch (CLICK_TAB) {
				case 1:
					newOffset = 0;
					return profileContentProvider.onLoadNews(10, newOffset,
							apiKey, config.typePhone);
				case 2:
					suggestedOffset = 0;
					return profileContentProvider.onLoadSuggested(10,
							suggestedOffset, apiKey, config.typePhone);
				case 3:
					popularOffset = 0;
					return profileContentProvider.onLoadPopular(10,
							popularOffset, apiKey, config.typePhone);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Profile> result) {
			super.onPostExecute(result);
			if (result != null && result.size() > 0) {
				switch (CLICK_TAB) {
				case 1:
					newMembers = result;
					adapter = new MemberAdapter(getActivity(),
							R.layout.item_profile_list, newMembers);
					break;
				case 2:
					suggestedMembers = result;
					adapter = new MemberAdapter(getActivity(),
							R.layout.item_profile_list, suggestedMembers);
					break;
				case 3:
					popularMembers = result;
					adapter = new MemberAdapter(getActivity(),
							R.layout.item_profile_list, popularMembers);
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
			AsyncTask<Void, Void, ArrayList<Profile>> {

		@Override
		protected ArrayList<Profile> doInBackground(Void... params) {
			if (isCancelled()) {
				return null;
			}
			try {
				switch (CLICK_TAB) {
				case 1:
					newOffset += 1;
					return profileContentProvider.onLoadNews(10, newOffset,
							apiKey, config.typePhone);
				case 2:
					suggestedOffset += 1;
					return profileContentProvider.onLoadSuggested(10,
							suggestedOffset, apiKey, config.typePhone);
				case 3:
					popularOffset += 1;
					return profileContentProvider.onLoadPopular(10,
							popularOffset, apiKey, config.typePhone);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Profile> result) {
			super.onPostExecute(result);
			if (result != null && result.size() > 0) {
				switch (CLICK_TAB) {
				case 1:
					for (Profile p : result) {
						newMembers.add(p);
					}
					adapter = new MemberAdapter(getActivity(),
							R.layout.item_profile_list, newMembers);
					listView.post(new Runnable() {

						@Override
						public void run() {
							listView.setSelection(newMembers.size() - 10);
						}
					});
					break;
				case 2:
					for (Profile p : result) {
						suggestedMembers.add(p);
					}
					adapter = new MemberAdapter(getActivity(),
							R.layout.item_profile_list, suggestedMembers);
					listView.post(new Runnable() {

						@Override
						public void run() {
							listView.setSelection(suggestedMembers.size() - 10);
						}
					});
					break;
				case 3:
					for (Profile p : result) {
						popularMembers.add(p);
					}
					adapter = new MemberAdapter(getActivity(),
							R.layout.item_profile_list, popularMembers);
					listView.post(new Runnable() {

						@Override
						public void run() {
							listView.setSelection(popularMembers.size() - 10);
						}
					});
					break;
				}

				listView.setAdapter(adapter);
			}
			progressBar.setVisibility(View.GONE);

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