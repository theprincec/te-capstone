import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
            primary: '#ff9800',
            secondary: '#dd2c00',
            accent: '#c66900',
            error: '#FF5252',
            info: '#2196F3',
            success: '#4CAF50',
            warning: '#FB8C00',
            },
            dark: {
            primary: '#c66900',
            secondary: '#a30000',
            accent: '#ff6434',
            error: '#FF5252',
            info: '#2196F3',
            success: '#4CAF50',
            warning: '#FB8C00',
            },
        },
    }
});
