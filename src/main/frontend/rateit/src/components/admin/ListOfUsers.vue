<template>
  <div class="panel panel-default">
    <h1>Welcome to the Admin Dashboard!</h1>
    <hr>
    <div class="panel-body">
      <div class="card text-center">
        <div class="card-header">
          <p v-for="user in showUsers">{{ user.nick }} - {{ user.email }}</p> <br>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as types from '../../store/types';
  import { mapGetters } from 'vuex';
  export default {
    data() {
      return {
        msg: 'no elo ulane kurwy'
      }
    },
    created() {
      this.$store.dispatch(types.FETCH_USERS);
    },
    computed: {
      ...mapGetters({
        showUsers: types.SHOW_USERS,
      })
    }
}
</script>

<style scoped>
  h1, p {
    text-align: center;
  }

</style>
