<template>
<div>
  <doctor-home v-if="$store.state.currentUserRole == 'ROLE_DOCTOR'"></doctor-home>

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

    </v-tabs>
     <strong class="pa-3">Welcome, {{$store.state.currentPatient.firstName}} {{$store.state.currentPatient.lastName}}!</strong> 
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
     
</v-container>
</div>
</template>

<script>
import DoctorsList from '@/components/DoctorsList'
import DoctorHome from '@/views/DoctorHome'

export default {
  name: "home",
  components: {
    DoctorsList,
    DoctorHome
  }
};
</script>

    