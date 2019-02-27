package com.dzf.serviceconsumersimple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@RestController
public class MyRibbonController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * service-provider-simple
     *
     * @return
     */
    @RequestMapping(value = "/ribbon/hello")
    public String ribbonHello() {
        return restTemplate.getForEntity("http://service-provider-simple/hello/dzf", String.class).getBody();
    }

    /**
     * service-provider-user
     *
     * @return
     */
    @RequestMapping(value = "/ribbon/query/user/{id}")
    public String ribbonQueryUser(@PathVariable("id") String id) {
        return restTemplate.getForEntity("http://service-provider-user/query/user/" + id, String.class).getBody();
    }

    /**
     * 测试路由的服务
     *
     * @param id
     */
    @RequestMapping("/query/{id}")
    public void hello(@PathVariable("id") String id) {
        ServiceInstance serviceInstance1 = loadBalancerClient.choose("service-provider-simple");
        System.out.println("Simple路由服务：" + ":" + serviceInstance1.getServiceId() + ":" + serviceInstance1.getHost() + ":" + serviceInstance1.getPort());

        ServiceInstance serviceInstance2 = loadBalancerClient.choose("service-provider-user");
        System.out.println("User路由服务：" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());
    }
}
