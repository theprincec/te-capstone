package com.techelevator.model;

public class Doctor {
	
//		doctor_id serial primary key, 
//    user_id int,
//    first_name varchar(50) NOT NULL,
//    last_name varchar(50) NOT NULL,
//    office_id int,
	
	private int doctorId;
	private int userId;
	private Office office;
	private String firstName;
	private String lastName;
	private String email;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	
}
