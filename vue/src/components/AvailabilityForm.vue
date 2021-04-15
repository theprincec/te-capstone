<template>
  <v-row justify="end">
            <v-dialog v-model="dialog" persistent max-width="600px">
                <template v-slot:activator="{ on, attrs }">
                
                <v-btn fab small color="primary" class="hidden-lg-and-up hidden-sm-only mx-5"
                    v-bind="attrs" v-on="on" >
                        <v-icon  dark>
                            mdi-plus
                        </v-icon>
                </v-btn>
                <v-btn class="hidden-md-only hidden-xs-only"
                        color="primary" dark v-bind="attrs" v-on="on" style="border-radius:16px;">
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

            <v-select
                v-model="appointment.appointmentType"
                :items="items"
                label="Appointment Type"
            ></v-select>
            <v-card-text>
                 <v-autocomplete
                    v-model="appointment.patient.patientId"
                    :items="$store.state.appointments"
                    :filter="customFilter"
                    item-text="patient.lastName"
                    item-value="patient.patientId"
                    label="Patient Name"
                    v-if="isAppointmentReqiured()"
                    required
                    clearable
                ></v-autocomplete>
            </v-card-text>
           
             <div class="field">
                <label for="date">Date: </label>
                <input id="date" name="date" type="date" :min="todayDate" required v-model="appointment.date"/>
            </div>

            <div class="field">
                <label for="startTime" style="color:rgb(118, 118, 118)">Start Time: </label>
                <input id="startTime" name="startTime" type="time" :step="60" required v-model="appointment.timeStart"/>
            </div>

            <div class="field">
                <label for="endTime" style="color:rgb(118, 118, 118)">End Time: </label>
                <input id="endTime" name="endTime" type="time" required v-model="appointment.timeEnd"/>
            </div>

            <v-btn
                form="appointment-form"
                class="mr-4"
                type="submit"
                @click="toggleDialog"                
            >
            submit
            </v-btn>
            <v-btn @click="clearForm, dialog=false">
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
            ],
        }
    }, 
    methods: {
        addAnAppointment() {
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
            const textOne = item.patient.firstName.toLowerCase()
            const textTwo = item.patient.lastName.toLowerCase()
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
                patient: {},
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
            (this.appointment.date == "" || this.appointment.timeStart == "" || this.appointment.timeEnd == "") 
                    ? this.dialog = true : this.dialog = false;
        }, 
        getUpdatedAppointments() {
            appointmentService.getAppointments().then(response => {
                if(response.status == 200) {
                    this.$store.commit("SET_APPOINTMENTS", response.data);
                }
            })
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
</style>