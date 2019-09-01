<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div style="background: #414141; height: 100%">

        <v-navigation-drawer  app  v-model="drawer" color="#515151" class="py-12 " >
            <v-spacer class="py-2"></v-spacer>
            <v-list dark class="mt-5" style="background: #515151;">
                <v-list-item
                        @click="dash"
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

        <component @switchComp="compVisible = false" @reviveComp="compVisible = true"  :is="component"></component>

        <!-- $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ -->
    <div v-if="compVisible">
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
                                max-width="100"
                                max-height="100"
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
                v-if="compVisible"
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
                compVisible: true,
                component: 'Search',
                albums: '',
                albumsDrawer: '',
                drawer: true,
                rightDrawer: false,
                page: 1,
                totalPages: null,

                modalRating: null,
                comment: '',
                listOfComments: '',

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

                axios.get(`/api/cds/${id}`)
                    .then(resp => {
                        console.log(resp)
                    })
                    .catch(e => console.log(e));

                this.albumsDrawer = albumObj;



                //console.log(albumObj);
            },

            drawerSwitch() {
                this.rightDrawer = !this.rightDrawer;

            },

            handler(id, artist, name, rating, released){
                this.drawerSwitch();
                this.albumData(id, artist, name, rating, released);
                this.fetchComments();
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

            rateAlbum(id, artist, name, rating, released){
               console.log('elo')
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
                            console.log(`ocena poszła`)
                            alert("Rate added")
                            this.modalRating = null;
                            this.comment = '';
                        })
                        .catch(e => console.log(e));
                //this.ratedAlbum(rateObj); //vuex

               // console.log(rateObj);
            },

            addComent(id, artist, name, rating, released) {
                const uId = this.getUserID; //vuex
               const commentObj = {
                   content: this.comment,
                   userId: uId,
               }

               console.log(commentObj);
               if(this.comment !== '') {
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

            fetchComments(){
                console.log(this.albumsDrawer.id)

                axios.get(`/api/cds/${this.albumsDrawer.id}`)
                    .then(resp => {
                        console.log('fecz')
                        console.log(typeof resp.data.comments);

                        this.listOfComments = resp.data.comments;
                    })
                    .catch(e => console.log(e));

            },

            dash() {
               console.log('elo');
               this.compVisible = true;
            },

            logout() {
                const userID = this.getUserID;
                console.log(userID);

                axios.post(`/api/users/${userID}/logout`)
                    .then(resp => {
                        console.log(resp);
                        alert('You have been logged out');
                        this.logout() //vuex
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