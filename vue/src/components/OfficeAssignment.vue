<template>
<div id="app">
  <v-app>
    <v-container>
      <v-row>
        <v-col cols="4">
            <v-card>
            <div style="color:red; font-weight:bold; padding-bottom:30px">Unassigned Doctors:</div>
            <draggable v-model="doctorsUnassigned" group="offices" >

                <div v-for="doctor in doctorsUnassigned" :key="doctor.id" style="font-weight:bold;">
                {{doctor.firstName}} {{doctor.lastName}}
                </div>
            </draggable>
          </v-card>
        </v-col>

        <v-col cols="8">
            <v-card v-for="office in offices" v-bind:key="office.officeId">

                <div style="color:red; font-weight:bold; padding-bottom:30px">{{office.name}}</div>
                <draggable v-model="offices[office]" group="offices" @drop="onDrop($event, office.officeId)">
                    <div v-for="doctor in THISOFFICE" :key="doctor.id" style="font-weight:bold;">
                    {{doctor.firstName}} {{doctor.lastName}}
                    </div>
                </draggable>
            </v-card>
        </v-col>
        </v-row>
    </v-container>

  </v-app>
</div>


    <!-- <div>
        <draggable class="drop-zone" group="doctors" v-model="doctorsUnassigned"
            @drop='onDrop($event, 1)' 
            @dragover.prevent
            @dragenter.prevent>
            
            <div v-for='doctor in doctorsUnassigned' :key='doctor.id' class='drag-obj'
                draggable @dragstart='startDrag($event, doctor)' style="max-width: 70px">
                {{ doctor.name }}
            </div>
        </draggable>
        <br>
        <draggable class="drop-zone" group="doctors" v-model="doctorsAssigned"
            @drop='onDrop($event, 2)' 
            @dragover.prevent
            @dragenter.prevent>
            <div v-for='doctor in doctorsAssigned' :key='doctor.id' class='drag-obj' 
                draggable @dragstart='startDrag($event, doctor)' style="max-width: 70px">
                {{ doctor.name}}
            </div>

        </draggable>

    </div> -->
    
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
            offices: []
            // doctorsUnassigned: [
            //     {name: "Doct1",
            //     id: 1, 
            //     officeId: 0
            //     }
            // ],
            // office1: [
            //     {name: "Doct2",
            //     id: 2, 
            //     officeId: 1
            //     } 
            // ],
            // office2: [
            //     {name: "Doct3",
            //     id: 3, 
            //     officeId: 2
            //     },
            //     {name: "Doct4",
            //     id: 4, 
            //     officeId: 2
            //     } 
            // ]
        }
    }, 
    
    methods: {
        // startDrag(event, doctor) {
        //     event.dataTransfer.dropEffect = 'move'
        //     event.dataTransfer.effectAllowed = 'move'
        //     event.dataTransfer.setData('itemID', doctor.id)
        // }, 
        onDrop (event, officeId) {
            const doctorID = event.dataTransfer.getData('doctorID');
            const doctor = this.doctors.find(doctor => doctor.id == doctorID);
            doctor.officeId = officeId;
            //ADD CONTROLLER/SERVICE EVENT TO UPDATE OFFICE HERE FOR EACH DOCTOR THAT GETS MOVED(BASED ON DOCTOR ID)
        }
        
    },
    computed: {
        // getDoctors() {
            //Use this computed property in each for loop to get the doctors that should 
            //be shown in that office (or in the unassigned category - this may need to be found separately)
        // }
    },
    created() {
        officeService.getOffices()
        .then(response => {
            this.offices = response.data;
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