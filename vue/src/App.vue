<template>
  <v-app>
    <v-app-bar>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </v-app-bar>
      <router-view />
  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {
  }

};
</script>
