import axios from 'axios';


const url = "https://smtpjs.com/v3/smtp.js";

const newAxios = axios.create();


export default {

    letsSendEmails(){
        return newAxios.get(url);
    }

//   login(user) {
//     return axios.post('/login', user)
//   },

//   register(user) {
//     return axios.post('/register', user)
//   }

}
