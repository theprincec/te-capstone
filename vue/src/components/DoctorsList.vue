<template>

    <!-- <v-row >
        <v-col cols="12" md="12">
            <h1 id="doctor-title">View list of Doctors</h1>
        </v-col>
    </v-row> -->
        <!-- <v-col cols="12" md="3">
            <v-card
                rounded="lg"
                min-height="368">
                <v-card-title>
                    Narrow doctors search:
                </v-card-title> 
                <v-card-text>
                    <v-text-field
                        class="my-5"
                        dense
                        solo
                        clearable
                        color="blue-grey lighten-2"
                        label="Name"
                    ></v-text-field>  
                    <v-text-field
                        class="my-5"
                        dense
                        solo
                        clearable
                        color="blue-grey lighten-2"
                        label="Location"
                    ></v-text-field>  
                    <v-text-field
                        class="my-5"
                        dense
                        solo
                        clearable
                        color="blue-grey lighten-2"
                        label="Specialty"
                    ></v-text-field>
                </v-card-text>
            </v-card>
        </v-col> -->
        <!-- vertical column for doctor's list -->
<v-container>
    <v-row>
        <v-col cols="12" md="12" class="mt-5">
            <v-card id="docs"
                rounded="lg"
                min-height="100"
                flat>

                <v-row>
                    
                     <v-col col="12" md="12" >
                         <!-- <v-card-title class="headline pb-0">Welcome to Homepage</v-card-title> -->
                        <v-card-title class="text-h4 pt-1 bold"> Choose a Doctor </v-card-title>
                        <v-card-text class=" text-md-body-1">
                            Select a Doctor to make an appointment with.
                        </v-card-text>
  
                     </v-col>
                </v-row>
            </v-card>
        </v-col>
    </v-row>
    <v-row justify="space-around" class="mt-5">
        <v-col cols="12" md="7">  
                    
             <doctor-card class="mb-5" v-for="doctor in $store.state.doctors" 
             :key="doctor.doctorId" :doctor="doctor" />
        </v-col>

 <!-- UPDATE WITH MAP COMPONENT -->
        <v-col cols="12" md="5">
            <v-card
            
                rounded="lg"
                class="hidden-sm-and-down"
                max-height="620"
                max-width="620">
                <!-- <book-appointment/> -->
                <mapper/>
                

            </v-card>
        </v-col>
    </v-row>
</v-container>
</template>

<script>
import doctorService from '@/services/DoctorService'
import DoctorCard from '@/components/DoctorCard'
// import BookAppointment from './BookAppointment.vue'
import Mapper from '@/components/Mapper'




export default {
    name: "doctors-list",
    components: {
        // BookAppointment,
        DoctorCard,
        Mapper

       
    },
    methods: {

        // setCurrentDoctor(id) {
        //     //route here
        //     this.$store.state.Id = id;
        //     TimeSlotCard.getTimeSlots();
        // }
    },
    created() {
        doctorService.getDoctors()
        .then(response => {
            this.$store.commit("SET_DOCTORS", response.data);
        }).catch( error => {
            console.error( error );
        });
    }

}
</script>

<style scoped>
/* .container {
    border: 1px solid red;
} */
#doctor-title{
    text-align: center;
}
/* .row{
    background-image: linear-gradient(#ffffff,#ffffff,#cccccc);
} */

</style>