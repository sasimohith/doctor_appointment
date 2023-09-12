package com.amdocs;

import com.amdocs.Exception.CustomerNotFoundException;

import com.amdocs.dao.impl.CustomerDaoImpl;
import com.amdocs.model.*;
import com.customerregistration.customer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.SQLException;
import java.util.*;
public class App 
{
	static CustomerDaoImpl rs=new CustomerDaoImpl();
	static Scanner scan=new Scanner(System.in);
	static AppointmentMenu app= new AppointmentMenu();
	
	
    public static void main( String[] args )
    {
    	try {
    			mainMenu();
		} 
    	  catch (NumberFormatException e) {

			System.out.println("Input should be a number in the range of 0 - 4.");
			System.out.println("Entered: " + e.getMessage());

		} catch (Exception e) {

			System.out.println("Error : " + e.getMessage());

		}
    	
    } 
    	public static void mainMenu() throws SQLException{

    		boolean x = true;
    		while (x) {
    	    System.out.println("1. Customer");
			System.out.println("2. Doctor");
			System.out.println("3. Appointment");
			int c = Integer.parseInt(scan.nextLine());
			switch (c) {
			case 1:
				CustomerMenu();
				break;
			case 2:
				app.appointmentMenu();
				break;
			case 3:
				app.viewSingleRecord();
			default:
				System.exit(0);
			}
    		}
		}
    private static void CustomerMenu()  {
    	System.out.println("1. Register Customer");
    	System.out.println("2. Modify Customer details");
    	System.out.println("3. Delete Customer record");
    	System.out.println("4. View Single record");
    	System.out.println("5. View all records");
    	System.out.println("0. Exit");
    	int c = Integer.parseInt(scan.nextLine());
		switch (c) {
		case 1:
			registration();
			break;
		case 2:
			modifyCustomerDetails();
			break;
		case 3:
			deleteRecord();
			break;
		case 4:
			findSingleRecord();
			break;
		case 5:
			viewAllRecords();
			break;
		default:
			System.exit(0);
		}

    }
 
    
    private static void registration() {
		System.out.println("First Name : " );
		String firstname = scan.nextLine();
		System.out.println("Last Name:" );
		String lastname = scan.nextLine();
		String email;
	    do {
	        System.out.println("Enter Email (must contain '@' and end with '.com'): ");
	        email = scan.nextLine();
	        if (!email.contains("@") || !email.endsWith(".com")) {
	            System.out.println("Invalid email format. Email must contain '@' and end with '.com'.");
	        }
	    } while (!email.contains("@") || !email.endsWith(".com"));
	    String password;
		Matcher specialCharacterMatcher;
		Matcher uppercaseLetterMatcher;
		Pattern specialCharacterPattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
		Pattern uppercaseLetterPattern = Pattern.compile("[A-Z]");
		boolean registrationSuccessful = false;

		do {
		    System.out.println("Create Password (must contain at least one uppercase letter and one special character): ");
		    password = scan.nextLine();
		    specialCharacterMatcher = specialCharacterPattern.matcher(password);
		    uppercaseLetterMatcher = uppercaseLetterPattern.matcher(password);

		    if (!specialCharacterMatcher.find() || !uppercaseLetterMatcher.find()) {
		        System.out.println("Invalid password. Password must contain at least one uppercase letter and one special character.");
		    } else {
		        registrationSuccessful = true; // Password with an uppercase letter and a special character is provided, registration is successful
		    }
		}while (!registrationSuccessful);
		System.out.println("Confirm Password :");
		String cpassword = scan.nextLine();
		System.out.println("Age:");
		int age = Integer.parseInt(scan.nextLine());
		Customer user=new Customer(firstname,lastname,email,password,age); 
		 if (password.equals(cpassword)) {
			 rs.insert(user);
	         System.out.println("Password and confirmation match. Password set successfully!");
	     } 
		 else {
	            System.out.println("Password and confirmation do not match. Please try again.");
	        }
}
    private static void findSingleRecord() {
    	System.out.println("Enter Emailid:");
    	String emailid=scan.nextLine();
    	Customer find = rs.findById(emailid);
		System.out.println(find.toString());
	}
    
    private static void modifyCustomerDetails()  {
  		System.out.println("First Name : " );
  		String firstname = scan.nextLine();
  		System.out.println("Last Name:" );
  		String lastname = scan.nextLine();
  		System.out.println("Email Id :");
  		String email = scan.nextLine();
  		System.out.println("Password:");
  		String password = scan.nextLine();
  		System.out.println("Age:");
  		int age = Integer.parseInt(scan.nextLine());
  		Customer ur = new Customer(firstname,lastname,email,password,age);
  		rs.update_user(ur);
    }

      private static void findById() {
   		System.out.println("\nEnter Id:");
   		int CustomerId = Integer.parseInt(scan.nextLine());
   		try {
   			CustomerDaoImpl dao;
   			Customer findById = dao.findById(CustomerId);
   			System.out.println(findById);
   		} catch (CustomerNotFoundException | SQLException e) {
   			System.err.println(e);
   		}
   	}
   private static void viewAllRecords() {
    	try {
			List<Customer> displayAllCustomer = rs.displayAllCustomers();
			for (Customer customer : displayAllCustomer) {
				System.out.println(customer);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}
    
	private static void deleteRecord() {
		System.out.println("Enter a emailid to delete");
		String email=scan.nextLine();
		try {
			rs.removeUser(email);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
