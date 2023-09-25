package com.microservice1.add.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice1.add.entity.Address;
import com.microservice1.add.repo.AddressRepo;
import com.microservice1.add.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressResponse findAddressByEmployeeId(int employeeId) {
		Address address=addressRepo.findAddressByEmployeeId(employeeId);
		AddressResponse addressResponse=this.modelMapper.map(address, AddressResponse.class);
		return addressResponse;
	}
}
