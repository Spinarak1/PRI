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
                        <span>New release</span>
                        <v-icon></v-icon>
                    </v-btn>
                    <v-btn text color="#FFA255" class="mx-2">
                        <span>Popular</span>
                        <v-icon></v-icon>
                    </v-btn>

                    <v-btn text color="#FFA255">
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

        <v-navigation-drawer app right color="#515151" v-model="rightDrawer" class="py-12 ">
            <v-spacer class="py-1"></v-spacer>
            <v-img
                    src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                    aspect-ratio="1"
                    class="grey lighten-2 mt-7"
                    max-width="100"
                    max-height="100">
            </v-img>
            <h4 class="white--text">Metallica</h4>
            <h5 style="color: #FFA255">Ride The Lightning(1984)</h5>
            <h5 class="white--text">Tracklist:</h5>
            <h5 class="white--text">1.	"Fight Fire with Fire" <br>
                2.	"Ride the Lightning"<br>
                3.	"For Whom the Bell Tolls"<br>
                4.	"Fade to Black"<br>
                5.	"Trapped Under Ice"<br>
                6.	"Escape"<br>
                7.	"Creeping Death"<br>
                8.	"The Call of Ktulu" (instrumental)<br></h5>
            <h5 class="white--text">Rating: 4.5</h5>

        </v-navigation-drawer>


        <v-container fluid>

            <v-row class="my-5 mx-5">
                <v-col xs="12" sm="12" md="3" v-for="record in records" :key="record.album">
                    <v-card
                            color="transarent"
                            flat
                            dark
                            hover
                            width="250"
                            height="360"
                            @click="rightDrawer = !rightDrawer">
                        <v-img
                                src="https://mir-s3-cdn-cf.behance.net/project_modules/disp/a2d57c46794097.58644a82d69c0.png"
                                aspect-ratio="1"
                                class="grey lighten-2"
                                max-width="250"
                                max-height="250"
                        ></v-img>
                        <v-card-title> {{ record.band }} </v-card-title>
                        <v-card-text style="color: #FFA255">
                            <span> {{ record.album }} </span>
                            <span> ({{ record.year }}) </span><br>
                            <v-rating
                                    v-model="record.rate"
                                    size="medium"
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
    export default {
        data() {
            return {
                drawer: true,
                rightDrawer: false,
                links: [
                    { icon: 'dashboard', text: 'Dashboard', route: '/dashboard' },
                    { icon: 'person', text: 'My Account', route: '/user' },

                ],

                 records: [
                     { band: 'Metallica', album: 'Ride The Lightning', year: '1984', rate: 4.5 },
                     { band: 'Slayer', album: 'Show No Mercy', year: '1983', rate: 4.5 },
                     { band: 'Megadeth', album: 'Rust in Peace', year: '1990', rate: 4.5 },
                     { band: 'Anthrax', album: 'State of Euphoria', year: '1986', rate: 4 },
                     { band: 'Venom', album: 'Storm The Gates', year: '2019', rate: 3 },
                     { band: 'Sodom', album: 'Agent Orange', year: '1989', rate: 4 },
                     { band: 'Bathory', album: 'Nordland', year: '2001', rate: 2.5 },
                     { band: 'Sabaton', album: 'Polsza walcz', year: '2008', rate: 1.5 },
                 ],

            }
        },
        components: {
            Navbar
        }
    }
</script>

<style scoped>
    .ttile:hover {
        background-color: green;
}
    .ttile:active {
        background-color: yellow;
    }
</style>