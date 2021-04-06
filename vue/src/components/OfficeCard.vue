<template>
    <v-card class="mx-auto pa-2">
       
        <v-card-title class="h4">Dr. {{doctor.firstName}} {{doctor.lastName}}</v-card-title>
        <v-card-text>
        
           <div class="d-flex justify-space-between subtitle-1">
                <p class="ma-1">Office name: {{doctor.office.name}}</p>
                <p class="ma-1">{{doctor.office.openTime}} - {{doctor.office.closeTime}}</p>
            </div>

            <div class="body-2">
                <p class="pb-1">{{doctor.office.address.addressLine}} {{doctor.office.address.city}} {{doctor.office.address.district}} {{doctor.office.address.postalCode}}</p>
            </div>
            <v-row
            align="center"
            class="mx-0"
        >
        <!-- change hardcoded value to rating -->
        </v-row>
        </v-card-text>

        <v-card-actions>
            <v-rating 
            v-model="rating"
            color="amber"
            dense
            background-color="grey lighten-1"
            empty-icon="$ratingFull"
            half-increments
            hover
            size="14"
            ></v-rating>

            <span class="grey--text ml-4">
        <!-- change hardcoded value to rating (counter for reviews??)-->
            4.5 (413)
            </span>
            <v-spacer></v-spacer>
            <a style="text-decoration: none" href=# class="blue--text">Check availability</a>
        </v-card-actions>
 
    </v-card>
</template>

<script>

export default {
    name: "office-card",
    props: ['doctor'],
    data: () => ({
        rating: 4.5,
    })
  
}

   
</script>

<style>
/* div {
    border: 1px solid red;
} */

</style>