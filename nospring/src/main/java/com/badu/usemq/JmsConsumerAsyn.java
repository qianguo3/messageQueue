package com.badu.usemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumerAsyn {

    private static  final  String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static  final  String PASSWORD
            =ActiveMQConnection.DEFAULT_PASSWORD;

    private static  final String URL
            =ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {


        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);

        Connection connection;
        connection = connectionFactory.createConnection();

        connection.start();

        Session session;
        session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination;
        destination = session.createQueue("HelloActiveMqQueue");

        MessageConsumer messageConsumer;
        messageConsumer = session.createConsumer(destination);

        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        System.out.println("end");

    }

}
