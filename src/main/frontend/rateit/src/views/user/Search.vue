<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <nav>
            <v-toolbar
                    style="background-color: #515151"
                    height=82
                    flat>
                <v-toolbar-title class="display-2" my-3 style="width: 800px;">
                    <v-row justify="center" align="center">
                        <v-col sm="1" md="1" >
                            <v-icon
                                    style="cursor: pointer"
                                    @click="backHome">home</v-icon>
                        </v-col>

                        <v-col class="mx-0 px-0">
                            <v-text-field

                                    @keypress.13="search"
                                     @input="inputSearch"
                                     label="serach"
                                     style="background-color: #646464"
                                     hide-details
                                     prepend-icon="search"
                                     single-line
                             >Search
                            </v-text-field>
                        </v-col>

                        <v-col class="mb-2" >
                            <v-dialog
                                    v-model="dialog"
                                    width="500">
                                <template v-slot:activator="{ on }">
                                    <v-icon @click="resetModal" v-on="on">settings_applications</v-icon>
                                </template>

                                <v-card>
                                    <v-card-title
                                            class="headline grey darken-4"
                                            style="color: #FFA255"
                                            primary-title>
                                        Filter search
                                    </v-card-title>

                                    <v-card-text>
                                        <v-form>
                                            <v-container>
                                                <v-row  justify="start"
                                                        align="center">
                                                    <v-col
                                                    sm="12"
                                                    md="6">
                                                        <h4>Search by date</h4>
                                                        <v-text-field
                                                                v-model="dateFrom"
                                                                label="From">

                                                        </v-text-field>
                                                    </v-col>

                                                    <v-col
                                                    sm="12"
                                                    md="6">
                                                        <h4>Search by genre</h4>
                                                        <v-text-field
                                                                v-model="genre"
                                                                label="Genre">

                                                        </v-text-field>
                                                    </v-col>
                                                </v-row>

                                                <v-row justify="start"
                                                       align="center">
                                                    <v-col
                                                    sm="12"
                                                    md="6">
                                                        <v-text-field
                                                                v-model="dateTo"
                                                                label="To">
                                                        </v-text-field>
                                                    </v-col>

                                                    <v-col
                                                    sm="12"
                                                    md="6">
                                                        <v-text-field
                                                            v-model="advancedArtist"
                                                            label="Artist">

                                                        </v-text-field>
                                                    </v-col>
                                                </v-row>
                                            </v-container>
                                        </v-form>
                                    </v-card-text>

                                    <v-card-actions>
                                        <div class="flex-grow-1"></div>
                                        <v-btn
                                                color="orange"
                                                text
                                                @click="advancedSearch"
                                        >
                                            Apply
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-col>

                        <v-col>
                           <v-select
                                    height="20"
                                    class="mt-10"
                                    flat
                                    dense
                                    :items="searchSelect"
                                    filled
                                    label="Search by"
                                    @change="selectSearch"
                            ></v-select>
                        </v-col>

                    </v-row>

                </v-toolbar-title>

                <v-spacer></v-spacer>

                <v-toolbar-items>
                    <v-btn text color="#FFA255" class="btn mx-2" @click="ranking">
                        <span>Ranking</span>
                        <v-icon></v-icon>
                    </v-btn>

                    <v-btn text color="#FFA255" @click="logout">
                        <span>Log Out</span>
                        <v-icon>mdi-export-variant</v-icon>
                    </v-btn>
                </v-toolbar-items>

            </v-toolbar>
            <v-divider dark></v-divider>
        </nav>

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
            <h5 style="color: #FFA255">{{albumsDrawer.genre}}</h5>
            <h5 class="white--text">Tracklist:</h5>
            <h5 class="white--text" v-for="(track, index) in tracks">
                {{ index+1}}. {{track.title}}
            </h5>
            <h5 class="white--text">Rating: {{albumsDrawer.rating}}</h5>

            <v-btn
                    @click="addAlbum(albumsDrawer.id, albumsDrawer.artist, albumsDrawer.name,albumsDrawer.genre,  albumsDrawer.rating, albumsDrawer.released)"
                    style="font-size: 10px; color: #FFA255"
                    text
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


<!-- ###################################################### -->
        <div v-if="visCom">
        <v-container fluid>
            <v-row class="my-3 ml-3">
                <v-col
                        xs="12"
                        sm="12"
                        md="3"
                        v-for="record in filteredAlbums" :key="record.id"
                >
                    <v-card
                            color="transarent"
                            flat
                            dark
                            hover
                            width="200"
                            height="280"
                            @click="albumData(record.id, record.artist, record.name, record.genre, record.rating, record.released)"
                    >
                        <v-img
                                :src="filteredAlbums.photoURL"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="100"
                                max-height="100"
                        ></v-img>
                        <v-card-title>  <h5>{{ record.artist }}</h5>  </v-card-title>
                        <v-card-text style="color: #FFA255">
                            <span class="subheading"> {{ record.name }}</span><br>
                            <span style="text-transform: capitalize;"> ({{ record.released }}) {{ record.genre }} </span><br>
                            <v-rating
                                    size="30"
                                    color="yellow darken-3"
                                    v-model="record.rating"
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

        <div v-if="curSearch==='search'" @click="search">
            <v-pagination
                    v-model="page"
                    :length="totalPages"
                    :total-visible="7"
                    prev-icon="menu-left"
                    next-icon="menu-right"
            ></v-pagination>
        </div>

            <div v-if="curSearch==='advancedSearching'" @click="advancedSearch">
                <v-pagination
                     v-model="page"
                    :length="totalPages"
                    :total-visible="7">

                </v-pagination>
            </div>
    </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import { mapMutations } from 'vuex'
    import { mapGetters } from 'vuex'
    export default {
        data() {
            return {
                curSearch: '',
                searchSelect: ['Artist', 'Name'],
                curSelect: '',
                filteredAlbums: '',
                page: 1,
                totalPages: null,

                inputValue: '',
                dialog: false,

                dateFrom: null,
                dateTo: null,
                genre: '',
                advancedArtist: '',

                visCom: false,
                rightDrawer: false,
                albumsDrawer: '',

                modalRating: null,
                comment: '',
                listOfComments: '',

                tracks: '',

            }
        },

        props: ['compAct'],

        computed: {
            ...mapGetters([
                'getUser',
                'getUserID'
            ])
        },

        methods: {

            toggleSearh() {
              this.compVisible = !this.compVisible;
              //this.$emit('compChange', this.compVisible);
            },

            advancedSearch() {
                this.visCom = true;
                this.$emit('switchComp', this.visCom);

                this.curSearch = 'advancedSearching';
              //console.log(this.dateFrom, this.dateTo, this.genre, this.advancedArtist);

                if((this.dateFrom && this.dateTo) !== null && (this.genre && this.advancedArtist) !== '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=${this.dateTo}&genre=${this.genre}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            // #1
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if((this.dateFrom && this.dateTo) !== null && this.genre !== '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=${this.dateTo}&genre=${this.genre}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            // #2
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if((this.dateFrom && this.dateTo) !== null && this.genre === '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=${this.dateTo}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            // #3
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if((this.dateFrom && this.dateTo) !== null && this.genre === '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=${this.dateTo}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            // #4
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre !== '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=2019&genre=${this.genre}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            // #5
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre !== '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=2019&genre=${this.genre}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#6
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre === '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=2019&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#7
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre === '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&released=2019&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#8
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre !== '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=1950&released=${this.dateTo}&genre=${this.genre}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#9
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre !== '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=1950&released=${this.dateTo}&genre=${this.genre}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#10
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre === '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=1950&released=${this.dateTo}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#11
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre === '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=1950&released=${this.dateTo}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#12
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo === null && this.genre !== '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?genre=${this.genre}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#13
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo === null && this.genre !== '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?genre=${this.genre}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#14
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo === null && this.genre === '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#15
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo === null && this.genre === '' && this.advancedArtist === '') {
                   alert('Please fill in the input.')
                }

             },

  inputSearch(value) {
      this.inputValue = value;
      console.log(value);
  },

  search() {
      this.visCom = true;
      this.$emit('switchComp', this.visCom);
      this.curSearch = 'search';
    console.log('elo z sercza');

    axios.get(`/api/cds?${this.curSelect}=${this.inputValue}&size=8&page=${this.page-1}`)
        .then(resp => {
            console.log(resp);
            this.filteredAlbums = resp.data.cds;
            const totalPages = resp.data.context.totalPages;
            this.totalPages = totalPages;
        })
        .catch(e => console.log(e));
    },

  pagin() {
    console.log("pagin" + this.inputValue);

  },

  selectSearch(select){
      console.log(select.toLowerCase());
      const searchSelect = select.toLowerCase()
      this.curSelect = searchSelect;
  },

  albumData(id, artist, name, genre, rating, released) {
      this.rightDrawer = !this.rightDrawer;
      this.fetchComments();

     const albumObj = {
          id: id,
          artist: artist,
          name: name,
          genre: genre,
          rating: rating,
          released: released,
      };

      axios.get(`/api/cds/${id}/tracks`)
          .then(resp => {
              console.log(resp.data);
              this.tracks = resp.data;
          })
          .catch(e => console.log(e));

      this.albumsDrawer = albumObj;
  },

            ...mapMutations([
                'albumInfo',
                'logOut',
                'ratedAlbum'
            ]),

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

  resetModal(){
      this.dateFrom= null;
      this.dateTo= null;
      this.genre= '';
      this. advancedArtist= '';

  },
   backHome() {
     console.log('erlo')
       this.visCom = false;
       this.$emit('reviveComp', this.visCom);
   },
  ranking(){
      this.$router.push('ranking');
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
}
</script>

<style scoped>

</style>