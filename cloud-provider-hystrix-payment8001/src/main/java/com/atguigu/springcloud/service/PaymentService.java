package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

/**
 * @auther River
 * @date 2020/8/11 16:24
 */
@Service
public class PaymentService {

    /**
     * 正常访问的情况
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){

        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_Ok,id:" + id;
    }

    /**
     * 线程休眠三秒钟，模拟请求超时
     * @param id
     * @return
     */
    public String paymentInfo_Timeout(Integer id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + " paymentInfo_Timeout,id:" + id;
    }
}
