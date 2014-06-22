package com.hitechno.sogoods.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;
import com.hitechno.sogoods.MyDiaryActivity;
import com.hitechno.sogoods.adapters.ProfileFollowingsAdapter;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint("NewApi")
public class DiaryFollowingsFragment
  extends Fragment
{
  private APIProfile ProfileContentProvider;
  private ProfileFollowingsAdapter adapter;
  private String apiKey;
  private Configuration config;
  private Context context;
  private PullAndLoadListView listview;
  private int offset = 0;
  private ArrayList<Profile> profiles;
  private ProgressBar progressBar;
  private TextView txtNoResult;
  private int valProfile;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.apiKey = MySharePreference.getApiKey(this.context);
    this.config = Configuration.getInstance(this.context);
    this.ProfileContentProvider = new APIProfile();
    this.valProfile = ((MyDiaryActivity)getActivity()).getProFile().profileID;
  }
  
  @SuppressLint("NewApi")
public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903159, paramViewGroup, false);
    this.progressBar = ((ProgressBar)localView.findViewById(2131230756));
    this.txtNoResult = ((TextView)localView.findViewById(2131230785));
    this.listview = ((PullAndLoadListView)localView.findViewById(2131231205));

    this.listview.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener()
    {
      public void onRefresh()
      {
        new DiaryFollowingsFragment.NewDataAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      }
    });
    this.listview.setOnLoadMoreListener(new PullAndLoadListView.OnLoadMoreListener()
    {
      @SuppressLint("NewApi")
	public void onLoadMore()
      {
        new DiaryFollowingsFragment.LoadMoreDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      }
    });
    new NewDataAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    return localView;
  }
  
  private class LoadMoreDataTask
    extends AsyncTask<Void, Void, ArrayList<Profile>>
  {
    private LoadMoreDataTask() {}
    
    protected ArrayList<Profile> doInBackground(Void... paramVarArgs)
    {
      if (isCancelled()) {
        return null;
      }
      try
      {
        DiaryFollowingsFragment localDiaryFollowingsFragment = DiaryFollowingsFragment.this;
        localDiaryFollowingsFragment.offset = (1 + localDiaryFollowingsFragment.offset);
        ArrayList localArrayList = DiaryFollowingsFragment.this.ProfileContentProvider.loadFollowing(DiaryFollowingsFragment.this.valProfile, 10, DiaryFollowingsFragment.this.offset, DiaryFollowingsFragment.this.apiKey, DiaryFollowingsFragment.this.config.typePhone);
        return localArrayList;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      DiaryFollowingsFragment.this.listview.onLoadMoreComplete();
    }
    
    protected void onPostExecute(ArrayList<Profile> paramArrayList)
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
//          adapter = new ProfileFollowingsAdapter(DiaryFollowingsFragment.this.context, 2130903161, DiaryFollowingsFragment.this.profiles);
//          DiaryFollowingsFragment.this.listview.post(new Runnable()
//          {
//            public void run()
//            {
//              DiaryFollowingsFragment.this.listview.setSelection(-10 + DiaryFollowingsFragment.this.profiles.size());
//            }
//          });
//          DiaryFollowingsFragment.this.listview.setAdapter(DiaryFollowingsFragment.this.adapter);
//          DiaryFollowingsFragment.this.progressBar.setVisibility(8);
//          DiaryFollowingsFragment.this.listview.invalidateViews();
//          DiaryFollowingsFragment.this.listview.onLoadMoreComplete();
//          return;
//        }
//        Profile localProfile = (Profile)localIterator.next();
//        DiaryFollowingsFragment.this.profiles.add(localProfile);
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class NewDataAsync
    extends AsyncTask<Void, Void, ArrayList<Profile>>
  {
    private NewDataAsync() {}
    
    protected ArrayList<Profile> doInBackground(Void... paramVarArgs)
    {
      if (isCancelled()) {
        return null;
      }
      try
      {
        DiaryFollowingsFragment.this.offset = 0;
        ArrayList localArrayList = DiaryFollowingsFragment.this.ProfileContentProvider.loadFollowing(DiaryFollowingsFragment.this.valProfile, 10, DiaryFollowingsFragment.this.offset, DiaryFollowingsFragment.this.apiKey, DiaryFollowingsFragment.this.config.typePhone);
        return localArrayList;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
      DiaryFollowingsFragment.this.listview.onLoadMoreComplete();
    }
    
    protected void onPostExecute(ArrayList<Profile> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      if ((paramArrayList != null) && (paramArrayList.size() > 0))
      {
        DiaryFollowingsFragment.this.txtNoResult.setVisibility(8);
        DiaryFollowingsFragment.this.listview.setVisibility(0);
        DiaryFollowingsFragment.this.profiles = paramArrayList;
        DiaryFollowingsFragment.this.adapter = new ProfileFollowingsAdapter(DiaryFollowingsFragment.this.context, 2130903161, DiaryFollowingsFragment.this.profiles);
        DiaryFollowingsFragment.this.listview.setAdapter(DiaryFollowingsFragment.this.adapter);
      }
//      for (;;)
//      {
//        DiaryFollowingsFragment.this.progressBar.setVisibility(8);
//        DiaryFollowingsFragment.this.listview.invalidateViews();
//        DiaryFollowingsFragment.this.listview.onRefreshComplete();
//        return;
//        DiaryFollowingsFragment.this.txtNoResult.setVisibility(0);
//        DiaryFollowingsFragment.this.listview.setVisibility(8);
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      DiaryFollowingsFragment.this.progressBar.setVisibility(0);
    }
  }
}

