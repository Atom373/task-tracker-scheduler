package com.example.tasktrackerscheduler.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;
import com.example.tasktrackerscheduler.repository.TaskRepository;
import com.example.tasktrackerscheduler.service.impl.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

	@Mock
	private TaskRepository taskRepo;
	
	@InjectMocks
	private TaskServiceImpl taskService;
	
	private List<Task> tasks;
	private User bob;
	
	@BeforeEach
	public void setUp() {
		bob = new User();
		bob.setEmail("bob@gmail.com");
		bob.setPassword("1234");
		
		Task task1 = Task.builder()
				.title("first task")
				.user(bob)
				.isDeleted(false)
				.build();
		Task task2 = Task.builder()
				.title("second task")
				.user(bob)
				.isDeleted(true)
				.build();
		
		tasks = List.of(task1, task2);
	}
	
	@Test
	public void findAllByUser() {
		when(taskRepo.findAllByUser(bob)).thenReturn(tasks);
		
		List<Task> obtained = taskService.findAllByUser(bob);
		
		assertNotNull(obtained);
		assertEquals(2, obtained.size());
		verify(taskRepo, times(1)).findAllByUser(bob);
	}
	
	@Test
	public void deleteAllMarkedTasks() {
		List<Task> tasksToDelete = tasks.stream().filter(task -> task.getIsDeleted()).toList();
		doNothing().when(taskRepo).deleteAll(tasksToDelete);
		
		taskService.deleteAllMarkedTasks(tasks);
		
		verify(taskRepo, times(1)).deleteAll(tasksToDelete);
	}
}
