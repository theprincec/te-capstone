package com.techelevator.controller;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Appointment;
import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.dao.AppointmentDAO;
import com.techelevator.model.dao.DoctorDAO;
import com.techelevator.model.dao.OfficeDAO;

@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class AppointmentController {
	
	public AppointmentController(AppointmentDAO appointmentDAO , DoctorDAO doctorDAO) {
		this.appointmentdao = appointmentDAO;
		this.doctordao = doctorDAO;
	}
	
	private AppointmentDAO appointmentdao;
	private DoctorDAO doctordao;

	@ResponseStatus(HttpStatus.CREATED)  
	@RequestMapping(path="/appointments", method=RequestMethod.POST)
	public void createAppointment (Principal principal, @RequestBody Appointment appointment) {
	
		//if principal is a doctor
		if(appointment.getDoctorId() == 0) {
			Doctor doctor = doctordao.getDoctor(principal.getName());
			
			appointment.setDoctorId(doctor.getDoctorId());

		}
		
		appointmentdao.updateAvailablity(appointment);
		
	}
	
	@RequestMapping(path="doctors/{id}/appointments", method=RequestMethod.GET)
	public List<LocalTime> viewAvailability (@PathVariable int id, @RequestParam(value="date") @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		Doctor doctor = new Doctor();
		doctor = doctordao.getDoctorId(id);
		Office office = doctor.getOffice();
		List<Appointment> listOfAppointments = appointmentdao.listOfAppointment(id);
		List<LocalTime> timeSlots = office.timeSlots();
		List<LocalTime> availableTimes = office.availableTimes(listOfAppointments, timeSlots, date);
//		To return a list of appointments with just the startTime filled in, uncomment below and change return type to List<Appointment>
//		--> May be useful if we additionally want to return the doctorId and patientId with it
//		List<Appointment> appointment = new ArrayList<Appointment>();
//		
//		for(LocalTime time: availableTimes) {
//			Appointment appointments = new Appointment();
//			appointments.setTimeStart(time);
//			appointment.add(appointments);
//		}
//		return appointment;	
		return availableTimes;
	}
	
	@RequestMapping(path="/appointments", method=RequestMethod.GET)
	public List<Appointment> viewAppointments (Principal principal) {
		Doctor doctor = doctordao.getDoctor(principal.getName());
		List<Appointment> appointments = appointmentdao.listOfAppointment(doctor.getDoctorId());
		return appointments;
	}
	
	
}
