package com.example.kafkaChat.model;

import com.example.kafkaChat.dto.NewRoomRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
@Entity
@Table(name = "chat_room")
public class Room extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    @Column(name = "chat_room_user_list")
    private List<User> chatroomUserList = new ArrayList<>();

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public static Room create(NewRoomRequestDto dto) {
        return new Room(
                dto.getName()
        );
    }
}
