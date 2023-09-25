package com.microservice1.emp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.microservice1.emp.enitity.Employee;
import com.microservice1.emp.feignclient.AddressClient;
import com.microservice1.emp.repo.EmployeeRepo;
import com.microservice1.emp.response.AddressResponse;
import com.microservice1.emp.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AddressClient addressClient;
	

	
	public EmployeeResponse getEmployeeById(Integer id) {
		
	
		
		
		Employee emp=this.employeeRepo.findById(id).get(); 
		EmployeeResponse employeeResponse=this.modelMapper.map(emp, EmployeeResponse.class);

		ResponseEntity<AddressResponse> addressResponseEntity=addressClient.getAddressByEmployeeId(id);
		AddressResponse addressResponse=addressResponseEntity.getBody();
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}
	

	
	
	
	
	
	
	
	
	
	
}
