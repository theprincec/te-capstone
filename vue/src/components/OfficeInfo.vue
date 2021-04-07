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
    </v-row>
        <v-col cols="12"
                md="12" class="px-0 py-3">
            <search-appointment />
        </v-col>
    <v-row>
        
        <v-col cols="12"
            md="4" class="pb-0 pt-3">
            <availability-form />
        </v-col>
        <v-col cols="12"
                md="4" class="py-3">
            <v-card class="mx-auto pa-2 mb-5">
       
                <v-card-title class="h4"></v-card-title>
                <v-card-text>
                
                <div v-if="!showForm">
                    <!-- <div 
                //class="d-flex justify-space-between subtitle-1"
                > -->
                        <h2 class="ma-1">Office name:  {{doctor.office.name}} </h2> <br>
                        <p class="ma-1"> Address: <br> {{doctor.office.address.addressLine}} </p> <br>
                        <p class="ma-1">Location: <br> {{doctor.office.address.city}}, {{doctor.office.address.district}} {{doctor.office.address.postalCode}}</p> <br>
                        <p class="ma-1"> Phone Number: <br> {{doctor.office.phoneNumber}} </p> <br>
                        <p class="ma-1">Hours: <br> {{doctor.office.openTime}} - {{doctor.office.closeTime}} </p>
                        <p class="ma-1">Hourly Rate: <br> {{doctor.office.officeRate}}</p>


                    </div>
                    <v-row
                    align="center"
                    class="mx-0"
                >
                <!-- change hardcoded value to rating -->
                </v-row>
                </v-card-text>

                <v-card-actions>
                    <a style="text-decoration: none" href=# class="blue--text" @click="showForm = true" v-if="!showForm"> Edit Office Info</a>
                </v-card-actions>
                
                <form v-on:submit.prevent="commitOfficeUpdate()" v-if="showForm">
                    <div class="field">
                    <label for="officeName">Office Name: </label>
                    <input name="officeName" type="text"  v-model="office.name"/>
                    </div>
                    <div class="field">
                    <label for="address"> Address line </label>
                    <input name="officeName" type="text" v-model="office.address.addressLine" />
                    </div>
                    
                    <div class="field">
                    <label for="addressLine"> City </label>
                    <input name="addressLine" type="text" v-model="office.address.city" />
                    </div>

                    <div class="field">
                    <label for="district"> District</label>
                    <input name="district" type="text" v-model="office.address.district" />
                    </div>

                    <div class="field">
                    <label for="postalCode"> Postal Code</label>
                    <input name="postalCode" type="text" v-model="office.address.postalCode" />
                    </div>

                    <div class="field">
                    <label for="phonenNumber"> Phone Number:</label>
                    <input name="phoneNumber" type="text" v-model="office.phoneNumber" />
                    </div>
                    <div class="field">
                    <label for="openTime"> Open-time</label>
                    <input name="openTime" type="text" v-model="office.openTime" />
                    </div>

                    <div class="field">
                    <label for="closeTime"> Close-time</label>
                    <input name="closeTime" type="text" v-model="office.closeTime" value=""/>
                    </div>
                    
                    <div class="field">
                    <label for="officeRate"> Office Rate:</label>
                    <input name="officeRate" type="text" v-model="office.officeRate"  />
                    </div>
                    <!-- <div class="field">
                    <label for="officeName"> Address:</label>
                    <input name="officeName" type="text" v-model="title" />
                    </div> -->
                    <div class="actions" style="color:green">
                    <button type="submit">Update Message</button>
                    </div>
                </form>


            </v-card>
        </v-col>
    </v-row>

</v-container>
</template>

<script>
import doctorService from '@/services/DoctorService'
import officeService from '@/services/OfficeService'
import AvailabilityForm from '@/components/AvailabilityForm'
import SearchAppointment from '@/components/SearchAppointment'
//import OfficeCard from '@/components/OfficeCard'

export default {
    name: "office-info",
    components: {
        AvailabilityForm,
        SearchAppointment
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
            showForm: false
        }
    },
    
    computed: {
        doctor() {
            return this.$store.state.doctors.find(doctor => {
                return doctor.userId == this.$store.state.user.id;
            })
        }
    },
    methods: {
        getOfficeData(doctor){
            this.office.officeId= doctor.office.officeId;
            this.office.name= doctor.office.name;
            this.office.phoneNumber= doctor.office.phoneNumber;
            this.office.openTime= doctor.office.openTime;
            this.office.closeTime= doctor.office.closeTime;
            this.office.officeRate= doctor.office.officeRate;
            this.office.address.addressLine= doctor.office.address.addressLine;
            this.office.address.city= doctor.office.address.city;
            this.office.address.district= doctor.office.address.district;
            this.office.address.postalCode= doctor.office.address.postalCode;           
        },
        commitOfficeUpdate(){
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
        }
    },
     created() {
        this.autoPopulateOfficeInfo();
    } 
}


</script>

<style>
.office-info {
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
}
</style>