<template>
  <v-content>
    <doctors-list />
   <!--<office-info />-->
  </v-content>
</template>

<script>
import DoctorsList from '@/components/DoctorsList'
//import OfficeInfo from '@/components/OfficeInfo'

export default {
  name: "home",
  components: {
    DoctorsList
  }
};
</script>
