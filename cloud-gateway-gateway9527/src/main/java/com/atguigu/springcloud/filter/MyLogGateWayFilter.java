package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @auther River
 * @date 2020/8/18 17:13
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("--------- Welcome to GateWayFilter,current time:" + new Date() + "---------");
        String username = exchange.getRequest().getQueryParams().getFirst("username");// 获取请求参数username
        if (username == null){
            log.info("--------- Username is null,please get out ---------");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE); // 设置响应：设置http响应状态码NOT_ACCEPTABLE
            return exchange.getResponse().setComplete();
        }else if (username.equals("")){
            log.info("--------- Username is empty,please get out ---------");
        }
        return chain.filter(exchange); // 通过的话就执行下一个filter
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
