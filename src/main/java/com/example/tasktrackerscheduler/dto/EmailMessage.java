package com.example.tasktrackerscheduler.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmailMessage implements Serializable{
	
	private static final long serialVersionUID = -7085150323755492652L;
	
	private String receiver;
	private String subject;
	private String body;
	
}
