/**
 * 
 */
package com.az.employee.service;

/**
 * @author Karthikeyan 15/07/2020
 * @version 1.0
 *
 * This is interface class which do write the business logic to store, retrieve, delete
 * and updates the product 
 */

import java.util.List;

import com.az.employee.model.Employee;

public interface EmployeeService {

	// Add employee abstract method which contains no implementation
	public abstract void addEmployee(Employee employee) throws Exception;

	// Update employee abstract method which contains no implementation
	public abstract void updateEmployee(Employee employees) throws Exception;

	// Delete employee abstract method which contains no implementation
	public abstract void deleteEmployee(String employeeId) throws Exception;

	// Employee List abstract method which contains no implementation
	public abstract List<Employee> getEmployees() throws Exception;

	// Employee Details abstract method which contains no implementation
	public abstract Employee getEmployee(String employeeId) throws Exception;
}
