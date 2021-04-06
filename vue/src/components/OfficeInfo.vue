<template>
  <v-row>
        <v-col cols="12"
                md="3">
            <v-card
                rounded="lg"
                min-height="368">
                <v-card-title>
                    Narrow appointments search:
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
        <v-col cols="12"
            md="6">
            <v-card class="mx-auto pa-2 mb-5">
       
                <v-card-title class="h4">{{doctor}}</v-card-title>
                <v-card-text>
                
                <div class="d-flex justify-space-between subtitle-1">
                        <p class="ma-1">Office name: </p>
                        <p class="ma-1">fdhkhjfdg</p>
                    </div>

                    <div class="body-2">
                        <p class="pb-1">bnvb</p>
                    </div>
                    <v-row
                    align="center"
                    class="mx-0"
                >
                <!-- change hardcoded value to rating -->
                </v-row>
                </v-card-text>

                <v-card-actions>
                    <a style="text-decoration: none" href=# class="blue--text">Check availability</a>
                </v-card-actions>
        
            </v-card>
        </v-col>
        <v-col cols="12"
                md="3">
            <v-card
                rounded="lg"
                min-height="368">
            </v-card>
        </v-col>
    </v-row>


</template>

<script>
import doctorService from '@/services/DoctorService'
//import officeService from '@/services/OfficeService'
//import OfficeCard from '@/components/OfficeCard'

export default {
    name: "office-info",

    // components: {
    //     OfficeCard
    // },
    // created() {
    //     doctorService.getDoctor()
    //     .then(response => {
    //         this.$store.commit("SET_CURRENT_DOCTOR", response.data);
    //     }).catch( error => {
    //         console.error( error );
    //     });
    // }
    computed: {
        doctor() {
            const doctor = this.$store.state.doctors.find(doctor => {
                doctor.userId == this.$store.state.user.id;
            })
            return doctor;
        }
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
.office-info {
    background-color: whitesmoke;
    border: 1px solid red;
    margin: 0 auto;
    width: 50%;
    text-align: center;
}
</style>