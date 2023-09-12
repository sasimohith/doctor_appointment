package com.amdocs;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.amdocs.Exception.AppointmentNotFoundException;
//import java.util.Date;
import com.amdocs.dao.impl.AppointmentDaoimpl;
import com.amdocs.dao.impl.CustomerDaoImpl;
import com.amdocs.model.Appointment;
import com.amdocs.model.Customer;

public class AppointmentMenu {
	static Scanner sc=new Scanner(System.in);
	static CustomerDaoImpl ud= new CustomerDaoImpl();
	static AppointmentDaoimpl ap=new AppointmentDaoimpl();
	public static void appointmentMenu() {
    	System.out.println("1. Book an appointment");
    	System.out.println("2. Modify appointment details");
    	System.out.println("3. Delete an appointment");
    	System.out.println("4. View single record");
    	System.out.println("5. View all records");
    	System.out.println("0. Exit");
    	int c = Integer.parseInt(sc.nextLine());
		switch (c) {
		case 1:
			bookingAppointment();
			break;
		case 2:
			updateAppointment();
			break;
		case 3:
			deleteAppointment();
			break;
		case 4:
			viewSingleRecord();
			break;
		case 5:
			viewAllrecords();
			break;
		default:
			System.exit(0);
		}
    }

	   public static void bookingAppointment() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter Name:");
	        String name = sc.nextLine();
	        System.out.println("Enter Patient Emailid:");
	        String emailid = sc.nextLine();
	        System.out.println("Enter Patient Doctor Name:");
	        String doctor_name = sc.nextLine();
	        System.out.println("Enter desired date:");
	        String dateStr = sc.nextLine();	        
//	        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//	        Date date1 = null;  
//
//	        try {
//	            date1 = (Date) format.parse(dateStr); 
//	        } catch (ParseException e) {
//	            System.out.println(e);
//	        }
		Appointment appoint=new Appointment(name,emailid,doctor_name,dateStr);
		 ap.insert(appoint);
         System.out.println("Password and confirmation match. Password set successfully!");
	}
	
	public static void updateAppointment() {
		System.out.println("Enter Name:");
		String name=sc.nextLine();
		System.out.println("Enter Patient Emailid:");
		String emailid=sc.nextLine();
		System.out.println("Enter Patient Doctor Name:");
		String Doctor_name=sc.nextLine();
		System.out.println("Enter desired date:");
		String date=sc.nextLine();
		Appointment app= new Appointment(name,emailid,Doctor_name,date);
		try {
			ap.update_user(app);
		} catch (AppointmentNotFoundException e) {
			System.out.println(e);
		}
	
	}
	
    private static void deleteAppointment() {
    	System.out.println("Enter a emailid to delete");
		String email=sc.nextLine();
		try {
			ap.removeAppointment(email);
		} catch (AppointmentNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    public static void viewSingleRecord() {
    	System.out.println("Enter Emailid:");
    	String emailid=sc.nextLine();
    	Appointment find;
		try {
			find = ap.findById(emailid);
			System.out.println(find.toString());
		} catch (AppointmentNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void viewAllrecords() {
    	List<Appointment> displayAllCustomer = ap.displayAllAppointments();
		for (Appointment appoint : displayAllCustomer) {
			System.out.println(appoint);
		}	
    }
}