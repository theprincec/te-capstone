import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import vuetify from './plugins/vuetify'
import firebase from 'firebase/app'
import 'firebase/database'; // If using Firebase database
import 'firebase/storage'; 
import 'firebase/firestore'; 

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
  created() {
    const firebaseConfig = {
      apiKey: "AIzaSyA41PM4iluptuDeyA0emzeGZYHbMLwDFII",
      authDomain: "image-upload-39110.firebaseapp.com",
      databaseURL: "https://image-upload-39110-default-rtdb.firebaseio.com",
      projectId: "image-upload-39110",
      storageBucket: "image-upload-39110.appspot.com",
      messagingSenderId: "673613184241",
      appId: "1:673613184241:web:11ea7978626d66b590bd50"
  };
  //   // Initialize Firebase
      firebase.initializeApp(firebaseConfig);
  }
}).$mount('#app');






