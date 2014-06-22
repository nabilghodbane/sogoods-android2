package com.hitechno.sogoods.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hitechno.sogoods.AddProductActivity;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.adapters.CategoriesAdapter;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Category;

import java.util.ArrayList;

public class ChooseCategoriesFragment
  extends Fragment
{
  private CategoriesAdapter adapter;
  private CategoryDBAdapter categoryDB;
  private Context context;
  private ListView listView;
  private ArrayList<Category> lstCategories;
  private ProgressBar progressBar;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.categoryDB = DBManager.getCategoryDBAdapter(this.context);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(R.layout.fragment_choose_categories, paramViewGroup, false);
    this.listView = ((ListView)localView.findViewById(R.id.categories_grid_view));
    this.progressBar = ((ProgressBar)localView.findViewById(R.id.pbLoading));
    this.progressBar.setVisibility(8);
    ((TextView)localView.findViewById(R.id.profile_header_menu_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((AddProductActivity)ChooseCategoriesFragment.this.getActivity()).onBackPressed();
      }
    });
    try
    {
      this.lstCategories = this.categoryDB.getAllCategories();
      if ((this.lstCategories != null) && (this.lstCategories.size() > 0))
      {
        this.adapter = new CategoriesAdapter(getActivity(), R.layout.category_and_brand_grid_item, this.lstCategories);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            Category localCategory = (Category)ChooseCategoriesFragment.this.lstCategories.get(paramAnonymousInt);
            AddProductActivity localAddProductActivity = (AddProductActivity)ChooseCategoriesFragment.this.getActivity();
            localAddProductActivity.categoryLabel.setText(localCategory.title);
            localAddProductActivity.CATEGORY_ID = localCategory.categoryID;
            localAddProductActivity.onBackPressed();
          }
        });
      }
      return localView;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localView;
  }
}
