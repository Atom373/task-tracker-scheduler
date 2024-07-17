package com.example.tasktrackerscheduler.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;
import com.example.tasktrackerscheduler.repository.TaskRepository;
import com.example.tasktrackerscheduler.service.TaskService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepo;
	
	@Override
	public List<Task> findAllByUser(User user) {
		return taskRepo.findAllByUser(user);
	}

	@Override
	public void deleteAllMarkedTasks() {
		taskRepo.deleteAllMarkedTasks();
	}
}
