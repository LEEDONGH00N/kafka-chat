package com.example.kafkaChat.service;

import com.example.kafkaChat.config.BaseException;
import com.example.kafkaChat.config.BaseResponseStatus;
import com.example.kafkaChat.dto.NewRoomRequestDto;
import com.example.kafkaChat.dto.RoomDto;
import com.example.kafkaChat.model.Room;
import com.example.kafkaChat.model.User;
import com.example.kafkaChat.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final RoomRepository roomRepository;
    public RoomDto createRoom(NewRoomRequestDto dto){
        if(roomRepository.findByRoomName(dto.getName()).isPresent())
            throw new BaseException(BaseResponseStatus.EXIST_CHATROOM);
        Room room = Room.create(dto);
        roomRepository.save(room);
        return RoomDto.entityToDto(room);
    }

    public List<RoomDto> findAllRooms(){
        return roomRepository.findAll().stream()
                .map(RoomDto::entityToDto).collect(Collectors.toList());
    }

    public Room findRoomById(Long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_CHATROOM));
        return room;
    }

    public List<User> addUserToChatroom(User user,
                                        Room room){
        List<User> userList = room.getChatroomUserList();
        userList.add(user);
        return userList;
    }
}
