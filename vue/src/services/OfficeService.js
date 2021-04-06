import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
}); 

export default {

    getOfficeInfo(doctorId) {
        
        return http.get(`/doctors/${doctorId}/offices`)
    },
    updateOfficeInfo(office){
        return http.put(`/offices`, office)
    }

//   login(user) {
//     return axios.post('/login', user)
//   },

//   register(user) {
//     return axios.post('/register', user)
//   }

}
