package com.badu.dlq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class DlpConsumerOther {

    private static final String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD
            = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String URL
            = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {

        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, URL);
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("DLQ.>");
            messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener((message) -> {

                try {
                    System.out.println("DLQ acceptMessage:" + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}
