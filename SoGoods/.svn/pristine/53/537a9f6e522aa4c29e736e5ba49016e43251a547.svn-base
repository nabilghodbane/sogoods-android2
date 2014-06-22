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
import com.hitechno.sogoods.models.Profile;
import com.mttam.toollibrary.tools.Tools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class SearchMemberAdapter extends ArrayAdapter<Profile> {
	private Context context;
	private ImageLoader imageloader;
	private ArrayList<Profile> objects;
	private DisplayImageOptions options;
	private int textViewResourceId;

	public SearchMemberAdapter(Context paramContext, int paramInt,
			ArrayList<Profile> paramArrayList) {
		super(paramContext, paramInt, paramArrayList);
		this.context = paramContext;
		this.objects = paramArrayList;
		this.textViewResourceId = paramInt;
		imageloader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading().cacheOnDisc()
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(0)).cacheInMemory()
				.delayBeforeLoading(0).build();
	}

	public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
		if (paramView == null)
			paramView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(this.textViewResourceId, paramViewGroup, false);

		ImageView image = (ImageView) paramView
				.findViewById(R.id.item_search_member_image);
		TextView memberName = (TextView) paramView
				.findViewById(R.id.item_search_member_name);
		Profile profile = objects.get(paramInt);

		memberName.setText(profile.username);
		if (!Tools.isEmpty(profile.urlAvatarSmall))
			imageloader.displayImage(profile.urlAvatarSmall, image, options);
		else
			imageloader.displayImage(profile.urlHome, image, options);

		return paramView;
	}
}