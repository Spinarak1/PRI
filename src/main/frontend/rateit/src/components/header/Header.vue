<template>
  <header id="header">
    <div class="logo">
      <router-link to="/">Rate It!</router-link>
    </div>
    <nav>
      <ul v-if="role==='user'">
        <li>
          <router-link to="/user">User</router-link>
        </li>
        <li>
          <router-link to="/records">Records</router-link>
        </li>
        <li>
          <router-link to="/user/dashboard">Dashboard</router-link>
        </li>
        <li>
          <router-link to="/signup">Sign Up</router-link>
        </li>
        <li>
          <router-link to="/signin">Sign In</router-link>
        </li>
        <li>
          <router-link to="/">Logout</router-link>
        </li>
      </ul>
      <ul v-else>
        <li>
          <router-link to="/admin/dashboard">Admin Dashboard</router-link>
        </li>
        <li>
          <router-link to="/admin/addalbum">Add album</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
  import { eventBus} from "../../main";


  export default {
    data() {
      return {
        role: null,
        search: ''
      }
    },
    created() {
      eventBus.$on('roleWasChosen', (role) => {
        this.role = role;
        console.log(`Poszlo ${role}`);
      });
      console.log(this.role);
    },
    methods: {
      searchValue(event) {
        //this.eventBus.$emit('searchValue', this.search);
      }
    }
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
