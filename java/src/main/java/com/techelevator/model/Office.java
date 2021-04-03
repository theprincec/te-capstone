package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalTime;

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
