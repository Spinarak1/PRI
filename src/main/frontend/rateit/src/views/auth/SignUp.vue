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
                                height="70vh">
                            <v-card-title>Sign up to RateIt</v-card-title>
                            <v-card-text>
                                <v-form class="px-2 my-4">
                                    <v-text-field hint="at least 5 characters" v-model="username" class="pt-2" label="Username" prepend-icon="person"></v-text-field>
                                    <v-text-field  v-model="email" class="pt-5" label="Email" prepend-icon="email"></v-text-field>
                                    <v-text-field hint="at least 6 characters"  v-model="password" :type="'password'" class="pt-5" label="Password" prepend-icon="https"></v-text-field>
                                    <v-text-field hint="confirm"  v-model="conPass" :type="'password'" class="pt-5" label="Confirm password" prepend-icon="replay"></v-text-field>
                                </v-form>
                                <v-btn @click="register()" class="px-4" text>Register</v-btn>
                                <h4 class="subheading px-4 py-4">Already have an account?</h4>
                                <v-btn x-small class="text-lowercase px-4" text @click="toLogin">Log in here</v-btn>
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
    import axios from 'axios'


    export default {
        data() {
            return {
                username: '',
                email: '',
                password: '',
                conPass: '',
            }
        },

        methods: {
            register() {

                if(this.password.length >= 6 && this.username.length >= 5 ) {
                    const userObj = {
                        username: this.username,
                        email: this.email,
                        password: this.password,
                        confirmPassword: this.conPass
                    };

                    //console.log(userObj);

                    axios.post('/api/signup', userObj)
                        .then(resp => {
                            console.log(resp);
                            alert('Your account was created successfully');
                            this.$router.push('signin')
                        })
                        .catch(e => {
                            console.log(e);
                            alert('Something went wrong, please try again')
                        });
                } else {
                    alert('Password must be at least 6 characters long and username 5.');
                }
            },

            toLogin() {
                this.$router.push('signin')
            },
        },

        components: {
            Navbar,
        }
    }
</script>