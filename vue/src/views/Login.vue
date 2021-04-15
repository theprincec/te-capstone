<template>
  <v-container ma-0 pa-0 fill-height fluid >
    <v-layout >
      <v-img id="background" src="../assets/background.jpg" cover="true" alt="Doctor" >
      
      <v-flex d-flex justify-end id="align-center">
        <!-- <v-card elevation="7" class="d-flex justify-center mb-10" mx-auto>
          <v-img src="../assets/carehub.png" max-height="170" max-width="134">
          </v-img>
        </v-card> -->
        <v-card elevation="7" width="400px" >
          <v-card-title id="title-container">
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
      
              <v-alert type="error" v-if="invalidCredentials">
                Invalid username and password!
              </v-alert>
        

              <v-alert type="success" v-if="this.$route.query.registration"> 
                Thank you for registering, please sign in.
              </v-alert>
        
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
          
          <v-card-actions>
            <router-link :to="{ name: 'register' }" style="text-decoration: none">
              <v-btn color="blue" text id="log-in-text">NEW USER? CREATE AN ACCOUNT</v-btn>
            </router-link>
          </v-card-actions>

          <v-divider></v-divider>

          <v-card-actions>
            <v-btn :disabled= !valid 
              block
              type="submit" 
              form="form-signin" class="mr-4 white--text" id="sign-in-button"
              @click="validate"
              
            >Sign In
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
      </v-img>
    </v-layout>
  </v-container>
</template>

<script>
import authService from "../services/AuthService";
import doctorService from "../services/DoctorService";
import patientService from "../services/PatientService";

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
  created() {
    this.$store.commit("LOGOUT");
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$store.commit("SET_USER_ROLE", this.$store.state.user.authorities[0].name)
            this.setCurrentUserInfo(this.$store.state.user.authorities[0].name);
            
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
    setCurrentUserInfo(role) {
        if(role == "ROLE_USER") {
          patientService.getCurrentPatient()
              .then(response => {
                  this.$store.commit("SET_CURRENT_PATIENT", response.data);
              }).catch( error => {
                  console.error( error );
              });

        } else if(role == "ROLE_DOCTOR") {
            doctorService.getDoctor()
              .then(response => {
                  this.$store.commit("SET_CURRENT_DOCTOR", response.data);
              }).catch( error => {
                  console.error( error );
              });
        }
    }
  }
};
</script>

<style scoped>
#sign-in-button{
  background-color: #f4931c;
}
#sign-in-button:hover{
  background-color: #f45d1c
}
#title-container{
  width: 100%;
  padding-left: 80px;
}
#log-in-text{
  width: 100%;
  padding-left: 60px;
}
/* #background {
  background-image: url('../assets/background.jpg');
  background-size: cover;
} */
.img {
  position:relative;
}

#align-center {
   position: absolute;
  top: 30%;
  left: 67%
}
#background {
  height: 100vh;
}




</style>