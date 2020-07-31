package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.enetities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther River
 * @date 2020/7/28 10:05
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    //@Resource 也可以，java自带的spring支持的注入
    private PaymentDao paymentDao;

    public Integer create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
