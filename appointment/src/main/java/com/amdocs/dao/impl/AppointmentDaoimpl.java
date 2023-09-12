package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.Exception.AppointmentNotFoundException;
import com.amdocs.dao.AppointmentDao;
import com.amdocs.model.Appointment;
import com.amdocs.model.Customer;
import com.amdocs.util.DButil;

public class AppointmentDaoimpl implements AppointmentDao{
	private final static String INSERT="insert into appointment (name,emailid,dname,appointdate) values (?,?,?,?)";
	private final static String SELECT_BY_ID="SELECT * from appointment where emailid=?";
	private final static String viewall="SELECT * from appointment";
	private Connection connection = DButil.getConnection();
	@Override
	public void insert(Appointment user) {
        PreparedStatement preparedStatement;
		try {
		   preparedStatement = connection.prepareStatement(INSERT);
           preparedStatement.setString(1, user.getName());
           preparedStatement.setString(2, user.getEmailid());
           preparedStatement.setString(3, user.getDoctor_name());
           preparedStatement.setString(4,user.getDate());
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
	public void update_user(Appointment app) throws AppointmentNotFoundException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("UPDATE Appointment SET  name=?,dname=?,appointdate=? WHERE emailid = ?");
			preparedStatement.setString(1, app.getName());
            preparedStatement.setString(2, app.getDoctor_name());
            preparedStatement.setString(3, app.getDate());
            preparedStatement.setString(4, app.getEmailid());
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
	public void removeAppointment(String email) throws AppointmentNotFoundException {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("Delete from appointment where emailid=?");
			st.setString(1, email);
			st.executeUpdate();
			System.out.println("deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Appointment findById(String emailid)throws AppointmentNotFoundException {
		Appointment user=new Appointment();
		try {
			PreparedStatement st=connection.prepareStatement(SELECT_BY_ID);
			st.setString(1, emailid);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
			 user.setName(rs.getString("name"));
			 user.setEmailid(rs.getString("emailid"));
			 user.setDoctor_name(rs.getString("dname"));
			 user.setDate(rs.getString("appointdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public List<Appointment> displayAllAppointments() {
		List<Appointment> appointment = new ArrayList<>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(viewall);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Appointment appoint = new Appointment();
				appoint.setName(rs.getString("name"));
				appoint.setEmailid(rs.getString("emailid"));
				appoint.setDoctor_name(rs.getString("dname"));
				appoint.setDate(rs.getString("appointdate"));
				appointment.add(appoint);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointment;
	}

	

}
