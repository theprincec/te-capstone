package com.techelevator.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.dao.OfficeDAO;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class OfficeController {
	
	private OfficeDAO officeDAO;
	
	public OfficeController(OfficeDAO officeDAO) {
		this.officeDAO = officeDAO;
	}
	
	@RequestMapping(path="/doctors/{id}/offices", method=RequestMethod.GET)
	public Office getOfficeInfo(@PathVariable int id) {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(id);
		return officeDAO.getOfficeInfo(doctor);
	}
	
	@RequestMapping(path="/offices", method=RequestMethod.PUT)
	public void updateOfficeInfo(@RequestBody Office newOffice) {
		officeDAO.updateOfficeInfo(newOffice);
	}
	
}
