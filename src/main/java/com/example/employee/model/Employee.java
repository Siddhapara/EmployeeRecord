package com.example.employee.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Entity
public class Employee {

	@Id
	private int empId;
	@Column
	private String empName;
	@Column
	private double salary;
	@Column
	private String location;
	@Column
	private Date startDate;
	@Column
	private String department;
	
	public Employee() {
		
	}

	public Employee(int empId, String empName, double salary, String location, Date startDate, String department) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
		this.location = location;
		this.startDate = startDate;
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
