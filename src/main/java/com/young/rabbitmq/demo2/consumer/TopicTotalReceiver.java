package com.young.rabbitmq.demo2.consumer;

import com.young.rabbitmq.common.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @author pgy
 * @date 2021/1/15 11:32 上午
 **/
@Component
@RabbitListener(queues = RabbitMQConstant.TOPIC_EXCHANGE_QUEUE_WOMAN)
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicTotalReceiver消费者收到消息  : " + testMessage.toString());
    }


}