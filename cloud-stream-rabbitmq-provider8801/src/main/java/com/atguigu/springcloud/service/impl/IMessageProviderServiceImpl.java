package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @auther River
 * @date 2020/8/21 15:00
 */
@EnableBinding(Source.class) // 定义消息推送管道
@Slf4j
public class IMessageProviderServiceImpl implements IMessageProviderService {

    @Autowired
    private MessageChannel output; // 消息发送管道

    @Override
    public String send() {
        String message = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(message).build());
        log.info("----- message: " + message + " -----");
        return null;
    }
}
