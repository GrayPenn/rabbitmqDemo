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
            /**
             * basic.ack用于肯定确认
             * basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展）
             * basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息
             */
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }else{
            /**
             * channel.basicNack(deliveryTag, false, true);
             * 第一个参数依然是当前消息到的数据的唯一id;
             * 第二个参数是指是否针对多条消息；如果是true，也就是说一次性针对当前通道的消息的tagID小于当前这条消息的，都拒绝确认。
             * 第三个参数是指是否重新入列，也就是指不确认的消息是否重新丢回到队列里面去。
             */
            //channel.basicNack();
        }
        logger.info("——————————————————消费完毕——————————————————");
    }



}
