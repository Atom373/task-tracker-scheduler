package com.example.tasktrackerscheduler.service;

import java.util.List;

import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;


public interface TaskService {
	
	List<Task> findAllByUser(User user);

	void deleteAllMarkedTasks();
}
