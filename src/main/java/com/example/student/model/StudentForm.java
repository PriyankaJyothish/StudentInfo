package com.example.student.model;

import javax.validation.constraints.Digits;


import org.springframework.stereotype.Component;
@Component
public class StudentForm {
	@Digits(integer=6, fraction=0)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}