<template>
    <div>
        <v-card>
            <v-card-title>
                <h2>Users</h2>
                <v-text-field
                        label="serach"
                        style="background-color: transparent"
                        right
                        class="mx-12 mt-4"
                        hide-details
                        prepend-icon="search"
                        single-line
                        v-model="searchArea"
                        @keypress.13="searchEvent"
                >Search </v-text-field>

                <v-select
                        class="mt-8"
                        :items="search"
                        filled
                        label="Search by"
                        v-model="curSelect"
                ></v-select>
            </v-card-title>
            <v-data-table
                    :headers="headers"
                    :items="users"
                    :items-per-page="5"
                    class="elevation-1"
            ></v-data-table>
        </v-card>
    </div>
</template>

<script>
import axios from 'axios'
    export default {
        data() {
            return {
                curSelect: '',
                searchArea: '',
                search: ['User by email', 'User by nick', 'User by ID'],

                users: [],
                drawer: true,
                items: [
                    { title: 'Dashboard', icon: 'dashboard' },
                    { title: 'Users', icon: 'account_box' },
                    { title: 'Add new user', icon: 'person' },
                    { title: 'Admin', icon: 'gavel' },
                ],
                headers: [
                    {
                        text: 'ID',
                        align: 'left',
                        sortable: false,
                        value: 'id',
                    },
                    { text: 'Username', value: 'nick'},
                    { text: 'Email', value: 'email'},
                    { text: 'Role', value: 'role'},
                ],
            }
        },

        created() {
          axios.get('/api/users')
              .then(resp => {
                  console.log(resp.data.users);
                  const user = resp.data.users;

                  user.forEach(cur => {
                      //console.log(`this is cur el ${cur}`)
                      this.users.push(cur);
                      //console.log(this.users)
                  })
              })
              .catch(e => console.log(e));
        },

        methods: {
            searchEvent() {
                //console.log(this.searchArea + "  " + this.curSelect);
                if(this.curSelect === 'User by nick') {
                    console.log('nick')
                    axios.get('/api/user-by-email')
                        .then(resp => {
                            console.log(resp);
                        })
                        .catch(e => console.log(e));
                } else if(this.curSelect === 'User by email') {
                    console.log('email')
                } else if(this.curSelect === 'User by ID') {
                    console.log('id');
                }
            }
        }
    }
</script>