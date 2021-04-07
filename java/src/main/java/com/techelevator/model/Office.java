package com.techelevator.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Office {
	
//    office_id serial primary key, 
//    office_name varchar(50) NOT NULL,
//    address varchar(100) NOT NULL,
//    city varchar(50) NOT NULL,
//    district varchar(50),
//    postal_code varchar(10) NOT NULL,
//    phone varchar(10) NOT NULL, 
//    open_time time NOT NULL,
//    close_time time NOT NULL,
//    hourly_rate numeric NOT NULL	
	private int officeId;
	private String name;
	private Address address;
	private String phoneNumber;
	private String openTime;
	private String closeTime;
	
	
	public List<LocalTime> timeSlots() {
		List<LocalTime> timeSlots = new ArrayList<LocalTime>();
		
		LocalTime startTime = LocalTime.parse(openTime);
		LocalTime current = startTime;
		LocalTime endTime = LocalTime.parse(closeTime);
		
		while(current.isBefore(endTime)) {
			timeSlots.add(current);
			current = current.plusHours(1);
		}
		return timeSlots;
	}
	
	public List<LocalTime> availableTimes(List<Appointment> appointments, List<LocalTime> timeSlots) {
		
		List<LocalTime> availableSlots = new ArrayList<LocalTime>();
		
		if(appointments.size() > 0) {
			for(LocalTime time : timeSlots) {
			for(Appointment appointment : appointments) {
				if(time.isBefore(appointment.getTimeStart()) && time.plusMinutes(59).isBefore(appointment.getTimeStart()) || (time.isAfter(appointment.getTimeEnd()))) {
					
						availableSlots.add(time);
					
				}
				//if((time.isAfter(appointment.getTimeStart()) ||time.equals(appointment.getTimeStart())) && (time.isBefore(appointment.getTimeEnd()) || time.equals(appointment.getTimeEnd()))) {
					//availableSlots.add(time);
				//}
			}
			}
		}
			return availableSlots;
	}
	
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	private BigDecimal officeRate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getOfficeRate() {
		return officeRate;
	}
	public void setOfficeRate(BigDecimal officeRate) {
		this.officeRate = officeRate;
	}
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	
	
	
	

}
