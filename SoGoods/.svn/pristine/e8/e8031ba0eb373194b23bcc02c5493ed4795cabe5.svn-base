package com.hitechno.sogoods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.models.Brand;

import java.util.ArrayList;

public class SearchCityNameAdapter
  extends ArrayAdapter<Brand>
{
  private Context context;
  private ArrayList<Brand> lstBrand;
  private int resource;
  
  public SearchCityNameAdapter(Context paramContext, int paramInt, ArrayList<Brand> paramArrayList)
  {
    super(paramContext, paramInt, paramArrayList);
    this.context = paramContext;
    this.resource = paramInt;
    this.lstBrand = paramArrayList;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(this.resource, paramViewGroup, false);
    }
    Brand localBrand = (Brand)this.lstBrand.get(paramInt);
    ((TextView)paramView.findViewById(R.id.search_result)).setText(localBrand.cityName);
    return paramView;
  }
}

