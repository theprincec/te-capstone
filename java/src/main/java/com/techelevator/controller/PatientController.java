package com.techelevator.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Patient;
import com.techelevator.model.dao.PatientDAO;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PatientController {

	private PatientDAO patientDao;
	
	public PatientController(PatientDAO patientDao) {
		this.patientDao = patientDao;
	}
	
	@RequestMapping(path="/patients/{id}", method=RequestMethod.GET)
	public Patient getPatient(@PathVariable int id) {
		return patientDao.getPatientById(id);
		
	}
	
}