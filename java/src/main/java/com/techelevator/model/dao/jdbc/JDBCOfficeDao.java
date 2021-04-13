package com.techelevator.model.dao.jdbc;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Address;
import com.techelevator.model.Doctor;
import com.techelevator.model.Office;
import com.techelevator.model.dao.OfficeDAO;

@Component
public class JDBCOfficeDao implements OfficeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCOfficeDao (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
	@Override
	public Office getOfficeInfo(Doctor doctor) {
		Office office = new Office();
		String sql = "SELECT offices.office_id, office_name, address, city, district, postal_code, phone, open_time, close_time, hourly_rate " + 
				"FROM doctors " + 
				"JOIN offices ON doctors.office_id = offices.office_id " + 
				"WHERE doctor_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, doctor.getDoctorId());
		while(rows.next()) {
			office = mapRowToOffice(rows);
		};
		return office;
	}
	
	@Override
	public void updateOfficeInfo(Office newOffice) {
		String sql = "UPDATE offices " + 
					"SET office_name = ?, address = ?, city = ?, district = ?, postal_code = ?, phone = ?, open_time = ?, close_time = ?, hourly_rate = ? " + 
					"WHERE office_id = ?";
		Address address = newOffice.getAddress();
		jdbcTemplate.update(sql, newOffice.getName(), address.getAddressLine(), address.getCity(),
									address.getDistrict(), address.getPostalCode(), newOffice.getPhoneNumber(),
									LocalTime.parse(newOffice.getOpenTime()), LocalTime.parse(newOffice.getCloseTime()), 
									newOffice.getOfficeRate(), newOffice.getOfficeId());
	}
	
	@Override
	public List<Office> getOffices() {
		String sql = "SELECT office_id, office_name, address, city, district, " + 
				"postal_code, phone, open_time, close_time, hourly_rate FROM offices";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		List<Office> offices = new ArrayList<Office>();
		while(rows.next()) {
			Office office = mapRowToOffice(rows);
			offices.add(office);
		}
		return offices;
		
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
	
	
	private Doctor mapRowsToDoctor (SqlRowSet row) {
		Doctor doctor = new Doctor();
//		if(row.getString("office_id").equals(null)) {
//			Office office = mapRowToOffice(row);
//			doctor.setOffice(office);
//		}
		doctor.setDoctorId(row.getInt("doctor_id"));
		doctor.setUserId(row.getInt("user_id"));
		doctor.setFirstName(row.getString("first_name"));
		doctor.setLastName(row.getString("last_name"));
		
		return doctor;
	}


	@Override
	public List<Doctor> getDoctorsByOfficeId(int officeId) {
			String sql = "SELECT doctor_id, user_id, first_name, last_name, doctors.office_id as office_id FROM doctors " + 
					"JOIN offices on offices.office_id = doctors.office_id " + 
					"WHERE doctors.office_id = ?";
			SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, officeId);
			List<Doctor> doctors = new ArrayList<Doctor>();
			while(rows.next()) {
				Doctor doctor = mapRowsToDoctor(rows);
				doctors.add(doctor);
			}
			return doctors;
		
		
	}
	

}
