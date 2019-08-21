<template>
    <nav>
        <v-toolbar color="transparent white--text" light flat>
            <v-toolbar-title class="display-2" my-3 style="cursor: pointer">
                <router-link to="/" tag="div">
                    <span class="font-weight-light">Rate</span>
                    <span>It</span>
                </router-link>
            </v-toolbar-title>

            <v-spacer></v-spacer>

            <v-toolbar-items>
                <v-btn text color="white" to="/signin">
                    <span>Log In</span>
                    <v-icon>mdi-export-variant</v-icon>
                </v-btn>
            </v-toolbar-items>

            <v-toolbar-items>
                <v-btn text color="white" to="/signup">
                    <span>Sign Up</span>
                    <v-icon>cash-marker</v-icon>
                </v-btn>
            </v-toolbar-items>


        </v-toolbar>
        <v-divider dark></v-divider>
    </nav>
</template>

<script>

    export default {

    }
</script>

<style scoped>
    div {
        background-image: inherit;
    }
</style>