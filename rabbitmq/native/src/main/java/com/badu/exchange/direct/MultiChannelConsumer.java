package com.badu.exchange.direct;

import com.rabbitmq.client.*;
import com.sun.jmx.snmp.tasks.Task;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * @author guo qian
 */
public class MultiChannelConsumer {

    static class  TaskThread implements Runnable{

        private Connection connection;


        public TaskThread(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                Channel channel = connection.createChannel();
                channel.exchangeDeclare(DirectorProduct.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
                String queueName = "multiChannel";
                channel.queueDeclare(queueName,false,false,false,null);
                String[] roukeyes = {"warn","info","error"};
                for (String roukeye : roukeyes) {
                    channel.queueBind(queueName,DirectorProduct.EXCHANGE_NAME,roukeye);
                }
                Consumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println(Thread.currentThread().getName()+":receiveMessage:"+new String(body, Charset.forName("UTF-8")));
                    }
                };

                channel.basicConsume(queueName,true,consumer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        for (int i = 0; i < 1; i++) {
            Thread thread = new Thread(new TaskThread(connection));
            thread.start();
        }
    }


}
