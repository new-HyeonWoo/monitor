package com.ohap.monitor.mq.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	public MessageConsumer(SimpMessagingTemplate simpMessagingTemplate){
		this.simpMessagingTemplate=simpMessagingTemplate;
	}

	@JmsListener(destination = "${activemq.queue.name}")
	public void receive(String message) {
		log.info("Received Message '{}'", message);
		String msg = "{\"content\" : \"" + message + "\"}";
		this.simpMessagingTemplate.convertAndSend("/chart", msg); //메시지 전송

	}
}
