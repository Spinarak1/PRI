<template>
    <div>
    <v-card
            style="opacity: 0.85"
            width="65vh"
            height="85vh">
        <v-card-title>Add new user</v-card-title>
        <v-card-text>
            <v-form class="px-2 my-4">
                <v-text-field class="pt-5" label="Username" prepend-icon="person"></v-text-field>
                <v-text-field class="pt-5" label="Email" prepend-icon="email"></v-text-field>
                <v-text-field class="pt-5" label="Password" prepend-icon="https"></v-text-field>
                <v-text-field class="pt-5" label="Confirm password" prepend-icon="replay"></v-text-field>
                <v-radio-group>
                    <v-radio class="mb-4" label="Admin"></v-radio>
                    <v-radio label="User"></v-radio>
                </v-radio-group>
            </v-form>
            <v-btn class="px-4" text>Add new user</v-btn>
          </v-card-text>
    </v-card>
    </div>
</template>

<script>
    export default {
        name: 'NewUser'
    }
</script>

<style scoped>

</style>