package com.example.tasktrackerscheduler.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.tasktrackerscheduler.dto.EmailMessage;
import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;

@Component
public class EmailFormer {

	private static final String subject = "Ежедневное уведомление от Task tracker";
	private static final int tasksToDisplayCount = 5;
	
	public EmailMessage formMessage(User user, List<Task> tasks) {
		EmailMessage message = new EmailMessage();
		
		String email = user.getEmail();
		String username = email.split("@")[0];
		StringBuilder body = new StringBuilder();
		
		body.append("Привет, %s!\n".formatted(username));
		
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
		String correctForm = getCorrectForm(finishedTodayTasks.size());
		body.append(
				"За сегодня вы выполнили %d %s:\n".formatted(finishedTodayTasks.size(), correctForm)
		);
		addTasksTitlesToBody(body, finishedTodayTasks);
	}

	private void addInfoAboutUnfinishedTasks(StringBuilder body, List<Task> unfinishedTasks) {
		String correctForm = getCorrectForm(unfinishedTasks.size());
		body.append(
				"Вам осталось выполнить %d %s:\n".formatted(unfinishedTasks.size(), correctForm)
		);
		addTasksTitlesToBody(body, unfinishedTasks);
	}
	
	private String getCorrectForm(int size) {
		if (size % 10 == 1)
			return "задачу";
		if (size % 10 < 5)
			return "задачи";
		return "задач";
	}
	
	private void addTasksTitlesToBody(StringBuilder body, List<Task> tasks) {
		int n = Math.min(tasks.size(), tasksToDisplayCount);
		
		for (int i=0; i<n; i++) {
			body.append("%d) %s\n"
					.formatted(i+1, tasks.get(i).getTitle())
			);
		}
		if (tasks.size() > tasksToDisplayCount) {
			body.append("...\n");
		}
		body.append("\n");
	}
}

