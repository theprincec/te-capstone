package com.techelevator.model.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Patient;
import com.techelevator.model.dao.PatientDAO;

@Component
public class JDBCPatientDao implements PatientDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCPatientDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Patient getPatientById(int id) {
		String sql = "SELECT patient_id, first_name, last_name, email FROM patients " + 
						"JOIN users ON users.user_id = patients.user_id WHERE patient_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
		Patient patient = new Patient();
		while(rows.next()) {
			patient = mapRowsToPatient(rows);
		}
		return patient;
	}
	
	
	
	@Override
	public Patient getPatientByUsername(String username) {
		String sql = "SELECT patient_id, first_name, last_name, email FROM patients " + 
				"JOIN users ON users.user_id = patients.user_id " + 
				"WHERE username = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, username);
		Patient patient = new Patient();
		while(rows.next()) {
			patient = mapRowsToPatient(rows);
		}
		return patient;
	}
	
	
	@Override
	public void addPatient(Patient patient, int userId) {
		String sql = "INSERT INTO patients (patient_id, user_id, first_name, last_name) " +
				"VALUES (DEFAULT, ?, ?, ?)";
		jdbcTemplate.update(sql, userId, patient.getFirstName(), patient.getLastName());
	}
	
	@Override
	public List<Patient> getPatientsList(){
		String sql = "SELECT patient_id, patients.user_id AS user_id, first_name, last_name, email FROM patients " + 
				"JOIN users ON users.user_id = patients.user_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		List<Patient> patients = new ArrayList<Patient>();
		while(rows.next()) {
			Patient patient = mapRowsToPatient(rows);
			patients.add(patient);
		}
		return patients;
	}

	
	
	private Patient mapRowsToPatient(SqlRowSet row) {
		Patient patient = new Patient();
		patient.setPatientId(row.getInt("patient_id"));
		patient.setFirstName(row.getString("first_name"));
		patient.setLastName(row.getString("last_name"));
		patient.setEmail(row.getString("email"));
		return patient;
	}
	


}
