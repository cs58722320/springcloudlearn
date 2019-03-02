package com.dzf.serviceconsumerhystrix.controller;

import com.dzf.serviceconsumerhystrix.rpc.HelloServiceCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
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
public class MyHystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hystrix/hello/{param}")
    public String hystrixHello(@PathVariable("param") String param){
        //Hystrix的缓存实现，这功能有点鸡肋。
        HystrixRequestContext.initializeContext();
        Map<String, String> params = new HashMap<>();
        params.put("hello", param);
        HelloServiceCommand command = new HelloServiceCommand("hello",restTemplate, params);
        return command.execute();
    }

}
