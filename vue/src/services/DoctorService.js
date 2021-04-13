import axios from 'axios';



export default {

    getDoctors() {
        return axios.get('/doctors');
    },

    updateOfficeForDoctor(doctor) {
        return axios.put('/doctors', doctor);
    },

    getDoctor() {
        return axios.get('/doctor');
    },

    getOfficeInfo(doctorId) {
        
        return axios.get(`/doctors/${doctorId}/offices`)
    },

    getImageCount() {
        
        return axios.get('/images')
    },

    updateImageCount() {
        
        return axios.put('/images')
    }

//   login(user) {
//     return axios.post('/login', user)
//   },

//   register(user) {
//     return axios.post('/register', user)
//   }

}