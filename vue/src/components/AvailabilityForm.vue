<template>
  <v-row justify="end">
            <v-dialog
                v-model="dialog"
                persistent
                max-width="600px"
            >
                <template v-slot:activator="{ on, attrs }">
                
                <v-btn fab small  class=" hidden-lg-and-up hidden-sm-only mx-5 white--text"
                id="button"
                    v-bind="attrs"
                        v-on="on"
                >
                        <v-icon  dark>
                            mdi-plus
                        </v-icon>
                 </v-btn>
                     <v-btn class=" hidden-md-only hidden-xs-only  white--text"
                        id="button"
                        v-bind="attrs"
                        v-on="on"
                    >
                    Add appointment
                </v-btn>
            </template>
    <v-card id="appointments"
            min-height="368" class="mt-5"
            flat>

            <v-card-title>
                <span class="headline">Update Availability</span>
            </v-card-title>

        <form id="appointment-form" class="px-9 pb-9"  v-on:submit.prevent="addAnAppointment()">

            <v-select class="px-6"
                v-model="appointment.appointmentType"
                :items="items"
                label="Appointment Type"
            ></v-select>
            <v-card-text class="py-0 px-6">
                 <v-autocomplete 
                    v-model="appointment.patient.patientId"
                    :items="$store.state.patients"
                    :filter="customFilter"
                    item-text="lastName"
                    item-value="patientId"
                    label="Patient Name"
                    v-if="isAppointmentReqiured()"
                    required
                    clearable
                ></v-autocomplete>
            </v-card-text>
           
             <div class="field pt-0">
                <label for="date">Date: </label>
                <input id="date" name="date" type="date" :min="todayDate" required @change="setDate()" v-model="appointment.date"/>
            </div>

            <div class="field">
                <label  for="startTime">Start time:</label>
                <select id="startTime" name="startTime" v-model="appointment.timeStart" @change="time">
                    <!-- <option v-for="time in timeSlots" v-bind:key="time">{{time}}</option> -->
                    <option v-for="(time, index) in $store.state.timeSlots"
                        v-bind:key="`time-${index}`"
                        :value="time"
                        required
                    >{{convertTime(time)}}
                    </option>
                </select>
            </div>

            <!-- <div class="field">
                <label for="startTime" >Start Time: </label>
                <input id="startTime" name="startTime" type="time" :step="60" required v-model="appointment.timeStart"/>
            </div> -->

            <div class="field">
                <label for="endTime" >End Time: </label>
                <select id="endTime" name="endTime" v-model="appointment.timeEnd" @change="time">
                    <option v-for="time in $store.state.timeSlots"
                        v-bind:key="time.key" 
                        :value="time"
                        required
                    >{{convertTimeEnd(time)}}
                    </option>

                <!-- <input id="endTime" name="endTime" type="time" required v-model="appointment.timeEnd"/> -->
                </select>
            </div>

            <v-btn
                form="appointment-form"
                class="mr-4"
                type="submit"
                @click="toggleDialog"               
            >
            submit
            </v-btn>
            <v-btn @click="clearForm, dialog=false"
                >
            cancel
            </v-btn>
        </form>

    </v-card>
     </v-dialog>
  </v-row>

</template>

<script>
import appointmentService from '@/services/AppointmentService';
import patientService from '@/services/PatientService';

export default {
    name: "availability-form", 
    data() {
        return {
     
            dialog: false,
            appointment: {
               
                patient: {
                    patientId: "",
                    firstName: "",
                    lastName: ""
                },            
                date: "", 
                timeStart: "", 
                timeEnd: "", 
                appointmentType: "Personal"
            },
            items: [
                'Personal',
                'Appoinment'
            ]
        }
    }, 
    methods: {
        addAnAppointment() {
            this.appointment.timeEnd = this.calculateTimeEnd(this.appointment.timeEnd);
            appointmentService.addAppointment(this.appointment).then(response => {
                if(response.status == 201) {
                    alert("Appointment successfully saved");
                    //UPDATE APPOINTMENTS LIST IN OUR STORE
                    //ADD POST FOR PAITIENT BY ID
                    //this.$store.commit("ADD_APPOINTMENT", this.appointment);
                    this.getUpdatedAppointments();
                    this.getPatientById()
                        .then(response => {
                            if(response.status == 200) {
                                this.$store.commit("ADD_APPOINTMENT", this.appointment);
                                this.getUpdatedAppointments();
                            }

                    });
                // this.getUpdatedAppointments();
                    

//                     this.getPatientById();
                }
            })
            .catch(error => {
                console.log(error);
            })
        }, 
        customFilter (item, queryText) {
            const textOne = item.firstName.toLowerCase()
            const textTwo = item.lastName.toLowerCase()
            const searchText = queryText.toLowerCase()

            return textOne.indexOf(searchText) > -1 ||
            textTwo.indexOf(searchText) > -1
        },
        getPatientById() {
            patientService.getPatient(this.appointment.patient.patientId)
                .then(response => {
                    const newPatient = response.data;
                    this.appointment.patient.firstName = newPatient.firstName;
                    this.appointment.patient.lastName = newPatient.lastName;
                    //this.getUpdatedAppointments();

                    //this.$store.commit("ADD_APPOINTMENT", this.appointment);
                    this.clearForm();

                })
                .catch(error => {
                    console.log(error);
                })

        },
        clearForm() {
            this.appointment =  {
                patient: {
                    firstName:"",
                    lastName: "",
                    patientId: ""
                },
                date: "", 
                timeStart: "", 
                timeEnd: "", 
                appointmentType: "Personal"
            }
        },
        isAppointmentReqiured() {
            return this.appointment.appointmentType == 'Personal' ? false : true;
        },
        toggleDialog() {
            if(this.appointment.date != "" && this.appointment.timeStart != ""
                && this.appointment.timeEnd != "") {
                    this.dialog = !this.dialog;
                }
           
        }, 
        getUpdatedAppointments() {
            appointmentService.getAppointments().then(response => {
                if(response.status == 200) {
                    this.$store.commit("SET_APPOINTMENTS", response.data);
                }
            })
        },
        setDate() {
           this.$store.commit("SET_CURRENT_DATE", this.appointment.date);
           this.getTimeSlots();
            
        },
        setCurrentTime(time) {
            //this.appointment.timeStart = this.$store.state.timeSlots[index];
            this.$store.commit("SET_CURRENT_APPOINTMENT", time)
        },
        getTimeSlots(){
            appointmentService.viewTimeSlots(this.$store.state.currentDoctor.doctorId, this.$store.state.currentDate)
                .then(response => {
                    if (response.status == 200) {
                        this.$store.commit("SET_TIME_SLOTS", response.data);
                    }
                } )
                .catch( error =>{
                    console.error(error);
                })
        },
         convertTime(time) { // 18:00:00
            let convertedTime = time.slice(0, 5); // 18:00
            let result;
            if (convertedTime.length > 1) { // If time format correct
                convertedTime = convertedTime.split (":");  // Remove full string match value - 18 00
                let timeUnder = (convertedTime[0] - 12 >= 0 || convertedTime[0] == 12) ? 'PM' : 'AM'; // Set AM/PM
                let hours = convertedTime[0] > 12 ? convertedTime[0] - 12 : convertedTime[0]; // Adjust hours
                let minutes = convertedTime[1];
                result = hours + ":" + minutes + " "+ timeUnder;
            }
            return result;
        },
         convertTimeEnd(time) { // 18:00:00
            let convertedTime = time.slice(0, 5); // 18:00
            let result;
            if (convertedTime.length > 1) { // If time format correct
                convertedTime = convertedTime.split (":");  // Remove full string match value - 18 00
                let timeUnder = (convertedTime[0] - 12 >= 0 || convertedTime[0] == 12) ? 'PM' : 'AM'; // Set AM/PM
                let hours = convertedTime[0] > 12 ? convertedTime[0] - 12 : convertedTime[0]; // Adjust hours
                let minutes = convertedTime[1];
                if (hours == 12) {
                    hours = 1;
                } else {
                    hours = parseInt(hours) + 1;
                }
                
                result = hours + ":" + minutes + " "+ timeUnder;
            }
            return result;
        },
        calculateTimeEnd(time) {
            //09:00:00
            let hours = parseInt(time.slice(0, 2));
            let minAndSec = time.slice(2)
           
                hours += 1;
     
            let fullTime = hours + minAndSec;
            return fullTime;
        }
    

    },

    created() {
        appointmentService.getAppointments().then(response => {
            if(response.status == 200) {
                this.$store.commit("SET_APPOINTMENTS", response.data);
            }
        })
        .catch(error => {
            console.log(error);
        })
    },
    computed: {
        todayDate() {
            return new Date().toISOString().split('T')[0];
        }
        // timeSlotGetter(){
        //     return this.getTimeSlots(); 
        // }  
    }
}

</script>

<style>
#startTime, #endTime, #date {
    padding: 8px 0 8px 8px;
    line-height: 20px;
    border-bottom: 1px solid rgb(118, 118, 118);
    width: 100%;
    border-color: (rgb(118, 118, 118), rgb(133, 133, 133));
    color: rgb(133, 133, 133);
    max-height: 32px;
}
.field {
    padding: 8px 0 8px 
}


#button {
  background-color:#f4931c
}
#button:hover, #button:active {
  background-color:#f45d1c
}
</style>