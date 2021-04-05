<template>
  <div class="doctor-card">
      <h3>Dr. {{doctor.firstName}} {{doctor.lastName}}</h3>
     <!-- <office-info :doctorId="doctor.id" />-->
      <div class="office-info">
        <h3>{{doctor.office.name}}</h3>
        <h2>Address:</h2>
        <p>{{doctor.office.address.addressLine}}</p>
        <p>{{doctor.office.address.city}} {{doctor.office.address.district}} {{doctor.office.address.postalCode}}</p>
        <p>Hours of Operation:</p>
        <p>{{doctor.office.openTime}} - {{doctor.office.closeTime}}</p>
        <p>{{doctor.office.phoneNumber}}</p>
</div>
  </div>
</template>

<script>
//import OfficeInfo from "@/components/OfficeInfo.vue"
//import officeService from '@/services/OfficeService'

export default {
    name: "doctor-card",
    props: ['doctor'],
    // components: { 
    //   OfficeInfo 
    // }
}

   
</script>

<style>

</style>