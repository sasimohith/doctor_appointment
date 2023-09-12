package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.Exception.CustomerNotFoundException;
import com.amdocs.model.Customer;

public interface CustomerDao {

	void insert(Customer user);

	Customer findById(String emailid);

	void update_user(Customer user);

	void removeUser(String emailid) throws CustomerNotFoundException;

	List<Customer> displayAllCustomers() throws SQLException;

}
