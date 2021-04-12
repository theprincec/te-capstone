package com.techelevator.controller;
import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Doctor;
import com.techelevator.model.dao.DoctorDAO;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class DoctorController {
	
	private DoctorDAO doctorDao;
	
	public DoctorController(DoctorDAO doctorDao) {
		this.doctorDao = doctorDao;
	}
	
	@RequestMapping(path="/doctors", method=RequestMethod.GET)
	public List<Doctor> getDoctors() {
		return doctorDao.getDoctors();
	}
	
	
	@RequestMapping(path="/doctor", method=RequestMethod.GET)
	public Doctor getDoctor(Principal principal) {
		return doctorDao.getDoctor(principal.getName());
	}
	
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
	@RequestMapping(path="/doctors", method=RequestMethod.PUT)
	public void updateDoctor(@RequestBody Doctor doctor) {
		doctorDao.updateOfficeForDoctor(doctor);
	}

}
