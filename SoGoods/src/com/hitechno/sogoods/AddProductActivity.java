package com.hitechno.sogoods;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.aviary.android.feather.FeatherActivity;
import com.aviary.android.feather.common.utils.StringUtils;
import com.aviary.android.feather.library.Constants;
import com.mttam.toollibrary.tools.Tools;
import com.hitechno.sogoods.api.APILikeProduct;
import com.hitechno.sogoods.api.Connectivity;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.fragments.ChooseBrandFragment;
import com.hitechno.sogoods.fragments.ChooseCategoriesFragment;
import com.hitechno.sogoods.managers.SoGoodsAPIManager;
import com.hitechno.sogoods.managers.SoGoodsAPIManager.APIResponse;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

@SuppressLint("NewApi")
public class AddProductActivity extends FragmentActivity {
	private static final int ACTION_AVIARY_EDITOR = 98;
	private static final int ACTION_REQUEST_GALLERY = 99;
	private static final int ACTION_TAKE_PICTURE = 101;
	public static String LINK_IMAGEPRODUCT = "";
	public static ImageView productImageView;
	public int BRAND_ID;
	public int CATEGORY_ID;
	private int LIKE = 111;
	private int ON_BACK;
	private String apiKey;
	private APILikeProduct apiLikeProduct;
	private Bitmap bitmap;
	public TextView brandLabel;
	private LinearLayout brandLayout;
	private TextView buttonBack;
	private TextView buttonSave;
	private ImageView cameraButton;
	private LinearLayout categoriesLayout;
	public TextView categoryLabel;
	private Configuration config;
	private Context context;
	private ProgressDialog dialog;
	public EditText feelingInput;
	public TextView feelingLabel;
	private ImageView galleryButton;
	private ImageView googleButton;
	private ToggleButton iGotButton;
	private ToggleButton ilikeButton;
	private ToggleButton likeDreamButton;
	private ToggleButton likeNextButton;
	private String mOutputFilePath;
	private PopupWindow mpopup;
	public EditText productNameInput;
	public TextView soLabel;
	private LinearLayout soLayout;

	private File getTempImageFile() {
		File destDir = getExternalFilesDir("images");
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		File destFile = null;
		try {
			destFile = File.createTempFile("avatar_", ".jpg", destDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;
	}
	
	private void checkingField() {
		if (Tools.isEmpty(this.productNameInput.getText().toString())) {
			Toast.makeText(this.context, "Product Name Empty !", 1).show();
			return;
		}
		if (this.BRAND_ID == 0) {
			Toast.makeText(this.context, "Brand Empty !", 1).show();
			return;
		}
		if (this.CATEGORY_ID == 0) {
			Toast.makeText(this.context, "Category Empty !", 1).show();
			return;
		}
		if (Tools.isEmpty(LINK_IMAGEPRODUCT)) {
			Toast.makeText(this.context, "Product Image Empty !", 1).show();
			return;
		}
		createProductAsync localcreateProductAsync = new createProductAsync();
		Executor localExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = LINK_IMAGEPRODUCT;
		localcreateProductAsync.executeOnExecutor(localExecutor, arrayOfObject);
	}

	private void clearBackGroundButton() {
		this.iGotButton.setBackgroundColor(this.context.getResources()
				.getColor(2131099652));
		this.likeDreamButton.setBackgroundColor(this.context.getResources()
				.getColor(2131099652));
		this.likeNextButton.setBackgroundColor(this.context.getResources()
				.getColor(2131099652));
		this.ilikeButton.setBackgroundColor(this.context.getResources()
				.getColor(2131099652));
	}

	private File createFolders() {
		File localFile1;
		File localFile2;
		if (Build.VERSION.SDK_INT < 8) {
			localFile1 = Environment.getExternalStorageDirectory();
			if (localFile1 != null) {
				localFile2 = Environment.getExternalStorageDirectory();
			}

		}
		// label32:
		do {
			// return localFile2;
			localFile1 = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			// break;
			localFile2 = new File(localFile1, "aviary");
			if (localFile2 != null)
				return localFile2;
		} while ((localFile2.exists()) || (localFile2.mkdirs()));
		return Environment.getExternalStorageDirectory();
	}

	private void goToAriaryEditor(Uri paramUri) {
		if (!isExternalStorageAvilable()) {
			Log.w("GalleryActivity", "External Storage not Available");
			return;
		}
		File localFile1 = createFolders();
		Log.w("localFile1", "" + localFile1.getAbsolutePath());
		File localFile2 = null;
		if (localFile1 != null) {
			boolean bool = localFile1.exists();
			localFile2 = null;
			if (bool) {
				localFile2 = new File(localFile1, "aviary_"
						+ System.currentTimeMillis() + ".png");
			}
		}
		if (localFile2 != null) {
			this.mOutputFilePath = localFile2.getAbsolutePath();
			Intent localIntent = new Intent(this, FeatherActivity.class);
			localIntent.setData(paramUri);
			localIntent.putExtra(Constants.EXTRA_OUTPUT,
					Uri.parse("file://" + this.mOutputFilePath));
			localIntent.putExtra(Constants.EXTRA_OUTPUT_FORMAT,
					Bitmap.CompressFormat.JPEG.name());
			localIntent.putExtra(Constants.EXTRA_OUTPUT_QUALITY, 90);
			final DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int max_size = Math.max(metrics.widthPixels, metrics.heightPixels);
			max_size = (int) ((float) max_size / 1.2f);
			localIntent.putExtra(Constants.EXTRA_MAX_IMAGE_SIZE, max_size);
			localIntent.putExtra(Constants.EXTRA_IN_SAVE_ON_NO_CHANGES, true);
			localIntent.putExtra(Constants.EXTRA_IN_API_KEY_SECRET,
					"a919a54d4af91822");
			startActivityForResult(localIntent, 1);
		}
		Log.w("GalleryActivity", "Fail to create a file");
	}

	private File m_PictureFileTemp = null;
	private void init() {
		this.categoriesLayout = ((LinearLayout) findViewById(R.id.relCategories));
		this.brandLayout = ((LinearLayout) findViewById(R.id.relaBrand));
		this.soLayout = ((LinearLayout) findViewById(R.id.addproduct_so_layout));
		this.buttonBack = ((TextView) findViewById(R.id.profile_header_menu_button));
		this.buttonSave = ((TextView) findViewById(R.id.bttValidatePro));
		this.categoryLabel = ((TextView) findViewById(R.id.txtCategories));
		this.brandLabel = ((TextView) findViewById(R.id.txtBrand));
		this.soLabel = ((TextView) findViewById(R.id.addproduct_so_label));
		this.feelingLabel = ((TextView) findViewById(R.id.addproduct_felling_label));
		productImageView = (ImageView) findViewById(R.id.addproduct_image);
		this.productNameInput = ((EditText) findViewById(R.id.addproduct_name_input));
		this.feelingInput = ((EditText) findViewById(R.id.addproduct_feeling_input));
		this.cameraButton = ((ImageView) findViewById(R.id.btt_camera));
		this.googleButton = ((ImageView) findViewById(R.id.btt_google));
		this.galleryButton = ((ImageView) findViewById(R.id.btt_gallery));
		this.feelingInput.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable paramAnonymousEditable) {
			}

			public void beforeTextChanged(
					CharSequence paramAnonymousCharSequence,
					int paramAnonymousInt1, int paramAnonymousInt2,
					int paramAnonymousInt3) {
			}

			public void onTextChanged(CharSequence paramAnonymousCharSequence,
					int paramAnonymousInt1, int paramAnonymousInt2,
					int paramAnonymousInt3) {
				String str = paramAnonymousCharSequence.toString();
				AddProductActivity.this.feelingLabel.setText(str.length()
						+ "/250");
			}
		});

		this.cameraButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				m_PictureFileTemp = getTempImageFile();
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(m_PictureFileTemp));
				startActivityForResult(intent, ACTION_TAKE_PICTURE);
			}
		});
		this.googleButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Intent localIntent = new Intent(
						AddProductActivity.this.context,
						ListPhotoActivity.class);
				localIntent.putExtra("type", true);
				AddProductActivity.this.startActivity(localIntent);
				ConstantsHelper.animationPro(AddProductActivity.this);
			}
		});
		this.galleryButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddProductActivity.this.showPopupAddPicture();
				// Intent localIntent = new
				// Intent(AddProductActivity.this.context,
				// ListPhotoActivity.class);
				// localIntent.putExtra("type", false);
				// AddProductActivity.this.startActivity(localIntent);
				// ConstantsHelper.animationPro(AddProductActivity.this);
			}
		});
		this.buttonSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				Log.d("value ", "value = brandID : "
						+ AddProductActivity.this.BRAND_ID
						+ "---- CATEGORY_ID : "
						+ AddProductActivity.this.CATEGORY_ID
						+ "-----"
						+ AddProductActivity.this.productNameInput.getText()
								.toString() + "--- image Path: "
						+ AddProductActivity.LINK_IMAGEPRODUCT);
				AddProductActivity.this.checkingField();
			}
		});
		this.buttonBack.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddProductActivity.this.onBackPressed();
			}
		});
		this.soLayout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddProductActivity.this.showLikePopupWindow();
			}
		});
		this.categoriesLayout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddProductActivity.this.ON_BACK = 1;
				FragmentTransaction localFragmentTransaction = AddProductActivity.this
						.getSupportFragmentManager().beginTransaction();
				ChooseCategoriesFragment localChooseCategoriesFragment = new ChooseCategoriesFragment();
				localFragmentTransaction.setCustomAnimations(
						R.anim.slide_in_left, R.anim.slide_out_left);
				localFragmentTransaction.replace(
						R.id.liner_contentbrand_addpro,
						localChooseCategoriesFragment, "LIST_CATE");
				localFragmentTransaction.commit();
			}
		});
		this.brandLayout.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				if (AddProductActivity.this.categoryLabel.getText().equals(
						AddProductActivity.this.getResources().getString(
								R.string.addproduct_hint2))) {
					AlertDialog.Builder localBuilder = new AlertDialog.Builder(
							AddProductActivity.this.context);
					localBuilder.setCancelable(false);
					localBuilder.setTitle(AddProductActivity.this
							.getResources().getString(
									R.string.veuillezChoisirUneCategorie));
					AddProductActivity.this.showDialog(localBuilder);
					return;
				}
				AddProductActivity.this.ON_BACK = 2;
				FragmentTransaction localFragmentTransaction = AddProductActivity.this
						.getSupportFragmentManager().beginTransaction();
				ChooseBrandFragment localChooseBrandFragment = new ChooseBrandFragment();
				localFragmentTransaction.setCustomAnimations(
						R.anim.slide_in_left, R.anim.slide_out_left);
				localFragmentTransaction.replace(
						R.id.liner_contentbrand_addpro,
						localChooseBrandFragment, "LIST_BRAND");
				localFragmentTransaction.commit();
			}
		});
	}

	private boolean isExternalStorageAvilable() {
		return "mounted".equals(Environment.getExternalStorageState());
	}

	private void showDialog(AlertDialog.Builder paramBuilder) {
		paramBuilder.setInverseBackgroundForced(true);
		paramBuilder.setPositiveButton(getResources().getString(2131165330),
				new DialogInterface.OnClickListener() {
					public void onClick(
							DialogInterface paramAnonymousDialogInterface,
							int paramAnonymousInt) {
						paramAnonymousDialogInterface.dismiss();
					}
				});
		paramBuilder.create().show();
	}

	private void showLikePopupWindow() {
		View localView = ((LayoutInflater) this.context
				.getSystemService("layout_inflater")).inflate(
				R.layout.dialog_like_product, null);
		this.mpopup = new PopupWindow(localView, -1, -1, true);
		this.mpopup.setAnimationStyle(16973826);
		TextView localTextView = (TextView) localView
				.findViewById(R.id.like_title);
		if ((!Tools.isEmpty(this.brandLabel.getText()))
				&& (!this.brandLabel.getText().toString()
						.equalsIgnoreCase(getString(2131165763)))) {
			localTextView.setText(this.brandLabel.getText());
		}
		this.iGotButton = ((ToggleButton) localView
				.findViewById(R.id.like_ilike));
		this.likeDreamButton = ((ToggleButton) localView
				.findViewById(R.id.like_dream));
		this.likeNextButton = ((ToggleButton) localView
				.findViewById(R.id.like_nextpurchase));
		this.ilikeButton = ((ToggleButton) localView
				.findViewById(R.id.like_have));
		((RadioGroup) localView.findViewById(R.id.like_choices))
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					public void onCheckedChanged(
							RadioGroup paramAnonymousRadioGroup,
							int paramAnonymousInt) {
						switch (paramAnonymousInt) {
						case R.id.like_dream:
							AddProductActivity.this.LIKE = 0;
							AddProductActivity.this.soLabel
									.setText(AddProductActivity.this.likeDreamButton
											.getText());
							break;
						case R.id.like_ilike:
							AddProductActivity.this.LIKE = 3;
							AddProductActivity.this.soLabel
									.setText(AddProductActivity.this.iGotButton
											.getText());
							break;
						case R.id.like_nextpurchase:
							AddProductActivity.this.LIKE = 2;
							AddProductActivity.this.soLabel
									.setText(AddProductActivity.this.likeNextButton
											.getText());
							break;
						case R.id.like_have:
							AddProductActivity.this.LIKE = 1;
							AddProductActivity.this.soLabel
									.setText(AddProductActivity.this.ilikeButton
											.getText());
							break;
						// case R.id.like_cancel:
						// break;
						}
						AddProductActivity.this.mpopup.dismiss();
						// int i = 0;
						// if (0 >= paramAnonymousRadioGroup.getChildCount()) {
						// return;
						// }
						// View localView =
						// paramAnonymousRadioGroup.getChildAt(paramAnonymousInt);
						// ToggleButton localToggleButton =
						// (ToggleButton)localView;
						// boolean bool;
						// if (localView.getId() == paramAnonymousInt)
						// {
						// bool = true;
						// // label37:
						// localToggleButton.setChecked(bool);
						// if (localView.getId() == paramAnonymousInt) {
						//
						// }
						// }
						// TODO:
						// for (;;)
						// {
						Log.w("test", "" + paramAnonymousInt);
						// switch (paramAnonymousInt)
						// {
						// case 111
						// bool = false;
						// break;
						//
						// break label37;
						// AddProductActivity.this.LIKE = 0;
						// AddProductActivity.this.soLabel.setText(AddProductActivity.this.likeDreamButton.getText());
						// continue;
						// AddProductActivity.this.LIKE = 3;
						// AddProductActivity.this.soLabel.setText(AddProductActivity.this.iGotButton.getText());
						// continue;
						// AddProductActivity.this.LIKE = 2;
						// AddProductActivity.this.soLabel.setText(AddProductActivity.this.likeNextButton.getText());
						// continue;
						// AddProductActivity.this.LIKE = 1;
						// AddProductActivity.this.soLabel.setText(AddProductActivity.this.ilikeButton.getText());
						// continue;
						// AddProductActivity.this.LIKE = 4;
						// AddProductActivity.this.soLabel.setText("");
						// }
						// AddProductActivity.this.mpopup.dismiss();
						// }
					}
				});
		// TODO:
		// switch (this.LIKE)
		// {
		// default:
		// clearBackGroundButton();
		// }
		// for (;;)
		// {
		this.mpopup.showAtLocation(localView, 80, 0, 0);
		// return;
		// clearBackGroundButton();
		// this.likeDreamButton.setBackgroundColor(this.context.getResources().getColor(2131099783));
		// continue;
		// clearBackGroundButton();
		// this.likeNextButton.setBackgroundColor(this.context.getResources().getColor(2131099783));
		// continue;
		// clearBackGroundButton();
		// this.iGotButton.setBackgroundColor(this.context.getResources().getColor(2131099783));
		// continue;
		// clearBackGroundButton();
		// this.ilikeButton.setBackgroundColor(this.context.getResources().getColor(2131099783));
		// continue;
		// clearBackGroundButton();
		// }
	}

	private void showPopupAddPicture() {
		View localView = ((LayoutInflater) this.context
				.getSystemService("layout_inflater")).inflate(
				R.layout.dialog_add_picture, null);
		this.mpopup = new PopupWindow(localView, -1, -1, true);
		this.mpopup.setAnimationStyle(16973826);
		TextView localTextView1 = (TextView) localView
				.findViewById(R.id.dialog_addpic_takepic_button);
		localTextView1.setText(R.string.choose_picture_my_gallery);
		TextView localTextView2 = (TextView) localView
				.findViewById(R.id.dialog_addpic_choosepic_button);
		localTextView2.setText(R.string.choose_picture_phone_gallery);
		((TextView) localView.findViewById(R.id.dialog_addpic_cancel_button))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View paramAnonymousView) {
						AddProductActivity.this.mpopup.dismiss();
					}
				});
		localTextView1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddProductActivity.this.mpopup.dismiss();
			}
		});
		localTextView2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				AddProductActivity.this.mpopup.dismiss();
				Intent localIntent = new Intent("android.intent.action.PICK",
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				AddProductActivity.this.startActivityForResult(localIntent, 99);
			}
		});
		this.mpopup.showAtLocation(localView, 80, 0, 0);
	}

	private void updateMedia(String paramString) {
		MediaScannerConnection.scanFile(getApplicationContext(),
				new String[] { paramString }, null, null);
	}

	protected void onActivityResult(int paramInt1, int paramInt2,
			Intent paramIntent) {
		super.onActivityResult(paramInt1, paramInt2, paramIntent);
		InputStream localInputStream = null;
		if (paramInt2 == -1) {

			// goToAriaryEditor(paramIntent.getData());
			switch (paramInt1) {
			case 100:
				// default:
				break;
			case 101:
				goToAriaryEditor(Uri.fromFile(m_PictureFileTemp));
				// Debugs.show("d", "GalleryActivity",
				// "intent take from camera NULL");
				break;
			case 99:
				if (paramIntent != null) {
					goToAriaryEditor(paramIntent.getData());

				}
				// Debugs.show("d", "GalleryActivity",
				// "intent choose from Gallery NULL");
				break;
			case 1:
				// String str = "Aviary OK";
				// str = "Aviary out Failed";
				// Log.w("lexury", ""+imagePatch);
				// Bundle localBundle = paramIntent.getExtras();
				// if (localBundle != null) {
				// if (!localBundle.getBoolean("bitmap-changed")) {
				if ((m_PictureFileTemp != null) && m_PictureFileTemp.exists()) {
					m_PictureFileTemp.delete();
					m_PictureFileTemp = null;
				}
				updateMedia(this.mOutputFilePath);
				Bitmap localBitmap = BitmapFactory
						.decodeFile(this.mOutputFilePath);
				this.productImageView.setImageBitmap(localBitmap);
				LINK_IMAGEPRODUCT = mOutputFilePath;
				// }
				// }
				// Log.d("GalleryActivity", str);

				break;
			}
		}

		// TODO:
		// for (;;)
		// {
		// return;
		// if (paramIntent != null)
		// {
		// goToAriaryEditor(paramIntent.getData());
		// return;
		// }
		// Debugs.show("d", "GalleryActivity", "intent take from camera NULL");
		// return;
		// if (paramIntent != null)
		// {
		// goToAriaryEditor(paramIntent.getData());
		// return;
		// }
		// Debugs.show("d", "GalleryActivity",
		// "intent choose from Gallery NULL");
		// return;
		// Uri localUri = paramIntent.getData();
		// Bundle localBundle = paramIntent.getExtras();
		// String str;
		// if (localBundle != null)
		// {
		// if (localBundle.getBoolean("bitmap-changed"))
		// {
		// str = "Aviary OK";
		// label132:
		// Log.d("GalleryActivity", str);
		// }
		// }
		// else
		// {
		// updateMedia(this.mOutputFilePath);
		// LINK_IMAGEPRODUCT = this.mOutputFilePath;
		// }
		// try
		// {
		// Bitmap localBitmap = this.bitmap;
		// localInputStream = null;
		// if (localBitmap != null) {
		// this.bitmap.recycle();
		// }
		// localInputStream = getContentResolver().openInputStream(localUri);
		// this.bitmap = BitmapFactory.decodeStream(localInputStream);
		// productImageView.setImageBitmap(this.bitmap);
		// if (localInputStream != null)
		// {
		// try
		// {
		// localInputStream.close();
		// return;
		// }
		// catch (IOException localIOException3)
		// {
		// localIOException3.printStackTrace();
		// return;
		// }
		// str = "Aviary out Failed";
		// break label132;
		// }
		// }
		// catch (Exception localException)
		// {
		// localException.printStackTrace();
		// if (localInputStream != null) {
		// try
		// {
		// localInputStream.close();
		// return;
		// }
		// catch (IOException localIOException2)
		// {
		// localIOException2.printStackTrace();
		// return;
		// }
		// }
		// }
		// finally
		// {
		// if (localInputStream == null) {}
		// }
		// }
		// try
		// {
		// localInputStream.close();
		// // throw localObject;
		// }
		// catch (IOException localIOException1)
		// {
		// // for (;;)
		// // {
		// localIOException1.printStackTrace();
		// // }
		// }
	}

	public void onBackPressed() {
		FragmentTransaction localFragmentTransaction = getSupportFragmentManager()
				.beginTransaction();
		Debugs.show("d", "AddProductActivity", "ON_BACK =" + this.ON_BACK);
		// finish();
		switch (this.ON_BACK) {
		case 0:
			// Tools.hideKeyboard(this, this.productNameInput);
			finish();
			// ConstantsHelper.animationPro(this);
			break;
		case 1:
			localFragmentTransaction
					.hide((ChooseCategoriesFragment) getSupportFragmentManager()
							.findFragmentByTag("LIST_CATE"));
			localFragmentTransaction.commit();
			this.ON_BACK = 0;
			break;
		case 2:
			localFragmentTransaction
					.hide((ChooseBrandFragment) getSupportFragmentManager()
							.findFragmentByTag("LIST_BRAND"));
			localFragmentTransaction.commit();
			this.ON_BACK = 0;
			break;
		// default:
		// super.onBackPressed();
		// finish();
		}
		// ConstantsHelper.animationPro(this);
		// for (;;)
		// {
		// Tools.hideKeyboard(this, this.productNameInput);
		// this.ON_BACK = 0;
		//
		// return;
		//
		// continue;
		//
		// }
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setContentView(R.layout.activity_add_product);
		this.context = this;
		this.apiLikeProduct = new APILikeProduct();
		this.apiKey = MySharePreference.getApiKey(this.context);
		SoGoodsAPIManager.getInstance().setAPIKey(apiKey);
		this.config = Configuration.getInstance(this.context);
		this.ON_BACK = 0;
		this.CATEGORY_ID = 0;
		init();
	}

	public void onProductLikeChoiceClicked(View paramView) {
		((RadioGroup) paramView.getParent()).check(paramView.getId());
	}

	private class CreateLikeProduct extends AsyncTask<String, Void, String> {
		private CreateLikeProduct() {
		}

		protected String doInBackground(String... paramVarArgs) {
			return AddProductActivity.this.apiLikeProduct.CreateLikeProduct(
					AddProductActivity.this.apiKey, paramVarArgs[0], "abc",
					Integer.parseInt(paramVarArgs[1]),
					AddProductActivity.this.config.currentLanguage);
		}

		protected void onPostExecute(String paramString) {
			super.onPostExecute(paramString);
		}
	}

	private class createProductAsync extends AsyncTask<Object, Void, Boolean> {
		private String IMAGE_PATH_LARGE;

		private createProductAsync() {
		}

		protected Boolean doInBackground(Object... paramVarArgs) {
			APIResponse response = SoGoodsAPIManager.getInstance().createProduct(new File(mOutputFilePath),
					productNameInput.getText().toString(), BRAND_ID, CATEGORY_ID, feelingInput.getText().toString());
			return response.isSuccess();
//			try {
//				this.IMAGE_PATH_LARGE = ((String) paramVarArgs[0]);
//				DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
//				HttpPost localHttpPost = new HttpPost(
//						"http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/user_gallery/create.json");
//				File localFile = new File(this.IMAGE_PATH_LARGE);
//				MultipartEntity localMultipartEntity = new MultipartEntity(
//						HttpMultipartMode.BROWSER_COMPATIBLE);
//				if (localFile.exists()) {
//					localMultipartEntity.addPart("product_picture_square",
//							new FileBody(localFile, "image/jpeg"));
//				}
//				// TODO:
//				// for (;;)
//				// {
//				localMultipartEntity.addPart("apikey", new StringBody(
//						AddProductActivity.this.apiKey));
//				localMultipartEntity.addPart("title", new StringBody(
//						AddProductActivity.this.productNameInput.getText()
//								.toString()));
//				localMultipartEntity.addPart(
//						"idbrand",
//						new StringBody(String
//								.valueOf(AddProductActivity.this.BRAND_ID)));
//				localMultipartEntity.addPart("idcategory", new StringBody(
//						String.valueOf(AddProductActivity.this.CATEGORY_ID)));
//				if (!Tools.isEmpty(AddProductActivity.this.feelingInput
//						.getText().toString())) {
//					localMultipartEntity.addPart("content", new StringBody(
//							AddProductActivity.this.feelingInput.getText()
//									.toString()));
//				}
//				localHttpPost.setEntity(localMultipartEntity);
//				String str = Connectivity
//						.inputStreamToString(localDefaultHttpClient
//								.execute(localHttpPost).getEntity()
//								.getContent());
//				Log.d("createProductAsync", "result = " + str);
//				if (!new JSONObject(str).getString("success").equals("true")) {
//					Log.e("createProductAsync", "file not found");
//				}
//				// return Boolean.valueOf(true);
//
//				// }
//				return Boolean.valueOf(false);
//			} catch (Exception localException) {
//				localException.printStackTrace();
//				return Boolean.valueOf(false);
//			}
		}

		protected void onPostExecute(Boolean paramBoolean) {
			super.onPostExecute(paramBoolean);
			paramBoolean.booleanValue();
			AddProductActivity.this.dialog.dismiss();
			AddProductActivity.this.buttonSave.setClickable(true);
		}

		protected void onPreExecute() {
			super.onPreExecute();
			AddProductActivity.this.buttonSave.setClickable(false);
			AddProductActivity.this.dialog = ProgressDialog.show(
					AddProductActivity.this,
					AddProductActivity.this.getString(2131165760),
					"Processing ....");
			Log.d("createProductAsync", "start to upload new product");
		}
	}
}
