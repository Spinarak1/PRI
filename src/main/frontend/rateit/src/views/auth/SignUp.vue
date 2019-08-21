<template>
    <div>
        <v-img
            src="https://cdn.pixabay.com/photo/2015/12/27/05/48/turntable-1109588_960_720.jpg"
            height="100vh">
            <Navbar></Navbar>
            <v-container fill-height>
                <v-col sm12 md7 >
                    <v-row
                            align="center"
                            justify="center"
                    >
                        <v-card
                                style="opacity: 0.85"
                                width="50vh"
                                height="60vh">
                            <v-card-title>Sign up to RateIt</v-card-title>
                            <v-card-text>
                                <v-form class="px-2 my-4">
                                    <v-text-field class="pt-5" label="Username" prepend-icon="person"></v-text-field>
                                    <v-text-field class="pt-5" label="Email" prepend-icon="email"></v-text-field>
                                    <v-text-field class="pt-5" label="Password" prepend-icon="https"></v-text-field>
                                    <v-text-field class="pt-5" label="Confirm password" prepend-icon="replay"></v-text-field>
                                </v-form>
                                <v-btn class="px-4" text>Register</v-btn>
                                <h4 class="subheading px-4 py-4">Already have an account?</h4>
                                <v-btn x-small class="text-lowercase px-4       e`wQ21  `" text>Log in here</v-btn>
                            </v-card-text>

                        </v-card>
                    </v-row>
                </v-col>
            </v-container>
        </v-img>
    </div>
</template>

<script>
    import Navbar from "../../components/shared/Navbar";
    export default {
        components: {
            Navbar
        }
    }
</script>