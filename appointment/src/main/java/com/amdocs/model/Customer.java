package com.amdocs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amdocs.util.*;

public class Customer {
	private String first_name;
	private String last_name;
	private String emailid;
	private String password;
	private int age;
	
	public Customer() {
		super();
	}
	
	public Customer(String first_name, String last_name, String emailid, String password, int age) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.emailid = emailid;
		this.password = password;
		this.age = age;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [first_name=" + first_name + ", last_name=" + last_name + ", emailid=" + emailid
				+ ", password=" + password + ", age=" + age + "]";
	}

}
