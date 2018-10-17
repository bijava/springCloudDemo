package com.devin.eruakeclient.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * 发送消息的服务接口，用来绑定Topic
 */
public interface SendMessageInterface {

    /**
     * 声明一个方法用来订阅渠道，使用output注解，声明渠道名称，从这里输出一个消息
     */
    @Output("myInput")
    SubscribableChannel sendMsg();
}
