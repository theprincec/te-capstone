import axios from 'axios';


export default {

    getDoctors() {
        return axios.get('/doctors');
    },

    // getDoctor() {
    //     return http.get('/doctor');
    // }

    // getOfficeInfo(doctorId) {
        
    //     return http.get(`/doctors/${doctorId}/offices`)
    // }

//   login(user) {
//     return axios.post('/login', user)
//   },

//   register(user) {
//     return axios.post('/register', user)
//   }

}