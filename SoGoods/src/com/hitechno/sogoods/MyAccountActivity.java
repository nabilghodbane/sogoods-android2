package com.hitechno.sogoods;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.androidannotations.annotations.*;

import com.aviary.android.feather.FeatherActivity;
import com.hitechno.sogoods.databases.JobDB;
import com.hitechno.sogoods.fragments.DatePickerFragment;
import com.hitechno.sogoods.fragments.DatePickerFragment_;
import com.hitechno.sogoods.fragments.PictureSelectOptionsFragment;
import com.hitechno.sogoods.fragments.SearchCityNameFragment;
import com.hitechno.sogoods.fragments.PictureSelectOptionsFragment.PictureSelectOptionsFragmentListener;
import com.hitechno.sogoods.managers.SoGoodsAPIManager;
import com.hitechno.sogoods.model.City;
import com.hitechno.sogoods.model.Job;
import com.hitechno.sogoods.views.CustomFontTextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

@WindowFeature({Window.FEATURE_NO_TITLE})
@EActivity(R.layout.activity_my_account)
public class MyAccountActivity extends FragmentActivity implements
	AdapterView.OnItemSelectedListener,
	DatePickerFragment.DatePickerFragmentListener,
	SearchCityNameFragment.SearchCityNameFragmentResult,
	View.OnFocusChangeListener,
	PictureSelectOptionsFragment.PictureSelectOptionsFragmentListener,
	View.OnClickListener
{
	public static final String EXTRA_ACTION = "action";
	public static final String EXTRA_CREATE_EMAIL = "email";
	public static final String EXTRA_CREATE_PASSWORD = "password";
	public static final int ACTION_CREATE = 0;
	private static final int REQ_CODE_TAKE_PIC = 0;
	private static final int REQ_CODE_CHOOSE_PIC = 1;
	private static final int REQ_CODE_AVIARY = 2;
	private static int EMPTY_VIEW_HEIGHT = -1;

	public static float dipToPixels(Context context, float dipValue) {
	    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
	    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
	}

	private static View getViewFor(Context ctx, int part, int position, View convertView, ViewGroup parent, String text) {
		View result;
		if (position > 0) {
			CustomFontTextView txt;
			if ((convertView != null) && (convertView instanceof CustomFontTextView)) {
				txt = (CustomFontTextView)convertView;
			} else {
				txt = new CustomFontTextView(ctx);
				txt.setCustomFont("fonts/HelveticaNeue-Light.ttf");
				txt.setTextSize(16);
				txt.setTextColor(Color.BLACK);
				AbsListView.LayoutParams params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
						AbsListView.LayoutParams.WRAP_CONTENT);
				txt.setLayoutParams(params);
				int padding = (int)MyAccountActivity.dipToPixels(ctx, 8);
				if (part != 1) {
					txt.setPadding(padding, padding, padding, padding);
				}
				txt.setTextColor(ctx.getResources().getColor(R.color.text1));
				txt.setBackgroundColor(Color.WHITE);
			}
			txt.setText(text);
			result = txt;
		} else {
			if ((convertView != null) && (!(convertView instanceof CustomFontTextView))) {
				result = convertView;
			} else {
				result = new View(ctx);
				if (EMPTY_VIEW_HEIGHT == -1) {
					CustomFontTextView txt = (CustomFontTextView)getViewFor(ctx, part, 1, null, null, "hello");
					txt.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
							View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
					EMPTY_VIEW_HEIGHT = txt.getMeasuredHeight();
					txt = null;
				}
				if (part == 1) {
					result.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
							EMPTY_VIEW_HEIGHT));
				} else {
					result.setLayoutParams(new AbsListView.LayoutParams(0, 0));
				}
			}
		}
		return result;
	}

	private static class GenderAdapter extends BaseAdapter {
		private SparseArray<String> m_List;
		private Context m_Context;
		public GenderAdapter(SparseArray<String> list, Context context) {
			m_List = list;
			m_Context = context;
		}
		
		@Override
		public int getCount() {
			return m_List.size() + 1;
		}
	
		@Override
		public Object getItem(int position) {
			if (position <= 0) {
				return null;
			}
			return m_List.get(position - 1);
		}
	
		@Override
		public long getItemId(int position) {
			if (position <= 0) return -1;
			return m_List.keyAt(position);
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getViewFor(m_Context, 1, position, convertView, parent,
					position == 0 ? "" : m_List.get(m_List.keyAt(position - 1)));
		}
	
		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			return getViewFor(m_Context, 0, position, convertView, parent,
					position == 0 ? "" : m_List.get(m_List.keyAt(position - 1)));
		}
	}

	private static class JobsAdapter extends BaseAdapter {
		private Job[] m_Jobs = null;
		private Context m_Context;
		
		public JobsAdapter(Context ctx, Job[] jobs) {
			m_Jobs = jobs;
			m_Context = ctx;
		}
		
		@Override
		public int getCount() {
			if (m_Jobs != null) {
				return m_Jobs.length + 1;
			}
			return 0;
		}

		@Override
		public Object getItem(int position) {
			if (position <= 0) {
				return null;
			}
			return m_Jobs[position - 1];
		}

		@Override
		public long getItemId(int position) {
			if (position <= 0) return -1;
			return m_Jobs[position - 1].getID();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getViewFor(m_Context, 1, position, convertView, parent,
					position == 0 ? "" : m_Jobs[position - 1].getName());
		}
	
		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			return getViewFor(m_Context, 0, position, convertView, parent,
					position == 0 ? "" : m_Jobs[position - 1].getName());
		}
	}
	
	private static class ClearShow implements TextWatcher {
		private ImageView m_Clear;
		
		public ClearShow(ImageView clear) {
			m_Clear = clear;
		}
		
		@Override
		public void afterTextChanged(Editable s) {
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			m_Clear.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
		}
	}
	
	@ViewById(R.id.save)
	TextView m_Save;
	@ViewById(R.id.progressPercent)
	TextView m_ProgressPercent;
	@ViewById(R.id.progressBar)
	ProgressBar m_ProgressBar;
	@ViewById(R.id.genderSpinner)
	Spinner m_Gender;
	@ViewById(R.id.jobSpinner)
	Spinner m_JobsSpinner;
	// Clear buttons
	@ViewById(R.id.genderClear)
	ImageView m_GenderClear;
	@ViewById(R.id.firstNameClear)
	ImageView m_FirstNameClear;
	@ViewById(R.id.lastNameClear)
	ImageView m_LastNameClear;
	@ViewById(R.id.userNameClear)
	ImageView m_UserNameClear;
	@ViewById(R.id.birthDateClear)
	ImageView m_BirthDateClear;
	@ViewById(R.id.cityClear)
	ImageView m_CityClear;
	@ViewById(R.id.jobClear)
	ImageView m_JobClear;
	@ViewById(R.id.emailClear)
	ImageView m_EMailClear;
	@ViewById(R.id.passwordClear)
	ImageView m_PasswordClear;
	@ViewById(R.id.confirmPasswordClear)
	ImageView m_ConfirmPasswordClear;
	// Input fields
	@ViewById (R.id.firstName)
	EditText m_FirstName;
	@ViewById (R.id.lastName)
	EditText m_LastName;
	@ViewById (R.id.userName)
	EditText m_UserName;
	@ViewById (R.id.birthDate)
	TextView m_BirthDate;
	@ViewById (R.id.city)
	TextView m_City;
	@ViewById (R.id.email)
	TextView m_EMail;
	@ViewById (R.id.password)
	TextView m_Password;
	@ViewById (R.id.confirmPassword)
	EditText m_ConfirmPassword;
	@ViewById(R.id.picturePhoto)
	ImageView m_Picture;
	@ViewById(R.id.loading)
	ProgressBar m_Loading;
	@ViewById(R.id.fragmentsContainer)
	FrameLayout m_FragmentsContainer;
	@ViewById(R.id.showUserName)
	ToggleButton m_ShowUserName;
	@ViewById(R.id.showBirthDay)
	ToggleButton m_ShowBirthDate;
	
	private Job[] m_Jobs = null;
	private GregorianCalendar m_BirthDateCalendar = null;
	private City m_CitySelected = null;
	private SparseBooleanArray m_CompletionFields;
	private File m_PictureFileTemp = null;
	private File m_PictureFinal = null;
	@AfterViews
	void init() {
		// Fill gender and job spinners
		SparseArray<String> genders = new SparseArray<String>();
		genders.put(1, getString(R.string.miss));
		genders.put(2, getString(R.string.mister));
		genders.put(3, getString(R.string.madam));
		m_Gender.setAdapter(new GenderAdapter(genders, this));
		m_Jobs = JobDB.getInstance().getAllJobs(Locale.getDefault().getLanguage());
		m_JobsSpinner.setAdapter(new JobsAdapter(this, m_Jobs));
		// Remove all the clear buttons
		m_GenderClear.setVisibility(View.INVISIBLE);
		m_FirstNameClear.setVisibility(View.INVISIBLE);
		m_LastNameClear.setVisibility(View.INVISIBLE);
		m_UserNameClear.setVisibility(View.INVISIBLE);
		m_BirthDateClear.setVisibility(View.INVISIBLE);
		m_CityClear.setVisibility(View.INVISIBLE);
		m_JobClear.setVisibility(View.INVISIBLE);
		m_ConfirmPasswordClear.setVisibility(View.INVISIBLE);
		// Disable eMail and password clear buttons
		m_EMailClear.setEnabled(false);
		m_PasswordClear.setEnabled(false);
		// Associate clear buttons to their respective views
		m_GenderClear.setTag(m_Gender);
		m_FirstNameClear.setTag(m_FirstName);
		m_LastNameClear.setTag(m_LastName);
		m_UserNameClear.setTag(m_UserName);
		m_BirthDateClear.setTag(m_BirthDate);
		m_CityClear.setTag(m_City);
		m_JobClear.setTag(m_JobsSpinner);
		m_ConfirmPasswordClear.setTag(m_ConfirmPassword);
		// Clear button show listener
		m_FirstName.addTextChangedListener(new ClearShow(m_FirstNameClear));
		m_LastName.addTextChangedListener(new ClearShow(m_LastNameClear));
		m_UserName.addTextChangedListener(new ClearShow(m_UserNameClear));
		m_ConfirmPassword.addTextChangedListener(new ClearShow(m_ConfirmPasswordClear));
		m_JobsSpinner.setOnItemSelectedListener(this);
		m_Gender.setOnItemSelectedListener(this);
		// Fill fields
		Intent startingIntent = getIntent();
		if (startingIntent.getIntExtra(EXTRA_ACTION, ACTION_CREATE) == ACTION_CREATE) {
			m_EMail.setText(startingIntent.getStringExtra(EXTRA_CREATE_EMAIL));
			m_Password.setText(startingIntent.getStringExtra(EXTRA_CREATE_PASSWORD));
		}
		// Progress init
		m_ProgressPercent.setText("10%");
		m_ProgressBar.setMax(10);
		m_ProgressBar.setProgress(1);
		m_CompletionFields = new SparseBooleanArray();
		m_CompletionFields.put(R.id.email, true);
		m_FirstName.setOnFocusChangeListener(this);
		m_LastName.setOnFocusChangeListener(this);
		m_UserName.setOnFocusChangeListener(this);
		m_ConfirmPassword.setOnFocusChangeListener(this);
	}
	
	public void onClearClick(View v) {
		Object toClear = v.getTag();
		if (toClear != null) {
			if (toClear instanceof Spinner) {
				((Spinner)toClear).setSelection(0);
				updateCompletion(((Spinner)toClear).getId(), false);
			} else if (toClear instanceof EditText) {
				((EditText)toClear).setText("");
				updateCompletion(((EditText)toClear).getId(), false);
			} else if (toClear instanceof TextView) {
				((TextView)toClear).setText("");
				if (v.getId() == R.id.birthDateClear) {
					m_BirthDateCalendar = null;
				} else if (v.getId() == R.id.cityClear) {
					m_CitySelected = null;
				}
				v.setVisibility(View.INVISIBLE);
				updateCompletion(((TextView)toClear).getId(), false);
			}
		}
	}

	private void updateCompletion(int res, boolean value) {
		m_CompletionFields.put(res, value);
		int progress = 0;
		for (int i = 0; i < m_CompletionFields.size(); i++) {
			progress += m_CompletionFields.get(m_CompletionFields.keyAt(i)) ? 1 : 0;
		}
		if (progress > 10) {
			progress = 10;
		}
		m_ProgressPercent.setText("" + (progress * 10) + "%");
		m_ProgressBar.setProgress(progress);
	}
	
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
	
	private void editInAviary(Uri source) {
		m_PictureFinal = getTempImageFile();
		if (m_PictureFinal != null) {
			Intent intent = new Intent(this, FeatherActivity.class);
			intent.setData(source);
			intent.putExtra(com.aviary.android.feather.library.Constants.EXTRA_OUTPUT, Uri.fromFile(m_PictureFinal));
			intent.putExtra(com.aviary.android.feather.library.Constants.EXTRA_OUTPUT_FORMAT, Bitmap.CompressFormat.JPEG.name());
			intent.putExtra(com.aviary.android.feather.library.Constants.EXTRA_OUTPUT_QUALITY, 90);
			final DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int max_size = Math.max(metrics.widthPixels, metrics.heightPixels);
			max_size = (int) ((float) max_size / 1.2f);
			intent.putExtra(com.aviary.android.feather.library.Constants.EXTRA_MAX_IMAGE_SIZE, max_size);
			intent.putExtra(com.aviary.android.feather.library.Constants.EXTRA_IN_SAVE_ON_NO_CHANGES, true);
			intent.putExtra(com.aviary.android.feather.library.Constants.EXTRA_IN_API_KEY_SECRET, "a919a54d4af91822");
			startActivityForResult(intent, REQ_CODE_AVIARY);
		}
	}
	
	private void showError(int msgID) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.error)
			.setMessage(msgID)
			.setCancelable(true)
			.setPositiveButton(android.R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
	}
	
	private boolean checkEmptyFields() {
		for (int i = 0; i < m_CompletionFields.size(); i++) {
			if (!m_CompletionFields.get(m_CompletionFields.keyAt(i))) {
				showError(R.string.fieldsNotFilled);
				return false;
			}
		}
		return true;
	}
	
	private boolean validateFields() {
		boolean result = false;
		int errorStringID = 0;
		do {
			if ((m_PictureFinal == null) ||
					(m_Gender.getSelectedItemPosition() == 0) ||
					(m_BirthDateCalendar == null) ||
					(m_City == null) ||
					(m_JobsSpinner.getSelectedItemPosition() == 0))
			{
				errorStringID = R.string.fieldsNotFilled;
				break;
			}
			if ((m_FirstName.length() < 2) || (m_FirstName.length() > 15)) {
				errorStringID = R.string.firstNameValidation;
				break;
			}
			if ((m_LastName.length() < 2) || (m_LastName.length() > 15)) {
				errorStringID = R.string.lastNameValidation;
				break;
			}
			if ((m_UserName.length() < 4) || (m_UserName.length() > 20)) {
				errorStringID = R.string.pseudoValidation;
				break;
			}
			if ((m_Password.length() < 4) || (m_ConfirmPassword.length() < 4)) {
				errorStringID = R.string.minimumPassword;
				break;
			}
			if (!m_Password.getText().toString().equals(m_ConfirmPassword.getText().toString())) {
				errorStringID = R.string.passwordValidationVsConfirm;
				break;
			}
			result = true;
		} while (false);
		if (!result) {
			showError(errorStringID);
		}
		return result;
	}
	
	@Click(R.id.birthDate)
	void birthDatePicker() {
		FragmentManager fm = getSupportFragmentManager();
		DatePickerFragment_ frag = new DatePickerFragment_();
		fm.beginTransaction().replace(R.id.fragmentsContainer, frag).addToBackStack(null).commit();
	}
	
	@Click(R.id.city)
	void cityClick() {
		FragmentManager fm = getSupportFragmentManager();
		SearchCityNameFragment frag = new SearchCityNameFragment();
		fm.beginTransaction().replace(R.id.fragmentsContainer, frag).addToBackStack(null).commit();
	}
	
	@Click(R.id.picturePhoto)
	void pictureClick() {
		FragmentManager fm = getSupportFragmentManager();
		PictureSelectOptionsFragment frag = new PictureSelectOptionsFragment();
		frag.setEditVisiblity(m_PictureFinal != null);
		fm.beginTransaction().replace(R.id.fragmentsContainer, frag).addToBackStack(null).commit();
	}
	
	@Click(R.id.save)
	void saveClick() {
		if (checkEmptyFields() && validateFields()) {
			(new CreateUserTask()).execute();
		}
	}
	
	@Click(R.id.cancel)
	void onCancelClick() {
		finish();
	}
	
	@OnActivityResult(REQ_CODE_TAKE_PIC)
	void takePictureResult(int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			if ((m_PictureFileTemp != null) && m_PictureFileTemp.exists()) {
				m_PictureFileTemp.delete();
				m_PictureFileTemp = null;
			}
		} else {
			editInAviary(Uri.fromFile(m_PictureFileTemp));
		}
	}
	
	@OnActivityResult(REQ_CODE_CHOOSE_PIC)
	void choosePictureResult(int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) return;
		editInAviary(data.getData());
	}
	
	@OnActivityResult(REQ_CODE_AVIARY)
	void aviaryResult(int resultCode, Intent data) {
		if ((m_PictureFileTemp != null) && m_PictureFileTemp.exists()) {
			m_PictureFileTemp.delete();
			m_PictureFileTemp = null;
		}
		if (resultCode != Activity.RESULT_OK) return;
		Bitmap bmp = BitmapFactory.decodeFile(m_PictureFinal.getAbsolutePath());
		m_Picture.setImageBitmap(bmp);
		updateCompletion(R.id.picturePhoto, true);
	}
	
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		ImageView clearButton = null;
		switch (arg0.getId()) {
		case R.id.genderSpinner:
			clearButton = m_GenderClear;
			break;
		case R.id.jobSpinner:
			clearButton = m_JobClear;
			break;
		}
		clearButton.setVisibility(arg2 != 0 ? View.VISIBLE : View.INVISIBLE);
		updateCompletion(arg0.getId(), arg2 != 0);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	public void onDateSelected(GregorianCalendar date) {
		if (date != null) {
			m_BirthDateCalendar = date;
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			m_BirthDate.setText(formatter.format(date.getTime()));
			m_BirthDateClear.setVisibility(View.VISIBLE);
			updateCompletion(m_BirthDate.getId(), true);
		}
	}
	
	@Override
	public void onCitySelected(City city) {
		m_CitySelected = city;
		m_City.setText(city.getName());
		m_CityClear.setVisibility(View.VISIBLE);
		updateCompletion(m_City.getId(), true);
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (!hasFocus) {
			EditText txt = (EditText)v;
			if (((v.getId() == R.id.firstName) || (v.getId() == R.id.lastName)) &&
					(m_FirstName.length() > 0) && (m_LastName.length() > 0) && !m_CompletionFields.get(R.id.userName))
			{
				m_UserName.setText(m_FirstName.getText().toString() + " " + m_LastName.getText().toString());
				m_CompletionFields.put(R.id.userName, true);
			}
			updateCompletion(v.getId(), txt.length() > 0);
		}
	}
	
	@Override
	public void onPictureOptionSelected(int option) {
		Intent intent;
		switch (option) {
		case PictureSelectOptionsFragmentListener.PICTURE_OPTION_TAKE:
			m_PictureFileTemp = getTempImageFile();
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(m_PictureFileTemp));
			startActivityForResult(intent, REQ_CODE_TAKE_PIC);
			break;
		case PictureSelectOptionsFragmentListener.PICTURE_OPTION_CHOOSE:
			intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, REQ_CODE_CHOOSE_PIC);
			break;
		case PictureSelectOptionsFragmentListener.PICTURE_OPTION_EDIT:
			editInAviary(Uri.fromFile(m_PictureFinal));
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		// Empty click handler to inhib user actions on fields when
		// create or update user profile task is running
	}

	private class CreateUserTask extends AsyncTask<Void, Void, SoGoodsAPIManager.APIResponse> {
		@Override
		protected SoGoodsAPIManager.APIResponse doInBackground(Void... params) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			String birthDate = formatter.format(m_BirthDateCalendar.getTime());
			SoGoodsAPIManager.APIResponse result = SoGoodsAPIManager.getInstance().createUser(m_Gender.getSelectedItemId(), m_FirstName.getText().toString(),
					m_LastName.getText().toString(), birthDate, m_EMail.getText().toString(), m_Password.getText().toString(),
					m_CitySelected.getID(), false, "", m_JobsSpinner.getSelectedItemId(), m_UserName.getText().toString(),
					m_ShowUserName.isChecked(), m_ShowBirthDate.isChecked(), m_PictureFinal);
			return result;
		}

		@Override
		protected void onPreExecute() {
			m_FragmentsContainer.setOnClickListener(MyAccountActivity.this);
			m_Loading.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onPostExecute(SoGoodsAPIManager.APIResponse result) {
			m_FragmentsContainer.setOnClickListener(null);
			m_Loading.setVisibility(View.INVISIBLE);
			if (result.isSuccess()) {
				Intent intent = new Intent(MyAccountActivity.this, QuizzActivity.class);
				intent.putExtra("status", 0);
				startActivity(intent);
			} else {
				StringBuilder builder = new StringBuilder();
				builder.append("Results :");
				if (result.getErrors() != null) {
					builder.append("\nerrors = [");
					for (int i = 0; i < result.getErrors().length; i++) {
						if (i != 0) {
							builder.append(", ");
						}
						builder.append("\"").append(result.getErrors()[i]).append("\"");
					}
					builder.append("]");
				}
				if (result.getWarnings() != null) {
					builder.append("\nwarnings = [");
					for (int i = 0; i < result.getWarnings().length; i++) {
						if (i != 0) {
							builder.append(", ");
						}
						builder.append("\"").append(result.getWarnings()[i]).append("\"");
					}
					builder.append("]");
				}
				Toast.makeText(MyAccountActivity.this, builder.toString(), Toast.LENGTH_LONG).show();
			}
		}
		
	}
//	private static final int ACTION_AVIARY_EDITOR = 98;
//	private static final int ACTION_REQUEST_GALLERY = 99;
//	private static final int ACTION_TAKE_PICTURE = 101;
//	private static final int WEBSVC_REQ_ID_CREATE_USER = 0;
//	public static int SEARCH_CITY = 2;
//	public static int SHOW_DIALOG;
//	static ArrayList<String> lsJob;
//	private int STATUS;
//	private int progress = 10;
//	private String apiKey;
//	private APILogin apiLogin;
//	private APIProfile apiProfile;
//	private ImageView avatarImage;
//	private boolean checkUserName;
//	public EditText cityInput;
//	private ImageView clearBirthday;
//	private ImageView clearCity;
//	private ImageView clearConfirmPass;
//	private ImageView clearEmail;
//	private ImageView clearFirstName;
//	private ImageView clearLastName;
//	private ImageView clearPass;
//	private ImageView clearUsername;
//	private Configuration config;
//	public EditText confirmPassInput;
//	private Context context;
//	private DialogWarning dialogWarning;
//	private int displayDOB = 0;
//	private int displayUserName = 0;
//	private ImageView divide;
//	private String dob;
//	public EditText dobInput;
//	private String email;
//	public EditText emailInput;
//	private String firstName;
//	public EditText firstNameInput;
//	private String gender;
//	private String imagePatch = "";
//	private ImageView imageValidate;
//	private ImageView imageValidatePass;
//	private ImageView imageValidateUser;
//	private String job;
//	private String lastName;
//	public EditText lastNameInput;
//	private TextView leftHeaderButton;
//	private ProgressBar mainProgressBar;
//	private PopupWindow mpopup;
//	private String pass;
//	public EditText passInput;
//	private ProfessionDBAdapter professionDB;
//	private Profile profile;
//	private ProgressBar progressBar;
//	private ProgressBar progressBarLoad;
//	private RelativeLayout quizzLayout;
//	private LinearLayout relConfirPass;
//	private TextView rightHeaderButton;
//	private ToggleButton showBirthdayButton;
//	private ToggleButton showUserNameButton;
//	private Spinner spinnerGender;
//	private Spinner spinnerJob;
//	public EditText txtActi;
//	public EditText txtDesign;
//	private TextView txtPercents;
//	public EditText userNameInput;
//	private String username;
//	private HashMap<Integer, Integer> completion = new HashMap<Integer, Integer>();
//	private EditText current = null;
//	private File currentImage = null;
//	private SparseArray<String> professionsList;
//	private SparseArray<String> genderList;
//
//	private File createFolders() {
//		File localFile1;
//		File localFile2;
//
//		localFile1 = Environment
//				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//		localFile2 = new File(localFile1, "aviary");
//		localFile2.mkdirs();
//		// if (Build.VERSION.SDK_INT < 8)
//		// {
//		// localFile1 = Environment.getExternalStorageDirectory();
//		// if (localFile1 != null) {
//		// localFile2 = Environment.getExternalStorageDirectory();
//		// }
//		//
//		// }
//		// // label32:
//		// do
//		// {
//		// // return localFile2;
//		// localFile1 =
//		// Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//		// // break;
//		// localFile2 = new File(localFile1, "aviary");
//		// if(localFile2!=null)
//		// return localFile2;
//		// } while ((localFile2.exists()) || (localFile2.mkdirs()));
//		return Environment.getExternalStorageDirectory();
//	}
//
//	private void goToAriaryEditor(Uri paramUri) {
//		File edited = getTempImageFile();
//		if (edited != null) {
//			Log.d("sogoods", ">> localFile2 not null");
//			this.imagePatch = edited.getAbsolutePath();
//			Intent localIntent = new Intent(this, FeatherActivity.class);
//			localIntent.setData(paramUri);
//			localIntent.putExtra(Constants.EXTRA_OUTPUT,
//					Uri.parse("file://" + this.imagePatch));
//
//			localIntent.putExtra(Constants.EXTRA_OUTPUT_FORMAT,
//					Bitmap.CompressFormat.JPEG.name());
//			localIntent.putExtra(Constants.EXTRA_OUTPUT_QUALITY, 90);
//			final DisplayMetrics metrics = new DisplayMetrics();
//			getWindowManager().getDefaultDisplay().getMetrics(metrics);
//			int max_size = Math.max(metrics.widthPixels, metrics.heightPixels);
//			max_size = (int) ((float) max_size / 1.2f);
//			localIntent.putExtra(Constants.EXTRA_MAX_IMAGE_SIZE, max_size);
//
//			localIntent.putExtra(Constants.EXTRA_IN_SAVE_ON_NO_CHANGES, true);
//
//			localIntent.putExtra(Constants.EXTRA_IN_API_KEY_SECRET,
//					"a919a54d4af91822");
//			startActivityForResult(localIntent, 1);
//		}
//		Log.w("GalleryActivity", "Fail to create a file");
//	}
//
//	private boolean isExternalStorageAvilable() {
//		return "mounted".equals(Environment.getExternalStorageState());
//	}
//
//	private void onLoadProfileContent() {
//		String str = MySharePreference.getProfileContent(this.context);
//		if (!Tools.isEmpty(str)) {
//		}
//		try {
//			this.profile = Profile.initFromJson(new JSONObject(str),
//					this.config.typePhone);
//			Log.w("lkhh", "" + profile.toString());
//		} catch (Exception localException) {
//			localException.printStackTrace();
//		}
//	}
//
//	private File getTempImageFile() {
//		File destDir = getExternalFilesDir("images");
//		if (!destDir.exists()) {
//			destDir.mkdirs();
//		}
//		File destFile = null;
//		try {
//			destFile = File.createTempFile("avatar_", ".jpg", destDir);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return destFile;
//	}
//
//	private void showPopupAddPicture() {
//		View localView = ((LayoutInflater) this.context
//				.getSystemService("layout_inflater")).inflate(
//				R.layout.dialog_add_picture, null);
//		this.mpopup = new PopupWindow(localView, -1, -1, true);
//		this.mpopup.setAnimationStyle(16973826);
//		TextView localTextView1 = (TextView) localView
//				.findViewById(R.id.dialog_addpic_edit_button);
//		localTextView1.setVisibility(imagePatch.equals("") ? View.GONE
//				: View.VISIBLE);
//		TextView localTextView2 = (TextView) localView
//				.findViewById(R.id.dialog_addpic_takepic_button);
//		TextView localTextView3 = (TextView) localView
//				.findViewById(R.id.dialog_addpic_choosepic_button);
//		TextView localTextView4 = (TextView) localView
//				.findViewById(R.id.dialog_addpic_cancel_button);
//		if (this.STATUS == 1) {
//			// localTextView1.setVisibility(0);
//			localTextView1.setOnClickListener(new View.OnClickListener() {
//				public void onClick(View paramAnonymousView) {
//					MyAccountActivity.this.goToAriaryEditor(Uri
//							.fromFile(new File(imagePatch)));
//				}
//			});
//		}
//		localTextView4.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				SEARCH_CITY = 2;
//				mpopup.dismiss();
//			}
//		});
//		localTextView2.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				MyAccountActivity.this.mpopup.dismiss();
//				currentImage = getTempImageFile();
//				if (currentImage != null) {
//					Intent localIntent = new Intent(
//							MediaStore.ACTION_IMAGE_CAPTURE);
//					localIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//							Uri.fromFile(currentImage));
//					MyAccountActivity.this.startActivityForResult(localIntent,
//							101);
//				}
//			}
//		});
//		localTextView3.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				MyAccountActivity.this.mpopup.dismiss();
//				Intent localIntent = new Intent("android.intent.action.PICK",
//						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//				MyAccountActivity.this.startActivityForResult(localIntent, 99);
//			}
//		});
//		this.mpopup.showAtLocation(localView, 80, 0, 0);
//	}
//
//	@SuppressLint("NewApi")
//	private void showProfileContent() {
//		onLoadProfileContent();
//		SEARCH_CITY = 2;
//		int i;
//		Iterator<String> localIterator;
//		if (!Tools.isImageEmpty(context, profile.urlAvatarSmall))
//			avatarImage.setImageBitmap(Tools.getImageFromStorage(context,
//					profile.urlAvatarSmall));
//		// if (!Tools.isEmpty(this.profile.urlHome))
//		// {
//		// if (SGTools.isImageEmpty(this.context, this.profile.urlHome)) {
//		// // TODO: break label587;
//		// }
//		// WeakReference<Bitmap> localWeakReference = new
//		// WeakReference<Bitmap>(SGTools.getImageFromStorage(this.context,
//		// this.profile.urlHome));
//		// if (localWeakReference.get() != null) {
//		// this.avatarImage.setImageBitmap((Bitmap)localWeakReference.get());
//		// }
//		// }
//		// else
//		{
//			if (this.profile.firstName.length() > 0) {
//				this.clearFirstName.setVisibility(0);
//			}
//			this.firstNameInput.setText(this.profile.firstName);
//			if (this.profile.lastName.length() > 0) {
//				this.clearLastName.setVisibility(0);
//			}
//			this.lastNameInput.setText(this.profile.lastName);
//			if (this.profile.username.length() > 0) {
//				this.clearUsername.setVisibility(0);
//			}
//			this.userNameInput.setText(this.profile.username);
//			if (this.profile.dateOfBirth.length() > 0) {
//				this.clearBirthday.setVisibility(0);
//			}
//			this.dobInput.setText(this.profile.dateOfBirth);
//			if (this.profile.email.length() > 0) {
//				this.clearEmail.setVisibility(0);
//			}
//			this.emailInput.setText(this.profile.email);
//			if (this.profile.cityName.length() > 0) {
//				this.clearCity.setVisibility(0);
//			}
//
//			if (this.profile.displayRealName != 0) {
//				this.cityInput.setText(this.profile.cityName);
//			}
//
//			if (this.profile.displayRealBirthday != 0) {
//				this.showUserNameButton.setChecked(false);
//			}
//
//			if (!this.profile.quizzCompletion.equals("100")) {
//				this.showBirthdayButton.setChecked(false);
//			}
//			this.imageValidate.setVisibility(0);
//			// ArrayList<String> localArrayList = new ArrayList<String>();
//			// localArrayList.add(this.profile.gender);
//			// localArrayList.add(this.profile.username);
//			// localArrayList.add(this.profile.firstName);
//			// localArrayList.add(this.profile.lastName);
//			// localArrayList.add(this.profile.dateOfBirth);
//			// localArrayList.add(this.profile.cityName);
//			// localArrayList.add(this.profile.activity);
//			// localArrayList.add(this.profile.email);
//			// localArrayList.add(this.profile.quizzCompletion);
//			// localArrayList.add(this.profile.urlAvatarLarge);
//			// i = 0;
//			// localIterator = localArrayList.iterator();
//			//
//			// //TODO:
//			// // for (;;)
//			// // {
//			// while (localIterator.hasNext())
//			// {
//			// this.progressBarLoad.setProgress(i * 10);
//			// this.txtPercents.setText(i * 10 + " %");
//			// // return;
//			// DownloadImageAsync localDownloadImageAsync1 = new
//			// DownloadImageAsync();
//			// Executor localExecutor1 = AsyncTask.THREAD_POOL_EXECUTOR;
//			// String[] arrayOfString1 = new String[1];
//			// arrayOfString1[0] = this.profile.urlHome;
//			// localDownloadImageAsync1.executeOnExecutor(localExecutor1,
//			// arrayOfString1);
//			//
//			// DownloadImageAsync localDownloadImageAsync2 = new
//			// DownloadImageAsync();
//			// Executor localExecutor2 = AsyncTask.THREAD_POOL_EXECUTOR;
//			// String[] arrayOfString2 = new String[1];
//			// arrayOfString2[0] = this.profile.urlHome;
//			// localDownloadImageAsync2.executeOnExecutor(localExecutor2,
//			// arrayOfString2);
//			//
//			// if (this.profile.displayRealName != 1) {
//			// this.showUserNameButton.setChecked(true);
//			// }
//			//
//			// if (this.profile.displayRealBirthday != 1) {
//			// this.showBirthdayButton.setChecked(true);
//			// }
//			//
//			// this.imageValidate.setVisibility(8);
//			// }
//			// if (!Tools.isEmpty((String)localIterator.next())) {
//			// i++;
//			// }
//			// }
//		}
//	}
//
//	private void updateMedia(String paramString) {
//		MediaScannerConnection.scanFile(getApplicationContext(),
//				new String[] { paramString }, null, null);
//	}
//
//	void creatDialog() {
//		AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//		localBuilder.setCancelable(true);
//		Log.w("befor", "" + SHOW_DIALOG);
//		switch (SHOW_DIALOG) {
//		default:
//			break;
//		case 1:
//			localBuilder.setTitle(getResources().getString(
//					R.string.alphabetOnlyLastName));
//			showDialog(localBuilder);
//			break;
//		case 2:
//			localBuilder.setTitle(getResources().getString(
//					R.string.lastNameValidation));
//			showDialog(localBuilder);
//			break;
//		case 3:
//			localBuilder.setTitle(getResources().getString(
//					R.string.alphabetOnlyFistName));
//			showDialog(localBuilder);
//			break;
//		case 4:
//			localBuilder.setTitle(getResources().getString(
//					R.string.firstNameValidation));
//			showDialog(localBuilder);
//			break;
//		case 5:
//			localBuilder.setTitle(getResources().getString(
//					R.string.pseudoValidation));
//			showDialog(localBuilder);
//			break;
//		case 6:
//			localBuilder.setTitle(getResources().getString(
//					R.string.fieldsNotFilled));
//			showDialog(localBuilder);
//			break;
//		}
//		// localBuilder.setTitle("Username existed");
//		// showDialog(localBuilder);
//	}
//
//	public void init() {
//		genderList = new SparseArray<String>();
//		genderList.put(1, getString(R.string.miss));
//		genderList.put(2, getString(R.string.mister));
//		genderList.put(3, getString(R.string.madam));
//		spinnerGender.setAdapter(new ListsAdapter(genderList, this));
//		spinnerGender.setOnItemSelectedListener(this);
//		// TODO:
//		professionsList = ProfessionUser.initFromCursor(this.professionDB.selectAll());
//		spinnerJob.setAdapter(new ListsAdapter(professionsList, this));
//		spinnerJob.setOnItemSelectedListener(this);
////		new ArrayAdapter<String>(
////				this, android.R.layout.simple_list_item_1, localArrayList);
////		localArrayAdapter1
////				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////		this.spinnerGender.setAdapter(localArrayAdapter1);
////		ArrayAdapter<String> localArrayAdapter2 = new ArrayAdapter<String>(
////				this, android.R.layout.simple_list_item_1,
////				ProfessionUser.initFromCursor(this.professionDB.selectAll()));
////		localArrayAdapter2
////				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////		this.spinnerJob.setAdapter(localArrayAdapter2);
//		if (this.STATUS == 2) {
//			showProfileContent();
//		}
//		this.passInput.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				MyAccountActivity.this.relConfirPass.setVisibility(0);
//				MyAccountActivity.this.divide.setVisibility(0);
//			}
//		});
//		progress = 10;
//		txtPercents.setText("" + progress + " %");
//		progressBarLoad.setProgress(progress);
//	}
//
//	protected void onActivityResult(int paramInt1, int paramInt2,
//			Intent paramIntent) {
//		super.onActivityResult(paramInt1, paramInt2, paramIntent);
//		if (paramInt2 == -1) {
//		}
//
//		// goToAriaryEditor(paramIntent.getData());
//		switch (paramInt1) {
//		case 100:
//			// default:
//			break;
//		case 101:
//			goToAriaryEditor(Uri.fromFile(currentImage));
//			SEARCH_CITY = 2;
//			// Debugs.show("d", "GalleryActivity",
//			// "intent take from camera NULL");
//			break;
//		case 99:
//			if (paramIntent != null) {
//				Log.d("sogoods", ">>> result intent not null");
//				goToAriaryEditor(paramIntent.getData());
//
//			} else {
//				Log.d("sogoods", ">>> result intent null");
//			}
//			SEARCH_CITY = 2;
//			// Debugs.show("d", "GalleryActivity",
//			// "intent choose from Gallery NULL");
//			break;
//		case 1:
//			// String str = "Aviary OK";
//			// str = "Aviary out Failed";
//			// Log.w("lexury", ""+imagePatch);
//			// Bundle localBundle = paramIntent.getExtras();
//			// if (localBundle != null) {
//			// if (!localBundle.getBoolean("bitmap-changed")) {
//			// updateMedia(this.imagePatch);
//			Bitmap localBitmap = BitmapFactory.decodeFile(this.imagePatch);
//			this.avatarImage.setImageBitmap(localBitmap);
//			// }
//			// }
//			// Log.d("GalleryActivity", str);
//
//			break;
//		}
//
//		// TODO: label160:
//
//		// break;
//
//	}
//
//	public void onBackPressed() {
//		switch (SEARCH_CITY) {
//		case 2:
//			super.onBackPressed();
//			break;
//		case 1:
//			// ConstantsHelper.animationPro(this);
//			// //TODO: return;
//			FragmentManager localFragmentManager = getSupportFragmentManager();
//			SearchCityNameFragment localSearchCityNameFragment = (SearchCityNameFragment) getSupportFragmentManager()
//					.findFragmentByTag("TAG_CITY");
//			FragmentTransaction localFragmentTransaction = localFragmentManager
//					.beginTransaction();
//			localFragmentTransaction.hide(localSearchCityNameFragment);
//			localFragmentTransaction.commit();
//			View clearIcon = findViewById(((Integer) cityInput.getTag()).intValue());
//			completion.put(Integer.valueOf(cityInput.getId()), 1);
//			clearIcon.setVisibility(View.VISIBLE);
//			SEARCH_CITY = 2;
//			break;
//		}
//	}
//
//	protected void onCreate(Bundle paramBundle) {
//		super.onCreate(paramBundle);
//		requestWindowFeature(1);
//		setContentView(R.layout.activity_my_account);
//		this.context = this;
//		this.leftHeaderButton = ((TextView) findViewById(R.id.myaccount_header_left_button));
//		this.rightHeaderButton = ((TextView) findViewById(R.id.myaccount_header_right_button));
//		this.avatarImage = ((ImageView) findViewById(R.id.myaccount_avatar_imageview));
//		this.imageValidate = ((ImageView) findViewById(R.id.imageValidate));
//		this.imageValidateUser = ((ImageView) findViewById(R.id.imageValidateUser));
//		this.imageValidatePass = ((ImageView) findViewById(R.id.imageValidatePass));
//		this.progressBar = ((ProgressBar) findViewById(R.id.progressBar1));
//		this.progressBarLoad = ((ProgressBar) findViewById(R.id.progressBarLoad));
//		this.txtPercents = ((TextView) findViewById(R.id.txtPercents));
//		this.relConfirPass = ((LinearLayout) findViewById(R.id.relConfirPass));
//		this.divide = ((ImageView) findViewById(R.id.imageView_logo));
//		this.quizzLayout = ((RelativeLayout) findViewById(R.id.idrequiz));
//		this.mainProgressBar = ((ProgressBar) findViewById(R.id.myaccount_progressbar));
//		this.showUserNameButton = ((ToggleButton) findViewById(R.id.myaccount_toggle_showusername));
//		this.showBirthdayButton = ((ToggleButton) findViewById(R.id.myaccount_toggle_showbirthday));
//		this.firstNameInput = ((EditText) findViewById(R.id.textfirstname));
//		firstNameInput.setTag(Integer.valueOf(R.id.imgClearFirst));
//		this.lastNameInput = ((EditText) findViewById(R.id.textLastname));
//		lastNameInput.setTag(Integer.valueOf(R.id.imgClearLast));
//		this.userNameInput = ((EditText) findViewById(R.id.textUs));
//		userNameInput.setTag(Integer.valueOf(R.id.imgClearUsername));
//		this.dobInput = ((EditText) findViewById(R.id.textbirthday));
//		dobInput.setTag(Integer.valueOf(R.id.imgClearBirdth));
//		this.cityInput = ((EditText) findViewById(R.id.textCity));
//		cityInput.setTag(Integer.valueOf(R.id.imgClearCity));
//		this.emailInput = ((EditText) findViewById(R.id.textEmail));
//		emailInput.setTag(Integer.valueOf(R.id.imgClearEmail));
//		this.passInput = ((EditText) findViewById(R.id.textPw));
//		passInput.setTag(Integer.valueOf(R.id.imgClearPass));
//		this.confirmPassInput = ((EditText) findViewById(R.id.textconfirmPw));
//		confirmPassInput.setTag(Integer.valueOf(R.id.imgClearConfirmPass));
//		this.spinnerGender = ((Spinner) findViewById(R.id.my_account_title));
//		this.spinnerJob = ((Spinner) findViewById(R.id.textDesigner));
//		this.clearLastName = ((ImageView) findViewById(R.id.imgClearLast));
//		this.clearFirstName = ((ImageView) findViewById(R.id.imgClearFirst));
//		this.clearUsername = ((ImageView) findViewById(R.id.imgClearUsername));
//		this.clearBirthday = ((ImageView) findViewById(R.id.imgClearBirdth));
//		this.clearCity = ((ImageView) findViewById(R.id.imgClearCity));
//		this.clearEmail = ((ImageView) findViewById(R.id.imgClearEmail));
//		this.clearPass = ((ImageView) findViewById(R.id.imgClearPass));
//		this.clearConfirmPass = ((ImageView) findViewById(R.id.imgClearConfirmPass));
//		this.apiProfile = new APIProfile();
//		this.apiLogin = new APILogin();
//		this.dialogWarning = new DialogWarning();
//		this.apiKey = MySharePreference.getApiKey(this.context);
//		this.config = Configuration.getInstance(this.context);
//		this.professionDB = DBManager.getProfessionDBAdapter(this.context);
//		lsJob = new ArrayList<String>();
//		Bundle localBundle = getIntent().getExtras();
//		if ((localBundle != null) && (localBundle.containsKey("status"))) {
//
//			this.STATUS = localBundle.getInt("status");
//			Log.w("status", "" + this.STATUS);
//			if (localBundle.containsKey("email")) {
//				String str1 = localBundle.getString("email");
//				String str2 = localBundle.getString("password");
//				this.emailInput.setText(str1);
//				this.emailInput.setClickable(false);
//				this.emailInput.setCursorVisible(false);
//				this.emailInput.setFocusable(false);
//				this.passInput.setText(str2);
//				this.passInput.setClickable(false);
//				this.passInput.setCursorVisible(false);
//				this.passInput.setFocusable(false);
//				this.relConfirPass.setVisibility(0);
//				this.divide.setVisibility(0);
//				this.rightHeaderButton
//						.setText(getString(R.string.createProduct));
//				this.quizzLayout.setVisibility(8);
//			}
//		}
//		init();
//		this.leftHeaderButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				MyAccountActivity.this.onBackPressed();
//			}
//		});
//		this.rightHeaderButton.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				MyAccountActivity.this.process();
//			}
//		});
//		this.avatarImage.setOnClickListener(new View.OnClickListener() {
//			public void onClick(View paramAnonymousView) {
//				MyAccountActivity.SEARCH_CITY = 3;
//				MyAccountActivity.this.showPopupAddPicture();
//			}
//		});
//		this.firstNameInput.addTextChangedListener(this);
//		this.firstNameInput.setOnFocusChangeListener(this);
//		this.lastNameInput.addTextChangedListener(this);
//		this.lastNameInput.setOnFocusChangeListener(this);
//		this.userNameInput.addTextChangedListener(this);
//		this.userNameInput.setOnFocusChangeListener(this);
//		this.dobInput.addTextChangedListener(this);
//		this.dobInput.setOnFocusChangeListener(this);
//		this.cityInput.addTextChangedListener(this);
//		this.cityInput.setOnFocusChangeListener(this);
//		this.emailInput.addTextChangedListener(this);
//		this.emailInput.setOnFocusChangeListener(this);
//		this.passInput.addTextChangedListener(this);
//		this.passInput.setOnFocusChangeListener(this);
//		this.confirmPassInput.addTextChangedListener(this);
//		this.confirmPassInput.setOnFocusChangeListener(this);
//
//		completion.put(Integer.valueOf(R.id.textEmail), Integer.valueOf(1));
//		completion.put(Integer.valueOf(R.id.textPw), Integer.valueOf(1));
//	}
//
//	protected void process() {
//		this.lastName = this.lastNameInput.getText().toString();
//		this.firstName = this.firstNameInput.getText().toString();
//		this.username = this.userNameInput.getText().toString();
//		this.dob = this.dobInput.getText().toString();
//		this.email = this.emailInput.getText().toString();
//		this.pass = this.passInput.getText().toString();
//		this.gender = this.spinnerGender.getSelectedItem().toString();
//		//this.job = this.spinnerJob.getSelectedItem().toString();
//
//		if (this.lastName.length() == 0) {
//			SHOW_DIALOG = 1;
//			creatDialog();
//			return;
//		}
//		if ((this.lastName.length() < 2) && (this.firstName.length() > 15)) {
//			SHOW_DIALOG = 2;
//			creatDialog();
//			return;
//		}
//		if (this.firstName.length() == 0) {
//			SHOW_DIALOG = 3;
//			creatDialog();
//			return;
//		}
//		if ((this.firstName.length() < 2) && (this.firstName.length() > 15)) {
//			SHOW_DIALOG = 4;
//			creatDialog();
//			return;
//		}
//		if ((this.username.length() < 4) && (this.username.length() > 15)) {
//			SHOW_DIALOG = 5;
//			creatDialog();
//			return;
//		}
//		if ((Tools.isEmpty(this.lastName)) || (Tools.isEmpty(this.firstName))
//				|| (Tools.isEmpty(this.username)) || (Tools.isEmpty(this.dob))) {
//			SHOW_DIALOG = 6;
//			creatDialog();
//			return;
//		}
//		// if (!this.checkUserName)
//		// {
//		// SHOW_DIALOG = 7;
//		// creatDialog();
//		// return;
//		// }
//		if (this.showUserNameButton.isChecked()) {
//			this.displayUserName = 1;
//
//		} else
//			this.displayUserName = 0;
//
//		if (!this.showBirthdayButton.isChecked()) {
//			this.displayDOB = 0;
//		} else
//			this.displayDOB = 1;
//		// TODO: label392:
//		// for ( )
//		// {
//		if (this.STATUS == 1) {
////			WebservicesAPIManager.getInstance().createUser(spinnerGender.getSelectedItemId(), firstNameInput.getText().toString(),
////					lastNameInput.getText().toString(), dobInput.getText().toString(), emailInput.getText().toString(),
////					passInput.getText().toString(), SearchCityNameFragment.CITY_ID, false, "", spinnerJob.getSelectedItemId(),
////					userNameInput.getText().toString(), showUserNameButton.isChecked(), showBirthdayButton.isChecked(),
////					new File(imagePatch), this, WEBSVC_REQ_ID_CREATE_USER);
//
//		} else {
//
//			new UpdateProfileAsync().executeOnExecutor(
//					AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
//		}
//		// TODO: return;
//
//		// break;
//		// }
//		// TODO: label400:
//
//	}
//
//	public void processClearBirthday(View paramView) {
//		this.dobInput.setText("");
//		this.clearBirthday.setVisibility(8);
//	}
//
//	public void processClearCity(View paramView) {
//		this.cityInput.setText("");
//		this.clearCity.setVisibility(8);
//	}
//
//	public void processClearConfirmPass(View paramView) {
//		this.confirmPassInput.setText("");
//		this.clearConfirmPass.setVisibility(8);
//		this.imageValidatePass.setVisibility(8);
//	}
//
//	public void processClearEmail(View paramView) {
//		this.emailInput.setText("");
//		this.clearEmail.setVisibility(8);
//	}
//
//	public void processClearFirst(View paramView) {
//		this.firstNameInput.setText("");
//		this.clearFirstName.setVisibility(8);
//	}
//
//	public void processClearLast(View paramView) {
//		this.lastNameInput.setText("");
//		this.clearLastName.setVisibility(8);
//	}
//
//	public void processClearPass(View paramView) {
//		this.passInput.setText("");
//		this.clearPass.setVisibility(8);
//	}
//
//	public void processClearUsername(View paramView) {
//		this.userNameInput.setText("");
//		this.clearUsername.setVisibility(8);
//		this.imageValidateUser.setVisibility(8);
//	}
//
//	public void processClickChooseCity(View paramView) {
//		SEARCH_CITY = 1;
//		FragmentTransaction localFragmentTransaction = getSupportFragmentManager()
//				.beginTransaction();
//		SearchCityNameFragment localSearchCityNameFragment = new SearchCityNameFragment();
//		Bundle localBundle = new Bundle();
//		localBundle.putInt("source", 1);
//		localSearchCityNameFragment.setArguments(localBundle);
//		localFragmentTransaction.replace(R.id.search_container_fragment,
//				localSearchCityNameFragment, "TAG_CITY");
//		localFragmentTransaction.commit();
//	}
//
//	// TODO:verifier QuizzActivity code
//	public void processMyQuizz(View paramView) {
//		Intent localIntent = new Intent(getApplicationContext(),
//				QuizzActivity.class);
//		localIntent.putExtra("status", this.STATUS);
//		startActivity(localIntent);
//		ConstantsHelper.animationPro(this);
//	}
//
//	public void setDate(int paramInt1, int paramInt2, int paramInt3) {
//		String str = Integer.toString(paramInt1) + "-"
//				+ Integer.toString(paramInt2 + 1) + "-"
//				+ Integer.toString(paramInt3);
//		this.dobInput.setText(str);
//		View clearIcon = findViewById(((Integer) dobInput.getTag()).intValue());
//		completion.put(Integer.valueOf(dobInput.getId()), 1);
//		clearIcon.setVisibility(View.VISIBLE);
//	}
//
//	public void showDatePickerDialog(View paramView) {
//		new DatePickerDialogFragment().show(getSupportFragmentManager(),
//				"datePicker");
//	}
//
//	void showDialog(AlertDialog.Builder paramBuilder) {
//		paramBuilder.setInverseBackgroundForced(true);
//		paramBuilder.setPositiveButton(getResources().getString(2131165330),
//				new DialogInterface.OnClickListener() {
//					public void onClick(
//							DialogInterface paramAnonymousDialogInterface,
//							int paramAnonymousInt) {
//						paramAnonymousDialogInterface.dismiss();
//					}
//				});
//		paramBuilder.create().show();
//	}
//
//	private class CheckNickNameAsync extends AsyncTask<Void, Void, Boolean> {
//		private CheckNickNameAsync() {
//		}
//
//		protected Boolean doInBackground(Void... paramVarArgs) {
//			return Boolean.valueOf(MyAccountActivity.this.apiProfile
//					.checkUserNameExisted(MyAccountActivity.this.userNameInput
//							.getText().toString()));
//		}
//
//		protected void onPostExecute(Boolean paramBoolean) {
//			super.onPostExecute(paramBoolean);
//			MyAccountActivity localMyAccountActivity = MyAccountActivity.this;
//			if (paramBoolean.booleanValue()) {
//			}
//			for (boolean bool = false;; bool = true) {
//				localMyAccountActivity.checkUserName = bool;
//				if (!paramBoolean.booleanValue()) {
//					break;
//				}
//				MyAccountActivity.this.imageValidateUser.setVisibility(0);
//				MyAccountActivity.this.imageValidateUser
//						.setImageResource(2130837713);
//				return;
//			}
//			MyAccountActivity.this.imageValidateUser.setVisibility(0);
//			MyAccountActivity.this.imageValidateUser
//					.setImageResource(2130837712);
//		}
//
//		protected void onPreExecute() {
//			super.onPreExecute();
//			MyAccountActivity.this.checkUserName = false;
//		}
//	}
//
//	private class CreateProfileAsync extends AsyncTask<Void, Void, Boolean> {
//		private CreateProfileAsync() {
//		}
//
//		protected Boolean doInBackground(Void... paramVarArgs) {
////			return Boolean.valueOf(apiLogin.createUser(
////					MyAccountActivity.this.firstName,
////					MyAccountActivity.this.lastName,
////					MyAccountActivity.this.dob, MyAccountActivity.this.gender,
////					MyAccountActivity.this.email, MyAccountActivity.this.pass,
////					SearchCityNameFragment.CITY_ID, MyAccountActivity.this.job,
////					MyAccountActivity.this.username,
////					MyAccountActivity.this.imagePatch,
////					MyAccountActivity.this.displayUserName,
////					MyAccountActivity.this.displayDOB,
////					MyAccountActivity.this.imagePatch,
////					MyAccountActivity.this.imagePatch,
////					MyAccountActivity.this.config.currentLanguage));
//			return Boolean.valueOf(false);
//		}
//
//		protected void onPostExecute(Boolean paramBoolean) {
//			super.onPostExecute(paramBoolean);
//			if (paramBoolean.booleanValue()) {
//				Intent localIntent = new Intent(
//						MyAccountActivity.this.getApplicationContext(),
//						QuizzActivity.class);
//				localIntent.putExtra("status", 0);
//				MyAccountActivity.this.startActivity(localIntent);
//				ConstantsHelper.animationPro(MyAccountActivity.this);
//			}
//			// for (;;)
//			// {
//			MyAccountActivity.this.rightHeaderButton.setClickable(true);
//			MyAccountActivity.this.mainProgressBar.setVisibility(8);
//			// TODO: return;
//			// MyAccountActivity.this.dialogWarning.show(MyAccountActivity.this.context,
//			// MyAccountActivity.this.getString(2131165784), "", false);
//			// }
//		}
//
//		protected void onPreExecute() {
//			super.onPreExecute();
//			MyAccountActivity.this.rightHeaderButton.setClickable(false);
//			MyAccountActivity.this.mainProgressBar.setVisibility(0);
//		}
//	}
//
//	private class DownloadImageAsync extends AsyncTask<String, Void, Boolean> {
//		private String url;
//
//		private DownloadImageAsync() {
//		}
//
//		protected Boolean doInBackground(String... paramVarArgs) {
//			this.url = paramVarArgs[0];
//			return Boolean.valueOf(SGTools.DownLoadImage(
//					MyAccountActivity.this.context, this.url));
//		}
//
//		protected void onPostExecute(Boolean paramBoolean) {
//			if (paramBoolean.booleanValue()) {
//				MyAccountActivity.this.avatarImage.setImageBitmap(SGTools
//						.getImageFromStorage(MyAccountActivity.this.context,
//								this.url));
//			}
//			// for (;;)
//			// {
//			MyAccountActivity.this.progressBar.setVisibility(8);
//			// TODO: return;
//			Debugs.show("w", "LoadAvartarImage",
//					"Download Avatar for user have problem");
//			// }
//		}
//
//		protected void onPreExecute() {
//			super.onPreExecute();
//			MyAccountActivity.this.progressBar.setVisibility(0);
//		}
//	}
//
//	private class UpdateProfileAsync extends AsyncTask<Void, Void, String> {
//		private UpdateProfileAsync() {
//		}
//
//		protected String doInBackground(Void... paramVarArgs) {
////			return MyAccountActivity.this.apiProfile.updateProfile(
////					MyAccountActivity.this.firstNameInput.getText().toString(),
////					MyAccountActivity.this.lastNameInput.getText().toString(),
////					MyAccountActivity.this.dobInput.getText().toString(),
////					MyAccountActivity.this.spinnerGender.getSelectedItem()
////							.toString(), MyAccountActivity.this.emailInput
////							.getText().toString(),
////					SearchCityNameFragment.CITY_ID,
////					MyAccountActivity.this.spinnerJob.getSelectedItem()
////							.toString(), MyAccountActivity.this.userNameInput
////							.getText().toString(), "",
////					MyAccountActivity.this.displayUserName,
////					MyAccountActivity.this.displayDOB, "", "",
////					MyAccountActivity.this.apiKey);
//			return "";
//		}
//
//		protected void onPostExecute(String paramString) {
//			super.onPostExecute(paramString);
//			try {
//				JSONObject localJSONObject = new JSONObject(paramString);
//				if (localJSONObject.getBoolean("success")) {
//					// MyAccountActivity.this.dialogWarning.show(MyAccountActivity.this.context,
//					// MyAccountActivity.this.getString(2131165465), "", true);
//				}
//				// for (;;)
//				// {
//				MyAccountActivity.this.rightHeaderButton.setClickable(true);
//				MyAccountActivity.this.mainProgressBar.setVisibility(8);
//				// TODO: return;
//				// MyAccountActivity.this.dialogWarning.show(MyAccountActivity.this.context,
//				// localJSONObject.getJSONArray("errors").getString(0), "",
//				// false);
//				// }
//			} catch (Exception localException) {
//				localException.printStackTrace();
//			}
//		}
//
//		protected void onPreExecute() {
//			super.onPreExecute();
//			MyAccountActivity.this.rightHeaderButton.setClickable(false);
//			MyAccountActivity.this.mainProgressBar.setVisibility(0);
//		}
//	}
//
//	@Override
//	public void afterTextChanged(Editable s) {
//	}
//
//	@Override
//	public void beforeTextChanged(CharSequence s, int start, int count,
//			int after) {
//	}
//
//	@Override
//	public void onTextChanged(CharSequence s, int start, int before, int count) {
//		if (current != null) {
//			View v = findViewById(((Integer) current.getTag()).intValue());
//			completion.put(Integer.valueOf(current.getId()),
//					Integer.valueOf((s.length() > 0) ? 1 : 0));
//			v.setVisibility((s.length() > 0) ? View.VISIBLE : View.INVISIBLE);
//		}
//	}
//
//	private void updateFillProgress() {
//		Set<Integer> keys = completion.keySet();
//		Iterator<Integer> iter = keys.iterator();
//		Integer key;
//		int pwd = 0;
//		int pwdConfirm = 0;
//		int internalProgress = 0;
//		while (iter.hasNext()) {
//			key = iter.next();
//			if (key.intValue() == R.id.textPw) {
//				pwd = completion.get(key).intValue();
//			} else if (key.intValue() == R.id.textconfirmPw) {
//				pwdConfirm = completion.get(key).intValue();
//			}
//			internalProgress += completion.get(key).intValue();
//		}
//		if ((pwd == 1) || (pwdConfirm == 1)) {
//			internalProgress -= 1;
//		}
//		txtPercents.setText("" + (internalProgress * 10) + " %");
//		progressBarLoad.setProgress(internalProgress * 10);
//	}
//	
//	@Override
//	public void onFocusChange(View v, boolean hasFocus) {
//		if (hasFocus) {
//			switch (v.getId()) {
//			case R.id.textfirstname:
//			case R.id.textLastname:
//			case R.id.textUs:
//			case R.id.textbirthday:
//			case R.id.textCity:
//			case R.id.textEmail:
//			case R.id.textPw:
//			case R.id.textconfirmPw:
//				current = (EditText) v;
//				break;
//			default:
//				current = null;
//			}
//		} else {
//			if (((v.getId() == R.id.textfirstname) || (v.getId() == R.id.textLastname)) && (userNameInput.getText().length() <= 0) &&
//					(firstNameInput.getText().length() > 0) && (lastNameInput.getText().length() > 0)) {
//				userNameInput.setText(firstNameInput.getText() + " " + lastNameInput.getText());
//				View clearIcon = findViewById(((Integer) userNameInput.getTag()).intValue());
//				completion.put(Integer.valueOf(userNameInput.getId()), 1);
//				clearIcon.setVisibility(View.VISIBLE);
//			}
//			updateFillProgress();
//			current = null;
//		}
//	}
//	
//	@Override
//	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
//			long arg3) {
//		int clearIconID = 0;
//		switch (arg0.getId()) {
//		case R.id.my_account_title:
//			clearIconID = R.id.imgClearGender;
//			break;
//		case R.id.textDesigner:
//			clearIconID = R.id.imgClearJob;
//			break;
//		}
//		if (clearIconID != 0) {
//			View clearIcon = findViewById(clearIconID);
//			clearIcon.setVisibility(arg2 != 0 ? View.VISIBLE : View.INVISIBLE);
//			completion.put(Integer.valueOf(arg0.getId()),
//					Integer.valueOf(arg2 != 0 ? 1 : 0));
//			updateFillProgress();
//		}
//	}
//
//	@Override
//	public void onNothingSelected(AdapterView<?> arg0) {
//	}
//	
//	public void processClear(View v) {
//		switch (v.getId()) {
//		case R.id.imgClearGender:
//			spinnerGender.setSelection(0);
//			break;
//		case R.id.imgClearJob:
//			spinnerJob.setSelection(0);
//			break;
//		}
//	}
//
//	@Override
//	public void onResponseReceived(int reqID, JSONObject response) {
//		// TODO Auto-generated method stub
//		
//	}
}
