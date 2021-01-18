//package com.young.rabbitmq.demo2.config;
//
//import com.young.rabbitmq.common.RabbitMQConstant;
//import com.young.rabbitmq.demo2.consumer.MyAckReceiver;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author pgy
// * @date 2021/1/15 3:26 下午
// **/
//
//@Configuration
//public class MessageListenerConfig {
//
//    @Autowired
//    private CachingConnectionFactory connectionFactory;
//    @Autowired
//    private MyAckReceiver myAckReceiver;//消息接收处理类
//
//    /**
//     *
//     * @return
//     * MyAckReceiver  messageId:9a4c93ca-8c03-4e55-b7a1-7ae832c61c9c  messageData:test message, hello!  createTime:2021-01-15 15:36:24
//     * 消费的主题消息来自：direct.exchange.queue
//     */
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//        container.setConcurrentConsumers(1);
//        container.setMaxConcurrentConsumers(1);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
//        //设置一个队列
////        container.setQueueNames(RabbitMQConstant.DIRECT_EXCHANGE_QUEUE);
//        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
//          container.setQueueNames(RabbitMQConstant.DIRECT_EXCHANGE_QUEUE,RabbitMQConstant.FANOUT_EXCHANGE_QUEUE_A);
//
//
//        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
//        //container.setQueues(new Queue(RabbitMQConstant.DIRECT_EXCHANGE_QUEUE,true));
//        //container.addQueues(new Queue(RabbitMQConstant.FANOUT_EXCHANGE_QUEUE_A,true));
//        //container.addQueues(new Queue(RabbitMQConstant.FANOUT_EXCHANGE_QUEUE_B,true));
//        container.setMessageListener(myAckReceiver);
//
//        return container;
//    }
//
//
//}