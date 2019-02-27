package com.dzf.serviceprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProviderUserApplication.class, args);
	}

}
