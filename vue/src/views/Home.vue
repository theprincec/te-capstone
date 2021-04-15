<template>
<div class="pa-0 ma-0">
  <v-container v-if="!$store.state.currentUserRole" fluid class="pa-0">
    <v-app-bar  class="d-flex  flex-row-reverse" height="65" app color="#f4931c"  flat > 
      <!-- <v-tabs centered
        class="ml-n9 "
        color="grey darken-1"
      > -->
      <v-divider vertical></v-divider>
      <router-link :to="{ name: 'register' }" style="text-decoration: none">
        <v-btn class="pa-8 button" text color="white" >Register</v-btn>
      </router-link>
      <v-divider vertical></v-divider>
      <router-link :to="{ name: 'login' }" style="text-decoration: none" >
        <v-btn  class="pa-8 button" text color="white" >Log In</v-btn>
      </router-link>
      <v-divider vertical></v-divider>
      
      
      <!-- </v-tabs> -->
    </v-app-bar>

    <v-img id="background" src="../assets/homebackground.jpg" cover="true" alt="Carehub">
    </v-img>
    
    <v-card id="logo" max-width="400px" flat>
      <v-card-title>
         <v-img height="50" max-width="200" src="../assets/logo.png" alt="logo"/>
      </v-card-title>
     
      <v-card-text>
        <p class="text-h5 white--text">CAREHUB is a solution for enterprise scheduling and ease of mind. 
          We make schedule as convinient as possible for you and your clients!</p>
      </v-card-text>

      <v-card-actions class="text-center">
        <router-link :to="{ name: 'login' }" style="text-decoration: none" >
          <v-btn large id="start" color="white" outlined>
            Let's get started
          </v-btn>
        </router-link>
      </v-card-actions>
    </v-card>

  </v-container>
  <doctor-home v-if="$store.state.currentUserRole == 'ROLE_DOCTOR'"></doctor-home>

  <admin-home v-if="$store.state.currentUserRole == 'ROLE_ADMIN'"></admin-home>

  <v-container grid-list-md fluid color="primary" v-if="($store.state.currentUserRole == 'ROLE_USER')">

  <v-app-bar  class="d-flex  flex-row-reverse " app color="#f4931c"  flat > 
    <!-- <v-tabs 
      class="ml-n9"
      color="grey darken-1"
    > -->
    <v-divider vertical></v-divider>
    <router-link :to="{ name: 'home' }" style="text-decoration: none" >
      <v-btn class="pa-8 button" text color="white" >Home</v-btn>
    </router-link>

    
    <v-divider vertical></v-divider>
      
    <router-link v-bind:to="{ name: 'logout' }" 
      v-if="$store.state.token != ''"
      style="text-decoration: none">
      <v-btn class="pa-8 button" text color="white" >Logout</v-btn>
    </router-link>
    <v-divider vertical></v-divider>

    <v-btn class="py-8 pl-8 pr-6 button" text color="white" >
      Welcome, {{$store.state.currentPatient.firstName}} {{$store.state.currentPatient.lastName}}!
     <v-avatar
        class="hidden-sm-and-down mx-2"
        color="grey"
        size="38"
      >
      <v-icon dark>
        mdi-account-circle
      </v-icon>
      </v-avatar>
      </v-btn >
  

    </v-app-bar>



   
      <doctors-list />
      <!-- <div @click.prevent="sendEmail">
        <button > SEND EMAIL FUNCTION </button>
      </div>
      <div @click.prevent="sendTheEmail">
        <button > SEND THE EMAIL function </button>
      </div> -->
      <!-- <mapper /> -->
     
</v-container>
</div>
</template>

<script>
import DoctorsList from '@/components/DoctorsList'
import DoctorHome from '@/views/DoctorHome'
import AdminHome from '@/views/AdminHome'
//import emailService from '@/services/EmailService'
// import Mapper from '@/components/Mapper'



export default {
  name: "home",
  components: {
    DoctorsList,
    DoctorHome, 
    AdminHome,
    // Mapper
  }
}
</script>

<style scoped>
#background {
  height: 100vh;
  z-index: 0;
  position: relative;
  margin-top: -65px
}
#logo {
  position: absolute;
  top:15%;
  left:60%;
  background-color: transparent!important;
  
  border-color: transparent!important;
}
#start {
  background-color: #f4931c;
  border:white;
}
#start:hover{
  background-color: #f45d1c;
}
.button {
  background-color:#f4931c29
}
.button:hover, .button:active {
  background-color:#f45d1c
}


</style>

    