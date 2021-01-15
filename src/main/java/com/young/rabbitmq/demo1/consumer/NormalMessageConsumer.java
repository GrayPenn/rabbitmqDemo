package com.young.rabbitmq.demo1.consumer;

import com.rabbitmq.client.Channel;
import com.young.rabbitmq.common.RabbitMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author pgy
 * @date 2021/1/14 6:08 下午
 **/
@Component
public class NormalMessageConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 接收消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConstant.NORMAL_EXCHANGE_QUEUE),
            exchange = @Exchange(value = RabbitMQConstant.NORMAL_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = RabbitMQConstant.NORMAL_EXCHANGE_ROUTINGKEY
    ), ackMode = RabbitMQAckMode.MANUAL)
    @RabbitHandler
    public void recieved(@Payload String json, Channel channel, Message message) throws IOException {
        boolean flag = false;
        try {
            logger.info("——————————————————消费消息——————————————————");
            System.out.println(json);
            flag = true;
        } catch (Exception ex) {
            logger.info("——————————————————消费失败——————————————————");
            logger.error("recieved message error:{},json:{}", ex.getMessage(), json);

        }
        if(flag){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }else{
            //channel.basicNack();
        }
        logger.info("——————————————————消费完毕——————————————————");
    }



}
