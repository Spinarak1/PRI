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
          <label for="ratingCount">Rating Count</label>
          <input
            type="number"
            id="ratingCount"
            v-model="ratingCount">
        </div>
        <div class="input">
          <label for="sumOfRating">Sum of Rating</label>
          <input
            type="text"
            id="sumOfRating"
            v-model="sumOfRating">
        </div>
        <div class="input">
          <label for="comment">Review</label>
          <input
            type="text"
            id="comment"
            v-model="comment">
        </div>
        <div class="input">
          <label for="url">Photo url</label>
          <input
            type="text"
            id="url"
            v-model="url">
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
        comment: '',
        name: '',
        ratingCount: null,
        sumOfRating: null,
        released: '',
        url: '',
      }
    },
    methods: {
      onSubmit () {
        const recordData = {
          comment: this.comment,
          name: this.name,
          photoURL: this.url,
          ratingCount: this.ratingCount,
          sumOfRating: this.sumOfRating,
          released: this.released,


        };
        const artistData = {
          stagename: this.stagename
        };

        axios.post('/api/cds', recordData)
          .then(resp => {
            console.log(resp);
          })
          .catch(error => console.log(error));

        /*axios.post('api/artist', artistData)
          .then(resp => {
            console.log(resp);
          })
          .catch(error => console.log(error)); */
        //this.$store.dispatch('signUp', {email: formData.email, password: formData.password})
        this.comment = '';
        this.name = '';
        this.ratingCount = null;
        this.released = '';
        this.sumOfRating = null;
        this.url = '';
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
