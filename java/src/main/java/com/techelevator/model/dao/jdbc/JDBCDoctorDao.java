package com.techelevator.model.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Doctor;
import com.techelevator.model.dao.DoctorDAO;


@Component
public class JDBCDoctorDao implements DoctorDAO{
	
private JdbcTemplate jdbcTemplate;
	
	public JDBCDoctorDao (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Doctor getDoctor(String username) {
		Doctor doctor = new Doctor();
		
		String sql = "SELECT doctor_id, users.user_id, first_name, last_name, office_id FROM doctors " + 
				"JOIN users ON users.user_id = doctors.user_id " + 
				"WHERE username = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, username);
		
		
		while(rows.next()) {
			doctor = mapDoctorToRow(rows);
		}
	
		return doctor;
	}
	
	public Doctor mapDoctorToRow (SqlRowSet row) {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(row.getInt("doctor_id"));
		doctor.setFirstName(row.getString("first_name"));
		doctor.setLastName(row.getString("last_name"));
		doctor.setOfficeId(row.getInt("office_id"));
		
		return doctor;
	}

}
