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
import com.hitechno.sogoods.adapters.ProfileFollowsAdapter;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint("NewApi")
public class DiaryFollowsFragment
  extends Fragment
{
  private APIProfile ProfileContentProvider;
  private ProfileFollowsAdapter adapter;
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
    View localView = paramLayoutInflater.inflate(2130903160, paramViewGroup, false);
    this.progressBar = ((ProgressBar)localView.findViewById(2131230756));
    this.txtNoResult = ((TextView)localView.findViewById(2131230785));
    this.listview = ((PullAndLoadListView)localView.findViewById(2131231207));
    this.listview.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener()
    {
        public void onRefresh()
        {
          new DiaryFollowsFragment.NewDataAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
      });
  
    this.listview.setOnLoadMoreListener(new PullAndLoadListView.OnLoadMoreListener()
    {
      public void onLoadMore()
      {
        new DiaryFollowsFragment.LoadMoreDataTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
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
        DiaryFollowsFragment localDiaryFollowsFragment = DiaryFollowsFragment.this;
        localDiaryFollowsFragment.offset = (1 + localDiaryFollowsFragment.offset);
//        ArrayList localArrayList = DiaryFollowsFragment.this.ProfileContentProvider.loadFollower(DiaryFollowsFragment.this.valProfile, 10, DiaryFollowsFragment.this.offset, DiaryFollowsFragment.this.apiKey, DiaryFollowsFragment.this.config.typePhone);
 //       return localArrayList;
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
      DiaryFollowsFragment.this.listview.onLoadMoreComplete();
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
//          DiaryFollowsFragment.this.adapter = new ProfileFollowsAdapter(DiaryFollowsFragment.this.context, 2130903161, DiaryFollowsFragment.this.profiles);
//          DiaryFollowsFragment.this.listview.post(new Runnable()
//          {
//            public void run()
//            {
//              DiaryFollowsFragment.this.listview.setSelection(-10 + DiaryFollowsFragment.this.profiles.size());
//            }
//          });
//          DiaryFollowsFragment.this.listview.setAdapter(DiaryFollowsFragment.this.adapter);
//          DiaryFollowsFragment.this.progressBar.setVisibility(8);
//          DiaryFollowsFragment.this.listview.invalidateViews();
//          DiaryFollowsFragment.this.listview.onLoadMoreComplete();
//          return;
//        }
//        Profile localProfile = (Profile)localIterator.next();
//        DiaryFollowsFragment.this.profiles.add(localProfile);
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
//      try
//      {
//        DiaryFollowsFragment.this.offset = 0;
//        ArrayList localArrayList = DiaryFollowsFragment.this.ProfileContentProvider.loadFollower(DiaryFollowsFragment.this.valProfile, 10, DiaryFollowsFragment.this.offset, DiaryFollowsFragment.this.apiKey, DiaryFollowsFragment.this.config.typePhone);
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
      DiaryFollowsFragment.this.listview.onLoadMoreComplete();
    }
    
    protected void onPostExecute(ArrayList<Profile> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      if ((paramArrayList != null) && (paramArrayList.size() > 0))
      {
        DiaryFollowsFragment.this.txtNoResult.setVisibility(8);
        DiaryFollowsFragment.this.listview.setVisibility(0);
        DiaryFollowsFragment.this.profiles = paramArrayList;
        DiaryFollowsFragment.this.adapter = new ProfileFollowsAdapter(DiaryFollowsFragment.this.context, 2130903161, DiaryFollowsFragment.this.profiles);
        DiaryFollowsFragment.this.listview.setAdapter(DiaryFollowsFragment.this.adapter);
      }
//      for (;;)
//      {
//        DiaryFollowsFragment.this.progressBar.setVisibility(8);
//        DiaryFollowsFragment.this.listview.invalidateViews();
//        DiaryFollowsFragment.this.listview.onRefreshComplete();
//        return;
//        DiaryFollowsFragment.this.txtNoResult.setVisibility(0);
//        DiaryFollowsFragment.this.listview.setVisibility(8);
//      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      DiaryFollowsFragment.this.progressBar.setVisibility(0);
    }
  }
}
