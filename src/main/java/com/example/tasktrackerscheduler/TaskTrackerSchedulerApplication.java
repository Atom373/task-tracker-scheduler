package com.example.tasktrackerscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import com.example.tasktracker.security.service.impl.AuthenticationServiceFacadeImpl;
import com.example.tasktracker.security.service.impl.RegistrationServiceFacadeImpl;

@SpringBootApplication
@ComponentScan(excludeFilters = {
	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
						  value = AuthenticationServiceFacadeImpl.class
	),
	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, 
						  value = RegistrationServiceFacadeImpl.class
	)
})
public class TaskTrackerSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerSchedulerApplication.class, args);
	}

}
