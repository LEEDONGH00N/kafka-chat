package com.example.kafkaChat;

import com.example.kafkaChat.dto.SlimChatDto;
import com.example.kafkaChat.service.kafka.ChatConsumer;
import com.example.kafkaChat.service.kafka.ChatProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
@EmbeddedKafka(
        topics = {"likelion-chat"},
        partitions = 1,
        ports = {9092},
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092"}
)
public class KafkaChatTest {

    @Autowired
    ChatProducer chatProducer;

    @Autowired
    ChatConsumer chatConsumer;

    @Test
    public void test() throws InterruptedException {
        String topic = "likelion-chat";
        SlimChatDto payload = SlimChatDto.builder()
                .roomName(topic)
                .message("this is a test message_num1")
                .sender("CHUL SOO")
                .build();

        SlimChatDto payload2 = SlimChatDto.builder()
                .roomName(topic)
                .message("this is a test message_num2")
                .sender("YOUNG HEE")
                .build();
        try{
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    chatProducer.sendToKafka(payload);
                } else {
                    chatProducer.sendToKafka(payload2);
                }
            }
        }catch (Exception e){
            log.info("error : {}", e.getMessage());
        }

        chatConsumer.getLatch().await(10, TimeUnit.SECONDS);
        System.out.println("============================================================");
        System.out.println(chatConsumer.getPayloads().size());
        System.out.println(chatConsumer.getPayloads());

    }
}
