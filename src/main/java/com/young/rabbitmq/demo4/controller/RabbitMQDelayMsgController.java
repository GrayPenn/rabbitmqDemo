package com.young.rabbitmq.demo4.controller;

import com.young.rabbitmq.demo4.common.DelayTypeEnum;
import com.young.rabbitmq.demo4.producer.DelayMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

/**
 * @author pgy
 * @date 2021/1/18 4:02 下午
 **/
@Slf4j
@RequestMapping("rabbitmq")
@RestController
public class RabbitMQDelayMsgController {

    @Autowired
    private DelayMessageSender sender;

    @RequestMapping("sendDelayMsg")
    public void sendMsg(String msg, Integer delayType){
        log.info("当前时间：{},收到请求，msg:{},delayType:{}", new Date(), msg, delayType);
        sender.sendMsg(msg, Objects.requireNonNull(DelayTypeEnum.getDelayTypeEnumByValue(delayType)));
    }


    @RequestMapping("delayMsg")
    public void delayMsg(String msg, Integer delayTime) {
        log.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), msg, delayTime);
        sender.sendMsg(msg, delayTime);
    }

    @RequestMapping("delayMsg2")
    public void delayMsg2(String msg, Integer delayTime) {
        log.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), msg, delayTime);
        sender.sendDelayMsg(msg, delayTime);
    }

}