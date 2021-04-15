<template>

    <div class="time-slot"> 
        <!-- <h1 v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`">{{time}}</h1> -->
        <form id = "appointment-time-slot" v-on:submit.prevent="addAnAppointment()">
            <div class="field" >
                <label for="slotDate">Select Date:</label>
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
            <div   v-for="(time, index) in $store.state.timeSlots" v-bind:key="`time-${index}`" >
            <v-card  id="timeslotcard"  class="ma-5" elevation="10" outlined >

                <v-card-actions class="px-5 py-5 mb-0">
                    <div id="icon">
                        <v-icon class="pr-2" meduim>mdi-account-clock</v-icon>
                    </div>
                    <div id="time-container"> 
                    <p class=" pt-2 mb-0 font-weight-medium"> 
                        
                    {{convertTime(time)}}</p>
                    </div>
                     <div id="button-container">
                  <book-appointment v-bind:time="time"/>
                </div>
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
import BookAppointment from '@/components/BookAppointment'


export default {
    name: "time-slot",
    components: {
        DoctorCard,
        BookAppointment       
    },
    data(){
        

         return {
            timeSlot:[{}],
            doctorId: "",
            currentDate:"",
            appointment:{
                timeStart:""
            }
        }
    },
    methods: {
        setDate() {
           this.$store.commit("SET_CURRENT_DATE", this.currentDate);
           this.getTimeSlots();
            
        },
        setCurrentTime(time) {
            //this.appointment.timeStart = this.$store.state.timeSlots[index];
            this.$store.commit("SET_CURRENT_APPOINTMENT", time)
        },
        getTimeSlots(){
            AppointmentService.viewTimeSlots(this.$store.state.currentDoctor.doctorId, this.$store.state.currentDate)
                .then(response => {
                    if (response.status == 200) {
                        this.$store.commit("SET_TIME_SLOTS", response.data);
                    }
                } )
                .catch( error =>{
                    console.error(error);
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
#timeslotcard {
   
    min-height: 60px;
    background-color: #fff8dc;
    border-radius: 20px
}
#timeslotcard:hover {
    background-color: #f48e115d;
}

.field {
    margin: 0 20px 0 20px
}
#slotDate {
    padding: 8px 0 8px 8px;
    line-height: 20px;
    border-bottom: 1px solid rgb(118, 118, 118);
    width: 100%;
    border-color: (rgb(118, 118, 118), rgb(133, 133, 133));
    color: rgb(133, 133, 133);
    max-height: 32px;
}
#icon {
    display: inline-block;
    width: 30px
}
#time-container {
    display: inline-block;
    width: 50%
}
/* #button-container  {
    display: inline-block;
    width: 100px;
  
} */
</style>