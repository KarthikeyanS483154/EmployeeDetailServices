/**
 * 
 */
package com.az.employee.exception;

/**
 * @author Karthikeyan 17/07/2020
 * @version 1.0
 * 
 * This is a base class for @ControllerAdvice classes that wish to provide centralized
 * exception handling across all @RequestMapping methods through @ExceptionHandler methods.
 * This base class provides an @ExceptionHandler method for handling internal Spring MVC exceptions
 */

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method handles application default exception and returns custom error with proper
	 * HTTP status
	 * 
	 * @param exception - Exception
	 * @param request - Web request
	 * @return - Response entity of exception response
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method handles application custom exception and returns custom error with 
	 * HTTP NOT_FOUND status code
	 * 
	 * @param exception - Exception
	 * @param request - Web request
	 * @return - Response entity of exception response
	 */
	@ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleEmployeeNotFoundException(Exception exception, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
