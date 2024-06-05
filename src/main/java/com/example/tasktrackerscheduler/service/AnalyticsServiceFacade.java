package com.example.tasktrackerscheduler.service;

import java.util.List;

import com.example.tasktrackerscheduler.dto.EmailMessage;

public interface AnalyticsServiceFacade {

	List<EmailMessage> createEmailMessagesForAllUsers();
}
