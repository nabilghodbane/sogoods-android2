package com.hitechno.sogoods.managers;
//package com.hitechno.sogoods.managers;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v4.app.LoaderManager.LoaderCallbacks;
//import android.support.v4.content.CursorLoader;
//import android.support.v4.content.Loader;
//import android.util.Log;
//
//import com.hitechno.sogoods.adapters.QuestionsAdapter;
//import com.hitechno.sogoods.helpers.ConstantsHelper;
//import com.hitechno.sogoods.models.Question;
//
//public class QuestionsLoaderManager implements LoaderCallbacks<Cursor> {
//
//	private Context context;
//	private RequestTasksManager tasksManager;
//	private ArrayList<Question> questions;
//	private QuestionsAdapter adapter;
//
//	public QuestionsLoaderManager(Context context, QuestionsAdapter adapter) {
//		this.context = context;
//		this.adapter = adapter;
//		tasksManager = RequestTasksManager.defaultManager(context);
//
//		questions = new ArrayList<Question>();
//	}
//
//	@Override
//	public Loader<Cursor> onCreateLoader(int arg0, Bundle savedInstanceState) {
//		CursorLoader loader = new CursorLoader(context,
//				ConstantsHelper.Questions.QUESTIONS_CONTENT_URI, null, null,
//				null, null);
//		return loader;
//	}
//
//	@Override
//	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//		questions.clear();
//
//		Cursor mostRecentQuestionCursor = tasksManager
//				.getContext()
//				.getContentResolver()
//				.query(ConstantsHelper.Questions.QUESTIONS_CONTENT_URI,
//						new String[] { "updated_at" }, null, null,
//						"updated_at DESC LIMIT 1");
//		Date mostRecentQuestion = null;
//		SimpleDateFormat dateFormatter = new SimpleDateFormat(
//				ConstantsHelper.DATE_FORMAT, ConstantsHelper.LOCALE);
//		try {
//			mostRecentQuestion = dateFormatter
//					.parse(ConstantsHelper.START_DATE);
//		} catch (ParseException e) {
//			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
//					+ e.getMessage());
//		}
//
//		int mostRecentlyUpdatedAtColumnIndex = mostRecentQuestionCursor
//				.getColumnIndexOrThrow(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT);
//		try {
//			if (mostRecentQuestionCursor.getCount() > 0) {
//				mostRecentQuestionCursor.moveToFirst();
//				String updatedAt = mostRecentQuestionCursor
//						.getString(mostRecentlyUpdatedAtColumnIndex);
//				mostRecentQuestion = new SimpleDateFormat(
//						ConstantsHelper.DATE_FORMAT, ConstantsHelper.LOCALE)
//						.parse(updatedAt);
//			}
//		} catch (ParseException e) {
//			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
//					+ e.getMessage());
//		}
//
//		/*
//		 * A model's #id method will reference the remote :id, not the local
//		 * :id.
//		 */
//		int idColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID);
//		int questionColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.Questions.QUESTION);
//		int urlColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.Questions.URL);
//		int updatedAtColumnIndex = cursor
//				.getColumnIndexOrThrow(ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT);
//
//		while (cursor.moveToNext()) {
//			Question question = new Question();
//			question.id = cursor.getInt(idColumnIndex);
//			question.question = cursor.getString(questionColumnIndex);
//			question.url = cursor.getString(urlColumnIndex);
//			question.updatedAt = cursor.getString(updatedAtColumnIndex);
//			questions.add(question);
//
//			if (mostRecentQuestion.before(question.getUpdatedAt())) {
//				mostRecentQuestion = question.getUpdatedAt();
//			}
//		}
//		adapter.setQuestions(questions);
//
//		/**
//		 * The current queryCursor will contain the cached data. We make a
//		 * request using the most recently added data, identified by its
//		 * updated_at column, to fetch new data.
//		 */
//		String url = tasksManager.setUrlUsingDate(
//				ConstantsHelper.Questions.INDEX_URL, mostRecentQuestion);
//		tasksManager.setUpdatedAtForRequest(mostRecentQuestion, url);
//		tasksManager.setContentUriForRequest(
//				ConstantsHelper.Questions.QUESTION_CONTENT_URI, url);
//		tasksManager.index(url);
//		
//		cursor.close();
//	}
//
//	@Override
//	public void onLoaderReset(Loader<Cursor> loader) {
//	}
//}
