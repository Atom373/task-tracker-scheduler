package com.example.tasktrackerscheduler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.tasktracker.domain.entity.Task;
import com.example.tasktracker.security.entity.User;
import com.example.tasktrackerscheduler.dto.EmailMessage;
import com.example.tasktrackerscheduler.service.EmailFormer;

@Component
public class EmailFormerImpl implements EmailFormer {

	private static final String subject = "Ежедневное уведомление от Task tracker";
	private static final int tasksToDisplayCount = 5;
	
	@Override
	public EmailMessage formMessage(User user, List<Task> tasks) {
		EmailMessage message = new EmailMessage();
		
		String email = user.getEmail();
		String username = email.split("@")[0];
		StringBuilder body = new StringBuilder();
		
		body.append("Привет %s!\n".formatted(username));
		
		List<Task> finishedTodayTasks = new ArrayList<>();
		List<Task> unfinishedTasks = new ArrayList<>();
		
		for (Task task : tasks) {
			if (task.getWasFinishedToday()) {
				finishedTodayTasks.add(task);
			} else if (!task.getIsFinished()) {
				unfinishedTasks.add(task);
			}
		}
		
		if (finishedTodayTasks.size() > 0) {
			addInfoAboutFinishedTodayTasks(body, finishedTodayTasks);
		}
		
		if (unfinishedTasks.size() > 0) {
			addInfoAboutUnfinishedTasks(body, unfinishedTasks);
		}
		
		message.setReceiver(email);
		message.setSubject(subject);
		message.setBody(body.toString());
		
		return message;
	}

	private void addInfoAboutFinishedTodayTasks(StringBuilder body, List<Task> finishedTodayTasks) {
		body.append(
				"За сегодня вы выполнили %d задач:\n".formatted(finishedTodayTasks.size())
		);
		addTasksTitlesToBody(body, finishedTodayTasks);
	}

	private void addInfoAboutUnfinishedTasks(StringBuilder body, List<Task> unfinishedTasks) {
		body.append(
				"Вам осталось выполнить %d задач:\n".formatted(unfinishedTasks.size())
		);
		addTasksTitlesToBody(body, unfinishedTasks);
	}
	
	private void addTasksTitlesToBody(StringBuilder body, List<Task> tasks) {
		tasks
			.subList(0, tasksToDisplayCount)
			.forEach( 
					task -> body.append(
								task.getTitle() + "\n"
							)
			);
		if (tasks.size() > tasksToDisplayCount) {
			body.append("...\n");
		}
		body.append("\n");
	}
}

