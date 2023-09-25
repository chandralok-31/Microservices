package com.microservice1.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.microservice1.emp.response.EmployeeResponse;
import com.microservice1.emp.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees/{id}")
	ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable Integer id){
		EmployeeResponse emp=this.employeeService.getEmployeeById(id);
		return   ResponseEntity.status(HttpStatus.OK).body(emp);
	}
}
