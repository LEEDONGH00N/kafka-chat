package com.example.kafkaChat.controller.kafka;

import com.example.kafkaChat.config.BaseException;
import com.example.kafkaChat.config.BaseResponse;
import com.example.kafkaChat.config.BaseResponseStatus;
import com.example.kafkaChat.service.kafka.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("topics")
public class TopicController {

    private final TopicService topicService;
    @GetMapping("all")
    public BaseResponse<Set<String>> getAllTopics() throws ExecutionException, InterruptedException {
        Set<String> result = topicService.getAllTopics();
        return new BaseResponse<>(result);
    }

    @GetMapping("{topicName}")
    public BaseResponse<Map<String, TopicDescription>> getTopic(@PathVariable String topicName){
        try{
            if(!topicService.isTopicExist(topicName))
                throw new BaseException(BaseResponseStatus.NO_TOPIC);
            Map<String, TopicDescription> topicInfo = topicService.getTopic(topicName);
            log.info(topicInfo.toString());
            return new BaseResponse<>(topicInfo);
        }catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
