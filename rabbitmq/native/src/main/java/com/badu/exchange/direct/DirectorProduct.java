package com.badu.exchange.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;



public class DirectorProduct {

    public  static  final  String EXCHANGE_NAME = "direct_product";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory  connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String[] roukeyes = {"warn","info","error"};
        for (int i = 0; i < roukeyes.length; i++) {

            String roukey = roukeyes[i % (roukeyes.length)];
            String msg = i+":"+roukey;
            System.out.println("sendMessage:"+msg);
            channel.basicPublish(EXCHANGE_NAME,roukey,null,msg.getBytes());
        }

        if (channel!=null){
            channel.close();
        }
        if (connection!=null){
            connection.close();
        }
    }

}
