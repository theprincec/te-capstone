package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.Doctor;

public interface DoctorDAO {

	public Doctor getDoctorId(int id);
	
	public Doctor getDoctor(String username);
	
	List<Doctor> getDoctors();
	
	void updateOfficeForDoctor(Doctor doctor);
	
	void addDoctor(Doctor doctor, int userId);

	
}
