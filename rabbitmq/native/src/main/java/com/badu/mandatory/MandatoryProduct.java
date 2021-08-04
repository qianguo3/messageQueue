package com.badu.mandatory;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * @author guo qian
 */
public class MandatoryProduct {

    protected  static final  String EXCHANGE_NAME = "mandatory_product";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String[] routingKeys = {"warn","info","error"};
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println(i+"\n"+s+"\n"+s1+"\n"+s2+"\n"+basicProperties+"\n"+new String(bytes, Charset.forName("UTF-8")));
            }
        });

        for (String routingKey : routingKeys) {
            String msg = routingKey;
            System.out.println("sendMessage:"+msg);
            channel.basicPublish(EXCHANGE_NAME,routingKey,false,null,msg.getBytes());
        }
//        if (channel!=null){
//            channel.close();
//        }
//        if (connection!=null){
//            connection.close();
//        }

    }
}
