import axios from 'axios';



export default {

    getPatient(patientId) {
        
        return axios.get(`/patients/${patientId}`);
    },

    getCurrentPatient() {
        
        return axios.get("/patient");
    }, 

    getPatientsList() {
        return axios.get("/patients");
    }

}