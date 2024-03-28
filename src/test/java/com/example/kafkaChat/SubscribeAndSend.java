package com.example.kafkaChat;

import com.example.kafkaChat.dto.SlimChatDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SubscribeAndSend {
    @Autowired
    ObjectMapper objectMapper;

    @LocalServerPort
    private int port;
    private WebSocketStompClient webSocketStompClient;
    private BlockingQueue blockingQueue;
    private List<StompSession> stompSessions;
    private String url;

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    @BeforeEach
    public void setup() {
        blockingQueue = new LinkedBlockingQueue();
        webSocketStompClient = new WebSocketStompClient(new StandardWebSocketClient());
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompSessions = new ArrayList<>(500);
        url = "ws://localhost:" + port + "/stomp";

    }

    @Test
    public void subscribe() throws ExecutionException, InterruptedException {
        StompSession session = webSocketStompClient
                .connectAsync(url, new StompSessionHandlerAdapter() {
                }).get();
        session.subscribe("/topic/chat", new StompSessionHandlerAdapter() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return SlimChatDto.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                log.info("payload : " + payload.toString());
            }
        });
        SlimChatDto dto = new SlimChatDto("chat", "LEE", "test-message");
        session.send("/topic/" + dto.getRoomName(), dto);
    }
}