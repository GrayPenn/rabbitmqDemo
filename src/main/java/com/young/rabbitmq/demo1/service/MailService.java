package com.young.rabbitmq.demo1.service;

import org.springframework.stereotype.Service;

/**
 * @author pgy
 * @date 2021/1/14 5:37 下午
 **/
@Service
public class MailService {


    /**
     * 发送邮件的方法
     *
     * 三种收件人模式的解释
     * 1，Message.RecipientType.TO  直接接收人
     * 2，Message.RecipientType.CC  明抄送收件人
     * 3，Message.RecipientType.BCc 暗抄送收件人（不会 被直接收件人 和 明抄送收件人 看见的收件人）
     *
     * @param toAddr  收件人的 邮件地址
     * @param ccAddr  抄送人的 邮件地址
     * @param subject 邮件标题
     * @param html    邮件内容
     */
    public void sendMail(String[] toAddr, String[] ccAddr, String subject, String html) {

        System.out.println("========================发送邮件==============================");
    }
}
