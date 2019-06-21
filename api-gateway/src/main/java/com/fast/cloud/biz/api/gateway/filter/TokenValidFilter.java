package com.fast.cloud.biz.api.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * Created by dylan.wang on 2019/1/14.
 */
@Component
public class TokenValidFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> token = exchange.getRequest().getHeaders().get("Authorization");
        List<String> username = exchange.getRequest().getHeaders().get("UserId");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
