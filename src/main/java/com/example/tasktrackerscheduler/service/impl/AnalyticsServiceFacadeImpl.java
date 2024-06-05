package com.example.tasktrackerscheduler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.tasktracker.domain.entity.Task;
import com.example.tasktracker.domain.service.TaskService;
import com.example.tasktracker.security.entity.User;
import com.example.tasktracker.security.service.UserService;
import com.example.tasktrackerscheduler.dto.EmailMessage;
import com.example.tasktrackerscheduler.service.AnalyticsServiceFacade;
import com.example.tasktrackerscheduler.service.EmailFormer;

import lombok.AllArgsConstructor;

@Component
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
			
			deleteAllMarkedTasks(tasks);
		}
		return messages;
	}
	
	private void deleteAllMarkedTasks(List<Task> tasks) {
		tasks.stream()
			.filter( task -> task.getIsDeleted() )
			.forEach( task -> taskService.delete(task.getId()) );
	}

}
