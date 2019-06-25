<template>
  <div class="panel panel-default">
    <h1>Welcome to the Admin Dashboard!</h1>
    <hr>
    <div class="panel-body">
      <ul v-for="user in showUsers">
        <li class="user">{{ user.nick }} - {{ user.email }}</li>
      </ul>
    </div>
  </div>
</template>

<script>
  import * as types from '../../store/types';
  import { mapGetters } from 'vuex';
  export default {
    data() {
      return {
        msg: 'l'
      }
    },
    computed: {
      ...mapGetters({
        showUsers: types.SHOW_USERS,
      })
    },

    created() {
      this.$store.dispatch(types.FETCH_USERS);
      console.log(this.showUsers)
    },
}
</script>

<style scoped>
  h1, p {
    text-align: center;
  }
  
  .user {
    list-style-type: none;
    text-align: center;
    font-size: 20px;
    letter-spacing: 1px;
  }
</style>
