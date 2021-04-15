<template>
  <v-row  justify="end" >
            <v-dialog
                v-model="dialog"
                persistent
                max-width="600px"
            >
                <template v-slot:activator="{ on, attrs }">
                    <v-btn class="mx-5 px-2" rounded small
                        color="primary"
                        dark
                        v-bind="attrs"
                        v-on="on"
                        @click="this.appointment.timeStart = convertTime(time), this.appointment.timeEnd = calculateTimeEnd"
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
            <p>Appointment date: {{$store.state.currentDate}}</p>
            <!-- <div class="field">
                <label for="startTime" style="color:rgb(118, 118, 118)">Start Time: </label>
                <input id="startTime" name="startTime" type="time" required v-model="appointment.timeStart"/>
            </div> -->
            <p>Start time: {{convertTime(time)}}</p>
            <p>End time: {{convertTime(calculateTimeEnd)}}</p>
            <!-- <div class="field">
                <label for="endTime" style="color:rgb(118, 118, 118)">End Time: </label>
                <input id="endTime" name="endTime" type="time" required v-model="appointment.timeEnd"/>
            </div> -->
<!--This button books an appointment-->
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
import emailService from '@/services/EmailService';
export default {
    name: "book-appointment", 
    props: ['time'],
    data() {
        return {
            dialog: false,
            appointment: {
                doctorId: "",
                patient: {
                    patientId: "",
                    firstName: "",
                    lastName: ""
                },            
                date: this.$store.state.currentDate, 
                timeStart: "", 
                timeEnd: "", 
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
    mounted() {
        let emailScript = document.createElement('script');
        emailScript.setAttribute('src', 'https://smtpjs.com/v3/smtp.js');
        document.head.appendChild(emailScript);
        //let toEmail = this.$store.state.currentPatient.email;
    },
    computed: {
        // calculateTimeStart() {
        //     const time = convertTime(this.time);
        //     return time;
        // },
        calculateTimeEnd() {
            //09:00:00
            let hours = parseInt(this.time.slice(0, 2));
           
                hours += 1;
            
            let fullTime = hours + this.time.slice(2);
            return fullTime;
        }
    },
    methods: {
        addAnAppointment() {
            this.appointment.timeStart = this.time;
            this.appointment.timeEnd = this.calculateTimeEnd;
            this.appointment.doctorId = this.$store.state.currentDoctor.doctorId;
            appointmentService.addAppointment(this.appointment).then(response => {
                if(response.status == 201) {
                    this.sendEmail();
                    //emailService.sendAppointmentEmail(this.appointment.timeStart);
                    alert("Appointment successfully booked");
                }
            })
            .catch(error => {
                console.log(error);
            })
        }, 
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
        sendEmail() {
            let emailPatient= this.$store.state.currentPatient;
            let emailDoctor = this.$store.state.currentDoctor;
            let emailAppointment = this.appointment;
            //let emailTime = convertTime(this.appointment.timeStart)
            emailService.sendAppointmentEmail(emailPatient, emailDoctor, emailAppointment);
        },
        
        clearForm() {
            this.appointment =  {
                patient: {},
                date: "", 
                timeStart: "", 
                timeEnd: "", 
                appointmentType: "Appointment"
            }
        },
        isAppointmentReqiured() {
            return this.appointment.appointmentType == 'Personal' ? false : true;
        },
        toggleDialog() {
            (this.appointment.date == "" || this.appointment.timeStart == "" || this.appointment.timeEnd == "") 
                    ? this.dialog = true : this.dialog = false;
        }
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

