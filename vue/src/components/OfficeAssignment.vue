<template>
<div id="app">
  <v-app>
    <v-container>
      <v-row>
        <v-col cols="4">
            <v-card>    
            <div style="color:red; font-weight:bold; padding-bottom:30px">Unassigned Doctors:</div>
            <draggable v-model="unassigned[0]" group="offices" @drop="onDrop($event, 0)">

                <div v-for="doctor in unassigned[0].doctors" :key="doctor.id" style="font-weight:bold;">
                {{doctor.firstName}} {{doctor.lastName}}
                </div>
            </draggable>
          </v-card>
        </v-col>

        <v-col cols="8">
            <v-card v-for="office in offices" v-bind:key="office.officeId">

                <div id=dropZoneTitle style="color:red; font-weight:bold; padding-bottom:30px">{{office.name}}</div>
                <draggable v-model="offices[office]" group="offices" @drop="onDrop($event, office.officeId)">
                    <div v-for="doctor in office.doctors" :key="doctor.id" style="font-weight:bold;">
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

export default {
    name: "office-assignment", 
    components: {
        draggable
    },
    data() {
        return {
            //Populate @ created
            //for each office: contains an office name, office id, and a list of doctors assigned to that office
            offices: [], 
            //contains an office id = 0, office name = Unassigned, and a list of doctors with no office
            unassigned: []
        }
    }, 
    
    methods: {
        onDrop (event, officeId) {
            const doctorID = event.dataTransfer.getData('doctorID');
            const doctor = this.doctors.find(doctor => doctor.id == doctorID);
            doctor.officeId = officeId;
            //ADD CONTROLLER/SERVICE EVENT TO UPDATE OFFICE HERE FOR EACH DOCTOR THAT GETS MOVED(BASED ON DOCTOR ID)
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
                        })
                }).catch(error => {
                    console.log("failed here" + error.message)
                })
                
            })
        });

        //Populate unassigned section - this officeId will be 0 (converts to null on the server side)
        
        officeService.getDoctorsInOffice(0)
            .then(responseDoctors => {
                let doctorsWithNoOffice = responseDoctors.data;
                this.unassigned.push({
                    officeId: 0,
                    name: "Unassigned",
                    doctors: doctorsWithNoOffice
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