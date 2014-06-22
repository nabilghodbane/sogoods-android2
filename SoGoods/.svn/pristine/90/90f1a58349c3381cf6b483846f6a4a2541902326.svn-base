package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.hitechno.sogoods.models.Brand;
import java.util.ArrayList;

public class MyProfileBrandsAdapter
  extends ArrayAdapter<Brand>
{
  private ArrayList<Brand> brands;
  private Context context;
  private int resource;
  
  public MyProfileBrandsAdapter(Context paramContext, int paramInt, ArrayList<Brand> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.resource = paramInt;
    this.brands = paramArrayList;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.resource, paramViewGroup, false);
    }
    Brand localBrand = (Brand)this.brands.get(paramInt);
    TextView localTextView1 = (TextView)paramView.findViewById(2131231104);
    TextView localTextView2 = (TextView)paramView.findViewById(2131231105);
    localTextView1.setText(localBrand.name);
    localTextView2.setText(localBrand.listCategory);
    return paramView;
  }
}

