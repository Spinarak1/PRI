<template>
    <div>
        <v-container fluid>
            <v-row class="my-5 mx-5">
                <v-col xs="12" sm="12" md="3" v-for="record in records" :key="record.album">
                    <v-card
                            color="transarent"
                            flat
                            dark
                            hover
                            width="250"
                            height="360">
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
    export default {
        name: "Rated",
        data() {
            return {
                records: [
                    { band: 'Metallica', album: 'Ride The Lightning', year: '1984', rate: 4.5 },
                    { band: 'Slayer', album: 'Show No Mercy', year: '1983', rate: 4.5 },
                    { band: 'Megadeth', album: 'Rust in Peace', year: '1990', rate: 4.5 },
                    { band: 'Anthrax', album: 'State of Euphoria', year: '1986', rate: 4 },
                    { band: 'Venom', album: 'Storm The Gates', year: '2019', rate: 3 },
                    { band: 'Sodom', album: 'Agent Orange', year: '1989', rate: 4 },
                    { band: 'Bathory', album: 'Nordland', year: '2001', rate: 2.5 },
                    { band: 'Sabaton', album: 'Polsza walcz', year: '2008', rate: 1.5 },
                ]
            }
        },
    }
</script>

<style scoped>

</style>