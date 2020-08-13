package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther River
 * @date 2020/8/12 10:40
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") // hystrix对服务降级的统一处理,含有@HystrixCommand注解但没有指定具体回调方法的方法会调用指定的默认方法
public class PaymentHyxtrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    /**
     * 正常访问的情况
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    /**
     * 模拟访问超时的情况
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeoutFallbackMethod", // hystrix服务降级注解，当调用该方法失败时会调用指定的方法
    commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    }) // 指定服务调用超时时间*/
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){

        //int a = 10 / 0; // 当服务消费端发生异常时，也能够触发指定的回调方法

        String result = paymentHystrixService.paymentInfo_Timeout(id);
        System.out.println(result);
        return result;
    }

    public String paymentTimeoutFallbackMethod(@PathVariable("id") Integer id){
        log.info("**********");
        return "我是80端口的消费者，对方调用服务正忙，请稍后再试~";
    }

    public String payment_Global_FallbackMethod(){

        return "Global异常信息，请稍后再试";
    }
}
