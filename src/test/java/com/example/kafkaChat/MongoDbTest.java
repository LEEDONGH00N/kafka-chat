package com.example.kafkaChat;

import com.example.kafkaChat.model.Chat;
import com.example.kafkaChat.repository.ChatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MongoDbTest {

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void test(){
        Chat chat = new Chat("this is mongo test");
        Chat get = chatRepository.save(chat);

        Assertions.assertEquals(chat.getMessage(), get.getMessage());
    }
}
