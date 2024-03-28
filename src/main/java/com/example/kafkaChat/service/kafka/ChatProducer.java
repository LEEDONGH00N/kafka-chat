package com.example.kafkaChat.service.kafka;

import com.example.kafkaChat.dto.SlimChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatProducer {
    private final KafkaTemplate<String, SlimChatDto> kafkaTemplate;
    public void sendToKafka(SlimChatDto dto){
        CompletableFuture<SendResult<String, SlimChatDto>> result = kafkaTemplate.send(dto.getRoomName(), dto);
        log.info("result : {}", result);
    }
}
