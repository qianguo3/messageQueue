package com.badu.queue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

@Component
public class QueueSender {

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    @Resource
    private getResponser getResponser;

    public  void  send(String queueName,String message){
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message1 = session.createTextMessage(message);
                Destination temporaryQueue = session.createTemporaryQueue();
                MessageConsumer consumer = session.createConsumer(temporaryQueue);
                consumer.setMessageListener(getResponser);
                message1.setJMSReplyTo(temporaryQueue);
                return message1;
            }
        });

    }
}
