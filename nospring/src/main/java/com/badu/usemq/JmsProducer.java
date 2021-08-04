package com.badu.usemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer {

    private static final String USERNAME
            = ActiveMQConnection.DEFAULT_USER;

    private static final String PASSWORD
            = ActiveMQConnection.DEFAULT_PASSWORD;

    private static final String URL
            = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final int SENDNUM
            = 3;

    public static void main(String[] args) throws JMSException {
        /*连接工厂*/
        ConnectionFactory connectionFactory;
        connectionFactory = new ActiveMQConnectionFactory(null, null, URL);

        /*
        工厂连接
        * */
        Connection connection;
        connection = connectionFactory.createConnection();
        /*
        启动连接
         */
        connection.start();
        /*
        创建session
         */
        Session session;
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        /*
        创建目的地
         */
        Destination destination;
        destination = session.createQueue("HelloActiveMqQueue");

        /*
        创建生产者
         */
        MessageProducer messageProducer;
        messageProducer = session.createProducer(destination);

        for (int i = 0; i < SENDNUM; i++) {
            String msg = "发送消息"+i;
            TextMessage textMessage = session.createTextMessage(msg);
            System.out.println("标准用法:" + msg);
            messageProducer.send(textMessage);
        }
        if (connection!=null){
            connection.close();
        }
    }
}
