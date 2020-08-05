package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther River
 * @date 2020/8/5 15:48
 */
@RestController
@Slf4j
public class OrderController {

    private static final String PAYMENT_URL = "http://consul-provider-payment"; // 该路径为服务提供者集群的名字

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/cs")
    public String ordercs(){

        return restTemplate.getForObject(PAYMENT_URL+"/payment/cs",String.class);
    }
}
