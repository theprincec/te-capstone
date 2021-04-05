package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.Doctor;

public interface DoctorDAO {

	public Doctor getDoctor(String username);
	
	List<Doctor> getDoctors();
}
