package edu.jxut;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer_topic {
    //交换机名称
    static final String FANOUT_EXCHAGE = "topic_exchange";
    //队列名称
    static final String FANOUT_QUEUE_1 = "topic_queue_1";
    //队列名称
    static final String FANOUT_QUEUE_2 = "topic_queue_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1建立连接
        ConnectionFactory factory=new ConnectionFactory();
        //2设置参数
        factory.setHost("211.159.223.55");
        factory.setPort(5672);
        factory.setVirtualHost("/itcast");
        factory.setUsername("heima");
        factory.setPassword("heima");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(FANOUT_QUEUE_1,true,false,false,null);
        channel.queueDeclare(FANOUT_QUEUE_2,true,false,false,null);

        channel.exchangeDeclare(FANOUT_EXCHAGE, BuiltinExchangeType.TOPIC);

        channel.queueBind(FANOUT_QUEUE_1, FANOUT_EXCHAGE, "*.*");
        channel.queueBind(FANOUT_QUEUE_2, FANOUT_EXCHAGE, "#.order");
        channel.queueBind(FANOUT_QUEUE_2, FANOUT_EXCHAGE, "error.#");

        for (int i = 0; i < 10; i++) {
            channel.basicPublish(FANOUT_EXCHAGE,"read.oz",null,("你好topic"+i).getBytes());
        }

// 关闭资源
        channel.close();
        connection.close();
    }
}
