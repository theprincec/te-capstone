<template>
<v-container grid-list-md fluid >

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
      Welcome, Admin!
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
   
      
     
        

      <v-container >
        <v-row>
          <v-col cols="12" md="12">
              <v-card id="docs"
                  rounded="lg"
                  min-height="100">
                  <p>We will put drag and drop office stuff here next sprint. </p>
              </v-card>
          </v-col>
        </v-row>
      </v-container>

     
    
</v-container>
</template>

<script>

export default {
  name: "admin_home",
 
};
</script>
