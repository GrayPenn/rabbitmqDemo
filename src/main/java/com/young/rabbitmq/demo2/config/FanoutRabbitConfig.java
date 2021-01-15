package com.young.rabbitmq.demo2.config;


import com.young.rabbitmq.common.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pgy
 * @date 2021/1/15 11:48 上午
 **/
@Configuration
public class FanoutRabbitConfig {

    /**
     *  创建三个队列 ：fanout.A   fanout.B  fanout.C
     *  将三个队列都绑定在交换机 fanoutExchange 上
     *  因为是扇型交换机, 路由键无需配置,配置也不起作用
     */


    @Bean
    public Queue queueA() {
        return new Queue(RabbitMQConstant.FANOUT_EXCHANGE_QUEUE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(RabbitMQConstant.FANOUT_EXCHANGE_QUEUE_B);
    }

    @Bean
    public Queue queueC() {
        return new Queue(RabbitMQConstant.FANOUT_EXCHANGE_QUEUE_C);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMQConstant.FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}