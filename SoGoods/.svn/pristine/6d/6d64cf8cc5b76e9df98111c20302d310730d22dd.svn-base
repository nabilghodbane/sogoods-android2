package com.hitechno.sogoods.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Search;

public class SearchAdapter extends BaseAdapter {

	private Context context;
	private int resource;
	private ArrayList<Search> results;

	public SearchAdapter(Context context, int resource,
			ArrayList<Search> objects) {
		this.context = context;
		this.resource = resource;
		this.results = objects;
	}

	/**
	 * Sets array of model data for this adapter. The model data can be
	 * products, categories, brands, news, or profiles, so a generic type is
	 * used internally.
	 * 
	 * @param results
	 *            the data to be adapted for display
	 */
	public void setResults(ArrayList<Search> results) {
		this.results = results;
		this.notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LinearLayout searchView;

		if (convertView == null) {
			searchView = new LinearLayout(context);
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li;
			li = (LayoutInflater) context.getSystemService(inflater);
			li.inflate(resource, searchView, true);
		} else {
			searchView = (LinearLayout) convertView;
		}

		Search result = getItem(position);
		TextView searchResult = (TextView) searchView
				.findViewById(R.id.search_result);
		searchResult.setText(result.result);

		return searchView;
	}

	@Override
	public int getCount() {
		return this.results.size();
	}

	@Override
	public Search getItem(int position) {
		return this.results.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
