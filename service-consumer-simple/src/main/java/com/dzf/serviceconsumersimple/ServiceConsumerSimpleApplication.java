package com.dzf.serviceconsumersimple;

import com.dzf.serviceconsumersimple.annotation.ExcludeFromComponentScan;
import com.dzf.serviceconsumersimple.config.FooConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="service-provider-simple", configuration = FooConfig.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})
public class ServiceConsumerSimpleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerSimpleApplication.class, args);
	}

}
