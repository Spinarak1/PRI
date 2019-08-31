<template>
    <div style="background: #414141; height: 100%" >

        <v-navigation-drawer app  v-model="drawer" color="#515151" class="py-12 ">
            <v-spacer class="py-1"></v-spacer>
            <v-list dark class="mt-5">
                <v-list-item
                        color="#FFA255"
                        class="tile"
                        v-for="link in links"
                        :key="link.text"
                        router :to="link.route"
                        >
                    <v-list-item-icon>
                        <v-icon>{{ link.icon }}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title>
                            {{ link.text }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

            </v-list>

        </v-navigation-drawer >

        <v-navigation-drawer app right color="#515151" v-model="rightDrawer" class="py-12 ">
            <v-spacer class="py-1"></v-spacer>
            <v-img
                    src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                    aspect-ratio="1"
                    class="grey lighten-2 mt-7"
                    max-width="100"
                    max-height="100">
            </v-img>
            <h4 class="white--text">{{albumsDrawer.artist}}</h4>
            <h5 style="color: #FFA255">{{albumsDrawer.name}}</h5>
            <h5 class="white--text">Tracklist:</h5>
            <h5 class="white--text">1.	"Fight Fire with Fire" <br>
                2.	"Ride the Lightning"<br>
                3.	"For Whom the Bell Tolls"<br>
                4.	"Fade to Black"<br>
                5.	"Trapped Under Ice"<br>
                6.	"Escape"<br>
                7.	"Creeping Death"<br>
                8.	"The Call of Ktulu" (instrumental)<br></h5>
            <h5 class="white--text">Rating: {{albumsDrawer.rating}}</h5>
            <v-btn
                    style="font-size: 10px; color: #FFA255"
                    text
                    @click="addAlbum(albumsDrawer.id, albumsDrawer.artist, albumsDrawer.name, albumsDrawer.rating, albumsDrawer.released)"
                    small
            >Add to owned</v-btn>

            <v-btn
                    style="font-size: 10px; color: #FFA255"
                    text
                    @click="rateAlbum(albumsDrawer.id, albumsDrawer.artist, albumsDrawer.name, albumsDrawer.rating, albumsDrawer.released)"
                    small
            >Rate this album</v-btn>

        </v-navigation-drawer>

        <component :is="component"></component>

        <!-- $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ -->
    <div v-if="false">
        <v-container fluid >
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
                                src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="150"
                                max-height="150"
                        ></v-img>
                        <v-card-title>  <h5>{{ record.artist }}</h5>  </v-card-title>
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
                v-if="false"
                v-model="page"
                :length="totalPages"
                :total-visible="7"
                prev-icon="menu-left"
                next-icon="menu-right"
        ></v-pagination>
        </div>
    </div>
    </div>
</template>

<script>

    import Search from './Search'
    import Ranking from './Ranking'
    import axios from 'axios'
    import { mapGetters } from 'vuex';
    import { mapMutations } from 'vuex';


    export default {
        data() {
            return {
                compVisible: false,
                component: 'Search',
                albums: '',
                albumsDrawer: '',
                drawer: true,
                rightDrawer: false,
                page: 1,
                totalPages: null,

                links: [
                    { icon: 'dashboard', text: 'Dashboard', route: '/dashboard' },
                    { icon: 'person', text: 'My Account', route: '/user' },
                ],
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

                axios.get(`/api/cds?size=8&page=${this.page-1}`)
                    .then(resp => {
                        const albumsData = resp.data.cds;
                        //console.log(resp.data.context);
                        this.albums = albumsData;
                        console.log(albumsData);
                    })
                    .catch(e => console.log(e));
            },

            albumData(id, artist, name, rating, released){
                //console.log(id, name, rating, released);
                const albumObj = {
                    id: id,
                    artist: artist,
                    name: name,
                    rating: rating,
                    released: released,
                };

                // TODO album details
                axios.get(`/api/cds/${id}`)
                    .then(resp => {
                        console.log(resp)
                    })
                    .catch(e => console.log(e));

                this.albumsDrawer = albumObj;



                //console.log(albumObj);
            },

            drawerSwitch() {
                this.rightDrawer = !this.rightDrawer
            },

            handler(id, artist, name, rating, released){
                this.drawerSwitch();
                this.albumData(id, artist, name, rating, released);
            },

            addAlbum(id, artist, name, rating, released){
                //console.log(id, artist, name, rating, released);
                const albumObj = {
                    id: id,
                    artist: artist,
                    name: name,
                    rating: rating,
                    released: released,
                };

                this.albumInfo(albumObj);   // vuex
            },

            rateAlbum(id, artist, name, rating, released){
                const rateObj = {
                    id: id,
                    artist: artist,
                    name: name,
                    rating: rating,
                    released: released
                };

                this.ratedAlbum(rateObj); //vuex

                console.log(rateObj);
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
                    //console.log(albumsData);
                    const totalPages = resp.data.context.totalPages;
                    //console.log(totalPages);
                    this.totalPages = totalPages;
                })
                .catch(e => console.log(e));

            console.log(this.albums);
        },

        components: {
            Search,
            Ranking
        }
    }
</script>

<style scoped>

</style>