<template>
<div id="app">
  <v-app>
    <v-container>
    <v-row>
        <v-col cols="12">
            <v-card class="pb-10px">
                <v-row>
                    <img style="max-height: 100px; margin-left: 50px" src="@/assets/carehub.png" alt="">
                    <h2 style="margin-left: 50px; margin-top: 40px; color: grey">Office Assignments and Updates</h2>
                </v-row>
            </v-card>
        </v-col>
    </v-row>
      <v-row>
        <!-- <v-col cols="2">
           
        </v-col> -->


        <!-- unassigned -->
        <v-col id="dragging-container" cols="12" >
        <v-card class="pb-10px dragger" id="dragger" >
            <v-col cols="5" id="col-1">
                <div  v-for="item in offices" v-bind:key="item.office.officeId" 
                        @drop="onDrop($event, item.office)"
                        @dragover.prevent
                        @dragenter.prevent>
                        
                <v-card v-if="item.office.name == 'Unassigned Doctors'" class="pb-5 mb-5 drag-name">

                    <div id=dropZoneTitle>

                        <v-card-text class="text-center text-lg-h6 font-weight-bold font-italic">
                        {{item.office.name}}
                        </v-card-text>
                    </div>
                    <v-card-text v-if="item.doctors.length == 0" class="text-center"> There are no unassigned doctors </v-card-text>

                    <draggable v-model="item.doctors" group="offices">
                        <div v-for="doctor in item.doctors" :key="doctor.doctorId" style="font-weight:bold;"  
                                @dragstart='startDrag($event, doctor)'  id="dragging-div">
                        <!-- <v-hover v-slot="{ hover }"> -->
                        <v-card class="ml-10 mr-10 mb-5 doctorCards" :elevation="hover ? 8 : 2" color="#Fddc96" id="doctor-in-office">
                            <v-card-text class="text-center text-md-body-1 font-weight-bold">
                            Dr. {{doctor.firstName}} {{doctor.lastName}}
                            </v-card-text>

                        </v-card>
                        <!-- </v-hover> -->
                        </div>
                    </draggable>
                    </v-card>
                </div>
            </v-col>

            <!-- arrows -->
            <v-col cols="1" id="col-2"> 
                <div id="filler" >
                    <v-row>
                <v-icon x-large>mdi-arrow-left-bold</v-icon> <v-icon x-large> mdi-arrow-right-bold</v-icon>
                </v-row>
                </div>
            </v-col>

            <!-- offices -->
            <v-col cols="6" id="col-3">
                <div v-for="item in offices" v-bind:key="item.office.officeId" 
                        @drop="onDrop($event, item.office)"
                        @dragover.prevent
                        @dragenter.prevent 
                        class="drag-name">
                    
                    <v-card v-if="item.office.name != 'Unassigned Doctors'" class="pb-5 mb-5 ">
                    <div id=dropZoneTitle>
                        <v-card-text class="text-center text-lg-h6 font-weight-bold">
                        {{item.office.name}}
                        </v-card-text>
                    </div>
                    <v-card-text v-if="item.doctors.length == 0" class="text-center"> No doctors have been assigned to this office </v-card-text>

                    <draggable v-model="item.doctors" group="offices" >
                        <div v-for="doctor in item.doctors" :key="doctor.doctorId" style="font-weight:bold;"  
                                @dragstart='startDrag($event, doctor)'  >
                        <v-hover v-slot="{ hover }">
                        <!-- Doctor in Office     -->
                        <v-card class="ml-10 mr-10 mb-5 doctorCards" :elevation="hover ? 5 : 2" color="#dddddd">
                            <v-card-text class="text-center text-md-body-1 font-weight-bold">
                            Dr. {{doctor.firstName}} {{doctor.lastName}}
                            </v-card-text>

                        </v-card>
                        </v-hover>
                        </div>
                    </draggable>
                    </v-card>

                </div>
            </v-col>
        </v-card>
        </v-col>

        <!-- <v-col cols="2">
           
        </v-col> -->
        </v-row>
    </v-container>

  </v-app>
</div>
    
</template>

<script>
import draggable from 'vuedraggable'
import officeService from '@/services/OfficeService'
import doctorService from '@/services/DoctorService'

export default {
    name: "office-assignment", 
    components: {
        draggable
    },
    data() {
        return {
            //Populates @ created
            offices: [   
                //{ 
                //office (contains name, officeId, ...)
                //arrayOfDoctors  (each doctor has an office)
                //}     
            ], 
            allDoctors: [], 
            hover: true
        }
    }, 
    
    methods: {
        startDrag: (event, doctor) => {
            event.dataTransfer.dropEffect = 'move'
            event.dataTransfer.effectAllowed = 'move'
            event.dataTransfer.setData('doctorId', doctor.doctorId)

        },
        async onDrop (event, office) {
            const doctorID = event.dataTransfer.getData('doctorId');
            console.log(doctorID);
            const doctor = this.allDoctors.find(doc => doc.doctorId == doctorID);
            console.log(doctorID);

            doctor.office = office;
            console.log(office);
            await 
            doctorService.updateOfficeForDoctor(doctor)
                .then(response => {
                    if(response.status == 200) {
                        console.log("Success");
                    }
                })
                .catch(error => {
                    console.log(error.message);
                    alert("Doctor transfer unsucessful");
                })
            

        }
        
    },
    created() {
        //Populate offices with doctors assigned to an office
        officeService.getOffices()
        .then(response => {
            let offices = response.data;
            offices.forEach(office => {
                officeService.getDoctorsInOffice(office.officeId)
                .then(responseDoctors => {
                    let doctorsInOffice = responseDoctors.data;
                    this.offices.push({
                        office: office,
                        doctors: doctorsInOffice
                        });
                    if(responseDoctors.data.length > 0) {
                        doctorsInOffice.forEach(doctor => {
                            this.allDoctors.push(doctor);
                         }
                    )
                    }
                }).catch(error => {
                    alert("Failed to load doctors")
                    console.log(error.message)
                })
                
            })
        });

        //Populate unassigned section - this officeId will be 0 (converts to null on the server side)
        
        officeService.getDoctorsInOffice(0)
            .then(responseDoctors => {
                let doctorsWithNoOffice = responseDoctors.data;

                this.offices.push({
                    office: {
                        name: "Unassigned Doctors", 
                        officeId: 0
                    },
                    doctors: doctorsWithNoOffice
                     })

                     if(responseDoctors.data.length > 0) {
                        doctorsWithNoOffice.forEach(doctor => {
                            this.allDoctors.push(doctor);
                         }
                    )
                     }
                }).catch(error => {
                    alert("Failed to load doctors")
                    console.log(error.message)
                })

    }

}
</script>

<style scoped>
/* #dragging-container{
    border: 2pt solid green;
} */
#app{
    background-color: #00000000;
}

#dragger{
    background-color: #eeeeee;
    vertical-align: top;
    display: flex;
    align-items: flex-start;
}
#col-1, #col-2, #col-3{
    display: inline-block;
}

.drop-zone {
    min-height: 10px;
    margin: 15px;
    padding: 10px;
    
}

.drag-obj {
    background-color: grey;
    margin: 10px;
}
/* .drag-name {
    background-color: chartreuse;
} */
#doctor-in-office{
    background-color: green;

}
#dropZoneTitle {
    padding: 10px;

}

.doctorCards:hover {
    box-shadow: 5px 0 5px;
    /* border: 5px solid #888888; 
    background-color: coral;*/
}

#filler {
	
	margin: 100px 0 0 15px;
}

.vertical-center {
  margin: 0;
  position: absolute;
  top: 50%;
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}



</style>