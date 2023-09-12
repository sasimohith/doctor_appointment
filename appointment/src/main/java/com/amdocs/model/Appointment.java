package com.amdocs.model;


public class Appointment {
	private String name;
	private String emailid;
	private String doctor_name;
	private String date;
	
	public Appointment() {
		super();
	}
	public Appointment(String name, String emailid, String doctor_name, String date) {
		this.name = name;
		this.emailid = emailid;
		this.doctor_name = doctor_name;
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Appointment [name=" + name + ", emailid=" + emailid + ", doctor_name=" + doctor_name + ", date=" + date
				+ "]";
	}
	
}
