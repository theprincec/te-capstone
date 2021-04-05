<template>
<div class="doctors-list">
    <doctor-card v-for="doctor in doctors" :key="doctor.id" :doctor="doctor" />

</div>
  
</template>

<script>
import doctorService from '@/services/DoctorService'
import DoctorCard from '@/components/DoctorCard'

export default {
    name: "doctors-list",
    components: {
        DoctorCard
    },
    data() {
        return {
            doctors: []
        }
    },
    created() {
         doctorService.getDoctors().then(response => {
             this.doctors = response.data;
         })
    }

}
</script>

<style>

</style>