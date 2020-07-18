package com.az.employee.repository;

/**
 * @author Karthikeyan 15/07/2020
 * @version 1.0
 * 
 * This is an interface and extends Spring data Repository interface. 
 * CrudRepository provides generic CRUD operation on a repository for a specific type. 
 * It has generic methods for CRUD operation such as Read employee, Create employee,
 * Update employee and Delete employee. 
 */

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.az.employee.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

	//Interface method used to get list of entity data using findAll query
	List<EmployeeEntity> findAll();

	//Interface method used to get entity data using findTopByOrderByIdDesc query
	EmployeeEntity findTopByOrderByIdDesc();

	//Interface method used to get entity data using findByEmployeeId query based on employee Id
	EmployeeEntity findByEmployeeId(String employeeId);

	//Interface method used to delete entity data using deleteById query based on employee Id
	void deleteById(Long id);
}
