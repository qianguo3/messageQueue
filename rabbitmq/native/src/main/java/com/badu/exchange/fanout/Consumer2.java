package com.badu.exchange.fanout;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * @author guo qian
 */
public class Consumer2 {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(FanoutProduct.EXCHANGE_NAME,BuiltinExchangeType.FANOUT);
        String[] roukeys = {"warn","info","error"};
        String queueName = "fanoutQueue1";
        channel.queueDeclare(queueName,false,false,false,null );

            channel.queueBind(queueName,FanoutProduct.EXCHANGE_NAME,"1");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(Thread.currentThread().getName()+":receiveMessage:"+new String(body, Charset.forName("UTF-8")));
            }
        };

        channel.basicConsume(queueName,true,consumer);
    }
}
