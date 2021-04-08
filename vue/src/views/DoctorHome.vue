<template>
<v-container grid-list-md fluid >

  <v-app-bar class="px-5" app color="primary" flat>
    <v-tabs
      class="ml-n9"
      color="grey darken-1"
    >
    <!-- <router-link :to="{ name: 'home' }" style="text-decoration: none" >
      <v-tab class="pa-6 mx-auto" style="color:white">Home</v-tab>
    </router-link>
    <v-divider vertical></v-divider>
      
    <router-link v-bind:to="{ name: 'logout' }" 
      v-if="$store.state.token != ''"
      style="text-decoration: none">
      <v-tab class="pa-6 mx-auto" style="color:white">Logout</v-tab>
    </router-link> -->
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
    <!-- appointtent availablity -->
   
      
      <office-info />
     
    
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
    DoctorsList