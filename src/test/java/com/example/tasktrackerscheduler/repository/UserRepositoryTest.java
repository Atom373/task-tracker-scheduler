package com.example.tasktrackerscheduler.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.tasktrackerscheduler.entity.User;



@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;
	
	private List<User> users;
	private int numberOfUsers;
	
	@BeforeEach
	public void setUp() {
		userRepo.deleteAll();
		
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
		numberOfUsers = users.size();
	}
	
	@Test
	public void findAll() {
		// given
		userRepo.saveAll(users);
		
		// when 
		List<User> obtained = userRepo.findAll();
		
		// then
		assertNotNull(obtained);
		assertEquals(numberOfUsers, obtained.size());
	}
}
