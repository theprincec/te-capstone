import emailjs from 'emailjs-com';
// src=https://smtpjs.com/v3/smtp.js

export default {
      
    send(newTime){
        let templateParams = {
         //mainEmail: this.$store.state.currentPatient.email, //patient
          ccEmail: this.$store.state.currentDoctor.email, //doc
          mainEmailFirstName: this.$store.state.currentPatient.firstName,//patient
          ccEmailLastName: this.$store.state.currentDoctor.lastName, //doc
          appointmentDate: this.$store.state.currentDate,
          officeName: this.$store.state.currentDoctor.office.name,
          officeAddress: `${this.$store.state.currentDoctor.office.address.addressLine}, 
              ${this.$store.state.currentDoctor.office.address.city}, 
              ${this.$store.state.currentDoctor.office.address.district}, 
              ${this.$store.state.currentDoctor.office.address.postalCode}`,
          startTime: newTime,
          phoneNumber: this.$store.state.currentDoctor.office.phoneNumber
          };
          emailjs.sendForm('service_6udn2qm', 'e686cdc1d727f115d98f13063942fa9a', e.target, 'user_pQ0yFNYUeywkuvLM6l3cd', templateParams)
          .then((result) => {
              console.log('SUCCESS!', result.status, result.text);
          }, (error) => {
              console.log('FAILED...', error);
          })

      }
      
      ,
      sendTheEmail(){
        // let templateParams = {
        //      mainEmail: this.$store.state.currentPatient.email, //patient
        //       ccEmail: this.$store.state.currentDoctor.email, //doc
        //       mainEmailFirstName: this.$store.state.currentPatient.firstName,//patient
        //       ccEmailLastName: this.$store.state.currentDoctor.lastName, //doc
        //       appointmentDate: this.$store.state.currentDate,
        //       officeName: this.$store.state.currentDoctor.office.name,
        //       officeAddress: `${this.$store.state.currentDoctor.office.address.addressLine}, 
        //           ${this.$store.state.currentDoctor.office.address.city}, 
        //           ${this.$store.state.currentDoctor.office.address.district}, 
        //           ${this.$store.state.currentDoctor.office.address.postalCode}`,
        //       startTime: newTime,
        //       phoneNumber: this.$store.state.currentDoctor.office.phoneNumber
        //       };
        Email.send({
            SecureToken : "b3261d9b-765c-498a-b914-98c84cb2951d",
            // To : this.$store.state.currentPatient.email,
            To : "ccumberl@kent.edu",
            From : "appointments.carehub@gmail.com",
            Subject : `Appointment Confirmation with Dr. ${this.$store.state.currentDoctor.lastName}`,
                Body : "And this is the body"
            }).then(
            message => alert(message)
            );
      }
    //   ,
    //   sendEmailJW(){
    //     window.Email && window.Email.send({
    //         Host: "smtp.gmail.com",
    //         Username: "appointments.carehub@gmail.com",
    //         Password: "CareHub+",
    //         To: 'ccumberl@kent.edu',
    //         //To: this.rankings.toString,
    //         From: "ccumberl",
    //         Subject: "Your Tournament Has Ended!",
    //         Body: "A recent tournament you entered has now concluded. Please check the website to see the final bracket! XXX was the winner!"
    //     })
    //   }
      ,
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


// ,
//   methods:{
//     // sendEmail(){
//     //   emailService.sendEmail();

//     // },
//     sendTheEmail(){
//       emailService.sendTheEmail();

//     },
//     sendEmail() {
//       let patientEmail = this.$store.state.currentPatient.email;
//       let doctorEmail = this.$store.state.currentDoctor.email;
//       let doctorName = this.$store.state.currentDoctor.lastName;
//       let appointmentTime = this.$store.state.currentAppointment.timeStart;

//       emailService.sendEmailJW(patientEmail, d);
//     }
//   },
//   mounted() {

//     let emailScript = document.createElement('script');
//     emailScript.setAttribute('src', 'https://smtpjs.com/v3/smtp.js');
//     document.head.appendChild(emailScript);
//     //let toEmail = this.$store.state.currentPatient.email;
//   }