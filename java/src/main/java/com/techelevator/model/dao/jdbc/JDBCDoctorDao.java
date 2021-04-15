package com.techelevator.model.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Address;
import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.Patient;
import com.techelevator.model.dao.DoctorDAO;


@Component
public class JDBCDoctorDao implements DoctorDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCDoctorDao (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
	@Override
	public Doctor getDoctorId(int id) {
		Doctor doctor = new Doctor();
		
		String sql = "SELECT doctor_id, users.user_id as user_id, first_name, last_name, doctors.office_id as office_id, " + 
				"office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate, users.email as email " + 
				"FROM doctors " + 
				"JOIN offices ON doctors.office_id = offices.office_id " + 
				"JOIN users ON users.user_id = doctors.user_id " + 
				"WHERE doctor_id = ? ";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
		
		
		while(rows.next()) {
			doctor = mapDoctorToRow(rows);
		}
	
		return doctor;
	}
	
	
	@Override
	public Doctor getDoctor(String username) {
		Doctor doctor = new Doctor();
		
		String sql = "SELECT doctor_id, users.user_id as user_id, first_name, last_name, doctors.office_id as office_id, " + 
				"office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate, users.email as email " + 
				"FROM doctors " + 
				"LEFT JOIN offices ON doctors.office_id = offices.office_id " + 
				"JOIN users ON users.user_id = doctors.user_id " + 
				"WHERE username = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, username);
		
		
		while(rows.next()) {
			doctor = mapDoctorToRow(rows);
		}
	
		return doctor;
	}
	
	@Override
	public List<Doctor> getDoctors() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		String sql = "SELECT doctor_id, doctors.user_id as user_id, first_name, last_name, doctors.office_id as office_id, office_name, " + 
				"address, city, district, postal_code, phone, open_time, close_time, hourly_rate, users.email FROM doctors " + 
				"JOIN offices ON doctors.office_id = offices.office_id " + 
				"JOIN users ON users.user_id = doctors.user_id ";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		while(rows.next()) {
			doctors.add(mapDoctorToRow(rows));
		}
		return doctors;
	}
	
	@Override
	public void updateOfficeForDoctor(Doctor doctor) {
		if(doctor.getOffice().getOfficeId() > 0) {
			jdbcTemplate.update("UPDATE doctors SET office_id = ? WHERE doctor_id = ?", doctor.getOffice().getOfficeId(), doctor.getDoctorId());

		} else {
			jdbcTemplate.update("UPDATE doctors SET office_id = null WHERE doctor_id = ?", doctor.getDoctorId());
		}
		
	}
	
	@Override
	public void addDoctor(Doctor doctor, int userId) {
		String sql = "INSERT INTO doctors (doctor_id, user_id, first_name, last_name) " +
				"VALUES (DEFAULT, ?, ?, ?)";
		jdbcTemplate.update(sql, userId, doctor.getFirstName(), doctor.getLastName());
	}
	
	private Doctor mapDoctorToRow (SqlRowSet row) {
		Doctor doctor = new Doctor();
		if(row.getInt("office_id") != 0) {
			Office office = mapRowToOffice(row);
			doctor.setOffice(office);
		}
		doctor.setDoctorId(row.getInt("doctor_id"));
		doctor.setUserId(row.getInt("user_id"));

//		doctor.setEmail(row.getString("users.email"));

		//doctor.setEmail(row.getString("email"));

		doctor.setFirstName(row.getString("first_name"));
		doctor.setLastName(row.getString("last_name"));
		
		return doctor;
	}
	
	private Office mapRowToOffice(SqlRowSet row) {
		Office office = new Office();
		Address address = new Address();
		office.setOfficeId(row.getInt("office_id"));
		office.setName(row.getString("office_name"));
		address.setAddressLine(row.getString("address"));
		address.setCity(row.getString("city"));
		if(row.getString("district") != null) {
			address.setDistrict(row.getString("district"));
		}
		address.setPostalCode(row.getString("postal_code"));
		office.setAddress(address);
		office.setPhoneNumber(row.getString("phone"));
		office.setOpenTime(row.getString("open_time"));
		office.setCloseTime(row.getString("close_time"));
		office.setOfficeRate(row.getBigDecimal("hourly_rate"));
		return office;
	}


}
