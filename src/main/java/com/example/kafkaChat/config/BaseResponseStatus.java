package com.example.kafkaChat.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true,HttpStatus.OK.value(), "요청에 성공하였습니다."),
    MATCH(true, HttpStatus.ACCEPTED.value(), "친구 목록에 추가되었습니다."),
    NO_USER(false, HttpStatus.NOT_FOUND.value(), "해당 유저가 존재하지 않습니다."),
    NO_CHATROOM(false, HttpStatus.NOT_FOUND.value(), "존재하지 않는 채팅방입니다."),
    NOT_ENTER_CHAT(false, HttpStatus.FORBIDDEN.value(), "입장하지 못했습니다."),
    EXIST_CHATROOM(false, HttpStatus.CONFLICT.value(), "존재하는 채팅방 이름입니다."),
    NO_TOPIC(false, HttpStatus.NO_CONTENT.value(), "존재하지 않는 토픽입니다.");
    private final boolean isSuccess;
    private final int code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
