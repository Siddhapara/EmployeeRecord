package com.example.employee.service;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.example.employee.model.Employee;

public interface EmployeeService {

	public ResponseEntity<?> getAllEmployee();

	public ResponseEntity<?> addEmployee(Employee emp);

	public ResponseEntity<?> deleteEmployee(int id);

	public ResponseEntity<?> updateEmployeeRecord(Employee emp);

	public ResponseEntity<?> getEmployee(Date startDate, double salary);

	public ResponseEntity<?> updateLocation(String department, String newLocation);

	public ResponseEntity<?> prizeDraw();
}
