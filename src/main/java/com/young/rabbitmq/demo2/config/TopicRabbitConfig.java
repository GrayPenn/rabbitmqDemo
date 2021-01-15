package com.young.rabbitmq.demo2.config;


import com.young.rabbitmq.common.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pgy
 * @date 2021/1/15 11:26 上午
 **/
@Configuration
public class TopicRabbitConfig {


    @Bean
    public Queue firstQueue() {
        return new Queue(RabbitMQConstant.TOPIC_EXCHANGE_QUEUE_MAN);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(RabbitMQConstant.TOPIC_EXCHANGE_QUEUE_WOMAN);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitMQConstant.TOPIC_EXCHANGE);
    }


    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(RabbitMQConstant.TOPIC_EXCHANGE_ROUTINGKEY_MAN);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with(RabbitMQConstant.TOPIC_EXCHANGE_ROUTINGKEY_TOTAL);
    }

}