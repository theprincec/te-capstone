<template>
  <v-card class="pa-5" flat color="transparent">
        <v-card-title>
            <span class="headline">Upcoming Appointments</span>
            <v-spacer></v-spacer>
            <availability-form />
        </v-card-title>
           
      <div class="field">
            <label for="date">Select Date: </label>
            <input id="date" name="date" type="date"  v-model="todayDate" @change="toggleShowAppointment()"/>
        </div>
           <!-- <time-slot-card/> -->
       
         <!-- availability Form -->
              
           
      <div v-if="showAppointments">
        <div   v-for="appointment in getAppointmentsForToday" :key="appointment.id" >
            <v-card color="#FFF8DC" class="ma-5" elevation="10" outlined style="border-radius: 20px">
                <v-card-actions class="px-5 pb-0 mb-0">
                    <p class=" pt-2 mb-0 font-weight-medium"> <v-icon class="pr-2" meduim>mdi-account-clock</v-icon>
                    {{convertTime(appointment.timeStart)}} - {{convertTime(appointment.timeEnd)}}</p>
                      <v-spacer></v-spacer>
                    <p class="pt-3 mb-0">{{appointment.date}}</p>
                </v-card-actions>
               
                
                <v-card-actions class="px-5 pb-0 mb-0">
                    <p>Patient ID: {{appointment.patient.patientId}}</p>
                    <v-spacer></v-spacer>
                    <p>Name: <span class="font-weight-medium">{{appointment.patient.firstName}} {{appointment.patient.lastName}}</span></p>
                </v-card-actions>
                 
            </v-card>
        </div> 
        <div>
            <v-btn class="hidden-md-only hidden-xs-only" color="primary" dark @click.prevent="sendEmail">
                    Email Agenda 
                </v-btn>
        </div>   
      </div>
       <div v-if="!showAppointments">
           <v-alert
                text
                dense
                color="teal"
                icon="mdi-clock-fast"
                border="left"
                class="ma-4"
                >
      No appointments for today.
    </v-alert>
    <!-- <h4 class="pa-5">No appointments for today</h4> -->
       </div>
  </v-card>
</template>
<script>
import appointmentService from '@/services/AppointmentService'
import AvailabilityForm from '@/components/AvailabilityForm'
import emailService from '@/services/EmailService'
//import TimeSlotCard from '@/components/TimeSlotCard'


export default {
    name: "appointments-list",
    components: {
        AvailabilityForm
        //TimeSlotCard
    },
    data() {
        return {
            //appointments: [],
            showAppointments: true,
            todayDate: new Date().toISOString().split('T')[0]
        }
    },
    created() { 
            appointmentService.getAppointments()
                .then(response => {
                    this.$store.commit("SET_APPOINTMENTS", response.data);
                    this.toggleShowAppointment();
                    //SET ARRAY OF APPOINTMENTS IN STORE
        }).catch(error => {
            console.log(error)
        })
    },
    mounted() {
        let emailScript = document.createElement('script');
        emailScript.setAttribute('src', 'https://smtpjs.com/v3/smtp.js');
        document.head.appendChild(emailScript);
        //let toEmail = this.$store.state.currentPatient.email;
    },
    methods: {
        convertTime(time) { // 18:00:00
            let convertedTime = time.slice(0, 5); // 18:00
            let result;
            if (convertedTime.length > 1) { // If time format correct
                convertedTime = convertedTime.split (":");  // Remove full string match value - 18 00
                let timeUnder = (convertedTime[0] - 12 >= 0 || convertedTime[0] == 12) ? 'PM' : 'AM'; // Set AM/PM
                let hours = convertedTime[0] > 12 ? convertedTime[0] - 12 : convertedTime[0]; // Adjust hours
                let minutes = convertedTime[1];
                result = hours + ":" + minutes + " "+ timeUnder;
            }
            return result;
        },
        
        toggleShowAppointment() {
            this.showAppointments = this.getAppointmentsForToday.length > 0;
        },
        sendEmail() { 
            let emailDate = this.getAppointmentsForToday[0].date;
            let emailDoctor = this.$store.state.currentDoctor;
            let emailBody = this.printAppointmentList(emailDate);
            //let emailAppointment = this.appointment;

            emailService.sendAgendaEmail(emailDoctor, emailBody, emailDate);
        },
        printAppointmentList(date) { //prints out list of appointments for today--formatted
            const todaysList = this.$store.state.appointments.filter(appointment => {
                return appointment.date == this.todayDate;});
            var printedText = `<h2>Your Appointments for ${date}:</h2><br>`

            for (let appointment of todaysList){
                printedText += `<div style="border-radius:10px; background: linear-gradient(#f5bd43,orange, orange, darkorange); border: 2pt solid darkorange; width: 200px; text-align: center"><p style="color=black">${this.convertTime(appointment.timeStart)} - ${appointment.patient.lastName}, ${appointment.patient.firstName}</p></div> <br>`
            }
            printedText += `<br><p style="color: #999999">Have a great Day!<br>
                  Carengton</p>`
            return printedText;
        }
    },
    computed: {
        getAppointmentsForToday() {
            //let todayDate = new Date().toISOString().split('T')[0];
            return this.$store.state.appointments.filter(appointment => {
                return appointment.date == this.todayDate;
            })
        } 
    }
}
</script>
<style>
.field {
    margin: 0 20px 0 20px
}
</style>