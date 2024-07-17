package com.example.tasktrackerscheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;

import jakarta.transaction.Transactional;


public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findAllByUser(User user);
	
	@Modifying(flushAutomatically = true)
	@Transactional
	@Query(value = "DELETE FROM task WHERE is_deleted = true", nativeQuery = true)
	int deleteAllMarkedTasks();
}
