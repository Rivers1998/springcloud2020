package com.atguigu.springcloud.service;

import com.atguigu.springcloud.enetities.CommonResult;
import com.atguigu.springcloud.enetities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @auther River
 * @date 2020/8/10 10:58
 */
@Component
@FeignClient("cloud-payment-service") //使用feign寻找指定的微服务
public interface PaymentFeignService {

    @GetMapping("/payment/getPaymentById/{id}") // 寻找指定请求路径下的接口
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id); // 该方法与所调微服务方法一一对应(此方法名与微服务方法名可不相同)

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();
}
