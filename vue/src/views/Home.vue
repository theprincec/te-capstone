<template>
<div>
  <v-container class="fill-height pa-0" fluid>
    <v-img :aspect-ratio="16/9" :cover="true"
          width="1920" v-if="!$store.state.currentUserRole" src="../assets/background.png" >

    </v-img>
  </v-container>

  <doctor-home v-if="$store.state.currentUserRole == 'ROLE_DOCTOR'"></doctor-home>

  <admin-home v-if="$store.state.currentUserRole == 'ROLE_ADMIN'"></admin-home>

  <v-container grid-list-md fluid color="primary" v-if="($store.state.currentUserRole == 'ROLE_USER')">

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

    <v-tab class="pa-6"  style="color:white">
      Welcome, {{$store.state.currentPatient.firstName}} {{$store.state.currentPatient.lastName}}!
     <v-avatar
        class="hidden-sm-and-down mx-2"
        color="grey darken-1 shrink"
        size="38"
      >
      <v-icon dark>
        mdi-account-circle
      </v-icon>
      </v-avatar>
      </v-tab >
    </v-tabs >

    </v-app-bar>



   
      <doctors-list />
      <!-- <mapper /> -->
     
</v-container>
</div>
</template>

<script>
import DoctorsList from '@/components/DoctorsList'
import DoctorHome from '@/views/DoctorHome'
import AdminHome from '@/views/AdminHome'
// import Mapper from '@/components/Mapper'



export default {
  name: "home",
  components: {
    DoctorsList,
    DoctorHome, 
    AdminHome,
    // Mapper
  }
};
</script>

    