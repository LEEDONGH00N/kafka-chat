package com.example.kafkaChat.dto;

import com.example.kafkaChat.model.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatDto {
    private String senderName;
    private String id;
    private String message;
    private Long senderId;
    private Long roomId;
    private LocalDateTime createdAt;
    private Chat.MessageType messageType;
}
