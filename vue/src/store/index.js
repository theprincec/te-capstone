import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    //currentUserRole will = ROLE_USER (patient) or ROLE_DOCTOR or ROLE_ADMIN
    currentUserRole: '',
    doctors: [],
    office: {},
    timeSlots:[],
    currentDoctor: {},
    currentPatient: {},
    appointments: [],
    currentAppointment: {
          doctorId: "",    
          patient: {
              patientId: "",
              firstName: "",
              lastName: "",
              email: ""
          },            
          date: "", 
          timeStart: "", 
          timeEnd: "", 
          appointmentType: "Appointment"
  },
    currentDate: {}, 
    patients: [],
    timeEnd: "", 
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    SET_USER_ROLE(state, role) {
      state.currentUserRole = role;
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      state.currentUserRole = "";
      state.currentDoctor = {};
      state.currentPatient = {};
      axios.defaults.headers.common = {};
    },
    SET_DOCTORS(state, doctors) {
      state.doctors = doctors;
    },
    SET_TIME_SLOTS(state, timeSlots) {
      state.timeSlots = timeSlots;
    },

    UPDATE_DOCTOR_INFO(state, doctorFromOffice) {
      state.doctors.find(doctor => {
        doctor.doctorId == doctorFromOffice.doctorId;
        doctorFromOffice.office.officeId = null;
      })
    },
    SET_CURRENT_DATE(state, currentDate) {
      state.currentDate = currentDate;
    },
    SET_CURRENT_DOCTOR(state, currentDoctor) {
      state.currentDoctor = currentDoctor;
    },
    SET_CURRENT_PATIENT(state, currentPatient) {
      state.currentPatient = currentPatient;
    },
    SET_CURRENT_APPOINTMENT(state, time) {
      state.currentAppointment.timeStart = time;
    },
    SET_APPOINTMENTS(state, appointments) {
      state.appointments = appointments;
    },
    ADD_APPOINTMENT(state, appointment) {
      state.appointments.push(appointment);
    },
    SET_PATIENTS_LIST(state, patients) {
      state.patients = patients;
    },
    SET_ENDING_TIME(state, timeEnd) {
      state.timeEnd = timeEnd;
    }
  }
})
