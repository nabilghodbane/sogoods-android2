package com.hitechno.sogoods;

import org.json.JSONArray;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.api.APILogin;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Checker;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;
import com.mttam.toollibrary.tools.Tools;

public class LoginActivity extends FragmentActivity {

	private EditText inputEmail, inputPassWord;
	private ImageView checkEmail, checkPassWord, logoImage;
	private TextView forgotButton;
	private TextView errorText;
	private LinearLayout signupButton;
	private Button loginButton;
	private ProgressBar progressBar;
	private String email;
	private String pass;
	private String lang;
	private Context context;
	private Configuration config;
	private FragmentActivity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		activity= this;
		context = this;
		config = Configuration.getInstance(context);

		inputEmail = (EditText) findViewById(R.id.login_email);
		inputPassWord = (EditText) findViewById(R.id.login_password);
		forgotButton = (TextView) findViewById(R.id.login_forgotpassword_button);
		errorText = (TextView) findViewById(R.id.login_error);
		signupButton = (LinearLayout) findViewById(R.id.login_signup_button);
		loginButton = (Button) findViewById(R.id.login_button);
		progressBar = (ProgressBar) findViewById(R.id.signin_progress);
		checkEmail = (ImageView) findViewById(R.id.login_email_status);
		checkPassWord = (ImageView) findViewById(R.id.login_password_status);
		logoImage = (ImageView)findViewById(R.id.imglogo);

		errorText.setVisibility(View.INVISIBLE);
		lang = config.currentLanguage;
		email = MySharePreference.getUserName(context);
		if (Tools.isEmpty(email)) {
			inputEmail.setText(email);
		}
		//TODO use the fragments ( login , signup1, signup2 )
		forgotButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//TODO
			}
		});

		signupButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, CreateAccountActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				 ConstantsHelper.animationPro(activity);
			}
		});

		loginButton.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				if (checkInput()) {
					email = inputEmail.getText().toString();
					pass = inputPassWord.getText().toString();
					new LoginAsync()
							.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				} else {

				}
			}
		});

		inputEmail.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() > ConstantsHelper.VALID_INPUT_LENGTH) {
					if (Checker.isValidEmail(s.toString())) {
						checkEmail.setVisibility(View.VISIBLE);
					} else {
						checkEmail.setVisibility(View.INVISIBLE);
					}
				} else {
					checkEmail.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		inputPassWord.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() >= ConstantsHelper.VALID_INPUT_LENGTH) {
					checkPassWord.setVisibility(View.VISIBLE);
				} else {
					checkPassWord.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

	}

	private void enableAllButton(boolean enable) {
		forgotButton.setEnabled(enable);
		signupButton.setEnabled(enable);
		loginButton.setEnabled(enable);
		forgotButton.setClickable(enable);
		signupButton.setClickable(enable);
		loginButton.setClickable(enable);
	}

	private boolean checkInput() {
		return (!Tools.isEmpty(inputEmail) && !Tools.isEmpty(inputPassWord));
	}

	@Override
	public void onResume() {
		super.onResume();
		// showFragment(fragments[0]);
	}

	// public Fragment[] getFragments() {
	// return this.fragments;
	// }

	public void showFragment(Fragment fragment) {
		// FragmentManager fragmentManager = getSupportFragmentManager();
		// FragmentTransaction fragmentTransaction = fragmentManager
		// .beginTransaction();
		//
		// for (int i = 0; i < 1; i++) {
		// if (fragment == fragments[i]) {
		// fragmentTransaction.show(fragment);
		// } else {
		// fragmentTransaction.hide(fragments[i]);
		// }
		// }
		// fragmentTransaction.commit();
	}

	/**
	 * @see com.hitechno.sogoods.delegates.RestResponseDelegate#respondUsingUrlAndModel(String,
	 *      String)
	 */

	public void didReceiveResponseWithModelId(final String modelId,
			final String apikey, String nameUser, String avatar) {

		if (modelId == "nullvalue") {
			showAlertDialog("Username or password not correctly");
		} else {
			Intent intent = new Intent(this, HomeActivity.class);
			intent.putExtra(ConstantsHelper.Sessions.SESSION_ID,
					Integer.parseInt(modelId));
			intent.putExtra(ConstantsHelper.Home.API_KEY, apikey);
			intent.putExtra(ConstantsHelper.Home.ID_PROFILE, modelId);
			intent.putExtra(ConstantsHelper.Home.USERNAME_PROFILE, nameUser);
			intent.putExtra(ConstantsHelper.Home.AVATAR_PROFILE, avatar);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	}

	private void showAlertDialog(String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Warning");
		alertDialog.setMessage(message);
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alertDialog.show();
	}

	private class LoginAsync extends AsyncTask<Void, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			enableAllButton(false);
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(Void... params) {
			APILogin api = new APILogin();
			return api.login(email, pass, lang);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			enableAllButton(true);
			progressBar.setVisibility(View.GONE);
			if (result != null) {
				try {
					JSONObject json = new JSONObject(result);
					boolean success = json.getBoolean("success");
					if (success) {
						// Save username and pass
						MySharePreference.saveUserName(context, email);
						MySharePreference.savePass(context, pass);
						// Save Profile of user
						JSONObject profil = json.getJSONObject("profil");
						MySharePreference.saveProfileContent(context,
								profil.toString());
						// Save apikey of user
						String apikey = json.getString("apikey");
						MySharePreference.saveAPIKEY(context, apikey);
						config.apiKey = apikey;
						// Save Profile ID
						Profile profile = Profile.initFromJson(profil,
								config.typePhone);
						MySharePreference.saveProfileID(context,
								profile.profileID);
						config.idprofile = profile.profileID;
						// Go to Home Page
						Intent intent = new Intent(context, HomeActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					} else {
						JSONArray error = json.getJSONArray("errors");
						String warning = error.getString(0);
						//showAlertDialog(warning);
						logoImage.setVisibility(View.INVISIBLE);
						errorText.setText(warning);
						errorText.setVisibility(View.VISIBLE);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}