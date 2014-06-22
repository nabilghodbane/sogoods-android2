package com.hitechno.sogoods.adapters;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Provides a view for each <code>Profile</code> in the application.
 * 
 */
public class MemberAdapter extends ArrayAdapter<Profile> {

	private Context context;
	private int resource;
	private ArrayList<Profile> profiles;

	private ImageLoader imageLoader;
	private DisplayImageOptions options;

	ArrayList<Profile> lstpro; // detail
	TextView txtusername, likesView, followingLabel, followerLabel, txtPercent;
	APIProfile profileContentProvider;
	private Configuration config;
	private String apiKey;

	public static int CLICK_TAB_PROFILE;

	/**
	 * Default constructor for a subclass of BaseAdapter. This constructor also
	 * creates a reference to a singleton <code>imageCache</code>.
	 * 
	 * @param context
	 *            the context
	 * @param resource
	 *            the reference to the resource
	 * @param items
	 *            the items to wrap in a view
	 */

	public MemberAdapter(Context context, int textViewResourceId,
			ArrayList<Profile> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.resource = textViewResourceId;
		this.profiles = objects;

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading().cacheOnDisc()
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(0)).cacheInMemory()
				.delayBeforeLoading(0).build();

		config = Configuration.getInstance(context);
		apiKey = MySharePreference.getApiKey(context);
		CLICK_TAB_PROFILE = 0;
	}

	/**
	 * Updates the adapters datasource with new profiles from the
	 * <code>LoaderManager</code>.
	 * 
	 * @param profiles
	 *            the profiles that the view will display
	 */

	public ArrayList<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(ArrayList<Profile> a) {
		// this.profiles = a;
	}

	/**
	 * Creates or finds a view.
	 * <p>
	 * The view includes an image, which is provided to the view from the
	 * <code>imageCache</code>.
	 * 
	 * @param position
	 *            the position in the <code>List<T></code>
	 * @param convertView
	 *            the view to be presented
	 * @param parent
	 *            the parent of the view to be presented
	 * 
	 * @return the view to be presented
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = li.inflate(resource, parent, false);
		}

		txtusername = (TextView) convertView.findViewById(R.id.usname);
		txtPercent = (TextView) convertView.findViewById(R.id.txtPercent);
		likesView = (TextView) convertView.findViewById(R.id.like_profile);
		followerLabel = (TextView) convertView
				.findViewById(R.id.item_member_follower_label);
		followingLabel = (TextView) convertView
				.findViewById(R.id.item_member_following_label);
		TextView locator = (TextView) convertView
				.findViewById(R.id.item_members_location_label);
		TextView followButton = (TextView) convertView
				.findViewById(R.id.item_members_follow_button);

		if (position % 2 == 0) {
			convertView.setBackgroundColor(Color.WHITE);
		} else {
			convertView.setBackgroundColor(context.getResources().getColor(
					R.color.background1));
		}

		lstpro = new ArrayList<Profile>();
		profileContentProvider = new APIProfile();
		final Profile profile = getItem(position);

		if (profile.followed == 0) {
			followButton.setText(context.getResources().getString(
					R.string.follow));
			followButton.setBackgroundResource(R.drawable.button_follow);
		} else {
			followButton.setText(context.getResources().getString(
					R.string.unfollow));
			followButton.setBackgroundResource(R.drawable.button_unfollow);
		}

		if (Integer.parseInt(profile.followIdsString) > 1) {
			followerLabel.setText(profile.followIdsString + " "
					+ context.getResources().getString(R.string.follower_s));
		} else {
			followerLabel.setText(profile.followIdsString + " "
					+ context.getResources().getString(R.string.follower));
		}

		if (Integer.parseInt(profile.followingIdsString) > 1) {
			followingLabel.setText(profile.followingIdsString + " "
					+ context.getResources().getString(R.string.following_s));
		} else {
			followingLabel.setText(profile.followingIdsString + " "
					+ context.getResources().getString(R.string.following));
		}

		final ImageView imageView = (ImageView) convertView
				.findViewById(R.id.profile_image);
		String imagePath = profile.urlHome;

		locator.setText(profile.cityName);
		txtusername.setText(profile.username);
		txtPercent.setText(profile.percent + "% "
				+ context.getResources().getString(R.string.affinity));
		likesView.setText(profile.nblike + " " + "SO");

		// String flag = "";
		// if (profile.cityId == " " || profile.cityId.equals(" ")) {
		// flag = "111";
		// } else {
		// flag = profile.cityId;
		// }
		// ImageView imgFlags = (ImageView) convertView
		// .findViewById(R.id.imageFlags);
		// try {
		// InputStream input = context.getAssets().open(
		// "flag/" + flag + ".png");
		// Drawable drawable = Drawable.createFromStream(input, null);
		//
		// imgFlags.setImageDrawable(drawable);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// fragment.mImageFetcher.loadImage(imagePath, imageView);


		imagePath=profile.urlHome;
		
		imageLoader.displayImage(imagePath, imageView, options,
				new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						imageView
								.setImageResource(R.drawable.background_nopicture);

					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						imageView
								.setImageResource(R.drawable.background_nopicture);

					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {

					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						imageView
								.setImageResource(R.drawable.background_nopicture);
					}
				});
		// new DownloadImageTask(imageView).execute(imagePath);

		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Thread threadprofile = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							CLICK_TAB_PROFILE = 1;
							Message mes = handler.obtainMessage(1,
									profileContentProvider.loadProfileUser(
											profile.profileID, apiKey,
											config.typePhone));
							handler.sendMessage(mes);
						} catch (Exception e) {
						}
					}
				});
				threadprofile.start();
				imageView.setClickable(false);
			}
		});

		followerLabel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Thread threadFollowers = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							CLICK_TAB_PROFILE = 1;
							Message mes = handler.obtainMessage(1,
									profileContentProvider.loadProfileUser(
											profile.profileID, apiKey,
											config.typePhone));
							handler.sendMessage(mes);
						} catch (Exception e) {
						}
					}
				});
				threadFollowers.start();
				followerLabel.setClickable(false);
			}
		});

		followingLabel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Thread threadFollowings = new Thread(new Runnable() {

					@Override
					public void run() {
						CLICK_TAB_PROFILE = 2;
						Message message = handler.obtainMessage(1,
								profileContentProvider.loadProfileUser(
										profile.profileID, apiKey,
										config.typePhone));
						handler.sendMessage(message);
					}
				});
				threadFollowings.start();
				followingLabel.setClickable(false);
			}
		});

		likesView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Thread threadFollowings = new Thread(new Runnable() {

					@Override
					public void run() {
						CLICK_TAB_PROFILE = 3;
						Message message = handler.obtainMessage(1,
								profileContentProvider.loadProfileUser(
										profile.profileID, apiKey,
										config.typePhone));
						handler.sendMessage(message);
					}
				});
				threadFollowings.start();
				likesView.setClickable(false);
			}
		});

		return convertView;
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 1:
				// List<Profile> lstpros = (ArrayList<Profile>) msg.obj;
				// for (Profile pros : lstpros) {
				// Intent intent = new Intent(context,
				// MyDiaryActivity.class);
				// intent.putExtra("profile", pros);
				// context.startActivity(intent);
				// ConstantsHelper.animationFragment(context);
				// }
			default:
				break;
			}
		}
	};

}
