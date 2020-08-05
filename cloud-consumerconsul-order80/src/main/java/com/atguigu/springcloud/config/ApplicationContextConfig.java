package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @auther River
 * @date 2020/8/5 10:33
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 设置消费服务提供者集群时的一种负载均衡的方式
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
