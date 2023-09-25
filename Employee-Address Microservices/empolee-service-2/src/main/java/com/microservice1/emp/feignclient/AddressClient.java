package com.microservice1.emp.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice1.emp.response.AddressResponse;

@FeignClient(name = "address-service",url = "http://localhost:8081",path = "/address-app/api/")
public interface AddressClient {

	@GetMapping("/address/{employee_id}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employee_id") int id) ;
	
}
