package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mttam.toollibrary.tools.Tools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.hitechno.sogoods.models.Product;
import com.hitechno.sogoods.views.TouchImageView;
import java.util.ArrayList;

public class MyProfileSOAdapter
  extends ArrayAdapter<Product>
{
  TextView addedByLabel;
  TextView brandName;
  private Context context;
  private ImageLoader imageLoader;
  TouchImageView imageView;
  ImageView imgCut;
  TextView likesNumberLabel;
  PopupWindow mpopup;
  private DisplayImageOptions options;
  View popUpView;
  TextView productName;
  private ArrayList<Product> products;
  ProgressBar progressBar;
  ProgressBar progressBar1;
  private int resource;
  TextView textViewAddBy;
  TextView textViewNamePro;
  TextView txtContentPro;
  
  public MyProfileSOAdapter(Context paramContext, int paramInt, ArrayList<Product> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.resource = paramInt;
    this.products = paramArrayList;
    this.imageLoader = ImageLoader.getInstance();
    this.options = new DisplayImageOptions.Builder().resetViewBeforeLoading().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Config.RGB_565).displayer(new FadeInBitmapDisplayer(0)).cacheInMemory().delayBeforeLoading(0).build();
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.resource, paramViewGroup, false);
    }
    ImageView localImageView = (ImageView)paramView.findViewById(2131231094);
    this.productName = ((TextView)paramView.findViewById(2131231091));
    this.brandName = ((TextView)paramView.findViewById(2131231092));
    this.addedByLabel = ((TextView)paramView.findViewById(2131231099));
    this.progressBar1 = ((ProgressBar)paramView.findViewById(2131230839));
    this.likesNumberLabel = ((TextView)paramView.findViewById(2131231096));
    TextView localTextView = ((TextView)paramView.findViewById(2131231101));
//    
//  ((ImageView)paramView.findViewById(2131231100));
//    ((ImageView)paramView.findViewById(2131231097));
    Product localProduct = (Product)this.products.get(paramInt);
    int i;
    if (paramInt % 2 == 0)
    {
      paramView.setBackgroundColor(-1);
      this.productName.setText(localProduct.title);
      this.brandName.setText(localProduct.brandName);
      String str = "Added by <b>" + localProduct.added_by + "</b>";
      this.addedByLabel.setText(Html.fromHtml(str));
      this.likesNumberLabel.setText(localProduct.numberOfLikes + " " + this.context.getResources().getString(2131165410));
      i = localProduct.numberOfComments;
      if (i <= 1) {
 //       break label375;
      }
      localTextView.setText(i + " " + this.context.getResources().getString(2131165735));
    }
//    for (;;)
//    {
//      if (Tools.isEmpty(localProduct.idLike)) {
//        localProduct.likeType = "0";
//      }
//      this.imageLoader.displayImage(localProduct.urlAvatarSquare, localImageView, this.options);
      return paramView;
//      paramView.setBackgroundColor(this.context.getResources().getColor(2131099787));
//      break;
//      label375:
//      localTextView.setText(i + " " + this.context.getResources().getString(2131165734));
//    }
  }
}
