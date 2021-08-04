package com.badu.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsTopicProduct {

    private static  final  String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static final  String PASSWORD
            =ActiveMQConnection.DEFAULT_PASSWORD;

    private static  final  String URL
            =ActiveMQConnection.DEFAULT_BROKER_URL;

    private static  final  int SENDNUM
            =3;


    public static void main(String[] args) throws JMSException {


        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);

        Connection connection;
        connection = connectionFactory.createConnection();
        connection.start();

        Session session;
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination;
        destination = session.createTopic("topicMessage");

        MessageProducer messageProducer;
        messageProducer = session.createProducer(destination);

        for (int i = 0; i < SENDNUM; i++) {
            String msg = "Product send Message"+i;
            TextMessage textMessage = session.createTextMessage(msg);
            System.out.println("生产者发送消息为："+textMessage.getText());
            messageProducer.send(textMessage);
        }

        if (connection!=null){
            connection.close();
        }

    }

}
