package com.young.rabbitmq.demo2.controller;

import com.young.rabbitmq.common.RabbitMQConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * 推送消息存在四种情况：
 * 1、消息推送到server，但是在server里找不到交换机
 * 2、消息推送到server，找到交换机了，但是没找到队列
 * 3、消息推送到sever，交换机和队列啥都没找到
 * 4、消息推送成功
 *
 *
 * @author pgy
 * @date 2021/1/15 11:26 上午
 **/
@RestController
public class AckTestController {

    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法


    /**
     * 1、消息推送到server，但是在server里找不到交换机
     * 写个测试接口，把消息推送到名为‘non-existent-exchange’的交换机上（这个交换机是没有创建没有配置的）：
     *
     * 返回值
     * 2021-01-15 15:11:26.993 ERROR 60356 --- [ 127.0.0.1:5672] o.s.a.r.c.CachingConnectionFactory       : Shutdown Signal: channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'non-existent-exchange' in vhost '/', class-id=60, method-id=40)
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：false
     * ConfirmCallback:     原因：channel error; protocol method: #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'non-existent-exchange' in vhost '/', class-id=60, method-id=40)
     *
     * */
    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";
    }


    /**
     * 消息推送到server，找到交换机了，但是没找到队列
     * 这种情况就是需要新增一个交换机，但是不给这个交换机绑定队列，
     * 在 DirectRabbitConfig 里面新增一个直连交换机，名叫‘lonelyDirectExchange’，但没给它做任何绑定配置操作：
     *
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：true
     * ConfirmCallback:     原因：null
     *
     * ReturnCallback:     消息：(Body:'{createTime=2021-01-15 15:16:24, messageId=5d7771c3-5888-4149-b1c2-9e8c4dc1fd59, messageData=message: lonelyDirectExchange test message }' MessageProperties [headers={}, contentType=application/x-java-serialized-object, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0])
     * ReturnCallback:     回应码：312
     * ReturnCallback:     回应信息：NO_ROUTE
     * ReturnCallback:     交换机：lonelyDirectExchange
     * ReturnCallback:     路由键：TestDirectRouting
     */
    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }


    /**
     *
     * 3、消息推送到sever，交换机和队列啥都没找到
     * 这种情况其实一看就觉得跟①很像，没错 ，3和1情况回调是一致的，所以不做结果说明了。
     *   结论： 3这种情况触发的是 ConfirmCallback 回调函数。
     */


    /**
     * 消息推送成功
     * 那么测试下，按照正常调用之前消息推送的接口就行，就调用下 /sendFanoutMessage 接口，可以看到控制台输出：
     *
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：true
     * ConfirmCallback:     原因：null
     */



}