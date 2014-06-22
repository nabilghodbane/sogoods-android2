package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.hitechno.sogoods.models.Photo;
import java.util.ArrayList;

public class GalleryAdapter
  extends ArrayAdapter<Photo>
{
  private Context context;
  private ImageLoader imageLoader;
  private ArrayList<Photo> lstPhotos;
  private DisplayImageOptions options;
  
  public GalleryAdapter(Context paramContext, int paramInt, ArrayList<Photo> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.lstPhotos = paramArrayList;
    this.imageLoader = ImageLoader.getInstance();
    this.options = new DisplayImageOptions.Builder().resetViewBeforeLoading().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Config.RGB_565).displayer(new FadeInBitmapDisplayer(0)).cacheInMemory().delayBeforeLoading(0).build();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903134, paramViewGroup, false);
    }
    ImageView localImageView = (ImageView)paramView.findViewById(2131231116);
    Photo localPhoto = (Photo)this.lstPhotos.get(paramInt);
    this.imageLoader.displayImage(localPhoto.url, localImageView, this.options);
    return paramView;
  }
}
