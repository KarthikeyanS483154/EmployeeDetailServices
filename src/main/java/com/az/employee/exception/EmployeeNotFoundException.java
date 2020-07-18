/**
 * 
 */
package com.az.employee.exception;

/**
 * @author Karthikeyan 17/07/2020
 * @version 1.0
 * 
 * This is the class which extends from RuntimeException that can be thrown during the
 * normal operation of the JVM such as employee id not found and data not present in database.
 *
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

	// Universal version identifier number
	private static final long serialVersionUID = 1L;

	// Default Constructor which refers superclass objects
	public EmployeeNotFoundException(String exception) {
		super(exception);
	}

}
