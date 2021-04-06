import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:8080"
// }); 

export default {

    getOfficeInfo(doctorId) {
        
        return axios.get(`/doctors/${doctorId}/offices`)
    },
    updateOfficeInfo(office){
        return axios.put(`/offices`, office)
    }

//   login(user) {
//     return axios.post('/login', user)
//   },

//   register(user) {
//     return axios.post('/register', user)
//   }

}
