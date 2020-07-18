package com.az.employee;

/**
 * @author Karthikeyan 15/07/2020
 * @version 1.0
 * 
 * This is the main class which starts whole Spring Framework
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeDetailApplication {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailApplication.class, args);
	}

}
