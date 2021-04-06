<template>

<v-container id="register" ma-0 pa-0 fill-height fluid>
  <v-layout>
      <v-flex md5 class="primary">
      </v-flex>
      <v-flex md7 align-self-center>
        <v-card elevation="0" class="d-flex justify-center" mx-auto>
          <v-img src="../assets/orange.png" max-height="150" max-width="134">
          </v-img>
        </v-card>
        <v-card width="400" class="mx-auto mt-5">
          <v-card-title>
          <h1 class="display-1">Create Account</h1>
        </v-card-title>
        <v-card-text>

          <v-form id="form-register" 
            ref="form"
            v-model="valid"
            lazy-validation
            @submit.prevent="register"
            validate
            >
          <div>
            <v-alert type="error" v-if="registrationErrors">
              {{ registrationErrorMsg }}
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
              @click:append="showPassword = !showPassword"
              required
            />
            <v-text-field 
              v-model="user.confirmPassword"
              :type="showConfirmPassword ? 'text' : 'password'"
              :rules="[v => !!v || 'Please confirm password']"
              label="Confirm Password"
              prepend-icon="mdi-lock"
              :append-icon="showConfirmPassword ? 'mdi-eye' : 'mdi-eye-off'"
              @click:append="showConfirmPassword = ! showConfirmPassword"
              required
            />
          </v-form>
        </v-card-text>
        <v-card-actions>
          <router-link :to="{ name: 'login' }" style="text-decoration: none">
            <v-btn color="blue" text>HAVE AN ACCOUNT? LOG IN</v-btn>
          </router-link>
        </v-card-actions>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn :disabled= !valid 
            block
            type="submit" 
            color="success" form="form-register" class="mr-4"
            @click="validate">Register</v-btn>
        </v-card-actions>
      </v-card>
       </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      valid: true,
      showPassword: false,
      showConfirmPassword: false,
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
    validate () {
        this.$refs.form.validate()
      },
  },
};
</script>

<style></style>
