package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther River
 * @date 2020/8/25 17:21
 */
@RestController
@Slf4j
public class NacosPaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/getPayment/{id}")
    public String getPayment(@PathVariable("id") Integer id){
        log.info("------------serverPort:"+serverPort+"------------");
        log.info("------------id:"+id+"------------");
        return serverPort;
    }
}
