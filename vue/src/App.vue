
<template>
  <v-app id="everything">
    
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
