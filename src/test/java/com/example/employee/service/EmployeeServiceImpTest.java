package com.example.employee.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.serviceImp.EmployeeServiceImp;
  

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceImpTest {
	
	@Mock
	EmployeeRepository empRepository;
	
	@InjectMocks
	EmployeeServiceImp empService;
	
	@InjectMocks
	Employee emp = new Employee(1, "milan", 12345, "london",null, "IT");
	
	@Mock
	EmployeeServiceImp empServiceockMock;
	
	@InjectMocks
	Date date = java.sql.Date.valueOf("2009-12-31");
	
	@Test
	public void getAllEmployee(){
		List<Employee> result = new ArrayList<>();
		Mockito.when(empRepository.findAll()).thenAnswer(x ->result);
		assertEquals("NO RECORDS FOUND FROM DB", empService.getAllEmployee().getBody());
	}
	
	@Test
	public void getAllEmployeeSuccessfully() throws ParseException{
		List<Employee> result = List.of(new Employee(1, "milan", 100, "london",date, "IT"));
		
		Mockito.when(empRepository.findAll()).thenAnswer(x ->result);
		assertEquals(result,empService.getAllEmployee().getBody());
	}
	
	@Test
	public void addEmployee(){
		Mockito.when(empServiceockMock.validateAllFields(emp)).thenAnswer(x ->true);
		assertEquals("PLEASE ENTER THE MISSING DETAILS", empService.addEmployee(emp).getBody());
	}
	
	@Test
	public void addEmployeeSuccessfully(){
		emp.setStartDate(date);
		Mockito.when(empServiceockMock.validateAllFields(emp)).thenAnswer(x ->false);
		Mockito.when(empRepository.save(emp)).thenAnswer(x ->emp);
		assertEquals(emp, empService.addEmployee(emp).getBody());
	}
	
	@Test
	public void deleteEmployeeWithoutID(){
		assertEquals("PLEASE ENTER THE ID", empService.deleteEmployee(0).getBody());
	}
	
	@Test
	public void deleteEmployeeWithID(){
		Optional<Employee> result = Optional.empty();
		Mockito.when(empRepository.findById(1)).thenAnswer(x ->result);
		assertEquals("NO RECORDS FOUND FROM DB", empService.deleteEmployee(1).getBody());
	}
	
	@Test
	public void deleteEmployeeSucessfully(){
		Optional<Employee> result = Optional.of(emp);
		Mockito.when(empRepository.findById(1)).thenAnswer(x ->result);
		assertEquals("RECORD DELETED", empService.deleteEmployee(1).getBody());
	}
	
	@Test
	public void updateEmployeeRecord(){
		Mockito.when(empServiceockMock.validateAllFields(emp)).thenAnswer(x ->true);
		assertEquals("PLEASE ENTER THE MISSING DETAILS", empService.updateEmployeeRecord(emp).getBody());
	}
	
	@Test
	public void getEmployee(){
		List<Employee> result = List.of(new Employee(1, "milan", 100, "london",date, "IT"));
		Mockito.when(empRepository.getEmployee(date, 102020)).thenAnswer(x ->result);
		assertEquals(result, empService.getEmployee(date,102020).getBody());
	}
	
	@Test
	public void updateLocation(){
		Mockito.when(empRepository.updateLocation("IT","London")).thenAnswer(x ->1);
		assertEquals("LOCATION UPDATED SUCCESSFULLY", empService.updateLocation("IT","London").getBody());
	}
	
}
