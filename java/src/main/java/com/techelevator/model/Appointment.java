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
	private Doctor doctor;
	private Patient parient;
	private LocalDate date;
	private LocalTime time;
	private double appointmentLength;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getParient() {
		return parient;
	}
	public void setParient(Patient parient) {
		this.parient = parient;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public double getAppointmentLength() {
		return appointmentLength;
	}
	public void setAppointmentLength(double appointmentLength) {
		this.appointmentLength = appointmentLength;
	}
	
	
	
	

}
