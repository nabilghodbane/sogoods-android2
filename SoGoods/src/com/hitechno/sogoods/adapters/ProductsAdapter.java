package com.hitechno.sogoods.adapters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.api.Connectivity;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Product;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.MySharePreference;
import com.hitechno.sogoods.util.SGTools;
import com.hitechno.sogoods.views.TouchImageView;
import com.mttam.toollibrary.tools.Tools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ProductsAdapter extends ArrayAdapter<Product> {

	private Context context;
	private int resource;
	private ArrayList<Product> products;

	// ProductsFragment fragment;
	TextView productTitle, brandproduct, addedByLabel, likeLabel,
			commentsLabel;

	TouchImageView mainImageView;
	ImageView imgCut;
	TextView textViewNamePro, txtContentPro;
	TextView textViewAddBy;

	PopupWindow mpopup;
	ProgressBar progressBar;
	Handler uiHandler = new Handler();
	boolean isUpdateRequired = false;

	private String apiKey;

	ArrayList<Profile> lstpro;
	List<Brand> lsBrands;
	// BrandContentProvider provider;
	// ProfileContentProvider profileContentProvider;
	// ProductContentProvider productContentProvider;
	// NewProductFragment new_ProductFragment;

	private ToggleButton iGotButton, likeDreamButton, likeNextButton,
			ilikeButton;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;

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

	public ProductsAdapter(Context context, int textViewResourceId,
			ArrayList<Product> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.resource = textViewResourceId;
		this.products = objects;

		imageLoader = ImageLoader.getInstance();

		// fragment = new ProductsFragment2();
		lstpro = new ArrayList<Profile>();
		lsBrands = new ArrayList<Brand>();
		// provider = new BrandContentProvider();
		// profileContentProvider = new ProfileContentProvider();
		// productContentProvider = new ProductContentProvider();
		// new_ProductFragment = new NewProductFragment();

		options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading().cacheOnDisc()
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(0)).cacheInMemory()
				.delayBeforeLoading(0).build();

		apiKey = MySharePreference.getApiKey(context);

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
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(resource, parent, false);
		}

		productTitle = (TextView) convertView.findViewById(R.id.product_title);
		brandproduct = (TextView) convertView.findViewById(R.id.product_brand);
		addedByLabel = (TextView) convertView
				.findViewById(R.id.product_added_by);
		final ImageView productImageView = (ImageView) convertView
				.findViewById(R.id.product_image);
		LinearLayout likeLayout = (LinearLayout) convertView
				.findViewById(R.id.item_product_like_layout);
		ImageView commentsImage = (ImageView) convertView
				.findViewById(R.id.imageViewCommentPro);
		ImageView likeButton = (ImageView) convertView
				.findViewById(R.id.item_product_like_button);
		likeLabel = (TextView) convertView
				.findViewById(R.id.product_likes_label);
		commentsLabel = (TextView) convertView
				.findViewById(R.id.product_comments);
		TextView timeLabel = (TextView) convertView
				.findViewById(R.id.item_product_time_label);

		if (position % 2 == 0) {
			convertView.setBackgroundColor(Color.WHITE);
		} else {
			convertView.setBackgroundColor(context.getResources().getColor(
					R.color.background1));
		}

		final Product product = getItem(position);

		if (product.idLike == "") {
			product.likeType = "0";
		}

		// set text
		// String underTitle = "<u>" + product.title + " "
		// + context.getResources().getString(R.string.product_from_brand)
		// + " " + product.brandName + "</u>";
		// titleTextView.setText(Html.fromHtml(underTitle));
		if (product.title.length() > 25) {
			productTitle.setTextSize(15);
			brandproduct.setTextSize(15);
		} else if (product.title.length() > 29) {
			productTitle.setTextSize(13);
			brandproduct.setTextSize(13);
		} else if (product.title.length() > 34) {
			productTitle.setTextSize(11);
			brandproduct.setTextSize(11);
		} else if (product.title.length() > 38) {
			productTitle.setTextSize(9);
			brandproduct.setTextSize(9);
		} else {
			productTitle.setTextSize(17);
			brandproduct.setTextSize(17);
		}

		productTitle.setText(product.title);
		brandproduct.setText(product.brandName);
		timeLabel.setText(SGTools.getTimeFromDate(product.updatedAt));

		String underAddby = context.getResources().getString(R.string.added_by)
				+ " <b>" + product.added_by + "</b>";
		addedByLabel.setText(Html.fromHtml(underAddby));

		likeLabel.setText(product.numberOfLikes + " "
				+ context.getResources().getString(R.string.so));

		int comment = product.numberOfComments;
		if (comment > 1) {
			commentsLabel.setText(comment + " "
					+ context.getResources().getString(R.string.comment_b_s));
		} else {
			commentsLabel.setText(comment + " "
					+ context.getResources().getString(R.string.comment_b));
		}

		// title onclick
		productTitle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(context, BrandActivity.class);
				// Debugs.show("d", "ProductsAdapter2",
				// "go to BrandActivity with product id = "
				// + product.productID);
				// intent.putExtra("brand", product.brandId);
				// intent.putExtra("brandname", product.brandName);
				// context.startActivity(intent);
				// ConstantsHelper.animationFragment(context);
			}
		});
		// added by onclick
		addedByLabel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Thread threadprofile = new Thread(new Runnable() {
				// @Override
				// public void run() {
				// try {
				// Message mes = handler.obtainMessage(1,
				// profileContentProvider
				// .loadProfileUser(product.idUser));
				// handler.sendMessage(mes);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// }
				// });
				// threadprofile.start();
			}
		});

		if (Tools.isEmpty(product.idLike)) {
			likeButton.setBackgroundResource(R.drawable.so_active_action);
		} else {
			likeButton.setBackgroundResource(R.drawable.so_non_active_action);
		}

		// mImageFetcher.loadImage(product.urlHome, productImageView);
		imageLoader.displayImage(product.urlPopup, productImageView, options,
				new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						productImageView
								.setImageResource(R.drawable.background_nopicture);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						productImageView
								.setImageResource(R.drawable.background_nopicture);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {

					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						productImageView
								.setImageResource(R.drawable.background_nopicture);
					}
				});

		productImageView.setOnClickListener(new OnClickListener() {

			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View popUpView = inflater.inflate(R.layout.popup_product, null); // inflating
																					// popup
																					// layout
				mpopup = new PopupWindow(popUpView, LayoutParams.MATCH_PARENT,
						LayoutParams.MATCH_PARENT, true); // Creation of popup
				mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
				progressBar = (ProgressBar) popUpView
						.findViewById(R.id.progressBar1);
				// mpopup.showAtLocation(popUpView, Gravity.CENTER, 100, 100);

				// ImageView imageView =
				// (ImageView)popUpView.findViewById(R.id.imagePopupProduct);
				mainImageView = (TouchImageView) popUpView
						.findViewById(R.id.imagePopupProduct);
				imgCut = (ImageView) popUpView.findViewById(R.id.imgCut);
				textViewNamePro = (TextView) popUpView
						.findViewById(R.id.textViewNamePro);
				textViewAddBy = (TextView) popUpView
						.findViewById(R.id.textViewAddBy);
				txtContentPro = (TextView) popUpView
						.findViewById(R.id.txtcontentpro);

				// SimpleDateFormat dateFormat = new
				// SimpleDateFormat("yyyy-MM-dd");
				// Date date;
				// String val = "";
				// try {
				// date = dateFormat.parse(product.updatedAt);
				// val = dateFormat.format(date);
				// } catch (ParseException e) {
				// e.printStackTrace();
				// }

				try {
					progressBar.setVisibility(View.VISIBLE);
					progressBar.setProgress(0);
					new Thread() {
						@Override
						public void run() {
							uiHandler.post(new Runnable() {

								@Override
								public void run() {
									try {
										if (isUpdateRequired) {

										} else {
											Thread thread = new Thread(
													new Runnable() {

														@Override
														public void run() {
															// Message message =
															// handlerProduct
															// .obtainMessage(
															// 1,
															// productContentProvider
															// .getDetailProduct(product.productID));
															// handlerProduct
															// .sendMessage(message);
														}
													});
											thread.start();
										}
									} catch (Throwable t) {
									}
								}
							});
						}
					}.start();
				} catch (Exception e) {
				}

				mainImageView.setMaxZoom(4f);

				imgCut.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						mpopup.dismiss();
					}
				});
				mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0);
			}
		});

		likeButton.setOnClickListener(onClickLikeProduct(position));

		commentsLabel.setOnClickListener(onclickComment(product));
		commentsImage.setOnClickListener(onclickComment(product));

		likeLayout.setOnClickListener(onclickLike(product));

		return convertView;
	}

	@Override
	public int getCount() {
		return this.products.size();
	}

	@Override
	public Product getItem(int position) {
		return this.products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public ArrayList<Product> getProducts() {
		return this.products;
	}

	@SuppressLint("HandlerLeak")
	Handler handlerProduct = new Handler() {
		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				List<Product> lstpro = (ArrayList<Product>) msg.obj;
				getPrepare(lstpro);
				progressBar.setVisibility(View.GONE);
			default:
				break;
			}
		}
	};

	@SuppressLint("SimpleDateFormat")
	void getPrepare(List<Product> ls) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		String val = "";

		for (int i = 0; i < ls.size(); i++) {
			Product pr = ls.get(i);
			try {
				date = dateFormat.parse(pr.updatedAt);
				val = dateFormat.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			textViewNamePro.setText(pr.title + " from " + pr.brandName);
			textViewAddBy.setText("Added by " + pr.added_by + " on " + val);
			txtContentPro.setText(pr.productContent);
			imageLoader.displayImage(pr.urlPopup, mainImageView, options);
		}
	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			// switch (msg.what) {
			// case 1:
			// List<Profile> lstpros = (ArrayList<Profile>) msg.obj;
			// for (Profile pros : lstpros) {
			// Intent intent = new Intent(context, MyDiaryActivity.class);
			// intent.putExtra("profile", pros);
			// context.startActivity(intent);
			// ConstantsHelper.animationFragment(context);
			// }
			// default:
			// break;
			// }
		}
	};

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	};

	// onclick like button
	public OnClickListener onclickLike(final Product product) {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(context,
				// ProductLikesActivity.class);
				// intent.putExtra(BaseColumns._ID, product.productID);
				// intent.putExtra(ConstantsHelper.Products.NAME_PRODUCT,
				// product.title);
				// context.startActivity(intent);
				// ConstantsHelper.animationFragment(context);
			}
		};
	}

	// onclick comment button
	public OnClickListener onclickComment(final Product product) {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(context,
				// CommentProductActivity.class);
				// intent.putExtra(BaseColumns._ID, product.productID);
				// context.startActivity(intent);
				// ConstantsHelper.animationFragment(context);
			}
		};
	}

	public OnClickListener onClickLikeProduct(final int position) {
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Product product = products.get(position);

				int selectedLike;
				if (product.idLike == null) {
					selectedLike = ConstantsHelper.Likes.LIKE_CANCEL;
				} else {
					if (product.typeLike.equals("GOT_IT")) {
						selectedLike = ConstantsHelper.Likes.LIKE_GOT;
					} else if (product.typeLike.equals("NEXT_BUY")) {
						selectedLike = ConstantsHelper.Likes.LIKE_NEXT;
					} else if (product.typeLike.equals("I_DREAM")) {
						selectedLike = ConstantsHelper.Likes.LIKE_DREAM;
					} else if (product.typeLike.equals("LIKE_IT")
							|| product.typeLike.equals("")) {
						selectedLike = ConstantsHelper.Likes.LIKE_ILIKE;
					} else {
						selectedLike = ConstantsHelper.Likes.LIKE_CANCEL;
					}
				}
				showPopupWindow(selectedLike, position);
				// FragmentManager fragmentManager = ((FragmentActivity)
				// context)
				// .getSupportFragmentManager();
				// FragmentTransaction fragmentTransaction = fragmentManager
				// .beginTransaction();
				//
				// Fragment existingDialog = fragmentManager
				// .findFragmentByTag(ConstantsHelper.LIKE_DIALOG_FRAGMENT_TAG);
				// if (existingDialog != null)
				// fragmentTransaction.remove(existingDialog);
				//
				// fragmentTransaction.addToBackStack(null);
				//
				// Debugs.show("d", " product.typeLike; ",
				// " product.typeLike;  "
				// + product.typeLike);
				// String tp = "";
				// if (Tools.isEmpty(LikeDialogFragment.valTypeLike)) {
				// tp = product.typeLike;
				// } else {
				// tp = LikeDialogFragment.valTypeLike;
				// }
				//
				// String idl = "";
				// if (Tools.isEmpty(LikeDialogFragment.valIdlike)) {
				// idl = product.idLike;
				// } else {
				// idl = LikeDialogFragment.valIdlike;
				// }
				//
				// Bundle bundle = new Bundle();
				// bundle.putInt(BaseColumns._ID, product.productID);
				// // bundle.putInt(ConstantsHelper.Products.LIKE_TYPE,
				// // product.likeType);
				// bundle.putString(ConstantsHelper.Products.PRODUCT_USER, idl);
				// bundle.putString(ConstantsHelper.Products.TYPE_LIKE, tp);
				// LikeDialogFragment fragment = new LikeDialogFragment();
				// fragment.setArguments(bundle);
				// fragment.show(fragmentTransaction,
				// ConstantsHelper.LIKE_DIALOG_FRAGMENT_TAG);
			}
		};
	}

	private void showPopupWindow(final int selectedLike, final int position) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popUpView = inflater.inflate(R.layout.dialog_like_product, null);
		mpopup = new PopupWindow(popUpView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true); // Creation of popup
		mpopup.setAnimationStyle(android.R.style.Animation_Dialog);

		final Product product = products.get(position);

		TextView likeTitle = (TextView) popUpView.findViewById(R.id.like_title);
		likeTitle.setText(product.title + " - " + product.brandName);

		RadioGroup radioGroup = (RadioGroup) popUpView
				.findViewById(R.id.like_choices);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				for (int i = 0; i < group.getChildCount(); i++) {
					View view = group.getChildAt(i);
					((ToggleButton) view).setChecked(view.getId() == checkedId);
					if (view.getId() == checkedId) {
						String url = "";
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						if (Tools.isEmpty(product.idLike)) {
							url = ConstantsHelper.Likes.API_LIKE_CREATE;
							switch (view.getId()) {
							case R.id.like_dream:
								params = ConstantsHelper.Likes
										.createLikeFromParams(apiKey,
												"I_DREAM", "abc",
												product.productID, Locale
														.getDefault()
														.getLanguage());
								excuteparams(url, params, "I_DREAM", position);
								break;
							case R.id.like_have:
								params = ConstantsHelper.Likes
										.createLikeFromParams(apiKey, "GOT_IT",
												"abc", product.productID,
												Locale.getDefault()
														.getLanguage());
								excuteparams(url, params, "GOT_IT", position);
								break;
							case R.id.like_nextpurchase:
								params = ConstantsHelper.Likes
										.createLikeFromParams(apiKey,
												"NEXT_BUY", "abc",
												product.productID, Locale
														.getDefault()
														.getLanguage());
								excuteparams(url, params, "NEXT_BUY", position);
								break;
							case R.id.like_ilike:
								params = ConstantsHelper.Likes
										.createLikeFromParams(apiKey,
												"LIKE_IT", "abc",
												product.productID, Locale
														.getDefault()
														.getLanguage());
								excuteparams(url, params, "LIKE_IT", position);
								break;
							case R.id.like_cancel:
								break;
							}
						} else {
							url = ConstantsHelper.Likes.API_LIKE_UPDATE;
							switch (view.getId()) {

							case R.id.like_dream:
								params = ConstantsHelper.Likes
										.updateLikeFromParams(apiKey,
												"I_DREAM", "abc",
												product.productID, Locale
														.getDefault()
														.getLanguage(),
												product.idLike);
								excuteparams(url, params, "I_DREAM", position);
								break;
							case R.id.like_have:
								params = ConstantsHelper.Likes
										.updateLikeFromParams(apiKey, "GOT_IT",
												"abc", product.productID,
												Locale.getDefault()
														.getLanguage(),
												product.idLike);
								excuteparams(url, params, "GOT_IT", position);
								break;
							case R.id.like_nextpurchase:
								params = ConstantsHelper.Likes
										.updateLikeFromParams(apiKey,
												"NEXT_BUY", "abc",
												product.productID, Locale
														.getDefault()
														.getLanguage(),
												product.idLike);
								excuteparams(url, params, "NEXT_BUY", position);
								break;
							case R.id.like_ilike:
								params = ConstantsHelper.Likes
										.updateLikeFromParams(apiKey,
												"LIKE_IT", "abc",
												product.productID, Locale
														.getDefault()
														.getLanguage(),
												product.idLike);
								excuteparams(url, params, "LIKE_IT", position);
								break;
							case R.id.like_cancel:
								break;
							}
						}
					}
				}
				mpopup.dismiss();
			}
		});

		iGotButton = (ToggleButton) radioGroup.findViewById(R.id.like_have);
		likeDreamButton = (ToggleButton) radioGroup
				.findViewById(R.id.like_dream);
		likeNextButton = (ToggleButton) radioGroup
				.findViewById(R.id.like_nextpurchase);
		ilikeButton = (ToggleButton) radioGroup.findViewById(R.id.like_ilike);
		switch (selectedLike) {
		case ConstantsHelper.Likes.LIKE_DREAM:
			clearBackGroundButton();
			likeDreamButton.setBackgroundColor(context.getResources().getColor(
					R.color.light_gray));
			break;
		case ConstantsHelper.Likes.LIKE_NEXT:
			clearBackGroundButton();
			likeNextButton.setBackgroundColor(context.getResources().getColor(
					R.color.light_gray));
			break;
		case ConstantsHelper.Likes.LIKE_GOT:
			clearBackGroundButton();
			iGotButton.setBackgroundColor(context.getResources().getColor(
					R.color.light_gray));
			break;
		case ConstantsHelper.Likes.LIKE_ILIKE:
			clearBackGroundButton();
			ilikeButton.setBackgroundColor(context.getResources().getColor(
					R.color.light_gray));
			break;
		case ConstantsHelper.Likes.LIKE_CANCEL:
			clearBackGroundButton();
			break;
		default:
			clearBackGroundButton();
		}
		mpopup.showAtLocation(popUpView, Gravity.BOTTOM, 0, 0);

	}

	private void clearBackGroundButton() {
		iGotButton.setBackgroundColor(context.getResources().getColor(
				R.color.white));
		likeDreamButton.setBackgroundColor(context.getResources().getColor(
				R.color.white));
		likeNextButton.setBackgroundColor(context.getResources().getColor(
				R.color.white));
		ilikeButton.setBackgroundColor(context.getResources().getColor(
				R.color.white));
	}

	private void excuteparams(final String url,
			final List<NameValuePair> params, final String type,
			final int position) {

		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... voids) {
				return Connectivity.loadContentFromAPI(url, params);
			}

			@Override
			protected void onPostExecute(String result) {
				if (result != null) {
					try {
						JSONObject jsonObject = new JSONObject(result);
						String valueResult = jsonObject.getString("success");
						if (valueResult.equals("true")) {
							products.get(position).typeLike = type;
							products.get(position).idLike = jsonObject
									.getString("idlike");
							notifyDataSetChanged();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}
}
