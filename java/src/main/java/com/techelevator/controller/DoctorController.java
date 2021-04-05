package com.techelevator.controller;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Doctor;
import com.techelevator.model.dao.DoctorDAO;

@RestController
@CrossOrigin

public class DoctorController {
	
	private DoctorDAO doctorDao;
	
	public DoctorController(DoctorDAO doctorDao) {
		this.doctorDao = doctorDao;
	}
	
	@RequestMapping(path="/doctors", method=RequestMethod.GET)
	public List<Doctor> getDoctors() {
		return doctorDao.getDoctors();
	}

}
