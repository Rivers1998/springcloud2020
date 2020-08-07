package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @auther River
 * @date 2020/7/28 14:51
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //调用自定义负载均衡算法时不需要此注解  @LoadBalanced // 设置消费服务提供者集群时的一种负载均衡的方式,为轮询
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}