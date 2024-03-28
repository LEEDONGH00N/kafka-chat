package com.example.kafkaChat;

import com.example.kafkaChat.dto.SlimChatDto;
import com.example.kafkaChat.service.kafka.ChatProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(
        topics = "test",
        partitions = 1,
        brokerProperties = {
        "listener=PLAINTEXT://localhost:9092"
        },
        ports = {9092})
public class KafkaProducerTest {
    @Autowired
    ChatProducer chatProducer;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test() throws Exception {
        // given
        SlimChatDto dto = SlimChatDto.builder()
                .roomName("test")
                .sender("LEE")
                .message("this is producer test")
                .build();
        // when
        chatProducer.sendToKafka(dto);

    }
}