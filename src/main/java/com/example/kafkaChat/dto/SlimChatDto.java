package com.example.kafkaChat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlimChatDto {
    private String roomName;
    private String sender;
    private String message;

    @Builder
    public SlimChatDto(String roomName, String sender, String message) {
        this.roomName = roomName;
        this.sender = sender;
        this.message = message;
    }
}