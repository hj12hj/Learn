package com.example.gateway.config;
import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * 限流配置KeyResolver——有三种写法(接口限流/ip限流/用户限流)
 */
@Configuration
public class RateLimiteConfig {

    Logger log = org.slf4j.LoggerFactory.getLogger(RateLimiteConfig.class);
    /**
     * 接口限流：根据请求路径限流
     * 如果不使用@Primary注解，会报错
     * @return
     */
    @Primary
    @Bean(value = "ipKeyResolver")
    KeyResolver ipKeyResolver1() {
        return exchange -> {
            String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            System.out.println(ip);
            return Mono.just(ip);
        };
    }

    /**
     * 根据请求IP限流
     * @return
     */
//    @Bean
//    public KeyResolver ipKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName()
//        );
//    }
//
//    /**
//     * 根据请求参数中的userId进行限流
//     * 请求地址写法：http://localhost:8801/rate/123?userId=lisi
//     * @return
//     */
//    @Bean
//    public KeyResolver userKeyResolver() {
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId")
//        );
//    }
}