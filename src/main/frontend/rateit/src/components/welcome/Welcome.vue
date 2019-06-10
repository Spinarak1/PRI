<template>
  <div id="welcome">
    <!--<h1>Pick the role: </h1>
    <p>(double click)</p>
    <form>
      <label for="user">
        <input
          type="radio"
          name="role"
          id="user"
          value="userProfile"
          v-model="role"
          @click="roleChosen"> User
      </label>
      <label for="admin">
        <input
          type="radio"
          name="role"
          id="admin"
          value="admin"
          v-model="role"
          @click="roleChosen"> Admin
      </label>
    </form>
    <p>{{role}}</p>
    <hr> -->
    <h1>Rate your favourite music!</h1>
    <p>Share it with your friends</p>
    <div class="cta" v-if="userDetails[0].active===false">
      <router-link to="/signup">Sign Up</router-link>
      <router-link to="/signin">Sign In</router-link>
    </div>
  </div>
</template>

<script>
  import { eventBus } from "../../main";
  import { mapGetters } from "vuex";
  import * as types from "../../store/types"

  export default {
    data() {
      return {
        role: 'userProfile'
      }
    },
    methods: {
      roleChosen() {
        eventBus.$emit('roleWasChosen', this.role)
      }
    },
    computed: {
      ...mapGetters({
        userDetails: types.USER_DETAILS
      })
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
