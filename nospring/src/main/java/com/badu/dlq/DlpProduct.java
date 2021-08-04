package com.badu.dlq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class DlpProduct {

    private static final  String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static final  String PASSWORD
            =ActiveMQConnection.DEFAULT_PASSWORD;

    private static  final  String URL
            =ActiveMQConnection.DEFAULT_BROKER_URL;

    private static  final  int SENDNUM
            =3;


    public static void main(String[] args) {

        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
        Connection connection=null;
        Session session;
        Destination destination;
        MessageProducer messageProducer;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("dlqtest");
            messageProducer = session.createProducer(destination);

            for (int i = 0; i < SENDNUM; i++) {

                String msg = "sendMessage:"+(i+1);
                TextMessage textMessage = session.createTextMessage(msg);
                System.out.println("发送消息为："+textMessage.getText());
                messageProducer.send(textMessage);
            }
            session.commit();

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
