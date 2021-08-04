package com.badu.wildcards;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class WildCardsProduct {

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
        MessageProducer messageProducer;
        Destination destination;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination=session.createQueue("xiangxue.vip.program.thread");
            for (int i = 0; i < 3; i++) {
                String msg = "Send message:"+i;
                TextMessage textMessage = session.createTextMessage(msg);
                System.out.println("发送的消息为："+textMessage.getText());
                messageProducer=session.createProducer(destination);
                messageProducer.send(textMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }


}
