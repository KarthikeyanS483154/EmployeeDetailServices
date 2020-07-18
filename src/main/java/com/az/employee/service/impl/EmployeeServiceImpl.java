/**
 * 
 */
package com.az.employee.service.impl;

/**
 * @author Karthikeyan 15/07/2020
 * @version 1.0
 * 
 * This is the class with @Service annotation act like a service layer which manage the domain model
 * objects and communicate with the repository layer.
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.az.employee.adapter.EmployeeDataAdapter;
import com.az.employee.entity.EmployeeEntity;
import com.az.employee.exception.EmployeeNotFoundException;
import com.az.employee.model.CustomResponse;
import com.az.employee.model.Employee;
import com.az.employee.repository.EmployeeRepository;
import com.az.employee.service.EmployeeService;
import com.az.employee.util.AppConstants;
import com.az.employee.util.Utils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// EmployeeRepository class injection object
	@Autowired
	private EmployeeRepository employeeRepo;

	// EmployeeDataAdapter class injection object
	@Autowired
	private EmployeeDataAdapter employeeDataAdapter;

	// Utils class injection object
	@Autowired
	private Utils appUtils;

	/**
	 * This method used to store information from the given employee details into
	 * database with using employee repository
	 */
	@Override
	public CustomResponse addEmployee(Employee employee) throws Exception {
		CustomResponse customResponse = new CustomResponse();
		try {
			EmployeeEntity employeeEntity = employeeRepo.findTopByOrderByIdDesc();
			String employeeId = AppConstants.EMPLOYEEID_PREFIX + AppConstants.INIT_VAL;
			if (employeeEntity != null) {
				if (!appUtils.isNullorEmpty(employeeEntity.getEmployeeId()))
					throw new Exception("Employee id not found");
				String previousEmployeeId = employeeEntity.getEmployeeId();
				employeeId = appUtils.getAutoIncrementId(previousEmployeeId);
			}
			EmployeeEntity empEntity = new EmployeeEntity();
			employeeRepo.save(employeeDataAdapter.getEmployeeEntity(empEntity, employeeId, employee));
			customResponse.setSuccess(true);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return customResponse;
	}

	/**
	 * This method used to update information from the given employee details into
	 * database with using employee repository
	 */
	@Override
	public CustomResponse updateEmployee(Employee employees) throws Exception {
		CustomResponse customResponse = new CustomResponse();
		try {
			if (!appUtils.isNullorEmpty(employees.getEmployeeId()))
				throw new EmployeeNotFoundException("Employee id not found");

			EmployeeEntity empEntity = new EmployeeEntity();
			empEntity = employeeRepo.findByEmployeeId(employees.getEmployeeId());
			if (empEntity == null) {
				throw new EmployeeNotFoundException("Employee not found for this id " + employees.getEmployeeId());
			}
			
			employeeRepo.save(employeeDataAdapter.getEmployeeEntity(empEntity, employees.getEmployeeId(), employees));
			customResponse.setSuccess(true);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return customResponse;
	}

	/**
	 * This method used to delete information for the given employee id from
	 * database with using employee repository
	 */
	@Override
	public CustomResponse deleteEmployee(String employeeId) throws Exception {
		CustomResponse customResponse = new CustomResponse();
		try {
			if (!appUtils.isNullorEmpty(employeeId))
				throw new Exception("Employee id not found");

			EmployeeEntity empEntity = new EmployeeEntity();
			empEntity = employeeRepo.findByEmployeeId(employeeId);
			if (empEntity == null) {
				throw new EmployeeNotFoundException("Employee not found for this id " + employeeId);
			}
			employeeRepo.deleteById(empEntity.getId());
			customResponse.setSuccess(true);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return customResponse;
	}

	/**
	 * This method used to get all the data from the database with using employee
	 * repository
	 */
	@Override
	public List<Employee> getEmployees() throws Exception {
		List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
		if (employeeEntities == null)
			throw new Exception("No employee data available");
		List<Employee> employees = new ArrayList<Employee>();
		for (EmployeeEntity employeeEntity : employeeEntities) {
			employees.add(employeeDataAdapter.getEmployee(employeeEntity));
		}
		return employees;
	}

	/**
	 * This method used to get all the data for the given employee id from the
	 * database with using employee repository
	 */
	@Override
	public CustomResponse getEmployee(String employeeId) throws Exception {
		CustomResponse customResponse = new CustomResponse();
		try {
			EmployeeEntity employeeEntity = employeeRepo.findByEmployeeId(employeeId);
			if (employeeEntity == null) {
				throw new EmployeeNotFoundException("Employee not found for this id " + employeeId);
			}
			customResponse.setSuccess(true);
			customResponse.setEmployee(employeeDataAdapter.getEmployee(employeeEntity));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return customResponse;
	}
}
