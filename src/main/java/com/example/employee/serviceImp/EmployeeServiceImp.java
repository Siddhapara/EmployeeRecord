package com.example.employee.serviceImp;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.employee.model.Employee;
import com.example.employee.model.Winner;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.WinnerRepository;
import com.example.employee.service.EmployeeService;


@Service
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepository;
	
	@Autowired
	WinnerRepository winnerRepository;
	
	private int temp=0;
	
	
	/*
	 * Used to get all employees from DB
	 * 
	 */
	@Override
	public ResponseEntity<?> getAllEmployee(){
		List<Employee> result=  empRepository.findAll();
		if(result.isEmpty())
			return new ResponseEntity<Object>("NO RECORDS FOUND FROM DB",HttpStatus.NOT_FOUND);
		else
			return ResponseEntity.ok(result);
	}

	
	/*
	 * used to add employee
	 * all fields are mandatory
	 */
	@Override
	public ResponseEntity<?> addEmployee(Employee emp){
		EmployeeServiceImp empService = new EmployeeServiceImp();
		Boolean validate= empService.validateAllFields(emp);
		if(validate) {
			return new ResponseEntity<Object>("PLEASE ENTER THE MISSING DETAILS",HttpStatus.BAD_REQUEST);
		}else {	
			return ResponseEntity.ok(empRepository.save(emp));
		}
		
	}

	/*
	 * used to delete the record from db
	 * employee ID is mandatory
	 * 
	 */
	@Override
	public ResponseEntity<?> deleteEmployee(int id) {
		if(id==0) 
			return new ResponseEntity<Object>("PLEASE ENTER THE ID",HttpStatus.BAD_REQUEST);
		
		Optional<Employee> result=  empRepository.findById(id);
		if(result.isEmpty())
			return new ResponseEntity<Object>("NO RECORDS FOUND FROM DB",HttpStatus.NOT_FOUND);
		else
			empRepository.deleteById(id);
			return ResponseEntity.ok("RECORD DELETED");
			
	}
	
	/*
	 * used to update employee record
	 * all fields are mandatory
	 */
	@Override
	public ResponseEntity<?> updateEmployeeRecord(Employee emp) {
		Boolean validate= validateAllFields(emp);
		if(validate) {
			return new ResponseEntity<Object>("PLEASE ENTER THE MISSING DETAILS",HttpStatus.BAD_REQUEST);
		}else {	
			List<Employee> result=  empRepository.findAll();
			if(result.isEmpty()) {
				return new ResponseEntity<Object>("NOTHING TO UPDATE IN DB",HttpStatus.NOT_FOUND);
			}else {
				for(Employee e:result) {
					if(e.getEmpId()==emp.getEmpId()) {
						temp=1;
						break;
					}
				}
				if(temp==1) {
					return ResponseEntity.ok(empRepository.save(emp));
				}else {
					return new ResponseEntity<Object>("DIDN'T FIND THE RECORD WITH SPECIFIC ID",HttpStatus.NOT_FOUND);
				}
			}
				
		}
	}

    /*
     * used to get a specific employee based on condition
     *
     */
	@Override
	public ResponseEntity<?> getEmployee(Date startDate, double salary) {
	    if(startDate == null || salary==0)
	    	return new ResponseEntity<Object>("PLEASE ENTER THE VALUE",HttpStatus.BAD_REQUEST);
		
	    List<Employee> result = empRepository.getEmployee(startDate, salary);
	    if(result.isEmpty())
	    	 return new ResponseEntity<Object>("NO SPECIFIC RECORDS FOUND FROM DB",HttpStatus.NOT_FOUND);
	    else
	    	return ResponseEntity.ok(result);
	}
	
	/*
	 * used to update the location of all employees based on department name
	 * 
	 */
	@Transactional
	@Override
	public ResponseEntity<?> updateLocation(String department, String newLocation) {
		if(department == "" || newLocation=="")
	    	return new ResponseEntity<Object>("PLEASE ENTER THE VALUE",HttpStatus.BAD_REQUEST);
		
		int result=empRepository.updateLocation(department,newLocation);
		if(result == 0)
			  return new ResponseEntity<Object>("SPECIFIED DEPARTMENT NOT FOUND FROM DB",HttpStatus.NOT_FOUND);
		else
			  return ResponseEntity.ok("LOCATION UPDATED SUCCESSFULLY");
	}
	
	
	/*
	 * used to get random employee of month for prize draw
	 *
	 */
	@Override
	public ResponseEntity<?> prizeDraw() {
		Employee result = empRepository.prizeDraw();
		if(Objects.isNull(result)) {
			  return new ResponseEntity<Object>("DB IS EMPTY",HttpStatus.NOT_FOUND);
		}else {
			  System.out.println("RECORD SAVED IN DB"+ winnerRepository.save(new Winner(result.getEmpId())));
			  return ResponseEntity.ok(result);
		}
	}
	
	
	public Boolean validateAllFields(Employee emp) {
		if(emp.getEmpId() ==0 || emp.getSalary()== 0 || emp.getStartDate()== null || emp.getLocation()=="" || emp.getEmpName()=="") 
			return true;
		else
			return false;
	}

	
	

	
	
	
	
	
   
}
