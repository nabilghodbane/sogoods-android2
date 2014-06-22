package com.hitechno.sogoods.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Product;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class SearchProductAdapter extends ArrayAdapter<Product> {

	private Context context;
	private ImageLoader imageloader;
	private ArrayList<Product> objects;
	private DisplayImageOptions options;
	private int textViewResourceId;

	public SearchProductAdapter(Context paramContext, int paramInt,
			ArrayList<Product> paramArrayList) {
		super(paramContext, paramInt, paramArrayList);
		this.context = paramContext;
		this.objects = paramArrayList;
		this.textViewResourceId = paramInt;
		this.imageloader = ImageLoader.getInstance();
		this.options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading().cacheOnDisc()
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(0)).cacheInMemory()
				.delayBeforeLoading(0).build();
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		if (paramView == null) {
			paramView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(textViewResourceId, paramViewGroup, false);
		}

		ImageView image = (ImageView) paramView
				.findViewById(R.id.item_search_product_image);
		TextView productName = (TextView) paramView
				.findViewById(R.id.item_search_product_name);
		TextView brandName = (TextView) paramView
				.findViewById(R.id.item_search_product_brand);

		Product localProduct = objects.get(paramInt);
		productName.setText(localProduct.title);
		brandName.setText(localProduct.brandName);
		imageloader.displayImage(localProduct.urlPopup, image, options);
		return paramView;
	}
}