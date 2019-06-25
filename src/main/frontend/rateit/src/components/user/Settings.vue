<template>
  <div v-if="userDetails.length === 1">
    <p>elo</p>
  </div>
</template>

<script>
  import { mapGetters } from "vuex";
  import * as types from "../../store/types";
  export default {
    computed: {
      ...mapGetters({
        userDetails: types.USER_DETAILS
      }),
    }
  }
</script>

<style scoped>

</style>
