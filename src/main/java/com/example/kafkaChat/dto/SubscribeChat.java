package com.example.kafkaChat.dto;

import com.example.kafkaChat.model.Chat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SubscribeChat {
    private String senderName;
    private String message;
    private Long senderId;
    private Long roomId;
    private LocalDateTime createdAt;
    private Chat.MessageType messageType;
}
