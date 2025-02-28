package com.union.unionbackend.controllers;

import com.union.unionbackend.dtos.ChatMessageDto;
import com.union.unionbackend.services.chatService.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;

  @MessageMapping("/chat.send")
  @SendTo("/topic/public")
  public ChatMessageDto sendMessage(@Payload ChatMessageDto message) {
    chatService.validateMessage(message);
    return chatService.processMessage(message);
  }
}