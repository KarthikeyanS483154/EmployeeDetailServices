package com.az.employee.util;

/**
 * @author Karthikeyan 16/07/2020
 * @version 1.0
 * 
 * This class contains various constants that are used throughout the application.
 */

public class AppConstants {
	
	public static final String EMPLOYEEID_PREFIX = "AZ";
	public static final String INIT_VAL = "0001";
	public static final String EMPTY = "";
	public static final String DATE_FORMATTER = "dd/MM/yyyy";
	
	public static final String SUCCESS_MSG = "success";
	
	public static final String EMAIL_PATTERN = "[a-zA-Z_0-9.]{1,100}[@]{1}[a-zA-Z]{1,100}[.]{1}[a-zA-Z0-9]{2,5}";
	public static final String PHONE_NUMBER_PATTERN = "[0-9+ \\-\\Q\\E()]{10,14}";
}
