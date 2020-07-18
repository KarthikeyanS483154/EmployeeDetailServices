/**
 * 
 */
package com.az.employee.util;

/**
 * @author Karthikeyan 17/07/2020
 * @version 1.0
 * 
 * This is a utility class that defines a set of methods that perform common,
 * often re-used functions.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	/**
	 * Method used to get formatted data for given data object
	 * 
	 * @param date - Date object
	 * @return - Data value result
	 */
	public String getFormatDate(Date date) {
		DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMATTER);
		return df.format(date);
	}

	/**
	 * Method used to get parsed data for given data string
	 * 
	 * @param date - date value
	 * @return - Data object result
	 * @throws ParseException - Parsing exception
	 */
	public Date getParseDate(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat(AppConstants.DATE_FORMATTER);
		return df.parse(date);
	}

	/**
	 * Method used to check whether given string is empty or null object if
	 * condition match true else false.
	 * 
	 * @param str - Given value
	 * @return - true/false
	 * @throws Exception - General Exception
	 */
	public boolean isNullorEmpty(String str) throws Exception {
		if (str == null || str.isEmpty())
			throw new Exception("Missing required request parameter");
		return true;
	}

	/**
	 * Method used to check whether given string is null object if condition match
	 * true else false.
	 *
	 * @param str - Given value
	 * @return - true/false
	 * @throws Exception - General Exception
	 */
	public boolean isNullObject(Object date) throws Exception {
		if (date == null)
			throw new Exception("Missing required request parameter");
		return true;
	}

	/**
	 * Method used to get increment value of given employee id
	 * 
	 * @param employeeId - Given employee ID
	 * @return - Result string
	 */
	public String getAutoIncrementId(String value) {
		String str = AppConstants.EMPLOYEEID_PREFIX + String.format("%04d",
				(Integer.parseInt(value.replace(AppConstants.EMPLOYEEID_PREFIX, AppConstants.EMPTY)) + 1));
		return str;
	}

	/**
	 * Method used to check whether given email is valid or not
	 * 
	 * @param emailId - Given Email ID
	 * @return - true/false
	 */
	public boolean isValidEmail(String emailId) throws Exception {
		String emailRegex = "[a-zA-Z_0-9.]{1,100}[@]{1}[a-zA-Z]{1,100}[.]{1}[a-zA-Z0-9]{2,5}";
		Pattern patttern = Pattern.compile(emailRegex);
		boolean isMatch = patttern.matcher(emailId).matches();
		if (!isMatch)
			throw new Exception("Email must be a valid address");
		return true;
	}

	/**
	 * Method used to check whether given phone number is valid or not
	 * 
	 * @param phoneNumber - Given phone number
	 * @return - true/false
	 */
	public boolean isValidPhoneNumber(String phoneNumber) throws Exception {
		String emailRegex = "[0-9+ \\-()]{10,14}";
		Pattern patttern = Pattern.compile(emailRegex);
		boolean isMatch = patttern.matcher(phoneNumber).matches();
		if (!isMatch)
			throw new Exception("Phone must be a valid number");
		return true;
	}
}
