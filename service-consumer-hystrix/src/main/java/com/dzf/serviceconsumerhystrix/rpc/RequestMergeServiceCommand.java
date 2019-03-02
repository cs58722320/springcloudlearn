package com.dzf.serviceconsumerhystrix.rpc;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class RequestMergeServiceCommand extends HystrixCommand<List<String>> {

    private RestTemplate restTemplate;
    private List<Long> ids;

    public RequestMergeServiceCommand(String commandGroupKey, RestTemplate restTemplate, List<Long> ids) {
        //根据commandGroupKey进行线程隔离
        super(HystrixCommandGroupKey.Factory.asKey(commandGroupKey));
        this.restTemplate = restTemplate;
        this.ids = ids;
    }

    @Override
    protected List<String> run() throws Exception {
        System.out.println("发送请求。。。参数为："+ids.toString()+Thread.currentThread().getName());
        String result = restTemplate.getForEntity("http://service-provider-simple/hystrix/request/merge/{params}",String.class, StringUtils.join(ids,",")).getBody();
        List<String> results = new ArrayList<>();
        results.add(result);
        return results;
    }
}
