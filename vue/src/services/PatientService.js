import axios from 'axios';



export default {

    getPatient(patientId) {
        
        return axios.get(`/patients/${patientId}`);
    }

}