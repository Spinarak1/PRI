<template>
    <div>
        <v-card>
            <v-card-title>
                <h2>Users</h2>
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
        }
    }
</script>