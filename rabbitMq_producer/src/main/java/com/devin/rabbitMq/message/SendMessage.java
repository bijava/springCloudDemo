package com.devin.rabbitMq.message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendMessage {

    public static void main(String[] args) throws Exception {
        /*
         * 1.生产者会发送消息给RabbitMQ服务器。
         * 2.通过渠道叫消息发送给交换器。
         * 3.交换器会发送给队列。
         * 4.队列将消息发送给消费者。
         */

        // 建立连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置host，其实无需设置，默认为localhost，用户名/密码默认guest，端口默认5671
        // factory.setHost("localhost");

        // 创建新的连接
        Connection connection = factory.newConnection();

        // 通过连接创建渠道（向该渠道发送消息）
        Channel channel = connection.createChannel();

        // 声明交换器(默认绑定)，交换器会将消息发送给队列，对列再发送给消费者
        // 直接声明队列，使用默认交换器
        String queueName = "SpringCloudMq";

        channel.queueDeclare(queueName, false, false, false, null);

        // 创建消息，使用渠道发布消息，""使用默认交换器，本列子中routingKey就使用queueName
        String messageBody = "这里可以是任何内容,实际应用中可能是JSON或者XML较多";
        channel.basicPublish("", queueName, null, messageBody.getBytes());

        // 发送之后，关闭渠道等(先关渠道，再关连接)
        channel.close();
        connection.close();

        System.out.println(">>>>>> end ----------");

    }

}
