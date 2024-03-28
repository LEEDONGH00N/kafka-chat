package com.example.kafkaChat.dto;

import com.example.kafkaChat.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String name;
    private Long id;

    public UserDto(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public UserDto(String name) {
        this.name = name;
    }

    public static UserDto entityToDto(User user){
        return new UserDto(user.getName(), user.getId());
    }
}
