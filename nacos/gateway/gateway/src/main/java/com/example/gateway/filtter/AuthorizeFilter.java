package com.example.gateway.filtter;

import com.example.gateway.utils.ResponeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: hj
 * @date: 2022/10/25
 * @time: 10:16 AM
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Autowired
    private ResponeUtils responeUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        HttpHeaders headers = exchange.getRequest().getHeaders();

        System.out.println(headers.get("authorization"));
        // 2.获取authorization参数
        String auth = params.getFirst("authorization");
        // 3.校验
        if ("admin".equals(auth)) {
            // 放行
            return chain.filter(exchange);
        }
        DataBuffer buffer = responeUtils.getResponseBuffer(exchange.getResponse(),"没有权限");
        return exchange.getResponse().writeWith(Mono.just(buffer));

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
