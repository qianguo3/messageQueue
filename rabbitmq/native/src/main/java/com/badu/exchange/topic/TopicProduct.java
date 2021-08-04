package com.badu.exchange.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author guo qian
 */
public class TopicProduct {

    public static  final String EXCHANGE_NAME = "topic_prodct";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String[] roukeys = {"warn","info","error"};
        for (String roukey : roukeys) {
            String[] roukeys1 = {"phone","mobile","email"};
            for (String s : roukeys1) {
                String[] roukeys2 = {"school","hotel","company"};
                for (String s1 : roukeys2) {
                    String msg = roukey+":"+s+":"+s1;
                    System.out.println("发送消息为,"+msg);
                    String key =roukey+"."+s+"."+s1;
                    channel.basicPublish(EXCHANGE_NAME,key,null,msg.getBytes());
                }
            }
        }
        if (channel!=null){
            channel.close();
        }
        if (connection!=null){
            connection.close();
        }
    }
}
