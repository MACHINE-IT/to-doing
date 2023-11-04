package com.example.websocket;

import com.example.taskmanagementservice.TaskManagementServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TaskManagementServiceApplication.class)
public class websocketServerTest {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public websocketServerTest(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Test
    public void testWebSocketServer() {
        simpMessagingTemplate.convertAndSend("/topic/notifications", "test");
    }
}
