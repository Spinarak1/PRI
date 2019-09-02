<template>
    <div>
        <v-container>
            <v-col sm="12" md="4" >
                <h3>Input the day value</h3>
                <v-row justify="center">

                    <v-form class="px-2 my-4">
                        <v-text-field
                                v-model="days"
                                class="pt-5"
                                @input="dayVal"></v-text-field>
                    </v-form>
                </v-row>

                <v-row justify="center">
                    <div class="my-2" @click="download">
                        <v-btn
                                x-large
                                color="success"
                                dark
                        >Download Admin report</v-btn>
                    </div>
                </v-row>
            </v-col>
        </v-container>
    </div>
   </template>

<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                days: null,
            }
        },

        methods: {
            download() {
                console.log(this.days);

                axios.get(`/api/generate-raport?days=${this.days}`, {
                    responseType: 'arraybuffer'
                })
                    .then((response) => {
                        console.log(response)

                        let blob = new Blob([response.data], { type: 'application/pdf' } )
                        let url = window.URL.createObjectURL(blob);

                        window.open(url);
                    })
            },

            dayVal(val) {
                console.log(val);
                this.days = val;
            },
        }
    }
</script>

<style scoped>

</style>