package com.example.student.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.student.dao.JasonParser;
import com.example.student.dao.StudentDao;
import com.example.student.model.Student;
import com.example.student.model.StudentForm;

@Controller
public class StudentController implements WebMvcConfigurer  {
	
	@Autowired
	private StudentDao dao;
	@Autowired
	private StudentForm studentForm;
	
	@GetMapping("/student")
	public String showForm(StudentForm studentForm) {
		return "home.html";
	}
	 
	 @GetMapping("student/{id}") 
	public String getStudent(@PathVariable String id) 
	  {
		   Student student=dao.fineOne(id);
	 return ("redirect:/student/" + String.valueOf(student.getSid()));
		  }
	 
}
