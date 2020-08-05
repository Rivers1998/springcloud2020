package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther River
 * @date 2020/8/5 9:50
 */
@SpringBootApplication
@EnableDiscoveryClient // 查看注册在zookeeper注册中心上的服务的详细信息
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
