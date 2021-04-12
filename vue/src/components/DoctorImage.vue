<template>
  <v-row  justify="end" >
      
      <v-img
        max-height="250"
        v-if="!fileUrl"
        src="../assets/placeholder.jpg"
        ></v-img>
        <v-img
        max-height="250"
        contain v-if="fileUrl"
        :src="fileUrl"
        alt="Office Image"
        ></v-img>

        <v-dialog
            v-model="dialog"
            persistent
            max-width="600px"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-btn class="mx-5 px-2" rounded small
                    color="primary"
                    dark
                    v-bind="attrs"
                    v-on="on"                >
                Change image
            </v-btn>
                

        <!-- FORM --> 
        </template>
        <v-card id="imageButton"
        min-height="368" class="mt-5"
        flat>

            <v-card-title>
                <span class="headline">Upload new image:</span>
            </v-card-title>

        <!-- IMAGE UPLOAD -->

             <v-img
                max-height="250"
                v-if="!fileUrl"
                src="../assets/placeholder.jpg"
                ></v-img>
                <v-img
                max-height="250"
                contain v-if="fileUrl"
                :src="fileUrl"
                alt="Office Image"
            ></v-img>

             <!-- FILE INPUT -->
            <v-file-input
                v-model="myFile"
                accept="image/png, image/jpeg"
                placeholder="Click to upload file"
                @change="fileInput"
                :disabled="processing"
                class="px-10"
                dense
                small-chips
                label="Update office image"
                prepend-icon="mdi-camera"

            >
            </v-file-input>


            <!--SUBMISSION-->


            <form id="booking-form" class="px-9 pb-9"  v-on:submit.prevent="">

            <v-btn
                form="booking-form"
                class="mr-4"
                type="submit"
                @click="dialog=false" >
            Upload and Save Image
                <v-icon
                    right
                    dark
                >
                    mdi-cloud-upload
                </v-icon>
            </v-btn>

            <v-btn @click="dialog=false">
            Cancel
            </v-btn>
        </form>

    </v-card>
     </v-dialog>
  </v-row>

</template>

<script>
import firebase from 'firebase/app'

export default {
    name: "doctor-image", 
    data() {
        return {
            dialog: false, 
            showEditImage: false,
            processing: false,
            myFile: null,
            fileUrl: null, 
            oldFileUrl: null 
        }
    }, 
    computed: {
        doctor() {
            return this.$store.state.currentDoctor;
        }
    },
    methods: {
        async fileInput(file) {
            try {
            if (file && file.name) {
                // firebase.storage().refFromURL(fileUrl).delete()
                // .then(response => {
                //     console.log("Image deleted");
                // })
                // .catch(err => {
                //     console.log("Failed");
                // })


                this.processing = true;

                const fr = new FileReader();
                fr.readAsDataURL(file);
                fr.addEventListener("load", () => {
                //   this is to load image on the UI
                //   .. not related to file upload
                this.fileUrl = fr.result;
                });
    
                const imgData = new FormData();
                imgData.append("image", this.myFile);
                const filePath = `doctors/${this.doctor.doctorId}-${Date.now()}-${file.name}`;
                const metadata = { contentType: this.myFile.type };

                const uploadTask = firebase.storage().ref()
                .child(filePath)
                .put(this.myFile, metadata);

                await uploadTask;
                
                uploadTask.snapshot.ref.getDownloadURL().then(url => {
                    this.fileUrl = url;
                    const doctor = {
                    doctorId: this.doctor.doctorId,
                    link: this.fileUrl
                }
                firebase.firestore().collection("doctors").add(doctor).then(() => {
                    this.showEditImage = false;
                })
                //ACCESS COLLECTION FROM FIRESTORE
             

             firebase.firestore().collection("doctors")
             .where("doctorId", "==", this.doctor.doctorId)
            .onSnapshot((querySnapShot) => {
                const lastDoc = querySnapShot.docs[querySnapShot.docs.length - 1];
                console.log(lastDoc.id, " => ", lastDoc.data());
                this.fileUrl = lastDoc.data().link;
                // querySnapShot.forEach((doc) => {
                //     console.log(doc.id, " => ", doc.data());

                //     this.fileUrl = doc.data().link;
                // })
                })
                
            })
            }
            } catch (e) {
            console.error(e);
            } finally {
            this.processing = false;
            }
        },
    }, 
    created() {
        firebase.firestore().collection("doctors")
         .where("doctorId", "==", this.doctor.doctorId)
        //  .orderBy("date", "desc")
                    .get()
                    .then((querySnapShot) => {
                        const lastDoc = querySnapShot.docs[querySnapShot.docs.length - 1];
                        console.log(lastDoc.id, " => ", lastDoc.data());
                        this.fileUrl = lastDoc.data().link;
                        // querySnapShot.forEach((doc) => {
                        //     console.log(doc.id, " => ", doc.data());

                        //     this.fileUrl = doc.data().link;
                        // })
                    })
                    .catch((error) => {
                        console.log("Error getting documents: ", error);
                    });
    }

}
</script>

<style>

</style>