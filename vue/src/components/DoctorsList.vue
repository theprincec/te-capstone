<template>
    
        
    

    <v-row>
        <v-col cols="12" md="12">
            <h1 id="doctor-title">View list of Doctors</h1>
        </v-col>
        <v-col cols="12" md="3">
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
        </v-col>
        <!-- vertical column for doctor's list -->
        <v-col cols="12" md="6">             
             <doctor-card class="mb-5" v-for="doctor in $store.state.doctors" :key="doctor.id" :doctor="doctor" />
        </v-col>
        <v-col cols="12" md="3">
            <v-card
                rounded="lg"
                min-height="368">
            </v-card>
        </v-col>
    </v-row>
</template>

<script>
import doctorService from '@/services/DoctorService'
import DoctorCard from '@/components/DoctorCard'

export default {
    name: "doctors-list",
    components: {
        DoctorCard
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

<style>
/* .container {
    border: 1px solid red;
} */
#doctor-title{
    text-align: center;
}
</style>