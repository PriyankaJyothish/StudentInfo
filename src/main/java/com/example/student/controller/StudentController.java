package com.example.student.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.student.dao.StudentDao;
import com.example.student.model.Student;

@Controller
public class StudentController  {
	
	@Autowired
	private StudentDao dao;
	
	/*
	 * @RequestMapping("/") public ModelAndView frontend(){ return new
	 * ModelAndView("home.html");
	 }*/
	
	
	  @GetMapping("/") public String showForm(Model model) {
	  model.addAttribute("st",new Student()); return "home.html"; }
	 	 
	  @RequestMapping(value={"/student","/student/{sid}"}, method = RequestMethod.GET) 
	public ResponseEntity<ResponseEntity<Student>> getStudent(@PathVariable("sid") @RequestParam String sid ) 
	  {
		
		 ResponseEntity<Student> st=dao.fineOne(sid);
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
