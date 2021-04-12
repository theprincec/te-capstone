<template>


<v-container grid-list-md fluid color="primary">

<!-- <gmap-map
  :center={lat:10,lng:10}
  :zoom="7"
  style="width: 100%; height: 320px;"
  ></gmap-map> -->
  

  <v-app-bar class="px-5" app color="primary" flat>
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

    </v-tabs>
     <strong class="pa-3">Username</strong> 
     <v-avatar
        class="hidden-sm-and-down"
        color="grey darken-1 shrink"
        size="38"
      >
      <v-icon dark>
        mdi-account-circle
      </v-icon>
      </v-avatar>
    </v-app-bar>



       
      <doctors-list />
      <!-- <time-slot-card/> -->
    <!--<office-info />-->
    <!-- <mapper /> -->
    
</v-container>
</template>

<script>
import DoctorsList from '@/components/DoctorsList'
// import TimeSlotCard from '@/components/TimeSlotCard.vue'
//import OfficeInfo from '@/components/OfficeInfo'
// import Mapper from '@/components/Mapper'



export default {
  name: "home",
  components: {
    DoctorsList,
    // Mapper
  
  },
  
};

</script>
