<template>
    <div>
        <v-card>
            <v-card-title>
                <h2>Users</h2>
            </v-card-title>
            <v-data-table
                    :headers="headers"
                    :items="dummy"
                    :items-per-page="5"
                    class="elevation-1"
            ></v-data-table>
        </v-card>
    </div>
</template>

<script>

    export default {
        data() {
            return {
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
                    { text: 'Username', value: 'username'},
                    { text: 'Email', value: 'email'},
                    { text: 'Role', value: 'role'},
                    { text: 'Status', value: 'status'},
                    { text: 'Registration date', value: 'registration_date'},
                ],

                dummy: [
                    { id:1, username: 'Krzyś', email: 'duppa@gmail.com', role: 'admin', status: 'active', registration_date: '01-01-1970' },
                    { id:2, username: 'Hubson', email: 'duppa@gmail.com', role: 'user', status: 'banned', registration_date: '01-01-1970' },
                    { id:3, username: 'Tomek', email: 'duppa@gmail.com',role: 'user', status: 'active', registration_date: '01-01-1970' },
                    { id:4, username: 'Maciek', email: 'duppa@gmail.com',role: 'user', status: 'active', registration_date: '01-01-1970' },
                    { id:5, username: 'Kandul cwel', email: 'duppa@gmail.com',role: 'user', status: 'banned', registration_date: '01-01-1970' },
                    { id:6, username: 'Łuki', email: 'duppa@gmail.com',role: 'admin', status: 'active', registration_date: '01-01-1970' },
                ]
            }
        }
    }
</script>