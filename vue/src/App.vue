
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
  /* background-image: linear-gradient(#cccccc, #666666); */
  background-image: linear-gradient(170deg, #d9e8ec,#777777,#666666);
  
}
</style>
