package com.hitechno.sogoods.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

/**
 * Collection of helper methods for working with <code>Profile</code>s.
 */
public class AccountHelper {
	private final SimpleDateFormat dateParser = new SimpleDateFormat(
			ConstantsHelper.DATE_FORMAT, ConstantsHelper.LOCALE);

	public AccountHelper() {
		super();
	}

	/**
	 * Determines if an email is valid.
	 * 
	 * @param email the email to verify
	 * @return whether the email was valid
	 */
	public Boolean isValidEmail(String email) {
		Boolean isValidEmail = false;
		Pattern pattern = Pattern.compile(ConstantsHelper.EMAIL_REGEX);
		isValidEmail = pattern.matcher(email).matches();
		return isValidEmail;
	}

	/**
	 * Obtains the filename from a url.
	 * 
	 * @param url the url that contains the filename
	 * @return the filename that the URL contained
	 */
	public String filenameFromUrl(String url) {
		Pattern pattern = Pattern.compile("^.*/(\\w+\\.[a-z]{3,4}){1}$");
		Matcher matcher = pattern.matcher(url);
		String filename = "";
		while (matcher.find()) {
			filename = matcher.group(1);
		}
		return filename;
	}

	/**
	 * Creates a <code>String</code> that contains a comma-delimited list
	 * of answers, organized according to question and answer index.
	 * 
	 * @param answers a list of answers (indexed by question, internally by answer index)
	 * @return a string containing answers concatenated and delimited with ','
	 */
	public String answersAsString(List<String> answers) {
		String answersString = "";
		for (int i = 0; i < answers.size(); i++) {
			answersString = answersString.concat(i
					+ ConstantsHelper.QUESTIONS_SEPARATOR + answers.get(i));

			if (i != answers.size() - 1) {
				answersString = answersString.concat(",");
			}
		}
		return answersString;
	}

	/**
	 * Calculates the age of a profile based on year, month, and day
	 * only.
	 * 
	 * @param dateOfBirth a <code>String</code> representation of a date
	 * @param now a <code>Date</code> that represents now
	 * 
	 * @return the age of the profile
	 */
	public int ageOfProfile(String dateOfBirth, Date now) {
		Date date = getDateOfBirthFromString(dateOfBirth);

		Calendar a = getCalendar(date);
		Calendar b = getCalendar(now);
		int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
		if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
				|| (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a
						.get(Calendar.DATE) > b.get(Calendar.DATE))) {
			diff--;
		}
		return diff;
	}

	public Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance(Locale.US);
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * Parses a date that is formatted using ISO8601 format. This format is of
	 * the form:
	 * <p>
	 * <code>"yyyy-MM-ddTHH:mm:ssZ"</code>
	 * 
	 * @param date the date of birth to be parsed
	 * @return the date representation of <code>updatedAt</code>
	 */
	public Date getDateOfBirthFromString(String date) {
		Date dateOfBirth = null;
		try {
			dateOfBirth = dateParser.parse(date);
		} catch (ParseException e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
					+ e.getMessage());
		}
		return dateOfBirth;
	}
}