<template>
<v-container>
    <v-row>
        <v-col cols="12" md="12">
            <v-card id="docs"
                rounded="lg"
                min-height="100"
                flat>

                <v-row>
                    <v-col col="12" md="2">
                        <v-card class="ml-10" max-height="110">
            <!-- DOCTOR IMAGE -->

                            <doctor-image></doctor-image>
                        </v-card>
                    </v-col>
                     <v-col col="12" md="4" >
                         <v-card-title class="headline pb-0">Welcome to Homepage</v-card-title>
                        <v-card-title class="text-h4 pt-1  ">Dr. {{$store.state.currentDoctor.firstName}} {{$store.state.currentDoctor.lastName}}</v-card-title>
  
                     </v-col>
                </v-row>
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
            <appointments-list
             />
    <!-- availability-form -->
            <!-- <availability-form /> -->

        </v-col>
        <v-col cols="12"
                md="6" class="py-3">
            
            <v-card class="mx-auto pa-2 mb-5" v-if="$store.state.currentDoctor.office != null">
                <v-form v-if="!showForm">
                    <v-card
                        class="mx-auto my-5"
                        max-width="480"
                    >
        <!-- IMAGE UPLOAD -->
                        <v-img
                        max-height="250"
                        v-if="!fileUrl"
                        src="../assets/placeholder.jpg"
                        ></v-img>
                        <v-img
                        max-height="250"
                        contain v-if="fileUrl"
                        :src="fileUrl"
                        alt="Office Image"
                        ></v-img>
                    </v-card>

                     <template v-slot:append-outer>
                        <v-progress-circular
                        v-if="processing"
                        color="grey"
                        indeterminate
                        small
                        />
                    </template>
                            <!-- FILE INPUT -->
                    <v-file-input
                        v-model="myFile"
                        accept="image/png, image/jpeg"
                        placeholder="Click to upload file"
                        @change="fileInput"
                        :disabled="processing"
                        class="px-10"
                        dense
                        small-chips
                        label="Update office image"
                        v-if="showEditImage"
                        prepend-icon="mdi-camera"
    
                    >
                    </v-file-input>
                   
                      <div class="text-center">
                        <v-btn
                            color="blue-grey"
                            class="ma-2 white--text"
                            @click="showEditImage=!showEditImage"                   
                            >
                            Edit Office Image
                        <v-icon
                            right
                            dark
                        >
                            mdi-cloud-upload
                        </v-icon>
                    </v-btn>
                      </div>
               
                <v-card-title class="h4 py-2 px-10">{{this.office.name}} </v-card-title>
                <v-card-text class="py-2 px-10">
                
                <div class="d-flex justify-space-between subtitle-1"> 
                    <p class="pa-1 ma-0"> {{this.office.address.addressLine}} <br>
                        {{this.office.address.city}}, {{this.office.address.district}} {{this.office.address.postalCode}}</p> 
                    <p class="pa-1 ma-0 subtitle-2">
                        <v-icon small class="px-2">mdi-clock</v-icon>
                        {{this.office.openTime}} - {{this.office.closeTime}} <br>
             <!-- CHANGE -->
                        <v-icon small class="px-2">mdi-phone</v-icon>{{convertNumber(this.office.phoneNumber)}} </p>
                </div>
                </v-card-text>
                    <v-divider></v-divider>
                <v-card-actions class="px-10">
                        <a style="text-decoration: none" class="blue--text" @click="showForm = true" v-if="!showForm"
                        > Edit Office Info</a>
                      
                        <v-spacer></v-spacer>
                        <a style="text-decoration: none" class="blue--text" @click="showDoctorForm = true"
                        v-if="!showDoctorForm"> Update Doctors</a>
                        <a style="text-decoration: none" class="blue--text" @click="showDoctorForm = false"
                         v-if="showDoctorForm"> Cancel </a>
                        <v-spacer></v-spacer>
                        <p class="mb-0 grey-text display-1"> ${{office.officeRate}}</p>
                </v-card-actions>
                </v-form>

            <!--Update form-->

                <v-form v-on:submit.prevent="commitOfficeUpdate()" v-if="showForm">
               
                <v-card-title class="h3 py-5 px-10">Update Office Details</v-card-title>
                    <v-text-field class="px-10" label="Office Name"  dense v-model="office.name">
                    </v-text-field>
        
                    <v-text-field class="px-10" label="Address" dense v-model="office.address.addressLine">
                    </v-text-field>
                    <div class="d-flex justify-space-between">
                        <v-text-field class="pl-10 pr-2" label="City" dense v-model="office.address.city">
                        </v-text-field>
                        <v-text-field class="px-2" label="State" dense v-model="office.address.district" >
                        </v-text-field>
                        <v-text-field class="pr-10 pl-2" label="ZipCode" dense v-model="office.address.postalCode">
                        </v-text-field>
                    </div>
                    <v-text-field type="tel" class=" px-10" label="Phone Number" dense v-model="office.phoneNumber">
                    </v-text-field>
                    <div class="d-flex justify-space-between">
                        <v-text-field  type="time" class=" pl-10 pr-2" label="Open Time" dense v-model="office.openTime">
                        </v-text-field>
                        <v-text-field  type="time" class=" pr-10 pl-2" label="Close Time" dense v-model="office.closeTime">
                        </v-text-field>
                        <v-text-field class=" px-10" label="Office Rate Per Hour" dense v-model="office.officeRate">
                    </v-text-field>
                    </div>
                    <!-- <v-text-field class=" px-10" label="Office Rate Per Hour" dense v-model="this.office.officeRate">
                    </v-text-field> -->
                    
                 <v-divider></v-divider>
                <v-card-actions>
                    <v-btn class="mx-7" type="submit" success="accent"
                    >SUBMIT FORM</v-btn>
                        <v-spacer></v-spacer>
                     <v-btn class="mx-7" @click="showForm=false" success="accent"
                    >Cancel</v-btn>
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
            <div class="mx-auto pa-2 mb-5" v-if="$store.state.currentDoctor.office == null">
                <v-alert text
                    dense
                    color="blue"
                    icon="mdi-office-building-marker-outline"
                    border="left"
                    class="ma-4"
                >You have not yet been assigned to an office.<br>
                    Please, contact administration. </v-alert>
            </div>
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
import DoctorImage from '@/components/DoctorImage'
import firebase from 'firebase/app'

export default {
    name: "office-info",
    components: {
        //AvailabilityForm,
        //SearchAppointment,
        AppointmentsList, 
       DoctorImage
    },
    data(){
        return{
            showEditImage: false,
            processing: false,
            myFile: null,
            fileUrl: null,
           // fileDoctorUrl: null,
            
            // loader: null,
            // loading: false,
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
                officeRate: "",
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
    // watch: {
    // loader () {
    //     const l = this.loader
    //     this[l] = !this[l]

    //     setTimeout(() => (this[l] = false), 3000)

    //     this.loader = null
    //   }
    // },
    watch: {
        doctor: function(newDoctor) {
            this.updateOfficeImage(newDoctor);
        }
    },
    
    computed: {
        doctor() {
            return this.$store.state.currentDoctor;
        },
        doctorsList() {
            return this.$store.state.doctors.filter(doctor => {
                return doctor.office.officeId == this.doctor.office.officeId;
            })
        }
          
    },
    methods: {
        updateOfficeImage(newDoctor) {
            const officeIdFromStore = newDoctor.office.officeId;
            firebase.firestore().collection("offices").doc(`${officeIdFromStore}`)
            .get()
            .then((doc) => {
                if(doc.exists) {
                    console.log(doc.id, " => ", doc.data());
                    this.fileUrl = doc.data().link;
                } else {
                    console.log(doc.data().timestamp)
                }   
            })
            .catch((error) => {
                console.log("Error getting documents: ", error);
            })
        },
        
        selectedDoctor() {
            this.doctorsList.find( doctor => {
                if(this.slctDoctor.id == doctor.doctorId){
                    this.newDoctor = doctor;
                }
            })
        },
        getOfficeData(doctor){
            this.office.officeId = doctor.office.officeId;
            this.office.name= doctor.office.name;
            this.office.phoneNumber= doctor.office.phoneNumber;
            this.office.openTime= this.convertTime(doctor.office.openTime);
            this.office.closeTime= this.convertTime(doctor.office.closeTime);
            this.office.officeRate= doctor.office.officeRate;
            this.office.address.addressLine= doctor.office.address.addressLine;
            this.office.address.city= doctor.office.address.city;
            this.office.address.district= doctor.office.address.district;
            this.office.address.postalCode= doctor.office.address.postalCode;           
        },
        commitOfficeUpdate(){
            // this.office.phoneNumber = this.doctor.office.phoneNumber;
            // this.office.openTime = this.doctor.office.openTime;
            // this.office.closeTime = this.doctor.office.closeTime;
            // this.office.officeRate = this.doctor.office.officeRate;
            officeService.updateOfficeInfo(this.office).then(response => {
                if(response.status == 200) {
                    alert("Form has been succesfully updated")
                    this.autoPopulateOfficeInfo();
                }
                this.showForm = false;
            }).catch(e => {
                console.log(e)
            })
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
        },
       
        async fileInput(file) {

        try {
          if (file && file.name) {
            this.processing = true;
// LOADING IMAGE TO THE PAGE
            const fr = new FileReader();
            fr.readAsDataURL(file);
            fr.addEventListener("load", () => {
    //   this is to load image on the UI
    //   .. not related to file upload
              this.fileUrl = fr.result;
            });
//    CREATE METADATA FOR FIREBASE
            const imgData = new FormData();
            imgData.append("image", this.myFile);
            const filePath = `offices/${this.doctor.doctorId}-${Date.now()}-${file.name}`;
            // const metadata = { contentType: this.myFile.type };
// UPLOADING AND GETTING URL FROM FIREBASE
            const uploadTask = firebase.storage().ref()
              .child(filePath)
              .put(this.myFile)
              .then((snapshot) => {
               return snapshot.ref.getDownloadURL()   
              })
              .then(url => {
//TEST console.log("File at:" + url);
                 this.fileUrl = url; //this should save fileUrl to url from firebase
              })
              .catch(e => {console.log(e)});
//WAITING ON WHEN UPLOAD IS FINISHED
            await uploadTask;

                let office = {
                    timestamp: firebase.firestore.FieldValue.serverTimestamp(),
                    link: this.fileUrl
                }
                const newId = this.$store.state.currentDoctor.office.officeId;
    
// ADDING COLLECTION TO FIRESTORE BY OFFICE ID
// UPDATE IF OFFICEID ALREADY EXISTS
// SET IF DOES NOT
                const docRef = firebase.firestore().collection("offices").doc(`${newId}`);
                docRef.get().then((doc) => {
                    if (doc.exists) {
                        docRef.update(office)
                    .then(() => {  
                        console.log("updated")   
                        this.showEditImage = false;   
                    }).catch((e) => {
                        console.log(e)
                    })
                    } else {
                        docRef.set(office)
                        .then(() => {
//TEST console.log("set")
                            this.showEditImage = false;   
                        }).catch((e) => {
                            console.log(e)
                        })
                    }
                }).catch(e => {
                    console.log(e)
                })
           
// TEST   console.log("filePath: ", filePath);
          }
        } catch (e) {
          console.error(e);
        } finally {
          this.processing = false;
        }
       this.getUpdateImage
     },
     getUpdateImage() {
         const id = this.$store.state.user.id;
            firebase.firestore().collection("offices").doc(`${id}`)
                .get()
                .then((doc) => {
                    if(doc.exists) {
                        console.log(doc.id, " => ", doc.data());
                        this.fileUrl = doc.data().link;
                    } else {
                        console.log(doc.data().timestamp)
                    }   
                })
            .catch((error) => {
                console.log("Error getting documents: ", error);
            })
     }
     
    },
     created() {
        this.autoPopulateOfficeInfo();
//ACCESS COLLECTION FROM FIRESTORE
        // const id = this.$store.state.user.id;
        // if(id == this.$store.state.currentDoctor.userId) {
            
        // }
        
//  //ACCESS COLLECTION FROM FIRESTORE
//         const docId = this.$store.state.currentDoctor.doctorId;
//          firebase.firestore().collection("doctors").doc(`${docId}`)
//             .get()
//             .then((doc) => {
//                 if(doc.exists) {
//                     console.log(doc.id, " => ", doc.data());
//                     this.fileDoctorUrl = doc.data().link;
//                 } else {
//                     console.log(doc.data().timestamp)
//                 }   
//             })
//             .catch((error) => {
//                 console.log("Error getting documents: ", error);
//             })
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
.custom-loader {
    animation: loader 1s infinite;
    display: flex;
  }
  @-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
</style>