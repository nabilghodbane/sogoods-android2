package com.hitechno.sogoods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.hitechno.sogoods.adapters.NotificationsAdapter;
import com.hitechno.sogoods.api.APINotification;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.fragments.LeftMenuFragment;
import com.hitechno.sogoods.fragments.MenuRightFragment;
import com.hitechno.sogoods.fragments.NotificationSettingFragment;
import com.hitechno.sogoods.models.Notification;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;

import java.util.ArrayList;
import java.util.Date;

@SuppressLint("InlinedApi")
public class NotificationsActivity
  extends SlidingFragmentActivity
{
  private String apiKey;
  private ImageView buttonBack;
  private Configuration config;
  private Context context;
  private ListView lstViewNewNotifi;
  private TextView noResult;
  private APINotification notificationContentProvider;
  private ProgressBar progressbar;
  private TextView rightButton;
  
  public void closeRightMenu()
  {
    toggle();
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    finish();
    ConstantsHelper.animationPro(this);
  }
  
  @SuppressLint("NewApi")
public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(R.layout.activity_notifications);
    this.context = this;
    this.notificationContentProvider = new APINotification();
    this.apiKey = MySharePreference.getApiKey(this.context);
    this.config = Configuration.getInstance(this.context);
    
    SlidingMenu sm = getSlidingMenu();
	sm.setFadeDegree(0.35f);
	sm.setMode(SlidingMenu.LEFT_RIGHT);
	sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	sm.setShadowWidthRes(R.dimen.shadow_width);
	sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);

	setBehindContentView(R.layout.layout_menu_frame_left);
	
	sm.setSecondaryMenu(R.layout.layout_menu_frame_right);
	getSupportFragmentManager().beginTransaction()
			.replace(R.id.menu_frame_right, new NotificationSettingFragment())
			.commit();
	this.buttonBack = ((ImageView)findViewById(R.id.notifications_header_left_button));
    this.rightButton = ((TextView)findViewById(R.id.notifications_header_right_button));
    this.lstViewNewNotifi = ((ListView)findViewById(R.id.notification_listview));
    this.progressbar = ((ProgressBar)findViewById(R.id.notification_progressbar));
    this.noResult = ((TextView)findViewById(R.id.notifications_noresult));
    this.buttonBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NotificationsActivity.this.onBackPressed();
      }
    });
    this.rightButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NotificationsActivity.this.showSecondaryMenu();
      }
    });
   new LoadNotificationFromServer().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  private class LoadNotificationFromServer
    extends AsyncTask<Void, Void, ArrayList<Notification>>
  {
    private LoadNotificationFromServer() {}
    
    protected ArrayList<Notification> doInBackground(Void... paramVarArgs)
    {
      long l = new Date().getTime() - 604800L;
      return notificationContentProvider.getAllNotification(String.valueOf(l), 100, 0, NotificationsActivity.this.apiKey, NotificationsActivity.this.config.typePhone);
    }
    
    protected void onPostExecute(ArrayList<Notification> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
   
      if ((paramArrayList != null) && (paramArrayList.size() > 0))
      {
        NotificationsActivity.this.noResult.setVisibility(8);
        NotificationsAdapter localNotificationsAdapter = new NotificationsAdapter(NotificationsActivity.this.context, R.layout.item_notifications_detail, paramArrayList);
        NotificationsActivity.this.lstViewNewNotifi.setAdapter(localNotificationsAdapter);
      }
      else
      {
        NotificationsActivity.this.lstViewNewNotifi.setAdapter(null);
        NotificationsActivity.this.noResult.setVisibility(0);
      }
      NotificationsActivity.this.progressbar.setVisibility(8);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      NotificationsActivity.this.progressbar.setVisibility(0);
    }
  }
}
