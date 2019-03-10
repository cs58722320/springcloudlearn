package com.dzf.serviceconsumerfeign.controller;

import com.dzf.serviceconsumerfeign.rpc.FeignSampleService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称：Feign调用用例<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@RestController
public class FeignSampleController {

    @Autowired
    FeignSampleService feignSampleService;

    @RequestMapping("/feign/hello/caller/{who}")
    public String feignHello(@PathVariable("who")String who){
        return feignSampleService.feignHello(who);
    }

}
