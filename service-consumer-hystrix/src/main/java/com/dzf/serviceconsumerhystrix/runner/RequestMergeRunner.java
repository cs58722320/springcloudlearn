package com.dzf.serviceconsumerhystrix.runner;

import com.dzf.serviceconsumerhystrix.reqbatch.RequestMergeServiceCommandBatch;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Component
public class RequestMergeRunner {

    @Autowired
    RestTemplate restTemplate;

    static Queue<Long> requests = new LinkedBlockingQueue<Long>();

    public static void addRequest(Long request) {
        requests.offer(request);
    }


    @Scheduled(fixedRate = 6000)
    public void runner() throws ExecutionException, InterruptedException {
        System.out.println("请求执行开始.");
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        List<Future<String>> futureList = new ArrayList<>();
        List<RequestMergeServiceCommandBatch> commandList = new ArrayList<RequestMergeServiceCommandBatch>();
        int i = 0;
        int maxTimes = 100;
        while(maxTimes > i){
            RequestMergeServiceCommandBatch command;
            Long request = requests.poll();
            if(request == null) break;
            commandList.add(command = new RequestMergeServiceCommandBatch(restTemplate, request + i++));
            futureList.add(command.queue());
        }

//        System.out.println("请求执行完成." + future1.get());
        if(futureList.size() != 0) System.out.println("请求执行完成." + futureList.get(0).get());

        context.close();
    }

}
