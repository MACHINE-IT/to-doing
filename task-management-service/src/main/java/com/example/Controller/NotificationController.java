package com.example.Controller;

import com.example.response.NotificationDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @MessageMapping("/notification")
    @SendTo("/topic/notification")
    public NotificationDTO sendNotification() {
        System.out.println("This is a notification message.");
        return null;
    }

    @MessageMapping("/send.anything")
    @SendTo("/topic/public")
    public String hello(String string) {
        System.out.println("received! " + string);
        return string;
    }
}
