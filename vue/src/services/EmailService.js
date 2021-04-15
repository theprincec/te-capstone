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
          Body: `Hello ${patient.firstName}, 
                  <br>
                  <br>
                  Your appointment has been scheduled with Dr. ${doctor.lastName}:
                  <br> 
                  Please arrive 15 minutes prior to  your appointment at <b>${appointment.time} on ${appointment.date}</b>
                  at our <b>${doctor.office.name}<b> office to fill out necessary paperwork. 
                  <br>
                  <br>
                  The address is: 
                  <br>
                  <br>
                  ${doctor.office.address.addressLine} <br> ${doctor.office.address.city}, ${doctor.office.address.district}, ${doctor.office.address.postalCode}
                  <br>
                  <br>
                  If you need to cancel your appointment, Please contact us at least 24 hours prior to your appointment. Our phone number is ${doctor.office.phoneNumber}.
                  <br>
                  <br>
                  <p style="color: #999999">Best wishes,<br>
                  Carengton</p>`
      })
    },
      sendAgendaEmail(doctor, messageBody, date){
        
        window.Email && window.Email.send({
            //SecureToken : "038e2730-a63d-4941-8ae5-a756fe9b42d2",
            Host: "smtp.gmail.com",
            Username: "appointments.carehub@gmail.com",
            Password: "CareHub+",
            To: doctor.email,
            // cc: ccEmail,
            //To: this.rankings.toString,
            From: "appointments.carehub@gmail.com",
            Subject: `Dr. ${doctor.lastName}'s Appointments for ${date}`,
            Body: messageBody
        })
      }
    }
