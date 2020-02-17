package com.example.employee.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.serviceImp.EmployeeServiceImp;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServiceImp empService;

	@GetMapping("/getAllEmployee")
	public ResponseEntity<?> getAllEmployee() {
		return empService.getAllEmployee();
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
		return empService.addEmployee(emp);
	}

	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<?> deleteEmployee(@RequestParam("id") int id) {
		return empService.deleteEmployee(id);
	}

	@PutMapping("/updateEmployeeRecord")
	public ResponseEntity<?> updateRecord(@RequestBody Employee emp) {
		return empService.updateEmployeeRecord(emp);
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<?> getEmployee(
			@RequestParam("startdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startdate,
			@RequestParam("salary") double salary) throws ParseException {
		return empService.getEmployee(startdate, salary);
	}

	@PutMapping("/updateLocation")
	public ResponseEntity<?> updateLocation(
			@RequestParam("department") String department,
			@RequestParam("newLocation") String newLocation) {
		return empService.updateLocation(department, newLocation);
	}

	@GetMapping("/prizeDraw")
	public ResponseEntity<?> prizeDraw() {
		return empService.prizeDraw();
	}

}
