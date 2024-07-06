package com.example.tasktrackerscheduler.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.example.tasktrackerscheduler.entity.User;
import com.example.tasktrackerscheduler.repository.UserRepository;
import com.example.tasktrackerscheduler.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	private List<User> users;
	
	@BeforeEach
	public void setUp() {
		User bob = new User();
		bob.setEmail("bob@gmail.com");
		bob.setPassword("1234");
		
		User sam = new User();
		sam.setEmail("sam@gmail.com");
		sam.setPassword("1234");
		
		User tom = new User();
		tom.setEmail("tom@gmail.com");
		tom.setPassword("1234");
		
		users = List.of(bob, sam, tom);
	}
	
	@Test
	public void findAll() {
		when(userRepo.findAll()).thenReturn(users);
		
		List<User> obtained = userService.findAll();
		
		assertNotNull(obtained);
		verify(userRepo, times(1)).findAll();
	}
}
