package com.young.rabbitmq.demo2.consumer;

import com.young.rabbitmq.common.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author pgy
 * @date 2021/1/15 11:21 上午
 **/
@Component
@RabbitListener(queues = RabbitMQConstant.DIRECT_EXCHANGE_QUEUE)//监听的队列名称
public class DirectReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者1收到消息  : " + testMessage.toString());
    }

}