package com.microservice1.emp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice1.emp.enitity.Employee;
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
	private WebClient webClient;
	
	//@Autowired
//	private RestTemplate restTemplate;
	
	//@Value("${addressservice.base.url}")
	//private String addressBaseURL;
	
//	public EmployeeService(@Value("${addressservice.base.url}") String addressBaseURL,RestTemplateBuilder builder) {
//		this.restTemplate=builder
//				.rootUri(addressBaseURL)
//				.build();
//	}
	
	public EmployeeResponse getEmployeeById(Integer id) {
		
		//addressResponse-> set data by making restApi call
		
		
		Employee emp=this.employeeRepo.findById(id).get();  // db call 10 sec bz it is synchronous
		EmployeeResponse employeeResponse=this.modelMapper.map(emp, EmployeeResponse.class);
		
		//AddressResponse addressResponse=restTemplate.getForObject("/address/{id}", AddressResponse.class, id);  //rest api call
		AddressResponse addressResponse=webClient
										.get()
										.uri("/address/"+id)
										.retrieve()
										.bodyToMono(AddressResponse.class)
										.block();
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}
	
	
//	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
//		return restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
//	}
	
	
	
	
	
	
	
	
	
	
}
