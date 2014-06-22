package com.hitechno.sogoods;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.mttam.toollibrary.tools.Tools;
import com.hitechno.sogoods.api.APIMember;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.fragments.DiaryBrandsFragment;
import com.hitechno.sogoods.fragments.DiaryFollowingsFragment;
import com.hitechno.sogoods.fragments.DiaryFollowsFragment;
import com.hitechno.sogoods.fragments.DiarySOFragment;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;
import com.hitechno.sogoods.util.SGTools;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import org.json.JSONObject;

@SuppressLint("NewApi")
public class MyDiaryActivity
  extends FragmentActivity
{
  public static int CLICK_BACK;
  private int CLICK_TAB;
  TextView addressLabel;
  private String apiKey;
  private RelativeLayout avartarLayout;
  Button bttMenu;
  private TextView bttSo2;
  TextView buttonBrands;
  TextView buttonFollow;
  TextView buttonFollowers;
  TextView buttonFollowing;
  TextView buttonSo;
  private Configuration config;
  private Context context;
  private int currrentPadding;
  private ImageView editStatusButton;
  int heightImage;
  private ImageView iconSo;
  ImageView imgAvatarLarg;
  ImageView imgBack;
  private int increase;
  private Profile mProfile;
  private float mScrollStartY;
  TextView nameLabel;
  Point p;
  private LinearLayout photosLayout;
  private TextView photosNumberLabel;
  APIMember profileContentProvider;
  ProgressBar progressBar;
  RelativeLayout relFollow;
  RelativeLayout relMyaccount;
  private EditText statusLabel;
  TextView txtViewSwitchFollow;
  Handler uiHandler = new Handler();
  private int width;
  int widthImage;
  
  private void clearColorText()
  {
    this.buttonFollowers.setTextColor(getResources().getColor(2131099784));
    this.buttonFollowing.setTextColor(getResources().getColor(2131099784));
    this.buttonBrands.setTextColor(getResources().getColor(2131099784));
    this.buttonSo.setTextColor(getResources().getColor(2131099784));
    this.bttSo2.setTextColor(getResources().getColor(2131099784));
    this.iconSo.setBackgroundResource(2130837676);
  }
  
  private void collapse(final View paramView)
  {
    final int i = paramView.getMeasuredHeight();
    Animation local12 = new Animation()
    {
      protected void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        if (paramAnonymousFloat == 1.0F)
        {
          paramView.getLayoutParams().height = (MyDiaryActivity.this.width / 3);
          paramView.requestLayout();
          MyDiaryActivity.this.increase = 0;
          MyDiaryActivity.this.currrentPadding = (MyDiaryActivity.this.width / 3);
          return;
        }
        paramView.getLayoutParams().height = (i - (int)(paramAnonymousFloat * (i - MyDiaryActivity.this.width / 3)));
        paramView.requestLayout();
      }
      
      public boolean willChangeBounds()
      {
        return true;
      }
    };
    local12.setDuration((int)(i / paramView.getContext().getResources().getDisplayMetrics().density));
    paramView.startAnimation(local12);
  }
  
  private void setColapseLayout(final RelativeLayout paramRelativeLayout)
  {
    paramRelativeLayout.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
//TODO:
//        switch (paramAnonymousMotionEvent.getAction())
//        {
//        }
//        for (;;)
//        {
          return true;
//          MyDiaryActivity.this.mScrollStartY = paramAnonymousMotionEvent.getY();
//          continue;
//          float f1 = paramAnonymousMotionEvent.getY();
//          float f2 = f1 - MyDiaryActivity.this.mScrollStartY;
//          if (f2 > 0.0F)
//          {
//            MyDiaryActivity.this.mScrollStartY = f1;
//            MyDiaryActivity.this.setHeaderPadding(paramRelativeLayout, (int)f2);
//            continue;
//            MyDiaryActivity.this.collapse(paramRelativeLayout);
//          }
//        }
      }
    });
  }
  
  private void setDefaultTab(FragmentTransaction paramFragmentTransaction)
  {
    clearColorText();
    this.buttonSo.setTextColor(getResources().getColor(2131099796));
    this.bttSo2.setTextColor(getResources().getColor(2131099796));
    this.iconSo.setBackgroundResource(2130837677);
    paramFragmentTransaction.replace(2131230895, new DiarySOFragment());
    paramFragmentTransaction.commit();
  }
  
  private void setHeaderPadding(View paramView, int paramInt)
  {
    this.increase = (paramInt + this.increase);
    if (this.increase > 2 * this.width / 3) {
      this.increase = (2 * this.width / 3);
    }
    this.currrentPadding = (this.width / 3 + this.increase);
    if (this.currrentPadding > this.width) {
      this.currrentPadding = this.width;
    }
    paramView.setLayoutParams(new LinearLayout.LayoutParams(this.width, this.currrentPadding));
  }
  
  private void showPopup(Activity paramActivity, Point paramPoint)
  {
    LinearLayout localLinearLayout = (LinearLayout)paramActivity.findViewById(2131231186);
    View localView = ((LayoutInflater)paramActivity.getSystemService("layout_inflater")).inflate(2130903152, localLinearLayout);
    this.relMyaccount = ((RelativeLayout)localView.findViewById(2131231187));
    final PopupWindow localPopupWindow = new PopupWindow(paramActivity);
    localPopupWindow.setContentView(localView);
    localPopupWindow.setWidth(330);
    localPopupWindow.setHeight(70);
    localPopupWindow.setFocusable(true);
    localPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    localPopupWindow.showAtLocation(localView, 0, 30 + paramPoint.x, 50 + paramPoint.y);
    this.relMyaccount.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localPopupWindow.dismiss();
      }
    });
  }
  
  private void updateAllNumberWithProfileData()
  {
    int i = this.mProfile.numberOfFollows;
    int j = this.mProfile.numberOfFollowings;
    int k = this.mProfile.numberOfBrands;
//TODO:
//    if (i > 1)
//    {
//      this.buttonFollowers.setText(i + "\r\n" + getString(2131165751));
//      if (j <= 1) {
//        break label218;
//      }
//      this.buttonFollowing.setText(j + "\r\n" + getString(2131165753));
//      label108:
//      if (k <= 1) {
//        break label258;
//      }
//      this.buttonBrands.setText(k + "\r\n" + getString(2131165755));
//    }
//    for (;;)
//    {
//      this.buttonSo.setText(this.mProfile.numberOfProducts);
//      return;
//      this.buttonFollowers.setText(i + "\r\n" + getString(2131165750));
//      break;
//      label218:
//      this.buttonFollowing.setText(j + "\r\n" + getString(2131165752));
//      break label108;
//      label258:
//      this.buttonBrands.setText(k + "\r\n" + getString(2131165754));
//    }
  }
  
  public Profile getProFile()
  {
    return this.mProfile;
  }
  
  public void init()
  {
    this.mProfile = MySharePreference.getProfile(this.context, this.config.typePhone);
    if (this.mProfile != null)
    {
      if (this.mProfile.displayRealName != 1) {
//        break label253;
      }
      this.nameLabel.setText(this.mProfile.firstName + " " + this.mProfile.lastName);
    }
    for (;;)
    {
      if (!Tools.isEmpty(this.mProfile.description)) {
        this.statusLabel.setText(this.mProfile.description);
      }
      this.photosNumberLabel.setText(this.mProfile.numberOfPhotos + " " + getString(2131165399));
      this.addressLabel.setText(this.mProfile.getCityName() + "\n" + this.mProfile.country_name);
      if (SGTools.isImageEmpty(this.context, this.mProfile.urlHome)) {
//        break label310;
      }
      WeakReference localWeakReference = new WeakReference(SGTools.getImageFromStorage(this.context, this.mProfile.urlHome));
      if (localWeakReference.get() == null) {
        break;
      }
      this.imgAvatarLarg.setImageBitmap((Bitmap)localWeakReference.get());
//      return;
//      label253:
      this.nameLabel.setText(this.mProfile.username);
    }
    DownloadImageAsync localDownloadImageAsync1 = new DownloadImageAsync();
    Executor localExecutor1 = AsyncTask.THREAD_POOL_EXECUTOR;
    String[] arrayOfString1 = new String[1];
    arrayOfString1[0] = this.mProfile.urlHome;
    localDownloadImageAsync1.executeOnExecutor(localExecutor1, arrayOfString1);
//    return;
//    label310:
    DownloadImageAsync localDownloadImageAsync2 = new DownloadImageAsync();
    Executor localExecutor2 = AsyncTask.THREAD_POOL_EXECUTOR;
    String[] arrayOfString2 = new String[1];
    arrayOfString2[0] = this.mProfile.urlHome;
    localDownloadImageAsync2.executeOnExecutor(localExecutor2, arrayOfString2);
  }
  
  public void onBackPressed()
  {
    CLICK_BACK = 1;
    Intent localIntent = new Intent(this, HomeActivity.class);
    localIntent.addFlags(131072);
    startActivity(localIntent);
    ConstantsHelper.animationPro(this);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903048);
    this.context = this;
    this.photosLayout = ((LinearLayout)findViewById(2131230882));
    this.photosNumberLabel = ((TextView)findViewById(2131230883));
    this.statusLabel = ((EditText)findViewById(2131230886));
    this.editStatusButton = ((ImageView)findViewById(2131230887));
    this.avartarLayout = ((RelativeLayout)findViewById(2131230878));
    this.imgBack = ((ImageView)findViewById(2131230758));
    this.imgAvatarLarg = ((ImageView)findViewById(2131230879));
    this.nameLabel = ((TextView)findViewById(2131230885));
    this.addressLabel = ((TextView)findViewById(2131230881));
    this.bttMenu = ((Button)findViewById(2131230876));
    this.progressBar = ((ProgressBar)findViewById(2131230880));
    this.buttonFollow = ((TextView)findViewById(2131230884));
    this.buttonFollowers = ((TextView)findViewById(2131230891));
    this.buttonFollowing = ((TextView)findViewById(2131230892));
    this.buttonBrands = ((TextView)findViewById(2131230893));
    this.buttonSo = ((TextView)findViewById(2131230888));
    this.bttSo2 = ((TextView)findViewById(2131230889));
    this.iconSo = ((ImageView)findViewById(2131230890));
    this.profileContentProvider = new APIMember();
    this.config = Configuration.getInstance(this.context);
    this.apiKey = MySharePreference.getApiKey(this.context);
    this.width = this.config.widthPhone;
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.width, this.width / 3);
    this.avartarLayout.setLayoutParams(localLayoutParams);
    setColapseLayout(this.avartarLayout);
    init();
    this.buttonFollow.setVisibility(8);
    clearColorText();
    updateAllNumberWithProfileData();
    final FragmentManager localFragmentManager = getSupportFragmentManager();
    setDefaultTab(localFragmentManager.beginTransaction());
    this.imgBack.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyDiaryActivity.this.onBackPressed();
      }
    });
    this.editStatusButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyDiaryActivity.this.statusLabel.requestFocus();
        ((InputMethodManager)MyDiaryActivity.this.getSystemService("input_method")).showSoftInput(MyDiaryActivity.this.statusLabel, 1);
      }
    });
    this.statusLabel.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        boolean bool = false;
        if (paramAnonymousInt == 6)
        {
          Tools.hideKeyboard(MyDiaryActivity.this, MyDiaryActivity.this.statusLabel);
          new MyDiaryActivity.UpdateProfileAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
          bool = true;
        }
        return bool;
      }
    });
    this.photosLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
//        Intent localIntent = new Intent(MyDiaryActivity.this.context, GalleryActivity.class);
//        MyDiaryActivity.this.startActivity(localIntent);
//        ConstantsHelper.animationPro(MyDiaryActivity.this);
      }
    });
    this.buttonSo.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyDiaryActivity.this.CLICK_TAB != 0)
        {
          MyDiaryActivity.this.CLICK_TAB = 0;
          FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
          MyDiaryActivity.this.setDefaultTab(localFragmentTransaction);
        }
      }
    });
    this.bttSo2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyDiaryActivity.this.CLICK_TAB != 0)
        {
          MyDiaryActivity.this.CLICK_TAB = 0;
          FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
          MyDiaryActivity.this.setDefaultTab(localFragmentTransaction);
        }
      }
    });
    this.buttonFollowers.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyDiaryActivity.this.CLICK_TAB != 1)
        {
          MyDiaryActivity.this.CLICK_TAB = 1;
          MyDiaryActivity.this.clearColorText();
          MyDiaryActivity.this.buttonFollowers.setTextColor(MyDiaryActivity.this.getResources().getColor(2131099796));
          FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
          localFragmentTransaction.replace(2131230895, new DiaryFollowsFragment());
          localFragmentTransaction.commit();
        }
      }
    });
    this.buttonFollowing.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyDiaryActivity.this.CLICK_TAB != 2)
        {
          MyDiaryActivity.this.CLICK_TAB = 2;
          MyDiaryActivity.this.clearColorText();
          MyDiaryActivity.this.buttonFollowing.setTextColor(MyDiaryActivity.this.getResources().getColor(2131099796));
          FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
          localFragmentTransaction.replace(2131230895, new DiaryFollowingsFragment());
          localFragmentTransaction.commit();
        }
      }
    });
    this.buttonBrands.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyDiaryActivity.this.CLICK_TAB != 3)
        {
          MyDiaryActivity.this.CLICK_TAB = 3;
          MyDiaryActivity.this.clearColorText();
          MyDiaryActivity.this.buttonBrands.setTextColor(MyDiaryActivity.this.getResources().getColor(2131099796));
          FragmentTransaction localFragmentTransaction = localFragmentManager.beginTransaction();
          localFragmentTransaction.replace(2131230895, new DiaryBrandsFragment());
          localFragmentTransaction.commit();
        }
      }
    });
  }
  
  protected void onResume()
  {
    super.onResume();
    this.mProfile = MySharePreference.getProfile(this.context, this.config.typePhone);
    this.photosNumberLabel.setText(this.mProfile.numberOfPhotos + " " + getString(2131165399));
    if (!Tools.isEmpty(this.mProfile.description)) {
      this.statusLabel.setText(this.mProfile.description);
    }
  }
  
  public void processClickMenuPopup(View paramView)
  {
    if (this.p != null)
    {
      if (this.mProfile.profileID == MySharePreference.getProfileID(this.context)) {
        showPopup(this, this.p);
      }
    }
    else {
      return;
    }
    showPopup2(this, this.p);
  }
  
  void showPopup2(Activity paramActivity, Point paramPoint)
  {
    LinearLayout localLinearLayout = (LinearLayout)paramActivity.findViewById(2131231186);
    View localView = ((LayoutInflater)paramActivity.getSystemService("layout_inflater")).inflate(2130903153, localLinearLayout);
    this.relFollow = ((RelativeLayout)localView.findViewById(2131231189));
    this.txtViewSwitchFollow = ((TextView)localView.findViewById(2131231190));
    PopupWindow localPopupWindow = new PopupWindow(paramActivity);
    localPopupWindow.setContentView(localView);
    localPopupWindow.setWidth(330);
    localPopupWindow.setHeight(165);
    localPopupWindow.setFocusable(true);
    localPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    localPopupWindow.showAtLocation(localView, 0, 30 + paramPoint.x, 50 + paramPoint.y);
  }
  
  private class DownloadImageAsync
    extends AsyncTask<String, Void, Boolean>
  {
    private String url;
    
    private DownloadImageAsync() {}
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      this.url = paramVarArgs[0];
      return Boolean.valueOf(SGTools.DownLoadImage(MyDiaryActivity.this.context, this.url));
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      if (paramBoolean.booleanValue()) {
        MyDiaryActivity.this.imgAvatarLarg.setImageBitmap(SGTools.getImageFromStorage(MyDiaryActivity.this.context, this.url));
      }
      for (;;)
      {
        MyDiaryActivity.this.progressBar.setVisibility(8);
//        return;
        Debugs.show("w", "LoadAvartarImage", "Download Avatar for user have problem");
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      MyDiaryActivity.this.progressBar.setVisibility(0);
    }
  }
  
  private class LoadUserProfileAsync
    extends AsyncTask<Integer, Void, String>
  {
    private LoadUserProfileAsync() {}
    
    protected String doInBackground(Integer... paramVarArgs)
    {
      try
      {
        String str = new APIProfile().loadContentProfileUser(MyDiaryActivity.this.context, paramVarArgs[0].intValue(), MyDiaryActivity.this.apiKey);
        return str;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      if (paramString != null) {}
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramString);
        if (localJSONObject1.getBoolean("success"))
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("profil");
          MySharePreference.saveProfileContent(MyDiaryActivity.this.context, localJSONObject2.toString());
        }
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private class UpdateProfileAsync
    extends AsyncTask<Void, Void, Boolean>
  {
    private UpdateProfileAsync() {}
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      return Boolean.valueOf(new APIProfile().updateStatus(MyDiaryActivity.this.statusLabel.getText().toString(), MyDiaryActivity.this.mProfile.profileID, MyDiaryActivity.this.apiKey));
    }
    
    @SuppressLint("NewApi")
	protected void onPostExecute(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      if (paramBoolean.booleanValue())
      {
        MyDiaryActivity.LoadUserProfileAsync localLoadUserProfileAsync = new MyDiaryActivity.LoadUserProfileAsync();
        Executor localExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        Integer[] arrayOfInteger = new Integer[1];
        arrayOfInteger[0] = Integer.valueOf(MyDiaryActivity.this.mProfile.profileID);
        localLoadUserProfileAsync.executeOnExecutor(localExecutor, arrayOfInteger);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}

