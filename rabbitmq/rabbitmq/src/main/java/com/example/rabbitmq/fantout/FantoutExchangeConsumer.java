package com.example.rabbitmq.fantout;

import com.example.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2022/10/21
 * @time: 2:50 PM
 */
@Component
public class FantoutExchangeConsumer {

    @RabbitListener(queues = "fanout_q1")
    public void consumerOne1(User vehicle) {
        System.out.println("rabbit_fanout_queue_one队列 消费者1-1：收到消息---" + vehicle);
    }

    @RabbitListener(queues = "fanout_q1")
    public void consumerOne2(User vehicle) {
        System.out.println("rabbit_fanout_queue_one队列 消费者1-2：收到消息---" + vehicle);
    }

    @RabbitListener(queues = "fanout_q2")
    public void consumerOne3(User vehicle) {
        System.out.println("rabbit_fanout_queue_one队列 消费者2-1：收到消息---" + vehicle);
    }

    @RabbitListener(queues = "fanout_q2")
    public void consumerOne4(User vehicle) {
        System.out.println("rabbit_fanout_queue_one队列 消费者2-2：收到消息---" + vehicle);
    }

}
