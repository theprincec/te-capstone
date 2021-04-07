<template>
     <form id="appointment-form" class="px-9 pb-9"  v-on:submit.prevent="addAnAppointment()">

            <!-- <v-select
                v-model="appointment.appointmentType"
                :items="items"
                label="Appointment Type"
            ></v-select> -->

            <!-- <v-text-field
                v-model="appointment.patientId"
                label="Patient ID"
                v-if="isAppointmentReqiured()"
                required
            ></v-text-field> -->

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

     
</template>

<script>
import AppointmentService from "@/services/AppointmentService.vue"
export default {
    name: "book-appointment",
    data() {
        return {
            appointment: [
                
            ]
        }
    }
    
    
}
</script>

<style>

</style>