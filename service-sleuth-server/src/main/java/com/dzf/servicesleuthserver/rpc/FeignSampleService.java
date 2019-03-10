package com.dzf.servicesleuthserver.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@FeignClient(name = "service-provider-simple")
public interface FeignSampleService {

    @RequestMapping(value = "/feign/hello/{who}")
    String feignHello(@PathVariable("who") String who);

}
