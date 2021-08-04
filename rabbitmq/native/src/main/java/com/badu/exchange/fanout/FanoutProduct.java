package com.badu.exchange.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author guo qian
 */
public class FanoutProduct {

    public static  final  String EXCHANGE_NAME = "fanoutProduct";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        String[] reokeys = {"warn","info","error"};
        for (String reokey : reokeys) {
            String message= reokey;
            System.out.println("sendMessage:"+message);
            channel.basicPublish(EXCHANGE_NAME,reokey,null,message.getBytes());
        }
        if (channel!=null){
            channel.close();
        }
        if (connection!=null){
            connection.close();
        }

    }
}
