import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:8080"
// }); 

export default {

    addAppointment(appointment) {
        return axios.post("/appointments", appointment);
<<<<<<< HEAD
=======
    },

    viewTimeSlots(doctorId) {
        return axios.get(`/doctors/${doctorId}/appointments`)
    },

    getAppointments() {
        return axios.get('/appointments');
>>>>>>> a198c7870b1cf344e6ed0751ef27dfa9c576e8db
    }
    // ,

    // viewTimeSlots(doctorId) {
    //     return axios.get(`/doctors/${doctorId}/appointments`)
    // }


}