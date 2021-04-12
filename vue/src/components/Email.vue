<template>
  <div>
    <h1> CLICK ME HEREEE</h1>
    <form class="contact-form" @submit.prevent="sendEmail">
      <label>Name</label>
      <input type="text" name="user_name">
      <label>Email</label>
      <input type="email" name="user_email">
      <label>Message</label>
      <textarea name="message"></textarea>
      <input type="submit" value="Send">
    </form>
  </div>
  
</template>

<script>
import emailjs from 'emailjs-com';

export default {
    name: 'email-service',
  methods: {
    sendEmail: (e) => {
      emailjs.sendForm('service_6udn2qm', 'template_a0ulcrl', e.target, 'user_pQ0yFNYUeywkuvLM6l3cd')
        .then((result) => {
            console.log('SUCCESS!', result.status, result.text);
            alert('thanks for sending an email')
        }, (error) => {
            console.log('FAILED...', error);
        });
    }
  }
}
</script>
<style scoped>
input, textarea{
  border:1px solid black;
}
</style>
// service_6udn2qm
// user_pQ0yFNYUeywkuvLM6l3cd
// e686cdc1d727f115d98f13063942fa9a
