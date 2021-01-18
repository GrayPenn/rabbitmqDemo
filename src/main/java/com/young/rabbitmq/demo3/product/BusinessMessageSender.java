package com.young.rabbitmq.demo3.product;

import com.young.rabbitmq.demo3.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author pgy
 * @date 2021/1/15 5:10 下午
 **/
@Component
public class BusinessMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg){
        rabbitTemplate.convertSendAndReceive(RabbitMQConfig.BUSINESS_EXCHANGE_NAME, "", msg);
    }
}