<template>
    <div>
        <v-card
                style="opacity: 0.85"
                width="65vh"
                height="80vh">
            <v-card-title>Add new album</v-card-title>
            <v-card-text class="">
                <v-form class="px-2 my-4">
                    <v-text-field v-model="artist" class="pt-5" label="Band name"></v-text-field>
                    <v-text-field v-model="recordName" class="pt-5" label="Record name"></v-text-field>
                    <v-text-field v-model="genre" class="pt-5" label="Genre"></v-text-field>
                    <v-text-field v-model="released" class="pt-5" label="Released"></v-text-field>
                </v-form>
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
            }
        },

        methods: {
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