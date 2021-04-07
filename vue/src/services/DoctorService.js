import axios from 'axios';



export default {

    getDoctors() {
        return axios.get('/doctors');
    },

    updateOfficeForDoctor(doctor) {
        return axios.put('/doctors', doctor);
    }

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