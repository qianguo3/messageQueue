package com.badu.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsTopicConsumer2 {

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

        MessageConsumer messageConsumer;
        messageConsumer = session.createConsumer(destination);

      Message message;
      while ((message=messageConsumer.receive())!=null){

          System.out.println("消费者接收的消息为："+((TextMessage)message).getText());
      }

    }

}
