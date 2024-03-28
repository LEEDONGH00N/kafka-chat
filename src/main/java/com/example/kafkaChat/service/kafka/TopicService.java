package com.example.kafkaChat.service.kafka;

import com.example.kafkaChat.dto.TopicRequestDto;
import org.apache.kafka.clients.admin.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class TopicService extends AbstractKafkaAdminClientService{
    public TopicService(AdminClient kafkaAdminClient) {
        super(kafkaAdminClient);
    }

    public ListTopicsResult create(TopicRequestDto dto) throws ExecutionException, InterruptedException {
        NewTopic newTopic = new NewTopic(dto.getName(), dto.getPartitions(), dto.getReplicas());
        this.kafkaAdminClient.createTopics(List.of(newTopic))
                .all()
                .get();
        return this.kafkaAdminClient.listTopics();
    }
    public Set<String> getAllTopics() throws ExecutionException, InterruptedException {
        return this.kafkaAdminClient.listTopics()
                .listings()
                .thenApply(it -> it.stream()
                        .map(TopicListing::name)
                        .collect(Collectors.toSet())
                ).get();

    }
    public boolean isTopicExist(String topic) throws ExecutionException, InterruptedException {
        return kafkaAdminClient.listTopics().namesToListings().get().containsKey(topic);
    }

    public Map<String, TopicDescription> getTopic(String topicName) throws ExecutionException, InterruptedException {
        return this.kafkaAdminClient.describeTopics(Collections.singleton(topicName))
                .allTopicNames()
                .get();
    }
}
