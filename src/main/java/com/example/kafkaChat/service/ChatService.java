package com.example.kafkaChat.service;

import com.example.kafkaChat.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final SimpMessagingTemplate template;
    public void sendChat(ChatDto dto){

    }
}
