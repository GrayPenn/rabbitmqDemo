package com.young.rabbitmq.demo2.consumer;

import com.young.rabbitmq.common.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 模拟多个不同的消费者
 *
 * 可以看到是实现了轮询的方式对消息进行消费，而且不存在重复消费。
 *
 *
 * DirectReceiver消费者1收到消息  : {createTime=2021-01-15 11:25:21, messageId=0654e190-a98a-40d8-b142-c64aa40b37ba, messageData=test message, hello!}
 * DirectReceiver消费者2收到消息  : {createTime=2021-01-15 11:25:22, messageId=52da6866-a24d-4615-989a-41d1ef0a63d0, messageData=test message, hello!}
 * DirectReceiver消费者1收到消息  : {createTime=2021-01-15 11:25:23, messageId=0fd4acb0-97c8-47e1-8486-2f657aa54052, messageData=test message, hello!}
 * DirectReceiver消费者2收到消息  : {createTime=2021-01-15 11:25:23, messageId=823d4ff6-813f-48ed-aec8-c9ad80f399dd, messageData=test message, hello!}
 * DirectReceiver消费者1收到消息  : {createTime=2021-01-15 11:25:24, messageId=ab33ba4c-0b1c-4904-b8b7-10a52000aee8, messageData=test message, hello!}
 *
 * @author pgy
 * @date 2021/1/15 11:21 上午
 **/
@Component
@RabbitListener(queues = RabbitMQConstant.DIRECT_EXCHANGE_QUEUE)//监听的队列名称
public class DirectReceiver2 {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者2收到消息  : " + testMessage.toString());
    }

}