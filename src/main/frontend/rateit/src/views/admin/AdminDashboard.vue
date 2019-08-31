<template>
    <div>
        <v-toolbar dense color="#333399" height="60">
            <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
            <v-toolbar-title>RateIt <br> <h6 class="subheading">Administration panel</h6></v-toolbar-title>
            <v-spacer></v-spacer>

        </v-toolbar>

        <v-navigation-drawer
                app
                v-model="drawer"
                color="#333399"
                dark>
            <v-list class="mt-12">
                <v-list-item
                        v-for="item in items"
                        :key="item.title"
                        link
                        @click="swapComponent(item.component)"
                >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
            <div class="pa-2">
                <v-btn block @click="logout">Logout</v-btn>
            </div>
        </v-navigation-drawer>

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
    import Users from "./Users";
    import NewAlbum from "./NewAlbum";
    import Records from './Records'
    import axios from 'axios';
    import { mapGetters } from 'vuex';
    import { mapMutations } from 'vuex';

    export default {
        data() {
            return {
                currentComponent: null,
                componentsArray: ['NewUser'],
                drawer: true,
                items: [
                    { title: 'Dashboard', icon: 'dashboard', },
                    { title: 'Users', icon: 'account_box', component: 'Users' },
                    { title: 'Add new album', icon: 'music_note', component: 'NewAlbum' },
                    { title: 'Records', icon: 'library_music', component: 'Records' },
                ],
            }
        },

        components: {
            Users,
            NewAlbum,
            Records
        },

        computed: {
            ...mapGetters([
                'getUser',
                'getUserID'
            ])
        },

        methods: {
            ...mapMutations([
               'logOut'
            ]),
            swapComponent(component) {
                this.currentComponent = component;
                console.log(this.currentComponent);
            },

            logout() {
                alert('You have been logged out');
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
        }
    }
</script>

<style scoped>

</style>