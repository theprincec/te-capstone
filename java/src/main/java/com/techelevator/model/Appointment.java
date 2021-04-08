package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
	
//	appointment_id serial primary key, 
//    doctor_id int,
//    patient_id int,
//    appointment_date date NOT NULL, 
//    appointment_time time NOT NULL,
	
	private int id;
	private int doctorId;
	private Patient patient;
	private LocalDate date;
	private LocalTime timeStart;
	private LocalTime timeEnd;
	private String appointmentType;
	
	
	public LocalTime getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}
	//public double getAppointmentLength() {
	//	return appointmentLength;
	//}
	//public void setAppointmentLength(double appointmentLength) {
	//	this.appointmentLength = appointmentLength;
	//}
	
	
	
	

}
