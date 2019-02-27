package com.dzf.serviceprovidersimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@RestController
public class ProviderSimpleController {

    @Autowired
    private DiscoveryClient client;

    @Autowired
    ServiceInstance serviceInstance;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        return "Hello," + name;
    }
}
