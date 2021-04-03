package model;

public class Address {
	
//	address varchar(100) NOT NULL,
//  city varchar(50) NOT NULL,
//  district varchar(50),
//  postal_code varchar(10) NOT NULL,

	
	private String addressLine;
	private String city;
	private String district;
	private int postalCode;
	
	
	
	
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	
	
	
}
