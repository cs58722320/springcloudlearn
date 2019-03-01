package com.dzf.serviceprovidersimple.controller;

import com.dzf.common.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/ribbon/get/string")
    public String ribbonGetByStringParam(@RequestParam("value")String value){
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        return "Ribbon Sample get by string, " + value;
    }

    @RequestMapping("/ribbon/get/map")
    public String ribbonGetByMapParam(@RequestParam("value")String value){
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        return "Ribbon Sample get by map, " + value;
    }

    @RequestMapping("/ribbon/post/entity")
    public StudentVo ribbonPostByEntityParam(@RequestBody StudentVo student){
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        return student;
    }
}
