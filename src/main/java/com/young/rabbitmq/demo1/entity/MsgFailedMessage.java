package com.young.rabbitmq.demo1.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author pgy
 * @date 2021/1/14 5:41 下午
 **/
@Data
public class MsgFailedMessage {

    /**
     * id
     */
    private Long id;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由键
     */
    private String routingKey;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

}
