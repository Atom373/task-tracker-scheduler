package com.example.tasktrackerscheduler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tasktrackerscheduler.dto.EmailMessage;
import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;
import com.example.tasktrackerscheduler.service.AnalyticsServiceFacade;
import com.example.tasktrackerscheduler.service.TaskService;
import com.example.tasktrackerscheduler.service.UserService;
import com.example.tasktrackerscheduler.util.EmailFormer;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnalyticsServiceFacadeImpl implements AnalyticsServiceFacade {

	private UserService userService;
	private TaskService taskService;
	private EmailFormer emailFormer;
	
	@Override
	public List<EmailMessage> createEmailMessagesForAllUsers() {
		List<EmailMessage> messages = new ArrayList<>();
		List<User> users = userService.findAll();
		
		for (User user : users) {
			List<Task> tasks = taskService.findAllByUser(user);
			
			if (tasks.size() == 0)
				continue;
			
			EmailMessage message = emailFormer.formMessage(user, tasks);
			messages.add(message);	
		}
		taskService.deleteAllMarkedTasks();
		
		return messages;
	}

}
