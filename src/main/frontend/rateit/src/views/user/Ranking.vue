<template>

    <div style="background: #414141; height: 100%" >
        <nav>
            <v-toolbar style="background-color: #515151" height=82 flat>
                <v-toolbar-title class="display-2" my-3 >
                    <v-text-field
                            label="serach"
                            style="background-color: #646464"
                            hide-details
                            prepend-icon="search"
                            single-line
                    >Search </v-text-field>
                </v-toolbar-title>

                <v-spacer></v-spacer>

                <v-toolbar-items>
                    <v-btn text color="#FFA255" class="btn mx-2">
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
        <h1 style="text-align: center; color: white;">Ranking</h1>
        <v-container fluid>
            <v-row
                    class="my-3 ml-3"
                    align="center"
                    justify="center"
                    >
                <v-col
                        xs="12"
                        sm="12"
                        md="8"
                        v-for="(record, index) in ranking"
                        :key="record.id">
                    <v-card
                            color="transarent"
                            flat
                            dark
                            hover
                            width="450"
                            height="500">
                        <v-img
                                src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="400"
                                max-height="400"
                        ></v-img>
                        <v-card-title> {{ index + 1 }}. {{ record.artist }}  </v-card-title>
                        <v-card-text style="color: #FFA255">
                            <span> {{ record.name }}  </span>
                            <span> ( {{ record.released }} ) </span><br>
                            <v-rating
                                    readonly
                                    size="30"
                                    v-model="record.rating"
                                    color="yellow darken-3"
                                    background-color="grey darken-1"
                                    empty-icon="$vuetify.icons.ratingFull"
                                    half-increments
                                    hover
                            ></v-rating>
                        </v-card-text>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
    import Navbar from "../../components/shared/Navbar";
    import axios from 'axios'

    export default {
        data() {
            return {
                drawer: true,
                ranking: '',
                rating: 4,
                links: [
                    { icon: 'dashboard', text: 'Dashboard', route: '/dashboard' },
                    { icon: 'person', text: 'My Account', route: '/user' },
                ],
            }
        },

        methods: {
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

        created() {
            console.log('dziala');
            axios.get('/api/cds/ranking')
                .then(resp => {
                    console.log(resp.data.slice(0,10));
                    this.ranking = resp.data.slice(0,10);
                })
                .catch(e => console.log(e));
        }
    }
</script>

<style scoped>

</style>