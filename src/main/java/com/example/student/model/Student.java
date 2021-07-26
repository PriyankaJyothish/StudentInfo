package com.example.student.model;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Student {
	final Logger logger = LogManager.getLogger(Student.class);

	
	/**
	 * 
	 */
	@Digits(integer=6, fraction=0)
	private String sid;
	public Student() {
		super();
	}
	private String sname;
	private String sdob;
	private String sspec;
	
	 
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSdob() {
		return sdob;
	}
	public void setSdob(String sdob) {
		this.sdob = sdob;
	}
	public String getsspec() {
		return sspec;
	}
	public void setsspec(String sspec) {
		this.sspec = sspec;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", sdob=" + sdob + ", sspec=" + sspec
				+ "]";
	}
	public Student(String sid, String sname, String sdob, String sspec) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sdob = sdob;
		this.sspec = sspec;
	}
	
}
