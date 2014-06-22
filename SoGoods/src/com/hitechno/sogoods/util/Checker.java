package com.hitechno.sogoods.util;

import java.util.regex.Pattern;

import com.hitechno.sogoods.constant.ConstantsHelper;

public class Checker {

	public static Boolean isValidEmail(String email) {
		Boolean isValidEmail = false;
		Pattern pattern = Pattern.compile(ConstantsHelper.EMAIL_REGEX);
		isValidEmail = pattern.matcher(email).matches();
		return isValidEmail;
	}
}
