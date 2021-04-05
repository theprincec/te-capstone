<template>
<div class="office-info">
    <h1>{{office.name}}</h1>
    <h2>Address:</h2>
    <p>{{office.address.addressLine}}</p>
    <p>City, District, postal-code</p>
    <p>Hours of Operation</p>
    <p>Open time Close time</p>
    <p>Phone number</p>
</div>
    <!--"officeId": 2,
    "name": "Therapy",
    "address": {
        "addressLine": "Test2",
        "city": "test",
        "district": "Franklin",
        "postalCode": "43016"
    },
    "phoneNumber": "6147451420",
    "openTime": "09:00:00",
    "closeTime": "18:00:00",
    "officeRate": 25
    -->
</template>

<script>
import officeService from '@/services/OfficeService'

export default {
    name: "office-info",
    props: ['doctorId'],
    data() {
        return {
               office: {}
        }
    },
    beforeUpdate() {
        officeService.getOfficeInfo(this.doctorId).then(response => {
            this.office = response.data;
        }).catch(error => {
            console.log(error);
        })
    }  
}


</script>

<style>
.office-info {
    background-color: whitesmoke;
    border: 1px solid red;
    margin: 0 auto;
    width: 50%;
    text-align: center;
}
</style>