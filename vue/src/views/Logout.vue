<template>
  <v-container>
    <h1 class="text-centered">Logout</h1>
  </v-container>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
