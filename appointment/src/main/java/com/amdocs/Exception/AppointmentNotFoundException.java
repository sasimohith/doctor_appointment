package com.amdocs.Exception;

public class AppointmentNotFoundException extends Exception {
	public void AppointmentNotFoundexception(String message) {
		System.err.println(message);
	}

}
