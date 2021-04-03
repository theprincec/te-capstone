package com.techelevator.model.dao;

import com.techelevator.model.Office;
import com.techelevator.model.Doctor;

public interface OfficeDAO {
	
	Office getOfficeInfo(Doctor doctor);
	
	void updateOfficeInfo(Office newOffice);

}
