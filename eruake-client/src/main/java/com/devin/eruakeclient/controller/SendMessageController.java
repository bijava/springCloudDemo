package com.devin.eruakeclient.controller;

import com.devin.eruakeclient.service.SendMessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用SendMessageInterface中的消息渠进行发送消息
 */
@RestController
public class SendMessageController {

    @Autowired
    private SendMessageInterface sendMessageInterface;

    @GetMapping("/send")
    public String sendMsg() {

        Message msg = MessageBuilder.withPayload("Hello World".getBytes())
                .build();

        sendMessageInterface.sendMsg().send(msg);
        return "Success";
    }
}
