<template>
  <v-content>
    <h1 class="text-centered">Logout</h1>
  </v-content>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
