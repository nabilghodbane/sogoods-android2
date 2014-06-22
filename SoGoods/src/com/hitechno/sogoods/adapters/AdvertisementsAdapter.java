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
import com.hitechno.sogoods.models.Advertisement;

/**
 * Provides a view for each <code>Advertisement</code> in the application.
 * 
 */
public class AdvertisementsAdapter extends BaseAdapter {

	private Context context;
	private int resource;
	private AssetCache imageCache;

	private ArrayList<Advertisement> advertisements;

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
	public AdvertisementsAdapter(Context context, int resource,
			List<Advertisement> items) {
		super();
		this.context = context;
		this.resource = resource;
		this.advertisements = (ArrayList<Advertisement>) items;

		imageCache = AssetCache.getInstance(context);
	}

	/**
	 * Updates the adapters datasource with new advertisements from the
	 * <code>LoaderManager</code>.
	 * 
	 * @param advertisements
	 *            the advertisements that the view will display
	 */
	public void setAdvertisements(ArrayList<Advertisement> advertisements) {
		this.advertisements = advertisements;
		this.notifyDataSetChanged();
	}

	/**
	 * Creates or finds a view.
	 * <p>
	 * The view includes an image, which is provided to the view from the
	 * <code>imageCache</code>.
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

		Advertisement advertisement = getItem(position);

		itemView.setTag(advertisement.id);
		TextView titleView = (TextView) itemView
				.findViewById(R.id.advertisement_title);
		ImageView imageView = (ImageView) itemView
				.findViewById(R.id.advertisement_image);

		titleView.setText(advertisement.title);

		String imagePath = advertisement.url;

		if (imagePath.matches("^http.*"))
			imagePath = "http";

		Bitmap bitmap;
		synchronized (imageCache) {
			bitmap = imageCache.get(imagePath);
		}

		imageView.setImageBitmap(bitmap);
		return itemView;
	}

	@Override
	public int getCount() {
		return this.advertisements.size();
	}

	@Override
	public Advertisement getItem(int position) {
		return this.advertisements.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public ArrayList<Advertisement> getAdvertisements() {
		return this.advertisements;
	}
}
