package com.example.kafkaChat.service.kafka;

import com.example.kafkaChat.config.kafka.KafkaConstants;
import com.example.kafkaChat.dto.SlimChatDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@Data
@RequiredArgsConstructor
public class ChatConsumer {

    private CountDownLatch latch = new CountDownLatch(10);

    private final SimpMessagingTemplate simpMessagingTemplate;
    private List<SlimChatDto> payloads = new ArrayList<>();
    private SlimChatDto payload;


    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeTest(ConsumerRecord<String, SlimChatDto> consumerRecord){
        try{
            payload = consumerRecord.value();
            log.info("received payload = {}", payload.toString());
            payloads.add(payload);
            simpMessagingTemplate.convertAndSend("/topic/chat", payload);
            latch.countDown();
        }catch (Exception e){
            log.info(e.getMessage());
        }

    }
    public List<SlimChatDto> getPayloads() {
        return payloads;
    }
    public void resetLatch() {
        latch = new CountDownLatch(1);
    }
}