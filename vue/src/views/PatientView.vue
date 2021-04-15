<template>
<v-container grid-list-md fluid >

  <v-app-bar class="px-5" app color="#666666" flat>
    <v-tabs 
      class="ml-n9"
      color="grey darken-1"
    >
    <router-link :to="{ name: 'home' }" style="text-decoration: none" >
      <v-tab class="pa-6 mx-auto" style="color:white">Home</v-tab>
    </router-link>
    <v-divider vertical></v-divider>
      
    <router-link v-bind:to="{ name: 'logout' }" 
      v-if="$store.state.token != ''"
      style="text-decoration: none">
      <v-tab class="pa-6 mx-auto" style="color:white">Logout</v-tab>
    </router-link>
    <v-divider vertical></v-divider>

    <v-tab class="pa-6"  style="color:white">
      Welcome, {{$store.state.currentPatient.firstName}} {{$store.state.currentPatient.lastName}}!
     <v-avatar
        class="hidden-sm-and-down mx-2"
        color="grey darken-1 shrink"
        size="38"
      >
      <v-icon dark >
        mdi-account-circle
      </v-icon>
      </v-avatar>
      </v-tab >
    </v-tabs >
    </v-app-bar>
    <!-- appointtent availablity -->
   
      
      <appointment-info />
     
    
</v-container>
</template>

<script>
import AppointmentInfo from '@/components/AppointmentInfo'
// import TimeSlotCard from '@/components/TimeSlotCard'




export default {
  name: "patient",
  components: {

    AppointmentInfo
    // TimeSlotCard


  }
};
</script>
   