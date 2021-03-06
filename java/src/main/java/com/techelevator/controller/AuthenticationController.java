package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Doctor;
import com.techelevator.model.Patient;
import com.techelevator.model.dao.DoctorDAO;
import com.techelevator.model.dao.PatientDAO;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;
import com.techelevator.userModel.LoginDTO;
import com.techelevator.userModel.RegisterUserDTO;
import com.techelevator.userModel.User;
import com.techelevator.userModel.UserAlreadyExistsException;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDAO userDAO;
    private DoctorDAO doctorDao;
    private PatientDAO patientDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, 
    									UserDAO userDAO, DoctorDAO doctorDao, PatientDAO patientDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDAO = userDAO;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        
        User user = userDAO.findByUsername(loginDto.getUsername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponse(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDTO newUser) {
        try {
            User user = userDAO.findByUsername(newUser.getUsername());
            throw new UserAlreadyExistsException();
        } catch (UsernameNotFoundException e) {
            userDAO.create(newUser.getUsername(),newUser.getPassword(), newUser.getRole(), newUser.getEmail());
            //Add new doctor or patient based on client side selection (need ID to insert new doctor or patient into database)
            int id = userDAO.findIdByUsername(newUser.getUsername());
            if(newUser.getRole().equals("doctor")) {
            	Doctor doctor = new Doctor();
            	doctor.setFirstName(newUser.getFirstName());
            	doctor.setLastName(newUser.getLastName());
            	doctorDao.addDoctor(doctor, id);
            } else if(newUser.getRole().equals("user")) {
            	Patient patient = new Patient();
            	patient.setFirstName(newUser.getFirstName());
            	patient.setLastName(newUser.getLastName());
            	patientDao.addPatient(patient, id);
            }
        }
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class LoginResponse {

        private String token;
        private User user;

        LoginResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }

        @JsonProperty("token")
        String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }

        @JsonProperty("user")
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
    }
}

