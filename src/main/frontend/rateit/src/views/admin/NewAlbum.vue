<template>
    <div>
        <v-card
                style="opacity: 0.85"
                width="65vh">
            <v-card-title>Add new album</v-card-title>
            <v-card-text class="">
                <v-form class="px-2 my-4">
                    <v-text-field v-model="artist" class="pt-5" label="Band name"></v-text-field>
                    <v-text-field v-model="recordName" class="pt-5" label="Record name"></v-text-field>
                    <v-text-field v-model="genre" class="pt-5" label="Genre"></v-text-field>
                    <v-text-field v-model="released" class="pt-5" label="Released"></v-text-field>
                </v-form>

                <v-form class="my-4">
                    <div >
                        <v-text-field v-model="activeSong" label="New song">
                        </v-text-field>
                    </div>
                </v-form>
                <v-btn @click="newSong" text>Add a new song</v-btn>
                <v-btn @click="albumSubmit" class="px-4" text>Add new album</v-btn>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                artist: '',
                recordName: '',
                genre: '',
                released: '',

                newAlbumId: null,

                activeSong: '',
            }
        },

        methods: {

            newSong() {
              console.log(this.activeSong)

                const songObj = {
                  title: this.activeSong
                }

                axios.post(`/api/cds/${this.newAlbumId}/add-track`, songObj)
                    .then(resp => {
                        console.log(resp);
                        console.log('Song has been added')
                        this.activeSong = '';
                    })
                    .catch(e => {
                        alert('Error occured. Please, try again.')
                        console.log(e)
                    });
            },

           /* submitSongs() {
                console.log(this.listSongs);
            },*/

            albumSubmit() {
                const albumObj = {
                    artist: this.artist,
                    genre: this.genre,
                    name: this.recordName,
                    released: this.released,
                };

                //console.log(albumObj);

                axios.post('/api/cds', albumObj)
                    .then(resp => {
                        console.log(resp);
                        alert("You've added a new album")

                        this.newAlbumId = resp.data.id;
                    })
                    .catch(e => {
                        console.log(e);
                        alert("Something went wrong. Please, try again")
                    });
            }
        }
    }
</script>

<style scoped>

</style>