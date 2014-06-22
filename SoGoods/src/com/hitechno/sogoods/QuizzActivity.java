package com.hitechno.sogoods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mttam.toollibrary.tools.Tools;
import com.hitechno.sogoods.adapters.QuestionImageAdapter;
import com.hitechno.sogoods.adapters.QuestionTextAdapter;
import com.hitechno.sogoods.api.APIQuestion;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.models.Answer;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.models.Question;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.DialogWarning;
import com.hitechno.sogoods.util.MySharePreference;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("NewApi")
public class QuizzActivity extends FragmentActivity {
	private String apiKey;
	private APIQuestion apiQuestion;
	private Configuration config;
	private Context context;
	private int current = 0;
	private DialogWarning dialogWarning;
	private GridView gridView;
	private boolean isChange = false;
	private TextView leftMenuButton;
	private ListView listView;
	private PopupWindow mpopup;
	private TextView percentLabel;
	private ProgressBar processBar;
	private Profile profile;
	private ProgressBar progressBar;
	private TextView questionLabel;
	private TextView questionNumber;
	private ArrayList<Question> questions;
	private TextView rightMenuButton;
	private int status;
	private SparseIntArray userAnswers;

	private String convertArrayToString() {
		try {
			JSONObject localJSONObject = new JSONObject();
			for (int i = 0;; i++) {
				if (i >= this.userAnswers.size()) {
					return localJSONObject.toString();
				}
				localJSONObject.put(String.valueOf(this.userAnswers.keyAt(i)),
						this.userAnswers.valueAt(i));
			}

		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
		return null;
	}

	private void displayQuestion(final Question paramQuestion) {
		if (this.current > 0) {
			this.leftMenuButton.setVisibility(0);
			this.questionLabel.setText(paramQuestion.question);
			this.questionNumber.setText(getString(2131165576) + " "
					+ paramQuestion.rank);
			if (!paramQuestion.isImageQus) {

			}
			this.listView.setVisibility(8);
			this.gridView.setVisibility(0);
			QuestionImageAdapter localQuestionImageAdapter = new QuestionImageAdapter(
					this.context, 2130903136, paramQuestion.lstAnswers);
			this.gridView.setAdapter(localQuestionImageAdapter);
			this.gridView.invalidate();
		}
		for (;;) {
			this.listView
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						public void onItemClick(
								AdapterView<?> paramAnonymousAdapterView,
								View paramAnonymousView, int paramAnonymousInt,
								long paramAnonymousLong) {
							QuizzActivity.this.isChange = true;
							QuizzActivity.this.listView
									.setSelection(paramAnonymousInt);
							QuizzActivity.this.userAnswers.put(
									paramQuestion.rank,
									((Answer) paramQuestion.lstAnswers
											.get(paramAnonymousInt)).answerId);
							Log.d("Anwser",
									"save answer "
											+ paramQuestion.rank
											+ " - "
											+ ((Answer) paramQuestion.lstAnswers
													.get(paramAnonymousInt)).answerId);
							QuizzActivity.this.showNextQuestion();
						}
					});
			this.gridView
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						public void onItemClick(
								AdapterView<?> paramAnonymousAdapterView,
								View paramAnonymousView, int paramAnonymousInt,
								long paramAnonymousLong) {
							QuizzActivity.this.isChange = true;
							QuizzActivity.this.userAnswers.put(
									paramQuestion.rank,
									((Answer) paramQuestion.lstAnswers
											.get(paramAnonymousInt)).answerId);
							QuizzActivity.this.showNextQuestion();
						}
					});
			// TODO: return;
			if (this.status != 0) {
				// TODO: break;
			}
			this.leftMenuButton.setVisibility(8);
			// TODO: break;
			// label173:
			this.listView.setVisibility(0);
			QuestionTextAdapter localQuestionTextAdapter = new QuestionTextAdapter(
					this.context, 2130903137, paramQuestion.lstAnswers);
			this.listView.setAdapter(localQuestionTextAdapter);
			this.listView.invalidate();
			this.gridView.setVisibility(8);
		}
	}

	private void extractAnswerToParseArray() {
		Iterator<Question> localIterator = this.questions.iterator();
		for (;;) {
			if (!localIterator.hasNext()) {
				return;
			}
			Question localQuestion = (Question) localIterator.next();
			if (localQuestion.currentUserAnswer >= 0) {
				this.userAnswers.put(localQuestion.rank,
						localQuestion.currentUserAnswer);
			}
		}
	}

	private void getSaveAnswer(int paramInt) {
		Iterator<Answer> localIterator;
		if (this.userAnswers.indexOfKey(((Question) this.questions
				.get(paramInt - 1)).rank) >= 0) {
			localIterator = ((Question) this.questions.get(paramInt - 1)).lstAnswers
					.iterator();
			if (!localIterator.hasNext()) {
				Answer localAnswer = (Answer) localIterator.next();
				if (localAnswer.answerId == this.userAnswers
						.get(((Question) this.questions.get(paramInt - 1)).rank)) {
					localAnswer.choosed = true;
				} else {
					localAnswer.choosed = false;
				}
			}

		}
		// for (;;)
		// {

		// }
	}

	private void onLoadProfileContent() {
		String str = MySharePreference.getProfileContent(this.context);
		if (!Tools.isEmpty(str)) {
		}
		try {
			this.profile = Profile.initFromJson(new JSONObject(str),
					this.config.typePhone);
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	private void showNextQuestion() {
		int i = this.questions.size();
		if (this.current < i - 1) {
			this.current = (1 + this.current);
			displayQuestion((Question) this.questions.get(this.current));
			return;
		}
		if (this.status == 1) {
			if (!this.isChange) {
				onBackPressed();
				return;
			}
			new UpdateQuestionAsync().executeOnExecutor(
					AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
			return;
		}
		new UpdateQuestionAsync().executeOnExecutor(
				AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
	}

	private void showPopupAskQuizz() {
		View localView = ((LayoutInflater) this.context
				.getSystemService("layout_inflater")).inflate(
				R.layout.popup_ask_quizz, null);
		mpopup = new PopupWindow(localView, -1, -1, true);
		// this.mpopup.setAnimationStyle(16973826);
		TextView localTextView1 = (TextView) localView
				.findViewById(R.id.popup_ask_skip_button);
		TextView localTextView2 = (TextView) localView
				.findViewById(R.id.popup_ask_next_button);
		localTextView1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				QuizzActivity.this.mpopup.dismiss();
			}
		});
		localTextView2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				QuizzActivity.this.mpopup.dismiss();
			}
		});
		// mpopup.showAtLocation(localView, 17, 0, 0);
	}

	private void showPreviousQuestion() {
		if (this.current > 0) {
			this.current = (-1 + this.current);
			getSaveAnswer(((Question) this.questions.get(this.current)).rank);
			displayQuestion((Question) this.questions.get(this.current));
		}
		while (this.status != 1) {
			return;
		}
		onBackPressed();
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setContentView(R.layout.activity_quizz_question);
		this.context = this;
		this.leftMenuButton = ((TextView) findViewById(R.id.quizz_left_button));
		this.rightMenuButton = ((TextView) findViewById(R.id.quizz_right_button));
		this.questionNumber = ((TextView) findViewById(R.id.quizz_question_number));
		this.questionLabel = ((TextView) findViewById(R.id.lbNameHeader));
		this.percentLabel = ((TextView) findViewById(R.id.quizz_percent_label));
		this.processBar = ((ProgressBar) findViewById(R.id.quizz_progress));
		this.progressBar = ((ProgressBar) findViewById(R.id.quizz_progressBar));
		this.listView = ((ListView) findViewById(R.id.quizz_question_listview));
		this.gridView = ((GridView) findViewById(R.id.quizz_question_gridview));
		this.dialogWarning = new DialogWarning();
		this.apiQuestion = new APIQuestion();
		this.userAnswers = new SparseIntArray();
		this.questions = new ArrayList<Question>();
		this.apiKey = MySharePreference.getApiKey(this.context);
		this.config = Configuration.getInstance(this.context);
		Bundle localBundle = getIntent().getExtras();
		if ((localBundle != null) && (localBundle.containsKey("status"))) {
			this.status = localBundle.getInt("status");
			if (this.status != 0) {
				showPopupAskQuizz();

			}
			this.leftMenuButton.setVisibility(8);
		}
		// for (;;)
		// {
		// new
		// LoadQuestionAsync().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
		// new Void[0]);
		// this.leftMenuButton.setOnClickListener(new View.OnClickListener()
		// {
		// public void onClick(View paramAnonymousView)
		// {
		// QuizzActivity.this.showPreviousQuestion();
		// }
		// });
		// this.rightMenuButton.setOnClickListener(new View.OnClickListener()
		// {
		// public void onClick(View paramAnonymousView)
		// {
		// if (QuizzActivity.this.status == 0)
		// {
		// if
		// (QuizzActivity.this.userAnswers.indexOfKey(((Question)QuizzActivity.this.questions.get(QuizzActivity.this.current)).rank)
		// >= 0)
		// {
		// QuizzActivity.this.showNextQuestion();
		// return;
		// }
		// QuizzActivity.this.dialogWarning.show(QuizzActivity.this.context,
		// QuizzActivity.this.getString(2131165785), "", false);
		// return;
		// }
		// QuizzActivity.this.showNextQuestion();
		// }
		// });
		// //TODO: return;
		// //TODO: label319:
		// onLoadProfileContent();
		// int i = Integer.parseInt(this.profile.quizzCompletion);
		// this.processBar.setProgress(i);
		// this.percentLabel.setText(i + " %");
		// }
	}

	public void showPopupWarning(String paramString1, String paramString2,
			boolean paramBoolean) {
		View localView = ((LayoutInflater) this.context
				.getSystemService("layout_inflater")).inflate(2130903157, null);
		final PopupWindow localPopupWindow = new PopupWindow(localView, -2, -2,
				true);
		localPopupWindow.setAnimationStyle(16973826);
		localPopupWindow.setOutsideTouchable(true);
		localPopupWindow.setFocusable(true);
		localPopupWindow.showAtLocation(localView, 17, 0, 0);
		TextView localTextView1 = (TextView) localView.findViewById(2131231199);
		Button localButton = (Button) localView.findViewById(2131231200);
		TextView localTextView2 = (TextView) localView.findViewById(2131231201);
		if (paramBoolean) {
			localButton.setBackgroundResource(2130837669);
		}
		for (;;) {
			localTextView1.setText(paramString1);
			if (!Tools.isEmpty(paramString2)) {
				localTextView2.setVisibility(0);
				localTextView2.setText(paramString2);
			}
			localButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View paramAnonymousView) {
					localPopupWindow.dismiss();
					if (QuizzActivity.this.status == 1) {
						QuizzActivity.this.onBackPressed();
					}
				}
			});
			// TODO: return;
			localButton.setBackgroundResource(2130837673);
		}
	}

	private class LoadQuestionAsync extends
			AsyncTask<Void, Void, ArrayList<Question>> {
		private LoadQuestionAsync() {
		}

		protected ArrayList<Question> doInBackground(Void... paramVarArgs) {
			return QuizzActivity.this.apiQuestion.getAllQuestions(
					QuizzActivity.this.apiKey,
					QuizzActivity.this.config.typePhone);
		}

		protected void onPostExecute(ArrayList<Question> paramArrayList) {
			super.onPostExecute(paramArrayList);
			if ((paramArrayList != null) && (paramArrayList.size() > 0)) {
				QuizzActivity.this.questions = paramArrayList;
				QuizzActivity.this
						.displayQuestion((Question) QuizzActivity.this.questions
								.get(0));
				if (QuizzActivity.this.status == 1) {
					QuizzActivity.this.extractAnswerToParseArray();
				}
			}
			QuizzActivity.this.progressBar.setVisibility(8);
		}

		protected void onPreExecute() {
			super.onPreExecute();
			QuizzActivity.this.progressBar.setVisibility(0);
		}
	}

	private class UpdateQuestionAsync extends AsyncTask<Void, Void, Boolean> {
		private UpdateQuestionAsync() {
		}

		protected Boolean doInBackground(Void... paramVarArgs) {
			return Boolean.valueOf(QuizzActivity.this.apiQuestion
					.setAnswerForUser(
							QuizzActivity.this.convertArrayToString(),
							QuizzActivity.this.apiKey,
							QuizzActivity.this.config.currentLanguage));
		}

		protected void onPostExecute(Boolean paramBoolean) {
			super.onPostExecute(paramBoolean);
			if (paramBoolean.booleanValue()) {
				// if (QuizzActivity.this.status == 0)
				// {
				// Intent localIntent = new Intent(QuizzActivity.this.context,
				// AddBrandActivity.class);
				// localIntent.putExtra("status", 1);
				// QuizzActivity.this.startActivity(localIntent);
				// ConstantsHelper.animationPro(QuizzActivity.this);
				// }
			} else {
				QuizzActivity.this.rightMenuButton.setClickable(true);
				QuizzActivity.this.progressBar.setVisibility(8);

				// QuizzActivity.this.showPopupWarning(QuizzActivity.this.getString(2131165465),
				// null, true);
				//
				// QuizzActivity.this.showPopupWarning(QuizzActivity.this.getString(2131165466),
				// null, false);
			}
		}

		protected void onPreExecute() {
			super.onPreExecute();
			QuizzActivity.this.rightMenuButton.setClickable(false);
			QuizzActivity.this.progressBar.setVisibility(0);
		}
	}
}
