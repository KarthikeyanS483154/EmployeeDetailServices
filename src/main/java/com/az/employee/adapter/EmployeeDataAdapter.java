/**
 * 
 */
package com.az.employee.adapter;

/**
 * @author Karthikeyan 16/07/2020
 * @version 1.0
 *
 * This is class which serves as a bridge between a data set and a data source for 
 * retrieving and saving data.
 */

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.az.employee.entity.EmployeeEntity;
import com.az.employee.model.Employee;
import com.az.employee.util.Utils;

@Component
public class EmployeeDataAdapter {

	// Utils class injection object
	@Autowired
	private Utils appUtils;

	/**
	 * This method used to set employee entity data from the given employee object
	 * Check the value whether is null or empty before assigning into entity object
	 * 
	 * @param employeeId - Employee ID String
	 * @param employee   - Employee Object
	 * @return - EmployeeEntity Object
	 * @throws ParseException - Parsing Exception
	 * @throws Exception      - General Exception
	 */
	public EmployeeEntity getEmployeeEntity(EmployeeEntity employeeEntity, String employeeId, Employee employee)
			throws ParseException, Exception {
		if (appUtils.isNullorEmpty(employeeId))
			employeeEntity.setEmployeeId(employeeId);

		if (appUtils.isNullorEmpty(employee.getFirstName()))
			employeeEntity.setFirstName(employee.getFirstName());

		employeeEntity.setLastName(employee.getLastName());

		if (appUtils.isNullorEmpty(employee.getEmailId()) && appUtils.isValidEmail(employee.getEmailId()))
			employeeEntity.setEmailId(employee.getEmailId());

		if (appUtils.isNullorEmpty(employee.getPhoneNumber()) && appUtils.isValidPhoneNumber(employee.getPhoneNumber()))
			employeeEntity.setPhoneNumber(employee.getPhoneNumber());

		if (appUtils.isNullorEmpty(employee.getDesignation()))
			employeeEntity.setDesignation(employee.getDesignation());

		if (appUtils.isNullorEmpty(employee.getDateOfBirth())) {
			String empDOB = employee.getDateOfBirth();
			employeeEntity.setDateOfBirth(appUtils.getParseDate(empDOB));
		}
		if (appUtils.isNullorEmpty(employee.getDateOfBirth()))
			employeeEntity.setGender(employee.getGender());

		if (appUtils.isNullorEmpty(employee.getMaritalStatus()))
			employeeEntity.setMaritalStatus(employee.getMaritalStatus());

		if (appUtils.isNullorEmpty(employee.getBloodGroup()))
			employeeEntity.setBloodGroup(employee.getBloodGroup());

		if (appUtils.isNullorEmpty(employee.getAddress()))
			employeeEntity.setAddress(employee.getAddress());
		return employeeEntity;
	}

	/**
	 * This method used to set employee data from the given employee entity object
	 * Check the value whether is null or empty before assigning into employee
	 * object
	 * 
	 * @param employeeEntity - Employee
	 * @param employee       - Employee Object
	 * @return - Employee Object
	 * @throws Exception - General exception with handled HTTP error
	 */
	public Employee getEmployee(EmployeeEntity employeeEntity) throws Exception {
		Employee employee = new Employee();
		if (appUtils.isNullorEmpty(employeeEntity.getEmployeeId()))
			employee.setEmployeeId(employeeEntity.getEmployeeId());

		if (appUtils.isNullorEmpty(employeeEntity.getFirstName()))
			employee.setFirstName(employeeEntity.getFirstName());

		employee.setLastName(employeeEntity.getLastName());

		if (appUtils.isNullorEmpty(employeeEntity.getEmailId()) && appUtils.isValidEmail(employeeEntity.getEmailId()))
			employee.setEmailId(employeeEntity.getEmailId());

		if (appUtils.isNullorEmpty(employeeEntity.getPhoneNumber())
				&& appUtils.isValidPhoneNumber(employeeEntity.getPhoneNumber()))
			employee.setPhoneNumber(employeeEntity.getPhoneNumber());

		if (appUtils.isNullorEmpty(employeeEntity.getDesignation()))
			employee.setDesignation(employeeEntity.getDesignation());

		if (appUtils.isNullObject(employeeEntity.getDateOfBirth())) {
			Date empDOB = employeeEntity.getDateOfBirth();
			employee.setDateOfBirth(appUtils.getFormatDate(empDOB));
		}

		if (appUtils.isNullorEmpty(employeeEntity.getGender()))
			employee.setGender(employeeEntity.getGender());

		if (appUtils.isNullorEmpty(employeeEntity.getMaritalStatus()))
			employee.setMaritalStatus(employeeEntity.getMaritalStatus());

		if (appUtils.isNullorEmpty(employeeEntity.getBloodGroup()))
			employee.setBloodGroup(employeeEntity.getBloodGroup());

		if (appUtils.isNullorEmpty(employeeEntity.getAddress()))
			employee.setAddress(employeeEntity.getAddress());
		return employee;
	}
}
