package com.badu.emebed;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class embedProduct {

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
        MessageProducer messageProducer;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("embedMQ");
            messageProducer=session.createProducer(destination);
            for (int i = 0; i < SENDNUM; i++) {
                String msg = "embedMq sendMessage:"+i;
                TextMessage textMessage = session.createTextMessage(msg);
                System.out.println("生产者发送的消息为："+textMessage.getText());
                messageProducer.send(textMessage);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
        finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
