package com.microservice1.emp.openfeignclients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

@LoadBalancerClient(name = "address-service")
public class AddressServiceLoadBalancer {

	@LoadBalanced
	@Bean
	public Feign.Builder feiBuilder(){
		return new Feign.Builder();
	}
}
