<template>
    <div>
        <div class="drop-zone unassignedDoctors">
            <div v-for='item in listOne' :key='item.id' class='drag-el'>
                {{ item.name }}
            </div>
        </div>
        <br>
        <div class="drop-zone assignedDoctors">
            <div v-for='item in listTwo' :key='item.id' class='drag-el'>
                {{ item.name}}
            </div>

        </div>





    </div>
    <!-- <v-container >
        <v-row>
          <v-col cols="6" md="6">
              <v-card id="docs" 
                  rounded="lg"
                  min-height="100">
                    <h1>Unassigned doctors: </h1>
                    <draggable style="min-height: 10px" v-model="unassignedDoctors" :options="{group:'doctors'}">
                      <p v-for="doctor in unassignedDoctors" v-bind:key="doctor.id">Doc name</p>
                    </draggable>
              </v-card>
          </v-col>
       
          <v-col cols="6" md="6">
              <v-card id="docs"
                  rounded="lg"
                  min-height="100">
                  <h1>Offices:  </h1>
              </v-card>
          </v-col>
        </v-row>
     
      </v-container> -->
</template>

<script>
export default {
    name: "office-assignment", 
    data() {
        return {
            doctors: [
                {name: "Doct1",
                id: 1, 
                list: 1
                },
                {name: "Doct2",
                id: 2, 
                list: 1
                },
                {name: "Doct3",
                id: 3, 
                list: 1
                },
                {name: "Doct4",
                id: 4, 
                list: 2
                }  
            ]
        }
    }, 
    computed: {
        listOne () {
            return this.doctors.filter(item => item.list === 1)
        },
        listTwo () {
            return this.doctors.filter(item => item.list === 2)
        }
    }

}
</script>

<style>
.drop-zone {
    border: solid black 2px;
    
}

</style>