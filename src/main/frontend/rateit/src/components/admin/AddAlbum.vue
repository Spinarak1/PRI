<template>
  <div id="addAlbum">
    <div class="addAlbum-form">
      <form @submit.prevent="onSubmit">
        <div class="input">
          <label for="name">Name</label>
          <input
            type="name"
            id="name"
            v-model="name">
        </div>
        <div class="input">
          <label for="released">Release date</label>
          <input
            type="text"
            id="released"
            v-model="released">
        </div>
        <div class="input">
          <label for="rating">Rating</label>
          <input
            type="number"
            id="rating"
            v-model="rating">
        </div>
        <div class="submit">
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
  import axios from '../../axios-auth';
  export default {
    data () {
      return {
        name: '',
        released: '',
        rating: null,
      }
    },
    methods: {
      onSubmit () {
        const formData = {
          name: this.name,
          released: this.released,
          rating: this.rating,
        };

        axios.post('/api/cds', formData)
          .then(resp => {
            console.log(resp);
          })
          .catch(error => console.log(error));
        //this.$store.dispatch('signUp', {email: formData.email, password: formData.password})
      }
    }
  }
</script>

<style scoped>
  .addAlbum-form {
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

  .input select {
    border: 1px solid #ccc;
    font: inherit;
  }

  .hobbies button {
    border: 1px solid #521751;
    background: #521751;
    color: white;
    padding: 6px;
    font: inherit;
    cursor: pointer;
  }

  .hobbies button:hover,
  .hobbies button:active {
    background-color: #8d4288;
  }

  .hobbies input {
    width: 90%;
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
