package com.example.tasktrackerscheduler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;


public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findAllByUser(User user);
}
