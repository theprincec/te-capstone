package com.techelevator.controller;


import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Appointment;
import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.dao.AppointmentDAO;
import com.techelevator.model.dao.DoctorDAO;
import com.techelevator.model.dao.OfficeDAO;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class AppointmentController {
	
	public AppointmentController(AppointmentDAO appointmentDAO , DoctorDAO doctorDAO) {
		this.appointmentdao = appointmentDAO;
		this.doctordao = doctorDAO;
	}
	
	private AppointmentDAO appointmentdao;
	private DoctorDAO doctordao;

	@RequestMapping(path="/appointements", method=RequestMethod.POST)
	public void createAppointment (Principal principal, @RequestBody Appointment appointment) {
	
		Doctor doctor = doctordao.getDoctor(principal.getName());
		
		appointment.setDoctorId(doctor.getDoctorId());
		
		appointmentdao.updateAvailablity(appointment);
		
	}
	
}
