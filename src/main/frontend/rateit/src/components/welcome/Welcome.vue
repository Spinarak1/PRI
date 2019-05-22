<template>
  <div id="welcome">
    <h1>Pick the role: </h1>
    <form>
      <input
        type="radio"
        name="role"
        value="user"
        v-model="role">
      User <br>
      <input
        type="radio"
        name="role"
        value="admin"
        v-model="role"
        @click="roleChosen">
      Admin <br>
    </form>
    <p>{{role}}</p>
    <hr>
    <h1>Rate your favourite music!</h1>
    <p>Share it with your friends</p>
    <div class="cta">
      <router-link to="/signup">Sign Up</router-link>
      <router-link to="/signin">Sign In</router-link>
    </div>
  </div>
</template>

<script>
  import { eventBus } from "../../main";

  export default {
    data() {
      return {
        role: ''
      }
    },
    methods: {
      roleChosen() {
        eventBus.$emit('roleWasChosen', this.role)
      }
    }
  }
</script>

<style scoped>
  #welcome {
    width: 80%;
    margin: auto;
  }

  h1 {
    text-align: center;
  }

  p {
    text-align: center;
  }

  .cta {
    width: 300px;
    margin: auto;
    text-align: center;
  }

  .cta a {
    margin: 10px;
    text-decoration: none;
    display: inline-block;
    border: 1px solid #521751;
    border-radius: 3px;
    width: 100px;
    padding: 10px;
    box-sizing: border-box;
    color: #521751;
  }

  .cta a:hover,
  .cta a:active {
    background-color: #521751;
    color: white;
  }
  form {
    text-align: center;
  }
</style>
