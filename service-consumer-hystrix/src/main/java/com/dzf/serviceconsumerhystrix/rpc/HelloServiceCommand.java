package com.dzf.serviceconsumerhystrix.rpc;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

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
public class HelloServiceCommand extends HystrixCommand<String> {

    private RestTemplate restTemplate;
//    private ThreadLocal<Map<String, String>> params = ThreadLocal.withInitial(()->{
//        Map<String, String> m = new HashMap<>();
//        return m;
//    });
    private Map<String, String> params = new HashMap<>();

    public HelloServiceCommand(String commandGroupKey, RestTemplate restTemplate, Map<String, String> params) {
        //根据commandGroupKey进行线程隔离的
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
        this.params = params;
    }

    @Override
    protected String run() throws Exception {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        String helloParam = params.get("hello");
        return restTemplate.getForEntity("http://service-provider-simple/hello/" + helloParam,String.class).getBody();
    }

    @Override
    protected String getFallback() {
        return "error";
    }
}
