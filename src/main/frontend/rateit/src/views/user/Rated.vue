<template>
    <div>
        <v-container fluid>
            <v-row class="my-2 mx-2">
                <v-col xs="12" sm="12" md="4" v-for="rate in ratedAlbum" :key="rate.id" >

                    <v-card
                            color="transarent"
                            flat
                            dark
                            hover
                            width="450"
                            height="500">
                        <v-img
                                src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="250"
                                max-height="250"
                        ></v-img>
                        <v-card-title class="justify-center"> {{ rate.album.artist }} </v-card-title>
                        <v-card-text style="color: #FFA255" @click="albumId(rate.id)">
                            <span> {{ rate.album.name }} </span>
                            <span> ({{ rate.album.released }}) </span><br>
                            <v-rating
                                    v-model="rate.note"
                                    readonly
                                    size="30"
                                    color="yellow darken-3"
                                    background-color="grey darken-1"
                                    empty-icon="$vuetify.icons.ratingFull"
                                    half-increments
                                    hover
                            ></v-rating>


                           <div class="mt-5 grey--text">

                           </div>
                        </v-card-text>

                    </v-card>
                    <v-divider></v-divider>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex'
    import axios from 'axios'

    export default {

        data() {
            return {
                ratedAlbum: '',
                userRate: null,
                albumID: null,
                comment: '',
                usrId: null,
                albumCommentsID: '',
                albumComments: '',

            }
        },

        methods: {
            ...mapGetters([
                'getRated',
                'getUserID'
            ]),

            albumId(id) {
                this.albumID = id;
                //console.log(`album id: ${id}`)
            },

        },

        created() {
            /*const ra = this.getRated(); //vuex
            this.ratedAlbum = ra; */

            const uId = this.getUserID(); //vuex
            this.usrId = uId;

            axios.get(`/api/users/${uId}/rated`)
                .then(resp => {
                   // console.log("albumy rated usera o id" + uId + " to: ");
                   // console.log(resp);
                    this.ratedAlbum = resp.data;    // tu tez sa piosenki
                    //console.log(this.ratedAlbum[0].note)

                })
                .catch(e => console.log(e));
        },

    }
</script>

<style scoped>

</style>