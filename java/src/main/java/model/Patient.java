package model;

public class Patient {
	
//	patient_id serial primary key,
//    user_id int,
//    first_name varchar(50) NOT NULL,
//    last_name varchar(50) NOT NULL, 
	private String firstName;
	private String lastName;
	private String fullName;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFullName() {
		return firstName + lastName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
	

}
