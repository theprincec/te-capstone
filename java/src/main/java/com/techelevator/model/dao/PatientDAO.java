package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.Patient;

public interface PatientDAO {
	
	Patient getPatientById(int id);
	
	Patient getPatientByUsername(String username);
	
	void addPatient(Patient patient, int userId);

	List<Patient> getPatientsList();

}
