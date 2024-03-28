package com.example.kafkaChat.service;

import com.example.kafkaChat.config.BaseException;
import com.example.kafkaChat.config.BaseResponseStatus;
import com.example.kafkaChat.dto.UserDto;
import com.example.kafkaChat.model.User;
import com.example.kafkaChat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(String name){
        User user = new User(name);
        userRepository.save(user);
        return UserDto.entityToDto(user);
    }

    public User findUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_USER));
    }
}
