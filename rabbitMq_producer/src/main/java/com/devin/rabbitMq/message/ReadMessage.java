package com.devin.rabbitMq.message;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ReadMessage {

    public static void main(String[] args) throws Exception {

        // 建立连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 创建新的连接
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String queueName = "SpringCloudMq";

        channel.queueDeclare(queueName, false, false, false, null);

        // 通过队列创建Consumer
        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("接收到的消息：" + msg);
            }

        };

        // 渠道绑定consumer
        channel.basicConsume(queueName, consumer);
    }
}
