package com.az.employee.controller;

/**
 * @author Karthikeyan 15/07/2020
 * @version 1.0
 * 
 * This class part of the model view controller pattern. A controller basically controls the
 * flow of data. It controls the data flow into model object and gives the view when invoke happens.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.az.employee.model.CustomResponse;
import com.az.employee.model.Employee;
import com.az.employee.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

	//EmployeeService class injection object
	@Autowired
	private EmployeeService employeeService;

	/**
	 * This is the "GET" method request is used to retrieve information
	 * from the given request mapping value using a Entity object and returns custom response with 
	 * success data if HttpStatus.OK or else it will throw Exception with proper error message
	 * 
	 * @return - List of employee response entity
	 * @throws Exception - General exception with handled HTTP error
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees() throws Exception {
		return new ResponseEntity<List<Employee>>(employeeService.getEmployees(), HttpStatus.OK);
	}

	/**
	 * This is the "GET" method request is used to retrieve particular information
	 * from the given request mapping value and returns custom response with success data if HttpStatus.OK
	 * or else it will throw Exception with proper error message
	 * 
	 * @return - Custom response with success data
	 * @throws Exception - General exception with handled HTTP error
	 */
	@RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CustomResponse> getEmployee(@PathVariable String employeeId) throws Exception {
		return new ResponseEntity<CustomResponse>(employeeService.getEmployee(employeeId), HttpStatus.OK);
	}

	/**
	 * This is the "POST" method request is used to store information
	 * for the given request mapping value and returns custom response with success data if HttpStatus.OK
	 * or else it will throw Exception with proper error message
	 * 
	 * @return - Custom response with success data
	 * @throws Exception - General exception with handled HTTP error
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomResponse> addEmployee(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<CustomResponse>(employeeService.addEmployee(employee), HttpStatus.OK);
	}

	/**
	 * This is the "PUT" method request is used to update information
	 * for the given request mapping value and returns custom response with success data if HttpStatus.OK
	 * or else it will throw Exception with proper error message
	 * 
	 * @return - Custom response with success data
	 * @throws Exception - General exception with handled HTTP error
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomResponse> updateEmployee(@RequestBody Employee employee) throws Exception {
		return new ResponseEntity<CustomResponse>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

	/**
	 * This is the "PUT" method request is used to delete information
	 * for the given request mapping value and returns custom response with success data if HttpStatus.OK
	 * or else it will throw Exception with proper error message
	 * 
	 * @return - Custom response with success data
	 * @throws Exception - General exception with handled HTTP error
	 */
	@RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<CustomResponse> deleteEmployee(@PathVariable String employeeId) throws Exception {
		return new ResponseEntity<CustomResponse>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
	}
}
