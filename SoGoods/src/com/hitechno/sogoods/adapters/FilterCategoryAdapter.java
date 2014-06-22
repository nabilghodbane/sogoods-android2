package com.hitechno.sogoods.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Category;

public class FilterCategoryAdapter extends ArrayAdapter<Category> {

	private ArrayList<Category> brands;
	private int textViewResourceId;
	private Context context;

	public FilterCategoryAdapter(Context context, int textViewResourceId,
			ArrayList<Category> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		brands = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(textViewResourceId, parent, false);
		}

		TextView title = (TextView) convertView.findViewById(R.id.idtextfilter);
		title.setText(brands.get(position).title);

		return convertView;
	}
}
