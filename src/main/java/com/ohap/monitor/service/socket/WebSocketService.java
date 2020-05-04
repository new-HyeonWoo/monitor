package com.ohap.monitor.service.socket;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate=simpMessagingTemplate;
    }


    public void broadcastSpittle(String jsonStr){
        String id = "1234";
        String message = "123456";
        String msg = "{\"sendId\" : \"" + id + "\", \"content\" : \"" + message + "\"}";
        this.simpMessagingTemplate.convertAndSend("/chart", msg); //메시지 전송
    }

}
