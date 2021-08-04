package com.badu.wildcards;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class WildCardsConusmer {

    private static final String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD
            =ActiveMQConnection.DEFAULT_PASSWORD;

    private static  final String URL
            =ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {

        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        Connection connection = null;
        Session session;
        MessageConsumer messageConsumer;
        Destination destination;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination=session.createQueue(">.thread");
            messageConsumer=session.createConsumer(destination);
            Message message;
            while ((message=messageConsumer.receive())!=null){
                System.out.println("receviver message:"+((TextMessage)message).getText());
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }


    }


}
