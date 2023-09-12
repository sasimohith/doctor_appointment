package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.*;
import com.amdocs.App;
import com.amdocs.AppointmentMenu;
import com.amdocs.Exception.CustomerNotFoundException;
import com.amdocs.model.Customer;
import com.amdocs.util.DButil;
import com.mysql.cj.xdevapi.Statement;

public class CustomerDaoImpl implements CustomerDao{
	private final static String INSERT = "insert into users(first_name,last_name,emailid,pwd,age) values(?,?,?,?,?)";
	private final static String SELECT_BY_ID = "SELECT * FROM users WHERE emailid=?";
	private final static String viewall="SELECT * FROM users";
	private Connection connection = DButil.getConnection();
	@Override
	public void insert(Customer user) {
        PreparedStatement preparedStatement;
		try {
		   preparedStatement = connection.prepareStatement(INSERT);
           preparedStatement.setString(1, user.getFirst_name());
           preparedStatement.setString(2, user.getLast_name());
           preparedStatement.setString(3, user.getEmailid());
           preparedStatement.setString(4, user.getPassword());
           preparedStatement.setInt(5, user.getAge());
           if (preparedStatement.executeUpdate() > 0) {
               System.out.println("Data inserted successfully.");
           } else {
               System.out.println("Data insertion failed.");
           }
           preparedStatement.close();
		} catch (SQLException e) {
			System.err.println(e);
		}
	} 
	@Override
	public Customer findById(String emailid) {
		Customer user=new Customer();
		try {
			PreparedStatement st=connection.prepareStatement(SELECT_BY_ID);
			st.setString(1, emailid);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
			 user.setFirst_name(rs.getString("first_name"));
			 user.setLast_name(rs.getString("last_name"));
			 user.setEmailid(rs.getString("emailid"));
			 user.setPassword(rs.getString("pwd"));
			 user.setAge(rs.getInt("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public void update_user(Customer user) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE users SET  first_name=?, last_name=?,pwd=?,age=? WHERE emailid = ?");
			preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getEmailid());
            if (preparedStatement.executeUpdate() > 0) {
                 System.out.println("Data updated successfully.");
             } else {
                 System.out.println("Data updation failed.");
             }
            preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void removeUser(String emailid) throws CustomerNotFoundException{
		PreparedStatement st;
		try {
			st = connection.prepareStatement("Delete from users where emailid=?");
			st.setString(1, emailid);
			st.executeUpdate();
			System.out.println("deleted successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<Customer> displayAllCustomers() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		PreparedStatement stmt = connection.prepareStatement(viewall);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Customer customer = new Customer();
			customer.setFirst_name(rs.getString("first_name"));
			customer.setLast_name(rs.getString("last_name"));
			customer.setEmailid(rs.getString("emailid"));
			customer.setPassword(rs.getString("pwd"));
			customer.setAge(rs.getInt("age"));
			customers.add(customer);
		}
		rs.close();
		stmt.close();
		return customers;
	}
}
