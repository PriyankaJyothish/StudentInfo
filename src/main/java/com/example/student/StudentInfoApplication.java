package com.example.student;



import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StudentInfoApplication {

	public static void main(String[] args) throws IOException {
		
		
		final Logger logger = LogManager.getLogger(StudentInfoApplication.class);
		SpringApplication.run(StudentInfoApplication.class, args);
		
	
	}
}
