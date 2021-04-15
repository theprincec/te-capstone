<template>

  <div>
      <google-map
        :center="{lat:39.9622601,lng:-83.0007065}"
        :zoom="11"
        style="width:620px; height:620px; align:center;"
        ref="mapRef"
 
      >
      <!-- TREECARE -->
      <google-map-marker
        :position="{lat:39.95217514038086,lng:-82.98344421386719}"
        :clickable="true"     
        :draggable="false" 
        @click="this.openWindow1"
        

        />
        <gmap-info-window 
            @closeclick="window_open1=false" 
            :opened="window_open1" 
            :position="{lat:39.95217514038086,lng:-82.98344421386719}"
            :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
        >
            <p>Office: TreeCare<br>Address: 591 E Livingston Ave, Columbus, OH 43215<br>Hours: 9:00AM - 5:00PM</p> 
        </gmap-info-window>
      />
      <!-- PARROTCARE -->
      <google-map-marker
        :position="{lat:39.98872375488281,lng:-83.04320526123047}"
        :clickable="true"     
        :draggable="false" 
        @click="this.openWindow2"
      />

      <gmap-info-window 
            @closeclick="window_open2=false" 
            :opened="window_open2" 
            :position="{lat:39.98872375488281,lng:-83.04320526123047}"
            :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
        >
            <p>Office: ParrotCare<br>Address: 1495 W 5th Ave, Columbus, OH 43212<br>Hours: 9:00AM - 5:00PM</p>
        </gmap-info-window>
      <!-- AARDVARKCARE -->
      <google-map-marker
        :position="{lat:40.040836334228516,lng:-82.96101379394531}"
        :clickable="true"     
        :draggable="false" 
       @click="this.openWindow3"
      />
      <gmap-info-window 
            @closeclick="window_open3=false" 
            :opened="window_open3" 
            :position="{lat:40.040836334228516,lng:-82.96101379394531}"
            :options="{
          pixelOffset: {
            width: 0,
            height: -35
          }
        }"
        >
            <p>Office: AardvarkCare<br>Address: 3100 Cleveland Ave, Columbus, OH 43224<br>Hours: 9:00AM - 6:00PM</p> 
        </gmap-info-window>
      
      </google-map>
  </div>
</template>

<script>
export default {
    data(){
        return{
            map: 'map',
            myCoordinates: {
                lat: 0,
                lng: 0
            },
            markers: [
                {
                    label: "A",
                    position: {lat:39.95217514038086,lng:-82.98344421386719}
                }, 
                {
                    label: "B",
                    position: {lat: 11.0, lng: 11.0}
                }
            ],
            info_marker: null,
            infowindow: {lat: 10, lng: 10.0},
            window_open1: false,
            window_open2: false,
            window_open3: false
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
        openWindow1() {
            this.window_open1 = true
        },
        openWindow2 () {
            this.window_open2 = true
        },
        openWindow3 () {
            this.window_open3 = true
        }

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