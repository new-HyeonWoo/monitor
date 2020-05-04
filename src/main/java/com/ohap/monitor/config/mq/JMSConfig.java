package com.ohap.monitor.config.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class JMSConfig {

	@Value("${activemq.broker.url}")
	private String brokerUrl;
	@Value("${activemq.broker.user}")
	private String brokerUser;
	@Value("${activemq.broker.password}")
	private String brokerPassword;

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		return new ActiveMQConnectionFactory(brokerUser, brokerPassword, brokerUrl);
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory());
		factory.setConcurrency("50");

		return factory;
	}
}
