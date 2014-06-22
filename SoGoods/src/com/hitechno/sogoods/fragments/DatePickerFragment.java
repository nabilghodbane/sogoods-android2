package com.hitechno.sogoods.fragments;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.androidannotations.annotations.*;

import com.hitechno.sogoods.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

@EFragment(R.layout.fragment_date_picker)
public class DatePickerFragment extends Fragment implements 
	DatePicker.OnDateChangedListener,
	DialogInterface.OnClickListener
{
	
	public static interface DatePickerFragmentListener {
		public void onDateSelected(GregorianCalendar date);
	}
	
	@ViewById(R.id.datePicker)
	DatePicker m_DatePicker;
	
	private GregorianCalendar m_MinimuAgeDate;
	private Activity m_Parent;
	private AlertDialog m_MinimumAgeDialog = null;
	@AfterViews
	void init() {
		GregorianCalendar today = new GregorianCalendar();
		m_MinimuAgeDate = new GregorianCalendar(today.get(Calendar.YEAR) - 18, today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
		m_DatePicker.init(m_MinimuAgeDate.get(Calendar.YEAR), m_MinimuAgeDate.get(Calendar.MONTH),
				m_MinimuAgeDate.get(Calendar.DAY_OF_MONTH), this);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		m_Parent = activity;
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		GregorianCalendar test = new GregorianCalendar(year, monthOfYear, dayOfMonth);
		if (test.compareTo(m_MinimuAgeDate) > 0) {
			if (m_MinimumAgeDialog == null) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(R.string.min_age_error)
					.setCancelable(true)
					.setPositiveButton(android.R.string.ok, this);
				m_MinimumAgeDialog = builder.create();
				m_MinimumAgeDialog.setCanceledOnTouchOutside(true);
				m_MinimumAgeDialog.show();
			}
			m_DatePicker.init(m_MinimuAgeDate.get(Calendar.YEAR), m_MinimuAgeDate.get(Calendar.MONTH),
					m_MinimuAgeDate.get(Calendar.DAY_OF_MONTH), this);
		}
	}
	
	@Click(R.id.ok)
	void clickOK() {
		GregorianCalendar date = new GregorianCalendar(m_DatePicker.getYear(), m_DatePicker.getMonth(), m_DatePicker.getDayOfMonth());
		dateSelected(date);
	}
	
	@Click(R.id.rootLayout)
	void outsideClick() {
		dateSelected(null);
	}
	
	@Click(R.id.cancel)
	void cancelClick() {
		dateSelected(null);
	}
	
	private void dateSelected(GregorianCalendar date) {
		try {
			((DatePickerFragmentListener)m_Parent).onDateSelected(date);
		} catch (ClassCastException e) {
		}
		try {
			FragmentManager fm = ((FragmentActivity)m_Parent).getSupportFragmentManager();
			fm.popBackStack();
		} catch (ClassCastException e) {
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		m_MinimumAgeDialog = null;
	}
}
