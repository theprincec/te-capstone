<template>
  <v-app id="everything">
    <v-main>
       
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
.flex {
  border: 1px solid red;
}
</style>
