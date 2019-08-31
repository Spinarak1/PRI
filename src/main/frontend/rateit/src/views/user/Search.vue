<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <nav>
            <v-toolbar style="background-color: #515151" height=82 flat>
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
                                                                label="From">
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
                            height="270"
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
                            <span class="subheading"> {{ record.name }}</span>
                            <span> ({{ record.released }}) </span><br>
                            <v-rating
                                    size="30"
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

        <div @click="search">
            <v-pagination
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
    import axios from 'axios'
    export default {
        data() {
            return {
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
            }
        },

        methods: {

            advancedSearch() {
              console.log(this.dateFrom, this.dateTo, this.genre);

              if( (this.dateFrom && this.dateTo) !== null && (this.dateFrom > this.dateTo)) alert("Incorrect date input. Input 'Date to' should be grater than 'Date from' ")
                else {
                    if((this.dateFrom && this.dateTo) === null && this.genre === '') {
                        console.log('Proszę podać wartość')
                    } else if((this.dateFrom && this.dateTo) !== null && this.genre !== '') {
                        console.log('Wyszukaj z tego przedzialu po gatunku');
                        axios.get(`/api/cds?released=${this.dateFrom}&released=${this.dateTo}&genre=${this.genre}&page=${this.page-1}&size=8`)
                            .then(resp => {
                                this.filteredAlbums = resp.data.cds;
                                const totalPages = resp.data.context.totalPages;
                                this.totalPages = totalPages;
                                console.log(resp);

                                this.dateFrom = null;
                                this.dateTo = null;
                                this.genre = '';
                            })
                            .catch(e => console.log(e));

                    } else if((this.dateFrom && this.dateTo) !== null && this.genre === '') {
                        console.log('Wyszukaj z tego przedzialu, nie bierz pod uwage gatunek');
                        axios.get(`/api/cds?released=${this.dateFrom}&released=${this.dateTo}&page=${this.page-1}&size=8`)
                            .then(resp => {
                                this.filteredAlbums = resp.data.cds;
                                const totalPages = resp.data.context.totalPages;
                                this.totalPages = totalPages;
                                console.log(resp);
                            })
                            .catch(e => console.log(e));
                    } else if(this.dateFrom !== null && this.dateTo === null && this.genre !== '') {
                        console.log('Wyszukaj wszystkie od dateFrom do 2019 po gatunku')
                        axios.get(`/api/cds?released=${this.dateFrom}&released=2019&genre=${this.genre}&page=${this.page-1}&size=8`)
                            .then(resp => {
                                this.filteredAlbums = resp.data.cds;
                                const totalPages = resp.data.context.totalPages;
                                this.totalPages = totalPages;
                                console.log(resp);
                            })
                            .catch(e => console.log(e));
                    } else if(this.dateFrom !== null && this.dateTo === null && this.genre === '') {
                        console.log('Wyszukaj wszystkie od dateFrom bez uwzglednienia gatunku');
                        axios.get(`/api/cds?released=${this.dateFrom}&released=2019&genre=${this.genre}&page=${this.page-1}&size=8`)
                            .then(resp => {
                                this.filteredAlbums = resp.data.cds;
                                const totalPages = resp.data.context.totalPages;
                                this.totalPages = totalPages;
                                console.log(resp);
                            })
                            .catch(e => console.log(e));
                    } else if(this.dateFrom === null && this.dateTo !== null && this.genre !== '') {
                        console.log('Wyszukaj wszystkie z dateTo po gatunku');
                        axios.get(`/api/cds?released=${this.dateTo}&genre=${this.genre}&page=${this.page-1}&size=8`)     //TODO
                            .then(resp => {
                                this.filteredAlbums = resp.data.cds;
                                const totalPages = resp.data.context.totalPages;
                                this.totalPages = totalPages;
                                console.log(resp);
                            })
                            .catch(e => console.log(e));
                    } else if(this.dateFrom === null && this.dateTo !== null && this.genre === '') {
                        console.log('Wyszukaj wszystkie z date to bez uwzglednienia gatunku');
                        axios.get(`/api/cds?released=${this.dateTo}&page=${this.page-1}&size=8`)     //TODO
                            .then(resp => {
                                this.filteredAlbums = resp.data.cds;
                                const totalPages = resp.data.context.totalPages;
                                this.totalPages = totalPages;
                                console.log(resp);
                            })
                    } else if((this.dateFrom && this.dateTo) === null && this.genre !== '') {
                        console.log('Wyszukaj po gatunku bez uwzgledniania roku');
                        axios.get(`/api/cds?genre=${this.genre}&page=${this.page-1}&size=8`)
                            .then(resp => {
                                this.filteredAlbums = resp.data.cds;
                                const totalPages = resp.data.context.totalPages;
                                this.totalPages = totalPages;
                                console.log(resp);
                            })
                    }
              }

            },

            inputSearch(value) {
                this.inputValue = value;
                console.log(value);
            },

            search() {
              console.log('elo z sercza');

              axios.get(`/api/cds?${this.curSelect}&size=8&page=${this.page-1}`)
                  .then(resp => {
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