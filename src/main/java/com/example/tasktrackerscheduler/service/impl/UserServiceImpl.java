package com.example.tasktrackerscheduler.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.tasktrackerscheduler.entity.User;
import com.example.tasktrackerscheduler.repository.UserRepository;
import com.example.tasktrackerscheduler.service.UserService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;
	
	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}
}
