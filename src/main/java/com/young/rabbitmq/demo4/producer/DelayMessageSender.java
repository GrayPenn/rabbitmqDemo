package com.young.rabbitmq.demo4.producer;

import com.young.rabbitmq.demo4.common.DelayTypeEnum;
import com.young.rabbitmq.demo4.config.DelayedRabbitMQConfig;
import com.young.rabbitmq.demo4.config.RabbitMQDelayConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author pgy
 * @date 2021/1/18 3:53 下午
 **/
@Component
public class DelayMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg, DelayTypeEnum type){
        switch (type){
            case DELAY_10s:
                rabbitTemplate.convertAndSend(RabbitMQDelayConfig.DELAY_EXCHANGE_NAME, RabbitMQDelayConfig.DELAY_QUEUEA_ROUTING_KEY, msg);
                break;
            case DELAY_60s:
                rabbitTemplate.convertAndSend(RabbitMQDelayConfig.DELAY_EXCHANGE_NAME, RabbitMQDelayConfig.DELAY_QUEUEB_ROUTING_KEY, msg);
                break;
            default:
                break;
        }
    }

    public void sendMsg(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(RabbitMQDelayConfig.DELAY_EXCHANGE_NAME, RabbitMQDelayConfig.DELAY_QUEUEC_ROUTING_KEY, msg, a ->{
            a.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return a;
        });
    }

    public void sendDelayMsg(String msg, Integer delayTime) {
        rabbitTemplate.convertAndSend(DelayedRabbitMQConfig.DELAYED_EXCHANGE_NAME, DelayedRabbitMQConfig.DELAYED_ROUTING_KEY, msg, a ->{
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }



}