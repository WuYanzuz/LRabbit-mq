package edu.jxut;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer_workQueen {
    public static void main(String[] args) throws IOException, TimeoutException {

        //1建立连接
        ConnectionFactory factory=new ConnectionFactory();
        //2设置参数
        factory.setHost("211.159.223.55");
        factory.setPort(5672);
        factory.setVirtualHost("/itcast");
        factory.setUsername("heima");
        factory.setPassword("heima");



        //3创建连接
        Connection connection = factory.newConnection();
        //4创建channel
        Channel channel = connection.createChannel();
        //5创建队列
        channel.queueDeclare("work_queen",true,false,false,null);

        for (int i = 0; i < 50; i++) {
            channel.basicPublish("","work_queen",null,("你好"+i).getBytes());
        }

        channel.close();
        connection.close();

    }
}
