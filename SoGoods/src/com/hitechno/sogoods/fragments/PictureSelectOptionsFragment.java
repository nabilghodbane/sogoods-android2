package com.hitechno.sogoods.fragments;

import com.hitechno.sogoods.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PictureSelectOptionsFragment extends Fragment implements View.OnClickListener {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.dialog_add_picture, null);
		m_Edit = (TextView)result.findViewById(R.id.dialog_addpic_edit_button);
		setEditVisiblity(m_EditVisible);
		result.findViewById(R.id.dialog_addpic_edit_button).setOnClickListener(this);
		result.findViewById(R.id.dialog_addpic_cancel_button).setOnClickListener(this);
		result.findViewById(R.id.dialog_addpic_takepic_button).setOnClickListener(this);
		result.findViewById(R.id.dialog_addpic_choosepic_button).setOnClickListener(this);
		return result;
	}

	public static interface PictureSelectOptionsFragmentListener {
		public static final int PICTURE_OPTION_CANCEL = 0;
		public static final int PICTURE_OPTION_TAKE = 1;
		public static final int PICTURE_OPTION_CHOOSE = 2;
		public static final int PICTURE_OPTION_EDIT = 3;
		public void onPictureOptionSelected(int option);
	}
	
	private TextView m_Edit = null;
	private boolean m_EditVisible = false;
	
	public void setEditVisiblity(boolean visibility) {
		m_EditVisible = visibility;
		if (m_Edit != null) {
			m_Edit.setVisibility(visibility ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		int option = PictureSelectOptionsFragmentListener.PICTURE_OPTION_CANCEL;
		switch (v.getId()) {
		case R.id.dialog_addpic_edit_button:
			option = PictureSelectOptionsFragmentListener.PICTURE_OPTION_EDIT;
			break;
		case R.id.dialog_addpic_choosepic_button:
			option = PictureSelectOptionsFragmentListener.PICTURE_OPTION_CHOOSE;
			break;
		case R.id.dialog_addpic_takepic_button:
			option = PictureSelectOptionsFragmentListener.PICTURE_OPTION_TAKE;
			break;
		}
		try {
			((PictureSelectOptionsFragmentListener)getActivity()).onPictureOptionSelected(option);
		} catch (ClassCastException e) {
		}
		try {
			((FragmentActivity)getActivity()).getSupportFragmentManager().popBackStack();
		} catch (ClassCastException e) {
		}
	}
}
