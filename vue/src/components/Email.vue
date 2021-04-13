<template>
  <div>
    <h1> CLICK ME HEREEE</h1>
    <!-- <form class="contact-form" @submit.prevent="sendEmail"> -->
    <form class="contact-form" >
      <label>Name</label>
      <input type="text" name="user_name">
      <label>Email</label>
      <input type="email" name="user_email">
      <label>Message</label>
      <textarea name="message"></textarea>
      <input type="submit" value="Send">
    </form>
    <div> <button @click.prevent="sendEmail"> JUST SEND THE EMAIL </button>
    </div>
  </div>
  
</template>

<script>
import emailjs from 'emailjs-com';

export default {
    name: 'email',
    methods: {
      sendEmail(){
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


      
      
      // sendEmail: (e) => {
      //   emailjs.sendForm('service_6udn2qm', 'e686cdc1d727f115d98f13063942fa9a', templateParams, 'user_pQ0yFNYUeywkuvLM6l3cd')
      //     .then((result) => {
      //         console.log('SUCCESS!', result.status, result.text);
      //     }, (error) => {
      //         console.log('FAILED...', error);
      //     });
      // }
    }
}


</script>
<style scoped>
input, textarea{
  border:1px solid black;
}
</style>
// service_6udn2qm //service
// user_pQ0yFNYUeywkuvLM6l3cd //user
// e686cdc1d727f115d98f13063942fa9a //template 

