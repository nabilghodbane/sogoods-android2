package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.content.res.Resources;
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
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Answer;

import java.util.ArrayList;

public class QuestionImageAdapter
  extends ArrayAdapter<Answer>
{
  private ArrayList<Answer> answers;
  private Context context;
  private ImageLoader imageLoader;
  private DisplayImageOptions options;
  private int resource;
  
  public QuestionImageAdapter(Context paramContext, int paramInt, ArrayList<Answer> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.resource = paramInt;
    this.answers = paramArrayList;
    this.imageLoader = ImageLoader.getInstance();
    this.options = new DisplayImageOptions.Builder().resetViewBeforeLoading().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Config.RGB_565).displayer(new FadeInBitmapDisplayer(0)).cacheInMemory().delayBeforeLoading(0).build();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.resource, paramViewGroup, false);
    }
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.quizz_item_image);
    Answer localAnswer = (Answer)this.answers.get(paramInt);
    this.imageLoader.displayImage(localAnswer.imageUrl, localImageView, this.options);
// TODO:   if (localAnswer.choosed)
//    {
//      paramView.setBackgroundColor(this.context.getResources().getColor(2131099800));
//      return paramView;
//    }
//    paramView.setBackgroundResource(2130837723);
    return paramView;
  }
}

