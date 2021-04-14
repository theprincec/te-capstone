<template>
<div id="app">
  <v-app>
    <v-container>
      <v-row>
        <v-col cols="4">
            <v-card >    
            <div style="color:red; font-weight:bold; padding-bottom:30px">Unassigned Doctors:</div>
            <draggable v-model="unassigned.doctors" group="offices" >

                <div v-for="doctor in unassigned.doctors" :key="doctor.doctorId" style="font-weight:bold;" 
                        @dragstart='startDrag($event, doctor)' @drop="onDrop($event, doctor)">
                {{doctor.firstName}} {{doctor.lastName}}
                </div>
            </draggable>
          </v-card>
        </v-col>

        <v-col cols="8">
            <v-card v-for="office in offices" v-bind:key="office.officeId">

                <div id=dropZoneTitle style="color:red; font-weight:bold; padding-bottom:30px">{{office.name}}</div>
                <draggable v-model="office.doctors" group="offices">
                    <div v-for="doctor in office.doctors" :key="doctor.doctorId" style="font-weight:bold;" 
                            @dragstart='startDrag($event, doctor)'  @drop="onDrop($event, doctor)">
                    {{doctor.firstName}} {{doctor.lastName}}
                    </div>
                </draggable>
            </v-card>
        </v-col>
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
            //Populate @ created
            //for each office: contains an office name, office id, and a list of doctors assigned to that office
            offices: [
                // {
                //     officeName
                //     officeId
                //     listofDoctors {
                //         firstName 
                //         office 
                //             officeId
                //     }
                // }
                
            ], 
            //contains an office id = 0, office name = Unassigned, and a list of doctors with no office
            unassigned: {
                officeId: "0",
                name: "Unassigned",
                doctors: []
            }, 
            allDoctors: []
        }
    }, 
    
    methods: {
        startDrag: (event, doctor) => {
            event.dataTransfer.dropEffect = 'move'
            event.dataTransfer.effectAllowed = 'move'
            event.dataTransfer.setData('doctorId', doctor.doctorId)

        },
        onDrop (event, doctor) {
            //let officeId = 0;
            let doctorID = event.dataTransfer.getData('doctorId');
            let foundDoctor = false;
            // this.offices.forEach(office => {
            //     console.log("checking offices");
            //     if(foundDoctor == false){
            //         office.doctors.forEach(doc => {
            //             console.log(doctorID);
            //             if(doc.doctorId == doctorID) {
            //                 console.log("GOT HERE")
            //                 officeId = office.officeId;
            //                 foundDoctor = true;
            //                 console.log(officeId);
            //     }
            //     })  
            //     }
            // })
            this.offices.forEach(office => {
                office.doctors.forEach(doc => {
                    doc.officeId = office.officeId;
                })
            })
            this.unassigned.doctors.forEach(doc => {
                doc.officeId = this.unassigned.officeId;
            })

            console.log(doctor);
            
            //let updatedDoctor = this.allDoctors.find(doctor => doctor.doctorId == doctorID);
            let updatedDoctor;
            this.offices.forEach(office => {
                console.log("checking offices");
                if(foundDoctor == false){
                    office.doctors.forEach(doc => {
                        console.log(doctorID);
                        if(doc.doctorId == doctorID) {
                            console.log("GOT HERE")
                            updatedDoctor = doc;
                            foundDoctor = true;
                }
                })  
                }
            })

            doctorService.updateOfficeForDoctor(updatedDoctor)
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
                        officeId: office.officeId,
                        name: office.name,
                        doctors: doctorsInOffice
                        });
                    if(responseDoctors.data.length > 0) {
                        doctorsInOffice.forEach(doctor => {
                            this.allDoctors.push(doctor);
                         }
                    )
                    }
                }).catch(error => {
                    console.log("failed here" + error.message)
                })
                
            })
        });

        //Populate unassigned section - this officeId will be 0 (converts to null on the server side)
        
        officeService.getDoctorsInOffice(0)
            .then(responseDoctors => {
                let doctorsWithNoOffice = responseDoctors.data;
                // doctorsWithNoOffice.forEach(doc => {
                //     unassigned.doctors.push(doc);
                // })
                // this.unassigned.push({
                //     officeId: 0,
                //     name: "Unassigned",
                //     doctors: doctorsWithNoOffice
                //     })
                this.unassigned.doctors = doctorsWithNoOffice;
                doctorsWithNoOffice.forEach(doctor => {
                    this.allDoctors.push(doctor);
                    })
                }).catch(error => {
                    console.log("failed here" + error.message)
                })

    }

}
</script>

<style>
.drop-zone {
    border: solid black 2px;
    background-color: red;
    margin: 10px;
    
}

.drag-obj {
    background-color: grey;
    padding: 10px;
    margin: 10px;
}



</style>