<template>

<v-container id="register" class="mt-15" fluid>
  <v-layout>
      <!-- <v-flex md5 class="primary" id="background">
      </v-flex> -->
      <v-flex align-self-center>
        <!-- <v-card elevation="0" class="d-flex justify-center mx-auto mb-10">
          <v-img src="../assets/carehub.png" max-height="150" max-width="134">
          </v-img>
        </v-card> -->
        <v-card elevation="7" width="600" class="mx-auto ">
          <v-card-title class="pb-8 display-1" id="title-container">
          Create Account
        </v-card-title>
        <v-card-text class="pb-0">

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
          <v-row>
            <v-col col="6">
             <v-text-field 
              prepend-icon="mdi-account-outline"
              :rules="[v => !!v || 'First name is required']"
              v-model="user.firstName"
              label="First Name"
              required
              dense
               class="ml-4" 
            />
            </v-col>
            <v-col col="6">
            <v-text-field 
              prepend-icon="mdi-account-outline"
              :rules="[v => !!v || 'Last name is required']"
              v-model="user.lastName"
              label="Last Name"
              required
              dense
               class="mr-4" 
            />
            </v-col>
          </v-row>

            <v-text-field 
              v-model="user.username"
              :rules="[v => !!v || 'Username is required']"
              label="Username"
              prepend-icon="mdi-account-circle"
              required
              dense   
              class="mx-4"       
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
              dense
               class="mx-4" 
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
              dense
               class="mx-4" 
            />
            <!-- EMAIL -->
            <v-text-field 
              prepend-icon="mdi-email"
              :rules="[v => !!v || 'Email is required']"
              v-model="user.email"
              label="Email Address"
              required
              dense
               class="mx-4" 
            />
           
          <center>
            <p class="pt-2 mb-2">Are you a patient or a doctor?</p>
          </center>
          
          <v-radio-group class="px-15 mt-2 " v-model="user.role" row align-center mandatory>       
              <v-radio label="Patient" value="user" name="role"></v-radio>
              <v-spacer></v-spacer>
              <v-radio label="Doctor" value="doctor" name="role"></v-radio>
          </v-radio-group>

          </v-form>
        </v-card-text>

        <v-card-actions class="pt-0">
          <router-link :to="{ name: 'login' }" style="text-decoration: none">
            <v-btn color="blue" text id="log-in-text">HAVE AN ACCOUNT? LOG IN</v-btn>
          </router-link>
        </v-card-actions>
        <v-divider></v-divider>
        <v-card-actions >
          <v-btn :disabled= !valid 
            block
            type="submit" 
            form="form-register" class="mr-4 white--text"
            @click="validate"  id="sign-in-button">Register</v-btn>
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
        email: '',
        role: 'user',
        firstName: '',
        lastName: ''
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
                query: { registration: 'success' }
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              //this.registrationErrorMsg = 'Bad Request: Validation Errors';
              this.registrationErrorMsg = 'Username already exists. Please try again.';

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

<style>
/* #background {
  background-image: url('../assets/img.png')
} */
#sign-in-button{
  background-color: #f4931c;
}
#sign-in-button:hover{
  background-color: #f45d1c
}
#title-container{
  width: 100%;
  padding-left: 180px;
}
#log-in-text{
  width: 100%;
  padding-left: 180px;
}

</style>
