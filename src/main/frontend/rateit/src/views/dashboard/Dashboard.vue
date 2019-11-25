<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div style="background: #414141; height: 100%">
        <search></search>
        <sidebar></sidebar>
        <right-sidebar
                :rightDrawer="drawerRight"
                :albumsDrawer="albumsDrawer"
                :tracks="tracks"
                :listOfComments="listOfComments"
        >

        </right-sidebar>
        <div>
            <v-container fluid>
                <v-row class="my-3 ml-3">
                    <v-col
                            xs="12"
                            sm="12"
                            md="3"
                            v-for="record in albums"
                            :key="record.id">
                        <v-card
                                color="transarent"
                                flat
                                dark
                                hover
                                width="200"
                                height="270"
                                @click="handler(record.id, record.artist, record.name, record.rating, record.released)"
                        >
                            <v-img
                                    :src="record.photoURL"
                                    aspect-ratio="1"
                                    class="grey lighten-2"
                                    max-width="150"
                                    max-height="150"
                            ></v-img>
                            <v-card-title><h5>{{ record.artist }}</h5></v-card-title>
                            <v-card-text style="color: #FFA255">
                                <span class="subheading"> {{ record.name }} </span>
                                <span> ({{ record.released }}) </span><br>

                                <v-rating
                                        size="30"
                                        v-model="record.rating"
                                        color="yellow darken-3"
                                        background-color="grey darken-1"
                                        empty-icon="$vuetify.icons.ratingFull"
                                        half-increments
                                        hover
                                        readonly
                                ></v-rating>
                            </v-card-text>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
            <div @click="pagin">
                <v-pagination
                        v-if="compVisible"
                        v-model="page"
                        :length="totalPages"
                        :total-visible="7"
                ></v-pagination>
            </div>
        </div>
    </div>
</template>

<script>

    import Search from './Search'
    import Ranking from './Ranking'
    import Sidebar from './Sidebar'
    import RightSidebar from './RightSidebar'

    import axios from 'axios/index'
    import { mapGetters } from 'vuex'
    import { mapMutations } from 'vuex'

    export default {
        data() {
            return {
                albums: '',
                page: 1,
                totalPages: null,
                listOfComments: '',
                tracks: '',
                drawerRight: false,
                albumsDrawer: ''
            }
        },

        methods: {
            ...mapMutations([
                'albumInfo',
                'logOut',
                'ratedAlbum'
            ]),

            pagin() {
                console.log(this.page);

                axios.get(`/api/cds?size=8&page=${this.page - 1}`)
                    .then(resp => {
                        const albumsData = resp.data.cds;
                        //console.log(resp.data.context);
                        this.albums = albumsData;
                        console.log(albumsData);
                    })
                    .catch(e => console.log(e));
            },

            albumData(id, artist, name, rating, released) {
                const albumObj = {
                    id: id,
                    artist: artist,
                    name: name,
                    rating: rating,
                    released: released,
                };

                axios.get(`/api/cds/${id}`)
                    .then(resp => {
                        console.log(resp)
                    })
                    .catch(e => console.log(e));

                axios.get(`/api/cds/${id}/tracks`)
                    .then(resp => {
                        console.log(resp.data);
                        this.tracks = resp.data;
                    })
                    .catch(e => console.log(e));

                this.albumsDrawer = albumObj;
            },

            drawerSwitch() {
                this.drawerRight = !this.drawerRight;
            },

            handler(id, artist, name, rating, released) {
                this.drawerSwitch();
                this.albumData(id, artist, name, rating, released);
                this.fetchComments();
            },

            fetchComments() {
                console.log(this.albumsDrawer.id);

                axios.get(`/api/cds/${this.albumsDrawer.id}`)
                    .then(resp => {
                        console.log('fecz');
                        console.log(typeof resp.data.comments);

                        this.listOfComments = resp.data.comments;
                    })
                    .catch(e => console.log(e));
            },

            logout() {
                const userID = this.getUserID;
                console.log(userID);

                axios.post(`/api/users/${userID}/logout`)
                    .then(resp => {
                        console.log(resp);
                        alert('You have been logged out');
                        this.logout(); //vuex
                        this.$router.push('signin')
                    })
                    .catch(e => console.log(e));
            },
        },

        computed: {
            ...mapGetters([
                'getUser',
                'getUserID'
            ])
        },

        created() {
            axios.get('/api/cds?size=8&page=0')
                .then(resp => {
                    const albumsData = resp.data.cds;
                    this.albums = albumsData;
                    const totalPages = resp.data.context.totalPages;
                    this.totalPages = totalPages;
                })
                .catch(e => console.log(e));
            console.log(this.albums);
        },

        components: {
            Search,
            Ranking,
            Sidebar,
            RightSidebar
        }
    }
</script>

<style scoped>

</style>