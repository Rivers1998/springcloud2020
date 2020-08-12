package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

/**
 * @auther River
 * @date 2020/8/11 16:24
 */
@Service
public class PaymentService {

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
    commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    }) // 指定服务调用的超时时间
    public String paymentInfo_Timeout(Integer id){
        // int a = 10 / 0;  服务异常和服务超时都可以达到降级的效果
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_Timeout,id:" + id;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_TimeoutHandler,id:" + id + ",服务出现故障,请重试~";
    }
}
