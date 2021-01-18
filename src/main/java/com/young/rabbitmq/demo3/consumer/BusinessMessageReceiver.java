package com.young.rabbitmq.demo3.consumer;

import com.rabbitmq.client.Channel;
import com.young.rabbitmq.demo3.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.young.rabbitmq.demo3.config.RabbitMQConfig.BUSINESS_QUEUEA_NAME;

/**
 *
 * 成为死信的三种可能
 * 1、消息被拒绝（ Basic.Reject/Basic.Nack ），井且设置 requeue 参数为 false;
 * 2、消息过期；
 * 3、令队列达到最大长度。
 *
 * @author pgy
 * @date 2021/1/15 5:06 下午
 **/
@Slf4j
@Component
public class BusinessMessageReceiver {

    @RabbitListener(queues = RabbitMQConfig.BUSINESS_QUEUEA_NAME)
    public void receiveA(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("收到业务消息Aaaaa：{}", msg);
        log.info("aaa死信消息properties：{}", message.getMessageProperties());
        boolean ack = true;
        Exception exception = null;
        try {
            if (msg.contains("deadletter")){
                throw new RuntimeException("dead letter exception");
            }
        } catch (Exception e){
            ack = false;
            exception = e;
        }
        if (!ack){
            log.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.BUSINESS_QUEUEB_NAME)
    public void receiveB(Message message, Channel channel) throws IOException {
        log.info("收到业务消息Bbbbb：{}", new String(message.getBody()));
        log.info("bbb死信消息properties：{}", message.getMessageProperties());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
}
