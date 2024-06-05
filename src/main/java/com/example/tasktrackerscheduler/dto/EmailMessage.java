package com.example.tasktrackerscheduler.dto;

import lombok.Data;

@Data
public class EmailMessage {

	private String receiver;
	private String subject;
	private String body;
	
}
