package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.enetities.CommonResult;
import com.atguigu.springcloud.enetities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther River
 * @date 2020/7/28 12:00
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-payment-service"; // 该路径为服务提供者集群的名字

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
        log.info("*******************************"+entity.getHeaders()+"*********************************");
        if (entity.getStatusCode().is2xxSuccessful()){ // 2xx表示http请求的状态响应码
            return entity.getBody(); // 返回一个请求体内容
        }else {
            return new CommonResult<>(500,"请求失败",null);
        }
    }
}
