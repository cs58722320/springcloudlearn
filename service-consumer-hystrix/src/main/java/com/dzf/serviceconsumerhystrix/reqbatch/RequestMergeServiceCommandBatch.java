package com.dzf.serviceconsumerhystrix.reqbatch;

import com.dzf.serviceconsumerhystrix.rpc.RequestMergeServiceCommand;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 名称：Hystrix请求合并<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
public class RequestMergeServiceCommandBatch extends HystrixCollapser<List<String>,String,Long> {

    private Long id;
    private RestTemplate restTemplate;

    /**
     * 合并2秒内的所有请求
     * @param restTemplate
     * @param id
     */
    public RequestMergeServiceCommandBatch(RestTemplate restTemplate, Long id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("RequestMergeServiceCommandBatch"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter()
                        .withTimerDelayInMilliseconds(2000)));
        this.id = id;
        this.restTemplate = restTemplate;
    }

    /**
     * 获取每一个请求的请求参数
     * @return
     */
    @Override
    public Long getRequestArgument() {
        return id;
    }

    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Long>> collection) {
        List<Long> ids = new ArrayList<>(collection.size());
        ids.addAll(collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        RequestMergeServiceCommand command = new RequestMergeServiceCommand("RequestMergeServiceCommand",restTemplate,ids);
        return command;
    }

    /**
     * 合并请求结果
     * @param results
     * @param collection
     */
    @Override
    protected void mapResponseToRequests(List<String> results, Collection<CollapsedRequest<String, Long>> collection) {
        System.out.println("分配批量请求结果。。。。");

        for (CollapsedRequest<String,Long> collapsedRequest : collection) {
            String result = results.get(0);
            collapsedRequest.setResponse(result);
            break;
        }
    }
}
