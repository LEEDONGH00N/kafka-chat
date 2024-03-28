package com.example.kafkaChat.service.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service
public class ConsumerGroupService extends AbstractKafkaAdminClientService{

    public ConsumerGroupService(AdminClient kafkaAdminClient) {
        super(kafkaAdminClient);
    }
    public List<ConsumerGroupListing> getAllConsumerGroup() throws ExecutionException, InterruptedException {
        return this.kafkaAdminClient
                .listConsumerGroups()
                .all()
                .get()
                .stream()
                .toList();
    }

    public Map<String, Map<TopicPartition, OffsetAndMetadata>> getOffset(String groupId) throws ExecutionException, InterruptedException {
        return this.kafkaAdminClient.listConsumerGroupOffsets(groupId)
                .all()
                .get();
    }

}
