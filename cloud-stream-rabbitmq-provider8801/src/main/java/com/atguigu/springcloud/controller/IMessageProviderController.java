package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.IMessageProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther River
 * @date 2020/8/21 15:18
 */
@RestController
@Slf4j
public class IMessageProviderController {

    @Autowired
    private IMessageProviderService iMessageProviderService;

    @GetMapping("/sendMessage")
    public String sendMessage(){

        return iMessageProviderService.send();
    }
}
