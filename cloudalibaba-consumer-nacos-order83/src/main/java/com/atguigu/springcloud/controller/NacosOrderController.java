package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther River
 * @date 2020/8/25 17:43
 */
@RestController
@Slf4j
public class NacosOrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/payment/getPayment/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){

        return restTemplate.getForObject(serverURL+"/payment/getPayment/"+id,String.class);
    }
}
