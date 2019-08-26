<template>
    <div>
        <v-img
            src="https://cdn.pixabay.com/photo/2016/11/21/12/59/electronics-1845272_960_720.jpg"
            height="100vh">
            <Navbar></Navbar>
            <v-container fill-height>
                <v-col sm12 md7 >
                    <v-row
                            align="center"
                            justify="center"
                            >
                        <v-card
                                class="cardlogin"
                                width="50vh"
                                height="55vh">
                            <v-card-title>Login to RateIt</v-card-title>
                            <v-card-text>
                                <v-form class="px-2">
                                    <v-text-field v-model="username" class="pt-5" label="Username" prepend-icon="person"></v-text-field>
                                    <v-text-field v-model="password" :type="'password'" class="pt-5" label="Password" prepend-icon="https"></v-text-field>
                                </v-form>
                                <v-btn @click="login({username: username, password: password})" class="px-4" text>Log in</v-btn>
                                <h4 class="subheading px-4 py-4">Don't have an account yet?</h4>
                                <span class="subheading px-4">Create an account now!</span>
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
    import axios from "axios"
    import { mapGetters } from 'vuex'
    import { mapMutations } from 'vuex'
    import { mapActions } from 'vuex'

    export default {

        data() {
            return {
                show: false,
                username: '',
                password: ''
            }
        },

        computed: {
          ...mapGetters([
              'getUser',
          ])
        },

        methods: {
            ...mapMutations([
                'activeUser'
            ]),

            ...mapActions([
               'login'
            ]),



          login() {
              const userObj = {
                  username: this.username,
                  password: this.password
              };
                //console.log(userObj)
              axios.post('/api/signin', userObj)
                  .then(resp => {

                      console.log(resp);
                      alert('Logged successfully!')
                      if(resp.data.role === "USER") {
                          this.$router.push('dashboard')
                      } else {
                          this.$router.push('admindashboard')
                      }

                      this.$store.dispatch('signIn', resp.data)
                  })
                  .catch(e => {
                      console.log(e);
                      alert('Something went wrong, try again')
                  });
          }
        },

        components: {
            Navbar
        }
    }
</script>

<style scoped>
    .cardlogin {
        opacity: 0.5;
    }
</style>