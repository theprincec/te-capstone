import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:8080"
// }); 

export default {

    addAppointment(appointment) {
        return axios.post("/appointments", appointment);
    }
    // ,

    // viewTimeSlots(doctorId) {
    //     return axios.get(`/doctors/${doctorId}/appointments`)
    // }


}