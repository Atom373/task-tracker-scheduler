package com.example.tasktrackerscheduler.service;

import java.util.List;

import com.example.tasktracker.domain.entity.Task;
import com.example.tasktracker.security.entity.User;
import com.example.tasktrackerscheduler.dto.EmailMessage;

public interface EmailFormer {

	EmailMessage formMessage(User user, List<Task> tasks);
}
