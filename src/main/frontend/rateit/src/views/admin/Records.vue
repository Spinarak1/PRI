<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <v-card>
            <v-simple-table v-if="tabVisible">
                <thead>
                <tr>
                    <th class="text-left">ID</th>
                    <th class="text-left">Artist</th>
                    <th class="text-left">Released</th>
                    <th class="text-left">Name</th>
                    <th class="text-left">Action</th>
                </tr>
                </thead>
                <tbody >
                <tr v-for="record in records" :key="record.id">
                    <td >{{ record.id }}</td>
                    <td>{{ record.artist }}</td>
                    <td>{{ record.released }}</td>
                    <td>{{ record.name }}</td>
                    <td style="cursor: pointer;">
                        <span @click="deleteRecord(record.id)">
                            <v-icon>delete</v-icon>
                        </span>

                        <span @click="addPhoto(record.id)">
                            <v-icon>add_photo_alternate</v-icon>
                        </span>


                    </td>
                </tr>
                </tbody>
            </v-simple-table>
        </v-card>
        <div @click="pagin">
            <v-pagination
                    @click="pagin"
                    v-model="page"
                    :length="totalPages"
                    :total-visible="7">
            </v-pagination>
        </div>
        <v-card v-if="pic">
            <v-card-text class="my-7">
                <form enctype="multipart/form-data">
                    <div class="field">
                        <input
                                type="file"
                                ref="file"
                                @change="selectFile">
                    </div>
                    <v-btn class="px-4 my-5" @click="sendFile" text>Change</v-btn>

                </form>
            </v-card-text>
        </v-card>

    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        data(){
            return {
                records: [],
                page: 1,
                totalPages: null,

                tabVisible: true,
                searchArea: '',
                search: ['User by email', 'User by nick', 'User by ID'],

                pic: false,
                file: "",
                cdID: '',
            }
        },

        methods: {

            selectFile() {

                this.file = this.$refs.file.files[0];
            },

            sendFile() {
                const recordId = this.cdID;
                console.log(this.cdID);

                const formData = new FormData();
                formData.append('file', this.file);


                axios.post(`/api/cds/${recordId}/add-image`, formData)
                    .then(resp => {
                        console.log(resp);
                        alert('Photo added')
                    })
                    .catch(e => console.log(e));

            },

            addPhoto(id) {
                console.log(id);
                this.cdID = id;
                this.pic = !this.pic
            },

            deleteRecord(id) {
               //console.log(id);
                axios.delete(`/api/cds/${id}`)
                    .then(resp => {
                        console.log(resp);
                        alert("Record was deleted");
                    })
                    .catch(e => console.log(e))
            },

            pagin() {

                axios.get(`/api/cds?size=8&page=${this.page-1}`)
                    .then(resp => {
                        console.log(resp);
                        const rec = resp.data.cds;

                        this.records = rec;

                    })
                    .catch(e => console.log(e));
            }
        },

        created() {
            axios.get('/api/cds?size=8&page=0')
                .then(resp => {
                    console.log(resp);
                    const records = resp.data.cds;

                    this.records = records;

                    this.totalPages = resp.data.context.totalPages;

                })
                .catch(e => console.log(e));

        },
    }
</script>

<style scoped>

</style>