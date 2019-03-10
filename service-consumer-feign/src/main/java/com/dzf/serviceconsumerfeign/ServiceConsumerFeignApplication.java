package com.dzf.serviceconsumerfeign;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ServiceConsumerFeignApplication {

	/*
		Logger.Level有如下几种选择：
		1. NONE, 不记录日志 (默认)。
		2. BASIC, 只记录请求方法和URL以及响应状态代码和执行时间。
		3. HEADERS, 记录请求和应答的头的基本信息。
		4. FULL, 记录请求和响应的头信息，正文和元数据。
	*/
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerFeignApplication.class, args);
	}

}
