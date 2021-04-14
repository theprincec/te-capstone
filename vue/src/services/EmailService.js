//import emailjs from 'emailjs-com';
// src=https://smtpjs.com/v3/smtp.js
export default {
    
    sendAppointmentEmail(patient, doctor, appointment){
      
      window.Email && window.Email.send({
          //SecureToken : "038e2730-a63d-4941-8ae5-a756fe9b42d2",
          Host: "smtp.gmail.com",
          Username: "appointments.carehub@gmail.com",
          Password: "CareHub+",
          To: patient.email,
          Cc: doctor.email,
          //To: [patient.email, doctor.email],
          // cc: ccEmail,
          //To: this.rankings.toString,
          From: "appointments.carehub@gmail.com",
          Subject: `${patient.lastName}, ${patient.firstName}: New Appointment with Dr. ${doctor.lastName}`,
          Body: `Hello ${patient.firstName}, Your appointment has been scheduled with Dr. ${doctor.lastName}: 
                  Please arrive 15 minutes prior to  your appointment ${appointment.timeStart} on ${appointment.date}
                  at our ${doctor.office.name} office to fill out necessary paperwork. The address is: 
                  ${doctor.office.address.addressLine}, ${doctor.office.address.city}, ${doctor.office.address.district}, ${doctor.office.address.postalCode}
                  Please Contact us if you have any last minute cancellations. Our phone number is ${doctor.office.phoneNumber}.
                  Best wishes,
                  Carengton`
      })
    },
      sendAgendaEmail(patient, doctor, appointment){
        
        window.Email && window.Email.send({
            //SecureToken : "038e2730-a63d-4941-8ae5-a756fe9b42d2",
            Host: "smtp.gmail.com",
            Username: "appointments.carehub@gmail.com",
            Password: "CareHub+",
            To: [doctor.email],
            // cc: ccEmail,
            //To: this.rankings.toString,
            From: "appointments.carehub@gmail.com",
            Subject: `Dr. ${doctor.lastName}'s Appointments for Today`,
            Body: `Here are your appointments`
        })
      }
    }
