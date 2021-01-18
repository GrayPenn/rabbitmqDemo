package com.young.rabbitmq.demo3.consumer;

import com.rabbitmq.client.Channel;
import com.young.rabbitmq.demo3.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author pgy
 * @date 2021/1/15 5:10 下午
 **/
@Component
public class DeadLetterMessageReceiver {


    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息A：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitMQConfig.DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息B：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}