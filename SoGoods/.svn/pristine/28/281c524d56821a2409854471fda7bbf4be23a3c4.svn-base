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

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.caches.AssetCache;
import com.hitechno.sogoods.models.Photo;

/**
 * Provides a view for each <code>Photo</code> that belongs to a brand or a
 * profile.
 */
public class DetailPhotosAdapter extends BaseAdapter {

	private Context context;
	private int resource;

	private ArrayList<Photo> photos;

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
	public DetailPhotosAdapter(Context context, int resource, List<Photo> items) {
		super();
		this.context = context;
		this.resource = resource;
		this.photos = (ArrayList<Photo>) items;

		imageCache = AssetCache.getInstance(context);
	}

	/**
	 * Updates the adapters datasource with new products from the
	 * <code>LoaderManager</code>.
	 * 
	 * @param photos
	 *            the photos that the view will display
	 */
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
		this.notifyDataSetChanged();
	}

	public ArrayList<Photo> getPhotos() {
		return this.photos;
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
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			itemView.setLayoutParams(new GridView.LayoutParams(
					linearLayoutParams));
			itemView.setPadding(5, 5, 5, 5);
		} else {
			itemView = (LinearLayout) convertView;
		}

		Photo photo = getItem(position);

		itemView.setTag(photo.id);
		ImageView imageView = (ImageView) itemView
				.findViewById(R.id.photo_image);
		String imagePath = photo.url;

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
		return this.photos.size();
	}

	@Override
	public Photo getItem(int position) {
		return this.photos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
