package com.dzf.serviceprovidersimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceProviderSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProviderSimpleApplication.class, args);
	}

}
