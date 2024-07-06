package com.example.tasktrackerscheduler.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.tasktrackerscheduler.entity.Task;
import com.example.tasktrackerscheduler.entity.User;


@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@BeforeEach
	public void setUp() {
		userRepo.deleteAll();
		taskRepo.deleteAll();
	}
	
	@Test
	public void findAllByUser_shouldReturnFirstTwoTasks() {
		// given
		User bob = new User();
		bob.setEmail("bob@gmail.com");
		bob.setPassword("1234");
		
		User sam = new User();
		sam.setEmail("sam@gmail.com");
		sam.setPassword("1234");
		
		userRepo.save(bob);
		userRepo.save(sam);
		
		Task task1 = Task.builder()
				.title("first task")
				.user(bob)
				.build();
		Task task2 = Task.builder()
				.title("second task")
				.user(bob)
				.build();
		Task task3 = Task.builder()
				.title("third task")
				.user(sam)
				.build();
		
		taskRepo.saveAll(List.of(task1, task2, task3));
		
		// when
		List<Task> obtained = taskRepo.findAllByUser(bob);
		
		// then
		assertNotNull(obtained);
		assertEquals(2, obtained.size());
	}
}
