package com.hitechno.sogoods.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.views.CustomFontTextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Provides a view for each <code>Category</code> in the application.
 * 
 */
public class CategoriesAdapter extends ArrayAdapter<Category> {

	private Context context;
	private int resource;
	private ArrayList<Category> models;

	private ImageLoader imageLoader;
	private DisplayImageOptions options;

	/**
	 * Default constructor for a subclass of BaseAdapter. This constructor also
	 * creates a reference to a singleton <code>imageCache</code>.
	 * 
	 * @param context
	 *            the context
	 * @param resource
	 *            the reference to the resource
	 * @param items
	 *            the items to wrap in a view
	 */
	public CategoriesAdapter(Context context, int resource,
			ArrayList<Category> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.models = objects;

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading().cacheOnDisc()
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(0)).cacheInMemory()
				.delayBeforeLoading(0).build();
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
		final ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = li.inflate(resource, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.titleView = (CustomFontTextView) convertView
					.findViewById(R.id.generic_title);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.generic_image);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final Category model = models.get(position);

		viewHolder.titleView.setText(model.title);
		imageLoader.displayImage(model.url, viewHolder.imageView, options);

		return convertView;
	}

	static class ViewHolder {
		CustomFontTextView titleView;
		ImageView imageView;
	}

}