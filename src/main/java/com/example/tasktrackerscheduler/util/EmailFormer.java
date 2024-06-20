package com.example.tasktrackerscheduler.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.tasktrackerscheduler.dto.EmailMessage;
import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;

@Component
public class EmailFormer {

	private static final String subject = "Ежедневное уведомление от Task tracker";
	private static final String finishedTasksMessage = "За сегодня вы выполнили %d %s:\n";
	private static final String unfinishedTasksMessage = "Вам осталось выполнить %d %s:\n";
	private static final int tasksToDisplayCount = 5;
	
	public EmailMessage formMessage(User user, List<Task> tasks) {
		EmailMessage message = new EmailMessage();
		
		String email = user.getEmail();
		String username = email.split("@")[0];
		StringBuilder body = new StringBuilder();
		
		body.append("Привет, %s!\n".formatted(username));
		
		addInfoAboutTasks(tasks, body);
		
		message.setReceiver(email);
		message.setSubject(subject);
		message.setBody(body.toString());
		
		return message;
	}

	private void addInfoAboutTasks(List<Task> tasks, StringBuilder body) {
		List<Task> finishedTodayTasks = new ArrayList<>();
		List<Task> unfinishedTasks = new ArrayList<>();
		
		for (Task task : tasks) {
			LocalDate finishingDate = task.getFinishingDate();
			if (finishingDate != null && finishingDate.isEqual(LocalDate.now())) {
				finishedTodayTasks.add(task);
			} else if ( !(task.getIsDeleted() || task.getIsFinished()) ) {
				unfinishedTasks.add(task);
			}
		}
		addInfoAbout(finishedTodayTasks, finishedTasksMessage, body);
		addInfoAbout(unfinishedTasks, unfinishedTasksMessage, body);
	}
	
	
	private void addInfoAbout(List<Task> tasks, String message, StringBuilder body) {
		if (tasks.isEmpty())
			return;
		
		String correctForm = getCorrectForm(tasks.size());
		body.append(
				message.formatted(tasks.size(), correctForm)
		);
		addTasksTitlesToBody(body, tasks);
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