package com.young.rabbitmq.demo3.controller;

import com.young.rabbitmq.demo3.product.BusinessMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pgy
 * @date 2021/1/15 5:11 下午
 **/
@RequestMapping("rabbitmq")
@RestController
public class RabbitMQMsgController {

    @Autowired
    private BusinessMessageSender sender;

    @RequestMapping("sendmsg")
    public void sendMsg(String msg){
        sender.sendMsg(msg);
    }
}