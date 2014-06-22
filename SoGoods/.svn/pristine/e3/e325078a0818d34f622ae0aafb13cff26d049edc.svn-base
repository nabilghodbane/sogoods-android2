package com.hitechno.sogoods;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hitechno.sogoods.api.APILogin;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.databases.JobDB;
import com.hitechno.sogoods.managers.SoGoodsAPIManager;
import com.hitechno.sogoods.model.Job;
import com.hitechno.sogoods.util.Checker;

public class CreateAccountActivity extends FragmentActivity {
	private APILogin apiLogin;
	private TextView backButton;
	private ImageView clearEmail;
	private ImageView clearPass;
	private Context context;
	private boolean emailEnable;
	private ProgressBar emailProgress;
	private EditText inputEmail;
	private EditText inputPass;
	private RelativeLayout signUpButton;
	private ImageView statusEmail;
	private ImageView statusPass;

	private boolean validateInput() {
		String str1 = this.inputEmail.getText().toString();
		String str2 = this.inputPass.getText().toString();
		return (str1 != null) && (str1.length() > 4) && (str2 != null)
				&& (str2.length() >= 4)
				&& (Checker.isValidEmail(str1).booleanValue())
				&& (!this.emailEnable);
	}

	@SuppressLint("NewApi")
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_create_account);
		this.context = this;
		this.backButton = ((TextView) findViewById(R.id.signup_back_button));
		this.inputEmail = ((EditText) findViewById(R.id.signup_email));
		this.inputPass = ((EditText) findViewById(R.id.signup_password));
		this.clearEmail = ((ImageView) findViewById(R.id.signup_email_clear));
		this.clearPass = ((ImageView) findViewById(R.id.signup_password_clear));
		this.statusEmail = ((ImageView) findViewById(R.id.signup_email_status));
		this.statusPass = ((ImageView) findViewById(R.id.signup_password_status));
		this.signUpButton = ((RelativeLayout) findViewById(R.id.signup_signup_button));
		this.emailProgress = ((ProgressBar) findViewById(R.id.signup_email_status_progress));
		this.apiLogin = new APILogin();
		this.inputEmail
				.setOnFocusChangeListener(new View.OnFocusChangeListener() {
					public void onFocusChange(View paramAnonymousView,
							boolean paramAnonymousBoolean) {
						String str = CreateAccountActivity.this.inputEmail
								.getText().toString();

						if (!paramAnonymousBoolean) {
							if ((str.length() > 4)
									&& (Checker.isValidEmail(str)
											.booleanValue())) {
								new CreateAccountActivity.checkEmailExistAsync()
										.executeOnExecutor(
												AsyncTask.THREAD_POOL_EXECUTOR,
												inputEmail.getText().toString());
							} else {
								CreateAccountActivity.this.statusEmail
										.setVisibility(0);
								CreateAccountActivity.this.statusEmail
										.setBackgroundResource(R.drawable.loginx);
							}
						} else {
							return;
						}

					}
				});
		this.inputEmail.addTextChangedListener(new TextWatcher() {
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
				if (paramAnonymousCharSequence.length() > 0) {
					clearEmail.setVisibility(0);
				}
				if (paramAnonymousCharSequence.length() > 4) {
					if (Checker.isValidEmail(
							paramAnonymousCharSequence.toString())
							.booleanValue()) {
						CreateAccountActivity.this.statusEmail
								.setBackgroundResource(R.drawable.login_check);
						statusEmail.setVisibility(View.VISIBLE);
					} else {
						CreateAccountActivity.this.statusEmail.setVisibility(4);
						return;
					}
				}
				// CreateAccountActivity.this.statusEmail.setVisibility(4);
			}
		});
		this.inputPass.addTextChangedListener(new TextWatcher() {
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
				if (paramAnonymousCharSequence.toString().length() > 0) {
					CreateAccountActivity.this.clearPass.setVisibility(0);
				}
				if (paramAnonymousCharSequence.length() >= 4) {
					CreateAccountActivity.this.statusPass
							.setBackgroundResource(R.drawable.login_check);
					statusPass.setVisibility(View.VISIBLE);

				} else
					statusPass.setVisibility(View.INVISIBLE);
			}
		});
		this.clearEmail.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				CreateAccountActivity.this.inputEmail.setText("");
				CreateAccountActivity.this.clearEmail.setVisibility(8);
			}
		});
		this.clearPass.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				CreateAccountActivity.this.inputPass.setText("");
				CreateAccountActivity.this.clearPass.setVisibility(8);
			}
		});
		this.signUpButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				if (CreateAccountActivity.this.validateInput()) {
					(new JobsRetrieve()).execute();
				}

			}
		});
		this.backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				CreateAccountActivity.this.onBackPressed();
				CreateAccountActivity.this.finish();
			}
		});
	}

	private class JobsRetrieve extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			Job[] jobs = SoGoodsAPIManager.getInstance().getJobs(Locale.getDefault().getLanguage());
			if (jobs != null) {
				JobDB.getInstance().fillDB(jobs, Locale.getDefault().getLanguage());
				}
			return Boolean.valueOf(jobs != null);
			}
		@Override
		protected void onPostExecute(Boolean result) {
			View pbLoading = findViewById(R.id.pbSignUpLoading);
			pbLoading.setVisibility(View.INVISIBLE);
			if (result.booleanValue()) {
				Intent intent = new Intent(CreateAccountActivity.this, MyAccountActivity_.class);
				intent.putExtra(MyAccountActivity.EXTRA_ACTION, MyAccountActivity.ACTION_CREATE);
				intent.putExtra(MyAccountActivity.EXTRA_CREATE_EMAIL, inputEmail.getText().toString());
				intent.putExtra(MyAccountActivity.EXTRA_CREATE_PASSWORD, inputPass.getText().toString());
				startActivity(intent);
			}
		}

		@Override
		protected void onPreExecute() {
			View pbLoading = findViewById(R.id.pbSignUpLoading);
			pbLoading.setVisibility(View.VISIBLE);
		}
	}
	
	private class checkEmailExistAsync extends AsyncTask<String, Void, Boolean> {
		private checkEmailExistAsync() {
		}

		protected Boolean doInBackground(String... paramVarArgs) {
			return Boolean.valueOf(SoGoodsAPIManager.getInstance().isEmailUsed(paramVarArgs[0]));

		}

		protected void onPostExecute(Boolean paramBoolean) {
			super.onPostExecute(paramBoolean);
			CreateAccountActivity.this.emailProgress.setVisibility(8);
			CreateAccountActivity.this.statusEmail.setVisibility(0);
			CreateAccountActivity.this.signUpButton.setClickable(true);
			CreateAccountActivity.this.emailEnable = paramBoolean
					.booleanValue();
			if (paramBoolean.booleanValue()) {
				CreateAccountActivity.this.statusEmail
						.setBackgroundResource(R.drawable.loginx);
				signUpButton.setEnabled(false);
			} else {
				CreateAccountActivity.this.statusEmail
						.setBackgroundResource(R.drawable.login_check);
				if (inputPass.getText().length() >= 4)
					signUpButton.setEnabled(true);
			}
		}

		protected void onPreExecute() {
			super.onPreExecute();
			CreateAccountActivity.this.statusEmail.setVisibility(8);
			CreateAccountActivity.this.emailProgress.setVisibility(0);
			CreateAccountActivity.this.signUpButton.setClickable(false);
		}
	}
}
