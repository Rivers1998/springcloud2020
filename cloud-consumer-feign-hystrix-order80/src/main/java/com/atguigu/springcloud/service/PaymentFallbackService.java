package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @auther River
 * @date 2020/8/13 14:45
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {

        return "----paymentFallbackService fall back -paymentInfo_OK----";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {

        return "----paymentFallbackService fall back -paymentInfo_Timeout----";
    }
}
