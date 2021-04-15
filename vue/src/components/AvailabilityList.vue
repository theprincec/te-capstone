<template>

  <v-card class="pa-5">

      <v-card-title>
                <span class="headline">Upcoming Appointments</span>
                <v-spacer></v-spacer>
                 <availability-form />
            </v-card-title>
           
      <div class="field">
            <label for="date">Select Date: </label>
            <input id="date" name="date" type="date" v-model="todayDate" @change="toggleShowAppointment()"/>
        </div>

       
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
      </div>
       <div v-if="toggleShowAppointment">
           <h4>No appointments for today</h4>
       </div>
  </v-card>
</template>

<script>
import appointmentService from '@/services/AppointmentService'
import AvailabilityForm from '@/components/AvailabilityForm'

export default {
    name: "appointments-list",
    props: [showNoAppointments],
    components: {
        AvailabilityForm
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
    computed: {
        getAppointmentsForToday() {
            let todayDate = new Date().toISOString().split('T')[0];
            return this.$store.state.appointments.filter(appointment => {
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

<style scoped>
.field {
    margin: 0 20px 0 20px
}

</style>