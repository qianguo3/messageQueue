package com.badu.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

public class NormalDirectorConsumer01 {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DirectorProduct.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String queueName = "directQueue01";
        channel.queueDeclare(queueName,false,false,false,null);

        String[] roukeyes = {"warn","info","error"};
        for (int i = 0; i < roukeyes.length; i++) {
            String roukeye = roukeyes[i % roukeyes.length];
            channel.queueBind(queueName,DirectorProduct.EXCHANGE_NAME,roukeye);
        }

       Consumer consumer = new DefaultConsumer(channel){
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               System.out.println("receive message:"+new String(body, Charset.forName("UTF-8")));
           }
       };
        channel.basicConsume(queueName,true,consumer);


    }
}
