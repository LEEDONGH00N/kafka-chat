package com.example.kafkaChat.config.kafka;

import java.util.List;

public class KafkaConstants {
    public static final String KAFKA_TOPIC = "likelion-chat";
    public static final String GROUP_ID = "consumer_";
    public static final String KAFKA_BROKER = "localhost:9092";
    public static List<Integer> partitionList;
}