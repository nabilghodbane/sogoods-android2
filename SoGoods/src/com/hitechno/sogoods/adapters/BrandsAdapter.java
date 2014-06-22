package com.hitechno.sogoods.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.caches.AssetCache;
import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.interfaces.GridItemModelInterface;

/**
 * Provides a view for each <code>Brand</code> in the application.
 * 
 */
public class BrandsAdapter extends BaseAdapter {

	private Context context;
	private int resource;
	private ArrayList<GridItemModelInterface> models;

	private AssetCache imageCache;

	/**
	 * Default constructor for a subclass of BaseAdapter. This constructor
	 * also creates a reference to a singleton <code>imageCache</code>.
	 * 
	 * @param context
	 *            the context
	 * @param resource
	 *            the reference to the resource
	 * @param items
	 *            the items to wrap in a view
	 */
	public BrandsAdapter(Context context, int resource,
			List<GridItemModelInterface> items) {
		super();
		this.context = context;
		this.resource = resource;
		this.models = (ArrayList<GridItemModelInterface>) items;

		imageCache = AssetCache.getInstance(context);
	}

	/**
	 * Sets array of model data for this adapter. The model data can be
	 * categories or brands, so a generic type is used internally.
	 * 
	 * @param models
	 *            the data to be adapted for display
	 */
	public void setModels(ArrayList<GridItemModelInterface> models) {
		this.models = models;

		this.notifyDataSetChanged();
	}

	/**
	 * Creates or finds a view.
	 * <p>
	 * The view includes an image, which is provided to the view from the
	 * <code>imageCache</code>.
	 * <p>
	 * TODO Replace <code>imagePath</code> with actual path to category icon.
	 * 
	 * @param position
	 *            the position in the <code>List<T></code>
	 * @param convertView
	 *            the view to be presented
	 * @param parent
	 *            the parent of the view to be presented
	 * 
	 * @return the view to be presented
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout itemView;

		if (convertView == null) {
			itemView = new LinearLayout(context);
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater li;
			li = (LayoutInflater) context.getSystemService(inflater);
			li.inflate(resource, itemView, true);

			LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
					parent.getWidth()
							/ ConstantsHelper.NUMBER_OF_COLUMNS_IN_GRID,
					parent.getWidth()
							/ ConstantsHelper.NUMBER_OF_COLUMNS_IN_GRID);
			itemView.setLayoutParams(new GridView.LayoutParams(
					linearLayoutParams));
			itemView.setPadding(5, 5, 5, 5);
		} else {
			itemView = (LinearLayout) convertView;
		}

		GridItemModelInterface model = getItem(position);

		TextView titleView = (TextView) itemView
				.findViewById(R.id.generic_title);
		titleView.setText(model.getTitle());

		ImageView imageView = (ImageView) itemView
				.findViewById(R.id.generic_image);

		Bitmap bitmap;
		synchronized (imageCache) {
			bitmap = imageCache.get(model.getUrl());
		}
		imageView.setImageBitmap(bitmap);

		return itemView;
	}

	@Override
	public int getCount() {
		return this.models.size();
	}

	@Override
	public GridItemModelInterface getItem(int position) {
		return this.models.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}