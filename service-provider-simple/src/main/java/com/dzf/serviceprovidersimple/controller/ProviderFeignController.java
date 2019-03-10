package com.dzf.serviceprovidersimple.controller;

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
public class ProviderFeignController {

    @RequestMapping(value = "/feign/hello/{who}")
    public String feignHello(@PathVariable("who") String who){

//        try {
//            //睡眠60秒，测试feign的熔断、降级
//            Thread.sleep(60 * 1000);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        return "hello " + who;
    }

}
