package com.microservice1.emp.openfeignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice1.emp.response.AddressResponse;



@FeignClient(name = "address-service",path = "/address-app/api/")
public interface AddressClient {

	@GetMapping("/address/{employee_id}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employee_id") int id);
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressResponse>> getAllAddress();
}
