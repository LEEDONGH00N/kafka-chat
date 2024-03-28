package com.example.kafkaChat.controller;

import com.example.kafkaChat.config.BaseException;
import com.example.kafkaChat.config.BaseResponse;
import com.example.kafkaChat.config.BaseResponseStatus;
import com.example.kafkaChat.dto.NewRoomRequestDto;
import com.example.kafkaChat.dto.RoomDto;
import com.example.kafkaChat.dto.TopicRequestDto;
import com.example.kafkaChat.service.ChatRoomService;
import com.example.kafkaChat.service.kafka.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final TopicService topicService;

    @PostMapping("/create")
    public BaseResponse<?> createChatRoom(@RequestBody NewRoomRequestDto request){
        try{
            if(topicService.isTopicExist(request.getName()))
                throw new BaseException(BaseResponseStatus.EXIST_CHATROOM);
            RoomDto dto = chatRoomService.createRoom(request);
            topicService.create(new TopicRequestDto(request.getName()));
            return new BaseResponse<>(dto);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        } catch (ExecutionException | InterruptedException e) {
            return new BaseResponse<>(e.getMessage());
        }
    }

    @GetMapping("/all")
    public  BaseResponse<List<RoomDto>> listAllRooms(){
        try{
            List<RoomDto> list = chatRoomService.findAllRooms();
            return new BaseResponse<>(list);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
    }
}
