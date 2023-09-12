package com.amdocs.Exception;

public class CustomerNotFoundException extends Exception{

	public void CustomerNotFoundException(String msg) {
		System.err.print(msg);
	}
}
