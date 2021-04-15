<template>
    <v-card class="mx-auto pa-2">

        <v-row>
            <v-col col="12" md="2">
                <v-card class="ml-5 mt-5" height="120" max-width="120">
    <!-- DOCTOR IMAGE -->

                    <v-img
                        class="hidden-sm-and-down"
                        height="120" max-width="120"
                        v-if="!fileDoctorUrl"
                        src="../assets/placeholder.jpg"
                        ></v-img>
                        <v-img
                        class="hidden-sm-and-down"
                        height="120" max-width="120"
                        contain v-if="fileDoctorUrl"
                        :src="fileDoctorUrl"
                        alt="Doctor Image"
                    ></v-img>
                </v-card>
            </v-col>
                <v-col col="12" md="6" >
                <v-card-title class="h4" >Dr. {{doctor.firstName}} {{doctor.lastName}}</v-card-title>
                <v-card-text>
                
                <div class="d-flex justify-space-between subtitle-1">
                        <p class="ma-1">Office name: {{doctor.office.name}}</p>
                        <p class="ma-1">{{convertTime(doctor.office.openTime)}} - {{convertTime(doctor.office.closeTime)}}</p>
                    </div>

                    <div class="body-2">
                        <p class="pb-1">{{doctor.office.address.addressLine}} {{doctor.office.address.city}} {{doctor.office.address.district}} {{doctor.office.address.postalCode}}</p>
                    </div>
                    <v-row
                    align="center"
                    class="mx-0"
                >
                <!-- change hardcoded value to rating -->
                </v-row>
                </v-card-text>
                </v-col>
        </v-row>
       
        

        <v-card-actions>
            <v-rating 
            v-model="rating"
            color="amber"
            dense
            background-color="grey lighten-1"
            empty-icon="$ratingFull"
            half-increments
            hover
            size="14"
            ></v-rating>

            <span class="grey--text ml-4">
        <!-- change hardcoded value to rating (counter for reviews??)-->
            4.5 (413)
            </span>
            <v-spacer></v-spacer>
            <a style="text-decoration: none" href=# class="blue--text" @click="setCurrentDoctor(doctor)">Check availability</a>
        </v-card-actions>
        <!-- <office-info :doctorId="doctor.id" />-->
            <!-- <h3>{{doctor.office.name}}</h3>
            <h2>Address:</h2>
            <p>{{doctor.office.address.addressLine}}</p>
            <p>{{doctor.office.address.city}} {{doctor.office.address.district}} {{doctor.office.address.postalCode}}</p>
            <p>Hours of Operation:</p>
            <p>{{doctor.office.openTime}} - {{doctor.office.closeTime}}</p>
            <p>{{doctor.office.phoneNumber}}</p> -->
    </v-card>
</template>

<script>
//import OfficeInfo from "@/components/OfficeInfo.vue"
//import officeService from '@/services/OfficeService'
import firebase from 'firebase/app'

export default {
    name: "doctor-card",
    props: ['doctor'],
    data: () => ({
        rating: 4.5,
        fileDoctorUrl: null,
    }),
    
    methods: {
        setCurrentDoctor(doctor) {
            this.$store.commit("SET_CURRENT_DOCTOR", doctor);
            this.$router.push('/patient');
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
        // doctorId() {
        //     return this.$store.state.doctors;
        // }
    },
    created() {
//ACCESS COLLECTION FROM FIRESTORE
//each card has it's own doctorId
//pull info from the doctors array and check if doctrId matches to the doctor - pull the image from fb
        // this.doctorsList.forEach((doc) => {
        //     console.log(doc.doctorId)
        //     if(doc.doctorId == this.doctor.doctorId) {
                const doctrId = this.doctor.doctorId;
                console.log(doctrId);
            firebase.firestore().collection("doctors").doc(`${doctrId}`)
                .get()
                .then((doc) => {
                    if(doc.exists) {
                        console.log(doc.id, " => ", doc.data());
                        this.fileDoctorUrl = doc.data().link;
                    } else {
                        console.log("Error")
                    }   
                })
                .catch((error) => {
                    console.log("Error getting documents: ", error);
                })
                }
        // })
        
    // }
}

   
</script>

<style>
/* div {
    border: 1px solid red;
} */

</style>