package com.techelevator.model.dao.jdbc;

import java.time.LocalTime;

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
