package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @auther River
 * @date 2020/8/7 10:24
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances); // 获取所有的服务实例
}
