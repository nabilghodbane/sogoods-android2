package com.hitechno.sogoods.interfaces;

/**
 * Interface that an {@link Activity} implements to handle
 * a date selected by a user from a {@link DatePickerDialog}.
 *
 */
public interface DatePickerActivityInterface {
	public void setDate(int year, int month, int day);
}
