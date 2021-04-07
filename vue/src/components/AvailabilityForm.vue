<template>
 
    <v-card id="appointments"
            min-height="368"
            flat>
        <h3 class="px-9">You can view appointments here</h3>
        
        <form id="appointment-form" class="px-9 pb-9"  v-on:submit.prevent="addAnAppointment()">

            <v-select
                v-model="appointment.appointmentType"
                :items="items"
                label="Appointment Type"
            ></v-select>

            <v-text-field
                v-model="appointment.patientId"
                label="Patient ID"
                v-if="isAppointmentReqiured()"
                required
            ></v-text-field>

             <div class="field">
                <label for="date">Date: </label>
                <input id="date" name="date" type="date"  v-model="appointment.date"/>
            </div>

            <div class="field">
                <label for="startTime" style="color:rgb(118, 118, 118)">Start Time: </label>
                <input id="startTime" name="startTime" type="time"  v-model="appointment.timeStart"/>
            </div>

            <div class="field">
                <label for="endTime" style="color:rgb(118, 118, 118)">End Time: </label>
                <input id="endTime" name="endTime" type="time" v-model="appointment.timeEnd"/>
            </div>

            <v-btn
                form="appointment-form"
                class="mr-4"
                type="submit"
            >
            submit
            </v-btn>
            <v-btn @click="clearForm">
            clear
            </v-btn>
        </form>

        <!-- <form v-on:submit.prevent="addAnAppointment()">
            <div class="field">
                <label for="patientFirstName">Patient ID: </label>
                <input name="patientFirstName" type="text"  v-model="appointment.patient.patientId"/>
            </div>
            <div class="field">
                <label for="date">Date: </label>
                <input name="date" type="date"  v-model="appointment.date"/>
            </div>
                            
            <div class="field">
                <label for="startTime">Start Time: </label>
                <input name="startTime" type="time"  v-model="appointment.timeStart"/>
            </div>

            <div class="field">
                <label for="endTime">End Time: </label>
                <input name="endTime" type="time" v-model="appointment.timeEnd"/>
            </div>

            <div class="field">
                <label for="type">Appointment Type: </label>
                <select name="type" v-model="appointment.appointmentType">
                    <option value="Personal">Personal</option>
                    <option value="Appointment">Appointment</option>
                </select>
            </div>

            <div class="actions" style="color:green">
                <button type="submit">Save Appointment</button>
            </div>
        
        </form> -->
    </v-card>

</template>

<script>
import appointmentService from '@/services/AppointmentService';
export default {
    name: "availability-form", 
    data() {
        return {
            appointment: {
               
                patientId: "",            
                date: this.fromDateDisp, 
                timeStart: "", 
                timeEnd: "", 
                appointmentType: "Personal"
            },
            items: [
                'Personal',
                'Appoinment'
            ],
            fromDateMenu: false,
            fromDateVal: null,
            minDate: "2020-01-05",
            maxDate: "2019-08-30"
        }
    }, 
    methods: {
        addAnAppointment() {
            appointmentService.addAppointment(this.appointment).then(response => {
                if(response.status == 201) {
                    this.clearForm();
                    alert("Appointment successfully saved");
                }
            })
            .catch(error => {
                console.log(error);
            })
        }, 
        clearForm() {
            this.appointment =  {
                patientId: "",
                date: "", 
                timeStart: "", 
                timeEnd: "", 
                appointmentType: "Personal"
            }
        },
        isAppointmentReqiured() {
            return this.appointment.appointmentType == 'Personal' ? false : true;
        }
    },
    computed: {
        fromDateDisp() {
            return this.fromDateVal;
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