package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.enetities.CommonResult;
import com.atguigu.springcloud.enetities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther River
 * @date 2020/7/30 15:16
 */
@RestController
@Slf4j // 日志信息
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient; // 在eureka上注册的所有服务详细信息类

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){ // 该注解将前端的json字符串与对象对应，用对象来接受值
        Integer result = paymentService.create(payment);
        log.info("****插入成功,为："+result+"****");

        if (result > 0){
            return new CommonResult(200,"插入成功,server_port:"+serverPort,result);
        }else {
            return new CommonResult(500,"插入失败",null);
        }
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询成功,为："+payment+"****");

        if (payment != null){
            return new CommonResult(200,"查询成功,server_port:"+serverPort,payment);
        }else {
            return new CommonResult(500,"查询失败",null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object getDiscovery(){
        List<String> services = discoveryClient.getServices(); // 获取所有在eureka上注册的服务名称
        for (String service : services) {
            log.info("service:"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info("instance:"+instance);
        }
        return this.discoveryClient;
    }

    // 调用时获取端口，检验自定义robbin负载均衡算法调用情况
    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}

