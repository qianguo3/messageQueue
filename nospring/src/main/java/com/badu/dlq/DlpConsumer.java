package com.badu.dlq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.region.policy.RedeliveryPolicyMap;

import javax.jms.*;

public class DlpConsumer {

    private static final  String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static final  String PASSWORD
            =ActiveMQConnection.DEFAULT_PASSWORD;

    private static  final  String URL
            =ActiveMQConnection.DEFAULT_BROKER_URL;


    public static void main(String[] args) {

        ActiveMQConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, URL);
        ActiveMQConnection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;

        try {
            connection = (ActiveMQConnection) connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("dlqtest");
            messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("acceptMessage:"+((TextMessage)message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
