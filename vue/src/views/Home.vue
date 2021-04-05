<template>
  <v-app>
    <v-app-bar app color="primary">
      <v-toolbar-title 
        class="font-weight-bold font-italic"
        style="color: white">
          CAREHUB
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn>Home</v-btn>
    </v-app-bar>
    <v-content>
      <p class="text-center">You must be authenticated to see this</p>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: "home"
};
</script>
