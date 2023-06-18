package com.milton.chat.controller;

import com.milton.chat.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("/topic/general/messages")
    public Message messages(Message message) {
        return message;
    }

}
