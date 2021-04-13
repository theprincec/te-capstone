import emailjs from 'emailjs-com';

export default {
      
    sendAppointmentEmail(){
        let templateParams = {
          mainEmail: this.$store.state.currentPatient.email, //patient
          ccEmail: this.$store.state.currentDoctor.email, //doc
          mainEmailFirstName: this.$store.state.currentPatient.firstName,//patient
          ccEmailLastName: this.$store.state.currentDoctor.lastName, //doc
          appointmentDate: this.$store.state.currentAppointment.timeStart,
          officeName: this.$store.state.currentDoctor.office.name,
          officeAddress: `${this.$store.state.currentDoctor.office.address.addressLine}, 
              ${this.$store.state.currentDoctor.office.address.city}, 
              ${this.$store.state.currentDoctor.office.address.district}, 
              ${this.$store.state.currentDoctor.office.address.postalCode}`,
          startTime: this.$store.state.currentAppointment.timeStart,
          phoneNumber: this.$store.state.currentDoctor.office.phoneNumber
          };
          emailjs.sendForm('service_6udn2qm', 'e686cdc1d727f115d98f13063942fa9a', templateParams, 'user_pQ0yFNYUeywkuvLM6l3cd')
          .then((result) => {
              console.log('SUCCESS!', result.status, result.text);
          }, (error) => {
              console.log('FAILED...', error);
          })

      }
}