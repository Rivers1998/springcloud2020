package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @auther River
 * @date 2020/7/28 11:54
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 此微服务不涉及数据库操作，去除数据库组件自动装配，防止启动报错
@EnableEurekaClient
@RibbonClient(name = "cloud-payment-service",configuration = MySelfRule.class) // name表示要访问的支付微服务名称，configuration表示装配选择自己所创建的类
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
