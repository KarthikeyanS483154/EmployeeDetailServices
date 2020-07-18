/**
 * 
 */
package com.az.employee.exception;

/**
 * @author Karthikeyan 17/07/2020
 * @version 1.0
 * 
 * This is the class that provides error data when exceptions occurs and provides 
 * customized exception handling functionality.
 *
 */

import java.util.Date;

public class ExceptionResponse {

	private Date timeStamp;
	private String message;
	private String details;

	//Constructor with custom value with refers superclass objects
	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

}
