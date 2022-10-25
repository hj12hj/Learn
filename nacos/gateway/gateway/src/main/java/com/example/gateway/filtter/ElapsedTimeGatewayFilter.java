package com.example.gateway.filtter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


//记录接口耗时
//局部定义
public class ElapsedTimeGatewayFilter implements GatewayFilter, Ordered {
    private final static String BEAGIN = "begin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put(BEAGIN, System.currentTimeMillis());

        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long startTime = exchange.getAttribute(BEAGIN);
                    long endTime = System.currentTimeMillis();
                    String url = exchange.getRequest().getURI().getRawPath();
                    System.out.println(endTime - startTime + "ms" + url);

                })

        );
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
