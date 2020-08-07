package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther River
 * @date 2020/8/7 10:31
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndInstcrement() % serviceInstances.size(); //访问次数(第几次访问)对发现的服务器个数取余
        return serviceInstances.get(index); // 返回列表中指定元素的位置，即指定服务的位置
    }

    public final int getAndInstcrement(){
        int current;
        int next; // 第几次访问
        do {
            current = this.atomicInteger.get();// 获取初始值
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next)); // CAS自旋操作：实际值与预期值比较是否相同来判断是否自旋
        System.out.println("********************* next:" + next + " ***********************");
        return next;
    }
}
