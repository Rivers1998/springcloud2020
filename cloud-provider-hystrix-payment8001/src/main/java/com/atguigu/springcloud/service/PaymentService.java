package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @auther River
 * @date 2020/8/11 16:24
 */
@Service
public class PaymentService {

    //服务降级案例

    /**
     * 正常访问的情况
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){

        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_Ok,id:" + id;
    }

    /**
     * 线程休眠三秒钟，模拟请求超时
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", // hystrix服务降级注解，当调用该方法失败时会调用指定的方法
    commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    }) // 指定服务调用的超时时间
    public String paymentInfo_Timeout(Integer id){
        // int a = 10 / 0;  服务异常和服务超时都可以达到降级的效果
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_Timeout,id:" + id;
    }

    public String paymentInfo_TimeoutHandler(Integer id){

        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeoutHandler,id:" + id + ",服务出现故障,请重试~";
    }

    //服务熔断案例
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        if (id < 0){
            throw new RuntimeException("-----id不能为负数-----");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功,流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){

        return "-----id不能为负数，清稍后再试,id:" + id + "-----";
    }
}
