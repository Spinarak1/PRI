<template>
    <v-navigation-drawer app v-model="drawer" color="#515151" class="py-10 ">
        <v-spacer class="py-2"></v-spacer>
        <v-list dark class="mt-5" style="background: #515151;">
            <v-list-item
                    @click="dash"
                    color="#FFA255"
                    class="tile"
                    v-for="el in userElements"
                    :key="el.text"
                    router :to="el.route">
                <v-list-item-icon>
                    <v-icon>{{ el.icon }}</v-icon>
                </v-list-item-icon>
                <v-list-item-content>
                    <v-list-item-title>
                        {{ el.text }}
                    </v-list-item-title>
                </v-list-item-content>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>

<script>
    export default {
        data() {
            return {
                userElements: [
                    {icon: 'dashboard', text: 'Dashboard', route: '/dashboard'},
                    {icon: 'person', text: 'My Account', route: '/user'},
                ],
            }
        },

        methods: {
            dash() {
                console.log('elo');
                this.compVisible = true;
            },
        }
    }
</script>

<style scoped>

</style>