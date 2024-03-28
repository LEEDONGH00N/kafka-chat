package com.example.kafkaChat.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class KafkaAdminClientConfig {
    @Bean
    AdminClient kafkaAdminClient() {
        Properties props = new Properties();
        props.put(AdminClientConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, 10_000); // 10s
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 10_000); // 10s
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
        return AdminClient.create(props);
    }

}
