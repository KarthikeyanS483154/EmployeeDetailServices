/**
 * 
 */
package com.az.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.az.employee.controller.EmployeeController;
import com.az.employee.entity.EmployeeEntity;
import com.az.employee.model.CustomResponse;
import com.az.employee.model.Employee;
import com.az.employee.repository.EmployeeRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ComponentScan(basePackages = { "com.az" })
@AutoConfigureMockMvc
@WebMvcTest(EmployeeController.class)
@WithMockUser
@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeRepository employeeRepo;

	List<EmployeeEntity> employeesEntity = new ArrayList<EmployeeEntity>();

	@SuppressWarnings("deprecation")
	EmployeeEntity employeeEntity = new EmployeeEntity("AZ0001", "James", "David", "Sr.Developer",
			"James.David@cognizant.com", "(257) 563-7401", new Date("03/02/1990"), "Male", "Married", "A+",
			"711-2880 Nulla St.Mankato Mississippi 96522.");

	Employee employee = new Employee("AZ0001", "James", "David", "Sr.Developer", "James.David@cognizant.com",
			"(257) 563-7401", "02/03/1990", "Male", "Married", "A+", "711-2880 Nulla St.Mankato Mississippi 96522.");
	Gson gsonBuilder = new GsonBuilder().create();

	@Test
	public void getEmployees() throws Exception {

		employeesEntity.add(employeeEntity);

		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);

		Mockito.when(employeeRepo.findAll()).thenReturn(employeesEntity);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstants.TestUrlParam2)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = gsonBuilder.toJson(employees);
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void getEmployee() throws Exception {
		CustomResponse customResponse = new CustomResponse();
		customResponse.setEmployee(employee);

		Mockito.when(employeeRepo.findByEmployeeId(TestConstants.TestEmployeeID)).thenReturn(employeeEntity);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(TestConstants.TestUrlParam1)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = gsonBuilder.toJson(customResponse);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void addEmployee() throws Exception {
		Employee employee = this.employee;
		employee.setEmployeeId(null); // New Request will not have employee ID
		String request = gsonBuilder.toJson(employee);
		CustomResponse customResponse = new CustomResponse();
		customResponse.setEmployee(employee);
		Mockito.when(employeeRepo.findTopByOrderByIdDesc()).thenReturn(employeeEntity);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(TestConstants.TestUrlParam2)
				.accept(MediaType.APPLICATION_JSON).content(request).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = gsonBuilder.toJson(customResponse);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void updateEmployee() throws Exception {
		Employee employee = this.employee;
		employee.setFirstName("Charles");
		String request = gsonBuilder.toJson(employee);
		CustomResponse customResponse = new CustomResponse();
		customResponse.setEmployee(employee);
		Mockito.when(employeeRepo.findByEmployeeId(TestConstants.TestEmployeeID)).thenReturn(employeeEntity);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(TestConstants.TestUrlParam2)
				.accept(MediaType.APPLICATION_JSON).content(request).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = gsonBuilder.toJson(customResponse);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void deleteEmployee() throws Exception {
		CustomResponse customResponse = new CustomResponse();
		customResponse.setEmployee(employee);
		Mockito.when(employeeRepo.findByEmployeeId(TestConstants.TestEmployeeID)).thenReturn(employeeEntity);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(TestConstants.TestUrlParam1)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = gsonBuilder.toJson(customResponse);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
