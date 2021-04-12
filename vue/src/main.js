
import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import vuetify from './plugins/vuetify';
import VueGeolocation from 'vue-browser-geolocation'
import * as VueGoogleMaps from 'vue2-google-maps'


Vue.config.productionTip = false
Vue.use(VueGeolocation)


Vue.use(VueGoogleMaps,  {
  load: {
    key: 'AIzaSyATgsdy5U0rd5141DhkvR-AF_Dw0jz-wGA'
  }

})

Vue.component('google-map', VueGoogleMaps.Map);
Vue.component('google-map-marker', VueGoogleMaps.Marker);


axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
