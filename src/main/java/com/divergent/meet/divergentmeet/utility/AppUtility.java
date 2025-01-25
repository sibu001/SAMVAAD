package com.divergent.meet.divergentmeet.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtility {

	private AppUtility() {

	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * To validate email
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isEmail(String text) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(text);
		return matcher.find();
	}

	/**
	 * To check if object is empty or null
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		if (object == null)
			return true;
		else
			return false;
	}

	/**
	 * To check if string is empty or null
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string) {
		if (string == null || string.trim().length() == 0)
			return true;
		return false;
	}

}
