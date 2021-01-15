package com.young.rabbitmq.common;

/**
 * @author pgy
 * @date 2021/1/14 6:02 下午
 **/
public class RabbitMQConstant {

    //================================= demo1 =================================

    /**
     * 普通消息
     */
    public final static String NORMAL_EXCHANGE = "normal.exchange";

    /**
     * 普通消息 的 路由 key
     */
    public final static String NORMAL_EXCHANGE_ROUTINGKEY = "normal.exchange.routing-key";

    /**
     * 普通消息 的 队列
     */
    public final static String NORMAL_EXCHANGE_QUEUE = "normal.exchange.queue";


    //================================= demo2 =================================


    /**
     * 普通消息
     */
    public final static String DIRECT_EXCHANGE = "direct.exchange";

    /**
     * 普通消息 的 路由 key
     */
    public final static String DIRECT_EXCHANGE_ROUTINGKEY = "direct.exchange.routing-key";

    /**
     * 普通消息 的 队列
     */
    public final static String DIRECT_EXCHANGE_QUEUE = "direct.exchange.queue";


    /**
     *
     */
    public final static String TOPIC_EXCHANGE = "topic.exchange";
    /**
     *
     */
    public final static String TOPIC_EXCHANGE_ROUTINGKEY_MAN = "topic.exchange.routing-key.man";

    /**
     *
     */
    public final static String TOPIC_EXCHANGE_ROUTINGKEY_WOMAN = "topic.exchange.routing-key.woman";

    /**
     * 模糊匹配
     */
    public final static String TOPIC_EXCHANGE_ROUTINGKEY_TOTAL = "topic.exchange.routing-key.*";

    /**
     *
     */
    public final static String TOPIC_EXCHANGE_QUEUE_MAN = "topic.exchange.queue.man";

    /**
     *
     */
    public final static String TOPIC_EXCHANGE_QUEUE_WOMAN = "topic.exchange.queue.woman";


    /**
     *
     */
    public final static String FANOUT_EXCHANGE = "fanout.exchange";

    /**
     *
     */
    public final static String FANOUT_EXCHANGE_QUEUE_A = "fanout.queue.A";

    /**
     *
     */
    public final static String FANOUT_EXCHANGE_QUEUE_B = "fanout.queue.B";

    /**
     *
     */
    public final static String FANOUT_EXCHANGE_QUEUE_C = "fanout.queue.C";



}
