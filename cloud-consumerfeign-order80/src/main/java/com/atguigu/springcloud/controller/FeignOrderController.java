package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.enetities.CommonResult;
import com.atguigu.springcloud.enetities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther River
 * @date 2020/8/10 11:10
 */
@RestController
@Slf4j
public class FeignOrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){

        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 模拟openFeign请求超时的情况，openFeign请求超时的默认时间为1s
     * @return
     */
    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){

        return paymentFeignService.paymentFeignTimeout();
    }
}
