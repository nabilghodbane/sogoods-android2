package com.hitechno.sogoods.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.api.APINotification;
import com.hitechno.sogoods.models.Notification;
import com.hitechno.sogoods.util.MySharePreference;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class NotificationSettingFragment
  extends Fragment
{
  private ToggleButton ToggleButtonFollowingAddComm;
  private ToggleButton ToggleButtonFollowingAddPic;
  private ToggleButton ToggleButtonFollowingAddPro;
  private ToggleButton ToggleButtonNewCommentPro;
  private ToggleButton ToggleButtonNewFollower;
  private String apiKey;
  private TextView bttSave;
  private APINotification contentProvider;
  private Context context;
  private int follComm;
  private int follPic;
  private int follPro;
  private int newComm;
  private int newfoll;
  private ProgressBar progressBar;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.contentProvider = new APINotification();
    this.apiKey = MySharePreference.getApiKey(this.context);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(R.layout.fragment_notifications_setting, paramViewGroup, false);
    this.bttSave = ((TextView)localView.findViewById(R.id.notificationsetting_header_right_button));
    this.progressBar = ((ProgressBar)localView.findViewById(R.id.notisetting_progressbar));
    this.ToggleButtonNewFollower = ((ToggleButton)localView.findViewById(R.id.notisettting_toggle_newfollower));
    this.ToggleButtonNewCommentPro = ((ToggleButton)localView.findViewById(R.id.notisettting_toggle_newcommentproduct));
    this.ToggleButtonFollowingAddPro = ((ToggleButton)localView.findViewById(R.id.notisettting_toggle_newproduct));
    this.ToggleButtonFollowingAddComm = ((ToggleButton)localView.findViewById(R.id.notisettting_toggle_newcomment));
    this.ToggleButtonFollowingAddPic = ((ToggleButton)localView.findViewById(R.id.notisettting_toggle_newpicture));
    String str = MySharePreference.getNotificationFilter(this.context);
    Log.w("the str", ""+str);
    if (Integer.parseInt(str.substring(0, 1)) == 1) {
      this.ToggleButtonNewFollower.setChecked(true);
    }
    if (Integer.parseInt(str.substring(1, 2)) != 1) {
      this.ToggleButtonNewCommentPro.setChecked(true);
    }
    if (Integer.parseInt(str.substring(2, 3)) != 1) {
      this.ToggleButtonFollowingAddPro.setChecked(true);
    }
    if (Integer.parseInt(str.substring(3, 4)) != 1) {
      this.ToggleButtonFollowingAddComm.setChecked(true);
    }
    if (Integer.parseInt(str.substring(4, 5)) != 1) {
      this.ToggleButtonFollowingAddPic.setChecked(true);
    }
    this.bttSave.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!NotificationSettingFragment.this.ToggleButtonNewFollower.isChecked())
        { 
        	NotificationSettingFragment.this.newfoll = 1;	
        }
        else newfoll = 0;	
          
          if (!NotificationSettingFragment.this.ToggleButtonNewCommentPro.isChecked()) {
        	   NotificationSettingFragment.this.newComm = 1;
          }
          else newComm = 0;
          if (!NotificationSettingFragment.this.ToggleButtonFollowingAddPro.isChecked()) {
        	  NotificationSettingFragment.this.follPro = 1;
          }
          else follPro = 0;
          if (!NotificationSettingFragment.this.ToggleButtonFollowingAddComm.isChecked()) {
        	  NotificationSettingFragment.this.follComm = 1;
          }else follComm = 0;
          if (!NotificationSettingFragment.this.ToggleButtonFollowingAddPic.isChecked()) {
        	  follPic=1;
          }
          else
        	  follPic=0;  
       

        
          String str = newfoll +""+ newComm +""+ follPro +""+ follComm +""+ follPic;
         Log.w("jkbkg", ""+str);
          MySharePreference.saveNotificationFilter(NotificationSettingFragment.this.context, ""+str);
          new NotificationSettingFragment.SetSettingAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
         
        
      }
    });
    new LoadSettingAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    return localView;
  }
  
  private class LoadSettingAsync
    extends AsyncTask<Void, Void, ArrayList<Notification>>
  {
    private LoadSettingAsync() {}
    
    protected ArrayList<Notification> doInBackground(Void... paramVarArgs)
    {
      return NotificationSettingFragment.this.contentProvider.getNotificationSetting(NotificationSettingFragment.this.apiKey);
    }
    
    protected void onPostExecute(ArrayList<Notification> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      String str1;
      String str2;
      String str3;
      String str4;
      if ((paramArrayList != null) && (paramArrayList.size() > 0))
      {
        Notification localNotification = (Notification)paramArrayList.get(0);
        str1 = "1";
        if (localNotification.newFollowerNum == 1) {
        	NotificationSettingFragment.this.ToggleButtonNewFollower.setChecked(true);
        }
        else
        {NotificationSettingFragment.this.ToggleButtonNewFollower.setChecked(false);
        str1 = "0";}
        str2 = str1 + "1";
        if (localNotification.newCommentOnMyProductNum == 1) {
            NotificationSettingFragment.this.ToggleButtonNewCommentPro.setChecked(true);
        } else
        {NotificationSettingFragment.this.ToggleButtonNewCommentPro.setChecked(false);
        str2 = str1 + "0";}

        str3 = str2 + "1";
        if (localNotification.followingAddProductNum == 1) {
        	   NotificationSettingFragment.this.ToggleButtonFollowingAddPro.setChecked(true);
        } else
        {  NotificationSettingFragment.this.ToggleButtonFollowingAddPro.setChecked(false);
        str3 = str2 + "0";}
     
        str4 = str3 + "1";
        if (localNotification.followingAddCommentNUm == 1) {
        	 NotificationSettingFragment.this.ToggleButtonFollowingAddComm.setChecked(true);
        } else
        {   NotificationSettingFragment.this.ToggleButtonFollowingAddComm.setChecked(false);
        str4 = str3 + "0";}
       
       
        String str5 = str4 + "1";
        if (localNotification.followingAddPictureNum == 1) {
        	NotificationSettingFragment.this.ToggleButtonFollowingAddPic.setChecked(true);
        } else
        {        NotificationSettingFragment.this.ToggleButtonFollowingAddPic.setChecked(false);
        str5 = str4 + "0";
        }
        MySharePreference.saveNotificationFilter(NotificationSettingFragment.this.context, str5);
        
      }
NotificationSettingFragment.this.progressBar.setVisibility(8);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      NotificationSettingFragment.this.progressBar.setVisibility(0);
    }
  }
  
  private class SetSettingAsync
    extends AsyncTask<Void, Void, Boolean>
  {
    private SetSettingAsync() {}
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      return Boolean.valueOf(NotificationSettingFragment.this.contentProvider.setNotificationSetting(NotificationSettingFragment.this.newfoll, NotificationSettingFragment.this.newComm, NotificationSettingFragment.this.follPro, NotificationSettingFragment.this.follComm, NotificationSettingFragment.this.follPic, NotificationSettingFragment.this.apiKey));
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      NotificationSettingFragment.this.progressBar.setVisibility(8);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      NotificationSettingFragment.this.progressBar.setVisibility(0);
    }
  }
}
