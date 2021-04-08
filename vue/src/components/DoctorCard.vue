<template>
    <v-card class="mx-auto pa-2">
       
        <v-card-title class="h4" >Dr. {{doctor.firstName}} {{doctor.lastName}}</v-card-title>
        <v-card-text>
        
           <div class="d-flex justify-space-between subtitle-1">
                <p class="ma-1">Office name: {{doctor.office.name}}</p>
                <p class="ma-1">{{doctor.office.openTime}} - {{doctor.office.closeTime}}</p>
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

export default {
    name: "doctor-card",
    props: ['doctor'],
    data: () => ({
        rating: 4.5,
    }),
    methods: {
        setCurrentDoctor(doctor) {
            this.$store.commit("SET_CURRENT_DOCTOR", doctor);
        }
    }
    // components: { 
    //   OfficeInfo 
    // }
    // computed: {
    //     openTime(){
    //         doctor.office.openTime
    //     }
    // }
}

   
</script>

<style>
/* div {
    border: 1px solid red;
} */

</style>