package com.amdocs.dao;

import com.amdocs.Exception.AppointmentNotFoundException;
import com.amdocs.model.Appointment;

public interface AppointmentDao {

	void insert(Appointment user);

	void update_user(Appointment app) throws AppointmentNotFoundException;

	void removeAppointment(String email) throws AppointmentNotFoundException;

	Appointment findById(String emailid) throws AppointmentNotFoundException;

}
