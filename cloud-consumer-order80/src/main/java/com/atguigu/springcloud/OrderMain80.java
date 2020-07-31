package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @auther River
 * @date 2020/7/28 11:54
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 此微服务不涉及数据库操作，去除数据库组件自动装配，防止启动报错
@EnableEurekaClient
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
