<template>
    <div>
        <v-btn
                class="my-6 white--text"
                height="60"
                style="background: #FFA255"
                block
                @click="passwordChange = !passwordChange">Change password</v-btn>

        <v-card
                v-if="passwordChange"
                color="#515151"
                class="cardlogin"
                width="50vh"
                height="45vh">
            <v-card-title>Change password</v-card-title>
            <v-card-text>
                <v-form class="px-2">
                    <v-text-field v-model="curPass" class="pt-5" label="Current password" prepend-icon="https"></v-text-field>
                    <v-text-field v-model="newPass" class="pt-5" label="New password" prepend-icon="https"></v-text-field>
                    <v-text-field v-model="confPass" class="pt-5" label="Confirm password" prepend-icon="https"></v-text-field>
                </v-form>
                <v-btn class="px-4" text @click="changePassword()">Change</v-btn>
            </v-card-text>
        </v-card>


        <v-btn
                @click="emailChange = !emailChange"
                class="my-6 white--text"
                height="60"
                style="background: #FFA255"
                block>Change email</v-btn>

        <v-card
                v-if="emailChange"
                color="#515151"
                class="cardlogin"
                width="50vh"
                height="45vh">
            <v-card-title>Change password</v-card-title>
            <v-card-text>
                <v-form class="px-2">
                    <v-text-field class="pt-5" label="Current email" prepend-icon="mail"></v-text-field>
                    <v-text-field class="pt-5" label="New email" prepend-icon="mail"></v-text-field>
                </v-form>
                <v-btn class="px-4" text @click="changeEmail()">Change</v-btn>
            </v-card-text>
        </v-card>

        <v-btn
               class="my-6 white--text"
               height="60"
               style="background: #FFA255"
               block>Change picture</v-btn>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex';
    import axios from 'axios';
    export default {
        name: "Settings",
        data() {
            return {
                curPass: '',
                newPass: '',
                confPass: '',

                passwordChange: false,
                emailChange: false,
            }
        },

        computed: {
            ...mapGetters([
                'getUser',
                'getUserID'
            ])
        },

        methods: {
            changePassword() {
                const passObj = {
                    oldPassword: this.curPass,
                    newPassword: this.newPass,
                    confirmPassword: this.confPass,
                };
                console.log(passObj);
                const userID = this.getUserID;
                axios.post(`/api/users/${userID}/change-password`, passObj)
                    .then(resp => {
                        console.log(resp);
                        alert('Password has been changed');
                    })
                    .catch(e => console.log(e));
                this.passwordChange = false;
            },

            changeEmail() {
                alert('Email has been changed');
                this.emailChange = false;
            }
        }
    }
</script>

<style scoped>

</style>