package com.badu.emebed;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class embedConsumer {

    private static  final  String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private  static  final  String PASSWORD
            =ActiveMQConnection.DEFAULT_PASSWORD;

    private  static  final  String URL=
            "tcp://localhost:62000";

    private static  final  int SENDNUM = 3;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        Connection connection=null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("embedMQ");
            messageConsumer=session.createConsumer(destination);
            Message message;
            while ((message=messageConsumer.receive())!=null){
                System.out.println("接收的消息为："+((TextMessage)message).getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}
