<template>
    <div style="background: #414141; height: 100%" >
        <nav>
            <v-toolbar style="background-color: #515151" height=82 flat>
                <v-toolbar-title class="display-2 white--text " style=" cursor: pointer" >
                    <router-link to="/dashboard" tag="div">
                        RateIt
                    </router-link>
                </v-toolbar-title>
                <v-spacer></v-spacer>

                <v-toolbar-items>
                    <v-btn text color="#FFA255" class="btn mx-2" to="/dashboard">
                        <span>Dashboard</span>
                        <v-icon></v-icon>
                    </v-btn>
                    <v-btn @click="logout" text color="#FFA255">
                        <span >Log Out</span>
                        <v-icon>mdi-export-variant</v-icon>
                    </v-btn>
                </v-toolbar-items>

            </v-toolbar>
            <v-divider dark></v-divider>
        </nav>
        <v-navigation-drawer app class="py-12" v-model="drawer" color="#515151">
            <v-spacer class="py-1"></v-spacer>
            <v-divider class="mt-7"></v-divider>
            <v-avatar
                    class="profile mx-10 my-8"
                    style="background: #FFA255"
                    size="164">
                <v-img src="https://olamundo.pl/wp-content/uploads/2014/09/pan-kleks-1160x774.jpg"></v-img>
            </v-avatar>
            <h3 style="text-align: center" class="white--text mb-5">{{getUser.nick}}</h3>
            <h5 style="text-align: center" class="white--text mb-5">{{getUser.email}}</h5>

            <v-divider></v-divider>
            <v-list dark class="mt-5" style="background: #515151;">
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
            </v-list>
        </v-navigation-drawer >

        <v-container>
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
    import Owned from "./Owned";
    import Settings from "./Settings";
    import { mapGetters } from 'vuex';
    import { mapMutations } from 'vuex';
    import axios from 'axios';

    export default {
        data() {
            return {
                userID: null,
                currentComponent: null,
                componentsArray: ['NewUser'],
                drawer: true,
                sideBar: [
                    { title: 'Rated albums', icon: 'note', component: 'Rated' },
                    { title: 'Owned', icon: 'favorite', component: 'Owned' },
                    { title: 'Settings', icon: 'settings', component: 'Settings' },
                ],
            }
        },

        computed: {
            ...mapGetters([
                'getUser',
                'getUserID'
            ])
        },

        methods: {
            swapComponent(component) {
                this.currentComponent = component;
                console.log(this.currentComponent);
            },

            ...mapMutations([
                'logOut'
            ]),

            logout() {
                alert("You have been logged out");
                const userID = this.getUserID;
                console.log('userID = ' + userID);
                axios.post(`/api/users/${userID}/logout`)
                    .then(resp => {
                        console.log(resp);
                        this.logout();
                        this.$router.push('signin')
                    })
                    .catch(e => console.log(e));

            }
        },



        components: {
            Rated,
            Owned,
            Settings
        }
    }
</script>