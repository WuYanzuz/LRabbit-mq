package edu.jxut;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.*;
import edu.jxut.util.ConnectionUtil;

import java.io.IOException;

public class Consumer_routing1 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

//        channel.exchangeDeclare("routing_exchange", BuiltinExchangeType.DIRECT);
//
//        channel.queueDeclare("routing_queue_1",true,false,false,null);
//
//        channel.queueBind("routing_queue_1","routing_exchange","info");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"UTF-8"));
            }
        };
        channel.basicConsume("routing_queue_1",true,consumer);
    }
}
