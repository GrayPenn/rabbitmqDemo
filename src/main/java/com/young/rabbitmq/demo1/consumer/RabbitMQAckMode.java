package com.young.rabbitmq.demo1.consumer;

/**
 * @author pgy
 * @date 2021/1/14 6:10 下午
 **/
public class RabbitMQAckMode {

    /**
     * 手动签收
     */
    public final static String MANUAL = "MANUAL";
    /**
     * 自动签收
     * RabbitMQ默认是自动确认
     */
    public final static String AUTO = "AUTO";


}
