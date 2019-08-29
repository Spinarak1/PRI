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
                            height="600">
                        <v-img
                                src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="250"
                                max-height="250"
                        ></v-img>
                        <v-card-title> {{ rate.artist }} </v-card-title>
                        <v-card-text style="color: #FFA255" @click="albumId(rate.id)">
                            <span> {{ rate.name }} </span>
                            <span> ({{ rate.released }}) </span><br>
                            <v-rating

                                    @input="showRate"
                                    :v-model="rate.rating"
                                    size="30"
                                    color="yellow darken-3"
                                    background-color="grey darken-1"
                                    empty-icon="$vuetify.icons.ratingFull"
                                    half-increments
                                    hover
                            ></v-rating>
                            <v-textarea
                                    v-model="comment"
                                    solo
                                    label="Write a comment"
                            ></v-textarea>
                            <v-btn text @click="addComment(rate.id)">Add</v-btn>
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
                ratedAlbum: '',
                userRate: null,
                albumID: null,
                comment: '',
                usrId: null,
            }
        },

        methods: {
            ...mapGetters([
                'getRated',
                'getUserID'
            ]),

            showRate(rating) {
                setTimeout(() => {
                    this.userRate = rating;
                    const id = this.albumID;
                    console.log(this.albumID);
                    console.log(rating, id);

                    axios.post(`/api/cds/${this.albumID}/rate?note=${rating}`)
                        .then(resp => {
                            console.log(resp);
                            this.userRate = null;
                            this.albumID = null;
                        })
                        .catch(e => console.log(e));
                }, 100);

            },

            albumId(id) {
                this.albumID = id;
                //console.log(`album id: ${id}`)
            },

            addComment(id) {
                //console.log(this.comment, id);
                const uId = this.getUserID(); //vuex
                this.usrId = uId;
                //console.log(this.usrId);

                const commentObj = {
                    content: this.comment,
                    userId: this.usrId
                };

                axios.post(`/api/cds/${id}/add-comment`, commentObj)
                    .then(resp => {
                        console.log(resp);
                        alert('Your comment has been saved')
                    })
                    .catch(e => console.log(e));
            }
        },

        created() {
            const ra = this.getRated(); //vuex
            this.ratedAlbum = ra;

            /* const al = this.getAlbums(); // vuex
            this.albums = al; */

            axios.get('/api/cds?size=10&page=0')
                .then(resp => console.log(resp) )
                .catch(e => console.log(e));
        }
    }
</script>

<style scoped>

</style>