<template>
  <header id="header">
    <div class="logo">
      <router-link to="/">Rate It!</router-link>
    </div>

    <nav v-if="userDetails.length === 1">
      <ul>
        <li>
          <router-link to="/dashboard">Dashboard</router-link>
        </li>

        <li>
          <router-link to="/settings">Settings</router-link>
        </li>

        <li @click="logMeOut">
          <router-link to="/">Logout</router-link>
        </li>
      </ul>
    </nav>

    <nav v-else-if="userDetails.length !== 1">
      <ul>
        <li>
          <router-link to="/signup">Sign up</router-link>
        </li>
        <li>
          <router-link to="/signin">Sign in</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
  import { eventBus } from "../../main";
  import { mapGetters } from "vuex";
  import { mapMutations } from "vuex";
  import { mapActions } from "vuex";
  import * as types from "../../store/types";

  export default {
    data() {
      return {
        isLogged: false,
        role: null,
        search: ''
      }
    },
    created() {
      /*eventBus.$on('roleWasChosen', (role) => {
        this.role = role;
        console.log(`Poszlo ${role}`);
      });
      console.log(this.role); */
    },
    computed: {
      ...mapGetters({
        userDetails: types.USER_DETAILS
      }),
      /*...mapMutations({
        logout: types.LOG_OUT
      }),*/
      ...mapActions({
        logOut: types.SIGN_OUT
      })
    },
    methods: {
      logMeOut() {
        this.$store.dispatch(types.SIGN_OUT);
        this.$forceUpdate();
      }
    },
  }
</script>

<style scoped>
  #header {
    height: 56px;
    display: flex;
    flex-flow: row;
    justify-content: space-between;
    align-items: center;
    background-color: #521751;
    padding: 0 20px;
  }

  .logo {
    font-weight: bold;
    color: white;
  }

  .logo a {
    text-decoration: none;
    color: white;
  }

  nav {
    height: 100%;
  }

  ul {
    list-style: none;
    margin: 0;
    padding: 0;
    height: 100%;
    display: flex;
    flex-flow: row;
    align-items: center;
  }

  li {
    margin: 0 16px;
  }

  li a {
    text-decoration: none;
    color: white;
  }

  li a:hover,
  li a:active,
  li a.router-link-active {
    color: #fa923f;
  }
</style>


<!--<ul v-else-if="userDetails[0].roles==='admin'">
          <li>
            <router-link to="/admin/dashboard">Admin Dashboard</router-link>
          </li>
          <li>
            <router-link to="/admin/addalbum">Add album</router-link>
          </li>
          <li>
            <router-link to="/admin/adduser">Add userProfile</router-link>
          </li>
        </ul>-->
