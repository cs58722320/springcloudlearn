package com.dzf.serviceconsumersimple.controller;

import com.dzf.common.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

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
    private StudentVo student;

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
     * Ribbon Client Sample
     * String param
     *
     * @Param value
     * @Return String
     */
    @RequestMapping(value = "/ribbon/get/string/{value}")
    public String ribbonGetByStringParam(@PathVariable("value") String value) {
        return restTemplate.getForEntity("http://service-provider-simple/ribbon/get/string?value={1}", String.class, value).getBody();
    }

    /**
     * Ribbon Client Sample
     * Map param
     *
     * @Param map
     * @Return String
     */
    @RequestMapping(value = "/ribbon/get/map/{value}")
    public String ribbonGetByMapParam(@PathVariable("value") String value) {
        Map<String, String> mapValue = new LinkedHashMap();
        mapValue.put("value", value);
        return restTemplate.getForEntity("http://service-provider-simple/ribbon/get/map?value={value}", String.class, mapValue).getBody();
    }

    /**
     * Ribbon Client Sample
     * POST for entity
     *
     * @Param studentVo
     * @Return studentVo
     */
    @RequestMapping(value = "/ribbon/post/entity")
    public StudentVo ribbonPostForEntity(@RequestBody StudentVo student) {
        System.out.println(student);
        return restTemplate.postForEntity("http://service-provider-simple/ribbon/post/entity", student, StudentVo.class).getBody();
    }

    /**
     * Ribbon Client Sample
     * PUT
     * @Param id
     */
    @RequestMapping(value = "/ribbon/put/{id}")
    public void ribbonPut(@PathVariable("id") String id) {
        System.out.println(id);
        StudentVo student = new StudentVo();
        restTemplate.put("http://service-provider-simple/ribbon/put", student, id);
    }

    /**
     * Ribbon Client Sample
     * DELETE
     * @Param id
     */
    @RequestMapping(value = "/ribbon/delte")
    public void ribbonDelete(){
        Long id=10000000000L;
        restTemplate.delete("http://EUREKA-CLIENT1/deleteUser/{1}",id);
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
