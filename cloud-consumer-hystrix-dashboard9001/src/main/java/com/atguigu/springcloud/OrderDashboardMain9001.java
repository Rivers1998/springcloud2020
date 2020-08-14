package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @auther River
 * @date 2020/8/14 14:33
 */
@SpringBootApplication
@EnableHystrixDashboard
public class OrderDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(OrderDashboardMain9001.class,args);
    }
}
