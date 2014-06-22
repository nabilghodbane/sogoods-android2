package com.hitechno.sogoods;

import java.io.IOException;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.fragments.LeftMenuFragment;
import com.hitechno.sogoods.fragments.MembersFragment;
import com.hitechno.sogoods.fragments.MenuRightFragment;
import com.hitechno.sogoods.fragments.ProductsFragment;
import com.hitechno.sogoods.fragments.StoreLocatorFragment;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;
import com.hitechno.sogoods.views.FuturaTextView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

@SuppressLint("NewApi")
public class HomeActivity extends SlidingFragmentActivity implements
		SurfaceHolder.Callback, MediaPlayerControl, OnPreparedListener {

	private Context context;

	private ImageView buttonMenuLeft;
	private FuturaTextView buttonMenuRight;
	private FuturaTextView headerTitle;
	private Button closeFillterButton;

	private ViewPager viewPager;
	protected PageIndicator mIndicator;
	private CirclePageIndicator indicator;

	private Handler handler;
	int timer = 5;

	private Configuration applicationInstance;

	private String filterProduct;

	private AssetFileDescriptor descriptor;
	private SurfaceView surfaceView;
	private MediaPlayer mediaPlayer;
	private MediaController mcontroller;
	private RelativeLayout layoutAds, layoutContent;

	private String apiKey;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		context = this;
		applicationInstance = Configuration.getInstance(context);

		setContentView(R.layout.activity_home);

		//mise en place du slidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setFadeDegree(0.35f);
		sm.setMode(SlidingMenu.LEFT_RIGHT);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		setBehindContentView(R.layout.layout_menu_frame_left);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame_left, new LeftMenuFragment()).commit();

		buttonMenuLeft = (ImageView) findViewById(R.id.home_header_menu_button);
		buttonMenuRight = (FuturaTextView) findViewById(R.id.home_header_right_button);
		headerTitle = (FuturaTextView) findViewById(R.id.home_header_title);
		closeFillterButton = (Button) findViewById(R.id.home_header_close_button);
		viewPager = (ViewPager) findViewById(R.id.pager);
		indicator = (CirclePageIndicator) findViewById(R.id.indicator);
		layoutAds = (RelativeLayout) findViewById(R.id.layout_ads);
		layoutContent = (RelativeLayout) findViewById(R.id.home_layout_content);

		buttonMenuRight.setVisibility(View.GONE);

		handler = new Handler();
		apiKey = MySharePreference.getApiKey(context);

		sm.setSecondaryMenu(R.layout.layout_menu_frame_right);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame_right, new MenuRightFragment())
				.commit();
// add frags in arrayList
		ArrayList<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(new StoreLocatorFragment());
		fragments.add(ProductsFragment.getInstance());
		fragments.add(MembersFragment.getInstance());
//creat fragments page adapter
		MyPageAdapter mSectionsPagerAdapter = new MyPageAdapter(
				getSupportFragmentManager(), fragments);
		viewPager.setAdapter(mSectionsPagerAdapter);
		mIndicator = indicator;
		indicator.setViewPager(viewPager);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				indicator.setCurrentItem(arg0);
				switch (arg0) {
				case 0:
					buttonMenuRight.setVisibility(View.GONE);
					headerTitle.setText(getString(R.string.storeLocator));
					break;
				case 1:
					buttonMenuRight.setVisibility(View.VISIBLE);
					if (MySharePreference.getBrandFilter(context) == 0
							&& MySharePreference.getProductFilter(context) == 0)
						headerTitle.setText(getString(R.string.products));
					else
						headerTitle.setText(filterProduct);
					break;
				case 2:
					buttonMenuRight.setVisibility(View.GONE);
					headerTitle.setText(getString(R.string.newMembers));
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		buttonMenuLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				toggle();
			}
		});

		buttonMenuRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showSecondaryMenu();
				// MenuRightFragment fragment = (MenuRightFragment)
				// getSupportFragmentManager()
				// .findFragmentById(R.id.menu_frame_right);
				// fragment.loadCategoryFragment();
			}
		});

		closeFillterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MySharePreference.saveProductFilter(context, 0);
				MySharePreference.saveBrandFilter(context, 0);
				closeFillterButton.setVisibility(View.GONE);
				headerTitle.setText(getString(R.string.products));
				ProductsFragment.getInstance().loadData();
			}
		});

		// if (MyDiaryActivity.CLICK_BACK == 1) {
		// // TODO nothing
		// } else {
		showAdvertisementVideoPopup();
		// }

		new LoadUserProfileAsync().executeOnExecutor(
				AsyncTask.THREAD_POOL_EXECUTOR, applicationInstance.idprofile);
	}

	class MyPageAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}

	@Override
	public void onBackPressed() {
		// if (StoreLocatorFragment.CLICK_SEARCH_CAT == 1) {
		// FragmentManager manager = getSupportFragmentManager();
		// final CategoriesSearchFragment searchFragment =
		// (CategoriesSearchFragment) getSupportFragmentManager()
		// .findFragmentByTag("TAG");
		// FragmentTransaction transaction = manager.beginTransaction();
		// transaction.hide(searchFragment);
		// transaction.commit();
		// StoreLocatorFragment.CLICK_SEARCH_CAT = 2;
		// } else {
		// super.onBackPressed();
		// }
	}

	public void onProductFilterClicked(View view) {
		RadioGroup radioGroup = (RadioGroup) view.getParent();
		radioGroup.check(view.getId());
	}

	public void onNewsFilterClicked(View view) {
		RadioGroup radioGroup = (RadioGroup) view.getParent();
		radioGroup.check(view.getId());
	}

	public void onProfileFilterClicked(View view) {
		RadioGroup radioGroup = (RadioGroup) view.getParent();
		radioGroup.check(view.getId());
	}

	public void onPrductFilterClicked(View view) {
		RadioGroup radioGroup = (RadioGroup) view.getParent();
		radioGroup.check(view.getId());
	}

	public void onProductLikeChoiceClicked(View view) {
		RadioGroup radioGroup = (RadioGroup) view.getParent();
		radioGroup.check(view.getId());
	}

	public void onProfileProductLikeChoiceClicked(View view) {
		RadioGroup radioGroup = (RadioGroup) view.getParent();
		radioGroup.check(view.getId());
	}

	private class LoadUserProfileAsync extends AsyncTask<Integer, Void, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			try {
				APIProfile profileContentProvider = new APIProfile();
				profileContentProvider.loadContentProfileUser(context,
						params[0], apiKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	public void switchMenu(int currentItem) {
		viewPager.setCurrentItem(currentItem);
	}

	public void closeRightMenu() {
		toggle();
	}

	public void setFilterStatus(boolean statue, String s) {
		if (statue) {
			closeFillterButton.setVisibility(View.VISIBLE);
			filterProduct = s;
			headerTitle.setText(filterProduct);
		} else {
			closeFillterButton.setVisibility(View.GONE);
		}
	}

	private void showAdvertisementVideoPopup() {
		layoutAds.setVisibility(View.VISIBLE);
		layoutContent.setVisibility(View.GONE);

		ImageView imgCut = (ImageView) findViewById(R.id.imgCut);
		surfaceView = (SurfaceView) findViewById(R.id.surfaceView_video);
		mediaPlayer = new MediaPlayer();
		mcontroller = new MediaController(this);
		SurfaceHolder holder = surfaceView.getHolder();
		holder.addCallback(this);

		surfaceView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mcontroller.show();
				return false;
			}
		});

		mediaPlayer.setOnPreparedListener(this);
		try {
			descriptor = getAssets().openFd("th.mp4");
			mediaPlayer
					.setDataSource(descriptor.getFileDescriptor(),
							descriptor.getStartOffset(),
							descriptor.getDeclaredLength());
			descriptor.close();

			mediaPlayer.prepare();
			mediaPlayer.start();

			// int videoHeight = mediaPlayer.getVideoHeight();
			// int videoWidth = mediaPlayer.getVideoWidth();
			// float scale;
			// if (Measuredwidth / videoWidth > Measuredheight / videoHeight)
			// scale = Measuredheight / videoHeight;
			// else
			// scale = Measuredwidth / videoWidth;
			//
			// holder.setFixedSize((int) (videoWidth * scale),
			// (int) (videoHeight * scale));
		} catch (IOException e) {
			e.printStackTrace();
		}

		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mcontroller.hide();
				layoutAds.setVisibility(View.GONE);
				layoutContent.setVisibility(View.VISIBLE);
				mediaPlayer.release();
				mediaPlayer = null;
			}
		});

		imgCut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mediaPlayer.stop();
				mcontroller.hide();
				layoutAds.setVisibility(View.GONE);
				layoutContent.setVisibility(View.VISIBLE);
				mediaPlayer.release();
				mediaPlayer = null;
			}
		});
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (mediaPlayer != null)
			mediaPlayer.setDisplay(holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return true;
	}

	@Override
	public boolean canSeekForward() {
		return true;
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		return mediaPlayer.getCurrentPosition();
	}

	@Override
	public int getDuration() {
		return mediaPlayer.getDuration();
	}

	@Override
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	@Override
	public void pause() {
		mediaPlayer.pause();
	}

	@Override
	public void seekTo(int pos) {
		mediaPlayer.seekTo(pos);
	}

	@Override
	public void start() {
		mediaPlayer.start();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mcontroller.setMediaPlayer(this);
		mcontroller
				.setAnchorView(findViewById(R.id.mediaplayer_surfaceview_container));
		handler.post(new Runnable() {

			public void run() {
				mcontroller.setEnabled(true);
				mcontroller.show();
			}
		});
	}

}
