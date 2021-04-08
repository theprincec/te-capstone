package com.techelevator.model.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Appointment;
import com.techelevator.model.Patient;
import com.techelevator.model.dao.AppointmentDAO;


@Component
public class JDBCAppointmentDao implements AppointmentDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCAppointmentDao (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

	@Override
	public void updateAvailablity(Appointment appointment) {
		
		String sql = "INSERT INTO appointments (appointment_id, doctor_id, patient_id, appointment_date, appointment_time_start, " +
		"appointment_time_end, appointment_status) VALUES (DEFAULT,?, ?, ?, ?, ?, ?) ";
		
		
		jdbcTemplate.update(sql, appointment.getDoctorId(), appointment.getPatient().getPatientId(), appointment.getDate(), appointment.getTimeStart(), appointment.getTimeEnd(), appointment.getAppointmentType());
		
		
		
	}

	@Override
	public List<Appointment> listOfAppointment(int doctorId) {

		List<Appointment> listOfAppointments = new ArrayList<Appointment>();
		
		String sql = "SELECT appointment_id, doctor_id, appointments.patient_id as patient_id, " +
				"patients.first_name as first_name, patients.last_name as last_name, appointment_date, " +
				"appointment_time_start, appointment_time_end, appointment_status " + 
				"FROM appointments " + 
				"JOIN patients ON patients.patient_id = appointments.patient_id " + 
				"WHERE doctor_id = ? AND current_date <= appointment_date " + 
				"ORDER BY appointment_date, appointment_time_start";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, doctorId);
		
		while(rows.next()) {
			listOfAppointments.add(mapAppointmentToRow(rows));
		}
		
		return listOfAppointments;
	}
	

	
	
	
	private Appointment mapAppointmentToRow(SqlRowSet row) {
		Appointment appointment = new Appointment();
		Patient patient = new Patient();
		patient.setPatientId(row.getInt("patient_id"));
		patient.setFirstName(row.getString("first_name"));
		patient.setLastName(row.getString("last_name"));
		appointment.setPatient(patient);
		appointment.setId(row.getInt("appointment_id"));
		appointment.setDoctorId(row.getInt("doctor_id"));
		appointment.setDate(row.getDate("appointment_date").toLocalDate());
		appointment.setTimeStart(row.getTime("appointment_time_start").toLocalTime());
		appointment.setTimeEnd(row.getTime("appointment_time_end").toLocalTime());
		appointment.setAppointmentType(row.getString("appointment_status"));
		
		return appointment;
		
		
		
		
	}
	

}
