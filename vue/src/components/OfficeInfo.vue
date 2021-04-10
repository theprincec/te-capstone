<template>
<v-container>
    <v-row>
        <v-col cols="12" md="12">
            <v-card id="docs"
                rounded="lg"
                min-height="100">
                <h1>Dr Info</h1>
            </v-card>
        </v-col>
    </v-row>
    <v-row>
    <!-- </v-row>
        <v-col cols="12"
                md="12" class="px-0 py-3">
            <search-appointment />
        </v-col>
    <v-row> -->
        
        <v-col cols="12"
            md="6" class="pb-0 pt-3">
       <!-- appointments -->
            <appointments-list />
    <!-- availability-form -->
            <!-- <availability-form /> -->

        </v-col>
        <v-col cols="12"
                md="6" class="py-3">
            <v-card class="mx-auto pa-2 mb-5">
                <v-form v-if="!showForm">
                    <v-card
                        class="mx-auto my-5"
                        max-width="480"
                    >
                        <v-img
                        height="250"
                        src="../assets/placeholder.jpg"
                        ></v-img>
                    </v-card>
               
                <v-card-title class="h4 py-2 px-10">{{doctor.office.name}} </v-card-title>
                <v-card-text class="py-2 px-10">
                
                <div class="d-flex justify-space-between subtitle-1"> 
                    <p class="pa-1 ma-0"> {{doctor.office.address.addressLine}} <br>
                        {{doctor.office.address.city}}, {{doctor.office.address.district}} {{doctor.office.address.postalCode}}</p> 
                    <p class="pa-1 ma-0 subtitle-2">
                        <v-icon small class="px-2">mdi-clock</v-icon>
                        {{convertTime(doctor.office.openTime)}} - {{convertTime(doctor.office.closeTime)}} <br>
                         <!-- CHANGE -->
                        <v-icon small class="px-2">mdi-phone</v-icon>{{convertNumber(doctor.office.phoneNumber)}} </p>
                </div>
                </v-card-text>
                    <v-divider></v-divider>
                <v-card-actions class="px-10">
                        <a style="text-decoration: none" class="blue--text" @click="showForm = true" v-if="!showForm"
                        > Edit Office Info</a>
                      
                        <v-spacer></v-spacer>
                        <a style="text-decoration: none" class="blue--text" @click="showDoctorForm = true"
                        > Update Doctors</a>
                        <v-spacer></v-spacer>
                        <p class="mb-0 grey-text display-1"> ${{doctor.office.officeRate}}</p>
                </v-card-actions>
                </v-form>

            <!--Update form-->

                <v-form v-on:submit.prevent="commitOfficeUpdate()" v-if="showForm">
               
                <v-card-title class="h3 py-5 px-10">Update Office Details</v-card-title>
                    <v-text-field class="px-10" label="Office Name" outlined dense v-model="office.name">
                    </v-text-field>
                    <v-text-field class="px-10" label="Address" outlined dense v-model="office.address.addressLine">
                    </v-text-field>
                    <div class="d-flex justify-space-between">
                        <v-text-field class="pl-10 pr-2" label="City" outlined dense v-model="office.address.city">
                        </v-text-field>
                        <v-text-field class="px-2" label="State" outlined dense v-model="office.address.district" >
                        </v-text-field>
                        <v-text-field class="pr-10 pl-2" label="ZipCode" outlined dense v-model="office.address.postalCode">
                        </v-text-field>
                    </div>
                    <v-text-field class=" px-10" label="Phone Number" outlined dense v-model="office.phoneNumber">
                    </v-text-field>
                    <div class="d-flex justify-space-between">
                        <v-text-field class=" pl-10 pr-2" label="Open Time" outlined dense v-model="office.openTime">
                        </v-text-field>
                        <v-text-field class=" pr-10 pl-2" label="Close Time" outlined dense v-model="office.closeTime">
                        </v-text-field>
                    </div>
                    <v-text-field class=" px-10" label="Office Rate" outlined dense v-model="office.officeRate">
                    </v-text-field>
                    
                 <v-divider></v-divider>
                <v-card-actions>
                    <v-btn block type="submit" success="accent"
                    >SUBMIT FORM</v-btn>
                </v-card-actions>

                </v-form>

                <div class="field" v-show="showDoctorForm" >
                    <label for="doctors">Doctors List: </label>
                    <select id="doctorList" name="doctors" v-model="slctDoctor"  @change="selectedDoctor()">
                        <option v-for="doctorFromOffice in doctorsList" 
                        v-bind:key="doctorFromOffice.doctorId" v-bind:value="{id: doctorFromOffice.doctorId}">Dr. {{doctorFromOffice.firstName}} {{doctorFromOffice.lastName}}</option>
                    </select>
                </div>

                <v-divider v-if="showDoctorForm"></v-divider>

                <v-card-actions v-if="showDoctorForm">
                    <v-btn block type="button" success="accent" @click="removeDoctorFromOffice()"
                    >REMOVE DOCTOR</v-btn>
                </v-card-actions>

            </v-card>
        </v-col>
    </v-row>
    

</v-container>
</template>

<script>
import doctorService from '@/services/DoctorService'
import officeService from '@/services/OfficeService'
//import AvailabilityForm from '@/components/AvailabilityForm'
//import SearchAppointment from '@/components/SearchAppointment'
import AppointmentsList from '@/components/AppointmentsList'
//import OfficeCard from '@/components/OfficeCard'

export default {
    name: "office-info",
    components: {
        //AvailabilityForm,
        //SearchAppointment,
        AppointmentsList
    },
    data(){
        return{
            office: {
                officeId: "",
                name:"",
                address: {
                    addressLine: "",
                    city: "",
                    district: "",
                    postalCode: ""
                },
                phoneNumber: "",
                openTime: "",
                closeTime: "",
                officeRate: ""
            }, 
            showForm: false,
            showDoctorForm: false,
            newDoctor: {
                doctorId: "",
                userId: "",
                office: {
                    officeId: "",
                    name: "",
                    address: {
                        addressLine: "",
                        city: "",
                        district: "",
                        postalCode: ""
                    },
                    phoneNumber: "",
                    openTime: "",
                    closeTime: "",
                    officeRate: ""
                    }, 
                firstName: "",
                lastName: ""
            },
            slctDoctor: {
                id: "",
                firstName: "",
                lastName: ""
            }
        }
    },
    
    computed: {
        doctor() {
            return this.$store.state.doctors.find(doctor => {
                return doctor.userId == this.$store.state.user.id;
            })
        },
        doctorsList() {
            return this.$store.state.doctors.filter(doctor => {
                return doctor.office.officeId == this.doctor.office.officeId;
            })
        }
          
    },
    methods: {
        selectedDoctor() {
            this.doctorsList.find( doctor => {
                if(this.slctDoctor.id == doctor.doctorId){
                    this.newDoctor = doctor;
                }
            })
        },
        getOfficeData(doctor){
            this.office.officeId= doctor.office.officeId;
            this.office.name= doctor.office.name;
            this.office.phoneNumber= this.convertNumber(doctor.office.phoneNumber);
            this.office.openTime= this.convertTime(doctor.office.openTime);
            this.office.closeTime= this.convertTime(doctor.office.closeTime);
            this.office.officeRate= doctor.office.officeRate + "$";
            this.office.address.addressLine= doctor.office.address.addressLine;
            this.office.address.city= doctor.office.address.city;
            this.office.address.district= doctor.office.address.district;
            this.office.address.postalCode= doctor.office.address.postalCode;           
        },
        commitOfficeUpdate(){
            this.office.openTime = this.doctor.office.openTime;
            this.office.closeTime = this.doctor.office.closeTime;
            this.office.officeRate = this.doctor.office.officeRate;
            officeService.updateOfficeInfo(this.office).then(response => {
                if(response.status == 200) {
                    this.autoPopulateOfficeInfo();
                }
                this.showForm = false;
            });


        },
        autoPopulateOfficeInfo(){
            doctorService.getDoctors()
                .then(response => {
                    this.$store.commit("SET_DOCTORS", response.data); // commit to store

                    const currentDoctor = this.$store.state.doctors
                        .find(doctor => {return doctor.userId == this.$store.state.user.id;}); //get correct dr
                    this.getOfficeData(currentDoctor); //set office equal data

                }).catch( error => {
                    console.error( error );
            });
        },
        removeDoctorFromOffice() {
            // const currentDoctor = this.doctor;
            doctorService.updateOfficeForDoctor(this.newDoctor)
                .then(response => {
                    if(response.status == 200) {
                         this.$store.commit("UPDATE_DOCTOR_INFO", this.newDoctor);
                         alert("Doctor has been removed")
                    }
                }).catch(error => {
                    console.log(error);
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
        convertNumber(phone) {
            let firstNum = phone.slice(0, 3);
            let secondNum = phone.slice(3, 6);
            let thirdNum = phone.slice(6);
            return "(" + firstNum + ")" + " " + secondNum + "-" + thirdNum;
        }
    },
     created() {
        this.autoPopulateOfficeInfo();
    } 
}


</script>

<style>
#doctorList {
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
/* .office-info {
    background-color: whitesmoke;
    border: 1px solid red;
    margin: 0 auto;
    width: 50%;
    text-align: center;
}
#appointments{
    background-color:aquamarine;
}
#docs{
    background-color:blueviolet;
    width: 100%;
}
form input{
    border: 1pt solid gray;
    border-radius: 2pt;
    box-shadow: 2px 2px #aaaaaaaa;
} */
</style>