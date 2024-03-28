package com.example.kafkaChat.controller;

import com.example.kafkaChat.config.BaseResponse;
import com.example.kafkaChat.dto.NewUserRequest;
import com.example.kafkaChat.dto.UserDto;
import com.example.kafkaChat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 새로운 유저의 이름을 받아 생성
     * @param newUserRequest
     * @return
     */
    //유저 생성
    @PostMapping("/create")
    public BaseResponse<UserDto> createUser(@RequestBody NewUserRequest newUserRequest){
        return new BaseResponse<>(userService.createUser(newUserRequest.getName()));
    }
}
