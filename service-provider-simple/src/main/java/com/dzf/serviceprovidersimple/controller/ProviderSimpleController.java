package com.dzf.serviceprovidersimple.controller;

import com.dzf.common.vo.StudentVo;
import com.sun.deploy.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public String ribbonGetByStringParam(@RequestParam("value") String value) {
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        return "Ribbon Sample get by string, " + value;
    }

    @RequestMapping("/ribbon/get/map")
    public String ribbonGetByMapParam(@RequestParam("value") String value) {
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        return "Ribbon Sample get by map, " + value;
    }

    @RequestMapping("/ribbon/post/entity")
    public StudentVo ribbonPostByEntityParam(@RequestBody StudentVo student) {
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        return student;
    }

    @RequestMapping("/hystrix/request/merge/{params}")
    public List<String> requestMerge(@PathVariable("params") String params) {
        System.out.println(client.toString());
        System.out.println(serviceInstance.getServiceId());
        List<String> results = Arrays.asList(params.split(",")).stream().map(s -> "processed, " + s).collect(Collectors.toList());
        results.stream().forEach(s-> System.out.println(s));
        return results;
    }
}
