package com.dzf.serviceconsumerhystrix.controller;

import com.dzf.serviceconsumerhystrix.reqbatch.RequestMergeServiceCommandBatch;
import com.dzf.serviceconsumerhystrix.runner.RequestMergeRunner;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@RestController
public class RequestMergeDistributeController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RequestMergeRunner runner;

    @RequestMapping(value = "/hystrix/request/merge/distribute/hello/{param}")
    public String requestMerge(@PathVariable("param") Long param) throws ExecutionException, InterruptedException {
        RequestMergeRunner.addRequest(param);
        return "";
    }
}
