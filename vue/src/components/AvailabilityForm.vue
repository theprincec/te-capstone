<template>
    <div>
        <form v-on:submit.prevent="addAnAppointment()">
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
        
        </form>


    </div>
  
</template>

<script>
import appointmentService from '@/services/AppointmentService';
export default {
    name: "availability-form", 
    data() {
        return {
            appointment: {
                patient: {
                    patientId: ""
                },
                date: "", 
                timeStart: "", 
                timeEnd: "", 
                appointmentType: ""
            }
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
                patient: {
                    firstName: "", 
                    lastName: ""
                },
                date: "", 
                timeStart: "", 
                timeEnd: "", 
                appointmentType: ""
            }
        }
    }

}
</script>

<style>
form select{
    border: 1pt solid gray;
    border-radius: 2pt;
    box-shadow: 2px 2px #aaaaaaaa;
}
</style>