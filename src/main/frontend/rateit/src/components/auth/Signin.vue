<template>
  <div id="signin">
    <div class="signin-form">
      <form @submit.prevent="onSubmit">
        <div class="input">
          <label for="nick">Nick</label>
          <input
            type="text"
            id="nick"
            v-model="nick">
        </div>
        <div class="input">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            v-model="password">
        </div>
        <div class="input">
          <label for="roles">Role type</label>
          <input
            type="text"
            id="roles"
            v-model="roles">
        </div>
        <p>{{roles}}  </p>
        <div class="submit">
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
    <p>Active user: {{userDetails}} </p>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';
  import { mapGetters } from 'vuex';
  import * as types from '../../store/types';

  export default {
    data () {
      return {
        nick: '',
        password: '',
        roles: '',
      }
    },
    computed: {
      ...mapActions({
        signIn: types.SIGN_IN
      }),

      ...mapGetters({
        userDetails: types.USER_DETAILS
      })
    },
    methods: {
      onSubmit () {
        const formData = {
          nick: this.nick,
          password: this.password,
          roles: this.roles
        };
        this.$store.dispatch(types.SIGN_IN, formData);
        this.nick = '';
        this.password = '';
        //this.roles = false;
      }
    }
  }
</script>

<style scoped>
  .signin-form {
    width: 400px;
    margin: 30px auto;
    border: 1px solid #eee;
    padding: 20px;
    box-shadow: 0 2px 3px #ccc;
  }

  .input {
    margin: 10px auto;
  }

  .input label {
    display: block;
    color: #4e4e4e;
    margin-bottom: 6px;
  }

  .input input {
    font: inherit;
    width: 100%;
    padding: 6px 12px;
    box-sizing: border-box;
    border: 1px solid #ccc;
  }

  .input input:focus {
    outline: none;
    border: 1px solid #521751;
    background-color: #eee;
  }

  .submit button {
    border: 1px solid #521751;
    color: #521751;
    padding: 10px 20px;
    font: inherit;
    cursor: pointer;
  }

  .submit button:hover,
  .submit button:active {
    background-color: #521751;
    color: white;
  }

  .submit button[disabled],
  .submit button[disabled]:hover,
  .submit button[disabled]:active {
    border: 1px solid #ccc;
    background-color: transparent;
    color: #ccc;
    cursor: not-allowed;
  }
</style>
