<template>

<div id="login">
   <v-card width="400" class="mx-auto mt-5">
     <v-card-title>
      <h1 class="display-1">Please Sign In</h1>
    </v-card-title>
    <v-card-text>

      <v-form id="form-signin"
        ref="form"
        v-model="valid"
        lazy-validation
        @submit.prevent="login"
        validate
        >
      <div>
        <v-alert type="error" v-if="invalidCredentials">
          Invalid username and password!
        </v-alert>
      </div>
      <div>
        <v-alert type="success" v-if="this.$route.query.registration"> 
          Thank you for registering, please sign in.
        </v-alert>
      </div>
        <v-text-field 
          v-model="user.username"
          :rules="[v => !!v || 'Username is required']"
          label="Username"
          prepend-icon="mdi-account-circle"
          required          
        />
        <v-text-field 
          v-model="user.password"
          :type="showPassword ? 'text' : 'password'"
          :rules="[v => !!v || 'Password is required']"
          label="Password"
          prepend-icon="mdi-lock"
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          @click:append="showPassword = ! showPassword"
          required
        />
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <router-link :to="{ name: 'register' }" style="text-decoration: none">
        <p style="color: success">Need an account?</p>
      </router-link>
      <v-spacer></v-spacer>
      <v-btn :disabled= !valid 
        type="submit" 
        color="success" form="form-signin" class="mr-4"
        @click="validate">Sign In</v-btn>
    </v-card-actions>
   </v-card>
  </div>

  <!-- <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>
  </div> -->
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      valid: true,
      showPassword: false,
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    validate () {
        this.$refs.form.validate()
      },
  }
};
</script>
