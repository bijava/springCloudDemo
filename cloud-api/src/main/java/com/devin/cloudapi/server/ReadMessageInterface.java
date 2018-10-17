package com.devin.cloudapi.server;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 用于接收消息
 */
public interface ReadMessageInterface {

    // 绑定myInput的渠道
    @Input("myInput")
    SubscribableChannel readMsg();
}
