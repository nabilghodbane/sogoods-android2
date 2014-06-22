package com.hitechno.sogoods.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;
import com.hitechno.sogoods.AddProductActivity;
import com.hitechno.sogoods.MyDiaryActivity;
import com.hitechno.sogoods.adapters.MyProfileSOAdapter;
import com.hitechno.sogoods.api.APIProduct;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Product;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint("NewApi")
public class DiarySOFragment
  extends Fragment
{
  private MyProfileSOAdapter adapter;
  private Button buttonAddproduct;
  private Configuration config;
  private Context context;
  private PullAndLoadListView listview;
  private int offset = 0;
  private APIProduct productContentProvider;
  private ArrayList<Product> products;
  private ProgressBar progressBar;
  private TextView txtNoResult;
  private int valProfile;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    String str = MySharePreference.getApiKey(this.context);
    this.config = Configuration.getInstance(this.context);
    this.productContentProvider = new APIProduct(this.context, str, this.config.currentLanguage, this.config.typePhone);
    this.valProfile = ((MyDiaryActivity)getActivity()).getProFile().profileID;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903163, paramViewGroup, false);
    this.progressBar = ((ProgressBar)localView.findViewById(2131230756));
    this.buttonAddproduct = ((Button)localView.findViewById(2131231213));
    this.txtNoResult = ((TextView)localView.findViewById(2131230785));
    this.listview = ((PullAndLoadListView)localView.findViewById(2131231214));
    this.buttonAddproduct.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(DiarySOFragment.this.context, AddProductActivity.class);
        DiarySOFragment.this.context.startActivity(localIntent);
        ConstantsHelper.animationFragment(DiarySOFragment.this.getActivity());
      }
    });
    this.listview.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener()
    {
        public void onRefresh()
        {
          new DiarySOFragment.NewDataAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
      });
   
    this.listview.setOnLoadMoreListener(new PullAndLoadListView.OnLoadMoreListener()
    {
      public void onLoadMore()
      {
        new DiarySOFragment.LoadMoreDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      }
    });
    new NewDataAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    return localView;
  }
  
  private class LoadMoreDataTask
    extends AsyncTask<Void, Void, ArrayList<Product>>
  {
    private LoadMoreDataTask() {}
    
    protected ArrayList<Product> doInBackground(Void... paramVarArgs)
    {
      if (isCancelled()) {
        return null;
      }
//      try
//      {
//        DiarySOFragment localDiarySOFragment = DiarySOFragment.this;
//        localDiarySOFragment.offset = (1 + localDiarySOFragment.offset);
//        ArrayList localArrayList = DiarySOFragment.this.productContentProvider.getSOProfileLike(DiarySOFragment.this.valProfile, 10, DiarySOFragment.this.offset);
//        return localArrayList;
//      }
//      catch (Exception localException)
//      {
//        localException.printStackTrace();
//      }
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      DiarySOFragment.this.listview.onLoadMoreComplete();
    }
    
    protected void onPostExecute(ArrayList<Product> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      Iterator localIterator;
      if ((paramArrayList != null) && (paramArrayList.size() > 0)) {
        localIterator = paramArrayList.iterator();
      }
//      for (;;)
//      {
//        if (!localIterator.hasNext())
//        {
//          DiarySOFragment.this.adapter = new MyProfileSOAdapter(DiarySOFragment.this.context, 2130903135, DiarySOFragment.this.products);
//          DiarySOFragment.this.listview.post(new Runnable()
//          {
//            public void run()
//            {
//              DiarySOFragment.this.listview.setSelection(-10 + DiarySOFragment.this.products.size());
//            }
//          });
//          DiarySOFragment.this.listview.setAdapter(DiarySOFragment.this.adapter);
//          DiarySOFragment.this.progressBar.setVisibility(8);
//          DiarySOFragment.this.listview.invalidateViews();
//          DiarySOFragment.this.listview.onLoadMoreComplete();
//          return;
//        }
//        Product localProduct = (Product)localIterator.next();
//        DiarySOFragment.this.products.add(localProduct);
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class NewDataAsync
    extends AsyncTask<Void, Void, ArrayList<Product>>
  {
    private NewDataAsync() {}
    
    protected ArrayList<Product> doInBackground(Void... paramVarArgs)
    {
      if (isCancelled()) {
        return null;
      }
//      try
//      {
//        DiarySOFragment.this.offset = 0;
//        ArrayList localArrayList = DiarySOFragment.this.productContentProvider.getSOProfileLike(DiarySOFragment.this.valProfile, 10, DiarySOFragment.this.offset);
//        return localArrayList;
//      }
//      catch (Exception localException)
//      {
//        localException.printStackTrace();
//      }
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      DiarySOFragment.this.listview.onLoadMoreComplete();
    }
    
    protected void onPostExecute(ArrayList<Product> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      if ((paramArrayList != null) && (paramArrayList.size() > 0))
      {
        DiarySOFragment.this.txtNoResult.setVisibility(8);
        DiarySOFragment.this.listview.setVisibility(0);
        DiarySOFragment.this.products = paramArrayList;
        DiarySOFragment.this.adapter = new MyProfileSOAdapter(DiarySOFragment.this.context, 2130903135, DiarySOFragment.this.products);
        DiarySOFragment.this.listview.setAdapter(DiarySOFragment.this.adapter);
      }
//      for (;;)
//      {
//        DiarySOFragment.this.progressBar.setVisibility(8);
//        DiarySOFragment.this.listview.invalidateViews();
//        DiarySOFragment.this.listview.onRefreshComplete();
//        return;
//        DiarySOFragment.this.txtNoResult.setVisibility(0);
//        DiarySOFragment.this.listview.setVisibility(8);
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      DiarySOFragment.this.progressBar.setVisibility(0);
    }
  }
}
