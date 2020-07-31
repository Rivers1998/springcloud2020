package com.atguigu.springcloud.service;

import com.atguigu.springcloud.enetities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @auther River
 * @date 2020/7/30 15:14
 */
public interface PaymentService {

    Integer create(Payment payment); // 插入操作

    Payment getPaymentById(@Param("id")Long id); // 读取操作
}
