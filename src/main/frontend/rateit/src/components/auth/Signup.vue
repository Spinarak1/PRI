<template>
  <div id="signup">
    <div class="signup-form">
      <form @submit.prevent="onSubmit">
        <div class="input" :class="{invalid: $v.email.$error}">
          <label for="email">Mail</label>
          <input
            type="email"
            id="email"
            @blur="$v.email.$touch()"
            v-model="email">
          <p v-if="!$v.email.email">Please provide a valid email address.</p>
        </div>
        <div class="input">
          <label for="nick">Nick</label>
          <input
            type="text"
            id="nick"
            v-model="nick">
        </div>
        <div class="input">
          <label for="photo">Photo URL</label>
          <input
            type="text"
            id="photo"
            v-model="photo">
        </div>
        <div class="input" :class="{invalid: $v.password.$error}">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            @blur="$v.password.$touch()"
            v-model="password">
        </div>
        <div class="input" :class="{invalid: $v.confirmPassword.$error}">
          <label for="confirm-password">Confirm Password</label>
          <input
            type="password"
            id="confirm-password"
            @blur="$v.confirmPassword.$touch()"
            v-model="confirmPassword">
        </div>
        <div class="submit">
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  import { required, email, minLength, sameAs } from 'vuelidate/lib/validators'
  import axios from 'axios'
  import {router} from '../../main'

  export default {
    data () {
      return {
        nick: '',
        email: '',
        photo: '',
        password: '',
        confirmPassword: ''
      }
    },
    created() {
    },
    validations: {
      email: {
        required,
        email
      },
      password: {
        required,
        minLen: minLength(8)
      },
      confirmPassword: {
        sameAs: sameAs('password')
      }
    },

    methods: {
      onSubmit () {
        const formData = {
          nick: this.nick,
          email: this.email,
          photoURL: this.photo,
          password: this.password,
          passwordConfirm: this.confirmPassword
        };

        axios.get('/api/users')
          .then(resp => {
            const data = resp.data;
            data.forEach(cur => {
              if(cur.email === this.email || cur.nick === this.nick) {
                alert('Email or nickname already in use!');
                this.email = '';
                this.nick = '';
                this.photo = '';
                this.password = '';
                this.confirmPassword = '';
              }
            });

            axios.post('/signup', formData)
              .then(resp => {
                console.log(resp);
              })
              .catch(error => {
                console.log(error)
              });
            alert('registration was successful');
            router.push('/signin');

          });
      }
    }
  }
</script>

<style scoped>
  .signup-form {
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

  .input.inline label {
    display: inline;
  }

  .input input {
    font: inherit;
    width: 100%;
    padding: 6px 12px;
    box-sizing: border-box;
    border: 1px solid #ccc;
  }

  .input.inline input {
    width: auto;
  }

  .input input:focus {
    outline: none;
    border: 1px solid #521751;
    background-color: #eee;
  }

  .input.invalid input {
    border: 1px solid red;
    background-color: #ffc9aa;
  }
  .input.invalid label {
    color: red;
  }

  .input select {
    border: 1px solid #ccc;
    font: inherit;
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
