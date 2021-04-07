package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.Appointment;

public interface AppointmentDAO {
	
	 void updateAvailablity(Appointment appointment);

	 List<Appointment> listOfAppointment(int doctorId);
}
