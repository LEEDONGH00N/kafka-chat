package com.example.kafkaChat.repository;

import com.example.kafkaChat.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {

}
