package com.techelevator.model.dao.jdbc;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Appointment;
import com.techelevator.model.dao.AppointmentDAO;


@Component
public class JDBCAppointmentDao implements AppointmentDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCAppointmentDao (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

	@Override
	public void updateAvailablity(Appointment appointment) {
		
		String sql = "INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start," +
		"appointment_time_end, appointment_status) VALUES (DEFAULT,?, ?, ?, ?, ?, ?) ";
		
		
		jdbcTemplate.update(sql, appointment.getDoctorid(), appointment.getPatientId(), appointment.getDate(), appointment.getTimeStart(), appointment.getTimeEnd(), appointment.getAppointmentType());
		
		
		
	}

	@Override
	public List<Appointment> listOfAppointment() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Appointment mapAppointmentToRow(SqlRowSet row) {
		Appointment appointment = new Appointment();
		appointment.setId(row.getInt("appointment_id"));
		appointment.setDoctorId(row.getInt("doctor_id"));
		appointment.setDate(row.getDate("apointment_date").toLocalDate());
		appointment.setTimeStart(row.getTime("appointment_time_start").toLocalTime());
		appointment.setTimeEnd(row.getTime("appointment_time_end").toLocalTime());
		appointment.setAppointmentType(row.getString("appointment_status"));
		
		return appointment;
		
		
		
		
	}
	

}
