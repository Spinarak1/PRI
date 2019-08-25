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
                <v-btn block>Logout</v-btn>
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
    import NewUser from "./NewUser";
    import Users from "./Users";
    import NewAlbum from "./NewAlbum";

    export default {
        data() {
            return {
                currentComponent: null,
                componentsArray: ['NewUser'],
                drawer: true,
                items: [
                    { title: 'Dashboard', icon: 'dashboard', },
                    { title: 'Users', icon: 'account_box', component: 'Users' },
                    { title: 'Add new user', icon: 'person', component: 'NewUser' },
                    { title: 'Add new album', icon: 'music_note', component: 'NewAlbum' },
                ],
            }
        },

        components: {
            NewUser,
            Users,
            NewAlbum
        },

        methods: {
            swapComponent(component) {
                this.currentComponent = component;
                console.log(this.currentComponent);
            },
            show() {
                alert('elo')
            }
        }
    }
</script>

<style scoped>

</style>