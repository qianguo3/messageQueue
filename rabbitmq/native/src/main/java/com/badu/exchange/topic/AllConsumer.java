package com.badu.exchange.topic;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * @author guo qian
 */
public class AllConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String queue = channel.queueDeclare().getQueue();
        channel.exchangeDeclare(TopicProduct.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        channel.queueBind(queue,TopicProduct.EXCHANGE_NAME,"#");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("routingKey:"+envelope.getRoutingKey()+","+new String(body, Charset.forName("UTF-8")));
            }
        };
        channel.basicConsume(queue,true,consumer);
    }
}
