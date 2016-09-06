package com.anupam.app.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MQConfig {

	@Bean(initMethod = "start", destroyMethod = "stop")
	public BrokerService broker() throws Exception {
		System.out.println("Brocker started...");
		final BrokerService broker = new BrokerService();
		broker.addConnector("tcp://localhost:61614");
		broker.setPersistent(false);
		final ActiveMQTopic topic = new ActiveMQTopic("jms.topic.test");
		broker.setDestinations(new ActiveMQDestination[] { topic });
		return broker;
	}

	@Bean
	public JmsTemplate jmsTemplate() throws Exception {
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61614");
		JmsTemplate template = new JmsTemplate(cf);
		return template;
	}
}
