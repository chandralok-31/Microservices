package com.microservice1.emp.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice1.emp.enitity.Employee;
import com.microservice1.emp.openfeignclients.AddressClient;
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
	
	
//	@Autowired
//	private WebClient webClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
//	
//	@Autowired
//	private LoadBalancerClient loadBalancerClient;
	
	public EmployeeResponse getEmployeeById(Integer id) {
		
		//addressResponse-> set data by making restApi call
		
		
		Employee emp=this.employeeRepo.findById(id).get();  // db call 10 sec bz it is synchronous
		EmployeeResponse employeeResponse=this.modelMapper.map(emp, EmployeeResponse.class);
		
		//AddressResponse addressResponse=restTemplate.getForObject("/address/{id}", AddressResponse.class, id);  //rest api call
		AddressResponse addressResponse=addressClient.getAddressByEmployeeId(id).getBody();
		
		System.out.println(addressResponse);
										
		employeeResponse.setAddressResponse(addressResponse);
		
		return employeeResponse;
	}
	
	
	
	
//	private AddressResponse callToAddressServiceUsingWebClient(int id) {
//								return webClient
//										.get()
//										.uri("/address/"+id)
//										.retrieve()
//										.bodyToMono(AddressResponse.class)
//										.block();
//	}
//	

	private AddressResponse callingAddressServiceUsingRESTTemplate(int id) {
		
		//get me details for the ip and port number fro address service
//		List<ServiceInstance>instance=discoveryClient.getInstances("address-service");
//		ServiceInstance serviceInstance=instance.get(0);
//		String uri=serviceInstance.getUri().toString();
		
//		ServiceInstance serviceInstance = loadBalancerClient.choose("address-service");
//		String uri = serviceInstance.getUri().toString();
//		String contextRoot = serviceInstance.getMetadata().get("configPath");
//		String user = serviceInstance.getMetadata().get("user");
//		
//		System.out.println(uri+contextRoot);
//		System.out.println(user);
//		
		return restTemplate.getForObject("http://address-service/address-app/api/address/{id}", AddressResponse.class, id);
	}




	public List<EmployeeResponse> getAllEmployees() {
		List<Employee> employeeList=employeeRepo.findAll();
		List<EmployeeResponse> employeeResponses=Arrays.asList(modelMapper.map(employeeList, EmployeeResponse[].class));
	
		List<AddressResponse> addressResponse=addressClient.getAllAddress().getBody();
		
		employeeResponses.forEach(employee ->{
			//rest call open feign
			// AddressResponse addressResponse= addressClient.getAddressByEmployeeId(employee.getId()).getBody();
			
			for (AddressResponse addrResponse : addressResponse) {
				if(addrResponse.getId()==employee.getId()) {
					employee.setAddressResponse(addrResponse);
				}
			}
			
		});
		return employeeResponses;
	}
	
	
	
	
	
	
	
	
	
	
}
