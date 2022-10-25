package com.example.rabbitmq.fantout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hj
 * @date: 2022/10/21
 * @time: 2:45 PM
 */
@Configuration
public class FanoutExchangeConfig
{

    @Bean
    public Queue fanout_q1() {
        return new Queue("fanout_q1");
    }

    @Bean
    public Queue fanout_q2() {
        return new Queue("fanout_q2");
    }


    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_exchange");
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanout_q1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanout_q2()).to(fanoutExchange());
    }
}
