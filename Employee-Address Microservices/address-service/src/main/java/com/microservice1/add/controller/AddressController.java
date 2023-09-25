package com.microservice1.add.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice1.add.response.AddressResponse;
import com.microservice1.add.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping("/address/{employee_id}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employee_id") int id) {
	
		AddressResponse addressResponse=this.addressService.findAddressByEmployeeId(id);
		
		return new ResponseEntity<AddressResponse>(addressResponse,HttpStatus.OK);
	}
}
