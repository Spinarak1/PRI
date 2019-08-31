<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <nav>
            <v-toolbar
                    style="background-color: #515151"
                    height=82
                    flat>
                <v-toolbar-title class="display-2" my-3 style="width: 800px;">
                    <v-row justify="center" align="center">
                        <v-col>
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
                                    <v-icon  v-on="on">settings_applications</v-icon>
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

<!-- ###################################################### -->
        <div v-if="true">
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
                    :total-visible="7"
                     prev-icon="menu-left"
                     next-icon="menu-right">

                </v-pagination>
            </div>
    </div>
    </div>
</template>

<script>
    import axios from 'axios'
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
            }
        },

        methods: {

            toggleSearh() {
              this.compVisible = !this.compVisible;
              //this.$emit('compChange', this.compVisible);
            },

            advancedSearch() {
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
                    axios.get(`/api/cds?released=${this.dateFrom}&released=${this.dateTo}}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            // #4
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre !== '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&genre=${this.genre}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            // #5
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre !== '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&genre=${this.genre}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#6
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre === '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#7
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom !== null && this.dateTo === null && this.genre === '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateFrom}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#8
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre !== '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=${this.dateTo}&genre=${this.genre}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#9
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre !== '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateTo}&genre=${this.genre}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#10
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre === '' && this.advancedArtist !== '') {
                    axios.get(`/api/cds?released=${this.dateTo}&artist=${this.advancedArtist}&page=${this.page-1}&size=8`)
                        .then(resp => {
                            //#11
                            this.filteredAlbums = resp.data.cds;
                            const totalPages = resp.data.context.totalPages;
                            this.totalPages = totalPages;
                            console.log(resp);
                        })
                } else if(this.dateFrom === null && this.dateTo !== null && this.genre === '' && this.advancedArtist === '') {
                    axios.get(`/api/cds?released=${this.dateTo}&page=${this.page-1}&size=8`)
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
                } else if(this.dateFrom == null && this.dateTo === null && this.genre !== '' && this.advancedArtist === '') {
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

  show() {
      console.log(this.dateFrom, this.dateTo, this.genre);
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