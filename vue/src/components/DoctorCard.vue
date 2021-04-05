<template>
  <div class="doctor-card">
      <h3>Dr. {{doctor.firstName}} {{doctor.lastName}}</h3>
      <office-info :doctorId="doctor.id" />
  </div>
</template>

<script>
import OfficeInfo from "@/components/OfficeInfo.vue"

export default {
    name: "doctor-card",
    props: ['doctor'],
    components: { 
      OfficeInfo 
    }
}
</script>

<style>

</style>