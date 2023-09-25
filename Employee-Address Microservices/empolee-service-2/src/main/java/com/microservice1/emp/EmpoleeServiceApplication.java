package com.microservice1.emp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@SpringBootApplication
@EnableFeignClients
public class EmpoleeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpoleeServiceApplication.class, args);
	
		
	}

}
