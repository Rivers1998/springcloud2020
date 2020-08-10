package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther River
 * @date 2020/8/10 16:07
 */
@SpringBootApplication
@EnableEurekaClient
public class HystrixPaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixPaymentMain8001.class,args);
    }
}
