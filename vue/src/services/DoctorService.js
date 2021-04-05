import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
}); 

export default {

    getDoctors() {
        return http.get('/doctors');
    }

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