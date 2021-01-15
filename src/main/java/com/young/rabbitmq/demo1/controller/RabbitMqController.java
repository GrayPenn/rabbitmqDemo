package com.young.rabbitmq.demo1.controller;

import com.young.rabbitmq.demo1.producer.NormalMessageProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pgy
 * @date 2021/1/14 6:12 下午
 **/
@RestController
@RequestMapping(value = "/demo")
public class RabbitMqController {

    @Autowired
    private NormalMessageProduct normalMessageProduct;

    @GetMapping("/sendNormalMessage")
    public void sendNormalMessage() {
        normalMessageProduct.send("这是普通消息");
    }

}
