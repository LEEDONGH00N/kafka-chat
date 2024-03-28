package com.example.kafkaChat.model;

import com.example.kafkaChat.dto.ChatDto;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "chat")
@EntityListeners(AuditingEntityListener.class)
public class Chat{
    public enum MessageType {
        CHAT, JOIN, QUIT;
    }

    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private String id;

    @Field(name = "sender_id")
    private String sender_id;

    @Field(name = "message")
    private String message;

    @Field(name = "room_id")
    private Long roomId;

    @Field(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Field(name = "message_type")
    private MessageType messageType;

    @Builder
    public Chat(String sender_id, String message, Long roomId, MessageType messageType, LocalDateTime createdAt) {
        this.sender_id = sender_id;
        this.message = message;
        this.roomId = roomId;
        this.messageType = messageType;
        this.createdAt = createdAt;
    }

    public Chat(String message){
        this.message = message;
    }

    public static Chat createMessage(ChatDto dto){
        return new Chat(
                dto.getId(),
                dto.getMessage(),
                dto.getRoomId(),
                dto.getMessageType(),
                dto.getCreatedAt()
        );
    }
}