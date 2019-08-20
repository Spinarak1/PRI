<template>
    <div style="background: #414141; height: 100%" >
        <nav>
            <v-toolbar style="background-color: #515151" height=82 flat>
                <v-toolbar-title class="display-2 white--text " style=" cursor: pointer" >
                    RateIt
                </v-toolbar-title>
                <v-spacer></v-spacer>

                <v-toolbar-items>
                    <v-btn text color="#FFA255" class="btn mx-2">
                        <span>Dashboard</span>
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
        <v-navigation-drawer app class="py-12" v-model="drawer" color="#515151">
            <v-spacer class="py-1"></v-spacer>
            <v-list dark class="mt-5" >
                <v-list-item

                        v-for="item in sideBar"
                        :key="item.title"
                        @click="swapComponent(item.component)"

                        class="tile">
                    <v-list-item-icon>
                        <v-icon style="color: #FFA255">{{ item.icon }}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title style="color: #FFA255">
                            {{ item.title }}
                        </v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-divider></v-divider>

            </v-list>

        </v-navigation-drawer >

        <v-container fluid>
            <v-row>
                <v-card
                        class="ml-3"
                        max-width="434">

                    <v-img
                            height="100%"
                            src="https://cdn.pixabay.com/photo/2016/11/29/07/36/audience-1868137_960_720.jpg">
                        <v-row
                                class="fill-height">

                            <v-col
                                    align-self="start"
                                    class="pa-0"
                                    cols="12">

                                <v-avatar
                                        class="profile mx-4 my-4"
                                        color="grey"
                                        size="164"

                                >
                                    <v-img src="https://olamundo.pl/wp-content/uploads/2014/09/pan-kleks-1160x774.jpg"></v-img>
                                </v-avatar>
                            </v-col>
                            <v-col class="py-0">
                                <v-list-item
                                        color="rgba(0, 0, 0, .4)"
                                        dark
                                >
                                    <v-list-item-content>
                                        <v-list-item-title class="title">Marcus Obrien</v-list-item-title>
                                        <v-list-item-subtitle>Network Engineer</v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-col>
                        </v-row>
                    </v-img>
                </v-card>
            </v-row>
            <v-row>
                <v-col justify="center" align="center">
                    <component :is="currentComponent"></component>
                </v-col>
            </v-row>

        </v-container>
    </div>


</template>

<script>
    import Rated from "./Rated";

    export default {
        data() {
            return {
                currentComponent: null,
                componentsArray: ['NewUser'],
                drawer: true,
                sideBar: [
                    { title: 'Rated albums', icon: 'note', component: 'Rated' },
                    { title: 'Favorites', icon: 'favorite', component: '' },
                    { title: 'Settings', icon: 'settings', component: '' },
                ],
                records: [
                    { band: 'Metallica', album: 'Ride The Lightning', year: '1984', rate: '4.5' },
                    { band: 'Slayer', album: 'Show No Mercy', year: '1983', rate: '4.5' },
                    { band: 'Megadeth', album: 'Rust in Peace', year: '1990', rate: '4.5' },
                    { band: 'Anthrax', album: 'State of Euphoria', year: '1986', rate: '4' },
                    { band: 'Venom', album: 'Storm The Gates', year: '2019', rate: '3' },
                    { band: 'Sodom', album: 'Agent Orange', year: '1989', rate: '4' },
                    { band: 'Bathory', album: 'Nordland', year: '2001', rate: '2.5' },
                ],
            }
        },

        methods: {
            swapComponent(component) {
                this.currentComponent = component;
                console.log(this.currentComponent);
            },
        },

        components: {
            Rated
        }
    }
</script>