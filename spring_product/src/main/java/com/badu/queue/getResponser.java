package com.badu.queue;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class getResponser implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("生产者接收消息为："+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
