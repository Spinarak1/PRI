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
                    <v-text-field class="pt-5" label="Current password" prepend-icon="https"></v-text-field>
                    <v-text-field class="pt-5" label="New password" prepend-icon="https"></v-text-field>
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
    export default {
        name: "Settings",
        data() {
            return {
                passwordChange: false,
                emailChange: false,
            }
        },

        methods: {
            changePassword() {
                alert('Password has been changed');
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