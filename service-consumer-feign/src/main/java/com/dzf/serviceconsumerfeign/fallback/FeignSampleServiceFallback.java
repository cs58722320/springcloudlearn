package com.dzf.serviceconsumerfeign.fallback;

import com.dzf.serviceconsumerfeign.rpc.FeignSampleService;
import org.springframework.stereotype.Service;

/**
 * 名称：<br>
 * 描述：<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Service
public class FeignSampleServiceFallback implements FeignSampleService {

    @Override
    public String feignHello(String who) {
        return who + " unknown";
    }
}
