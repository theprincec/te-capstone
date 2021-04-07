<template>
    <v-card height="100" 
        rounded="lg">
        <v-card-title class="pa-0 px-5 py-1">
            Narrow appointments search:
        </v-card-title>
        <v-card-text>
            <v-row>
                <v-col col="12"
                        md="4">
                    <v-text-field
                        dense
                        solo
                        clearable
                        color="blue-grey lighten-2"
                        label="Name"
                    ></v-text-field>
                </v-col>
                <v-col col="12"
                        md="4">
                    <v-text-field
                        dense
                        solo
                        clearable
                        color="blue-grey lighten-2"
                        label="Location"
                    ></v-text-field> 
                </v-col>
                <v-col col="12"
                        md="4">
                    <v-text-field
                        dense
                        solo
                        clearable
                        color="blue-grey lighten-2"
                        label="Specialty"
                    ></v-text-field>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>
export default {
    name: "search-appointment"

}
</script>

<style>

</style>