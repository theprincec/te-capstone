<template>

    <div class="time-slot"> 
        <!-- <h1 v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">{{time}}</h1> -->
        <form id = "appointment-time-slot" v-on:submit.prevent="addAnAppointment()">
            <div>
                <label for="slotDate">Date:</label>
                <input required id="slotDate" name="slotDate" type="date" v-model="currentDate" @change="setDate()"/>
            </div>

            <!-- <div>
                <label for="timeslot">Available Appointments:</label>
                <select id="timeSlotsList" name="times">
                    <option v-for="time in timeSlots" v-bind:key="time">{{time}}</option>
                    <option v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">{{time}}

                        
                    </option>
                </select>
            </div> -->
            <!-- <div v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">{{time}}   

            </div> -->
            </form>
            
            <!-- <div v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">{{time}}   

            </div> -->
            <div  v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">
            <v-card color="#FFF8DC" class="ma-5" elevation="10" outlined style="border-radius: 20px">

                <v-card-actions class="px-5 pb-0 mb-0">
                    <p class=" pt-2 mb-0 font-weight-medium"> <v-icon class="pr-2" meduim>mdi-account-clock</v-icon>
                    {{time}}</p>
                      <v-spacer></v-spacer>
                    <!-- <p class="pt-3 mb-0">{{time}}</p> -->
                </v-card-actions>
               
                
                <!-- <v-card-actions class="px-5 pb-0 mb-0">
                    <p>Patient ID: {{appointment.patient.patientId}}</p>
                    <v-spacer></v-spacer>
                    <p>Name: <span class="font-weight-medium">{{appointment.patient.firstName}} {{appointment.patient.lastName}}</span></p>

                </v-card-actions> -->
                 
            </v-card>
        </div>  
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
            doctorId: "",
            currentDate:""
        }
    },
    methods: {
        setDate() {
           this.$store.commit("SET_CURRENT_DATE", this.currentDate);
           this.getTimeSlots();
            
        },
        getTimeSlots(){
            AppointmentService.viewTimeSlots(this.$store.state.currentDoctor.doctorId,this.$store.state.currentDate)
                .then(response => {
                    if (response.status == 200) {
                        this.$store.commit("SET_TIME_SLOTS", response.data);
                    }
                   
                    
                    
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