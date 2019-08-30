<template>
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
                             >Search </v-text-field>
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
            }
        },

        methods: {

            inputSearch(value) {
                this.inputValue = value;
                console.log(value);
            },

            search() {
              console.log('elo z sercza');

              axios.get(`/api/cds?${this.curSelect}&size=8&page=${this.page-1}`)
                  .then(resp => {
                      this.filteredAlbums = resp.data.cds;
                   // console.log(resp.data.context.totalPages);
                      const totalPages = resp.data.context.totalPages;
                      //console.log(totalPages);
                      this.totalPages = totalPages;
                  })
                  .catch(e => console.log(e));
              },

            pagin() {
              console.log("pagin" + this.inputValue);

              /*
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

            console.log(this.albums);*/
            },

            selectSearch(select){
                console.log(select.toLowerCase());
                const searchSelect = select.toLowerCase()
                this.curSelect = searchSelect;
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