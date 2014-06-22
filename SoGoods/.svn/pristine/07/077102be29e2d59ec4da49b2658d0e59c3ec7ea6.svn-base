package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Brand;

import java.util.ArrayList;

public class SearchBrandAdapter extends ArrayAdapter<Brand> {

	private Context context;
	private ArrayList<Brand> objects;
	private int textViewResourceId;

	public SearchBrandAdapter(Context paramContext, int paramInt,
			ArrayList<Brand> paramArrayList) {
		super(paramContext, paramInt, paramArrayList);
		this.context = paramContext;
		this.objects = paramArrayList;
		this.textViewResourceId = paramInt;
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		if (paramView == null) {
			paramView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(textViewResourceId, paramViewGroup, false);
		}

		TextView brandName = (TextView) paramView
				.findViewById(R.id.item_search_brand_name);
		TextView brandDes = (TextView) paramView
				.findViewById(R.id.item_search_brand_des);
		Brand localBrand = objects.get(paramInt);
		brandName.setText(localBrand.name);
		brandDes.setText(localBrand.listCategory);
		return paramView;
	}
}
