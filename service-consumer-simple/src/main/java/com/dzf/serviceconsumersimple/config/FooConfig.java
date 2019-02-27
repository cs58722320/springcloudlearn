package com.dzf.serviceconsumersimple.config;

import com.dzf.serviceconsumersimple.annotation.ExcludeFromComponentScan;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 名称：自定义Ribbon配置<br>
 * 描述：配置路由算法为随机路由<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Configuration
@ExcludeFromComponentScan
public class FooConfig {

    @Autowired
    IClientConfig config;

    @Bean
    public IRule ribbonRule(IClientConfig config){
        return new RandomRule();
    }
}
