package com.badu.durabletopic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class DruableTopicConsumer {

    private  static final  String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static  final  String PASSWORD
            =ActiveMQConnection.DEFAULT_PASSWORD;

    private static  final String URL
            =ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final int SENDNUM
            =3;


    public static void main(String[] args) {

        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        Connection connection=null;
        Session session;
        Topic destination;
        MessageConsumer messageConsumer;
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("Lison1");
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination=session.createTopic("druTopic1");
            messageConsumer=session.createDurableSubscriber(destination,"any-name");

            Message message;
            while ((message=messageConsumer.receive())!=null){

                System.out.println("消费者收到消息为："+((TextMessage)message).getText());
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
