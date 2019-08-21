<template>
    <div>
        <v-card
                style="opacity: 0.85"
                width="65vh"
                height="80vh">
            <v-card-title>Add new album</v-card-title>
            <v-card-text class="">
                <v-form class="px-2 my-4">
                    <v-text-field class="pt-5" label="Band name"></v-text-field>
                    <v-text-field class="pt-5" label="Record name"></v-text-field>
                    <v-text-field class="pt-5" label="Released"></v-text-field>
                    <v-text-field class="pt-5" label="Year"></v-text-field>
                </v-form>
                <v-btn class="px-4" text>Add new album</v-btn>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
    export default {
        name: "NewAlbum"
    }
</script>

<style scoped>

</style>