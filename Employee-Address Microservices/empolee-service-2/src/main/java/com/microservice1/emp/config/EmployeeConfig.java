package com.microservice1.emp.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeConfig {

	@Value("${addressservice.base.url}")
		private String addressBaseURL;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
	

	
	
}
