package com.hitechno.sogoods.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.hitechno.sogoods.R;
//import com.hitechno.sogoods.adapters.QuestionsAdapter;
//import com.hitechno.sogoods.managers.QuestionsLoaderManager;
import com.hitechno.sogoods.models.Question;

public class SignupFinalFragment extends Fragment {

	private ArrayList<Question> questions;
//	private QuestionsAdapter questionsAdapter;
//	private QuestionsLoaderManager questionsManager;
	
	public SignupFinalFragment() { }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		questions = new ArrayList<Question>();
		int questionResource = R.layout.signup_question_list_item;
//		questionsAdapter = new QuestionsAdapter(getActivity(), questionResource, questions);
//		questionsManager = new QuestionsLoaderManager(getActivity(), questionsAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.signup_final_fragment, container,
				false);
		
		Button submitProfileButton = (Button) rootView.findViewById(R.id.submit_profile_button);
		submitProfileButton.setEnabled(false);
		submitProfileButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				AccountActivity activity = (AccountActivity) getActivity();
//				activity.submitProfile();
			}
		});
		
//		ListView quizList = (ListView) rootView.findViewById(R.id.profile_quiz_list);
//		quizList.setAdapter(questionsAdapter);
//		getLoaderManager().initLoader(ConstantsHelper.QUESTIONS_LOADER, null, questionsManager);
		
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
//		getLoaderManager().restartLoader(ConstantsHelper.QUESTIONS_LOADER, null, questionsManager);
	}
}