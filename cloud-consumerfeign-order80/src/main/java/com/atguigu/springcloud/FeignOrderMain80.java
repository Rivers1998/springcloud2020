package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @auther River
 * @date 2020/8/10 10:54
 */
@SpringBootApplication
@EnableFeignClients //启动Feign(feign继承了ribbon，自动实现负载均衡)
public class FeignOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderMain80.class,args);
    }
}
