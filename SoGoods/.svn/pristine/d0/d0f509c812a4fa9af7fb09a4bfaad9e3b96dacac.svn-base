package com.hitechno.sogoods.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hitechno.sogoods.MyDiaryActivity;
import com.hitechno.sogoods.adapters.MyProfileBrandsAdapter;
import com.hitechno.sogoods.api.APIBrand;
import com.hitechno.sogoods.databases.BrandDBAdapter;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.MySharePreference;
import java.util.ArrayList;
import java.util.Iterator;

public class DiaryBrandsFragment
  extends Fragment
{
  private String apiKey;
  private BrandDBAdapter brandDB;
  private CategoryDBAdapter categoryDB;
  private Context context;
  private ListView listview;
  private APIBrand productContentProvider;
  private ProgressBar progressBar;
  private TextView txtNoResult;
  private int valProfile;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.apiKey = MySharePreference.getApiKey(this.context);
    this.productContentProvider = new APIBrand();
    this.valProfile = ((MyDiaryActivity)getActivity()).getProFile().profileID;
    this.brandDB = DBManager.getBrandDBAdapter(this.context);
    this.categoryDB = DBManager.getCategoryDBAdapter(this.context);
  }
  

@SuppressLint("NewApi")
public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903158, paramViewGroup, false);
    this.progressBar = ((ProgressBar)localView.findViewById(2131230756));
    this.txtNoResult = ((TextView)localView.findViewById(2131230785));
    this.listview = ((ListView)localView.findViewById(2131231203));
    this.txtNoResult.setVisibility(8);
    new NewDataAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    return localView;
  }
  
  private class NewDataAsync
    extends AsyncTask<Void, Void, ArrayList<String>>
  {
    private NewDataAsync() {}
    
    protected ArrayList<String> doInBackground(Void... paramVarArgs)
    {
      if (isCancelled()) {
        return null;
      }
      try
      {
        ArrayList localArrayList = DiaryBrandsFragment.this.productContentProvider.newLoadFan(DiaryBrandsFragment.this.valProfile, DiaryBrandsFragment.this.apiKey);
        return localArrayList;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(ArrayList<String> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      ArrayList<Brand> localArrayList1;
      Iterator localIterator;
      if ((paramArrayList != null) && (paramArrayList.size() > 0))
      {
        localArrayList1 = new ArrayList<Brand>();
        localIterator = paramArrayList.iterator();
        if (!localIterator.hasNext())
        {
          DiaryBrandsFragment.this.txtNoResult.setVisibility(8);
          DiaryBrandsFragment.this.listview.setVisibility(0);
          MyProfileBrandsAdapter localMyProfileBrandsAdapter = new MyProfileBrandsAdapter(DiaryBrandsFragment.this.context, 2130903132, localArrayList1);
          DiaryBrandsFragment.this.listview.setAdapter(localMyProfileBrandsAdapter);
        }
      }
//      for (;;)
//      {
//        DiaryBrandsFragment.this.progressBar.setVisibility(8);
//        DiaryBrandsFragment.this.listview.invalidateViews();
//        return;
//        String str1 = (String)localIterator.next();
//        Brand localBrand = DiaryBrandsFragment.this.brandDB.select(Integer.parseInt(str1));
//        ArrayList localArrayList2 = DiaryBrandsFragment.this.categoryDB.getAllCategoriesWithBrand(localBrand.brandID);
//        String str2 = "";
//        int i = 0;
//        if (i >= localArrayList2.size())
//        {
//          localBrand.listCategory = str2;
//          localArrayList1.add(localBrand);
//          break;
//        }
//        if (i != -1 + localArrayList2.size()) {}
//        for (str2 = str2 + ((Category)localArrayList2.get(i)).title + ", ";; str2 = str2 + ((Category)localArrayList2.get(i)).title)
//        {
//          i++;
//          break;
//        }
//        DiaryBrandsFragment.this.txtNoResult.setVisibility(0);
//        DiaryBrandsFragment.this.listview.setVisibility(8);
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      DiaryBrandsFragment.this.progressBar.setVisibility(0);
    }
  }
}

