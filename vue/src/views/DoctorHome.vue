<template>
<v-container grid-list-md fluid >

  <v-app-bar class="px-5" app color="#666666" flat>
    <v-tabs 
      class="ml-n9"
      color="grey darken-1"
    >
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
      Welcome, {{$store.state.currentDoctor.firstName}} {{$store.state.currentDoctor.lastName}}!
     <v-avatar
        class="hidden-sm-and-down mx-2"
        color="grey"
        size="38"
      >
      <v-icon dark>
        mdi-account-circle
      </v-icon>
      </v-avatar>
      </v-btn>
 
    </v-app-bar>
    <!-- appointtent availablity -->
   
      
      <office-info/>
     
        

     
    
</v-container>
</template>

<script>
import OfficeInfo from '@/components/OfficeInfo'

export default {
  name: "home",
  components: {
    OfficeInfo
  }
};
</script>

<style scoped>
#start {
  background-color: #f4931c;
  border:white;
}
#start:hover{
  background-color: #f45d1c;
}.button {
  background-color:#f4931c29
}
.button:hover, .button:active {
  background-color:#f45d1c
}
</style>
