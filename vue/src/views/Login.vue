<template>
  <v-container ma-0 pa-0 fill-height fluid>
    <v-layout>
      <v-flex md5 class="primary" id="background">
      </v-flex>
      <v-flex md7 align-self-center>
        <v-card elevation="0" class="d-flex justify-center" mx-auto>
          <v-img src="../assets/carehub.png" max-height="170" max-width="134">
          </v-img>
        </v-card>
        <v-card elevation="4" width="400" class="mx-auto mt-5">
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
              form="form-signin" class="mr-4" id="sign-in-button"
              @click="validate">
              Sign In
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
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

<style scoped>
.card {
  border: 1px solid red;

}
#sign-in-button{
  background-color: #aaaaaa;
}
#sign-in-button:hover{
  background-color: #f48d11;
}
#title-container{
  width: 100%;
  padding-left: 80px;
}
#log-in-text{
  width: 100%;
  padding-left: 60px;
}
#background {
  background-image: url('../assets/img.png')
}


</style>