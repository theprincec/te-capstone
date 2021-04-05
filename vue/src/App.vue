<template>
  <v-app>
    <v-app-bar app color="primary">
      <router-link :to="{ name: 'home' }" style="text-decoration: none" >
        <v-btn style="color:white" text>Home</v-btn></router-link>
      <v-divider vertical v-if="$store.state.token != ''"></v-divider>
      <router-link v-bind:to="{ name: 'logout' }" 
        v-if="$store.state.token != ''"
        style="text-decoration: none">
        <v-btn style="color:white" text>Logout</v-btn></router-link>
    </v-app-bar>
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {
  }

};
</script>
