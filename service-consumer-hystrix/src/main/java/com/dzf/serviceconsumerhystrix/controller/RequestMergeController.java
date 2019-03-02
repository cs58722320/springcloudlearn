package com.dzf.serviceconsumerhystrix.controller;

import com.dzf.serviceconsumerhystrix.reqbatch.RequestMergeServiceCommandBatch;
import com.dzf.serviceconsumerhystrix.rpc.HelloServiceCommand;
import com.dzf.serviceconsumerhystrix.rpc.RequestMergeServiceCommand;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
public class RequestMergeController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/hystrix/request/merge/hello/{1}")
    public String requestMerge(String a) throws ExecutionException, InterruptedException {
            //请求合并
            HystrixRequestContext context = HystrixRequestContext.initializeContext();
            RequestMergeServiceCommandBatch command = new RequestMergeServiceCommandBatch(restTemplate,1L);
            RequestMergeServiceCommandBatch command1 = new RequestMergeServiceCommandBatch(restTemplate,2L);
            RequestMergeServiceCommandBatch command2 = new RequestMergeServiceCommandBatch(restTemplate,3L);

            //这里你必须要异步，因为同步是一个请求完成后，另外的请求才能继续执行，所以必须要异步才能请求合并
            Future<String> future = command.queue();
            Future<String> future1 = command1.queue();



            String r1 = future.get();
//        String r2 = future2.get();

            Thread.sleep(2000);
        Future<String> future2 = command2.queue();
        String r2 = future2.get();
//            //可以看到前面两条命令会合并，最后一条会单独，因为睡了2000毫秒，而你请求设置要求在200毫秒内才合并的。
//            Future<String> future2 = command2.queue();
//            String r2 = future2.get();


            System.out.println(r1);
//            System.out.println(r1);
            System.out.println(r2);

            context.close();
            return r1;
    }
}
