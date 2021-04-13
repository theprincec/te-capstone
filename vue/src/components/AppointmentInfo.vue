<template>
<v-container ><v-row>
        <v-col cols="12" md="12">
            <v-card id="docs"
                rounded="lg"
                min-height="100"
                flat>

                <v-row>
                    <v-col col="12" md="2">
                        <v-card class="ml-10" max-height="110">
            <!-- DOCTOR IMAGE -->

                            <v-img
                                class="hidden-xs-and-down"
                                max-height="100"
                                v-if="!fileDoctorUrl"
                                src="../assets/placeholder.jpg"
                                ></v-img>
                                <v-img
                                max-height="100"
                                contain v-if="fileDoctorUrl"
                                :src="fileDoctorUrl"
                                alt="Doctor Image"
                            ></v-img>
                        </v-card>
                    </v-col>
                     <v-col col="12" md="4" >
                        <v-card-title class="text-h4 pt-1  ">Dr. {{$store.state.currentDoctor.firstName}} {{$store.state.currentDoctor.lastName}}</v-card-title>
                     </v-col>
                </v-row>
            </v-card>
        </v-col>
    </v-row>
    <!-- <v-row>
        <v-col cols="12" md="12">
            <v-card id="docs"
                rounded="lg"
                min-height="100"
                flat>
                <v-row >
                    <v-col col="12" md="2">
                        <v-card class="ml-10" max-height="110" max-width="120">
                            <v-img class="hidden-sm-and-down"
                                height="100"
                                src="src/assets/img.png"
                            ></v-img>
                        </v-card>
                    </v-col>
                     <v-col col="12" md="12">
                         <v-card class="ml-10" max-height="110" max-width="120" id="doctor-image-container">
                            <img 
                                height="100px"
                                src="assets/img.png"
                            >
                        </v-card>
                        <v-card-title id="doctor-title" class="text-h4 pt-10  ">Dr {{$store.state.currentDoctor.firstName}} {{$store.state.currentDoctor.lastName}}</v-card-title>
  
                     </v-col>
                </v-row>

            </v-card>
        </v-col> -->
    <!-- </v-row> -->
    <v-row justify="space-around">
        
        
        <v-col cols="12"
            md="5" class="pb-0 pt-3">
       <!-- appointments -->
            <!-- <appointments-list /> -->
            <!-- <email/> -->
            <time-slot-card/>
    <!-- availability-form -->
            <!-- <availability-form /> -->

        </v-col>
        <v-col cols="12"
                md="5" class="py-3">
            <v-card class="mx-auto pa-2 mb-5">
                <v-form >
                    <v-card
                        class="mx-auto my-5"
                        max-width="480"
                    >

    <!-- OFFICE IMAGE UPDATE FROM DOCTOR -->

                        <v-img
                            max-height="250"
                            contain v-if="fileOfficeUrl"
                            :src="fileOfficeUrl"
                            alt="Office Image"
                        ></v-img>
                        <v-img
                            v-if="!fileOfficeUrl"
                            max-height="250"
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
                        
                        <v-spacer></v-spacer>
                        <p class="mb-0 grey-text display-1"> ${{doctor.office.officeRate}}</p>
                </v-card-actions>
                </v-form>


            </v-card>
        </v-col>
       
    </v-row>
    

</v-container>
</template>

<script>
// import doctorService from '@/services/DoctorService'
// import officeService from '@/services/OfficeService'
//import AvailabilityForm from '@/components/AvailabilityForm'
// import AppointmentsList from '@/components/AppointmentsList'
//import OfficeCard from '@/components/OfficeCard'
import TimeSlotCard from '@/components/TimeSlotCard'
import firebase from 'firebase/app'
//import email from '@/components/Email'



export default {
    name: "appointment-info",
    components: {
        //AvailabilityForm,

        // AppointmentsList,
        TimeSlotCard
    },
    data(){
   
        return{
            fileOfficeUrl: null,
            fileDoctorUrl: null
            // office: {
            //     officeId: "",
            //     name:"",
            //     address: {
            //         addressLine: "",
            //         city: "",
            //         district: "",
            //         postalCode: ""
            //     },
            //     phoneNumber: "",
            //     openTime: "",
            //     closeTime: "",
            //     officeRate: ""
            // }, 
            // showForm: false,
            // showDoctorForm: false,
            // newDoctor: {
            //     doctorId: "",
            //     userId: "",
            //     office: {
            //         officeId: "",
            //         name: "",
            //         address: {
            //             addressLine: "",
            //             city: "",
            //             district: "",
            //             postalCode: ""
            //         },
            //         phoneNumber: "",
            //         openTime: "",
            //         closeTime: "",
            //         officeRate: ""
            //         }, 
            //     firstName: "",
            //     lastName: ""
            // },
            // slctDoctor: {
            //     id: "",
            //     firstName: "",
            //     lastName: ""
            // }
        }
    },
    
    computed: {
        doctor() {
            return this.$store.state.currentDoctor;
        }
        // doctorsList() {
        //     return this.$store.state.doctors.filter(doctor => {
        //         return doctor.office.officeId == this.doctor.office.officeId;
        //     })
        // }
          
    },
    methods: {
        // selectedDoctor() {
        //     this.doctorsList.find( doctor => {
        //         if(this.slctDoctor.id == doctor.doctorId){
        //             this.newDoctor = doctor;
        //         }
        //     })
        // },
        // getOfficeData(doctor){
        //     this.office.officeId= doctor.office.officeId;
        //     this.office.name= doctor.office.name;
        //     this.office.phoneNumber= doctor.office.phoneNumber;
        //     this.office.openTime= doctor.office.openTime;
        //     this.office.closeTime= doctor.office.closeTime;
        //     this.office.officeRate= doctor.office.officeRate;
        //     this.office.address.addressLine= doctor.office.address.addressLine;
        //     this.office.address.city= doctor.office.address.city;
        //     this.office.address.district= doctor.office.address.district;
        //     this.office.address.postalCode= doctor.office.address.postalCode;           
        // },
        // commitOfficeUpdate(){
        //     officeService.updateOfficeInfo(this.office).then(response => {

        //         if(response.status == 200) {
        //             this.autoPopulateOfficeInfo();
        //         }
        //         this.showForm = false;
        //     });


        // }
        // autoPopulateOfficeInfo(){
        //     doctorService.getDoctors()
        //         .then(response => {
        //             this.$store.commit("SET_DOCTORS", response.data); // commit to store

        //             // const currentDoctor = this.$store.state.doctors
        //             //     .find(doctor => {return doctor.userId == this.$store.state.user.id;}); //get correct dr
        //             this.getOfficeData(this.doctor); //set office equal data

        //         }).catch( error => {
        //             console.error( error );
        //     });
        // },
        // removeDoctorFromOffice() {
        //     // const currentDoctor = this.doctor;
        //     doctorService.updateOfficeForDoctor(this.newDoctor)
        //         .then(response => {
        //             if(response.status == 200) {
        //                  this.$store.commit("UPDATE_DOCTOR_INFO", this.newDoctor);
        //                  alert("Doctor has been removed")
        //             }
        //         }).catch(error => {
        //             console.log(error);
        //         })
        // },
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
        // this.autoPopulateOfficeInfo();

//ACCESS COLLECTION FROM FIRESTORE
        const doctrId = this.$store.state.currentDoctor.doctorId;
         firebase.firestore().collection("doctors").doc(`${doctrId}`)
            .get()
            .then((doc) => {
                if(doc.exists) {
                    console.log(doc.id, " => ", doc.data());
                    this.fileDoctorUrl = doc.data().link;
                } else {
                    console.log("Error")
                }   
            })
            .catch((error) => {
                console.log("Error getting documents: ", error);
            })
//ACCESS COLLECTION FROM FIRESTORE
        const id = this.$store.state.currentDoctor.office.officeId;
         firebase.firestore().collection("offices").doc(`${id}`)
            .get()
            .then((doc) => {
                if(doc.exists) {
                    console.log(doc.id, " => ", doc.data());
                    this.fileOfficeUrl = doc.data().link;
                } else {
                    console.log(doc.data().timestamp)
                }   
            })
            .catch((error) => {
                console.log("Error getting documents: ", error);
            })
        
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
#doctor-image-container, #doctor-title{
    display: inline-block;
}
#doctor-image-container{
    margin-right: 20px;
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