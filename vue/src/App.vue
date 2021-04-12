
<template>
  <v-app id="everything">
    <v-app-bar class="px-5" app color="primary" flat>
      <v-tabs 
        class="ml-n9"
        color="grey darken-1"
      >
      <router-link :to="{ name: 'login' }" style="text-decoration: none" >
        <v-tab class="pa-6 mx-auto" style="color:white">Sign In</v-tab>
      </router-link>
      <v-divider vertical></v-divider>
      
      
      </v-tabs>
    </v-app-bar>
    <v-main >
      
      <router-view />
       
  
    </v-main>
  </v-app>
  
</template>

<script>


export default {
  name: 'App',

  components: {
   
  }

};
</script>

<style scoped>

#everything{
  background-image: linear-gradient(#ffffff,#ffffff, #cccccc);
  
}
</style>
