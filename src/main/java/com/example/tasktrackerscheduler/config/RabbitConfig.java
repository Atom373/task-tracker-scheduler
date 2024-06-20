package com.example.tasktrackerscheduler.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Value("${spring.rabbitmq.queue-name}")
	private String queueName;
	
	@Bean
	public ConnectionFactory connectionFactory() {
		return new CachingConnectionFactory("localhost");
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}
	
	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}
}
