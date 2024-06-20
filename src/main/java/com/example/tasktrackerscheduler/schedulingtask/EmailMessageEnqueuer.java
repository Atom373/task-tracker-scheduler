package com.example.tasktrackerscheduler.schedulingtask;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.tasktrackerscheduler.dto.EmailMessage;
import com.example.tasktrackerscheduler.service.AnalyticsServiceFacade;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailMessageEnqueuer {
	
	private final AnalyticsServiceFacade analyticsService;
	private final RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.queue-name}")
	private String queueName;
	
	@Scheduled(cron = "0 0 23 * * ?")
	//@Scheduled(fixedDelayString = "PT1M")
	public void enqueueMessages() {
		List<EmailMessage> messages = analyticsService.createEmailMessagesForAllUsers();
		messages.forEach( message -> rabbitTemplate.convertAndSend(queueName, messages));
	}
}
