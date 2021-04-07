package com.techelevator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techelevator.model.Appointment;
import com.techelevator.model.Office;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	
//    	Office office = new Office();
//    	office.setOpenTime("09:00:00");
//    	office.setCloseTime("17:00:00");
//    	List<LocalTime> times = office.timeSlots();
//    	List<Appointment> appointments = new ArrayList<Appointment>();
//    	Appointment appointment = new Appointment();
//    	appointment.setTimeStart(LocalTime.parse("11:00:00"));
//    	appointment.setTimeEnd(LocalTime.parse("11:59:00"));
//    	appointments.add(appointment);
//    	
//    	List<LocalTime> availableTimes = office.availableTimes(appointments, times);
//    	
//    	System.out.println(availableTimes);
    	
    	
    	
    	//office.availableTimes(appointments, )
    	
        SpringApplication.run(Application.class, args);
    }

}
