<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <v-card>
            <v-card-title>
                 <h2 @click="tabVisible=true">Users</h2>
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

            <v-simple-table v-if="tabVisible">
                <thead>
                <tr>
                    <th class="text-left">ID</th>
                    <th class="text-left">Username</th>
                    <th class="text-left">Email</th>
                    <th class="text-left">Role</th>
                    <th class="text-left">Action</th>
                </tr>
                </thead>
                <tbody >
                <tr v-for="user in users" :key="user.id">
                    <td >{{ user.id }}</td>
                    <td>{{ user.nick }}</td>
                    <td>{{ user.email }}</td>
                    <td>{{ user.role }}</td>
                    <td style="cursor: pointer;">
                        <span @click="deleteUser(user.id, user.nick, user.email, user.role)">
                            <v-icon>delete</v-icon>
                        </span>
                        <span @click="setAdmin(user.id, user.nick, user.email, user.role)">
                            <v-icon>person_add</v-icon>
                        </span></td>
                </tr>
                </tbody>
            </v-simple-table>

            <v-simple-table v-if="!tabVisible">
                <thead>
                <tr>
                    <th class="text-left">ID</th>
                    <th class="text-left">Username</th>
                    <th class="text-left">Email</th>
                    <th class="text-left">Role</th>
                    <th class="text-left">Action</th>
                </tr>
                </thead>
                <tbody >
                <tr>
                    <td >{{ filtered.id }}</td>
                    <td>{{ filtered.nick }}</td>
                    <td>{{ filtered.email }}</td>
                    <td>{{ filtered.role }}</td>
                    <td style="cursor: pointer;">
                        <span @click="deleteUser(filtered.id, filtered.nick, filtered.email, filtered.role)">
                            <v-icon>delete</v-icon>
                        </span>
                        <span @click="setAdmin(filtered.id, filtered.nick, filtered.email, filtered.role)">
                            <v-icon>person_add</v-icon>
                        </span>
                    </td>
                </tr>
                </tbody>
            </v-simple-table>
        </v-card>
        <div class="text-center">
            <v-pagination
                    v-model="page"
                    :length="totalPages"
                    :total-visible="7"
            ></v-pagination>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
    export default {
        data() {
            return {
                page: 1,
                curSelect: '',
                searchArea: '',
                search: ['User by email', 'User by nick', 'User by ID'],
                totalPages: 1,

                filtered: '',

                users: [],
                drawer: true,
                items: [
                    { title: 'Dashboard', icon: 'dashboard' },
                    { title: 'Users', icon: 'account_box' },
                    { title: 'Add new user', icon: 'person' },
                    { title: 'Admin', icon: 'gavel' },
                ],
                tabVisible: true,
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
                  console.log(`Ilość userów:`)
                  console.log(`${this.users.length}`)
              })
              .catch(e => console.log(e));


          axios.get('/api/cds')
              .then(resp => {
                  console.log(resp);
              })
              .catch(e => console.log(e));
        },

        methods: {

            //user.id, user.nick, user.email, user.role
            deleteUser(id, nick, email, role) {
                console.log(`Delete user ${id} ${nick} ${email} ${role}`)

                axios.delete(`/api/users/${id}`)
                    .then(resp => {
                        console.log(resp);
                        alert('User was removed');
                    })
                    .catch(e => console.log(e));
            },

            setAdmin(id, nick, email, role) {
                console.log(`Delete user ${id} ${nick} ${email} ${role}`)

                axios.post(`/api/users/${id}/set-as-admin`)
                    .then(resp => {
                        console.log(resp);
                        alert('User was set to Admin');
                    })
                    .catch(e => console.log(e));
            },



            searchEvent() {
                this.tabVisible = false;
                //console.log(this.searchArea + "  " + this.curSelect);
                if(this.curSelect === 'User by nick') {
                    console.log('nick')
                    axios.get(`/api/user-by-nick?nick=${this.searchArea}`)
                        .then(resp => {
                            console.log(resp.data);
                            this.filtered = resp.data;
                        })
                        .catch(e => {
                            console.log(e)
                            alert('This user does not exist')
                        });
                } else if(this.curSelect === 'User by email') {
                    axios.get(`/api/user-by-email?email=${this.searchArea}`)
                        .then(resp => {
                            console.log(resp.data);
                            this.filtered = resp.data;
                        })
                        .catch(e => {
                            console.log(e);
                            alert('This email does not exist');
                        });
                } else if(this.curSelect === 'User by ID') {
                    axios.get(`/api/users/${this.searchArea}`)
                        .then(resp => {
                            console.log(resp.data);
                            this.filtered = resp.data;
                        })
                        .catch(e => {
                            console.log(e)
                            alert('User with this id doesn\'t exis');
                        });
                }
            }
        }
    }
</script>

<style scoped>
    h4 {
        cursor: pointer;
    }

    h4:hover {
        background: #696969;
        cursor: pointer;
    }
</style>