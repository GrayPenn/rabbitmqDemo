package com.young.rabbitmq.demo1.producer;

import com.young.rabbitmq.common.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author pgy
 * @date 2021/1/14 6:01 下午
 **/

@Slf4j
@Component
public class NormalMessageProduct extends BaseMQProducer{

    /**
     * 普通消息
     * @param json 参数对象
     */
    public void send(String json) {
        //JSONUtil.fill()
        this.sendNormalMessage(RabbitMQConstant.NORMAL_EXCHANGE, RabbitMQConstant.NORMAL_EXCHANGE_ROUTINGKEY, json);
    }
}

