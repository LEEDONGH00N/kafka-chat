package com.example.kafkaChat.repository;

import com.example.kafkaChat.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomName(String name);
}
