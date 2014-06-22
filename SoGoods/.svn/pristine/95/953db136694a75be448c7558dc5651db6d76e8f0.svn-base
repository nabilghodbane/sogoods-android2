package com.hitechno.sogoods.fragments;

import java.util.Calendar;

import com.hitechno.sogoods.interfaces.DatePickerActivityInterface;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

//import com.hitechno.sogoods.interfaces.DatePickerActivityInterface;

public class DatePickerDialogFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	public DatePickerDialogFragment() {
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	// TODO Implement interface for callback in AccountActivity and
	// MyProfileActivity
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		 DatePickerActivityInterface activity = (DatePickerActivityInterface) getActivity();
		 activity.setDate(year, month, day);
	}
}
