package edu.jxut;

import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.*;
import edu.jxut.util.ConnectionUtil;

import java.io.IOException;

public class Consumer_pubSub2 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare("fanout_exchange", BuiltinExchangeType.FANOUT);

        channel.queueDeclare("fanout_queue_2",true,false,false,null);

        channel.queueBind("fanout_queue_2","fanout_exchange","");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"UTF-8"));
            }
        };
        channel.basicConsume("fanout_queue_2",true,consumer);
    }
}
