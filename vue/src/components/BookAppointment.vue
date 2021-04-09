<template>
  <v-row justify="center">
            <v-dialog
                v-model="dialog"
                persistent
                max-width="600px"
            >
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                        color="primary"
                        dark
                        v-bind="attrs"
                        v-on="on"
                    >
                    Book appointment
                </v-btn>
                
            </template>
    <v-card id="appointments"
            min-height="368" class="mt-5"
            flat>

            <v-card-title>
                <span class="headline">Book an appointment with Dr. {{$store.state.currentDoctor.firstName}} {{$store.state.currentDoctor.lastName}}</span>
            </v-card-title>

        <form id="booking-form" class="px-9 pb-9"  v-on:submit.prevent="addAnAppointment()">


            <!-- <v-text-field
                v-model="appointment.patient.patientId"
                label="Patient ID"
                v-if="isAppointmentReqiured()"
                required
            ></v-text-field> -->
            <p>Patient: {{appointment.patient.firstName}} {{appointment.patient.lastName}}</p>

             <!-- <div class="field">
                <label for="date">Date: </label>
                <input id="date" name="date" type="date" required v-model="appointment.date"/>
            </div> -->

            <p>Appointment date: {{appointment.date}}</p>

            <!-- <div class="field">
                <label for="startTime" style="color:rgb(118, 118, 118)">Start Time: </label>
                <input id="startTime" name="startTime" type="time" required v-model="appointment.timeStart"/>
            </div> -->
            <p>Start time: {{appointment.timeStart}}</p>
            <p>End time: {{appointment.timeEnd}}</p>


            <!-- <div class="field">
                <label for="endTime" style="color:rgb(118, 118, 118)">End Time: </label>
                <input id="endTime" name="endTime" type="time" required v-model="appointment.timeEnd"/>
            </div> -->

            <v-btn
                form="booking-form"
                class="mr-4"
                type="submit"
                @click="dialog=false"                
            >
            Confirm Appointment
            </v-btn>
            <v-btn @click="dialog=false">
            Cancel
            </v-btn>
        </form>

    </v-card>
     </v-dialog>
  </v-row>

</template>

<script>
import appointmentService from '@/services/AppointmentService';
import patientService from '@/services/PatientService';

export default {
    name: "book-appointment", 
    data() {
        return {
            dialog: false,
            appointment: {
                doctorId: this.$store.state.currentDoctor.doctorId,
                patient: {
                    patientId: "",
                    firstName: "",
                    lastName: ""
                },            
                date: this.$store.state.currentDate, 
                timeStart: this.$store.state.currentAppointment.timeStart, 
                timeEnd: calculateTimeEnd(), 
                appointmentType: "Appointment"
            }
        }
    }, 
    created() {
        patientService.getCurrentPatient()
            .then(response => {
                this.appointment.patient = response.data
            })
            .catch(error => {
                console.log(error);
            })

    },
    computed: {
        calculateTimeEnd() {
            let hours = this.timeStart.slice(0, 2);
            if(hours < 12) {
                hours += 1;
            } else{
                hours = 1;
            }   
            return hours + this.timeStart.slice(2);
        }
    },
    methods: {
        addAnAppointment() {
            appointmentService.addAppointment(this.appointment).then(response => {
                if(response.status == 201) {
                    alert("Appointment successfully booked");
                }
            })
            .catch(error => {
                console.log(error);
            })
        }, 
        getUpdatedAppointments() {
            appointmentService.getAppointments().then(response => {
                if(response.status == 200) {
                    this.$store.commit("SET_APPOINTMENTS", response.data);
                }
            })
            .catch(error => {
                console.log(error);
            })
        },
        
        // clearForm() {
        //     this.appointment =  {
        //         patient: {},
        //         date: "", 
        //         timeStart: "", 
        //         timeEnd: "", 
        //         appointmentType: "Appointment"
        //     }
        // },
        // isAppointmentReqiured() {
        //     return this.appointment.appointmentType == 'Personal' ? false : true;
        // },
        // toggleDialog() {
            // (this.appointment.date == "" || this.appointment.timeStart == "" || this.appointment.timeEnd == "") 
            //         ? this.dialog = true : this.dialog = false;
        //}
    }
}

</script>

<style>
#startTime, #endTime, #date {
    padding: 8px 0 8px 8px;
    line-height: 20px;
    border-bottom: 1px solid rgb(118, 118, 118);
    width: 100%;
    border-color: (rgb(118, 118, 118), rgb(133, 133, 133));
    color: rgb(133, 133, 133);
    max-height: 32px;
}
.field {
    padding: 8px 0 8px 
}
</style>