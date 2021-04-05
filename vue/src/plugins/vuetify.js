import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#9c27b0',
                secondary: '#03a9f4',
                accent: '#673ab7',
                error: '#f44336',
                warning: '#e91e63',
                info: '#ff5722',
                success: '#4caf50'
            }
          },
      },
});
