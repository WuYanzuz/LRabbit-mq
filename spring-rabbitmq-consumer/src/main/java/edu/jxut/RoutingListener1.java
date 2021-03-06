package edu.jxut;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

public class RoutingListener1 implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            String msg = new String(message.getBody(), "UTF-8");
            System.out.printf("接收路由名称为：%s，路由键为：%s，队列名为：%s的消息1：%s \n",
                    message.getMessageProperties().getReceivedExchange(),
                    message.getMessageProperties().getReceivedRoutingKey(),
                    message.getMessageProperties().getConsumerQueue(),
                    msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
