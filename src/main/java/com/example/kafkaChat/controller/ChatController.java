package com.example.kafkaChat.controller;

import com.example.kafkaChat.dto.SlimChatDto;
import com.example.kafkaChat.service.kafka.ChatProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final ChatProducer chatProducer;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @SubscribeMapping("/topic/chat")
    public void subscribeTest(SlimChatDto dto){
        log.info("subscribe message : " + dto.getMessage());
    }

    @MessageMapping("/chat")
    public void sendTest(@RequestBody SlimChatDto dto){
        log.info("send to : " + dto.getRoomName());
        log.info("message : " + dto.getMessage());
        chatProducer.sendToKafka(dto);
    }
    @MessageMapping("{topic}")
    public void send(@RequestBody SlimChatDto dto,
                     @DestinationVariable String topic){
        log.info("{topic}");
        log.info("send to : " + dto.getRoomName());
        log.info("message : " + dto.getMessage());
        simpMessagingTemplate.convertAndSend("/topic/chat/" + topic, dto);
    }

}
