package com.example.student.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.student.dao.StudentDao;
import com.example.student.model.Student;
import com.example.student.model.StudentForm;

@RestController
public class StudentController  {
	
	@Autowired
	private StudentDao dao;
	@Autowired
	private StudentForm stForm;
	
	@RequestMapping("/student")
	public String showForm() {
		return "home";
	}
	 
	 @GetMapping("/student/{id}") 
	public ResponseEntity<ResponseEntity<Student>> getStudent(@PathVariable("id") String id ) 
	  {
		
		 ResponseEntity<Student> st=dao.fineOne(id);
		 if(st==null)
		   {
			   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		   }
		   if(st.getStatusCode().is5xxServerError())
		   {
			   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		   }
		   
	 return ResponseEntity.of(Optional.of(st));
		  }
	 
}
