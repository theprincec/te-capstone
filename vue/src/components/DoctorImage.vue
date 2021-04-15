<template>
  <div>
      <v-img
        class="hidden-xs-and-down"
        height="100"
        width="120"
        v-if="!fileDoctorUrl"
        src="../assets/placeholder.jpg"
        style="background-color:#00000000"
        ></v-img>
        <v-img
        height="100"
        width="120"
        contain v-if="fileDoctorUrl"
        :src="fileDoctorUrl"
        alt="Doctor Image"
        
        ></v-img>
        <!-- <div id="image-fill" style=`backgrounds-image: url('paper.gif')" contain v-if="fileDoctorUrl"
        :src="fileDoctorUrl"> 

        </div> -->

        <v-dialog
            v-model="dialog"
            persistent
            max-width="600px"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-btn fab id="button" class="mx-5 px-2" 
                    color="#666666"
                    dark
                    x-small
                    v-bind="attrs"
                    v-on="on"
                >
                <v-icon  dark>
                    mdi-plus
                </v-icon>
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
                v-model="myFile"
                accept="image/png, image/jpeg"
                placeholder="Click to upload file"
                @change="myFileInput"
                :disabled="processing"
                class="px-10"
                dense
                small-chips
                label="Update doctor image"
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
            myFile: null,
            fileDoctorUrl: null,
            currentDoctor: {} 
        }
    }, 
    watch: {
        doctor: function(newDoctor) {
            this.updatedDoctorImage(newDoctor);
        }
    },
    computed: {
        doctor() {
            return this.$store.state.currentDoctor;
        }
    },
    methods: {

        updatedDoctorImage(newDoctor) {
            //ACCESS COLLECTION FROM FIRESTORE
            const id = newDoctor.doctorId;
            firebase.firestore().collection("doctors").doc(`${id}`)
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
        },
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
            const filePath = `doctors/${this.currentDoctor.doctorId}-${Date.now()}-${file.name}`;
            // const metadata = { contentType: this.myFile.type };
// UPLOADING AND GETTING URL FROM FIREBASE
            const uploadTask = firebase.storage().ref()
              .child(filePath)
              .put(this.myFile)
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

                let doctor = {
                    timestamp: firebase.firestore.FieldValue.serverTimestamp(),
                    link: this.fileDoctorUrl
                }
                const newId = this.$store.state.currentDoctor.doctorId;
    
// ADDING COLLECTION TO FIRESTORE BY OFFICE ID
// UPDATE IF OFFICEID ALREADY EXISTS
// SET IF DOES NOT
                const docRef = firebase.firestore().collection("doctors").doc(`${newId}`);
                docRef.get().then((doc) => {
                    if (doc.exists) {
                        docRef.update(doctor)
                    .then(() => {  
                        console.log("updated")   
                        this.showEditImage = false;   
                    }).catch((e) => {
                        console.log(e)
                    })
                    } else {
                        docRef.set(doctor)
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
     }
    }
}

</script>

<style scoped>
#button {
  background-color:#888888;
  position: absolute;
  top: 70%;
  left: 67%;

}
#button:hover, #button:active {
  background-color:#f45d1c
}

/* #image-fill{
    background-image: url("paper.gif");
    background-size: fill;
} */
</style>


