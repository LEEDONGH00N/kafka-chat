package com.example.kafkaChat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicRequestDto {
    private String name;
    private int partitions = 2;
    private short replicas = 1;
    public TopicRequestDto(String name) {
        this.name = name;
    }
}
