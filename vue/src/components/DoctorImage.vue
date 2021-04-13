<template>
  <div >
      
      <v-img
        class="hidden-xs-and-down"
        max-height="100"
        v-if="!fileDoctorUrl"
        src="../assets/placeholder.jpg"
        ></v-img>
        <v-img
        max-height="100"
        contain v-if="fileDoctorUrl"
        :src="fileDoctorUrl"
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
                    v-on="on"
                >
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
                v-if="!fileDoctorUrl"
                src="../assets/placeholder.jpg"
                ></v-img>
                <v-img
                max-height="250"
                contain v-if="fileDoctorUrl"
                :src="fileDoctorUrl"
                alt="Doctor Image"
            ></v-img>

             <!-- FILE INPUT -->
            <v-file-input
                v-model="myFileImage"
                accept="image/png, image/jpeg"
                placeholder="Click to upload file"
                @change="myFileInput"
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

            <!-- <v-btn @click="dialog=false">
            Cancel
            </v-btn> -->
        </form>

     </v-card>
     </v-dialog>
  </div>

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
            myFileImage: null,
            fileDoctorUrl: null 
        }
    }, 
    computed: {
        doctor() {
            return this.$store.state.currentDoctor;
        }
    },
    methods: {
        async myFileInput(file) {

        try {
          if (file && file.name) {
            this.processing = true;
// LOADING IMAGE TO THE PAGE
            const fr = new FileReader();
            fr.readAsDataURL(file);
            fr.addEventListener("load", () => {
    //   this is to load image on the UI
    //   .. not related to file upload
              this.fileDoctorUrl = fr.result;
            });
//    CREATE METADATA FOR FIREBASE
            const imgData = new FormData();
            imgData.append("image", this.myFile);
            const filePath = `doctors/${this.doctor.doctorId}-${Date.now()}-${file.name}`;
            // const metadata = { contentType: this.myFile.type };
// UPLOADING AND GETTING URL FROM FIREBASE
            const uploadTask = firebase.storage().ref()
              .child(filePath)
              .put(this.myFileImage)
              .then((snapshot) => {
               return snapshot.ref.getDownloadURL()   
              })
              .then(url => {
//TEST console.log("File at:" + url);
                 this.fileDoctorUrl = url; //this should save fileUrl to url from firebase
              })
              .catch(e => {console.log(e)});
//WAITING ON WHEN UPLOAD IS FINISHED
            await uploadTask;

                let doctorImage = {
                    timestamp: firebase.firestore.FieldValue.serverTimestamp(),
                    link: this.fileDoctorUrl
                }

                const newId = this.doctor.doctorId;
    
// ADDING COLLECTION TO FIRESTORE BY OFFICE ID
// UPDATE IF OFFICEID ALREADY EXISTS
// SET IF DOES NOT
                const docRef = firebase.firestore().collection("doctors").doc(`${newId}`);
                docRef.get().then((doc) => {
                    if (doc.exists) {
                        docRef.update(doctorImage)
                    .then(() => {  
                        console.log("updated")   
                        this.showEditImage = false;   
                    }).catch((e) => {
                        console.log(e)
                    })
                    } else {
                        docRef.set(doctorImage)
                        .then(() => {
//TEST console.log("set")
                            this.showEditImage = false;   
                        }).catch((e) => {
                            console.log(e)
                        })
                    }
                }).catch(e => {
                    console.log(e)
                })
// TEST   console.log("filePath: ", filePath);
          }
        } catch (e) {
          console.error(e);
        } finally {
          this.processing = false;
        
     }
    },
    mounted() {
//ACCESS COLLECTION FROM FIRESTORE
        const doctor = this.doctor();
        firebase.firestore().collection("doctors").doc(`${doctor.doctorId}`)
            .get()
            .then((doc) => {
                if(doc.exists) {
                    console.log(doc.id, " => ", doc.data());
                    this.fileDoctorUrl = doc.data().link;
                } else {
                    console.log(doc.data().timestamp)
                }   

            })

            .catch((error) => {
                console.log("Error getting documents: ", error);
            })

        } 
    }
   

}
</script>

<style>

</style>