<template>
<div class="doctors-list">
    <doctor-card v-for="doctor in $store.state.doctors" :key="doctor.id" :doctor="doctor" />

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
    created() {
        doctorService.getDoctors()
        .then(response => {
            this.$store.commit("SET_DOCTORS", response.data);
        }).catch( error => {
            console.error( error );
        });
    }

}
</script>

<style>

</style>