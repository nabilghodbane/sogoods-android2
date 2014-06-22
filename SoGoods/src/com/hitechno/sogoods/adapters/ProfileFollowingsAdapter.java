package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.MySharePreference;
import java.util.ArrayList;

public class ProfileFollowingsAdapter
  extends ArrayAdapter<Profile>
{
  public static int CLICK_TAB_PROFILE_MENU;
  private Context context;
  public ImageLoader imageLoader;
  ImageView imageView;
  ArrayList<Profile> lstpro;
  private DisplayImageOptions options;
  Profile profile;
  private ArrayList<Profile> profiles;
  ProgressBar progressBar;
  private int resource;
  
  public ProfileFollowingsAdapter(Context paramContext, int paramInt, ArrayList<Profile> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.resource = paramInt;
    this.profiles = paramArrayList;
    this.imageLoader = ImageLoader.getInstance();
    this.options = new DisplayImageOptions.Builder().resetViewBeforeLoading().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Config.RGB_565).displayer(new FadeInBitmapDisplayer(0)).cacheInMemory().delayBeforeLoading(0).build();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.resource, paramViewGroup, false);
    }
    Profile localProfile = (Profile)this.profiles.get(paramInt);
    ImageView localImageView = (ImageView)paramView.findViewById(2131231208);
    TextView localTextView1 = (TextView)paramView.findViewById(2131231112);
    TextView localTextView2 = (TextView)paramView.findViewById(2131231210);
    TextView localTextView3 = (TextView)paramView.findViewById(2131231211);
    TextView localTextView4 = (TextView)paramView.findViewById(2131231209);
    if (paramInt % 2 == 0)
    {
      paramView.setBackgroundColor(-1);
      localTextView2.setText(localProfile.username);
      localTextView3.setText(localProfile.cityName);
      localTextView1.setText(localProfile.percent + "% " + this.context.getResources().getString(2131165443));
      if (localProfile.followed != 1) {
//        break label246;
      }
      localTextView4.setText(this.context.getResources().getString(2131165442));
      localTextView4.setVisibility(0);
      localTextView4.setBackgroundResource(2130837653);
    }
//    for (;;)
//    {
//      this.imageLoader.displayImage(localProfile.urlAvatarSmall, localImageView, this.options);
//      return paramView;
//      paramView.setBackgroundColor(this.context.getResources().getColor(2131099787));
//      break;
//      label246:
//      if (localProfile.profileID == MySharePreference.getProfileID(this.context))
//      {
//        localTextView4.setVisibility(8);
//      }
//      else
//      {
//        localTextView4.setText(this.context.getResources().getString(2131165441));
//        localTextView4.setVisibility(0);
//        localTextView4.setBackgroundResource(2130837652);
//      }
//    }
	return paramView;
  }
}
