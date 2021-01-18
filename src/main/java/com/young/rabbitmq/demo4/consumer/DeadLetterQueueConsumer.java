package com.young.rabbitmq.demo4.consumer;

import com.rabbitmq.client.Channel;
import com.young.rabbitmq.demo4.config.RabbitMQDelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author pgy
 * @date 2021/1/18 3:52 下午
 **/
@Slf4j
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = RabbitMQDelayConfig.DEAD_LETTER_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列A收到消息：{}", new Date().toString(), msg);
        log.info("aaa死信消息properties：{}", message.getMessageProperties());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitMQDelayConfig.DEAD_LETTER_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列B收到消息：{}", new Date().toString(), msg);
        log.info("bbb死信消息properties：{}", message.getMessageProperties());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    //不能监听这2个队列，不然就没办法走死信队列，无法实现延时消息
    //如果监听了，那么消息会被直接消费
//    @RabbitListener(queues = RabbitMQDelayConfig.DELAY_QUEUEA_NAME)
//    public void queuaA(Message message, Channel channel) throws IOException {
//        String msg = new String(message.getBody());
//        log.info("当前时间：{},队列A收到消息：{}", new Date().toString(), msg);
//        log.info("aaa消息properties：{}", message.getMessageProperties());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
//
//    @RabbitListener(queues = RabbitMQDelayConfig.DELAY_QUEUEB_NAME)
//    public void queuaB(Message message, Channel channel) throws IOException {
//        String msg = new String(message.getBody());
//        log.info("当前时间：{},队列B收到消息：{}", new Date().toString(), msg);
//        log.info("bbb消息properties：{}", message.getMessageProperties());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
}