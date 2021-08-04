package com.badu.queue;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author wang bin
 */
@Component
public class QueueReceiver1 implements MessageListener {

    @Resource
    private ReplyTo replyTo;


    @Override
    public void onMessage(Message message) {
        try {
            String textMsg = ((TextMessage) message).getText();
            System.out.println("QueueReceiver1 accept msg : " + textMsg);
            replyTo.replyTo(textMsg+":consumer",message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
