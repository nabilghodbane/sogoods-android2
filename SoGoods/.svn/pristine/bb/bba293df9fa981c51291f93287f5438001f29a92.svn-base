package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Store;

import java.util.ArrayList;

public class SearchShopAdapter extends ArrayAdapter<Store> {
	private Context context;
	private ArrayList<Store> objects;
	private int textViewResourceId;

	public SearchShopAdapter(Context paramContext, int paramInt,
			ArrayList<Store> paramArrayList) {
		super(paramContext, paramInt, paramArrayList);
		this.context = paramContext;
		this.objects = paramArrayList;
		this.textViewResourceId = paramInt;
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		if (paramView == null)
			paramView = ((LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(textViewResourceId, paramViewGroup, false);

		TextView shopName = (TextView) paramView
				.findViewById(R.id.item_search_shop_name);
		TextView shopAddress = (TextView) paramView
				.findViewById(R.id.item_search_shop_address);
		TextView shopCity = (TextView) paramView
				.findViewById(R.id.item_search_shop_city);
		TextView shopDistance = (TextView) paramView
				.findViewById(R.id.item_search_shop_distance);

		Store store = objects.get(paramInt);
		shopName.setText(store.name);
		shopAddress.setText(store.address);
		shopCity.setText(store.cityName);
		if (store.distance > 1.0D)
			shopDistance.setText(store.distance + " "
					+ context.getString(R.string.meter_s));
		else if (store.distance <= 1.0D)
			shopDistance.setText(store.distance + " "
					+ this.context.getString(R.string.meter));

		return paramView;
	}
}