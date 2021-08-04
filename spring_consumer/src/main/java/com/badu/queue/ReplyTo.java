package com.badu.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Component
public class ReplyTo {

    @Autowired
    @Qualifier("conJmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    public  void replyTo(final String consumerMsg, Message producerMessage) throws JMSException {
                jmsTemplate.send(producerMessage.getJMSReplyTo(), new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        Message msg
                                = session.createTextMessage("ReplyTo " + consumerMsg);
                        return msg;
                    }
                });
    }
}
