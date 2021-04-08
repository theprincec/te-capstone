<template>
    <div class="time-slot"> 
        <!-- <h1 v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">{{time}}</h1> -->
        <form id = "appointment-time-slot" v-on:submit.prevent="addAnAppointment()">
            <div>
                <label for="slotDate">Date:</label>
                <input id="slotDate" name="slotDate" type="date" v-bind="$store.state.currentDate"/>
            </div>

            <div>
                <label for="timeslot">Available Appointments:</label>
                <select id="timeSlotsList" name="times">
                    <!-- <option v-for="time in timeSlots" v-bind:key="time">{{time}}</option> -->
                    <option v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">{{time}}</option>
                </select>
            </div>
            </form>
<doctor-card/>
    </div>
</template>

<script>
import DoctorCard from '@/components/DoctorCard'
import AppointmentService from "@/services/AppointmentService.js"


export default {
    name: "time-slot",
    components: {
        DoctorCard
    },
    data(){
        
        return {
            // doctorid = doctorFromRouter
            timeSlot:[{

            }],
            doctorId: "1"
        }
    },
    methods: {
        setDate() {
            return this.$store.state.currentDate
        },
        getTimeSlots(){
            AppointmentService.viewTimeSlots(this.$store.state.currentDoctor.doctorId,this.$store.state.currentDate)
                .then(response => {
                    this.$store.state.timeSlots = response.data;
                    
                } )
                .catch( error =>{
                    console.error(error);
                })
        }
    },
    created() {
        //this.setDate();
        //this.getTimeSlots();
        // doctorService.getDoctors()
        // .then(response => {
        //     this.$store.commit("SET_DOCTORS", response.data);
        //     // return this.getTimeSlots();
            
        // }).catch( error => {
        //     console.error( error );
        // });
    },
    computed: {
        timeSlotGetter(){
            return this.getTimeSlots(); 
        }
    }
}
    

 

   
</script>

<style>
/* div {
    border: 1px solid red;
} */

</style>