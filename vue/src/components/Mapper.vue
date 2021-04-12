

<template>

  <div>
      <google-map
        :center="{lat:39.9622601,lng:-83.0007065}"
        :zoom="11"
        class="hidden-md-and-down"
        style="width:620px; height:620px; align:center;"
        ref="mapRef"
 
      >
      <gmap-info-window
        :options="infoWindowOptions"
        :position="infoWindowPosition"
        :opened="infoWindowOpened"
      >
        <div class="info-window">
            <h2>Office</h2>
            <h5>Hours</h5>
            <p>Address</p>
            <p>City, State</p>

        </div>
      </gmap-info-window>
      <!-- TREECARE -->
      <google-map-marker
        :position="{lat:39.95217514038086,lng:-82.98344421386719}"
        :clickable="true"     
        :draggable="false" 
        @click="activeOffice = 'TreeCare'; infoWindowOpened = true"
      />
      <!-- PARROTCARE -->
      <google-map-marker
        :position="{lat:39.98872375488281,lng:-83.04320526123047}"
        :clickable="true"     
        :draggable="false" 
        @click="activeOffice = 'ParrotCare'; infoWindowOpened = true"
      />
      <!-- AARDVARKCARE -->
      <google-map-marker
        :position="{lat:40.040836334228516,lng:-82.96101379394531}"
        :clickable="true"     
        :draggable="false" 
        @click="activeOffice = 'AardvarkCare'; infoWindowOpened = true"
      />
      
      </google-map>
  </div>
</template>

<script>
export default {
    data(){
        return{
            map: null,
            myCoordinates: {
                lat: 0,
                lng: 0
            },
            infoWindowOptions: {
                pixelOffset: {
                    width: 0,
                    height: -35
                }
            },
            activeOffice: "",
            infoWindowOpened: false
        }
    },
    created() {
        //get users coordinates from browser request
        this.$getLocation({})
            .then(coordinates => {
                this.myCoordinates = coordinates;
            })
            .catch(error => alert(error));
    },
    methods: {

    },
    mounted() {
        //add the map to a data object
        this.$refs.mapRef.$mapPromise.then(map => this.map = map);
    },
    computed: {
        mapCoordinates() {
           if(!this.map) {
               return {
                   lat:0,
                   lng:0
               };
           }


           return {
                lat: this.map.getCenter().lat(),
                lng: this.map.getCenter().lng()
            }
        },
        infoWindowPosition() {
                return {
                lat: this.activeOffice.latitude,
                lng: this.activeOffice.longitude
                }
            },
    }
}
</script>

<style>

</style>