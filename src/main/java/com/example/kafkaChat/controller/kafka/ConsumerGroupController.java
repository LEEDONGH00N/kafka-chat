package com.example.kafkaChat.controller.kafka;

import com.example.kafkaChat.config.BaseResponse;
import com.example.kafkaChat.service.kafka.ConsumerGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("consumer-group")
public class ConsumerGroupController{

    private final ConsumerGroupService consumerGroupService;

    @GetMapping("/all")
    public BaseResponse<List<ConsumerGroupListing>> getAllConsumerGroup(){
        List<ConsumerGroupListing> consumerGroupList = null;
        try{
            consumerGroupList = consumerGroupService.getAllConsumerGroup();
            return new BaseResponse<>(consumerGroupList);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
