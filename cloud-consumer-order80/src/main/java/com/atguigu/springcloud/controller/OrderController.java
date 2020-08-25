package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.enetities.CommonResult;
import com.atguigu.springcloud.enetities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @auther River
 * @date 2020/7/28 12:00
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    private static final String PAYMENT_URL = "http://cloud-payment-service"; // 该路径为服务提供者集群的名字

    /**
     * 插入操作微服务调用(返回类型为json格式)
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    /**
     * 查询操作微服务调用(返回类型为json格式)
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id,CommonResult.class);
    }

    /**
     * 查询操作微服务调用(返回类型为对象格式,包含http请求头、请求体等信息)
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
        log.info("*******************************"+entity.getHeaders()+"*********************************");
        if (entity.getStatusCode().is2xxSuccessful()){ // 2xx表示http请求的状态响应码
            return entity.getBody(); // 返回一个请求体内容
        }else {
            return new CommonResult<Payment>(500,"请求失败",null);
        }
    }

    /**
     * 测试自定义负载均衡算法
     * @return
     */
    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");// 根据名称获取所有服务提供者
        if (instances == null || instances.size() <= 0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances); // 经过负载均衡算法后得到的微服务提供者
        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/lb", String.class);
    }

    /**
     * 测试zipkin
     * @return
     */
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){

        return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
    }
}
