package com.ohap.monitor.mq.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

	@JmsListener(destination = "${activemq.queue.name}")
	public void receive(String message) {
		log.info("Received Message '{}'", message);
	}
}
