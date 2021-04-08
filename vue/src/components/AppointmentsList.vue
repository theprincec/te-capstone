<template>

  <v-card>

      <div class="field">
            <label for="date">Date: </label>
            <input id="date" name="date" type="date"  v-model="todayDate" @change="toggleShowAppointment()"/>
        </div>

      <div v-if="showAppointments">
        <div v-for="appointment in getAppointmentsForToday" :key="appointment.id" >
                <p>{{appointment.date}}</p>
                <p>{{convertTime(appointment.timeStart)}} - {{convertTime(appointment.timeEnd)}}</p>
                <p>Patient ID: {{appointment.patient.patientId}} Name: {{appointment.patient.firstName}} {{appointment.patient.lastName}}</p>

        </div>  
      </div>
       <div v-if="!showAppointments">
           <h4>No appointments for today</h4>
       </div>
  </v-card>
</template>

<script>
import appointmentService from '@/services/AppointmentService'

export default {
    name: "appointments-list",
    data() {
        return {
            appointments: [],
            showAppointments: true,
            todayDate: new Date().toISOString().split('T')[0]
        }
    },
    created() { 
            appointmentService.getAppointments()
                .then(response => {
                    this.appointments = response.data;
                    this.toggleShowAppointment();
        }).catch(error => {
            console.log(error)
        })
    },
    computed: {
        getAppointmentsForToday() {
            //let todayDate = new Date().toISOString().split('T')[0];
            return this.appointments.filter(appointment => {
                return appointment.date == this.todayDate;
            })
        }
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
        }
    }
}
</script>

<style>

</style>