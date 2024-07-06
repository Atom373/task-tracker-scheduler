package com.example.tasktrackerscheduler.schedulingtask;

import com.example.tasktrackerscheduler.dto.EmailMessage;
import com.example.tasktrackerscheduler.service.AnalyticsServiceFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Testcontainers
public class EmailMessageEnqueuerTest {

    @MockBean
    private AnalyticsServiceFacade analyticsService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EmailMessageEnqueuer emailMessageEnqueuer;

    @Container
    static RabbitMQContainer rabbitmq = new RabbitMQContainer("rabbitmq:management");

    @DynamicPropertySource
    public static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", rabbitmq::getHost);
        registry.add("spring.rabbitmq.port", rabbitmq::getAmqpPort);
        registry.add("spring.rabbitmq.username", rabbitmq::getAdminUsername);
        registry.add("spring.rabbitmq.password", rabbitmq::getAdminPassword);
        registry.add("spring.rabbitmq.queue-name", () -> "EMAIL_SENDING_TASKS");
    }

    @BeforeEach
    public void setUp() {
        EmailMessage message = new EmailMessage();
        when(analyticsService.createEmailMessagesForAllUsers()).thenReturn(Collections.singletonList(message));
    }

    //@Test
    public void enqueueMessages() {
        emailMessageEnqueuer.enqueueMessages();

        verify(analyticsService).createEmailMessagesForAllUsers();
        verify(rabbitTemplate).convertAndSend("EMAIL_SENDING_TASKS", any(EmailMessage.class));
    }
}
