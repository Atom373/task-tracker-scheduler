package com.example.tasktrackerscheduler.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String title;
	private String description;
	private Boolean isFinished;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	private Boolean isDeleted;
	private Boolean wasFinishedToday;
}