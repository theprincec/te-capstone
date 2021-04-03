package model;

public class Doctor {
	
//		doctor_id serial primary key, 
//    user_id int,
//    first_name varchar(50) NOT NULL,
//    last_name varchar(50) NOT NULL,
//    office_id int,
	
	
	private String name;
	private String officeName;
	private String firstName;
	private String lastName;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = "Dr." + firstName.substring(0, 1) + " " + lastName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	
	
	
	
	
}
