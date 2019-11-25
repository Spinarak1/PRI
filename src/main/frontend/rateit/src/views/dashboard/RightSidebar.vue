<template>
    <v-navigation-drawer app right color="#515151" v-model="rightDrawer" class="py-12 ">
        <v-spacer class="py-1"></v-spacer>
        <v-img
                :src="albumsDrawer.photoURL"
                aspect-ratio="1"
                class="grey lighten-2 mt-7"
                max-width="100"
                max-height="100">
        </v-img>
        <h4 class="white--text">{{albumsDrawer.artist}}</h4>
        <h5 style="color: #FFA255">{{albumsDrawer.name}}</h5>
        <h5 class="white--text">Tracklist:</h5>
        <h5 class="white--text" v-for="(track, index) in tracks">
            {{ index+1}}. {{track.title}}
        </h5>
        <h5 class="white--text">Rating: {{albumsDrawer.rating}}</h5>
        <v-btn
                style="font-size: 10px; color: #FFA255"
                text
                @click="addAlbum(albumsDrawer.id, albumsDrawer.artist, albumsDrawer.name, albumsDrawer.rating, albumsDrawer.released)"
                small
        >Add to owned
        </v-btn>

        <h5 class="white--text ml-1">Rate it!</h5>
        <div @click="rateAlbum(albumsDrawer.id, albumsDrawer.artist, albumsDrawer.name, albumsDrawer.rating, albumsDrawer.released)"
        >
            <v-rating
                    size="30"
                    v-model="modalRating"
                    color="yellow darken-3"
                    background-color="grey darken-1"
                    empty-icon="$vuetify.icons.ratingFull"
                    half-increments
                    hover
            ></v-rating>
        </div>

        <v-textarea
                v-model="comment"
                @keypress.13="addComent(albumsDrawer.id, albumsDrawer.artist, albumsDrawer.name, albumsDrawer.rating, albumsDrawer.released)"
                name="input-3-1"
                label="Write a comment"
        ></v-textarea>

        <v-card

                style="background: #696969"
                class="mx-3" v-for="comment in listOfComments">
            <p class="subheading">{{comment.user.nick}}: {{comment.content}}</p>
        </v-card>
    </v-navigation-drawer>

</template>

<script>
    import axios from 'axios'
    import { mapGetters } from 'vuex'

    export default {
        data() {
            return {
                modalRating: null,
                comment: '',
            }
        },

        props: ['rightDrawer', 'albumsDrawer', 'tracks', 'listOfComments'],

        methods: {
            addAlbum(id, artist, name, rating, released) {
                const albumObj = {
                    id: id,
                    artist: artist,
                    name: name,
                    rating: rating,
                    released: released,
                };

                const userID = this.getUserID;
                console.log(userID);

                axios.post(`/api/users/${userID}/add-cd/${id}`)
                    .then(resp => {
                        console.log(resp);
                        alert("Album added!")
                    })
                    .catch(e => console.log(e));

                // this.albumInfo(albumObj);   // vuex
            },

            rateAlbum(id, artist, name, rating, released) {
                const uId = this.getUserID; //vuex

                const rateObj = {
                    id: id,
                    artist: artist,
                    name: name,
                    rating: rating,
                    released: released
                };

                axios.post(`/api/cds/${id}/rate?note=${this.modalRating}&userId=${uId}`)
                    .then(resp => {
                        console.log(resp);
                        alert("Rate added");
                        this.modalRating = null;
                        this.comment = '';
                    })
                    .catch(e => console.log(e));
                //this.ratedAlbum(rateObj); //vuex
            },

            addComent(id, artist, name, rating, released) {
                const uId = this.getUserID; //vuex
                const commentObj = {
                    content: this.comment,
                    userId: uId,
                };

                console.log(commentObj);
                if (this.comment !== '') {
                    axios.post(`/api/cds/${id}/add-comment`, commentObj)
                        .then(resp => {
                            console.log(resp);
                            console.log("Komentarz poszedł");
                            alert("Comment added");
                            this.comment = '';
                        })
                        .catch(e => console.log(e));
                }
            },
        },

        computed: {
            ...mapGetters([
                'getUserID'
            ])
        }
    }
</script>

<style scoped>

</style>