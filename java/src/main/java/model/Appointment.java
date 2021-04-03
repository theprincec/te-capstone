package model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
	
//	appointment_id serial primary key, 
//    doctor_id int,
//    patient_id int,
//    appointment_date date NOT NULL, 
//    appointment_time time NOT NULL,
	
	private int id;
	private Doctor doctor;
	private Patient parient;
	private Date date;
	private Time time;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public double getAppointmentLength() {
		return appointmentLength;
	}
	public void setAppointmentLength(double appointmentLength) {
		this.appointmentLength = appointmentLength;
	}
	
	
	
	

}
