<template>
    <div>
        <v-card>
            <v-card-title>
                <h2>Records</h2>
            </v-card-title>
            <v-data-table
                    :headers="headers"
                    :items="records"
                    :items-per-page="5"
                    class="elevation-1"
            ></v-data-table>
        </v-card>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data(){
            return {
                records: [],
                headers: [
                    {
                        text: 'ID',
                        align: 'left',
                        sortable: false,
                        value: 'id',
                    },
                    { text: 'Artist', value: 'artist'},
                    { text: 'Released', value: 'released'},
                    { text: 'Name', value: 'name'},
                ],
            }
        },

        created() {
            axios.get('/api/cds?size=100000')
                .then(resp => {
                    console.log(resp);
                    const user = resp.data.cds;

                    user.forEach(cur => {
                        //console.log(`this is cur el ${cur}`)
                        this.records.push(cur);
                        //console.log(this.users)
                    })
                })
                .catch(e => console.log(e));
        },
    }
</script>

<style scoped>

</style>