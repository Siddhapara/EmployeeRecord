package com.example.employee.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT u FROM Employee u WHERE u.startDate > ?1 and u.salary > ?2")
	List<Employee> getEmployee(Date startDate, double salary);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Employee c SET c.location = :newLocation WHERE c.department = :department")
	int updateLocation(String department, String newLocation);

	@Query(value = "SELECT * FROM Employee u ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Employee prizeDraw();

}
