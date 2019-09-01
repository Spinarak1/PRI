<template>
    <div>
        <v-container fluid>
            <v-row class="my-5 mx-5">
                <v-col xs="12" sm="12" md="3" v-for="record in albums" :key="record.id" >
                    <v-card

                            color="transarent"
                            flat
                            dark
                            hover
                            width="250"
                            height="360">
                        <v-img
                                src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="250"
                                max-height="250"
                        ></v-img>
                        <v-card-title class="justify-center"> {{ record.artist }} </v-card-title>
                        <v-card-text style="color: #FFA255">
                            <span> {{ record.name }} </span>
                            <span> ({{ record.released }}) </span><br>
                            <v-rating
                                    readonly
                                    v-model="record.rating"
                                    size="30"
                                    color="yellow darken-3"
                                    background-color="grey darken-1"
                                    empty-icon="$vuetify.icons.ratingFull"
                                    half-increments
                                    hover
                            ></v-rating>
                        </v-card-text>
                    </v-card>
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
                albums: '',

            }
        },

        methods: {
            ...mapGetters([
                'getAlbums',
                'getUserID'
            ]),


        },

        created() {

           /*const al = this.getAlbums(); // vuex
            this.albums = al; */
            const userID = this.getUserID();

            console.log(userID);

            axios.get(`/api/users/${userID}/cds`)
                .then(resp => {
                    console.log(resp)
                    this.albums = resp.data
                })
                .catch(e => console.log(e));
        }
    }
</script>

<style scoped>

</style>