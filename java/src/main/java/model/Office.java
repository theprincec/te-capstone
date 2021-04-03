package model;

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
	
	private String name;
	private Address address;
	private String phoneNumber;
	private int openTime;
	private int closeTime;
	private int officeRate;
	
	
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
	public int getOpenTime() {
		return openTime;
	}
	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}
	public int getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(int closeTime) {
		this.closeTime = closeTime;
	}
	public int getOfficeRate() {
		return officeRate;
	}
	public void setOfficeRate(int officeRate) {
		this.officeRate = officeRate;
	}
	
	
	
	

}
