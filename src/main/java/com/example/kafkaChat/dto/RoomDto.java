package com.example.kafkaChat.dto;

import com.example.kafkaChat.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private String roomName;
    public static RoomDto entityToDto(Room chatRoom){
        return new RoomDto(chatRoom.getId(),
                chatRoom.getRoomName());
    }
}
