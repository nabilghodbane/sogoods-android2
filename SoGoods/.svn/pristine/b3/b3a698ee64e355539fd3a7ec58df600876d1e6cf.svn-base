package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mttam.toollibrary.tools.Tools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Brand;

import java.util.ArrayList;

public class FilterBrandAdapter2
  extends ArrayAdapter<Brand>
{
  private ArrayList<Brand> brands;
  private Context context;
  private ImageLoader imageLoader;
  private DisplayImageOptions options;
  private int textViewResourceId;
  
  public FilterBrandAdapter2(Context paramContext, int paramInt, ArrayList<Brand> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.textViewResourceId = paramInt;
    this.brands = paramArrayList;
    this.imageLoader = ImageLoader.getInstance();
    this.options = new DisplayImageOptions.Builder().resetViewBeforeLoading().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Config.RGB_565).displayer(new FadeInBitmapDisplayer(0)).cacheInMemory().delayBeforeLoading(0).build();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.textViewResourceId, paramViewGroup, false);
    }
    TextView localTextView = (TextView)paramView.findViewById(R.id.generic_title);
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.generic_image);
    Brand localBrand = (Brand)this.brands.get(paramInt);
    localTextView.setText(localBrand.name);
    if (!Tools.isEmpty(localBrand.url))
    {
      String[] arrayOfString = localBrand.url.split(":");
      String str = arrayOfString[1] + ":" + arrayOfString[2];
      this.imageLoader.displayImage(str.trim(), localImageView, this.options);
    }
    return paramView;
  }
}

