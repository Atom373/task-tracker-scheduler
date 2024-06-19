package com.example.tasktrackerscheduler.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.tasktrackerscheduler.entity.User;


public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findAll();
}