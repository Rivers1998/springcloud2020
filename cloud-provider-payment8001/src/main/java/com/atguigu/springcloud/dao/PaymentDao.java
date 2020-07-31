package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.enetities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @auther River
 * @date 2020/7/28 9:33
 */
@Mapper //推荐使用mybatis的mapper注解来标注持久层接口
public interface PaymentDao {

    Integer create(Payment payment); // 插入操作

    Payment getPaymentById(@Param("id")Long id); // 读取操作
}
