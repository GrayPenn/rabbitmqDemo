package com.young.rabbitmq.demo1.producer;

import com.young.rabbitmq.demo1.entity.MsgFailedMessage;
import com.young.rabbitmq.demo1.service.MailService;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * mq 发送消息 方法
 * @author pgy
 * @date 2021/1/14 5:36 下午
 **/
public class BaseMQProducer {


    private final static String RABBITMQ_DOWN_MSG = "RabbiMQ服务连接失败";

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    //    @Autowired
//    protected MqFailedMessageMapper mqFailedMessageMapper;
    @Autowired
    protected MailService mailService;

    /**
     * 发送普通消息(就算丢失也没关系)
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容
     */
    public void sendNormalMessage(String exchange, String routingKey, String content) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, content);
        } catch (Exception ex) {
            if (ex instanceof AmqpConnectException) {
                connectExceptionHandler();
            }
        }
    }

    /**
     * 发送重要消息(如果发送失败需要持久化起来,人工操作)
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容
     */
    public void sendImportantMessage(String exchange, String routingKey, String content) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, content);
        } catch (Exception ex) {
            if (ex instanceof AmqpConnectException) {
                connectExceptionHandler(exchange, routingKey, content);
            }
        }
    }


    //---------------------------------------------------------------------------------------------------------------------


    /**
     * RabbitMQ连接异常处理
     */
    private void connectExceptionHandler() {
        System.out.println("============================" + RABBITMQ_DOWN_MSG);


    }

    /**
     * RabbitMQ连接异常处理(需要持久化消息,待人工处理)
     *
     * @param exchange   交换机
     * @param routingKey 路由键
     * @param content    消息内容c
     */
    private void connectExceptionHandler(String exchange, String routingKey, String content) {
        connectExceptionHandler();
        //持久化消息,保存到数据库中,等待人工处理
        MsgFailedMessage message = new MsgFailedMessage();
        message.setExchange(exchange);
        message.setRoutingKey(routingKey);
        message.setContent(content);
        //mqFailedMessageMapper.insert(message);
    }
}
